package org.jhotdraw.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.Reference;
import java.util.BitSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.samples.svg.SVGPanels;
import org.jhotdraw.samples.svg.command.GetImageForPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.client.Client;
import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.command.impl.BroadcastConvertedCommand;
import com.lorent.whiteboard.model.Updater;

public class PicsGenerator implements Runnable {
	
	private static final Logger logger = LoggerFactory
			.getLogger(PicsGenerator.class);

	private final DefaultDrawingView view;
	
	private final File folder;
	
	private final LinkedBlockingQueue<ImageEntity> images=new LinkedBlockingQueue<ImageEntity>();
	
	private final BitSet recieved = new BitSet();

	private DefaultDrawingView virtualView;

	private JFrame jFrame;

	private final JProgressBar progressBar;

	private volatile IOThread ioThread;

	private RequestThread requestThread;

	private final int beginPage;
	
	private final int count;

	public PicsGenerator(DefaultDrawingView view, File folder, int beginPage, int count, JProgressBar progressBar) {
		this.view = view;
		this.folder = folder;
		this.beginPage = beginPage;
		this.count = count;
		this.progressBar=progressBar;
	}

	@Override
	public void run() {
		try {
			virtualView = cloneView();
		} catch (Exception e) {
			logger.error("cloneView",e);
			return;
		}
		
		
		jFrame = new JFrame();
		jFrame.add(virtualView);
		if(virtualView.isDoc()){
			SVGPanels.getInstance().registerPicsGenerator(virtualView.getId(),this);
			final Task task = printPrintablePage();
			requestThread = new RequestThread(task);
			ioThread = new IOThread(virtualView.getId(),count,requestThread);
			requestThread.start();
			ioThread.start();
		}else{
			generateImage(1);
			ioThread = new IOThread(virtualView.getId(),count);
			ioThread.start();
		}

	}

	private Task printPrintablePage() {
		Task task = new Task(view,virtualView,beginPage,count);
		SwingUtilities.invokeLater(task);
		return task;
	}

