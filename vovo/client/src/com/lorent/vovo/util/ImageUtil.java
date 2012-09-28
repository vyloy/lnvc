package com.lorent.vovo.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ImageUtil {
	public static final String IMAGE_ROOT = "/com/lorent/vovo/resource/images/";
	public static final String TRAY_ICON_ROOT = IMAGE_ROOT + "trayicon/";
	public static final String TRAY_ICON_REC_MSG = TRAY_ICON_ROOT + "rec_msg.gif";
	public static final String GROUP_SMALL_ICON = IMAGE_ROOT + "group40.png";
	public static final String GROUP_BIG_ICON = IMAGE_ROOT + "group60.png";
	
	
	public static Image getImage(String path) throws IOException{
//		return ImageIO.read(ImageUtil.class.getResource(path));
		return Toolkit.getDefaultToolkit().createImage(ImageUtil.class.getResource(path));
	}
	
	public static ImageIcon getImageIcon(String path){
		return new ImageIcon(ImageUtil.class.getResource(path));
	}
	
	public static void captureToFile(BufferedImage img, String endWith, File file)throws Exception
	  {
	    if (!file.exists()) {
	      file.mkdirs();
	      file.createNewFile();
	    }
	    try
	    {
	      ImageIO.write(img, endWith, file);
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	
	public static BufferedImage bufferImage(Image image, int type) { 
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), type); 
		Graphics2D g = bufferedImage.createGraphics(); 
		g.drawImage(image, null, null);
		return bufferedImage; 
	} 
	
	public static void adjustLabelIcon(javax.swing.JLabel label,ImageIcon icon){
		BufferedImage bufferedImage = bufferImage(icon.getImage(),BufferedImage.TYPE_INT_RGB);
		bufferedImage = PicScale.getInstance().resizeImage(ImageUtil.bufferImage(bufferedImage, BufferedImage.TYPE_INT_RGB), label.getMaximumSize().width,
				label.getMaximumSize().height);
		icon = null;
		setLabelIcon(label,bufferedImage);
	}
	
	private static void setLabelIcon(javax.swing.JLabel label,Image image){
		ImageIcon icon1 = new ImageIcon(image);
		label.setIcon(icon1);
	}
	
	public static void adjustLabelIcon(javax.swing.JLabel label,String imgPath){
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(ImageUtil.class.getResource(imgPath));
		} catch (Exception e) {
			imgPath = Constants.SYSTEM_HEAD_IMAGE_PATH_SYS + "head_1.jpg";
			try {
				bufferedImage = ImageIO.read(ImageUtil.class.getResource(imgPath));
			} catch (IOException e1) {
				
			}
		}
		bufferedImage = PicScale.getInstance().resizeImage(ImageUtil.bufferImage(bufferedImage, BufferedImage.TYPE_INT_RGB), label.getMaximumSize().width,
				label.getMaximumSize().height);
		setLabelIcon(label,bufferedImage);
	}
}
