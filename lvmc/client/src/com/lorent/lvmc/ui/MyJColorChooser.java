package com.lorent.lvmc.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.JColorChooser;
import javax.swing.JDialog;

public class MyJColorChooser extends JColorChooser {

	
	public static Color showDialog(Component component,
	        String title, Color initialColor) throws HeadlessException {

	        final JColorChooser pane = new JColorChooser(initialColor != null?
	                                               initialColor : Color.white);

//	        ColorTracker ok = new ColorTracker(pane);
	        
	        JDialog dialog = createDialog(component, title, true, pane, null, null);
	        dialog.setUndecorated(true);
	        dialog.show(); // blocks until user brings dialog down...

	        return pane.getColor();
	    }
}
