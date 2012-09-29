/*
 * SendFileList.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author  Jack
 */
public class TransferFileList extends JPanel {

	/** Creates new form BeanForm */
	public TransferFileList() {
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
		setMinimumSize(new java.awt.Dimension(220, 10));
		jScrollPane1.setViewportView(jPanel);
		setLayout(new BorderLayout());
		add(jScrollPane1);
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JScrollPane jScrollPane1;
	// End of variables declaration//GEN-END:variables

	private JPanel jPanel;

	private class ItemEventListener implements
			TransferFileListItem.EventListener {

		@Override
		public void clickCancel(TransferFileListItem item) {
			removeItem(item.getStreamID());
			if (listener != null) {
				listener.clickCancel(item.getStreamID(), item.getFileName());
			}
		}

		@Override
		public void clickAccept(String streamID, String fileName) {
			if (listener != null) {
				listener.clickAccept(streamID, fileName);
			}
		}

		@Override
		public void clickReject(String streamID, String fileName) {
			removeItem(streamID);
			if (listener != null) {
				listener.clickReject(streamID, fileName);
			}
		}

		@Override
		public void cancelInProcess(String streamID, String fileName) {
			removeItem(streamID);
			if (listener != null) {
				listener.cancelInProcess(streamID, fileName);
			}
		}

	}

	private EventListener listener;

	public interface EventListener {
		void clickCancel(String streamID, String fileName);

		void clickAccept(String streamID, String fileName);

		void clickReject(String streamID, String fileName);

		void cancelInProcess(String streamID, String fileName);

		void listIsEmpty();
	}

	public void setEventListener(EventListener listener) {
		this.listener = listener;
	}

	private Map<String, TransferFileListItem> map = new HashMap<String, TransferFileListItem>();

	public void addSendFileItem(File file, String streamID) {
		TransferFileListItem item = new TransferFileListItem();
		item.setSendFileInfo(file.getName(), file.length(), streamID);
		item.setEventListener(new ItemEventListener());
		map.put(streamID, item);
		jPanel.add(item);
		jPanel.revalidate();
	}

	public void addRecvFileItem(String fileName, long fileSize, String streamID) {
		TransferFileListItem item = new TransferFileListItem();
		item.setRecvFileInfo(fileName, fileSize, streamID);
		item.setEventListener(new ItemEventListener());
		map.put(streamID, item);
		jPanel.add(item);
		jPanel.revalidate();
	}

	public void removeItem(String streamID) {
		TransferFileListItem item = map.get(streamID);
		map.remove(streamID);
		jPanel.remove(item);
		jPanel.repaint();
		if (map.size() == 0) {
			if (listener != null) {
				listener.listIsEmpty();
			}
		}
	}

	public void updateInfo(String streamID, int percent) {
		TransferFileListItem item = map.get(streamID);
		if (item != null) {
			item.updateInfo(percent);
		}
	}

	public String getSavePath(String streamID) {
		TransferFileListItem item = map.get(streamID);
		return item.getSavePath();
	}

	public void setSavePath(String streamID, String path) {
		map.get(streamID).setSavePath(path);
	}

	public boolean isSend(String streamID) {
		return map.get(streamID).isSend();
	}

}