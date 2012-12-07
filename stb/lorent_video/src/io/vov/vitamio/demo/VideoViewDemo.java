/*
 * Copyright (C) 2012 YIXIA.COM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.vov.vitamio.demo;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.R;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class VideoViewDemo extends Activity {

	private VideoView mVideoView;
	private io.vov.vitamio.widget.VideoView.ActivitySizeType type = io.vov.vitamio.widget.VideoView.ActivitySizeType.VIDEO;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
			return;
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		Intent intent = this.getIntent();
		String videoUrl = intent.getExtras().getString("videoUrl");
		String title = intent.getExtras().getString("fileName");
		Log.i("videoUrl", videoUrl);
		Log.i("title", title+"");
//		currentDisplay = getWindowManager().getDefaultDisplay();
//		mVideoView = (VideoView) findViewById(R.id.surface_view);
//		mVideoView.setTitle(title);
//		mVideoView.setCurrentDisplay(currentDisplay);
//		mVideoView.setVideoPath(videoUrl);
//		mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
//		mVideoView.setMediaController(new MediaController(this));
//		mVideoView.setOnCompletionListener(new OnCompletionListener(){
//			@Override
//			public void onCompletion(MediaPlayer arg0) {
//				finish();
//			}
//			
//		});
		init(videoUrl,title);
	}
	
	public void init(String videoUrl,String title){
		setContentView(R.layout.videoview);
//		Display currentDisplay = getWindowManager().getDefaultDisplay();
//		LinearLayout layout = (LinearLayout)findViewById(R.id.LinearLayout1);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		mVideoView.setActivitySizeType(getActivitySizeType());
		mVideoView.setTitle(title);
//		mVideoView.setCurrentDisplay(currentDisplay);
		mVideoView.setVideoPath(videoUrl);
		mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.setOnCompletionListener(new OnCompletionListener(){
			@Override
			public void onCompletion(MediaPlayer arg0) {
				finish();
			}
			
		});
	}
	
	public io.vov.vitamio.widget.VideoView.ActivitySizeType getActivitySizeType(){
		return io.vov.vitamio.widget.VideoView.ActivitySizeType.VIDEO;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (mVideoView != null)
			mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_ZOOM, 0);
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onDestroy() {
		if(mVideoView!=null){
			mVideoView.destroyDrawingCache();
			mVideoView = null;
		}
		
		super.onDestroy();
	}
	
	
}
