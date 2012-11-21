package my.launch;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivity extends Activity {
	
	private ImageButton btn_music,btn_file,btn_game,btn_tv,btn_phone,btn_set,btn_service,btn_systemsetting,btn_videoclip;
	private TextView register_show,network_show;
	private String TAG = "HomePageActivity";
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {       
    	super.onCreate(savedInstanceState);
//    	sendBroadcast(new Intent("lorent.stb.tv.MyServer"));
        setContentView(R.layout.main);
        initView();
//        initGrid();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Log.i(TAG, "homepage oncreate() "+sDateFormat.format(new java.util.Date()));
        
        registerReceiver(lccMessage, new IntentFilter("com.lorent.lcc.register"));
        registerReceiver(networkMessage, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        
        new PhoneCommandReceiver().startServer();
    }
    
    
    @Override
    protected void onPause() {
    	Log.i(TAG, "onPause");
    	super.onPause();

    }
    
    @Override
    protected void onRestart() {
    	Log.i(TAG, "onRestart");
    	super.onRestart();
    }
    
    @Override
    protected void onStop() {
    	Log.i(TAG, "onStop");

    	super.onStop();
    }
    
    @Override
    protected void onDestroy() {
    	unregisterReceiver(lccMessage);
    	unregisterReceiver(networkMessage);
    	super.onDestroy();
    }
    
  //广播接收器
    private BroadcastReceiver lccMessage = new BroadcastReceiver(){
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Bundle b=arg1.getExtras();
			Message msg=new Message();
			msg.setData(b);
			handlerLccMsg.sendMessage(msg);
			
		}
		    	
	};
	 private BroadcastReceiver networkMessage = new BroadcastReceiver(){
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				Message msg = new Message();
				handlerNetworkMsg.sendMessage(msg);
				
			}
			    	
		};
		
	private Handler handlerNetworkMsg  = new Handler(){
		public void handleMessage(Message msg) {
			//检查网络连接
			ConnectivityManager cm =
				(ConnectivityManager)HomePageActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

				NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
				if(activeNetwork!=null)
				{
				boolean isConnected = activeNetwork.isConnectedOrConnecting();
				if(isConnected)
				{
					int type =activeNetwork.getType();
					if(type == ConnectivityManager.TYPE_WIFI)
					{
						Log.i(TAG, "wifi network");
					}
					else if(type == ConnectivityManager.TYPE_MOBILE)
					{
						Log.i(TAG, "mobile network");
					}
					else
					{
						Log.i(TAG, "unknow network");
					}
					network_show.setText(getString(R.string.netconnected));
					
				}
				else
				{
					network_show.setText(getString(R.string.netnotconnect));
				}
				}
		};
	};	
	/**
	 * 
	 * 处理epg,预约和预录信息
	 */
	private Handler handlerLccMsg =new Handler(){
		public void handleMessage(Message msg) {
			
			Bundle b=msg.getData();
			for (String type : b.keySet()) {

				String[] args = b.getStringArray(type);
				
				
				if(type.equals("register")) {
		                
					if(args[0].equals("ok"))
					{
						
						register_show.setText(args[1]+"@"+args[3]);
					}
					else
					{
						register_show.setText(getString(R.string.localnotregister));
					}
				}
				else{
					
					 
		            	
		            }
				}
			}			
		};
    @Override
    protected void onStart() {
    	Log.i(TAG, "onStart");
    	super.onStart();
    }
    
    class MyAdapter extends BaseAdapter{
    	private Context context;
		
    	MyAdapter(Context context){
    		this.context = context;
    	}
    	
		//图片
		private Integer[] imgs = {
				R.drawable.music,R.drawable.file
		};
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ImageView imageView = null;
			if (convertView == null) {
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(75, 75));
				imageView.setAdjustViewBounds(false);//边界对齐
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			}
			else{
				imageView = (ImageView)convertView;
			}
			
			imageView.setImageResource(imgs[position]);
			
			return imageView;
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public Object getItem(int position) {
			return position;
		}
		
		@Override
		public int getCount() {
			return imgs.length;
		}
    }
    
    
    private void initGrid(){
    	GridView gridView = (GridView)findViewById(R.id.gridview);
    	gridView.setAdapter(new MyAdapter(this));
    	gridView.setOnItemClickListener(new OnItemClickListener() {

			/* (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
//				Toast.makeText(HomePageActivity.this, "position:"+position, Toast.LENGTH_SHORT).show();
			}
    		
		});
    }
    
    private void initView(){
    	
    	network_show = (TextView)findViewById(R.id.network_show);
    	register_show = (TextView)findViewById(R.id.register_show);
    	btn_music = (ImageButton)findViewById(R.id.music);
    	btn_file = (ImageButton)findViewById(R.id.file);
    	btn_game = (ImageButton)findViewById(R.id.game);
//    	btn_service = (ImageButton)findViewById(R.id.convenience_services);
    	btn_tv = (ImageButton)findViewById(R.id.tv);
    	btn_set = (ImageButton)findViewById(R.id.set);
    	btn_phone = (ImageButton)findViewById(R.id.phone);
//    	btn_systemsetting = (ImageButton)findViewById(R.id.systemset);
    	btn_videoclip = (ImageButton)findViewById(R.id.videoclip);
    	
    	ClickEvent clickEvent = new ClickEvent();
    	btn_music.setOnClickListener(clickEvent);
    	btn_file.setOnClickListener(clickEvent);
    	btn_game.setOnClickListener(clickEvent);
//    	btn_service.setOnClickListener(clickEvent);
    	btn_tv.setOnClickListener(clickEvent);
    	btn_set.setOnClickListener(clickEvent);
    	btn_phone.setOnClickListener(clickEvent);
//    	btn_systemsetting.setOnClickListener(clickEvent);
    	btn_videoclip.setOnClickListener(clickEvent);
    	
    }
    @Override
    protected void onResume() {
    	Log.i(TAG, "onResume");
    	super.onResume();
    	Message msg = new Message();
		handlerNetworkMsg.sendMessage(msg);
    	btn_music.setFocusable(true);
    }
    
    public static boolean isPkgInstalled(Context ctx, String packageName){
        
    	PackageManager pm = ctx.getPackageManager();
        try {
            pm.getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return false;
        }
        return true;
    }
    
    private boolean sdCardIsExist(){
    	boolean sdCardExist = Environment.getExternalStorageState()   
        .equals(android.os.Environment.MEDIA_MOUNTED);  
    	return sdCardExist;
    }
    
    class ClickEvent implements View.OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if(v == btn_music)
			{
			  if(sdCardIsExist())
			  {
				  if(isPkgInstalled(HomePageActivity.this, "com.android.music"))
				  {
					  Intent mIntent=getPackageManager().getLaunchIntentForPackage("com.android.music");
					  startActivity(mIntent);  
				  }
				  else 
				  {
					  Toast.makeText(HomePageActivity.this, getString(R.string.appnotinstall), Toast.LENGTH_SHORT).show();
				  }
				  
			  }
			  else
			  {
				  Toast.makeText(HomePageActivity.this, getString(R.string.sdcardnotexist), Toast.LENGTH_SHORT ).show();
			  }
			}
			else if(v == btn_file)
			{
				
				if(sdCardIsExist())
				{
					
					if(isPkgInstalled(HomePageActivity.this, "com.file"))
					{
						Intent mIntent=getPackageManager().getLaunchIntentForPackage("com.file");
						startActivity(mIntent);  
					}
					else 
					{
						Toast.makeText(HomePageActivity.this, getString(R.string.appnotinstall), Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					Toast.makeText(HomePageActivity.this, getString(R.string.sdcardnotexist), Toast.LENGTH_SHORT ).show();
				}
			}
			else if(v == btn_game)
			{
				
				if(isPkgInstalled(HomePageActivity.this, "com.my.game"))
				{
					if(isPkgInstalled(HomePageActivity.this, "com.lianzhong.ddz600x800") ||
							isPkgInstalled(HomePageActivity.this, "org.cocos2dx.FishGame") ||
							isPkgInstalled(HomePageActivity.this, "com.halfbrick.fruitninjahd"))
					{
				        Intent mIntent=getPackageManager().getLaunchIntentForPackage("com.my.game");
		                startActivity(mIntent);
					}
					else 
					{
						Toast.makeText(HomePageActivity.this, getString(R.string.gamenotinstall), Toast.LENGTH_SHORT).show();
					}
				}
				else 
				{
					Toast.makeText(HomePageActivity.this, getString(R.string.appnotinstall), Toast.LENGTH_SHORT).show();
				}
			}
			else if(v == btn_tv)
			{
				if(isPkgInstalled(HomePageActivity.this, "lorent.stb.tv"))
				{
				Intent mIntent=getPackageManager().getLaunchIntentForPackage("lorent.stb.tv");
		        startActivity(mIntent);  
				}
				else 
				{
					Toast.makeText(HomePageActivity.this, getString(R.string.appnotinstall), Toast.LENGTH_SHORT).show();
				}
			}
			else if(v == btn_phone)
			{
				if(isPkgInstalled(HomePageActivity.this, "com.lorent.vovo"))
				{
					Intent mIntent=getPackageManager().getLaunchIntentForPackage("com.lorent.vovo");
			        startActivity(mIntent);  
				}
				else 
				{
					Toast.makeText(HomePageActivity.this, getString(R.string.appnotinstall), Toast.LENGTH_SHORT).show();
				}
			}
			else if(v == btn_set)
			{
				
				Intent intent =  new Intent();   
				intent.setAction("Android.intent.action.MAIN");   
				intent.setClassName("com.android.launcher", "com.android.launcher2.Launcher");   
				startActivity(intent);   

			}
			else if(v == btn_service){
				
			}
			else if(v == btn_systemsetting){
				Intent mIntent=getPackageManager().getLaunchIntentForPackage("com.android.settings");
		        startActivity(mIntent);  
			}
			else if(v == btn_videoclip){
				if(isPkgInstalled(HomePageActivity.this, "com.lorent.video")){
					Intent intent =  new Intent();   
					intent.setAction("Android.intent.action.MAIN");   
					intent.setClassName("com.lorent.video", "com.lorent.video.MainActivity");   
					startActivity(intent); 
				}
				else{
					Toast.makeText(HomePageActivity.this, getString(R.string.appnotinstall), Toast.LENGTH_SHORT).show();
				} 
			}
		}	
	}    
    
}