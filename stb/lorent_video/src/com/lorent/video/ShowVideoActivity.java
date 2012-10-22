package com.lorent.video;

import com.lorent.video.R;
import com.lorent.video.util.WebViewUtil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShowVideoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showvideo);
		Intent intent = getIntent();
		String videoName = intent.getExtras().getString("videoName");
		WebView webView = (WebView) findViewById(R.id.webview);
		String url = "http://10.168.100.73:6090/myvideo/index.jsp?videoName="+videoName;
		WebViewUtil.initWebView(webView, new MyWebViewClient(), url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals("10.168.100.73")||Uri.parse(url).getHost().equals("content.longtailvideo.com")) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
}
