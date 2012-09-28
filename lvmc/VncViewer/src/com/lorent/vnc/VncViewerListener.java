package com.lorent.vnc;


import java.awt.Container;
import java.awt.Panel;



public interface VncViewerListener {
	void createDesktopView(Container desktop,Panel buttompanel);
	
	void onFatalError(String errString);
}
