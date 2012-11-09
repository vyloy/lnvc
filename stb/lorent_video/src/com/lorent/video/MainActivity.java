package com.lorent.video;



import io.vov.vitamio.activity.InitActivity;
import io.vov.vitamio.demo.VideoViewDemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.lorent.common.dto.LCMVideoClip;
import com.lorent.video.bean.VideoInfo;
import com.lorent.video.service.VideoService;
import com.lorent.video.util.AsyncImageLoader;
import com.lorent.video.util.ShareAppUtil;

public class MainActivity extends Activity {

	private GridView gridView;
	VideoInfoAdapter adapter;
	private static final int DIALOG_PROGRESS = 0;
	List<LCMVideoClip> datas;
	private ProgressDialog mProgressDialog;
	private final static String TAG = "main";
	private int currentPage = 1;
//	private GetGridDataTask task = new GetGridDataTask();
	private VideoService videoService ;
	private boolean loadDataFinish = false;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Log.i("viewdisplay", "height:"+display.getHeight());
        Log.i("viewdisplay","width:"+display.getWidth());
        videoService = new VideoService(this);
        if(this.findViewById(R.id.gridview) instanceof GridView){
        	gridView = (GridView)this.findViewById(R.id.gridview);
        }
        mProgressDialog = new ProgressDialog(MainActivity.this);  
        mProgressDialog.setMessage("正在获取数据");  
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
        datas = new ArrayList<LCMVideoClip>();
        gridView.setOnItemClickListener(new GridViewClickListener());
        new LoadInfoThread().start();
//        show();
        
    }
    
    private void show(){
    	if(adapter==null){
    		adapter = new VideoInfoAdapter(this,datas,R.layout.video_item,gridView);
        	gridView.setAdapter(adapter);
    	}
    }
    
    private class LoadInfoThread extends Thread{
    	
    	public LoadInfoThread(){
//    		showDialog(DIALOG_PROGRESS);//打开等待对话框  
    		mProgressDialog.show();
    	}
    	
    	public void run(){
    		List<LCMVideoClip> result;
			try {
				result = videoService.getVideoInfo(currentPage);
				mProgressDialog.dismiss();
	    		datasHandler.sendMessage(datasHandler.obtainMessage(100, result));
			} catch (Exception e) {
				mProgressDialog.dismiss();
	    		datasHandler.sendMessage(datasHandler.obtainMessage(0, null));
			}
    	}
    }
    
    protected void onStart () {  
        super.onStart();
//        new GetGridDataTask().execute();//执行获取数据的任务  
//        new LoadInfoThread().start();
    }
    
    
    
    private Handler datasHandler = new Handler() {  
        public void handleMessage(Message message) {
        	int code = message.what;
        	switch (code){
        	case 0:
        		Toast errorToast = Toast.makeText(MainActivity.this, R.string.connect_server_timeout, 3000);
        		errorToast.setGravity(Gravity.CENTER,0,0);
        		errorToast.show();
        		finish();
        		break;
        	case 100:
	        	List<LCMVideoClip> result = (List<LCMVideoClip>)message.obj;
	        	if(result==null || result.size()<1){
	        		if(currentPage<=0){
	        			currentPage = 1;
	        		}else{
	        			currentPage--;
	        		}
	    			Toast toast = Toast.makeText(MainActivity.this, R.string.no_more_content, 3000);
	    			toast.setGravity(Gravity.CENTER,0,0);
	    			toast.show();
	    		}else{
	    			AsyncImageLoader.clearCache();
	    			VideoInfoAdapter.clearCacheView();
	    			datas.clear(); 
	    			datas.addAll(result);
	    			if(adapter==null){
		        		show();
		        	}else{
		        		adapter.notifyDataSetChanged();//通知ui界面更新  
		        	}
	    		}
	        	loadDataFinish = true;
	        	break;
        	}
        	
        }  
    };  
      

    private void selectPlayer(){
    	final List<ResolveInfo> videoPlayerList = ShareAppUtil.getVideoShareApps(MainActivity.this);
		List<String> appNames = new ArrayList<String>();
		for(ResolveInfo info:videoPlayerList){
			Log.i("videoPlayerList","==================================");
			appNames.add(info.activityInfo.loadLabel(getPackageManager()).toString());
			Log.i("videoPlayerList",info.activityInfo.loadLabel(getPackageManager()).toString());
			Log.i("videoPlayerList",info.activityInfo.name);
			Log.i("videoPlayerList",info.activityInfo.packageName);
		}
		
		String temp[] = new String[appNames.size()];
		for(int i=0;i<temp.length;i++){
			temp[i] = appNames.get(i);
		}
		final String[] items = temp;
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);  
        builder.setTitle("选择播放器").setItems(items,new DialogInterface.OnClickListener(){  
            public void onClick(DialogInterface dialog, int which) {  
            	Log.i("player_which", which + "");
            	ResolveInfo info = videoPlayerList.get(which);
                String className = info.activityInfo.name;
                Log.i("player_classname", className);
                Intent it = new Intent(className);  
                it.setAction(Intent.ACTION_VIEW);
                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                String tmpPath ="rtsp://10.168.250.12:554/20121026090508_317_720P_h264_test.mp4";
	            Uri uri = Uri.parse(tmpPath);  
	            it.setType("video/*");
	            it.setDataAndType(uri , "video/*");  
	            startActivity(it);  
            }  
        });  
        AlertDialog ad = builder.create();  
        ad.show();  
    }
    
    
    private class GridViewClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			LCMVideoClip item = (LCMVideoClip) arg0.getItemAtPosition(arg2);
			
			//手机播放
			/*Intent intent = new Intent(MainActivity.this,VideoViewDemo.class);
			intent.putExtra("fileName", item.getTitle());
			intent.putExtra("videoUrl", item.getRtspVideoUrlStandard());
			startActivity(intent);*/
			
			//机顶盒播放视频
			/*Intent intent = new Intent(MainActivity.this,SurfaceViewPlayVideo.class);
//			intent.putExtra("videoUrl", item.getHttpVideoUrlStandard());
			intent.putExtra("videoUrl", "http://10.168.250.12:8800/lian720p.mp4");
			intent.putExtra("fileName", item.getTitle());
			startActivity(intent);*/
			
			//云电视
			Intent intent = new Intent(MainActivity.this,WebVideoActivity.class);
