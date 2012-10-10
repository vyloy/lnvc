package com.lorent.lvmc.ui;

import javax.swing.JRadioButton;

public abstract class VideoPixelButtonSetAction {
	public abstract void execute(String pixelValue, String spixelValue,
			JRadioButton[] highQualityButtons,
			JRadioButton[] standardQualityButtons,
			javax.swing.ButtonGroup pixelbuttonGroup,
			javax.swing.JPanel pixelButtonPanel,VideoSetupDialog dialog);
}
