package com.lorent.video;

import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.util.Log;

/**
 * Record from Android camera.
 *
 * @author Guillaume Beraudo
 *
 */
public class LntCamera1 extends LntCamera implements PreviewCallback {
     
	protected static final String tag="LntCamera1";
	private long filterCtxPtr;
	private double timeElapsedBetweenFrames = 0;
	private long lastFrameTime = 0;
	private final double expectedTimeBetweenFrames;
	protected final int rotation;

	
	
	public LntCamera1(RecorderParams parameters) {
		super(parameters);
		expectedTimeBetweenFrames = 1d / Math.round(parameters.fps);
		filterCtxPtr = parameters.filterDataNativePtr;
		rotation = parameters.rotation;

		storePreviewCallBack(this);
	}
	
	
	
	

	
	private native void putImage(long filterCtxPtr, byte[] buffer, int rotate);


	public void onPreviewFrame(byte[] data, Camera camera) {
		if (data == null) {
			Log.e(tag, "onPreviewFrame Called with null buffer");
			return;
		}
		//if (filterCtxPtr == 0l) {
		//	Log.e(tag, "onPreviewFrame Called with no filterCtxPtr set");
		//	return;
		//}
		
		int expectedBuffLength = getExpectedBufferLength();
		if (expectedBuffLength != data.length) {
			Log.e(tag, "onPreviewFrame called with bad buffer length " + data.length
					+ " whereas expected is " + expectedBuffLength + " don't calling putImage");
			return;
		}
		
		long curTime = System.currentTimeMillis();
		if (lastFrameTime == 0) {
			lastFrameTime = curTime;
			if(filterCtxPtr != 0)
				putImage(filterCtxPtr, data, rotation);
			return;
		}

		double currentTimeElapsed = 0.8 * (curTime - lastFrameTime) / 1000 + 0.2 * timeElapsedBetweenFrames;
		if (currentTimeElapsed < expectedTimeBetweenFrames) {
//			Log.d(tag, "Clipping frame " + Math.round(1 / currentTimeElapsed) + " > " + fps);
			return;
		}
		lastFrameTime = curTime;
		timeElapsedBetweenFrames = currentTimeElapsed;

		//		Log.d("onPreviewFrame: ", Integer.toString(data.length));
		if(filterCtxPtr != 0)
			putImage(filterCtxPtr, data, rotation);
	}



	@Override
	protected void lowLevelSetPreviewCallback(Camera camera, PreviewCallback cb) {
		camera.setPreviewCallback(cb);
	}

}
