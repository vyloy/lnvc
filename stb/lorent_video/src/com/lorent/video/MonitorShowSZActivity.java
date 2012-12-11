package com.lorent.video;

import lorent.tools.jni.VideoMonitoring;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MonitorShowSZActivity extends Activity {
	private SurfaceView mSurfaceView;
	private Boolean  isStart = false;
	private String mUrl;// = "10.168.130.223";
	private int videoId = 0;
	private LinearLayout monitorShowContainer;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.monitorshow_sz);
        monitorShowContainer = (LinearLayout)findViewById(R.id.monitorShowContainer);
        Intent intent = this.getIntent();
		String videoUrl = intent.getExtras().getString("videoUrl");
		String title = intent.getExtras().getString("fileName");
		try{
			int startP = videoUrl.indexOf("://");
			if(startP!=-1){
				int endP = videoUrl.indexOf(":", startP+"://".length());
				if(endP!=-1){
					mUrl = videoUrl.substring(startP + "://".length(), endP);
				}else{
					endP = videoUrl.indexOf("/", startP+"://".length());
					if(endP!=-1){
						mUrl = videoUrl.substring(startP + "://".length(), endP);
					}else{
						mUrl = videoUrl.substring(startP + "://".length());
					}
				}
			}
		}catch(Exception ex){
			
		}
		mSurfaceView = (SurfaceView)findViewById(R.id.surfaceViewId);
        
        DisplayMetrics metric = new DisplayMetrics();
        mSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//		getWindowManager().getDefaultDisplay().getMetrics(metric);
//		int width = metric.widthPixels;  // 屏幕宽度（像素） 
//        int height = metric.heightPixels;  // 屏幕高度（像素） 
//        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5） 
//        mSurfaceView.getHolder().setFixedSize(width, height);
        
        mSurfaceView.getHolder().addCallback(new MonitorSurfaceCallBack());
        monitorShowContainer.setPadding(MainActivity.left, MainActivity.top, MainActivity.right, MainActivity.bottom);
        
    }
    
    private class MonitorSurfaceCallBack implements SurfaceHolder.Callback{

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			showHandler.sendMessageDelayed(showHandler.obtainMessage(100), 1000);
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    /*private boolean initialFlag = false;
	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if(hasFocus && !initialFlag){
			initialFlag = true;
			
		}
	}*/
    
    private Handler showHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(isStart == false){
	    		isStart = true;
	    		videoId = VideoMonitoring.start(mUrl, mSurfaceView.getHolder().getSurface());
	    	}
		}
    	
    };
    
    @Override
    public void onPause(){
    	if(isStart){
    		VideoMonitoring.stop(videoId);
    		isStart = false;
    	}
    	super.onPause();
    }
    
    public boolean dispatchKeyEvent(KeyEvent event){
    	Log.i("MONITORSZ", event.getKeyCode()+"");
    	if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
    		if(isStart){
	    		VideoMonitoring.stop(videoId);
	    		isStart = false;
    		}
    	}
		return super.dispatchKeyEvent(event);
    }
    
    public void onClickStart(View v){
    	if(isStart == false){
    		isStart = true;
    		videoId = VideoMonitoring.start(mUrl, mSurfaceView.getHolder().getSurface());
    	}
    }
    
    public void onClickStop(View v){
    	if(isStart){
	    		VideoMonitoring.stop(videoId);
	    		isStart = false;
    	}
    }
}
