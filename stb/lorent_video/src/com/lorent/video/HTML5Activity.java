package com.lorent.video;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;


public class HTML5Activity extends Activity {

    HTML5WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new HTML5WebView(this);

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        } else {    
//            mWebView.loadUrl("http://v.youku.com/v_show/id_XNDY4MzM2MDE2.html");
        	Intent intent = getIntent();
        	String videoUrl = intent.getExtras().getString("videoUrl");
        	String ip = intent.getExtras().getString("ip");
        	String port = intent.getExtras().getString("port");
    		
            mWebView.loadUrl("http://"+ip+":"+port+"/?videoUrl="+videoUrl);
        }

        setContentView(mWebView.getLayout());
//        mWebView.setFocusable(false);
//        mWebView.setClickable(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mWebView!=null){
        	try{
        		mWebView.stopLoading();
        	}catch(Exception ex){
        		
        	}
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	int forwardKey = 22;
    	int backKey = 21;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	Log.i("keyvalue", "onKeyDown");
            if (mWebView.inCustomView()) {
            	if(mWebView!=null){
                	mWebView.stopLoading();
                }
            	mWebView.hideCustomView();
            //  mWebView.goBack();
                //mWebView.goBack();
            }
            finish();
            return true;
        }else if(event.getKeyCode()==forwardKey||event.getKeyCode()==backKey){
        	return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
		Log.i("keyvalue", event.getKeyCode()+"");
		int forwardKey = 22;
    	int backKey = 21;
    	int playPauseKey = 66;
    	if(event.getKeyCode()==forwardKey||event.getKeyCode()==backKey){
    		if(event.getAction()==KeyEvent.ACTION_DOWN && (event.getKeyCode()==forwardKey||event.getKeyCode()==backKey)){
    			Log.i("keyvalue", event.getKeyCode()+"");
    			if(event.getKeyCode()==backKey){//向左键；快退
        			mWebView.loadUrl("javascript:forwardOrBack(0)");
        		}else if(event.getKeyCode()==forwardKey){//向右键：快进
        			mWebView.loadUrl("javascript:forwardOrBack(1)");
        		}
        		
    			return true;
        	}
    		return true;
    	}else if(event.getKeyCode()==66){
    		mWebView.loadUrl("javascript:playOrPause()");
    	}
    	return super.dispatchKeyEvent(event);
    }
}
