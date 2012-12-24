package com.lorent.common.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.MediaLocator;
import javax.media.NoDataSourceException;
import javax.media.NoPlayerException;

import net.sf.fmj.media.cdp.GlobalCaptureDevicePlugger;
import net.sf.fmj.ui.application.ContainerPlayer;
import net.sf.fmj.ui.application.PlayerPanelPrefs;
import net.sf.fmj.ui.control.TransportControlListener;
import net.sf.fmj.ui.control.TransportControlState;

public class FmjUtil {

	private ContainerPlayer containerPlayer;
	private java.awt.Container container;

	public int start(java.awt.Container cameraPanel) {
		int flag = 0;// 正常
		container = cameraPanel;
		containerPlayer = new ContainerPlayer(container);
		// MediaLocator locator = CaptureDeviceBrowser.run(null); //弹出摄像头设备选择

		MediaLocator locator = null;
		GlobalCaptureDevicePlugger.addCaptureDevices();
		Vector vectorDevices = CaptureDeviceManager.getDeviceList(null);
		if (vectorDevices == null || vectorDevices.size() == 0) {
			System.out.println("没有摄像头===");
			flag = 1;
			return flag;
		}else if(vectorDevices.size() == 1){
			CaptureDeviceInfo infoCaptureDevice = (CaptureDeviceInfo) vectorDevices
			.get(0);
			if("JavaSound".equalsIgnoreCase(infoCaptureDevice.getName())){
				flag = 1;
				return flag;
			}
		}
		// 选择第一个摄像头设备
		for (int i = 0; i < vectorDevices.size(); i++) {
			CaptureDeviceInfo infoCaptureDevice = (CaptureDeviceInfo) vectorDevices
					.get(i);
			System.out.println("设备名==============="
					+ infoCaptureDevice.getName());
			
			locator = infoCaptureDevice.getLocator();
			
			break;
		}

		PlayerPanelPrefs prefs = new PlayerPanelPrefs();
		try {
			containerPlayer.setMediaLocation(locator.toExternalForm(),
					prefs.autoPlay);
		} catch (Exception e) {
			e.printStackTrace();
			flag = 1;
		}
		
		return flag;
	}

	public BufferedImage takePhone() {
		Dimension imageSize = container.getSize();
		BufferedImage image = new BufferedImage(imageSize.width,
				imageSize.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		// cameraPanel.paint(image.getGraphics());
		container.paint(g);
		g.dispose();
		if (containerPlayer != null) {
			containerPlayer.stop();
		}
		return image;
	}

	public void repeatPhone() {
		if (containerPlayer != null) {
			containerPlayer.start();
		}
	}

	public void closeCamera() {
		if (containerPlayer != null) {
			containerPlayer.close();
			containerPlayer = null;
		}
	}
}
