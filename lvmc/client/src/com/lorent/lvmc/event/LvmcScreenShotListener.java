package com.lorent.lvmc.event;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import com.lorent.common.screenshot.ScreenShotListener;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.StringUtil;

public class LvmcScreenShotListener extends ScreenShotListener {

	private static Logger log = Logger.getLogger(LvmcScreenShotListener.class);
	
	public void doSave(final BufferedImage img){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFileChooser chooser = new JFileChooser("d:/");
			    chooser.setFileFilter(new FileNameExtensionFilter("*.gif", new String[] { "gif" }));
			    chooser.setFileFilter(new FileNameExtensionFilter("*.bmp", new String[] { "bmp" }));
			    chooser.setFileFilter(new FileNameExtensionFilter("*.png", new String[] { "png" }));
			    chooser.setSelectedFile(new File(chooser.getCurrentDirectory(), "截图"));
			    int state;
				try {
					state = chooser.showSaveDialog(DataUtil.getTopWindow());
					if (state == 0) {
					      File file = chooser.getSelectedFile();
					      FileFilter filter = chooser.getFileFilter();
					      String descrition = filter.getDescription();
					      String endWith = descrition.substring(2);
					      File saveFile = new File(file.getAbsoluteFile() + "." + endWith);
					      if (saveFile.exists()) {
				                if (JOptionPane.showConfirmDialog(null, StringUtil.getUIString("ShareFileListController.askDownLoad")) != JOptionPane.YES_OPTION) {
				                    return;
				                }
				            }
					      captureToFile(img, endWith, saveFile);
					    }
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	    
	}
	
	public void captureToFile(BufferedImage img, String endWith, File file)
	  {
	    if (!file.exists()) {
	      file.mkdir();
	    }
	    try
	    {
	      ImageIO.write(img, endWith, file);
	    }
	    catch (IOException e) {
	      JOptionPane.showMessageDialog(null, "截图保存出错...");
	      e.printStackTrace();
	    }
	  }
	
	public void doExit(){}
	
}
