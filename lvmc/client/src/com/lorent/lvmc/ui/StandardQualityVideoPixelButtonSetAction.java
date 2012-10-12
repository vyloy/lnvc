package com.lorent.lvmc.ui;

import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StandardQualityVideoPixelButtonSetAction extends VideoPixelButtonSetAction{

	@Override
	public void execute(String pixelValue, String spixelValue,JRadioButton[] highQualityButtons,JRadioButton[] standardQualityButtons,javax.swing.ButtonGroup pixelbuttonGroup,javax.swing.JPanel pixelButtonPanel,final VideoSetupDialog dialog) {
		for (int i = 0; i < standardQualityButtons.length; i++) {
			pixelbuttonGroup.add(standardQualityButtons[i]);
			pixelButtonPanel.add(standardQualityButtons[i]);
			java.awt.event.ActionListener[] listeners = standardQualityButtons[i].getActionListeners();
			if(listeners==null || listeners.length<1){
				standardQualityButtons[i].addActionListener(new java.awt.event.ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						JRadioButton btn = (JRadioButton)e.getSource();
						btn.setSelected(true);
						dialog.setVideoParas(false);
					}
					
				});
			}
			if (spixelValue != null
					&& spixelValue.equals(standardQualityButtons[i]
							.getText())) {
				standardQualityButtons[i].setSelected(true);
			}else{
				standardQualityButtons[i].setSelected(false);
			}
		}
	}
	
}
