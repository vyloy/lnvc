/*
 * MyTextPane.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.packet.Message;

import com.lorent.vovo.dto.FontStyle;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.ImageUtil;
import com.lorent.vovo.util.VovoStringUtil;

/**
 *
 * @author  Jack
 */
public class MyTextPane extends JTextPane {

	public static final int NOTICE_TYPE_ERROR = 1;
	public static final int NOTICE_TYPE_OK = 2;
	private static java.util.Random random = new java.util.Random();
	private Logger log = Logger.getLogger(MyTextPane.class);

	private ImageIcon errorIcon = new ImageIcon(getClass().getResource(
			"/com/lorent/vovo/resource/images/notice_no.png"));
	private ImageIcon okIcon = new ImageIcon(getClass().getResource(
			"/com/lorent/vovo/resource/images/notice_ok.png"));

	/** Creates new form BeanForm */
	public MyTextPane() {
		initComponents();
		this.addKeyListener(new MyKeyAdapter());
		dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE,   
		        new MyDropListener(), true, null);  
	}

	private class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			MyKeyPressed(e);
		}
	}

	private void MyKeyPressed(KeyEvent e) {
		try {
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				if (this.getSelectedText() == null) {
					if (this.getCaretPosition() > 0) {
						String removeText = this.getText(this
								.getCaretPosition() - 1, 1);
						if (removeText.equals("]")) {
							String temp = "";
							while (!temp.equals("[")) {
								this.getDocument().remove(
										this.getCaretPosition() - 1, 1);
								temp = this.getText(
										this.getCaretPosition() - 1, 1);
							}
							this.getDocument().remove(
									this.getCaretPosition() - 1, 1);
							e.consume();
						}
					}
				}
			} else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
				if (this.getSelectedText() == null) {
					String removeText = this
							.getText(this.getCaretPosition(), 1);
					if (removeText.equals("[")) {
						String temp = "";
						while (!temp.equals("]")) {
							this.getDocument().remove(this.getCaretPosition(),
									1);
							temp = this.getText(this.getCaretPosition(), 1);
						}
						this.getDocument().remove(this.getCaretPosition(), 1);
						e.consume();
					}

				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				String selectText = this.getText(this.getCaretPosition(), 1);
				if (selectText.equals("[")) {
					String temp = "";
					while (!temp.equals("]")) {
						this.setCaretPosition(this.getCaretPosition() + 1);
						temp = this.getText(this.getCaretPosition(), 1);
					}
					this.setCaretPosition(this.getCaretPosition() + 1);
					e.consume();
				}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (this.getCaretPosition() > 0) {
					String selectText = this.getText(
							this.getCaretPosition() - 1, 1);
					if (selectText.equals("]")) {
						String temp = "";
						while (!temp.equals("[")) {
							this.setCaretPosition(this.getCaretPosition() - 1);
							temp = this.getText(this.getCaretPosition() - 1, 1);
						}
						this.setCaretPosition(this.getCaretPosition() - 1);
						e.consume();
					}
				}
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				//TODO
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				//TODO				
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isAltDown() && !e.isControlDown() && !e.isShiftDown()) {
				listener.enterType(e);
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()) {// ctrl + enter
				insertText("\n", style);
			} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
				log.info("ctrl + c");
				//				e.consume();
			} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
				log.info("ctrl + v");
				if(this.isEditable()){
					Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
					if (t != null && t.isDataFlavorSupported(DataFlavor.imageFlavor)) {
						Image img = (Image) t.getTransferData(DataFlavor.imageFlavor);
						String imgFileName = DataUtil.getUserName() + String.valueOf(System.currentTimeMillis()) + random.nextInt(1000000);
						insertIcon(Constants.IMG_FLAG + imgFileName, new ImageIcon(img));
						
					}
				}
				//			    Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
				//			    if (t != null && t.isDataFlavorSupported(DataFlavor.imageFlavor)) {
				//			      Image img = (Image) t.getTransferData(DataFlavor.imageFlavor);
				//			      
				//			      insertIcon(" ", new ImageIcon(img));
				//			      
				//			    }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	public interface EventListener {
		public void enterType(KeyEvent e);
		public void dropFile(File file);
	}

	private EventListener listener;

	public void setEventListener(EventListener l) {
		this.listener = l;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

	public void insertIcon(String name, ImageIcon icon)
			throws BadLocationException {
		if (this.isEditable()) {
			MutableAttributeSet inputAttributes = getInputAttributes();
			String temp = "[" + name + "]";
			Document doc = getStyledDocument();
			doc.insertString(this.getCaretPosition(), " ", inputAttributes);
			inputAttributes.removeAttributes(inputAttributes);
			StyleConstants.setIcon(inputAttributes, icon);
			doc.insertString(this.getCaretPosition(), temp, inputAttributes);
			doc.remove(this.getCaretPosition() - temp.length() - 1, 1);
			inputAttributes.removeAttributes(inputAttributes);
		} else {
			MutableAttributeSet inputAttributes = getInputAttributes();
			String temp = "[" + name + "]";
			Document doc = getStyledDocument();
			doc.insertString(doc.getLength(), " ", inputAttributes);
			inputAttributes.removeAttributes(inputAttributes);
			StyleConstants.setIcon(inputAttributes, icon);
			doc.insertString(doc.getLength(), temp, inputAttributes);
			doc.remove(doc.getLength() - temp.length() - 1, 1);
			inputAttributes.removeAttributes(inputAttributes);
		}

	}

	
	public void insertMsg(String msg, Map<String, ImageIcon> map, FontStyle style, Map<String,String> imgs)throws Exception{
		while(true){

			int imgBI = msg.indexOf("[");
			if (imgBI != -1) {
				if (imgBI != 0) {
					String text = msg.substring(0, imgBI);
					insertText(text, style);
				}
				int imgEI = msg.indexOf("]");
				String picName = msg.substring(imgBI + 1, imgEI);
				if(picName.startsWith(Constants.IMG_FLAG)){
					String key = picName.replaceFirst(Constants.IMG_FLAG, "");
					try{
						if(imgs != null){
							String imgStr = imgs.get(key);
							BufferedImage bufferedImage = VovoStringUtil.decodeStrToImg(imgStr);
							File file = new File(InputArea.SCREENSHOT_SAVE_DIR + key + "." + Constants.SCREENSHOT_IMG_SUFFIX);
							ImageUtil.captureToFile(bufferedImage, Constants.SCREENSHOT_IMG_SUFFIX, file);
							ImageIcon icon = new ImageIcon(bufferedImage);
							insertIcon(picName, icon);
						}else{
							ImageIcon icon = new ImageIcon(InputArea.SCREENSHOT_SAVE_DIR + key + "." + Constants.SCREENSHOT_IMG_SUFFIX);
							insertIcon(picName, icon);
						}
					}catch(Exception ex){
						ImageIcon brokenIcon = new ImageIcon(this.getClass().getResource("/com/lorent/vovo/resource/images/brokenimg.jpg"));
						insertIcon(picName, brokenIcon);
					}
				}else{
					if(map.containsKey(picName)){
						ImageIcon icon = map.get(picName);
						if(icon != null){
							insertIcon(picName, icon);
						}
					}
				}
				msg = msg.substring(imgEI + 1);
			} else {
				insertText(msg + "\n", style);
				break;
			}
		}
		StyledDocument doc = getStyledDocument();
		this.setCaretPosition(doc.getLength());
	}

	public void insertText(String text, FontStyle style) throws Exception {
		StyledDocument doc = getStyledDocument();
		if (style == null) {
			doc.insertString(doc.getLength(), text, null);
		} else {
			doc.insertString(doc.getLength(), text, style.getAttrSet());
		}
	}

	public void insertWhiteSpace(int num, FontStyle style) throws Exception {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			sb.append(" ");
		}
		insertText(sb.toString(), style);
	}

	public void insertTitle(String name, FontStyle nameStyle, Date date,
			FontStyle dateStyle) throws Exception {
		insertText(name, nameStyle);
		insertWhiteSpace(2, null);
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		insertText(sdf.format(date) + "\n", dateStyle);
	}
	
	public void insertMsgAndTitle(String name, FontStyle nameStyle, Date date, FontStyle dateStyle, String msg, Map<String, ImageIcon> map, FontStyle msgStyle, Map<String,String> imgs)throws Exception{

		insertTitle(name, nameStyle, date, dateStyle);
		insertWhiteSpace(2, null);
		insertMsg(msg, map, msgStyle, imgs);
	}

	public void insertBlankRow(int num, FontStyle style) throws Exception {
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			sb.append("\n");
		}
		insertText(sb.toString(), style);
	}

	public void insertNotice(int type, Date date, String text, FontStyle style)
			throws Exception {
		if (type == NOTICE_TYPE_ERROR) {
			insertIcon("error", errorIcon);
		} else if (type == NOTICE_TYPE_OK) {
			insertIcon("ok", okIcon);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		insertWhiteSpace(1, null);
		insertText(sdf.format(date) + "\n", style);
		insertWhiteSpace(2, null);
		insertText(text + "\n", style);
		insertBlankRow(1, style);
	}

	private FontStyle style = new FontStyle();

	public void changeFontStyle(FontStyle style) {
		this.style = style;
		this.setFont(style.getFont());
		this.setForeground(style.getColor());
	}

	public void insertDateSplit(Date date, FontStyle style) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String text = "Date:" + sdf.format(date);
		insertText(text, style);
		insertText("\n", null);
		JPanel jpanel = new JPanel();
		jpanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		jpanel.setBackground(Color.BLUE);
		this.insertComponent(jpanel);
		insertBlankRow(2, style);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame j = new JFrame();
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				j.setSize(800, 600);
				j.add(new MyTextPane());
				j.setVisible(true);

			}
		});
	}

	private DropTarget dropTarget;

	private class MyDropListener extends DropTargetAdapter{


		@Override
		public void drop(DropTargetDropEvent dtde) {
			dtde.acceptDrop(dtde.getDropAction());
			DataFlavor[] dataFlavors = dtde.getCurrentDataFlavors();
			if (dataFlavors[0].match(DataFlavor.javaFileListFlavor)) {
				try {
					Transferable tr = dtde.getTransferable();
					Object obj = tr.getTransferData(DataFlavor.javaFileListFlavor);
					List<File> files = (List<File>) obj;
					for (int i = 0; i < files.size(); i++) {
						if(listener != null){
							listener.dropFile(files.get(i));
						}
					}
				} catch (UnsupportedFlavorException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

		}
		
	}
}