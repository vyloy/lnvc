package com.lorent.video;



import io.vov.vitamio.activity.InitActivity;
import io.vov.vitamio.demo.VideoViewDemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lorent.common.dto.LCMVideoClip;
import com.lorent.video.bean.VideoInfo;
import com.lorent.video.service.VideoService;
import com.lorent.video.util.AsyncImageLoader;
import com.lorent.video.util.DBAdapterImpl;
import com.lorent.video.util.ImageUtil;
import com.lorent.video.util.NetWorkUtil;
import com.lorent.video.util.ShareAppUtil;
import com.lorent.video.util.StringUtil;

public class MainActivity extends Activity {

	public GridView gridView;
	VideoInfoAdapter adapter;
	private static final int DIALOG_PROGRESS = 0;
	List<LCMVideoClip> datas;
	private ProgressDialog mProgressDialog;
	private final static String TAG = "main";
	private int currentPage = 1;
//	private GetGridDataTask task = new GetGridDataTask();
	private VideoService videoService ;
	private boolean loadDataFinish = false;
	private DeviceType device = DeviceType.STB;
	private String selectedType = "电影";
	private LinearLayout setupLayout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memorySize = activityManager.getMemoryClass();
        Log.i("memory", ""+memorySize);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        RelativeLayout container = (RelativeLayout)findViewById(R.id.content);
//        int cw = container.getWidth();
//        int ch = container.getHeight();
//        Log.i("container", cw + ":" + ch);
        Display display = getWindowManager().getDefaultDisplay();
        container.setBackgroundDrawable(new BitmapDrawable(ImageUtil.decodeSampledBitmapFromResource(this.getResources(), R.drawable.main_bg, display.getWidth(), display.getHeight())));
        setupLayout = (LinearLayout)findViewById(R.id.setupLayout);
        getSeverInfoPreferences();
        videoService = new VideoService(this);
        if(this.findViewById(R.id.gridview) instanceof GridView){
        	gridView = (GridView)this.findViewById(R.id.gridview);
        }
        new Thread(){
        	public void run(){
        		NetWorkUtil.setSessionId(ip);
        	}
        }.start();
        mProgressDialog = new ProgressDialog(MainActivity.this);  
        mProgressDialog.setMessage("正在获取数据");  
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
        datas = new ArrayList<LCMVideoClip>();
        gridView.setOnItemClickListener(new GridViewClickListener());
        DBAdapterImpl.init(this);
        new LoadInfoThread().start();
//        show();
        
    }
    
    private void show(){
    	if(adapter==null){
    		if(device==DeviceType.PHONE){
    			adapter = new VideoInfoAdapter(this,datas,R.layout.video_item_phone,gridView);
    		}else{
    			adapter = new VideoInfoAdapter(this,datas,R.layout.video_item,gridView);
    		}
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
//        		finish();
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
	        	break;
        	}
        	loadDataFinish = true;
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
                //com.skyworth.videoplayer.SkyVideoPlayer
                Log.i("player_classname", className);
                Intent it = new Intent(className);  
                it.setAction(Intent.ACTION_VIEW);
                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                String tmpPath ="http://10.168.250.12:8800/20121109144057_287_480P_1000K.mp4";
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
			if(device==DeviceType.PHONE){
				//手机播放视频
				Log.i("videoUrlp", StringUtil.encodeUrl(item.getHttpVideoUrlStandard()));
				Intent intent = new Intent(MainActivity.this,VideoViewDemo.class);
				intent.putExtra("fileName", item.getTitle());
				intent.putExtra("videoUrl", StringUtil.encodeUrl(item.getHttpVideoUrlStandard()));
				startActivity(intent);
			}else if(device==DeviceType.STB){
				//机顶盒播放视频
				Intent intent = new Intent(MainActivity.this,SurfaceViewPlayVideo.class);
				intent.putExtra("videoUrl", StringUtil.encodeUrl(item.getHttpVideoUrlHigh()));
//				intent.putExtra("videoUrl", "http://10.168.250.12:8800/");
				intent.putExtra("fileName", item.getTitle());
				startActivity(intent);
			}else if(device==DeviceType.CLOUDTV){
				//云电视播放视频
//				Intent intent = new Intent(MainActivity.this,WebVideoActivity.class);
//				intent.putExtra("videoUrl", item.getHttpVideoUrlHigh());
////				intent.putExtra("videoUrl", "http://10.168.250.12:8800/lian720p.mp4");
//				intent.putExtra("fileName", item.getTitle());
//				startActivity(intent);
				
//				selectPlayer();
				
//				Intent it = new Intent(Intent.ACTION_VIEW);
//				it.setDataAndType(Uri.parse(item.getHttpVideoUrlHigh()), "video/mp4");
//				startActivity(it);
				
				Intent intent = new Intent(MainActivity.this,HTML5Activity.class);
				intent.putExtra("videoUrl", StringUtil.encodeUrl(item.getHttpVideoUrlHyper()));
				intent.putExtra("fileName", item.getTitle());
				intent.putExtra("ip", MainActivity.this.ip);
				intent.putExtra("port", MainActivity.this.port);
				startActivity(intent);
			}
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
    	if(v.getId()==R.id.previousLayout){
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
    	if(device==DeviceType.CLOUDTV){
    		nextPageKey = 167;
    		prePageKey = 166;
    	}
    	if(event.getKeyCode()== KeyEvent.KEYCODE_1){
    		setupLayout.requestFocus();
    		return true;
    	}
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
    
    private enum DeviceType{
    	PHONE,
    	STB,
    	CLOUDTV
    }
    
    AlertDialog.Builder builder;  
    AlertDialog alertDialog;
    public String ip = null;
    public String port = null;
    EditText ipComponent;
    EditText portComponent;
    
    public void openSetupDialog(View v){
    	LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);  
        View layout = inflater.inflate(R.layout.setup_dialog,null);  
        ipComponent = (EditText)layout.findViewById(R.id.serverip);
        portComponent = (EditText)layout.findViewById(R.id.serverport);
        ipComponent.setText(ip);
        portComponent.setText(port);
        builder = new AlertDialog.Builder(this);
        builder.setTitle("设置");
        builder.setView(layout);
        
        alertDialog = builder.create();
        alertDialog.show(); 
       /* WindowManager m = getWindowManager(); 
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高 

        WindowManager.LayoutParams p = getWindow().getAttributes(); 
        //获取对话框当前的参数值 
        p.x = 0; //设置位置 默认为居中
        p.y = 0; //设置位置 默认为居中
        p.height = LayoutParams.WRAP_CONTENT; //高度设置为屏幕的0.6 
        p.width = (int) (d.getWidth() * 0.4); //宽度设置为屏幕的0.4 
        alertDialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) p);*/
//        alertDialog.getWindow().setLayout(Integer.parseInt(this.getResources().getString(R.string.setupdialog_width)), Integer.parseInt(this.getResources().getString(R.string.setupdialog_height)));
//        alertDialog.getWindow().setAttributes(p);
         
    }
    
    public void saveServerInfo(View v){
    	if(v.getId()==R.id.setupOkBtn){
    		alertDialog.dismiss();
    		if(!(ip.equals(ipComponent.getText().toString().trim()))){
    			if(datas!=null){
    				datas.clear();
    			}
    			if(adapter!=null){
    				adapter.notifyDataSetChanged();
    			}
	    		final String oldIp = new String(ip);
	    		new Thread(){
	    			public void run(){
	    				NetWorkUtil.clearSessionId(oldIp);
	    			}
	    		}.start();
	    		ip = ipComponent.getText().toString();
	            port = portComponent.getText().toString();
	            setSeverInfoPreferences(ip,port);
	            currentPage = 1;
	            new Thread(){
	    			public void run(){
	    				NetWorkUtil.setSessionId(ip);
	    			}
	    		}.start();
	            new LoadInfoThread().start();
    		}
    	}else{
    		alertDialog.dismiss();
    	}
    }
    
    public static final String PREFS_NAME = "PreferencesFile";
    private void setSeverInfoPreferences(String ip,String port){
        // set preference
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
//        editor.putString("preferences", mPreferences);
        editor.putString("serverip", ip);
        editor.putString("serverport", port);
        editor.commit();
    }
    
    private void getSeverInfoPreferences() {
        
        // get preference
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        ip = settings.getString("serverip", "10.168.250.12");
        port = settings.getString("serverport", "8800");
    }

	

	@Override
	protected void onDestroy() {
		Log.i("livemethod", "onDestroy:"+ip);
		AsyncImageLoader.clearCache();
		VideoInfoAdapter.clearCacheView();
		new Thread(){
			public void run(){
				NetWorkUtil.clearSessionId(ip);
			}
		}.start();
		super.onDestroy();
	}
    
    public void startMonitorActivity(View v){
    	Intent intent = new Intent(MainActivity.this,MonitorActivity.class);
//    	intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
    }
	
}
