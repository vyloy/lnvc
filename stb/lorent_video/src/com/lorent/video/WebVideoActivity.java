package com.lorent.video;

import io.vov.utils.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.lorent.video.R;
import com.lorent.video.util.WebViewUtil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebVideoActivity extends Activity {

	private int windowH;
    private int windowW;
    private WebView webView;
    private Display currentDisplay;
    private String charset = "utf-8";
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.web_video);
		Intent intent = getIntent();
		String videoUrl = intent.getExtras().getString("videoUrl");
		String title = intent.getExtras().getString("fileName");
//		try {
//			videoUrl = URLEncoder.encode(intent.getExtras().getString("videoUrl"),charset);
//			title = URLEncoder.encode(intent.getExtras().getString("fileName"),charset);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		String ip = intent.getExtras().getString("ip");
    	String port = intent.getExtras().getString("port");
		webView = (WebView) findViewById(R.id.webView);
//		currentDisplay = getWindowManager().getDefaultDisplay();
//		windowH = currentDisplay.getHeight();
//		windowW = currentDisplay.getWidth();
		String url = "http://" + ip + ":" + port + "/?videoUrl=" + videoUrl;
		Log.i("wburl", url);
		//		String url = "http://10.168.100.73:6090/myvideo/index.jsp";
		//http://10.168.100.73:6090/myvideo/index.jsp?videoUrl=http://10.168.250.12:8800/lian720p.mp4&w=500&h=400&videoName=年aaa
		WebViewUtil.initWebView(webView, new MyWebViewClient(),new InredisChromeClient(this), url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            /*if (Uri.parse(url).getHost().equals("10.168.100.73")||Uri.parse(url).getHost().equals("10.168.250.12")||Uri.parse(url).getHost().equals("content.longtailvideo.com")) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;*/
        	return true;
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
}
