package com.lorent.whiteboard.command.impl;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.lorent.whiteboard.command.ClientRunnable;
import com.lorent.whiteboard.model.CommandsManager;

public class ShowMessage implements ClientRunnable {

	private static final long serialVersionUID = 1L;

	private final String message;
	         
	private final String title;
	         
	private final int messageType;
	         
	private final ClientRunnable runnable;
	
	public ShowMessage(String message, String title, int messageType) {
		this(message, title, messageType, null);
	}
	
	public ShowMessage(String message, String title, int messageType,
			ClientRunnable runnable) {
		this.message = message;
		this.title = title;
		this.messageType = messageType;
		this.runnable = runnable;
	}

	@Override
	public void run(final CommandsManager manager) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(runnable!=null)
					runnable.run(manager);
				JOptionPane.showMessageDialog(null, message, title, messageType);
			}
		});
	}

}
