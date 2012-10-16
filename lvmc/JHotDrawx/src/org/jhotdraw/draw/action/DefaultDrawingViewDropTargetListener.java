package org.jhotdraw.draw.action;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.samples.svg.SVGPanels;
import org.jhotdraw.samples.svg.figures.SVGImage;

import com.lorent.whiteboard.client.Client;

public class DefaultDrawingViewDropTargetListener implements DropTargetListener {

	private static final long MAX_SIZE=1024L*1024*2;
	private static final HashSet<String> supports=new HashSet<String>(Arrays.asList(".jpg", ".bmp", ".jpeg", ".png"));
	private boolean droped;
	private DefaultDrawingView view;
	
	public DefaultDrawingViewDropTargetListener(
			DefaultDrawingView defaultDrawingView) {
		this.view=defaultDrawingView;
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		if(droped||!SVGPanels.getInstance().isWritable()||view.hasSVGImage()){
			dtde.rejectDrag();
			return;
		}
		boolean r = dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
		if(!r){
			dtde.rejectDrag();
			return;
		}
		
		Transferable transferable = dtde.getTransferable();
		try {
			List<File> data = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
			if(data.isEmpty()){
				dtde.rejectDrag();
				return;
			}
			File file = data.get(0);
			String suffix = file.getName();
			int i = suffix.lastIndexOf('.');
			if(i==-1)
				suffix=null;
			else
				suffix=suffix.substring(i);
			if(suffix!=null&&supports.contains(suffix.toLowerCase())){
				dtde.acceptDrag(DnDConstants.ACTION_LINK);
			}else{
				dtde.rejectDrag();
			}
		} catch (Exception e) {
			dtde.rejectDrag();
			JOptionPane.showMessageDialog(null, "操作失败！文件格式不支持或正被使用！");
		}
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		if(!SVGPanels.getInstance().isWritable()){
			dtde.dropComplete(false);
			return;
		}
		boolean r = dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
		if(!r)
			return;
		dtde.acceptDrop(DnDConstants.ACTION_LINK);
		Transferable transferable = dtde.getTransferable();
		try {
			List<File> data = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
			if(data.isEmpty()){
				dtde.dropComplete(false);
				return;
			}
			File f = data.get(0);
			if(f.length()>MAX_SIZE){
				dtde.dropComplete(false);
				JOptionPane.showMessageDialog(null, "操作失败！文件不能大于2M！");
				return;
			}
			BufferedImage read = ImageIO.read(f);
			String name = f.getName();
			int i = name.lastIndexOf('.');
			Client.getInstance().broadcast(new SVGImage(read,new String(name.substring(i+1))));
			dtde.dropComplete(true);
			droped=true;
		} catch (Exception e) {
			dtde.dropComplete(false);
			JOptionPane.showMessageDialog(null, "操作失败！文件格式不支持或正被使用！");
		}
		
	}

}