//			intent.putExtra("videoUrl", item.getHttpVideoUrlHigh());
			intent.putExtra("videoUrl", "http://10.168.250.12:8800/lian720p.mp4");
			intent.putExtra("fileName", item.getTitle());
			startActivity(intent);
		}
    	
    }
    
    

    public void showVideo(View v){
    	Intent intent = new Intent(this,WebVideoActivity.class);
    	intent.putExtra("videoName", "flvplayer.flv");
    	this.startActivity(intent);
    }
    
    
    
    public void loadData(View v){
    	if(!loadDataFinish){
    		return;
    	}else{
    		loadDataFinish = false;
    	}
    	if(v.getId()==R.id.previousbutton){
    		currentPage--;
    	}else{
    		currentPage++;
    	}
    	new LoadInfoThread().start();
//    	new GetGridDataTask().execute();//执行获取数据的任务  
    	
    }
    

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
    	Log.i("keyvalue", event.getKeyCode()+"");
    	int nextPageKey = Integer.parseInt(this.getResources().getString(R.string.NEXT_PAGE_KEY));
    	int prePageKey = Integer.parseInt(this.getResources().getString(R.string.PREVIOUS_PAGE_KEY));
    	if(event.getAction()==KeyEvent.ACTION_UP && (event.getKeyCode()==nextPageKey||event.getKeyCode()==prePageKey)){
    		if(!loadDataFinish){
    			return super.dispatchKeyEvent(event);
    		}else{
    			loadDataFinish = false;
    		}
    		if(event.getKeyCode()==nextPageKey){//下一页;频道^
    			currentPage--;
    		}else if(event.getKeyCode()==prePageKey){//上一页;频道v
    			currentPage++;
    		}
    		new LoadInfoThread().start();
    	}
    	return super.dispatchKeyEvent(event);
    }
    
    
}
