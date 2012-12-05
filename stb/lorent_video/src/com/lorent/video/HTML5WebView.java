package com.lorent.video;



import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.VideoView;

public class HTML5WebView extends WebView {

    private Context                             mContext;
    private MyWebChromeClient                   mWebChromeClient;
    private View                                mCustomView;
    private FrameLayout                         mCustomViewContainer;
    private WebChromeClient.CustomViewCallback  mCustomViewCallback;

    private FrameLayout                         mContentView;
    private FrameLayout                         mBrowserFrameLayout;
    private FrameLayout                         mLayout;
    Activity a;

    static final String LOGTAG = "HTML5WebView";

    private void init(Context context) {
        mContext = context;     
        a = (Activity) mContext;

        mLayout = new FrameLayout(context);

        mBrowserFrameLayout = (FrameLayout) LayoutInflater.from(a).inflate(R.layout.custom_screen, null);
        mContentView = (FrameLayout) mBrowserFrameLayout.findViewById(R.id.main_content);
        mCustomViewContainer = (FrameLayout) mBrowserFrameLayout.findViewById(R.id.fullscreen_custom_content);
        mLayout.addView(mBrowserFrameLayout, COVER_SCREEN_PARAMS);

        // Configure the webview
        WebSettings s = getSettings();
        s.setBuiltInZoomControls(true);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
      //  s.setSavePassword(true);
        s.setSaveFormData(true);
        s.setJavaScriptEnabled(true);
        mWebChromeClient = new MyWebChromeClient();
        setWebChromeClient(mWebChromeClient);
        setWebViewClient(new MyWebViewClient());

        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        // enable navigator.geolocation 
       // s.setGeolocationEnabled(true);
       // s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");

        // enable Web Storage: localStorage, sessionStorage
        s.setDomStorageEnabled(true);

        mContentView.addView(this);
//        mLayout.setOnKeyListener(new ViewKeyListener());
//        mLayout.setFocusable(false);
//        mLayout.setClickable(false);
//        mBrowserFrameLayout.setClickable(false);
//        mBrowserFrameLayout.setFocusable(false);
    }

    public HTML5WebView(Context context) {
        super(context);
        init(context);
    }

    public HTML5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HTML5WebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public FrameLayout getLayout() {
        return mLayout;
    }

    public boolean inCustomView() {
        return (mCustomView != null);
    }

    public void hideCustomView() {
        mWebChromeClient.onHideCustomView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((mCustomView == null) && canGoBack()){
                goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    
    private class MyWebViewClient extends WebViewClient{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			boolean flag = super.shouldOverrideUrlLoading(view, url);
			Log.i("shouldOverrideUrlLoading", ""+flag + ";" + url);
			return flag;
		}

		@Override
		public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
			Log.i("KeyEvent", ""+event.getKeyCode());
			if(event.getKeyCode()==66 ||event.getKeyCode()==22 ||event.getKeyCode()==21){
				return false;
			}
			return super.shouldOverrideKeyEvent(view, event);
		}
    	
    }
    
    private class ViewKeyListener implements View.OnKeyListener{

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if(keyCode==22||keyCode==21||keyCode==66){
				return true;
			}
			return false;
		}
    	
    }

    private class MyWebChromeClient extends WebChromeClient {
        private Bitmap      mDefaultVideoPoster;
        private View        mVideoProgressView;

        @Override
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback)
        {
            Log.i(LOGTAG, "here in on ShowCustomView:" + view.getClass());
            HTML5WebView.this.setVisibility(View.GONE);

            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
//            mCustomViewContainer.setClickable(false);
//            mCustomViewContainer.setFocusable(false);
//            view.setClickable(false);
//            view.setFocusable(false);
            
            mCustomViewContainer.addView(view);
            mCustomView = view;
            if(((FrameLayout)mCustomView).getFocusedChild() instanceof VideoView){
            	VideoView videoView = (VideoView) mCustomViewContainer.getFocusedChild();
            	videoView.setOnKeyListener(new ViewKeyListener());
            }
            mCustomViewCallback = callback;
            mCustomViewContainer.setVisibility(View.VISIBLE);
        }

        @Override
        public void onHideCustomView() {
        	Log.i(LOGTAG,"customview hideeeeeeeeeeeeeeeeeeeeeeeeeee");
            if (mCustomView == null)
                return;        
            
            
            // Hide the custom view.
            mCustomView.setVisibility(View.GONE);

            // Remove the custom view from its container.
            mCustomViewContainer.removeView(mCustomView);
            mCustomView = null;
            mCustomViewContainer.setVisibility(View.GONE);
            mCustomViewCallback.onCustomViewHidden();

//            HTML5WebView.this.setVisibility(View.VISIBLE);
//            HTML5WebView.this.goBack();
            HTML5WebView.this.destroy();
            a.finish();
            //Log.i(LOGTAG, "set it to webVew");
        }


        @Override
        public View getVideoLoadingProgressView() {
            Log.i(LOGTAG, "here in on getVideoLoadingPregressView");

//            if (mVideoProgressView == null) {
//                LayoutInflater inflater = LayoutInflater.from(mContext);
//                mVideoProgressView = inflater.inflate(R.layout.video_loading_progress, null);
//            }
//            return mVideoProgressView;
            return null;
        }

         @Override
         public void onReceivedTitle(WebView view, String title) {
            ((Activity) mContext).setTitle(title);
         }

         @Override
         public void onProgressChanged(WebView view, int newProgress) {
             ((Activity) mContext).getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress*100);
         }

         @Override
         public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
             callback.invoke(origin, true, false);
         }
    }

    

    static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS =
        new FrameLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
}
