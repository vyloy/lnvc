package com.lorent.vnc.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Panel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import com.lorent.vnc.VncViewer;
import com.lorent.vnc.VncViewerListener;

public class TestRepeater {

//	public static ArrayList<String> waitlist = new ArrayList<String>();
	public static ArrayBlockingQueue<String> waitlist = new ArrayBlockingQueue<String>(1000);
	
	public static void NextView(final String repeaterid,String serverIP){
		//穿越
		String[] args = new String[] { 
				"HOST", "ID", "PORT", repeaterid+"",
				"PASSWORD","1234",
				//"HOST", "10.168.150.71", "PORT", "5901","PASSWORD","1234"
				"Restricted colors","256"
				,"REPEATERHOST", serverIP, "REPEATERPORT", "5901"//,"USESSL","1","TRUSTALL","1"
				//,"REPEATER","ID:1234" 
				};
		
		final VncViewer v = new VncViewer();
		v.setVncViewerListener(new VncViewerListener() {

			@Override
			public void createDesktopView(Container desktop, Panel buttompanel) {
				// TODO Auto-generated method stub
				final JFrame jFrame = new JFrame();
				jFrame.setSize(800, 600);
				jFrame.setVisible(true);
				buttompanel.setVisible(true);
				jFrame.add(buttompanel, BorderLayout.NORTH);
				jFrame.add(desktop, BorderLayout.CENTER);
				jFrame.setTitle(repeaterid+"");
				
				new Thread(){

					@Override
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (v.isEnabled()) {
							System.out.println("titile:"+repeaterid);
//							waitlist.remove(repeaterid+"");
						}
						else{
							jFrame.dispose();
							waitlist.offer(repeaterid+"");
						}
					}
				}.start();
			}

			@Override
			public void onFatalError(String errString) {
				System.out.println("onFatalError"+errString);
				
				v.setEnabled(false);
				v.disconnect();
				v.destroy();
				v.setVisible(false);
			}
			});
		v.mainArgs = args;
		v.inAnApplet = false;
		v.inSeparateFrame = true;

		v.init();
		v.start();
		
		v.setViewOnly(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		final String serverAddr = "10.168.250.12";
		int viewCount = 15009;
		int nbegin = 15000;
		
		for (int i = nbegin; i <= viewCount; i++) {
//			waitlist.add(i+"");
			waitlist.offer(i+"");
		}
		new Thread(){
			@Override
			public void run() {
				
				while (true) {
					
					String poll;
					try {
						poll = waitlist.poll(1000, TimeUnit.MILLISECONDS);
//						poll = waitlist.poll();
						if (poll != null) {
							String cmdString = "cmd /c " + "D:\\NetBeansProjects\\kangrong\\kangrong\\lvmc\\client\\vnc\\winvnc" + " -repeaterhost "+serverAddr+" -connid "+poll+";";
							Process exec = Runtime.getRuntime().exec(cmdString);
							
							System.out.println(poll+": "+cmdString);
//							Thread.sleep(2000);
//							
//							NextView(poll, serverAddr);
//							System.out.println("NextView: "+poll);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}.start();
		
		
//		for (int i = nbegin; i <= viewCount; i++) {
//			String cmdString = "cmd /c " + "D:\\NetBeansProjects\\kangrong\\kangrong\\lvmc\\client\\vnc\\winvnc" + " -repeaterhost "+serverAddr+" -connid "+i+";";
//			Process exec = Runtime.getRuntime().exec(cmdString);
//			System.out.println(exec+" "+cmdString);
//			Thread.sleep(10000);
//			
//			NextView(i, serverAddr);
//			System.out.println("NextView "+i);
//		}
//		for (int i = nbegin; i <= viewCount; i++) {
//			Thread.sleep(3000);
//			NextView(i, serverAddr);
//			System.out.println("NextView "+i);
//			
//		}
		
//		NextView(3335, serverAddr);
	}

}
