package com.lorent.video.util;

import com.lorent.video.InredisChromeClient;
import com.lorent.video.WebVideoActivity;

import android.graphics.Color;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewUtil {

	public static void initWebView(WebView webView,WebViewClient webViewClient,WebChromeClient chromeClient,String url){
		webView.setWebViewClient(webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setPluginsEnabled(true);
        webView.getSettings().setPluginState(PluginState.ON);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebChromeClient(chromeClient);
//        webView.getSettings().setDefaultTextEncodingName("GBK");
        
        
        
//        webView.setInitialScale(60);
//        webView.setBackgroundColor(Color.BLACK);
//        getWindow().addFlags(128);
//        webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
//        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

//        webView.setWebChromeClient(new WebChromeClient() {  
//            @Override  
//            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result)  
//            {  
//                return true;
//            }
//        });
        webView.loadUrl(url);
	}
	
}
