package com.lorent.vnc.test;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Panel;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import javax.swing.JFrame;

import com.lorent.vnc.VncViewer;
import com.lorent.vnc.VncViewerListener;



public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args == null || args.length == 0) {
			args = new String[]{"HOST","10.168.150.71","PORT","5900","PASSWORD","1234",
								"View only","Yes",
								"Restricted colors","Full"
			};	//,"Show Controls","No"	
			
			args = new String[] { 
					"HOST", "ID", "PORT", "3",
					"PASSWORD","1234",
					//"HOST", "10.168.150.71", "PORT", "5901","PASSWORD","1234"
					"Restricted colors","Full"
					,"REPEATERHOST", "10.168.100.201", "REPEATERPORT", "5901","USESSL","1","TRUSTALL","1"
					//,"REPEATER","ID:1234" 
					};
		}
		
		
		
		VncViewer v = new VncViewer();
		v.setVncViewerListener(new VncViewerListener() {

			@Override
			public void createDesktopView(Container desktop, Panel buttompanel) {
				// TODO Auto-generated method stub
				JFrame jFrame = new JFrame();
				jFrame.setSize(800, 600);
				jFrame.setVisible(true);
				buttompanel.setVisible(true);
				jFrame.add(buttompanel, BorderLayout.NORTH);
				jFrame.add(desktop, BorderLayout.CENTER);
				jFrame.setTitle("Test ");
				// jFrame.add(desktop);
			}

			@Override
			public void onFatalError(String errString) {
				// TODO Auto-generated method stub
				System.out.println(errString);
			}
			});
		v.mainArgs = args;
		v.inAnApplet = false;
		v.inSeparateFrame = true;

		v.init();
		v.start();
		
		v.setViewOnly(true);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		
		System.out.println("21819-21063".hashCode());
		System.out.println("21063-21819".hashCode());
		System.out.println("21819-21064".hashCode());
		System.out.println("21819,21065".hashCode());
		System.out.println("21811,21811".hashCode());
		System.out.println("9181291812".hashCode());
		System.out.println("1234-1234".hashCode());
		System.out.println(Integer.MAX_VALUE);
		
		try {
			String cmdString = "cmd /c " + "D:\\MyEclipseWorkspaces\\v4.2\\lvmc\\client\\vnc\\winvnc" + " -repeaterhost 10.168.150.71 -connid 15002;";
//			Runtime.getRuntime().exec(cmdString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

}
