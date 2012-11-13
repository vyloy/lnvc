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
  
     private static ConcurrentHashMap<String, SoftReference<Drawable>> imageCache;
     private static ConcurrentHashMap<Integer,Object> recordIdThreads;
     private ImageCallback imageCallback;
     private String imageUrl;
     private Context context;
     private Drawable bgDrawable;
     private boolean enableCache = true;//缓存
//     private Handler handler;
    
     public static void clearCache(){
    	 if(imageCache!=null){
    		 imageCache.clear();
    	 }
    	 if(recordIdThreads!=null){
    		 recordIdThreads.clear();
    	 }
     }
     
     public AsyncImageLoader(Context context) {  
         imageCache = new ConcurrentHashMap<String, SoftReference<Drawable>>();
         recordIdThreads = new ConcurrentHashMap<Integer,Object>();
         this.context = context;
         bgDrawable = context.getResources().getDrawable(R.drawable.video_no_pic);
     }
     
     
     public void loadDrawable(int position,Integer id,final String imageUrl,
 			final ImageCallback imageCallback) {
    	this.imageCallback = imageCallback;
    	this.imageUrl = imageUrl;
    	if(enableCache){
    		if (imageCache.containsKey(imageUrl)) {
      			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
      			Drawable drawable = softReference.get();
      			Handler handler = new ImageHandler(imageCallback);
      			if (drawable != null) {
      				Log.i("imageCache", "get image from cache:" + imageUrl);
      				
      				Message message = handler.obtainMessage(0, drawable);
      				handler.sendMessage(message);
      			}else{
      				Log.i("imageCache", "get image from server");
      				recordIdThreads.put(id, id);
  					new ImageLoadAsyncTask(imageUrl,imageCallback).execute(imageUrl);
//      				new ImageLoadThread(imageUrl,imageCallback).start();
      			}
      		}else{
      			if(!recordIdThreads.containsKey(id)){
      				Log.i("imageCache", "get image from server");
      				Log.e("loading", id+ "=="+imageUrl);
  					recordIdThreads.put(id, id);
  					new ImageLoadAsyncTask(imageUrl,imageCallback).execute(imageUrl);
//  				new ImageLoadThread(imageUrl,imageCallback).start();
  				}/*else if(position==0){//第一个VIEW无法显示图片，需要重新加载
  					new ImageLoadAsyncTask(imageUrl,imageCallback).execute(imageUrl);
  				}*/
      		}
    	}else{
    		new ImageLoadAsyncTask(imageUrl,imageCallback).execute(imageUrl);
//    		new ImageLoadThread(imageUrl,imageCallback).start();
    	}
    	 
//    	new ImageLoadAsyncTask(imageUrl,imageCallback).execute(imageUrl);
 	}

     
     private class ImageLoadThread extends Thread{
    	 String url;
    	 private Handler handler;
    	 public ImageLoadThread(String url,ImageCallback imageCallback){
    		 this.url = url;
    		 this.handler = new ImageHandler(imageCallback);
    	 }
    	 public void run(){
    		 asyncProcessLoadImg(url,handler);
    	 }
     }
     
     public void asyncProcessLoadImg(String url,Handler handler){
    	 Drawable drawable = loadImageFromUrl(url);
			if(enableCache){
				if(drawable!=null){
					imageCache.put(url, new SoftReference<Drawable>(drawable));
				}else{
					imageCache.put(url, new SoftReference<Drawable>(bgDrawable));
				}
			}
			Message message = handler.obtainMessage(0, drawable);
			handler.sendMessage(message);
     }
     
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
			asyncProcessLoadImg(params[0],handler);
			return null;
		}
	}
         
    public Drawable loadImageFromUrl(String url) {  
    	
    	/**  
       * 加载网络图片  
       */  
            URL m;  
            InputStream i = null;
            Drawable d = null;
            try {
            	Log.e("loading", url);
                m = new URL(url);  
                i = (InputStream) m.getContent();
                d = Drawable.createFromStream(i, "src");
            } catch (Exception e) {
            	Log.e("loadimage", e.getMessage());
                e.printStackTrace(); 
            }finally{
            	try {
            		if(i!=null){
            			i.close();
            			i = null;
            		}
				} catch (IOException e) {
					
				}
            }  
            
            return d;
          
        /** 
         * 加载内存卡图片 
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
