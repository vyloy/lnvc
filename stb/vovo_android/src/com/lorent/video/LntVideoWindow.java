package com.lorent.video;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Surface;
import android.view.Surface.OutOfResourcesException;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

//import com.lorent.LCCActivity;
import com.lorent.LCCUtil;


public class LntVideoWindow  {
	private Bitmap mBitmap;
	private SurfaceView mView;
	private Surface mSurface;
	private VideoWindowListener mListener;
	static private String TAG = "lcc"; 
	public static final String SURFACECHANGED = "surfaceChanged";
	public static final String SURFACE_CREATE = "surfaceCreate";
	
	public static interface VideoWindowListener{
		void onSurfaceReady(LntVideoWindow vw);
		void onSurfaceDestroyed(LntVideoWindow vw);
	};
	public LntVideoWindow(){
//		setSurfaceView(view);
	}
	
	public void setSurfaceView(SurfaceView view){
		mView=view;
		mBitmap=null;
		mSurface=null;
		mListener=null;

		view.getHolder().addCallback(new Callback(){
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				Log.i(TAG,"Surface is being changed.");
				synchronized(LntVideoWindow.this){
					mBitmap=Bitmap.createBitmap(width,height,Config.RGB_565);
					mSurface=holder.getSurface();

				}
				if (mListener!=null) mListener.onSurfaceReady(LntVideoWindow.this);
				Log.w("Linphone", "Video display surface changed");
				sendMessage(SURFACECHANGED, new String[]{});
			}

			public void surfaceCreated(SurfaceHolder holder) {
				Log.w("Linphone", "Video display surface created");
				sendMessage(SURFACE_CREATE, new String[]{});
			}

			public void surfaceDestroyed(SurfaceHolder holder) {
				synchronized(LntVideoWindow.this){
					mSurface=null;
					mBitmap=null;
				}
				if (mListener!=null)
					mListener.onSurfaceDestroyed(LntVideoWindow.this);
				Log.d("Linphone", "Video display surface destroyed"); 
			}
		});
	}
   public synchronized void sendMessage(String type, String[] args){
//	   boolean flag = true;
//	   while(flag){
//		   if(LCCActivity.thislcc != null){
//			   flag = false;
//		   }
//	   }
////	    Handler myHandler = LCCActivity.thislcc.getMyHandler();
////        Message msg = myHandler.obtainMessage();
////        Bundle b = new Bundle();
////        b.putStringArray(type, args);
////        msg.setData(b);
////        myHandler.sendMessage(msg);
//	   Intent intent = new Intent(LCCUtil.LCC_BROADCAST);
//	   intent.putExtra(LCCUtil.BROADCAST_TYPE, type);
//	   intent.putExtra(type, args);
//	   LCCActivity.thislcc.sendBroadcast(intent);
   }
	
	
	static final int LANDSCAPE=0;
	static final int PORTRAIT=1;
	public void requestOrientation(int orientation){
		//Surface.setOrientation(0, orientation==LANDSCAPE ? 1 : 0);
		//Log.d("Linphone", "Orientation changed.");
	}
	public void setListener(VideoWindowListener l){
		mListener=l;
	}
	public Surface getSurface(){
		return mView.getHolder().getSurface();
	}
	public Bitmap getBitmap(){
		return mBitmap;
	}
	//Called by the mediastreamer2 android display filter
	public synchronized void update(){
		if (mSurface!=null){
			try {
				//Canvas canvas2 = mView.getHolder().lockCanvas(null);
				
				//mView.getHolder().unlockCanvasAndPost(canvas2);
				Canvas canvas=mSurface.lockCanvas(null);
				canvas.drawBitmap(mBitmap, 0, 0, null);
				mSurface.unlockCanvasAndPost(canvas);
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OutOfResourcesException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
}