	protected DefaultDrawingView cloneView() throws IOException, ClassNotFoundException{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bytes);
		out.writeObject(view);
		out.flush();
		out.close();
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()));
		final DefaultDrawingView _view = (DefaultDrawingView) in.readObject();
		in.close();
		return _view;
	}
	
	public synchronized void printImage(final Updater<?> updater,final int page){
		if(recieved.get(page))
			return;
		recieved.set(page);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Collection<BroadcastCommand> commands = virtualView.getPageCommands(page);
				for(BroadcastCommand c:commands){
					if(c instanceof BroadcastConvertedCommand){
						BroadcastConvertedCommand command = (BroadcastConvertedCommand) c;
						command.setUpdater(updater);
					}
				}
				virtualView.setPage(page, false);
				generateImage(page);
			}
		});
	}
	
	public void generateImage(final int page){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				logger.debug("正在生成白板{}第{}页",virtualView.getId(),page);
				Dimension bounds = virtualView.getPreferredSize();
				virtualView.setSize(bounds);
				BufferedImage image = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_RGB);
				Graphics target = image.getGraphics();
				virtualView.printAll(target);
				target.dispose();
				images.offer(new ImageEntity(image,page));
			}
		});
	}
	
	public void interrupt(){
		IOThread ioThread = this.ioThread;
		RequestThread requestThread = this.requestThread;
		if(ioThread!=null){
			ioThread.interrupt();
			if(requestThread!=null){
				requestThread.interrupt();
			}
		}
	}
	
	static class ImageEntity{
		private BufferedImage image;
		private int page;
		public ImageEntity(BufferedImage image, int page) {
			this.image = image;
			this.page = page;
		}
	}
	
	private class Task implements Runnable{
		private int count;

		private int page;
		
		private LinkedList<Integer> unprintablePages=new LinkedList<Integer>();
		
		private DefaultDrawingView view;
		
		private DefaultDrawingView vView;
		
		private volatile Thread thread;
		
		private volatile boolean done;

		public Task(DefaultDrawingView view,DefaultDrawingView vView, int beginPage, int count) {
			this.view=view;
			this.vView=vView;
			this.count = count;
			page = beginPage;
		}
		
		public void run() {
			Collection<BroadcastCommand> commands = view.getPageCommands(page);
			for(BroadcastCommand c:commands){
				if(c instanceof BroadcastConvertedCommand){
					BroadcastConvertedCommand command = (BroadcastConvertedCommand) c;
					Reference<Updater<?>> ref = command.getUpdaterReference();
					Updater<?> updater;
					if(ref==null||(updater = ref.get())==null){
						unprintablePages.add(page);
						next();
						return;
					}
					setVirtualUpdater(updater);
					vView.setPage(page, false);
					generateImage(page);
					logger.debug("printable {} {}",vView,page);
					next();
					return;
				}
			}
		}
		
		private void setVirtualUpdater(Updater<?> updater){
			Collection<BroadcastCommand> commands = virtualView.getPageCommands(page);
			for(BroadcastCommand c:commands){
				if(c instanceof BroadcastConvertedCommand){
					BroadcastConvertedCommand command = (BroadcastConvertedCommand) c;
					command.setUpdater(updater);
					return;
				}
			}
		}
		
		private void next(){
			if(0==--count){
				done();
				return;
			}
			++page;
			SwingUtilities.invokeLater(this);
		}
		
		private void done(){
			done=true;
			LockSupport.unpark(thread);
		}
		
		public LinkedList<Integer> get(){
			thread=Thread.currentThread();
			while(!done){
				LockSupport.park(unprintablePages);
			}
			return unprintablePages;
		}
	}
	
	private class IOThread extends Thread{
		private RequestThread rThread;
		private final String whiteboardId;
		private final int count;

		public IOThread(String whiteboardId,int count) {
			this(whiteboardId, count, null);
		}

		public IOThread(String whiteboardId,int count, RequestThread rThread) {
			super("SaveWhiteboardIOThread");
			this.whiteboardId = whiteboardId;
			this.count = count;
			this.rThread=rThread;
			setDaemon(true);
		}

		@Override
		public void run() {
			File file = new File(folder,whiteboardId);
			if(file.exists()){
				if(file.isFile()){
					return;
				}
			}else{
				if(!file.mkdirs()){
					return;
				}
			}
			try{
				for(int i=1,count=this.count;i<=count&&!isInterrupted();i++){
					ImageEntity image = images.take();
					int page = image.page;
					File output = new File(file,page+".jpg");
					if(rThread!=null){
						ImageEntity peek = images.peek();
						if(peek==null){
							rThread.request();
						}
						rThread.writing(page);
					}
					ImageIO.write(image.image, "jpeg", output);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							progressBar.setValue(progressBar.getValue()+1);
						}
					});
				}
			} catch (InterruptedException e) {
				logger.debug("whiteboard saving interrupted",e);
			} catch (IOException e) {
				logger.error("whiteboard saving error",e);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						JOptionPane.showMessageDialog(null, "所指定的路径不可写，任务被取消，请检查保存目录。", "保存白板出错", JOptionPane.ERROR_MESSAGE);
						JRootPane rootPane = SwingUtilities.getRootPane(progressBar);
						Window parent = (Window) rootPane.getParent();
						parent.dispose();
					}
				});
			}finally{
				SVGPanels.getInstance().removePicsGenerator(whiteboardId);
				if(rThread!=null)
					rThread.interrupt();
			}
			
		}
	}
	
	private class RequestThread extends Thread{
		
		private final Task task;
		
		private final ConcurrentLinkedQueue<Integer> recieved = new ConcurrentLinkedQueue<Integer>();
		
		private final ConcurrentLinkedQueue<Boolean> requestSemaphore = new ConcurrentLinkedQueue<Boolean>();
		
		private static final long parkNanos = 1000000000l;
		
		public RequestThread(Task task) {
			super("SaveWhiteboardRequestThread");
			this.task = task;
			setDaemon(true);
		}

		@Override
		public void run() {
			LinkedList<Integer> requestTask = task.get();
			Client client = Client.getInstance();
			String meetingId = client.getMeetingId();
			String whiteboardId = view.getId();
			int pagecount = virtualView.getPagecount();
			while(!isInterrupted()){
				for(int i:requestTask){
					while(requestSemaphore.peek()==null){
						client.send(new GetImageForPrint(meetingId,whiteboardId,i,pagecount));
						LockSupport.parkNanos(requestSemaphore,parkNanos);
						if(isInterrupted())
							return;
					}
					requestSemaphore.poll();
					if(isInterrupted())
						return;
				}
				requestTask.removeAll(recieved);
				if(requestTask.isEmpty())
					return;
			}
		}
		
		public void writing(int page){
			recieved.offer(page);
		}
		
		public void request(){
			requestSemaphore.offer(Boolean.TRUE);
			LockSupport.unpark(this);
		}
	}
}
