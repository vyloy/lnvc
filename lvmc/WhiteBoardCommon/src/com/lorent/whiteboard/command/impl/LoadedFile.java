package com.lorent.whiteboard.command.impl;

import javax.swing.SwingUtilities;

import com.lorent.whiteboard.command.ClientRunnable;
import com.lorent.whiteboard.model.CommandsManager;
import com.lorent.whiteboard.model.LoadingFileDialog;

public class LoadedFile implements ClientRunnable {

	private static final long serialVersionUID = 1L;
	private String whiteboardId;

	public LoadedFile(String whiteboardId) {
		this.whiteboardId = whiteboardId;
	}

	@Override
	public void run(CommandsManager manager) {
		final LoadingFileDialog d=(LoadingFileDialog) manager.removeAttribute(whiteboardId);
		if(d==null)
			return;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				d.loaded();
			}
		});
	}

}
