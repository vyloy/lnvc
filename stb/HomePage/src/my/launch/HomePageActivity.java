package my.launch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


import android.app.Activity;
import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivity extends Activity {
	
	private ImageButton btn_music,btn_file,btn_game,btn_tv,btn_phone,btn_set,btn_service;
	private TextView register_show,network_show;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	sendBroadcast(new Intent("lorent.stb.tv.MyServer"));
       
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("homepage oncreate()"+sDateFormat.format(new java.util.Date()));
        
        registerReceiver(lccMessage, new IntentFilter("com.lorent.lcc.register"));
        registerReceiver(networkMessage, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
       
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
						System.out.println("wifi network");
					}
					else if(type == ConnectivityManager.TYPE_MOBILE)
					{
						System.out.println("mobile network");
					}
					else
					{
						System.out.println("unknow network");
					}
					network_show.setText("网络已连接");
					
				}
				else
				{
					network_show.setText("网络未连接");
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
						register_show.setText("本机未注册");
					}
				}
				else{
					
					 
		            	
		            }
				}
			}			
		};
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	
    }
    
    private void initView(){
    	
    	network_show = (TextView)findViewById(R.id.network_show);
    	register_show = (TextView)findViewById(R.id.register_show);
    	btn_music = (ImageButton)findViewById(R.id.music);
    	btn_file = (ImageButton)findViewById(R.id.file);
    	btn_game = (ImageButton)findViewById(R.id.game);
    	btn_service = (ImageButton)findViewById(R.id.convenience_services);
    	btn_tv = (ImageButton)findViewById(R.id.tv);
    	btn_set = (ImageButton)findViewById(R.id.set);
    	btn_phone = (ImageButton)findViewById(R.id.phone);
    	
    	btn_music.setOnClickListener(new ClickEvent());
    	btn_file.setOnClickListener(new ClickEvent());
    	btn_game.setOnClickListener(new ClickEvent());
    	btn_service.setOnClickListener(new ClickEvent());
    	btn_tv.setOnClickListener(new ClickEvent());
    	btn_set.setOnClickListener(new ClickEvent());
    	btn_phone.setOnClickListener(new ClickEvent());
    	
    }
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
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
					  Toast.makeText(HomePageActivity.this, "应用程序未安装！", Toast.LENGTH_SHORT).show();
				  }
				  
			  }
			  else
			  {
				  Toast.makeText(HomePageActivity.this, "sdcard 不存在", Toast.LENGTH_SHORT ).show();
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
						Toast.makeText(HomePageActivity.this, "应用程序未安装！", Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					Toast.makeText(HomePageActivity.this, "sdcard 不存在", Toast.LENGTH_SHORT ).show();
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
						Toast.makeText(HomePageActivity.this, "游戏未安装！", Toast.LENGTH_SHORT).show();
					}
				}
				else 
				{
					Toast.makeText(HomePageActivity.this, "应用程序未安装！", Toast.LENGTH_SHORT).show();
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
					Toast.makeText(HomePageActivity.this, "应用程序未安装！", Toast.LENGTH_SHORT).show();
				}
			}
			else if(v == btn_phone)
			{
				if(isPkgInstalled(HomePageActivity.this, "com.lorent"))
				{
				Intent mIntent=getPackageManager().getLaunchIntentForPackage("com.lorent");
		        startActivity(mIntent);  
				}
				else 
				{
					Toast.makeText(HomePageActivity.this, "应用程序未安装！", Toast.LENGTH_SHORT).show();
				}
			}
			else if(v == btn_set)
			{
				
				Intent intent =  new Intent();   
				intent.setAction("Android.intent.action.MAIN");   
				intent.setClassName("com.android.launcher", "com.android.launcher2.Launcher");   
				startActivity(intent);   

			}
			else if(v == btn_service)
			{
				
			}
		}	
	}    
    
}