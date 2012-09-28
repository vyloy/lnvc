package com.lorent.vovo.ui.screenshot;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.lorent.common.screenshot.ScreenShotListener;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.ui.MessageFrame;
import com.lorent.vovo.util.Constants;

public class VovoScreenShotListener extends ScreenShotListener {

	@Override
	public void doSave(BufferedImage img) {
		final BufferedImage image = img;
		Transferable trans = new Transferable() {
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { DataFlavor.imageFlavor };
			}

			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}

			public Object getTransferData(DataFlavor flavor)
					throws UnsupportedFlavorException, IOException {
				if (isDataFlavorSupported(flavor))
					return image;
				throw new UnsupportedFlavorException(flavor);
			}

		};
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans,
				null);
	}
	
	public void doExit(){
		MessageFrame frame = Vovo.getViewManager().getView(Constants.ViewKey.MESSAGEFRAME.toString());
	    frame.setVisible(true);
	}

}
