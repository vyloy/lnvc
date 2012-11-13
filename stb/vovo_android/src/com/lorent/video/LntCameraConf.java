package com.lorent.video;


//import org.linphone.Hacks;

import android.util.Log;

public class LntCameraConf {
	private static final String tag = "Linphone";

	public void findFrontAndRearCameraIds(int[] frontCameraId, int[] rearCameraId, int[] cameraId) {
		Log.i(tag, "Detecting cameras");
		/*
		if (Hacks.isGalaxyS()) {
			Log.d(tag, "Hack Galaxy S : has 2 cameras front=2; rear=1");
			frontCameraId[0] = 2;
			rearCameraId[0] = 1;
			cameraId[0] = rearCameraId[0];
			return;
		}
		 */
		// default to 0/0
		//frontCameraId[0] = 0;
		//rearCameraId[0] = 1;
		//cameraId[0] = 0;//rearCameraId[0];
	}

	public int getNumberOfCameras() {
		// Use hacks to guess the number of cameras
		/*
		if (Hacks.isGalaxyS()) {
			Log.d(tag, "Hack Galaxy S : has 2 cameras");
			return 2;
		} else
			return 1;
			*/
		return 1;
	}
	


	public int getCameraOrientation(int cameraId) {
		// Use hacks to guess orientation of the camera
		/*
		if (cameraId == 2 && Hacks.isGalaxyS()) {
			Log.d(tag, "Hack Galaxy S : rear camera id=2 ; mounted landscape");
			// mounted in landscape for a portrait phone orientation
			return 90;
		}
		*/
		return 0;
	}




	public boolean isFrontCamera(int cameraId) {
		// Use hacks to guess facing of the camera
		/*
		if (cameraId == 2 && Hacks.isGalaxyS()) {
			Log.d(tag, "Hack Galaxy S : front camera has id=2");
			return true;
		}
		*/
		if (cameraId == 2)
			return true;
		else
			return false;
	}
	
	

}
