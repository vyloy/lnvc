package com.lorent.video.util;

import java.io.IOException;  
import java.io.InputStream;  
import java.lang.ref.SoftReference;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.util.HashMap;  
import java.util.concurrent.ConcurrentHashMap;

import com.lorent.video.R;
  
import android.R.drawable;  
import android.content.Context;
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;  
import android.graphics.BitmapFactory.Options;  
import android.graphics.drawable.BitmapDrawable;  
import android.graphics.drawable.Drawable;  
import android.os.AsyncTask;
import android.os.Handler;  
import android.os.Message;  
import android.util.Log;  
import android.widget.ImageView;  
  
public class AsyncImageLoader {  
  
     private ConcurrentHashMap<String, SoftReference<Drawable>> imageCache;
     private ImageCallback imageCallback;
     private String imageUrl;
     private Context context;
     private Drawable bgDrawable;
//     private Handler handler;
    
     
     public AsyncImageLoader(Context context) {  
         imageCache = new ConcurrentHashMap<String, SoftReference<Drawable>>();
         this.context = context;
         bgDrawable = context.getResources().getDrawable(R.drawable.video_no_pic);
     }
     
     
     
     public void loadDrawable(final String imageUrl,
 			final ImageCallback imageCallback) {
    	this.imageCallback = imageCallback;
    	this.imageUrl = imageUrl;
    	
    	 if (imageCache.containsKey(imageUrl)) {
  			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
  			Drawable drawable = softReference.get();
  			Handler handler = new ImageHandler(imageCallback);
  			if (drawable != null) {
  				Log.i("imageCache", "get image from cache");
  				
  				Message message = handler.obtainMessage(0, drawable);
  				handler.sendMessage(message);
  			}else{
  				Log.i("imageCache", "get image from server");
//  				Message message = handler.obtainMessage(0, null);
//  				handler.sendMessage(message);
  				new ImageLoadAsyncTask(imageUrl,imageCallback).execute(imageUrl);
  			}
  		}else{
  			new ImageLoadAsyncTask(imageUrl,imageCallback).execute(imageUrl);
  		}
//    	new ImageLoadAsyncTask(imageUrl,imageCallback).execute(imageUrl);
 	}
        
//	public Drawable loadDrawable(final String imageUrl,
//			final ImageCallback imageCallback) {
//		if (imageCache.containsKey(imageUrl)) {
//			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
//			Drawable drawable = softReference.get();
//			if (drawable != null) {
//				return drawable;
//			}
//		}
//		final Handler handler = new Handler() {
//			public void handleMessage(Message message) {
//				imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
//			}
//		};
//		
//		new Thread() {
//			@Override
//			public void run() {
//				Drawable drawable = loadImageFromUrl(imageUrl);
//				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
//				Message message = handler.obtainMessage(0, drawable);
//				handler.sendMessage(message);
//			}
//		}.start();
//		
//		return null;
//	}
     
     private class ImageHandler extends Handler{
    	 ImageCallback imageCallback;
    	 public ImageHandler(){}
    	 
    	 public ImageHandler(ImageCallback imageCallback){
    		 this.imageCallback = imageCallback;
    	 }
    	 
    	 public void handleMessage(Message message) {
				imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
		 }
     }
	
	private class ImageLoadAsyncTask extends AsyncTask<String,Integer,Drawable>{
		private Handler handler;
		
		public ImageLoadAsyncTask(final String imageUrl,final ImageCallback imageCallback){
			handler = new ImageHandler(imageCallback);
		}
		
        protected void onPostExecute (Drawable result) {  
        	
        }

		@Override
		protected Drawable doInBackground(String... params) {
			Drawable drawable = loadImageFromUrl(params[0]);
			if(drawable!=null){
				imageCache.put(params[0], new SoftReference<Drawable>(drawable));
			}else{
				imageCache.put(params[0], new SoftReference<Drawable>(bgDrawable));
			}
			Message message = handler.obtainMessage(0, drawable);
			handler.sendMessage(message);
			return null;
		}
	}
         
    public Drawable loadImageFromUrl(String url) {  
      
//    	if (imageCache.containsKey(url)) {
//			SoftReference<Drawable> softReference = imageCache.get(url);
//			Drawable drawable = softReference.get();
//			if (drawable != null) {
//				return drawable;
//			}
//		}
    	
    	/**  
       * º”‘ÿÕ¯¬ÁÕº∆¨  
       */  
            URL m;  
            InputStream i = null;
            Drawable d = null;
            try {  
                m = new URL(url);  
                i = (InputStream) m.getContent();
                d = Drawable.createFromStream(i, "src");
            } catch (Exception e) {
            	Log.e("loadimage", e.getMessage());
                e.printStackTrace(); 
            }  
            
            return d;
          
        /** 
         * º”‘ÿƒ⁄¥Êø®Õº∆¨ 
         */  
//            Options options=new Options();  
//            options.inSampleSize=2;  
//            Bitmap bitmap=BitmapFactory.decodeFile(url, options);  
//            Drawable drawable=new BitmapDrawable(bitmap);  
//            return drawable;  
        }  
        
	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, String imageUrl);

	}  
}  
