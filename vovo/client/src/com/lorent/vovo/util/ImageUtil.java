package com.lorent.vovo.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.ui.HeadImagePanel;

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
	
	public static void adjustLabelIcon(javax.swing.JLabel label,BufferedImage resultImage){
		BufferedImage bufferedImage = PicScale.getInstance().resizeImage(resultImage, label.getMaximumSize().width,
				label.getMaximumSize().height);
		setLabelIcon(label,bufferedImage);
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
	
	public static void adjustLabelIcon(javax.swing.JLabel label,MemberBean bean){
		if(bean.getIsCustomPic()==0){
			if (bean.getDefaultImg() != null) {
				try {
//					ImageIcon image = new ImageIcon(getClass().getResource("/com/lorent/vovo/resource/images/systemheads/sys/" + bean.getDefaultImg()));
					//				Image temp = image.getImage().getScaledInstance(this.iconLabel.getMaximumSize().width, this.iconLabel.getMaximumSize().height,
					//						image.getImage().SCALE_DEFAULT);
					//				image = new ImageIcon(temp);
					//				this.iconLabel.setIcon(image);
					ImageUtil.adjustLabelIcon(label,
							Constants.SYSTEM_HEAD_IMAGE_PATH_SYS
									+ bean.getDefaultImg());
				} catch (Exception ex) {

				}
			}
		}else{
			try {
				ImageUtil.adjustLabelIcon(label,
						ImageUtil.convertByteArray2BufferedImage(bean.getCustomPic()));
			} catch (Exception ex) {

			}
		}
	}
	
	public static HeadImagePanel generateHeadImagePanel(MemberBean bean, int w, int h){
		HeadImagePanel panel = null;
		if(bean.getIsCustomPic()==0){
			panel = new HeadImagePanel(bean.getDefaultImg(), bean
					.getState(), w, h);
		}else{
			panel = new HeadImagePanel(bean.getCustomPic(), bean
					.getState(), w, h);
		}
		return panel;
	} 
	
	public static byte[] convertBufferedImage2ByteArray(BufferedImage image){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = null;
        try {
			boolean flag = ImageIO.write(image, "jpg", out);
			b = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return b;
	}
	
	public static BufferedImage convertByteArray2BufferedImage(byte[] b) throws Exception{
		ByteArrayInputStream in = new ByteArrayInputStream(b);    //将b作为输入流；
		BufferedImage image = ImageIO.read(in);//将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
		return image;
	}
}
