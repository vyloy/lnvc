package com.lorent.video;



import io.vov.vitamio.activity.InitActivity;
import io.vov.vitamio.demo.VideoViewDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.graphics.drawable.Drawable;
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
import com.lorent.video.util.MeasureUtil;
import com.lorent.video.util.MyXMLRPCClient;
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
	public final static DeviceType device = DeviceType.PHONE;
	private String selectedType = "电影";
	private LinearLayout setupLayout;
	private LinearLayout movLayout;
	private Map<String,LinearLayout> categoryMap = new HashMap<String,LinearLayout>();
	private LinearLayout toolbar;
	private int pageSize;
	RelativeLayout container;
	private static Drawable bgDrawable;
	
	public static Drawable getDgDrawable(){
		return bgDrawable;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.i("livemethod", "onCreate");
        super.onCreate(savedInstanceState);
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int memorySize = activityManager.getMemoryClass();
        Log.i("memory", ""+memorySize);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        container = (RelativeLayout)findViewById(R.id.content);
//        int cw = container.getWidth();
//        int ch = container.getHeight();
//        Log.i("container", cw + ":" + ch);
        Display display = getWindowManager().getDefaultDisplay();
        if(bgDrawable==null){
        	bgDrawable = new BitmapDrawable(ImageUtil.decodeSampledBitmapFromResource(this.getResources(), R.drawable.main_bg, display.getWidth(), display.getHeight()));
        }
        container.setBackgroundDrawable(bgDrawable);
        setupLayout = (LinearLayout)findViewById(R.id.setupLayout);
        getSeverInfoPreferences();
//        videoService = new VideoService(this);
        videoService = new VideoService();
        if(this.findViewById(R.id.gridview) instanceof GridView){
        	gridView = (GridView)this.findViewById(R.id.gridview);
        }
        new Thread(){
        	public void run(){
        		NetWorkUtil.setSessionId(ip);
        	}
        }.start();
        
        //设置频道分类
        movLayout = (LinearLayout)findViewById(R.id.movLayout);
//        movLayout.setFocusable(false);
//        movLayout.setClickable(false);
        movLayout.setBackgroundColor(getResources().getColor(R.color.category_selected_color));
        selectedType = (String)movLayout.getTag();
        categoryMap.put(selectedType,movLayout);
        LinearLayout tvLayout = (LinearLayout)findViewById(R.id.tvLayout);
        categoryMap.put((String)tvLayout.getTag(),tvLayout);
        LinearLayout newsLayout = (LinearLayout)findViewById(R.id.newsLayout);
        categoryMap.put((String)newsLayout.getTag(),newsLayout);
        LinearLayout gameLayout = (LinearLayout)findViewById(R.id.gameLayout);
        categoryMap.put((String)gameLayout.getTag(),gameLayout);
        LinearLayout dongmLayout = (LinearLayout)findViewById(R.id.dongmLayout);
        categoryMap.put((String)dongmLayout.getTag(),dongmLayout);
        LinearLayout otherLayout = (LinearLayout)findViewById(R.id.otherLayout);
        categoryMap.put((String)otherLayout.getTag(),otherLayout);
        
        toolbar = (LinearLayout)findViewById(R.id.toolbar);
        mProgressDialog = new ProgressDialog(MainActivity.this);  
        mProgressDialog.setMessage("正在获取数据");  
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
        datas = new ArrayList<LCMVideoClip>();
        gridView.setOnItemClickListener(new GridViewClickListener());
        DBAdapterImpl.init(this);
        pageSize = MeasureUtil.getPageSize(MainActivity.this);
        container.setPadding(left, top, right, bottom);
        new LoadInfoThread().start();
//        show();
        
    }
    
    private boolean initialFlag = false;
    @Override
	public void onWindowFocusChanged(boolean hasFocus){
    	if(hasFocus && !initialFlag){
			initialFlag = true;
//			new LoadInfoThread().start();
    	}
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
    
    
    
    @Override
	protected void onPause() {
    	Log.i("livemethod", "onPause");
//    	AsyncImageLoader.clearCache();
//		VideoInfoAdapter.clearCacheView();
		super.onPause();
	}



	private class LoadInfoThread extends Thread{
    	
    	public LoadInfoThread(){
//    		showDialog(DIALOG_PROGRESS);//打开等待对话框  
    		try{
    			mProgressDialog.show();
    		}catch(Exception ex){
    			
    		}
    	}
    	
    	public void run(){
    		List<LCMVideoClip> result;
			try {
//				result = videoService.getVideoInfo(currentPage);
				MyXMLRPCClient client = new MyXMLRPCClient(ip,currentPage,pageSize,selectedType);
				result = videoService.getVideoInfo(client);
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
    
    
    
    @Override
	protected void onRestart() {
    	Log.i("livemethod", "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.i("livemethod", "onResume");
		super.onResume();
	}

	@Override
	protected void onStop() {
		Log.i("livemethod", "onStop");
		if(mProgressDialog!=null){
			mProgressDialog.dismiss();
		}
		super.onStop();
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
//	    			gridView.invalidate();
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
				Log.i("videoUrlp", StringUtil.encodeUrl(StringUtil.changeIPForUrl(item.getHttpVideoUrlStandard(),ip)));
				Intent intent = new Intent(MainActivity.this,VideoViewDemo.class);
				intent.putExtra("fileName", item.getTitle());
				intent.putExtra("videoUrl", StringUtil.encodeUrl(StringUtil.changeIPForUrl(item.getHttpVideoUrlStandard(),ip)));
				startActivity(intent);
			}else if(device==DeviceType.STB){
				//机顶盒播放视频
				Intent intent = new Intent(MainActivity.this,SurfaceViewPlayVideo.class);
				intent.putExtra("videoUrl", StringUtil.encodeUrl(StringUtil.changeIPForUrl(item.getHttpVideoUrlHigh(),ip)));
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
				intent.putExtra("videoUrl", StringUtil.encodeUrl(StringUtil.changeIPForUrl(item.getHttpVideoUrlHyper(),ip)));
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
    
    static public enum DeviceType{
    	PHONE,
    	STB,
    	CLOUDTV
    }
    
    AlertDialog.Builder builder;  
    AlertDialog alertDialog;
    public static String ip = null;
    public static String port = null;
    EditText ipComponent;
    EditText portComponent;
    LinearLayout paddingLayout;
    EditText leftPadding;
    EditText topPadding;
    EditText rightPadding;
    EditText bottomPadding;
    
    public void openSetupDialog(View v){
    	LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);  
        View layout = inflater.inflate(R.layout.setup_dialog,null);  
        ipComponent = (EditText)layout.findViewById(R.id.serverip);
        portComponent = (EditText)layout.findViewById(R.id.serverport);
        paddingLayout = (LinearLayout)layout.findViewById(R.id.paddingLayout);
        if(device==DeviceType.STB){
        	paddingLayout.setVisibility(View.VISIBLE);
        	leftPadding = (EditText)layout.findViewById(R.id.leftPadding);
        	topPadding = (EditText)layout.findViewById(R.id.topPadding);
        	rightPadding = (EditText)layout.findViewById(R.id.rightPadding);
        	bottomPadding = (EditText)layout.findViewById(R.id.bottomPadding);
        	leftPadding.setText(String.valueOf(left));
        	topPadding.setText(String.valueOf(top));
        	rightPadding.setText(String.valueOf(right));
        	bottomPadding.setText(String.valueOf(bottom));
        }
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
    
    private void clearVideoList(){
    	if(datas!=null){
			datas.clear();
		}
		if(adapter!=null){
			adapter.notifyDataSetChanged();
		}
    }
    
    public void saveServerInfo(View v){
    	if(v.getId()==R.id.setupOkBtn){
    		alertDialog.dismiss();
    		if(device==DeviceType.STB){
            	setPaddingInfoPreferences(leftPadding.getText().toString(),topPadding.getText().toString(),rightPadding.getText().toString(),bottomPadding.getText().toString());
            }
    		if(!(ip.equals(ipComponent.getText().toString().trim()))){
    			clearVideoList();
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
    
    private void setPaddingInfoPreferences(String padingleft,String padingtop,String padingright,String padingbottom){
    	
        
    	left = StringUtil.convertCharToInt(padingleft);
        top = StringUtil.convertCharToInt(padingtop);
        right = StringUtil.convertCharToInt(padingright);
        bottom = StringUtil.convertCharToInt(padingbottom);
        
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("padingtop", top);
        editor.putInt("padingleft", left);
        editor.putInt("padingright", right);
        editor.putInt("padingbottom", bottom);
        editor.commit();
        container.setPadding(left, top, right, bottom);
    }
    
    public static int top = 0;
    public static int left = 0;
    public static int right = 0;
    public static int bottom = 0;
    private void getSeverInfoPreferences() {
        
        // get preference
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        ip = settings.getString("serverip", "10.168.130.20");
        port = settings.getString("serverport", "8800");
        if(device==DeviceType.STB){
        	top = settings.getInt("padingtop", 10);
        	left = settings.getInt("padingleft", 10);
        	right = settings.getInt("padingright", 10);
        	bottom = settings.getInt("padingbottom", 10);
        }
    }

	

	@Override
	protected void onDestroy() {
		Log.i("livemethod", "onDestroy");
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
    
    public void getVideoList(View v){
    	if(!v.getTag().equals(selectedType)){
    		clearVideoList();
    		LinearLayout preSelectedLayout = categoryMap.get(selectedType);
//    		preSelectedLayout.setClickable(true);
//    		preSelectedLayout.setFocusable(true);
    		preSelectedLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.category_layout_bg));
    		selectedType = (String)v.getTag();
//    		v.setClickable(false);
//    		v.setFocusable(false);
    		v.setBackgroundColor(getResources().getColor(R.color.category_selected_color));
    		currentPage = 1;
    		new LoadInfoThread().start();
//    		toolbar.requestFocus();
    	}
    }
	
}
