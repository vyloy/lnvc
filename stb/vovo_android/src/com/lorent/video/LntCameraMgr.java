package com.lorent.video;

import java.util.List;

import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.lorent.video.LntCamera.RecorderParams;

/**
 * Manage the video capture, only one for all cameras.
 *
 * @author Guillaume Beraudo
 *
 */
public class LntCameraMgr {
	private static final String tag = "LntCamMgr";
	private static LntCameraMgr instance;

	/**
	 * @return instance
	 */
	public static final synchronized LntCameraMgr getInstance() {
		if (instance == null) {
			instance = new LntCameraMgr();
		}
		return instance;
	}

	private LntCamera.RecorderParams parameters;
	private final LntCameraConf cc;
	private SurfaceView surfaceView;
	private boolean muted;
	private int cameraId;

	private LntCamera recorder;
	private List<Size> supportedVideoSizes;
	private int phoneOrientation;
	public int getPhoneOrientation() {return phoneOrientation;}
	public void setPhoneOrientation(int degrees) {this.phoneOrientation = degrees;}

	private int frontCameraId;
	private int rearCameraId;

	
	// singleton
	private LntCameraMgr() {
		cc = new LntCameraConf();

		int[] fId = {-1};int[] rId = {-1};int[] cId = {-1};
		cc.findFrontAndRearCameraIds(fId, rId, cId);
		frontCameraId=fId[0];rearCameraId=rId[0];cameraId=cId[0];
	}


	public boolean hasSeveralCameras() {
		return frontCameraId != rearCameraId;
	}

	
	public void setUseFrontCamera(boolean value) {
		if (cc.isFrontCamera(cameraId) == value) return; // already OK

		toggleUseFrontCamera();
	}

	public boolean isUseFrontCamera() {return cc.isFrontCamera(cameraId);}
	public boolean toggleUseFrontCamera() {
		boolean previousUseFront = cc.isFrontCamera(cameraId);

		cameraId = previousUseFront ? rearCameraId : frontCameraId;

		if (parameters != null) {
			parameters.cameraId = cameraId;
			if (isRecording()) {
				stopVideoRecording();
				tryToStartVideoRecording();
			}
		}

		return !previousUseFront;
	}


	
	public void setParametersFromFilter(long filterDataPtr, int height, int width, float fps) {
		stopVideoRecording();
		RecorderParams p = new RecorderParams(filterDataPtr);
		p.fps = fps;
		p.width = width;
		p.height = height;
		p.cameraId = cameraId;
		parameters = p;
		tryToStartVideoRecording();
	} 
	
	
	public final void setSurfaceView(final SurfaceView sv, final int phoneOrientation) {
		this.phoneOrientation = phoneOrientation;
		/*
		SurfaceHolder holder = sv.getHolder();
	    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		holder.addCallback(new Callback() {
			public void surfaceDestroyed(SurfaceHolder holder) {
				surfaceView = null;
				Log.d(tag , "Video capture surface destroyed");
				stopVideoRecording();
			}

			
			public void surfaceCreated(SurfaceHolder holder) {
				surfaceView = sv;
				Log.d(tag , "Video capture surface created");
				tryToStartVideoRecording();
			}

			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				Log.d(tag , "Video capture surface changed");
			}
		});
		*/
	}
	
	public void setMuted(boolean muteState) {
		if (muteState == muted) return;
		muted = muteState;
		if (muted) {
			stopVideoRecording();
		} else {
			tryToStartVideoRecording();
		}
	}
	public boolean toggleMute() {
		setMuted(!muted);
		return muted;
	}
	public boolean isMuted() {
		return muted;
	}

	public void tryResumingVideoRecording() {
		if (isRecording()) return;
		tryToStartVideoRecording();
	}
	
	private void tryToStartVideoRecording() {
		//if (muted || surfaceView == null || parameters == null) return;
		if (muted || parameters == null) return;
		
		parameters.rotation = bufferRotationForCorrectImageOrientation();

		//parameters.surfaceView = surfaceView;
		//if (Version.sdkAbove(9)) {
		//	recorder = new LntCamera1(parameters);
		//} else if (Version.sdkAbove(8)) {
		//	recorder = new AndroidCameraRecord8Impl(parameters);
		recorder = new LntCamera5(parameters);

		recorder.startPreview();
	}

	public void stopVideoRecording() {
		if (recorder != null) {
			recorder.stopPreview();
			recorder = null;
		}
	}

	
	// FIXME select right camera
	/**
	 * Eventually null if API < 5.
	 * 
	 */
	public List<Size> supportedVideoSizes() {
		if (supportedVideoSizes != null) {
			return supportedVideoSizes;
		}

		if (recorder != null) {
			supportedVideoSizes = recorder.getSupportedVideoSizes();
			if (supportedVideoSizes != null) return supportedVideoSizes;
		}

		supportedVideoSizes = LntCamera5.oneShotSupportedVideoSizes();
		
		// eventually null

		return supportedVideoSizes;
	}


	public boolean isRecording() {
		if (recorder != null) {
			return recorder.isStarted();
		}

		return false;
	}

	
	public void invalidateParameters() {
		stopVideoRecording();
		parameters = null;
	}

	public boolean outputIsPortrait() {
		final int rotation = bufferRotationForCorrectImageOrientation();
		final boolean isPortrait = (rotation % 180) == 90;
		
		Log.d(tag, "Camera sensor in portrait orientation ?" + isPortrait);
		return isPortrait;
	}

	
	
	


	public boolean isCameraOrientationPortrait() {
		return (cc.getCameraOrientation(cameraId) % 180) == 90;
	}



	private int bufferRotationForCorrectImageOrientation() {
		final int cameraOrientation = cc.getCameraOrientation(cameraId);
		final int rotation = 0;
		Log.d(tag, "Capture video buffer will need a rotation of " + rotation
				+ " degrees : camera " + cameraOrientation
				+ ", phone " + phoneOrientation);
		return rotation;
	}
}
