package com.lorent.video;

import android.hardware.Camera;
import java.util.List;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;


public class LntCamera5 extends LntCamera1 {

	public LntCamera5(RecorderParams parameters) {
		super(parameters);
	}

	@Override
	protected void onSettingCameraParameters(Parameters parameters) {
		super.onSettingCameraParameters(parameters);

		if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
			Log.w(tag, "Auto Focus supported by camera device");
			parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		} else {
			Log.w(tag, "Auto Focus not supported by camera device");
			if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_INFINITY)) {
				Log.w(tag, "Infinity Focus supported by camera device");
				parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
			} else {
				Log.w(tag, "Infinity Focus not supported by camera device");
			}
		}
	}

	public static List<Size> oneShotSupportedVideoSizes() {
		Camera camera = Camera.open();
		List<Size> supportedVideoSizes =camera.getParameters().getSupportedPreviewSizes();
		camera.release();
		return supportedVideoSizes;
	}
	
	@Override
	protected List<Size> getSupportedPreviewSizes(Parameters parameters) {
		return parameters.getSupportedPreviewSizes();
	}
}
