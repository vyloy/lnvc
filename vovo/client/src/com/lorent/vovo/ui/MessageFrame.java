/*
 * MessageFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.vovo.ui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.apache.log4j.Logger;

import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.FlashWindowUtil;

/**
 *
 * @author  Jack
 */
public class MessageFrame extends javax.swing.JFrame {

	private Logger log = Logger.getLogger(MessageFrame.class);

	/** Creates new form MessageFrame */
	public MessageFrame() {
		initComponents();
		this.addWindowListener(new CloseHandler());
		setIconImage(Toolkit.getDefaultToolkit().createImage(
				getClass().getResource(Constants.TRAYICON_PATH)));
		messageFrame = this;
	}

	/**
	 * add by chard.wu 
	 * 主要是因为聊天对话框最大化在第一次关闭后失效，
	 * 在此采用隐藏窗体的方式处理。
	 */
	private static MessageFrame messageFrame = null;

	protected class CloseHandler extends WindowAdapter {

		public void windowClosing(final WindowEvent event) {
			log.info("MessageFrame closing");
			boolean flag = (Boolean) Vovo.exeC("chat", "canRemoveMsgFrame");
			if (flag) {
				messageFrame.setVisible(false);
			}
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setTitle("\u6d88\u606f\u7a97\u53e3");
		setMinimumSize(new java.awt.Dimension(920, 600));
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowActivated(java.awt.event.WindowEvent evt) {
				formWindowActivated(evt);
			}
		});

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void formWindowActivated(java.awt.event.WindowEvent evt) {
		FlashWindowUtil.stop(this);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MessageFrame().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

}