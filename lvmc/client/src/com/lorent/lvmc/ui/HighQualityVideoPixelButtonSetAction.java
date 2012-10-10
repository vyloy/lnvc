package com.lorent.lvmc.ui;

import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class HighQualityVideoPixelButtonSetAction extends VideoPixelButtonSetAction{

	@Override
	public void execute(String pixelValue, String spixelValue,JRadioButton[] highQualityButtons,JRadioButton[] standardQualityButtons,javax.swing.ButtonGroup pixelbuttonGroup,javax.swing.JPanel pixelButtonPanel,final VideoSetupDialog dialog) {
		for (int i = 0; i < highQualityButtons.length; i++) {
			pixelbuttonGroup.add(highQualityButtons[i]);
			pixelButtonPanel.add(highQualityButtons[i]);
			highQualityButtons[i].addActionListener(new java.awt.event.ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JRadioButton btn = (JRadioButton)e.getSource();
					btn.setSelected(true);
					dialog.setVideoParas(false);
				}
			});
			if (pixelValue != null
					&& pixelValue.equals(highQualityButtons[i]
							.getText())) {
				highQualityButtons[i].setSelected(true);
			}else{
				highQualityButtons[i].setSelected(false);
			}
		}
	}
}
