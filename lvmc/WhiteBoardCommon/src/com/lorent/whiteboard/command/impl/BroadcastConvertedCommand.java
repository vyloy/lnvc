package com.lorent.whiteboard.command.impl;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.command.ClientRunnable;
import com.lorent.whiteboard.context.AppContext;
import com.lorent.whiteboard.model.CommandsManager;
import com.lorent.whiteboard.model.LoadingFileDialog;
import com.lorent.whiteboard.model.Sendable;
import com.lorent.whiteboard.model.Updater;
import com.lorent.whiteboard.model.View;
import com.lorent.whiteboard.model.Whiteboard;

public class BroadcastConvertedCommand extends BroadcastCommand implements ClientRunnable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(BroadcastConvertedCommand.class);
	protected transient static Updater<?> loadingImage;
	protected final int pageCount;
	protected transient volatile Reference<Updater<?>> updaterReference;
	
	public BroadcastConvertedCommand(String meetingId, Updater<?> updater,
			String whiteboardId, int page,int pageCount) {
		super(meetingId, updater, whiteboardId, page);
		commandId=1;
		this.pageCount=pageCount;
	}
	
	public void execute(Whiteboard board, IoSession session) {
		if(board.save(this)){
			session.write(this);
			board.getMeeting().broadcast(session,this);
		}else{
			session.write(new ShowMessage("文档已被加载。", 
					"尊敬的用户", JOptionPane.INFORMATION_MESSAGE,new LoadedFile(getSimpleFileName())));
			logger.info("{} Whiteboard {} create failed because of loaded!",board.getMeeting().getMeetingId(),whiteboardId);
		}
	}
	
	private String getSimpleFileName(){
		int index=whiteboardId.indexOf('_');
		if(index!=-1)
			return new String(whiteboardId.substring(index+1));
		return whiteboardId;
	}

	@Override
	public void run(CommandsManager manager) {
		View view = manager.getView(whiteboardId);
		view.execute(this);
		for(int i=2;i<=pageCount;i++){
			BroadcastConvertedCommand clone = this.clone();
			clone.page = i;
			view.executeWithoutCheck(clone);
		}
		final LoadingFileDialog d=(LoadingFileDialog) manager.removeAttribute(getSimpleFileName());
		if(d==null)
			return;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				d.loaded();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends View> Updater<T> getUpdater() {
		if(this.updater!=null){
			this.setUpdaterReference(this.updater);
			try{
				return (Updater<T>) this.updater;
			}finally{
				this.updater=null;
			}
		}
		Updater<?> cache;
		if(updaterReference==null||(cache=updaterReference.get())==null){
			Sendable client = AppContext.get(Sendable.class);
			client.send(new GetImage(meetingId, whiteboardId, page, pageCount));
			logger.debug("Request page {} fileName {}",page,whiteboardId);
			if(loadingImage==null){
				initLoadingImage();
			}
			return (Updater<T>) loadingImage;
		}
		return (Updater<T>) cache;
	}
	
	public void initLoadingImage(){
		try{
			Class<?> c = BroadcastConvertedCommand.class.getClassLoader().loadClass("org.jhotdraw.samples.svg.figures.SVGImage");
			Method method = c.getMethod("getLoadingImage");
			Object o = method.invoke(null);
			loadingImage = (Updater<?>)o;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	@Override
	protected BroadcastConvertedCommand clone(){
		BroadcastConvertedCommand clone=(BroadcastConvertedCommand) super.clone();
		clone.updaterReference=null;
		clone.updater=null;
		return clone;
	}

	public void setUpdaterReference(Updater<?> u){
		this.updaterReference=new SoftReference<Updater<?>>(u);
	}

	public Reference<Updater<?>> getUpdaterReference() {
		return updaterReference;
	}

	public void setUpdater(Updater<?> updater) {
		this.updater=updater;
	}

	@Override
	public String toString() {
		return "BroadcastConvertedCommand [pageCount=" + pageCount
				+ ", commandId=" + commandId + ", whiteboardId=" + whiteboardId
				+ ", meetingId=" + meetingId + "]";
	}

}
