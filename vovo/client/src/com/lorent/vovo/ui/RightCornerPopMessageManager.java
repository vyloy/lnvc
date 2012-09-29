package com.lorent.vovo.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

public class RightCornerPopMessageManager {

	public static void showDefaultRightCornerPopMessage(String content){
		RightCornerMessageDialog dialog = new RightCornerMessageDialog(null,false);
		dialog.setContent(content);
		showRightCornerPopMessage(dialog,new DefaultRightCornerPopMessageShowStrategy());
	}
	
	public static void showRightCornerPopMessage(javax.swing.JDialog dialog,RightCornerPopMessageShowStrategy sg){
		sg.setDialog(dialog);
		sg.show();
	}
	
}

