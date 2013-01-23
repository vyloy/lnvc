package com.lorent.video;


import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.demo.VideoViewDemo;
import io.vov.vitamio.widget.MediaController;

public class MonitorShowActivity extends VideoViewDemo {

	private io.vov.vitamio.widget.VideoView mVideoView;
	private TextView tv;
	
	public io.vov.vitamio.widget.VideoView.ActivitySizeType getActivitySizeType(){
		return io.vov.vitamio.widget.VideoView.ActivitySizeType.MONITOR;
	}
	
//	public void init(String videoUrl,String title){
//		setContentView(R.layout.videoview);
////		LinearLayout layout = (LinearLayout)findViewById(R.id.LinearLayout1);
//		mVideoView = (VideoView) findViewById(R.id.surface_view);
//		mVideoView.setActivitySizeType(io.vov.vitamio.widget.VideoView.ActivitySizeType.MONITOR);
//		mVideoView.setTitle(title);
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
//	}

	private String videoUrl;
	private String title;
	private boolean initialFlag = false;
	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if(hasFocus && !initialFlag){
			initialFlag = true;
			mVideoView = (io.vov.vitamio.widget.VideoView) findViewById(R.id.monitor_surface_view);
			DisplayMetrics metric = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metric);
			float density = metric.density;
			io.vov.vitamio.widget.CenterLayout layout = (io.vov.vitamio.widget.CenterLayout)findViewById(R.id.monitor_centerLayout);
//			int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//	        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//	        layout.measure(w, h);
//	        int width = layout.getMeasuredWidth();
//	        int height = layout.getMeasuredHeight();
//	        mVideoView.setPixWidth(width);
//	        mVideoView.setPixHeight(height);
//			int w = (int)(Integer.parseInt(MonitorShowActivity.this.getResources().getString(R.string.monitor_surface_view_width)) * density - 10);
//			int h = (int)(Integer.parseInt(MonitorShowActivity.this.getResources().getString(R.string.monitor_surface_view_height)) * density - 10);
			int w = (int)(layout.getWidth() - 5);
			int h = (int)(layout.getHeight()  - 5);
			mVideoView.setPixWidth(w);
			mVideoView.setPixHeight(h);
//			tv = (TextView)findViewById(R.id.monitor_surface_container_title);
			mVideoView.setActivitySizeType(io.vov.vitamio.widget.VideoView.ActivitySizeType.MONITOR);
//			tv.setText(title);
			mVideoView.setTitle(title);
//			mVideoView.setCurrentDisplay(currentDisplay);
			mVideoView.setVideoPath(videoUrl);
			mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
			mVideoView.setMediaController(new MediaController(MonitorShowActivity.this));
			mVideoView.setOnCompletionListener(new OnCompletionListener(){
				@Override
				public void onCompletion(MediaPlayer arg0) {
					finish();
				}
				
			});
		}
		super.onWindowFocusChanged(hasFocus);
	}



	public void init(final String videoUrl,final String title){
		this.videoUrl = videoUrl;
		this.title = title;
		setContentView(R.layout.monitorshow_dialog);
		
	}

	
	
}
