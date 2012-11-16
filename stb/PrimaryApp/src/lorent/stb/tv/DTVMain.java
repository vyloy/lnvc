package lorent.stb.tv;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.qd.bean.EPGInfo;
import com.qd.bean.EventBean;
import com.qd.bean.TunerParam;
import com.qd.jni.JniLoad;
import com.qd.jni.JniPlayer;
import com.qd.tool.DBFactory;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.PixelFormat;

import android.media.AudioManager;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
/**
 * 节目录制状态
 * @author Administrator
 *
 */
class Pvr
{
	
	static final int pvr_waiting=0;//添加后等待状态
	static final int pvr_doing=1;  //录制状态
	static final int pvr_end=2;    //手动停止状态
}

class SaveChannel
{	
	private int channelNum;
	private int serviceId;
	public int getChannelNum()
	{
		return channelNum;
	}
	
	public void setChannelNum(int channelNum)
	{
		this.channelNum = channelNum;
	}
	
	public int getServiceId()
	{
		return serviceId;
	}
	
	public void setServiceId(int serviceId)
	{
		this.serviceId = serviceId;
	}
}

public class DTVMain extends Activity implements Callback
{	
	public static DTVMain myself;
	
	public static final Uri CHANNEL_TB_URI = Uri.parse("content://stb.info/channel_tb");
	
	public static final Uri LOVE_TB_URI = Uri.parse("content://stb.info/love_tb");
	
	public static final Uri LOCK_TB_URI = Uri.parse("content://stb.info/lock_tb");
	
	public static final Uri FRELIST_TB_URI = Uri.parse("content://stb.info/frelist_tb");
	
	public static final Uri AUDIO_TB_URI = Uri.parse("content://stb.info/audio_tb");
	
	private static final Uri CA_TB_URI = Uri.parse("content://stb.info/ca_tb");
	
	private static final Uri CHANNEL_INFO_TB_URI = Uri.parse("content://stb.info/channel_info_tb");
	
	private static final String TAG="DTVMain";
	private String path="";
	private int ServiceId=0;
	private int period_ServiceId_tempt=0;  // 预约提示serviceId
	private int serviceId_listView_Tempt = 0;//channelList录制时 换台 缓存ServiceId
	private int isUpDown = 0;
	private String now_channel_num="",now_channel_name="";//频道列表选中时的编号和频道名称
	private String period_channel_num ="001";
	
	
	HashMap<String,String> mapHD = new HashMap<String,String>();   //保存ServiceId
	
	private boolean isFullscreen=true,isFull=false;  //全屏状态标识 和全频状态banner条标示符
	private LinearLayout linearLayoutFull,linearLayout_banner,linearLayout_introduce1,
	linearLayout_banner2,linearLayout_channelList,linearLayout_epg,
	linearLayout_epg_introduce,linearLayout_edit;
	private LinearLayout linearLayout_menu;
	private boolean isChannelList=false,isMenu=false,isEPG=false,isEPGIntroduce=false,isEdit=false;
	private boolean isbanner=false,isIntroduce=false;  //判断玲珑窗是否隐藏
	private Timer banner_timer=new Timer();
	private Timer t=new Timer();
	private Timer t1 = new Timer();     //预录定时结束
	private Timer tPeriod = new Timer();
	
	private int bannerCount=0;          //信息键按下次数
	private boolean isInput=false;     //信息键输入判断
	private boolean restart_timer=true;  //是否重启定时器
	private boolean showAllView=false;  //状态 full
	private int full_introduce_eventId=0;   //evnetId  全屏参数
	private TextView fullscreenNow,fullscreenNext,
	nameAndDate,fullIntroduce,fullTitle,fullName,fullTime;
	
	private boolean isStart=false;        //按键标识
	private String channelNumber="";      //频道号
	private boolean isChoice=true;        //选台开关
	private int nowChannel=0;             //当前频道号  
	private int ServiceType = 1;          //当前节目类型
	private TextView channelN;            //频道号  
	private TextView full_list_type;
	private int msg_pf=1,msg_close=2,msg_epg_introduce=3,msg_list_play=4,msg_num_play=5,
	msg_channel_play=6,msg_save_play=7,msg_schudle=9,msg_period = 10,msg_pvr = 11,msg_volume = 12;
	
	private int pf_close = 11;
	private SurfaceView mPreview;
	private SurfaceHolder holder;
	private boolean isFree = false;   //节目是否加密
	private TextView pvr_notice;      //录制标志
	private boolean isStopPvr = false; //是否终止录制
	private boolean isPvring = false;  //录制时间显示线程锁
	private int channel_num_tempt;  // 数字键换台缓存的频道号
	private int channel_type_tempt; // 缓存频道类型
	private LinearLayout media_auto_layout;
	private ProgressBar main_bar;
	private TextView media_level;
	private AudioManager am;               //音量管理对象
	private int bar2current,bar2max;       //系统音乐音量的最大值与当前值
	private boolean isMediaVolume = false;
	
	private HashMap<Integer,Integer> map_channelList = new HashMap<Integer,Integer>();
	private HashMap<Integer,Integer> map_bcList = new HashMap<Integer,Integer>();
	private String hh = "00";   //录制时间
	private String min = "00";
	private String ss = "00";
	private int ServiceId_num_tempt = 0;
	//menu
	private SimpleAdapter menu_adapter;
	private int msg_menu_size = 8;
	private ListView menu_listview;
	private int menu_position = 0;
	//channel list
	private static final String TV_SET="tv_set";  //sava last channel
	ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String, Object>>();
	ArrayList<HashMap<String, Object>> listTV = new ArrayList<HashMap<String, Object>>();
	ArrayList<HashMap<String, Object>> listBC = new ArrayList<HashMap<String, Object>>();
	ArrayList<HashMap<String, Object>> listLove = new ArrayList<HashMap<String, Object>>();
	private ListView listView_channelList,listView_bcList,listView_loveList;
	private boolean isTVList=true,isBCList=false,isLoveList=false;
	private TextView list_type;
	private ArrayList<SaveChannel> saveChannels = new ArrayList<SaveChannel>();
	private String isSaveState = "two";   //save channel state
	
	//period channel
	private boolean first_epg = true;  //epg 可以换台
	private ArrayList<HashMap<String,String>> listPeriodTV = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter adapter_period_tv;
	
	ArrayList<HashMap<String,Object>> listEPG = new ArrayList<HashMap<String, Object>>();
	HashMap<String, Object> mapEPG;
	private SimpleAdapter adapter_epg;
	HashMap<String, String> map_epg =new HashMap<String,String>(); //period channel serviceId
	private int n=0;                       //schedule 黃色鍵切換日期的累加器
	private int period_position=0;     //缓存频道列表项的索引
	private boolean isPeriodPlay = false;  //预约提示时  用户选择播放时为true
	private boolean isPvrPlay = false;     //预录提示      用户选择播放时为true
	int freq_Period=0;                     //频点值
	int freqNum_Period=0;                  //频点序号
	private int day=0;                     //getSchedule()的参数
	private TextView time_epg,epg_introduce;
	private Handler handleList=new Handler();        //channel list
	private Handler handlerMenu = new Handler();     //menu list
	private Handler handlerperiod = new Handler();   //period channellist
	private Handler handlerperiod_epg = new Handler();//period epglist
	private ListView listview_period_channel,listview_period_epg;
	private TextView time_epg_now;
	
	private int count = 0;    //总数据数
	private int pageSize = 10; //每页条数
	private int pageCount = 0; //总页数 
	private int nowPage = 1;   //当前页
	
	private boolean hasReveration=true; //节目预约失败提示
	
	//edit channel
	ArrayList<HashMap<String,Object>> listEditTV = new ArrayList<HashMap<String, Object>>();
	private SimpleAdapter adapter_edit_video;
	ArrayList<HashMap<String,Object>> listEditBC = new ArrayList<HashMap<String, Object>>();
	private SimpleAdapter adapter_edit_bc;
	ArrayList<HashMap<String,Object>> listEditLove = new ArrayList<HashMap<String, Object>>();
	private SimpleAdapter adapter;
	private ListView edit_video_listView,edit_bc_listView,edit_love_listView;
	private TextView edit_time,edit_video_bottom,edit_love_bottom,edit_now_time,edit_now_name,edit_next_time,edit_next_name;
	private TextView edit_video_txt,edit_bc_txt,edit_love_txt;
	private boolean isVideo=true,isBC=false,isLove=false;
	private TextView edit_love_title;   //隐藏喜爱标题
	
	private ListView listView_reveration;
	ArrayList<HashMap<String, Object>> listReveration = new ArrayList<HashMap<String, Object>>();//預約頻道
	SimpleAdapter adapter_reveration; 
	private HashMap<String,EventBean> map2 = new HashMap<String, EventBean>(); 
	private LinearLayout linearLayout_reveration;
	private boolean isReveration=false;
	private TextView reveration_time,reveration_introduce;
	
	ArrayList<HashMap<String, Object>> listPvr = new ArrayList<HashMap<String, Object>>();//录制列表
	HashMap<String, Object> mapPvr;
	private LinearLayout linearLayout_pvr;
	private TextView pvr_time,pvr_introduce;
	private boolean isPvr=false;
	private ListView pvr_listView;
	int pvr_state=Pvr.pvr_end;                       //录制状态
	private int eventId_pvr=0;             //存储eventId
	private int serviceId_pvr=0;           //serviceId
	private HashMap<String,EventBean> map_pvr = new HashMap<String, EventBean>();
	private SimpleAdapter adapter_pvr;
	
	private boolean isClickOrder=false; // 选择预约点播
	private boolean isClickSet = false; // 选择系统设置
	
	//无节目时搜台
	private boolean isNoData = false;
	private LinearLayout linearLayout_auto_notice;
	private ProgressBar bar;
	private TextView searchResult,freq,freqcount,tvandbc,ber,signal_quality,signal_strength;
	boolean isGetTuner = false;
	private int barMax;	
	private String fre="";
	private String symbol="";
	private String modulation="";
	private boolean isExit; 
	/** Called when the activity is first created. */
	SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);

        setContentView(R.layout.main);
        
        startService(new Intent(this, MyServer.class));  //启动服务
        getView();
        myself = this;
    }
    
    @Override
    protected void onRestart()
    {
    	// TODO Auto-generated method stub
    	super.onRestart();
    	Log.i(TAG, "on restart");
    	
       if(isClickOrder || isClickSet)
  	   {
  	      isFullscreen = true;
    	  isClickOrder = false;
  	      isClickSet = false;
  	      /*isMenu = true;
  	      linearLayout_menu.setVisibility(View.VISIBLE);*/
  	   }
       else
       {
    		if(isChannelList)
    		{
    			isChannelList=false;
    			linearLayout_channelList.setVisibility(View.INVISIBLE);
    			isFullscreen = true;
    			
    		}
    		else if(isEPG)
    		{
    			if(isEPGIntroduce)
    			{
    				isEPGIntroduce=false;
    				linearLayout_epg_introduce.setVisibility(View.INVISIBLE);
    			}
    			isEPG=false;
    			linearLayout_epg.setVisibility(View.INVISIBLE);
    			
    			isFullscreen=true;
    			//surfaceView 全屏设置
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		else if(isEdit)
    		{
    			isEdit=false;
    			linearLayout_edit.setVisibility(View.INVISIBLE);
    			
    			isFullscreen=true;
    			//surfaceView 全屏设置
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		else if(isReveration)
    		{
    			isReveration=false;
    			linearLayout_reveration.setVisibility(View.INVISIBLE);
    			
    			isFullscreen=true;
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		else if(isPvr)
    		{
    			isPvr=false;
    			linearLayout_pvr.setVisibility(View.INVISIBLE);
    			
    			isFullscreen=true;
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		
    		if(isFullscreen)
    		{
    			if(isMenu) 
                 {
                 	isMenu = false;
                 	linearLayout_menu.setVisibility(View.INVISIBLE);
                 }
    		}
    	}
    }
    
   @Override
   protected void onResume()
   {
    // TODO Auto-generated method stub
     super.onResume();
     Log.i(TAG, "on resume");
     if(isServiceRunning())
     {
        sendBroadcast(new Intent("com.android.music.musicservicecommand.pause")); //关闭系统播放器
     }
     registerReceiver(MsgReceiver, new IntentFilter("lorent.stb.tv.dvb"));//注册广播
     if(checkDB())
     {
    	 //init();                                 
     }
     else
     {
    	 isNoData  = false;
    	 showDialog(11);	
     }
     isExit = false;
    
   }
   
   private boolean isServiceRunning()
   {
	    ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
	    {
	        if ("com.android.music.MediaPlaybackService".equals(service.service.getClassName()))
	        {
	            return true;
	        }
	    }
	    return false;
	}


    private void getView()
    {
    	
    	media_auto_layout = (LinearLayout)findViewById(R.id.media_audio_linearLayout);
    	main_bar = (ProgressBar)findViewById(R.id.level_progressbar);
    	media_level = (TextView)findViewById(R.id.media_level_num);
    	am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		bar2max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		bar2current = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		main_bar.setMax(bar2max);
		main_bar.setProgress(bar2current);
		media_level.setText(bar2current+"");
		full_list_type = (TextView)findViewById(R.id.full_list_type);
		
		
    	linearLayout_auto_notice = (LinearLayout)findViewById(R.id.linearLayout_auto_notice);
        freq = (TextView)findViewById(R.id.init_searchChannel_freq);
        freqcount = (TextView)findViewById(R.id.init_searchaChannel_freqCount);
        tvandbc =(TextView)findViewById(R.id.init_searchaChannel_tv_bc);
        ber = (TextView)findViewById(R.id.init_search_searchChannel_ber);
        signal_quality = (TextView)findViewById(R.id.init_search_signal_quality);
        signal_strength = (TextView)findViewById(R.id.init_search_signal_strength);
	    bar=(ProgressBar)findViewById(R.id.init_seekbar);
	    bar.setMax(100);
	    searchResult=(TextView)findViewById(R.id.init_showResult);
	    searchResult.setText(getString(R.string.start_search));
       
    	
    	mPreview = (SurfaceView) findViewById(R.id.surfaceView);
    	mPreview.setVisibility(View.INVISIBLE);
    	holder = mPreview.getHolder();
    	holder.addCallback(this);
    	
    	pvr_notice = (TextView)findViewById(R.id.pvr_notice);
    	
    	time_epg = (TextView)findViewById(R.id.time_epg);
    	time_epg_now = (TextView)findViewById(R.id.time_epg_now);
    	epg_introduce = (TextView)findViewById(R.id.epg_introduce);
    	fullTitle = (TextView)findViewById(R.id.fullscreen_title);
    	fullName = (TextView)findViewById(R.id.fullscreen_name);
    	fullTime =(TextView)findViewById(R.id.full_time);
    	fullscreenNow = (TextView)findViewById(R.id.fullscreen_now);                
    	fullscreenNext = (TextView)findViewById(R.id.fullscreen_next);
    	nameAndDate = (TextView)findViewById(R.id.fullscreen_nowprogramNameAndDate);
    	fullIntroduce = (TextView)findViewById(R.id.fullscreen_introduce);
    	channelN=(TextView)findViewById(R.id.channelNumber);
    	
    	
    	edit_time = (TextView)findViewById(R.id.edit_time);
    	edit_video_txt = (TextView)findViewById(R.id.edit_video_txt);
    	edit_video_txt.setBackgroundResource(R.drawable.channel_edit_menu_checked);
    	edit_bc_txt = (TextView)findViewById(R.id.edit_bc_txt);
    	edit_love_txt = (TextView)findViewById(R.id.edit_love_txt);
    	edit_video_bottom = (TextView)findViewById(R.id.edit_video_bottom);
    	edit_love_bottom = (TextView)findViewById(R.id.edit_love_bottom);
    	edit_now_time = (TextView)findViewById(R.id.edit_now_time);
    	edit_now_name = (TextView)findViewById(R.id.edit_now_name);
    	edit_next_time =(TextView)findViewById(R.id.edit_next_time);
    	edit_next_name = (TextView)findViewById(R.id.edit_next_name);
    	edit_video_listView = (ListView)findViewById(R.id.edit_video_listview);
    	edit_bc_listView = (ListView)findViewById(R.id.edit_bc_listview);
    	edit_love_listView = (ListView)findViewById(R.id.edit_love_listview);
    	edit_love_title = (TextView)findViewById(R.id.edit_love_title);
    	
    	/*edit_video_listView.setDividerHeight(0);
    	
    	edit_bc_listView.setDividerHeight(0);
    	edit_love_listView.setDividerHeight(0);*/
    	
    	
    	linearLayout_reveration = (LinearLayout)findViewById(R.id.linearLayout_reveration);
    	reveration_time = (TextView)findViewById(R.id.reveration_time);
    	reveration_introduce = (TextView)findViewById(R.id.reveration_introduce);
    	listView_reveration = (ListView)findViewById(R.id.reveration_listview);
    	
    	linearLayout_pvr = (LinearLayout)findViewById(R.id.linearLayout_pvr);
    	pvr_introduce = (TextView)findViewById(R.id.pvr_introduce);
    	pvr_time = (TextView)findViewById(R.id.pvr_time);
    	pvr_listView = (ListView)findViewById(R.id.pvr_listview);
    	
    	list_type = (TextView)findViewById(R.id.channel_list_type);
    	list_type.setText(getString(R.string.main_channel_list));
    	listView_channelList = (ListView)findViewById(R.id.channelList_listView);
    	
    	listView_channelList.setOnItemClickListener(new ListView.OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				channel_type_tempt = 1;
				period_position = position;
				final TextView tNum = (TextView) view.findViewById(R.id.channel_list_num);
				final TextView tName= (TextView)view.findViewById(R.id.channel_list_name);
				now_channel_num = tNum.getText().toString().trim();
				now_channel_name = tName.getText().toString().trim();
				Map<String, String> mp = (Map<String, String>)listView_channelList.getItemAtPosition(position);   
				serviceId_listView_Tempt = Integer.parseInt(mp.get("ServiceId"));
				
				
				if(ServiceId==serviceId_listView_Tempt)return;
				
				if(pvr_state == Pvr.pvr_doing)
				{
					isUpDown = 5;
					showDialog(6);
				}
				else
				{
					set_listview_check();
			    }
				
			}});
    	listView_channelList.setOnFocusChangeListener(new OnFocusChangeListener(){

			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(channel_type_tempt == 1)
				{
					handleList.postAtFrontOfQueue(new Runnable(){

						public void run() {
							// TODO Auto-generated method stub
							listView_channelList.setSelection(period_position);
							
						}});
				}
			}});
    	listView_bcList = (ListView)findViewById(R.id.bcList_listView);
    	
    	listView_bcList.setOnItemClickListener(new ListView.OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final TextView tNum = (TextView) view.findViewById(R.id.channel_list_num);
				final TextView tName= (TextView)view.findViewById(R.id.channel_list_name);
				now_channel_num = tNum.getText().toString().trim();
				now_channel_name = tName.getText().toString().trim();
				Map<String, String> mp = (Map<String, String>)listView_bcList.getItemAtPosition(position);   
				serviceId_listView_Tempt = Integer.parseInt(mp.get("ServiceId"));
				
                if(ServiceId==serviceId_listView_Tempt)return;
				
				if(pvr_state == Pvr.pvr_doing)
				{
					isUpDown = 6;
					showDialog(6);
				}
				else
				{
					channel_type_tempt = 2;
					period_position = position;
					set_listview_check_bc();
			    }
				
			}});
    	listView_bcList.setOnFocusChangeListener(new ListView.OnFocusChangeListener(){

			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(channel_type_tempt == 2)
				{
				handleList.postAtFrontOfQueue(new Runnable(){

					public void run() {
						// TODO Auto-generated method stub
						listView_bcList.setSelection(period_position);
						
					}});
				}
			}});
    	listView_loveList = (ListView)findViewById(R.id.loveList_listView);
    	listView_loveList.setOnItemClickListener(new ListView.OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				channel_type_tempt = (Integer)listLove.get(position).get("serviceType");
				final TextView tNum = (TextView) view.findViewById(R.id.channel_list_num);
				final TextView tName= (TextView)view.findViewById(R.id.channel_list_name);
				now_channel_num = tNum.getText().toString().trim();
				now_channel_name = tName.getText().toString().trim();
				Map<String, String> mp = (Map<String, String>)listView_loveList.getItemAtPosition(position);   
				serviceId_listView_Tempt = Integer.parseInt(mp.get("ServiceId"));
				
                if(ServiceId==serviceId_listView_Tempt)return;
				
				if(pvr_state == Pvr.pvr_doing)
				{
					isUpDown = 5;
					showDialog(6);
				}
				else
				{
					set_listview_check();
			    }
				
				
			}});
    	
    	listview_period_channel = (ListView)findViewById(R.id.listview_period);
    	listview_period_channel.setOnItemSelectedListener(new ListView.OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				System.out.println("selected tv");
				if(isEPG)
				{
					 final TextView t=(TextView)arg1.findViewById(R.id.period_channel_num);	
					 period_channel_num = t.getText().toString();
				
				 if(pvr_state == Pvr.pvr_doing)
				 {
					 isUpDown= 7;	
					 showDialog(6);
			     }
				 else 
				 {
					 channel_type_tempt = 1; 
					 period_position=arg2;
					 set_listview_period_check();
				 }
				} 
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});
    	listview_period_channel.setOnFocusChangeListener(new OnFocusChangeListener(){

			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus)
				{
					if(channel_type_tempt == 1)
					{
					   handlerperiod.postAtFrontOfQueue(new Runnable(){

						  public void run() {
							// TODO Auto-generated method stub
							listview_period_channel.setSelection(period_position);
						  }});
					}
				}
					
			}});
    	
    	
    	listview_period_epg = (ListView)findViewById(R.id.listview_epg);
    	listview_period_epg.setOnItemSelectedListener(new OnItemSelectedListener(){

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				hasReveration = true;
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}});
    	listview_period_epg.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Map<String, Object> mp = (Map<String, Object>)listview_period_epg.getItemAtPosition(listview_period_epg.getSelectedItemPosition());   
		           
	            int eventId=(Integer)mp.get("eventId");
	            int i=JniLoad.AddOrder(ServiceId, eventId, 1);//添加到内存
	            int j=JniLoad.Save();
				
	            if(i==0 && j==0)
				{
				final ImageView image=(ImageView)listview_period_epg.getSelectedView().findViewById(R.id.period_list_periodPic);
				image.setImageResource(R.drawable.jog_tab_target_red);
				}
				else if(i == 4 && j == 0)
				{
					if(hasReveration)
					{
					  hasReveration = false;
					  //预约无效
					  Toast.makeText(DTVMain.this, "预约无效！", Toast.LENGTH_SHORT).show();
					}
				}
				else if(i==1 && j==0)
				{
					//时间冲突
					Toast.makeText(DTVMain.this, "该節目与预约列表中的节目冲突,预约失败！",Toast.LENGTH_SHORT).show();
					
				}
				
			}});
    	
    	listview_period_epg.setOnFocusChangeListener(new OnFocusChangeListener(){

			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus)
				{
					handlerperiod_epg.postAtFrontOfQueue(new Runnable(){

						public void run() {
							// TODO Auto-generated method stub
							listview_period_epg.setSelection(0);
						}});
				}
					
			}});
    	linearLayoutFull = (LinearLayout)findViewById(R.id.linearLayout_fullscreen);        //全頻時視頻層上面的窗口 包含fullscreen1和linearLayout_horital
    	linearLayout_introduce1 = (LinearLayout)findViewById(R.id.linearLayout_introduce);          //節目簡介的view
    	linearLayout_banner=(LinearLayout)findViewById(R.id.full_banner_and_advertisement);//banner 条
    	linearLayout_banner2 = (LinearLayout)findViewById(R.id.fullscreen2);          //banner條的view
    	
    	linearLayout_channelList = (LinearLayout)findViewById(R.id.linearLayout_channelList);
    	linearLayout_epg = (LinearLayout)findViewById(R.id.linearLayout_egp);
    	linearLayout_epg_introduce = (LinearLayout)findViewById(R.id.linearLayout_epg_introduce);
//    	linearLayout_epg_introduce.getBackground().setAlpha(150);
    	linearLayout_edit =(LinearLayout)findViewById(R.id.linearLayout_edit);
    	linearLayout_menu=(LinearLayout)findViewById(R.id.linearLayout_menu_item);
    	
    	
    	Time t=new Time();  
		t.setToNow();
		day=t.monthDay;
		menu_listview = (ListView)findViewById(R.id.listview_menu);
    	menu_listview.setOnItemClickListener(new ListView.OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				menu_position = arg2;
				switch(arg2)
				{
				
				case 0:
					
					isMenu = false;
					linearLayout_menu.setVisibility(View.INVISIBLE);
					isFullscreen = false;
					isChannelList=true;
					linearLayout_channelList.setVisibility(View.VISIBLE);
					linearLayout_channelList.getBackground().setAlpha(180);
					
					if(channel_type_tempt == 1)
					{
						
						list_type.setText(getString(R.string.main_channel_list));
						isLoveList=false;
						listView_loveList.setVisibility(View.INVISIBLE);
						isBCList = false;
						listView_bcList.setVisibility(View.INVISIBLE);
						isTVList=true;
						listView_channelList.setVisibility(View.VISIBLE);
					}
					else if(channel_type_tempt == 2)
					{
						list_type.setText(getString(R.string.main_bc_list));
						isLoveList=false;
						listView_loveList.setVisibility(View.INVISIBLE);
						isTVList=false;
						listView_channelList.setVisibility(View.INVISIBLE);
						isBCList = true;
						listView_bcList.setVisibility(View.VISIBLE);
					}
					/*if(listTV.size()==0)
					loadChannelList();
					if(listBC.size() == 0)
					loadBCList();
					*/
					loadLoveList();
					
					//initValue(0, pageSize);
					
					break;
				case 1:
					
					isMenu = false;
					linearLayout_menu.setVisibility(View.INVISIBLE);
					isFullscreen=false;
					linearLayout_edit.setVisibility(View.VISIBLE);
					isEdit=true;
					
					pvr_notice.setVisibility(View.INVISIBLE);
					
					//加载频道列表
					loadEditTV();
					loadEditBC();
					loadEditLove();
					//设置小窗口
					handler_minWindow.removeMessages(msg_menu_size);
					handler_minWindow.sendMessageDelayed(handler_minWindow.obtainMessage(msg_menu_size), 500);
					
					break;
				case 2:
					pvr_notice.setVisibility(View.INVISIBLE);
					isMenu = false;
					linearLayout_menu.setVisibility(View.INVISIBLE);
					isFullscreen=false;
					linearLayout_epg.setVisibility(View.VISIBLE);
					isEPG=true;
					hasReveration = true;  
					//loadPeriodTV();
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日EEE");
			 		String sDate=df1.format(new Date()).replace("周", "星期");
			 		time_epg.setText(sDate);
					
			 		//设置小窗口
			 		handler_minWindow.removeMessages(msg_menu_size);
			 		handler_minWindow.sendMessageDelayed(handler_minWindow.obtainMessage(msg_menu_size), 500);
			 		
			 		if(listEPG!=null)
					{
						listEPG.clear();
						if(adapter_epg!=null)
							adapter_epg.notifyDataSetChanged();
					}
			 		/*handlerDelay_schudle.removeMessages(msg_schudle);
					handlerDelay_schudle.sendMessageDelayed(handlerDelay_schudle.obtainMessage(msg_schudle), 100);*/
					changeSchedule(ServiceId);
			 		break;
				case 3:
					 if(pvr_state == Pvr.pvr_doing)
					 {
						 isUpDown= 8;	
						 showDialog(6);
				     }
					 else 
					 {
						 vod();
					 }
					
				    break;
				case 4:
					pvr_notice.setVisibility(View.INVISIBLE);
					isMenu = false;
					linearLayout_menu.setVisibility(View.INVISIBLE);
					isFullscreen=false;
					isReveration=true;
					linearLayout_reveration.setVisibility(View.VISIBLE);
					String full_list_instroduce=JniLoad.GetShortDescriptor(ServiceId, full_introduce_eventId);		    		
					reveration_introduce.setText(full_list_instroduce);
					setOrderList();   //加载预约列表
					//设置小窗口
					handler_minWindow.removeMessages(msg_menu_size);
					handler_minWindow.sendMessageDelayed(handler_minWindow.obtainMessage(msg_menu_size), 200);
					break;
				case 5:
					pvr_notice.setVisibility(View.INVISIBLE);
					isMenu = false;
					linearLayout_menu.setVisibility(View.INVISIBLE);
					isFullscreen=false;
					isPvr=true;
					linearLayout_pvr.setVisibility(View.VISIBLE);
					String pvr_introduce_tempt=JniLoad.GetShortDescriptor(ServiceId, full_introduce_eventId);		    		
					pvr_introduce.setText(pvr_introduce_tempt);
					setPVRList();
					//设置小窗口
					handler_minWindow.removeMessages(msg_menu_size);
					handler_minWindow.sendMessageDelayed(handler_minWindow.obtainMessage(msg_menu_size), 200);
					break;
				case 6:
					 if(pvr_state == Pvr.pvr_doing)
					 {
						 isUpDown= 9;	
						 showDialog(6);
				     }
					 else 
					 {
						 tvSet();
					 }
					
					break;
				}
			}});
    	
    	menu_listview.setOnFocusChangeListener(new ListView.OnFocusChangeListener(){

			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				menu_listview.getSelector().setAlpha(100);
				handlerMenu.postAtFrontOfQueue(new Runnable(){

					public void run() {
						// TODO Auto-generated method stub
						menu_listview.setSelection(menu_position);
						
					}});
			}});
   
    	
    	
		 new Thread(new Runnable(){

				public void run(){
					while(true){
					  
				        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");		      
				        String sFull = df2.format(new Date());
				        SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日EEE HH:mm");
				 		String sDate=df1.format(new Date()).replace("周", "星期");
				        try {
				        	   Message msg = new Message();
						       String[] dates ={sFull,sDate};
						       Bundle b = new Bundle();
						       b.putStringArray("setTime", dates);
						       msg.setData(b);
						       mHandler.sendMessage(msg);
						       Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}}).start();
    }
    //vod
    private void vod()
    {
    	isMenu = false;
		linearLayout_menu.setVisibility(View.INVISIBLE);
		isFullscreen=false;
		isClickOrder = true;
		Intent intent_vod = new Intent(DTVMain.this,OrderActivity.class);
		startActivity(intent_vod);
    }
    private void tvSet()
    {
    	isMenu = false;
		linearLayout_menu.setVisibility(View.INVISIBLE);
		isFullscreen=false;
		isClickSet = true;
		Intent intent_set = new Intent(DTVMain.this,SettingActivity.class);
		startActivity(intent_set);
    }
    
    private void set_listview_check_bc()
    {
		ServiceId = serviceId_listView_Tempt;
		
		new Thread(new Runnable()
			{
	
				public void run()
				{
					// TODO Auto-generated method stub
					System.out.println("listview_check_bc defaultPath");
					defaultPath(ServiceId);
					
				}
			}
		).start();
		
		send(now_channel_num);
		updateNowChannel(ServiceId+"");
		changePF();
		linearLayout_channelList.setVisibility(View.INVISIBLE);
		isChannelList=false;
		isFullscreen = true;
    }
    private void set_listview_check()
    {
    	ServiceId=serviceId_listView_Tempt;
		new Thread(new Runnable()
			{
				public void run()
				{
					// TODO Auto-generated method stub
					System.out.println("listview_check defaultPath");
					defaultPath(ServiceId);
					
				}
			}
		).start();
		
		fullTitle.setText(now_channel_num+"\t\t"+now_channel_name);
		fullName.setText(now_channel_num+"\t\t"+now_channel_name);
		send(now_channel_num);
		
		updateNowChannel(ServiceId+"");
		changePF();
		linearLayout_channelList.setVisibility(View.INVISIBLE);
		isChannelList=false;
		isFullscreen = true;
		//显示频道号
		String ChannelNumTempt=nowChannel+"";
			if(ChannelNumTempt.length()==1)
			{
				ChannelNumTempt = "00"+ChannelNumTempt;
			}
			else if(ChannelNumTempt.length()==2)
			{
				ChannelNumTempt = "0"+ChannelNumTempt;
			}
			channelN.setText("频道"+ChannelNumTempt);
			
			handlerCloseView.removeMessages(msg_close);
	    	handlerCloseView.sendMessageDelayed(handlerCloseView.obtainMessage(msg_close), 5000);
	    	
	    	SaveChannel sc=new SaveChannel();
          sc.setChannelNum(nowChannel);
          sc.setServiceId(ServiceId);
          saveChannels.add(sc);
          if(saveChannels.size()>2)
          {
             saveChannels.remove(0);
          }
    }
    private void set_listview_period_check()
    {
    	//设置当前日期		 
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日EEE");
		String sDate=df1.format(new Date()).replace("周", "星期");
		time_epg.setText(sDate); 
		if(listEPG!=null)
		{
			listEPG.clear();
			if(adapter_epg!=null)
				adapter_epg.notifyDataSetChanged();
		}
		n=0;                                                                  //切換頻道時累加器清零							 
		
		send(period_channel_num);
		ServiceId = Integer.parseInt(map_epg.get(period_channel_num));
		
		/*handlerDelay_schudle.removeMessages(msg_schudle);
		handlerDelay_schudle.sendMessageDelayed(handlerDelay_schudle.obtainMessage(msg_schudle), 500);
		*/
		handlerDelay_TV.removeMessages(msg_list_play);
		handlerDelay_TV.sendMessageDelayed(handlerDelay_TV.obtainMessage(msg_list_play), 500);
		
    }
    private Handler handler_minWindow = new Handler(){
    	public void handleMessage(Message msg) {
    		
    		JniPlayer.setWindowSize(20, 50, 260, 180);
    	};
    };
    
    private List<HashMap<String,String>> getData(){                
    	String[] menus = {"频道列表","频道编辑","节目预告","预约点播","预约播放","预约录制","系统设置"};
    	List<HashMap<String,String>> menuData = new ArrayList<HashMap<String, String>>();      
    	HashMap<String, String> menuMap = null;
    	
    	for(int i=0;i< menus.length;i++)
    	{
    		menuMap = new HashMap<String, String>();
    	    menuMap.put("name", menus[i]); 
    	    menuData.add(menuMap);
    	}
    	return menuData;    
   } 
   
    /**
     * editLove name
     * @return
     */
    private ArrayList<String> getLoveName(){
		
		ArrayList<String> loveList=new ArrayList<String>();
		
		Cursor Mycursor = getContentResolver().query(DTVMain.LOVE_TB_URI, new String[]{"loveName"},
				null,null, null);
		while (Mycursor.moveToNext()) {

			String loveName = Mycursor.getString(Mycursor.getColumnIndex("loveName"));
			loveList.add(loveName);
		}
		Mycursor.close();
		
		return loveList;
	}
    /**
     * get lockSid
     * @return
     */
    private ArrayList<Integer> getLockSid()
    {
    	ArrayList<Integer> lockList=new ArrayList<Integer>();
    	Cursor Mycursor = getContentResolver().query(DTVMain.LOCK_TB_URI, new String[]{"serviceId"},
    			null,null, null);
		while (Mycursor.moveToNext()) {

			Integer lockSid = Mycursor.getInt(Mycursor.getColumnIndex("serviceId"));
			lockList.add(lockSid);
		}
		Mycursor.close();
		return lockList;
    }
    /**
	 * 
	 * 加载预录列表
	 */
	private void setPVRList()
	{
		if(listPvr != null)
		{
			listPvr.clear();
		}
		
		try
		{
			EventBean[] eventBean = JniLoad.PVRGetAll();
			int pvrLength=eventBean.length;
			
		    Cursor Mycursor = null;
		    for(int i=0;i<pvrLength;i++)
		    {
			
				mapPvr = new HashMap<String,Object>();
				mapPvr.put("num", (i+1)+"");
				mapPvr.put("event_name", eventBean[i].getEventName());
				
				int sid = eventBean[i].getSerId();
	 			String cn_tempt="";
	 			Iterator<String> it= mapHD.keySet().iterator();              
	 	        while(it.hasNext())
	 	        {
	 	             String keyString=it.next();
	 	             if(mapHD.get(keyString).equals(sid+"")) 
	 	             {
	 	            	 cn_tempt=keyString;
	 	                 break;
	 	             }
	 	        }
	 	        
	 	        if(cn_tempt.length()==1)
	 	        {
	 	        	cn_tempt = "00"+cn_tempt;
	 	        }
	 	        else if(cn_tempt.length()==2)
	 	        {
	 	        	cn_tempt = "0"+cn_tempt;
	 	        }
	 			mapPvr.put("channel_num", cn_tempt);
		        
	 			String sql = "ServiceId = ?";
	 			Mycursor = getContentResolver().query(DTVMain.CHANNEL_TB_URI, new String[]{"ServiceName"},
	 	    			sql,new String[]{sid+""}, null);
	 			
		        while (Mycursor.moveToNext())
		        {
				    byte[] s=Mycursor.getBlob(0);
				    String tvName1 = null;
				    try
				    {
				    	tvName1 = new String(s,"utf8");//new String(s,"gb2312");
				    }
				    catch (UnsupportedEncodingException e)
				    {
						// TODO Auto-generated catch block
						e.printStackTrace();
				    }
				    mapPvr.put("channel_name", tvName1);
					//开始时间
					String starth1="";
					String startm1="";
					int starth = eventBean[i].getEventStartTime()/ 3600 + 8;
					
					if(starth>=24)
					{
						starth=starth%24;
					}
				
					if (eventBean[i].getEventStartTime() % 3600 == 0)
					{
						if (starth < 10)
						{
							starth1 = "0" + starth;
						}
						else
						{
							starth1 = starth + "";
						}
						startm1 = "00";
					}
					else
					{
	
						if (starth < 10)
						{
							starth1 = "0" + starth;
						}
						else
						{
							starth1 = starth + "";
						}
						
						int startm = eventBean[i].getEventStartTime() % 3600 / 60;
	
						if (startm < 10)
						{
							startm1 = "0" + startm;
						}
						else
						{
							startm1 = startm + "";
						}
					}
					
					String endh1 = "";
					String endm1 = "";
					int endh = (eventBean[i].getEventStartTime() + eventBean[i]
							.getEventDuration()) / 3600 + 8;
					
		            if(endh>=24)
		            {
		            	endh=endh%24;
		            }
		            
				if ((eventBean[i].getEventStartTime() + eventBean[i].getEventDuration()) % 3600 == 0)
				{
					if (endh < 10)
					{
						endh1 = "0" + endh;
					}
					else
					{
						endh1 = endh + "";
					}
					
					endm1 = "00";
				}
				else
				{
					if (endh < 10)
					{
						endh1 = "0" + endh;
					}
					else
					{
						endh1 = endh + "";
					}
	
					int endm = (eventBean[i].getEventStartTime() + eventBean[i]
							.getEventDuration()) % 3600 / 60;
	
					if (endm < 10)
					{
						endm1 = "0" + endm;
					}
					else
					{
						endm1 = endm + "";
					}
				}
				mapPvr.put("event_time",starth1+":"+startm1+"-"+endh1+":"+endm1);
				
				if(pvr_state==Pvr.pvr_doing)
				{
				    if(eventBean[i].getEventId()== eventId_pvr)
				    {
				    	mapPvr.put("state", "录制");
				    }
				    else
				    {
				    	mapPvr.put("state", "等待");
				    }
				}
				else if(pvr_state==Pvr.pvr_waiting)
				{
					mapPvr.put("state", "等待");
				}
				else if(pvr_state==Pvr.pvr_end)				
				{
					if(eventBean[i].getEventId()== eventId_pvr)
					{
						mapPvr.put("state", "停止");
					}
					else
					{
						mapPvr.put("state", "等待");
					}
				}
				
				listPvr.add(mapPvr);
				
				//保存预录列表信息
				EventBean en=new EventBean();
				en.setSerId(eventBean[i].getSerId());
				en.setEventId(eventBean[i].getEventId());
				map_pvr.put(Integer.toString(i+1), en);
		   }
		}

		    
 		Mycursor.close();
 		
		adapter_pvr = new SimpleAdapter(DTVMain.this, listPvr,
				R.layout.pvr_content
				, new String[] { "num","channel_num","channel_name","event_name","event_time","state"}
				, new int[] {R.id.pvr_num,R.id.pvr_channel_num,R.id.pvr_channel_name,R.id.pvr_event_name,R.id.pvr_event_time,R.id.pvr_state });

		pvr_listView.setAdapter(adapter_pvr);
		}catch(NullPointerException e){
		
			adapter_pvr = new SimpleAdapter(DTVMain.this, listPvr,
					R.layout.pvr_content, new String[] { "num","channel_num","channel_name","event_name","event_time","state"}, new int[] {R.id.pvr_num,R.id.pvr_channel_num,R.id.pvr_channel_name,R.id.pvr_event_name,R.id.pvr_event_time,R.id.pvr_state });

			pvr_listView.setAdapter(adapter_pvr);
			return;
		}

	}
    /**
     * 加载edit tv 列表
     */
    private void loadEditTV()
    {
    	if (listEditTV != null)
			listEditTV.clear();
    	ArrayList<String> loveNames=getLoveName();
    	ArrayList<Integer> lockSids=getLockSid();
    	HashMap<String, Object> map;
		
    	String sql = "MyServiceType = ?";
    	Cursor Mycursor = getContentResolver().query(DTVMain.CHANNEL_INFO_TB_URI, new String[]{"MyLogicNum","MyServiceName","MyServiceId"},
    			sql,new String[]{"1"}, null);
		
		while (Mycursor.moveToNext()) {
			
			String tvName = Mycursor.getString(Mycursor.getColumnIndex("MyServiceName"));
			String tvId = Mycursor.getString(Mycursor.getColumnIndex("MyLogicNum"));
			int sId=Mycursor.getInt(Mycursor.getColumnIndex("MyServiceId"));
			
			map = new HashMap<String, Object>();
			map.put("num", tvId );
			map.put("name", tvName.trim());
			boolean hasLock=false;
			for(Integer slock:lockSids){
				try{
				if(sId==slock){
					hasLock=true;
				}
				}catch(Exception e)
				{
					break;
				}
			}
			if(hasLock){
				map.put("lockImage", R.drawable.ic_secure);
			}else{
				map.put("lockImage", R.drawable.ic_partial_secure);
				
			}
			boolean hasLove=false;
			for(String slove:loveNames){
				try{	
				if(tvName.trim().equals(slove.trim())){
					hasLove=true;
				}
				}catch(Exception e){
					break;
				}
			}
			if(hasLove){
				map.put("loveImage", R.drawable.rate_star_small_on);
			}else{
				map.put("loveImage", R.drawable.rate_star_small_off);
				
			}
			map.put("ServiceId", sId+"");
			listEditTV.add(map);
			
			
		}

		Mycursor.close();
		
		adapter_edit_video = new SimpleAdapter(DTVMain.this, listEditTV,
				R.layout.edit_video_content, new String[] { "num","name","lockImage","loveImage" }, new int[] {R.id.edit_vedeo_num,R.id.edit_video_name,R.id.edit_video_lock_image,R.id.edit_video_love_image});

		edit_video_listView.setAdapter(adapter_edit_video);
    }
    /**
     * 加载edit bc 列表
     */
    private void loadEditBC()
    {
    	if (listEditBC != null)
			listEditBC.clear();
    	
    	ArrayList<String> loveNames=getLoveName();
    	ArrayList<Integer> lockSids=getLockSid();
    	HashMap<String, Object> map;
		
    	String sql = "MyServiceType = ?";
    	Cursor Mycursor = getContentResolver().query(DTVMain.CHANNEL_INFO_TB_URI, new String[]{"MyLogicNum","MyServiceName","MyServiceId"},
    			sql,new String[]{"2"}, null);
		
		while (Mycursor.moveToNext())
		{	
			String tvName = Mycursor.getString(Mycursor.getColumnIndex("MyServiceName"));
			String tvId = Mycursor.getString(Mycursor.getColumnIndex("MyLogicNum"));
			int sId=Mycursor.getInt(Mycursor.getColumnIndex("MyServiceId"));
			
			map = new HashMap<String, Object>();
			map.put("num", tvId );
			map.put("name", tvName);
			boolean hasLock=false;
			for(Integer slock:lockSids)
			{
				try
				{
					if(sId==slock)
					{
						hasLock=true;
					}
				}
				catch(Exception e)
				{
					break;
				}
			}
			
			if(hasLock)
			{
				map.put("lockImage", R.drawable.ic_secure);
			}
			else
			{
				map.put("lockImage", R.drawable.ic_partial_secure);	
			}
			
			boolean hasLove=false;
			for(String slove:loveNames)
			{
				try
				{	
					if(tvName.trim().equals(slove.trim()))
					{
						hasLove=true;
					}
				}
				catch(Exception e)
				{
					break;
				}
			}
			if(hasLove)
			{
				
				map.put("loveImage", R.drawable.rate_star_small_on);
			}
			else
			{
				map.put("loveImage", R.drawable.rate_star_small_off);
			}
			
			map.put("ServiceId", sId+"");
			listEditBC.add(map);
			
		}

		Mycursor.close();
		
		adapter_edit_bc = new SimpleAdapter(DTVMain.this, listEditBC,
				R.layout.edit_bc_content, new String[] { "num","name","lockImage","loveImage" }, new int[] {R.id.edit_bc_num,R.id.edit_bc_name,R.id.edit_bc_lock_image,R.id.edit_bc_love_image});

		edit_bc_listView.setAdapter(adapter_edit_bc);
    }
    
    /**
     * 加载edit love 列表
     */
    private void loadEditLove()
    {
    	if (listEditLove != null)
			listEditLove.clear();
    	ArrayList<Integer> lockSids=getLockSid();
    	HashMap<String, Object> map;
		
    	Cursor Mycursor = getContentResolver().query(DTVMain.LOVE_TB_URI, new String[]{"logicNum","loveName","serviceId","serviceType"},
    			null,null, null);
		
		while (Mycursor.moveToNext()) {
			String tvId = Mycursor.getString(Mycursor.getColumnIndex("logicNum"));
			String tvName =Mycursor.getString(Mycursor.getColumnIndex("loveName"));
			int sId=Mycursor.getInt(Mycursor.getColumnIndex("serviceId"));
			int sType = Mycursor.getInt(Mycursor.getColumnIndex("serviceType"));
			
			map = new HashMap<String, Object>();
			map.put("num", tvId );
			map.put("name", tvName);
			boolean hasLock=false;
			for(Integer slock:lockSids){
				try{
				if(sId==slock){
					hasLock=true;
				}
				}catch(Exception e)
				{
					break;
				}
			}
			if(hasLock){
				map.put("lockImage", R.drawable.ic_secure);
			}else{
				map.put("lockImage", R.drawable.ic_partial_secure);
				
			}
			
			map.put("ServiceId", sId+"");
			map.put("serviceType",sType);
			listEditLove.add(map);
			
		}

		Mycursor.close();
		
		adapter = new SimpleAdapter(DTVMain.this, listEditLove,
				R.layout.edit_love_content, new String[] { "num","name","lockImage"}, new int[] {R.id.edit_love_num,R.id.edit_love_name,R.id.edit_love_lock_image});

		edit_love_listView.setAdapter(adapter);
    }
    
    /**
     * 加载period频道列表
     */
    private void loadPeriodTV()
    {
    	if (listPeriodTV != null)
			listPeriodTV.clear();
    	
    	if(map_epg!=null)
    		map_epg.clear();
    	HashMap<String, String> map;
    	
    	String sql = "MyServiceType = ?";
    	Cursor Mycursor = getContentResolver().query(DTVMain.CHANNEL_INFO_TB_URI, new String[]{"MyLogicNum","MyServiceId","MyServiceName"},
    			sql,new String[]{"1"}, null);
		
		while (Mycursor.moveToNext()) {
			
			String logicNum = Mycursor.getString(Mycursor.getColumnIndex("MyLogicNum"));
			String tvName = Mycursor.getString(Mycursor.getColumnIndex("MyServiceName"));
			int sId=Mycursor.getInt(Mycursor.getColumnIndex("MyServiceId"));
			
			map = new HashMap<String, String>();
			map.put("num", logicNum );
			map.put("name", tvName);
			map.put("ServiceId", sId+"");
			listPeriodTV.add(map);
			
			map_epg.put(logicNum, sId+"");
		}
      
		Mycursor.close();
		adapter_period_tv = new SimpleAdapter(DTVMain.this, listPeriodTV,
				R.layout.period_channel_content, new String[] { "num","name" }, new int[] {R.id.period_channel_num,R.id.period_channel_name});

		listview_period_channel.setAdapter(adapter_period_tv);
		
    }
    
    
    /**
     * 加载channel list 列表
     */
    private void loadChannelList()
    {
    	if (listTV != null)
    		listTV.clear();
    	ArrayList<Integer> lockSids=getLockSid();
    	HashMap<String, Object> map;
    	String sql = "MyServiceType=?";
    	Cursor Mycursor = getContentResolver().query(DTVMain.CHANNEL_INFO_TB_URI, new String[]{"MyLogicNum","MyServiceId","MyServiceName"},
    			sql,new String[]{"1"}, null);
		int count = 0;
		while (Mycursor.moveToNext()) {
			
			String tvName = Mycursor.getString(Mycursor.getColumnIndex("MyServiceName"));
			int sId=Mycursor.getInt(Mycursor.getColumnIndex("MyServiceId"));
			String tvId = Mycursor.getString(Mycursor.getColumnIndex("MyLogicNum")); 
			map = new HashMap<String, Object>();
			map.put("num", tvId );
			map.put("name", tvName.trim());
			map.put("ServiceId", sId+"");
			boolean hasLock=false;
			for(Integer slock:lockSids){
				try{
				if(sId==slock){
					hasLock=true;
				}
				}catch(Exception e)
				{
					break;
				}
			}
			if(hasLock){
				map.put("image", R.drawable.ic_secure);
			}else{
				map.put("image", R.drawable.ic_partial_secure);
				
			}
			
			listTV.add(map);
			
			map_channelList.put(sId, count);//保存频道列表的索引
			count++;
		}
        Mycursor.close();
		
		SimpleAdapter adapter = new SimpleAdapter(DTVMain.this, listTV,
				R.layout.channel_listview_content, new String[] { "num","name","image" }, new int[] {R.id.channel_list_num,R.id.channel_list_name,R.id.channel_list_image });

		listView_channelList.setAdapter(adapter);
		/*count = data.size();
		if (count % pageSize == 0)
			pageCount = count / pageSize;
		else
			pageCount = count / pageSize + 1;
		*/
		
    }
    /**
	 * 
	 * 数据分页加载
	 * 
	 * @param pageStart
	 *            起始数
	 * @param pageEnd
	 *            结束数
	 * 
	 **/
	public void initValue(int pageStart, int pageEnd) {
		if (listTV != null)
			listTV.clear();

		for (int i = pageStart; i < pageEnd; i++) {
			if (i < data.size())
				listTV.add(data.get(i));
			else
				break;
		}

		SimpleAdapter adapter = new SimpleAdapter(DTVMain.this, listTV,
				R.layout.channel_listview_content, new String[] { "num","name","image" }, new int[] {R.id.channel_list_num,R.id.channel_list_name,R.id.channel_list_image });

		listView_channelList.setAdapter(adapter);

	}
	/**
	 * load BC
	 */
	private void loadBCList()
	{
		if (listBC != null)
    		listBC.clear();
		ArrayList<Integer> lockSids=getLockSid();
    	HashMap<String, Object> map;
    	
    	String sql = "MyServiceType=?";
    	Cursor Mycursor = getContentResolver().query(DTVMain.CHANNEL_INFO_TB_URI, new String[]{"MyLogicNum","MyServiceName","MyServiceId"},
    			sql,new String[]{"2"}, null);
		int count = 0;
		while (Mycursor.moveToNext()) {
			
			String tvId = Mycursor.getString(Mycursor.getColumnIndex("MyLogicNum"));
			String tvName = Mycursor.getString(Mycursor.getColumnIndex("MyServiceName"));
			int sId=Mycursor.getInt(Mycursor.getColumnIndex("MyServiceId"));
			map = new HashMap<String, Object>();
			map.put("num", tvId );
			map.put("name", tvName);
			
			boolean hasLock=false;
			for(Integer slock:lockSids){
				try{
				if(sId==slock){
					hasLock=true;
				}
				}catch(Exception e)
				{
					break;
				}
			}
			if(hasLock){
				map.put("image", R.drawable.ic_secure);
			}else{
				map.put("image", R.drawable.ic_partial_secure);
				
			}
			map.put("ServiceId", sId+"");
			listBC.add(map);
			
			map_bcList.put(sId, count);
			count++;
            
		}
		Mycursor.close();
		SimpleAdapter adapter = new SimpleAdapter(DTVMain.this, listBC,
				R.layout.channel_listview_content, new String[] { "num","name","image" }, new int[] {R.id.channel_list_num,R.id.channel_list_name,R.id.channel_list_image});

		listView_bcList.setAdapter(adapter);
	}
	
	/**
	 * load love
	 */
	private void loadLoveList()
	{
		if (listLove != null)
    		listLove.clear();
		ArrayList<Integer> lockSids=getLockSid();
    	HashMap<String, Object> map;
    	
    	Cursor Mycursor = getContentResolver().query(DTVMain.LOVE_TB_URI, new String[]{"logicNum","serviceId","loveName","serviceType"},
    			null,null, null);
		
		while (Mycursor.moveToNext()) {
			String tvId = Mycursor.getString(Mycursor.getColumnIndex("logicNum"));
			String tvName = Mycursor.getString(Mycursor.getColumnIndex("loveName"));
			int sId=Mycursor.getInt(Mycursor.getColumnIndex("serviceId"));
			int sType = Mycursor.getInt(Mycursor.getColumnIndex("serviceType"));
			
			map = new HashMap<String, Object>();
			map.put("num", tvId );
			map.put("name", tvName);
			boolean hasLock=false;
			for(Integer slock:lockSids){
				try{
				if(sId==slock){
					hasLock=true;
				}
				}catch(Exception e)
				{
					break;
				}
			}
			if(hasLock){
				map.put("image", R.drawable.ic_secure);
			}else{
				map.put("image", R.drawable.ic_partial_secure);
				
			}
			map.put("ServiceId", sId+"");
			map.put("serviceType", sType);
			listLove.add(map);
			
		}
        
		
		Mycursor.close();
		SimpleAdapter adapter = new SimpleAdapter(DTVMain.this, listLove,
				R.layout.channel_listview_content, new String[] { "num","name","image" }, new int[] {R.id.channel_list_num,R.id.channel_list_name, R.id.channel_list_image });

		listView_loveList.setAdapter(adapter);
	}
    /**
	 * 
	 * 更新当前频道号
	 * @param sid
	 */
	private void updateNowChannel(String sid){
		
		Iterator<String> it= mapHD.keySet().iterator();              
        while(it.hasNext())
        {
             String keyString=it.next();
             if(mapHD.get(keyString).equals(sid)) 
             {
            	 nowChannel=Integer.parseInt(keyString);
                 break;
             }
        }
		
	}
    
    /**
	 * 捕获指南键
	 */
	private void full_set(){
		
		t.schedule(new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				restart_timer=false;
				if(showAllView)
				{
					showAllView=false;
					Message m=new Message();
					m.what=2;
					handlerBanner.sendMessage(m);
					bannerCount=0;
					restart_timer=true;
				    isInput=false;
				}
				else
				{
				if(bannerCount==1){
					//显示banner条和广告
					Message msg=new Message();
					msg.what=1;
					handlerBanner2.sendMessage(msg);
					
				}
				else if(bannerCount==2)
				{
					//显示所有view
					Message msg=new Message();
					msg.what=2;
					handlerBanner2.sendMessage(msg);
					
					
				    bannerCount=0;
				    restart_timer=true;
				    showAllView=true;
				    isInput=false;
				    
				}
				}
			}}, 1000);
	}
	/**
	 * 显示全屏时的view
	 */
	private Handler handlerBanner2= new Handler(){
		public void handleMessage(Message msg) {
			switch(msg.what)
			{
			case 0:
				//显示广播banner条
				full_list_type.setText(getString(R.string.main_bc));
				if(isFullscreen)
				{
				isFull=true;
				linearLayoutFull.setVisibility(View.VISIBLE);
				if(isIntroduce)
				{
					isIntroduce=false;
					linearLayout_introduce1.setVisibility(View.INVISIBLE);
					linearLayout_introduce1.getBackground().setAlpha(180);
				}
				if(!isbanner)
				{
				isbanner=true;
				linearLayout_banner.setVisibility(View.VISIBLE);
				linearLayout_banner2.getBackground().setAlpha(180);
				}
				}
				break;
			case 1:
				//显示电视banner条
				full_list_type.setText(getString(R.string.main_sd));
				if(isFullscreen)
				{
				isFull=true;
				linearLayoutFull.setVisibility(View.VISIBLE);
				if(isIntroduce)
				{
					isIntroduce=false;
					linearLayout_introduce1.setVisibility(View.INVISIBLE);
					linearLayout_introduce1.getBackground().setAlpha(180);
				}
				if(!isbanner)
				{
				isbanner=true;
				linearLayout_banner.setVisibility(View.VISIBLE);
				linearLayout_banner2.getBackground().setAlpha(180);
				}
				bannerSet();
				}
				break;
			case 2:
				//显示所有view
				
				isFull=true;
				linearLayoutFull.setVisibility(View.VISIBLE);
				if(!isIntroduce)
				{
				isIntroduce=true;
				linearLayout_introduce1.setVisibility(View.VISIBLE);
				linearLayout_introduce1.getBackground().setAlpha(180);
				}
				if(!isbanner)
				{
				isbanner=true;
				linearLayout_banner.setVisibility(View.VISIBLE);
				linearLayout_banner2.getBackground().setAlpha(180);
				}
				String full_list_instroduce=JniLoad.GetShortDescriptor(ServiceId, full_introduce_eventId);		    		
			    fullIntroduce.setText(full_list_instroduce);
				
			    break;
			    
			}
		};
	};
    /**
	 * 
	 * 全屏状态下banner条的定时显示
	 */
	private void bannerSet() {
		

		banner_timer.schedule(new TimerTask() {

			@Override
			public void run() {
				
			Message m=new Message();
			m.what=3;
			handlerBanner.sendMessage(m);
			}

		}, 3000);
	}
	//指南键输入逻辑判断
	 private void setInput(){
		
		if(restart_timer)
		{
			
		 if(!isInput)
		{
			isInput=true;
			full_set();
			bannerCount++;
		}
		else
		{
			if(bannerCount<3){
				bannerCount++;
			}
		}
		}
	 }
	/**
	 * 
	 * 隐藏banner条 、节目简介等信息
	 */
	private Handler handlerBanner = new Handler(){
		
		public void handleMessage(Message msg){
			
		switch(msg.what)
		{
			case 1:
				JniLoad.PVRStop();
				pvr_state=Pvr.pvr_end;
//				pvr_notice.setVisibility(View.INVISIBLE);
				isPvring = false;
				pvr_notice.setText("");
				if(isPvr)
				{
					setPVRList();   //预录定时结束   更新预录列表
				}
				isPvrPlay = false;
				Toast.makeText(DTVMain.this, "当前节目录制结束！", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				linearLayoutFull.setVisibility(View.INVISIBLE);
				isFull=false;
				break;
			case 3:
				linearLayoutFull.setVisibility(View.INVISIBLE);
				isFull=false;
				bannerCount=0;       //计数器清零
				restart_timer=true; //定时器开关打开
			    isInput=false;
		}
		}
	};
	
	
    protected Dialog onCreateDialog(int id) {
    	
    	Dialog dialog = null;
		if(id==2){
			dialog = new AlertDialog.Builder(DTVMain.this).setTitle("标题")
		        .setMessage("该频道已经添加到喜爱频道列表中！").setPositiveButton("确定",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog,
		                            int whichButton) {
		                    	
		                        setResult(RESULT_OK);// 确定按钮事件
		                      
		                    }
		                }).create();
		}
		else if(id==3){
			dialog = new AlertDialog.Builder(DTVMain.this).setTitle("标题")
		        .setMessage("该频道已经加锁！").setPositiveButton("确定",
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog,
		                            int whichButton) {
		                    	
		                        setResult(RESULT_OK);// 确定按钮事件
		                      
		                    }
		                }).create();
		}
		else if(id==4){
			dialog = new AlertDialog.Builder(DTVMain.this).setTitle("标题")
	        .setMessage("该節目与预约列表中的节目冲突,预约失败！").setPositiveButton("确定",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,
	                            int whichButton) {
	                    	
	                        setResult(RESULT_OK);// 确定按钮事件
	                    }
	                }).create();
			
			
		}
		else if(id==5){
			dialog = new AlertDialog.Builder(DTVMain.this).setTitle("标题")
	        .setMessage("是否停止录制！").setPositiveButton("是",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,
	                            int whichButton) {
	                    	
	                        setResult(RESULT_OK);// 确定按钮事件
	                       
	                      /*  pvr_notice.setVisibility(View.INVISIBLE);
	                        pvr_state = Pvr.pvr_end;
	                        JniLoad.PVRStop();     */ 
	                        
	                        
	                    }
	                }).setNegativeButton("否", 
	                		new DialogInterface.OnClickListener() {
								
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									setResult(RESULT_CANCELED); //
									
								}
							}).create();
			
			
		}
		else if(id==6){
			AlertDialog.Builder builder = new AlertDialog.Builder(DTVMain.this);
			
			builder.setTitle("标题")
	        .setMessage("是否停止录制！").setPositiveButton("是",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,
	                            int whichButton) {
	                    	
	                        setResult(RESULT_OK);// 确定按钮事件
	                        isPvring = false;     //结束线程
	                        pvr_notice.setText("");
	                        pvr_state = Pvr.pvr_end;
	                        JniLoad.PVRStop(); 
	                        switch(isUpDown)
	                        {
	                        case 1:
	                        	isUpDown = 0;
	                        	setDown_check();
	                            break;
	                        case 2:
	                        	isUpDown = 0;
	                        	setUp_check();
	                        	break;
	                        case 3:
	                        	isUpDown = 0;
	                        	setBack_check();
	                        	break;
	                        case 4:
	                        	isUpDown = 0;
	                        	numPlay_check();
	                        	break;
	                        case 5:
	                        	isUpDown = 0;
	                        	set_listview_check();
	                        	break;
	                        case 6:
	                        	isUpDown = 0;
	                        	set_listview_check_bc();
	                        	break;
	                        case 7:
	                        	isUpDown = 0;
	                        	set_listview_period_check();
	                        	break;
	                        case 8:
	                        	isUpDown = 0;
	                        	vod();
	                        	break;
	                        case 9:
	                        	isUpDown = 0;
	                        	tvSet();
	                        	break;
	                        default:
	                        	break;
	                        }
	                        dialog.dismiss();
	                        
	                    }
	                }).setNegativeButton("否", 
	                		new DialogInterface.OnClickListener() {
								
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									setResult(RESULT_CANCELED); //
									isUpDown = 0;
									dialog.dismiss();
								}
							});
			dialog = builder.create();
			
		}
		else if(id==7){
			dialog = new AlertDialog.Builder(DTVMain.this).setTitle("标题")
	        .setMessage("该節目与预录列表中的节目冲突,预录失败！").setPositiveButton("确定",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,
	                            int whichButton) {
	                    	
	                        setResult(RESULT_OK);// 确定按钮事件
	                    }
	                }).create();
		}
		
		else if(id==8){
			dialog = new AlertDialog.Builder(DTVMain.this).setTitle("标题")
	        .setMessage("预约节目即将开始！").setPositiveButton("确定",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,
	                            int whichButton) {
	                    	
	                        setResult(RESULT_OK);// 确定按钮事件
	            		    isPeriodPlay = true;
	                        ServiceId = period_ServiceId_tempt;
	                       new Thread(new Runnable(){

							public void run() {
								// TODO Auto-generated method stub
								System.out.println("id=8 defaultPath");
								defaultPath(ServiceId);         //根据serviceid 改变path换台
								
							}}).start();
	                        updateNowChannel(ServiceId+""); //更新当前频道号
	                        changePF();                     //换台更新PF
	                       //save channel
	             		   SaveChannel sc=new SaveChannel();
	             		   sc.setChannelNum(nowChannel);
	             		   sc.setServiceId(ServiceId);
	             		   saveChannels.add(sc);
	             		   if(saveChannels.size()>2)
	             		   {
	             			   saveChannels.remove(0);
	             		   }
	                    }
	                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setResult(RESULT_CANCELED);
							
							
						}
					}).create();
		}
		else if(id==9){
			System.out.println("id=9");
			
			dialog = new AlertDialog.Builder(DTVMain.this).setTitle("标题")
	        .setMessage("预录节目时间就绪，是否开始录制？").setPositiveButton("录制",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,
	                            int whichButton) {
	                    	
	                        setResult(RESULT_OK);// 确定按钮事件
	                        if(isPvring) isPvring = false;
	                        isPvrPlay = true;
	                        if(ServiceId!=serviceId_pvr)
	                        {
	                           ServiceId = serviceId_pvr;
		                       new Thread(new Runnable(){

								public void run() {
									// TODO Auto-generated method stub
									System.out.println("id=9 defaultPath");
									defaultPath(ServiceId);         //根据serviceid 改变path换台
									
								}}).start();
		                        updateNowChannel(ServiceId+""); //更新当前频道号
		                        changePF();                     //换台更新PF
		                       //save channel
		             		   SaveChannel sc=new SaveChannel();
		             		   sc.setChannelNum(nowChannel);
		             		   sc.setServiceId(ServiceId);
		             		   saveChannels.add(sc);
		             		   if(saveChannels.size()>2)
		             		   {
		             			   saveChannels.remove(0);
		             		   }
	                        }	                        
	                        if(isPvr)
	                        {
	                        	setPVRList();     //更新预录列表
	                        }
	                        pvr_state=Pvr.pvr_doing;
	                        setPVR();            //启动节目录制  
	                    }
	                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setResult(RESULT_CANCELED);
							isPvrPlay = true;
							eventId_pvr=0;           //eventId_pvr清零
							pvr_state=0;             //录制状态
						}
					}).create();
		}
		else if(id==10){
			dialog = new AlertDialog.Builder(DTVMain.this).setTitle("标题")
			.setMessage("当前录制结束").setPositiveButton("确定", 
					new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setResult(RESULT_OK);
						}
					}).create();
		}
		else if(id==11)
		{
			dialog = new AlertDialog.Builder(DTVMain.this).setTitle("标题")
		        .setMessage("暂时还没有频道信息！按确定去搜索页面").setPositiveButton("确定",
		                new DialogInterface.OnClickListener()
		        		{
		                    public void onClick(DialogInterface dialog,
		                            int whichButton)
		                    {
		                    	
		                       setResult(RESULT_OK);// 确定按钮事件
		                       startAutoSearch();
		                       /*Intent in = new Intent(DTVMain.this,SearchChannel.class);
		                       startActivity(in);*/
		                       
		                    }
		                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								setResult(RESULT_CANCELED);
								DTVMain.this.finish();
							}
						}).create();
		}
		return dialog;
    }
    /*protected void onPrepareDialog(int id, Dialog dialog) {

    	super.onPrepareDialog(id, dialog);

    	if(id == 6){
    		((AlertDialog)dialog).setMessage("是否停止录制！");
    	}
    }*/
    
    
    /**
     * auto search
     */
    private void startAutoSearch()
    {
    	
        linearLayout_auto_notice.setVisibility(View.VISIBLE);
    	
    	JniLoad.Init();				
		
		JniLoad.Config(1, 275000 , 0, 6875, 3);
		
		JniLoad.Start();
		isGetTuner = true;
		
    	new Thread(new Runnable()
    	{

			public void run()
			{
				TunerParam tuner = null;
				while(isGetTuner)
				{
					try
					{
						tuner = JniLoad.GetTunerAttribute();
						ber.setText(tuner.getBer()+"");
						signal_quality.setText(tuner.getSignal_quality()+"");
						signal_strength.setText(tuner.getSignal_strength()+"");
						Thread.sleep(500);
					}
					catch(Exception e)
					{
						continue;
					}
				}
				
			}}).start();
	   
    }
    
    /**
	 * 捕获home键
	 */
	/*public void onAttachedToWindow() 
	{
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		super.onAttachedToWindow();
	};*/
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
		Log.i(TAG, keyCode + "");
    	//home 
    	/*if(keyCode==KeyEvent.KEYCODE_HOME){
    		
    		Intent intent = new Intent(Intent.ACTION_MAIN);   
            intent.addCategory(Intent.CATEGORY_HOME);   
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
            startActivity(intent);   
            this.finish();
		    return true;
		}*/
    	//退出键
    	if(keyCode == KeyEvent.KEYCODE_Z){
    		
    		if(isFullscreen)
    		{
    			if(isMenu) 
                 {
                 	isMenu = false;
                 	linearLayout_menu.setVisibility(View.INVISIBLE);
                 }
    			 
    		}
    		else if(isChannelList)
    		{
    			isChannelList=false;
    			linearLayout_channelList.setVisibility(View.INVISIBLE);
    			isFullscreen = true;
    			
    		}
    		else if(isEPG)
    		{
    			if(isEPGIntroduce)
    			{
    				isEPGIntroduce=false;
    				linearLayout_epg_introduce.setVisibility(View.INVISIBLE);
    			}
    			isEPG=false;
    			linearLayout_epg.setVisibility(View.INVISIBLE);
    			
    			isFullscreen=true;
    			//surfaceView 全屏设置
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		else if(isEdit)
    		{
    			isEdit=false;
    			linearLayout_edit.setVisibility(View.INVISIBLE);
    			
    			isFullscreen=true;
    			//surfaceView 全屏设置
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		else if(isReveration)
    		{
    			isReveration=false;
    			linearLayout_reveration.setVisibility(View.INVISIBLE);
    			
    			isFullscreen=true;
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		else if(isPvr)
    		{
    			isPvr=false;
    			linearLayout_pvr.setVisibility(View.INVISIBLE);
    			
    			isFullscreen=true;
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		
    		if(pvr_state == Pvr.pvr_doing)
			{
				pvr_notice.setVisibility(View.VISIBLE);
			}
    		
    		return true;
    	}
    	if(keyCode == KeyEvent.KEYCODE_BACK)
    	{
    		
    		if(isFullscreen)
    		{
             if(isMenu) 
             {
                	isMenu = false;
                	linearLayout_menu.setVisibility(View.INVISIBLE);
             }
             else
             {
                if(pvr_state == Pvr.pvr_doing)
       			{
       				isUpDown = 3;
                	showDialog(6);
       			}
                else 
                {
                   setBack_check();
                }
             }
    		}
    		else if(isEPG)
    		{
    			if(pvr_state == Pvr.pvr_doing)
    			{
    				pvr_notice.setVisibility(View.VISIBLE);
    			}
    			if(isEPGIntroduce)
    			{
    				isEPGIntroduce=false;
    				linearLayout_epg_introduce.setVisibility(View.INVISIBLE);
    			}
    			isEPG=false;
    			linearLayout_epg.setVisibility(View.INVISIBLE);
    			isFullscreen=true;
//    			isMenu = true;
//    			linearLayout_menu.setVisibility(View.VISIBLE);
    			
    			isFull=false;
				linearLayoutFull.setVisibility(View.INVISIBLE);
    			
    			//surfaceView 全屏设置
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		else if(isEdit)
    		{
    			if(pvr_state == Pvr.pvr_doing)
    			{
    				pvr_notice.setVisibility(View.VISIBLE);
    			}
    			isEdit=false;
    			linearLayout_edit.setVisibility(View.INVISIBLE);
    			isFullscreen=true;
//    			isMenu = true;
//    			linearLayout_menu.setVisibility(View.VISIBLE);
    			
    			//surfaceView 全屏设置
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		else if(isReveration)
    		{
    			if(pvr_state == Pvr.pvr_doing)
    			{
    				pvr_notice.setVisibility(View.VISIBLE);
    			}
    			isReveration=false;
    			linearLayout_reveration.setVisibility(View.INVISIBLE);
    			isFullscreen=true;
//    			isMenu = true;
//    			linearLayout_menu.setVisibility(View.VISIBLE);
    			
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		else if(isPvr)
    		{
    			if(pvr_state == Pvr.pvr_doing)
    			{
    				pvr_notice.setVisibility(View.VISIBLE);
    			}
    			
    			isPvr=false;
    			linearLayout_pvr.setVisibility(View.INVISIBLE);
    			isFullscreen=true;
//    			isMenu = true;
//    			linearLayout_menu.setVisibility(View.VISIBLE);
    			
    			JniPlayer.setWindowSize(0, 0, 0, 0);
    		}
    		else if(isChannelList)
    		{
    			if(pvr_state == Pvr.pvr_doing)
    			{
    				pvr_notice.setVisibility(View.VISIBLE);
    			}
    			isChannelList=false;
    			linearLayout_channelList.setVisibility(View.INVISIBLE);
    			isFullscreen= true;
//    			isMenu = true;
//    			linearLayout_menu.setVisibility(View.VISIBLE);
    			
    		}
    	
    		return true;
    	}
    	//电视 /广播 列表
    	if(keyCode == KeyEvent.KEYCODE_J)
    	{
    		if(isChannelList)
    		{
    			if(isLoveList)
    			{
    				list_type.setText(getString(R.string.main_channel_list));
    				isLoveList=false;
    				listView_loveList.setVisibility(View.INVISIBLE);
    				isTVList=true;
					listView_channelList.setVisibility(View.VISIBLE);
    			}
    			else
    			{
    			if(isTVList)
    			{
    				list_type.setText(getString(R.string.main_bc_list));
    				isTVList=false;
    				listView_channelList.setVisibility(View.INVISIBLE);
    				isBCList=true;
    				listView_bcList.setVisibility(View.VISIBLE);
    			}
    			else if(isBCList)
    			{
    				list_type.setText(getString(R.string.main_channel_list));
    				isBCList=false;
    				listView_bcList.setVisibility(View.INVISIBLE);
    				isTVList=true;
    				listView_channelList.setVisibility(View.VISIBLE);
    			}
    			}
    		}
    		
    		return true;
    	}
    	//喜爱列表
    	if(keyCode == KeyEvent.KEYCODE_V)
    	{
    		if(isChannelList)
    		{
    			if(!isLoveList)
    			{
    				if(isTVList)
    				{
    					isTVList=false;
    					listView_channelList.setVisibility(View.INVISIBLE);
    				}
    				else if(isBCList)
    				{
    					isBCList=false;
    					listView_bcList.setVisibility(View.INVISIBLE);
    				}
    				list_type.setText(getString(R.string.main_love_list));
    				isLoveList=true;
    				listView_loveList.setVisibility(View.VISIBLE);
    			}
    		}
    		return true;
    	}
    	//媒体音量调节
    	if(keyCode ==KeyEvent.KEYCODE_VOLUME_UP){
    		if(!isMediaVolume)
			media_auto_layout.setVisibility(View.VISIBLE);
			handler_volume.removeMessages(msg_volume);
  		    handler_volume.sendMessageDelayed(handler_volume.obtainMessage(msg_volume), 5000);
			if(bar2current<bar2max){
			bar2current++;
			am.setStreamVolume(AudioManager.STREAM_MUSIC, bar2current, 0);
			main_bar.setProgress(bar2current);
			media_level.setText(bar2current+"");
			}
			return true;
		}
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
			if(!isMediaVolume)
			media_auto_layout.setVisibility(View.VISIBLE);
			handler_volume.removeMessages(msg_volume);
  		    handler_volume.sendMessageDelayed(handler_volume.obtainMessage(msg_volume), 5000);
			if(bar2current>0){
			bar2current--;
			am.setStreamVolume(AudioManager.STREAM_MUSIC, bar2current, 0);
			main_bar.setProgress(bar2current);
			media_level.setText(bar2current+"");
			}
			return true;
		}
    	
    	//所有列表上下键  循环
    	if(keyCode == KeyEvent.KEYCODE_DPAD_UP)
    	{
    		if(isMenu)
    		{
    		if(menu_listview.getSelectedItemPosition() == 0)
    			menu_listview.setSelection(6);
    		}
    		else if(isChannelList)
    		{
    			if(isTVList)
    			{
    				if(listView_channelList.getSelectedItemPosition() == 0)
    					listView_channelList.setSelection(listTV.size()-1);
    			}
    			else if(isBCList)
    			{
    				if(listView_bcList.getSelectedItemPosition() == 0)
    					listView_bcList.setSelection(listBC.size()-1);
    			}
    			else if(isLoveList)
    			{
    				if(listView_loveList.getSelectedItemPosition() == 0)
    					listView_loveList.setSelection(listLove.size()-1);
    			}
    		}
    		else if(isEdit)
    		{
    			if(isVideo)
    			{
    			   if(edit_video_listView.getSelectedItemPosition() == 0)
    				   edit_video_listView.setSelection(listEditTV.size()-1);
    			}
    			else if(isBC)
    			{
    			    if(edit_bc_listView.getSelectedItemPosition() == 0)
    			    	edit_bc_listView.setSelection(listEditBC.size()-1);
    			}
    			else if(isLove)
    			{
    				if(edit_love_listView.getSelectedItemPosition() == 0)
    					edit_love_listView.setSelection(listEditLove.size()-1);
    			}
    		}
    		else if(isEPG)
    		{
    			if(listview_period_channel.hasFocus())
    			{
    				if(listview_period_channel.getSelectedItemPosition() == 0)
    					listview_period_channel.setSelection(listPeriodTV.size()-1);
    			}
    			else if(listview_period_epg.hasFocus())
    			{
    				if(listview_period_epg.getSelectedItemPosition() == 0)
    					listview_period_epg.setSelection(listEPG.size()-1);
    			}
    		}
    		else if(isReveration)
    		{
    			if(listView_reveration.getSelectedItemPosition() == 0)
    				listView_reveration.setSelection(listReveration.size()-1);
    		}
    		else if(isPvr)
    		{
    			if(pvr_listView.getSelectedItemPosition() == 0)
    				pvr_listView.setSelection(listPvr.size()-1);
    		}
    		return true;
    	}
    	if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN)
    	{
    		
    		if(isMenu)
    		{
    		if(menu_listview.getSelectedItemPosition()==6)
    			menu_listview.setSelection(0);
    		}
    		
    		else if(isChannelList)
    		{
    			if(isTVList)
    			{
    				if(listView_channelList.getSelectedItemPosition() == listTV.size()-1)
    					listView_channelList.setSelection(0);
    					
    			}
    			else if(isBCList)
    			{
    				if(listView_bcList.getSelectedItemPosition() == listBC.size()-1)
    				{
    					listView_bcList.setSelection(0);
    				}
    			}
    			else if(isLoveList)
    			{
    				if(listView_loveList.getSelectedItemPosition() == listLove.size()-1)
    				{
    					listView_loveList.setSelection(0);
    				}
    			}
    		}
    		
    		else if(isEdit)
    		{
    			if(isVideo)
    			{
    			   if(edit_video_listView.getSelectedItemPosition() == listEditTV.size()-1)
    				   edit_video_listView.setSelection(0);
    			}
    			else if(isBC)
    			{
    			    if(edit_bc_listView.getSelectedItemPosition() == listEditBC.size()-1)
    			    	edit_bc_listView.setSelection(0);
    			}
    			else if(isLove)
    			{
    				if(edit_love_listView.getSelectedItemPosition() == listEditLove.size()-1)
    					edit_love_listView.setSelection(0);
    			}
    		}
    		
    		else if(isEPG)
    		{
    			if(listview_period_channel.hasFocus())
    			{
    				if(listview_period_channel.getSelectedItemPosition() == listPeriodTV.size()-1)
    					listview_period_channel.setSelection(0);
    			}
    			else if(listview_period_epg.hasFocus())
    			{
    				if(listview_period_epg.getSelectedItemPosition() == listEPG.size()-1)
    					listview_period_epg.setSelection(0);
    			}
    		}
    		else if(isReveration)
    		{
    			if(listView_reveration.getSelectedItemPosition() == listReveration.size()-1)
    				listView_reveration.setSelection(0);
    		}
    		else if(isPvr)
    		{
    			if(pvr_listView.getSelectedItemPosition() == listPvr.size()-1)
    				pvr_listView.setSelection(0);
    		}
    		return true;
    	}
    	//节目简介  binner条   广告  信息鍵
		if(keyCode==KeyEvent.KEYCODE_O){
			if(isFullscreen)
			{
		        if(ServiceType ==2)
		        {
		        	return true;
		        }
		        else if(ServiceType == 1)
		        {
		        	if(View.VISIBLE == linearLayoutFull.getVisibility())
		        	{
		        		linearLayoutFull.setVisibility(View.INVISIBLE);
		        	}
		        	else
		        	{
		        	//显示电视banner条
		    			full_list_type.setText(getString(R.string.main_sd));
		    			if(isFullscreen)
		    			{
		    			isFull=true;
		    			linearLayoutFull.setVisibility(View.VISIBLE);
		    			if(isIntroduce)
		    			{
		    				isIntroduce=false;
		    				linearLayout_introduce1.setVisibility(View.INVISIBLE);
		    				linearLayout_introduce1.getBackground().setAlpha(180);
		    			}
		    			if(!isbanner)
		    			{
		    			isbanner=true;
		    			linearLayout_banner.setVisibility(View.VISIBLE);
		    			linearLayout_banner2.getBackground().setAlpha(180);
		    			}
		    			}
		    			handlerPF.removeMessages(pf_close);
		    			handlerPF.sendMessageDelayed(handlerPF.obtainMessage(pf_close), 3000);
		    			
		        	}   
		    		
		        }
//				setInput();
		        
			}
			else if(isEPG && listview_period_epg.hasFocus())
			{
				Map<String, Object> mp = (Map<String, Object>)listview_period_epg.getItemAtPosition(listview_period_epg.getSelectedItemPosition());   
	            int eventId=(Integer)mp.get("eventId");
	            String instroduce_tempt=JniLoad.GetShortDescriptor(ServiceId, eventId);		
	            epg_introduce.setText(instroduce_tempt);
	            if(!isEPGIntroduce)
	            {
	            isEPGIntroduce=true;
	            linearLayout_epg_introduce.setVisibility(View.VISIBLE);
	            }
	            handler_epg_intruduce.removeMessages(msg_epg_introduce);
	            handler_epg_intruduce.sendMessageDelayed(handler_epg_intruduce.obtainMessage(msg_epg_introduce), 5000);
  		    	
	            
			}
			return true;
     	}
		/*//蓝色键系统设置
		if(keyCode == KeyEvent.KEYCODE_H)
		{
			Intent in = new Intent(DTVMain.this,SettingActivity.class);
			
			in.putExtra("serviceId", ServiceId+"");
			startActivity(in);
		}*/
		//黄色键
		if(keyCode == KeyEvent.KEYCODE_G)
		{
			if(isEdit && isLove && edit_love_listView.hasFocus())
			{
				//删除单个喜爱
				Map<String, Object> mp = (Map<String, Object>)edit_love_listView.getItemAtPosition(edit_love_listView.getSelectedItemPosition());   
				int edit_serviceId=Integer.parseInt(mp.get("ServiceId").toString());
				
				String sql = "serviceId = ?";
				getContentResolver().delete(LOVE_TB_URI, sql, new String[]{edit_serviceId+""});
	    	   
	    		listEditLove.remove(edit_love_listView.getSelectedItemPosition());//删除频道
	    	    
	    		adapter.notifyDataSetChanged();          //刷新频道列表
				loadEditTV();           //重新加载editTV
				loadEditBC();
			}
			else if(isEdit && isVideo && edit_video_listView.hasFocus())
			{
				//tv加锁
                Map<String, Object> mp = (Map<String, Object>)edit_video_listView.getItemAtPosition(edit_video_listView.getSelectedItemPosition());   
	            
	            String edit_ServiceId=(String)mp.get("ServiceId");
				String sql = "serviceId = ?";
	            Cursor Mycursor1 = getContentResolver().query(DTVMain.LOCK_TB_URI, null,
	        			sql,new String[]{edit_ServiceId}, null);
				if(Mycursor1.moveToNext())
				{
					Mycursor1.close();
					return true;
					
				}
				Mycursor1.close();
				
				ContentValues values = new ContentValues();      
				values.put("serviceId", edit_ServiceId);        
				getContentResolver().insert(LOCK_TB_URI, values);
				loadEditLove();
				
				mp.remove("lockImage");
				mp.put("lockImage", R.drawable.ic_secure);
				adapter_edit_video.notifyDataSetChanged();
				
			}
			
			else if(isEdit && isBC && edit_bc_listView.hasFocus())
			{
				//bc加锁
                Map<String, Object> mp = (Map<String, Object>)edit_bc_listView.getItemAtPosition(edit_bc_listView.getSelectedItemPosition());   
	            
	            String edit_ServiceId=(String)mp.get("ServiceId");
				String sql = "serviceId = ?";
	            Cursor Mycursor1 = getContentResolver().query(DTVMain.LOCK_TB_URI, null,
	        			sql,new String[]{edit_ServiceId}, null);
				if(Mycursor1.moveToNext())
				{
					Mycursor1.close();
					return true;
				}
				Mycursor1.close();
				
				ContentValues values = new ContentValues();      
				values.put("serviceId", edit_ServiceId);        
				getContentResolver().insert(LOCK_TB_URI, values);
				
				
				loadEditLove();
				mp.remove("lockImage");
				mp.put("lockImage", R.drawable.ic_secure);
				adapter_edit_bc.notifyDataSetChanged();
			}
			else if(isReveration && listView_reveration.hasFocus())
			{
				
	    		final TextView t=(TextView)listView_reveration.getSelectedView().findViewById(R.id.reveration_num);
				String temp=t.getText().toString();
				EventBean et=map2.get(temp);
				JniLoad.DelOrder(et.getSerId(), et.getEventId());	                 //删除预约
				//删除单个预约
				listReveration.remove(listView_reveration.getSelectedItemPosition());//删除频道
				adapter_reveration.notifyDataSetChanged();                           //刷新频道列表
			}
			else if(isPvr && pvr_listView.hasFocus())
			{
				final TextView t=(TextView)pvr_listView.getSelectedView().findViewById(R.id.pvr_num);
				String temp=t.getText().toString();
				EventBean et=map_pvr.get(temp);
				JniLoad.PVRDel(et.getSerId(), et.getEventId());        //删除预录
				listPvr.remove(pvr_listView.getSelectedItemPosition());//删除频道
				adapter_pvr.notifyDataSetChanged();                    //刷新频道列表
			}
			else if(isEPG){
				
				if(listEPG!=null)
				listEPG.clear();
				if(n>5)
	    		    n=0;
	    		else 
	    			n++;
	    		time_epg.setText(getPeriodDate(n));       //设置节目时间表的日期
	            getSchedule(ServiceId,day+n);
	            //JniLoad.SetFreqset(freq_Period, 6875, 3);
	     		JniLoad.ConfigEPG(freqNum_Period, 256);   //1 表示PF 256表示Schedule
	     		JniLoad.StartEPG();
			}
			return true;
		}
		//红色键 
		if(keyCode==KeyEvent.KEYCODE_E)
		{
			
			if(isFullscreen)
			{
			  if(!isNoData)return true;
			  if(isMenu)
			   {
					
				isMenu=false;
				linearLayout_menu.setVisibility(View.INVISIBLE);
				
			   }
			   else
			   {
				 System.out.println("red key isMenu="+isMenu);
				isMenu=true;
				linearLayout_menu = (LinearLayout)findViewById(R.id.linearLayout_menu_item);
				linearLayout_menu.setVisibility(View.VISIBLE);
				linearLayout_menu.getBackground().setAlpha(180);
				menu_listview.setFocusable(true);
			   }
			}
			else if(isEPG && listview_period_epg.hasFocus())
			{
			    boolean	sdCardExist = Environment.getExternalStorageState()   
		        .equals(android.os.Environment.MEDIA_MOUNTED); 
				if(sdCardExist)
				{
				//添加预录
				Map<String, Object> mp = (Map<String, Object>)listview_period_epg.getItemAtPosition(listview_period_epg.getSelectedItemPosition());                 
                int eventId=(Integer)mp.get("eventId");
                int i=JniLoad.PVRAdd(ServiceId, eventId, 1);
                int j=JniLoad.PVRSave();
                System.out.println("i="+i+",j="+j);
    			if(i==0 && j==0)
    			{
    				final ImageView image=(ImageView)listview_period_epg.getSelectedView().findViewById(R.id.period_list_pvrPic);
    				image.setImageResource(R.drawable.jog_tab_target_red);
    				//this.showDialog(6);
    			}
    			else if(i == 4 && j==0)
    			{
                     Toast.makeText(DTVMain.this, "预录无效！", Toast.LENGTH_SHORT).show();
    			}
    			else if(i==1 && j==0)
    			{
    				//时间冲突
    				DTVMain.this.showDialog(7);
    				
    			}
				}
				else
				{
					Toast.makeText(DTVMain.this, "没有发现存储设备！", Toast.LENGTH_SHORT).show();
				}
			}
			else if(isEdit && isVideo && edit_video_listView.hasFocus())
			{
				
				//添加喜爱tv
				final TextView tName = (TextView) edit_video_listView.getSelectedView().findViewById(R.id.edit_video_name);
				final TextView tNum = (TextView) edit_video_listView.getSelectedView().findViewById(R.id.edit_vedeo_num);
				String logicNum = tNum.getText().toString().trim();
				String name = tName.getText().toString().trim();
				Map<String, Object> mp = (Map<String, Object>)edit_video_listView.getItemAtPosition(edit_video_listView.getSelectedItemPosition());   
	            
	            String edit_ServiceId=(String)mp.get("ServiceId");
				
	            String sql1 = "loveName = ?";
	            Cursor Mycursor1 = getContentResolver().query(LOVE_TB_URI, null, sql1, new String[]{name}, null);
				//如果该频道已经存在喜爱频道中 给用户提示
				if(Mycursor1.moveToNext())
				{
					Mycursor1.close();
					
				    this.showDialog(2);
					return true;
					
				}
				Mycursor1.close();
				ContentValues values = new ContentValues();
				values.put("logicNum", logicNum);
				values.put("loveName", name);
				values.put("serviceId", edit_ServiceId);
				values.put("serviceType", 1);
				getContentResolver().insert(LOVE_TB_URI, values);
//				
				loadEditLove();         //重新加载editLove
				//更新video_edit
				mp.remove("loveImage");
				mp.put("loveImage", R.drawable.rate_star_small_on);
			    adapter_edit_video.notifyDataSetChanged();
			}
			else if(isEdit && isBC && edit_bc_listView.hasFocus())
			{
				//添加喜爱bc
				final TextView tNum = (TextView) edit_bc_listView.getSelectedView().findViewById(R.id.edit_bc_num);
				final TextView tName = (TextView) edit_bc_listView.getSelectedView().findViewById(R.id.edit_bc_name);
				String logicNum = tNum.getText().toString().trim();
				String name = tName.getText().toString().trim();
				Map<String, Object> mp = (Map<String, Object>)edit_bc_listView.getItemAtPosition(edit_bc_listView.getSelectedItemPosition());   
	            
	            String edit_ServiceId=(String)mp.get("ServiceId");
				
	            String sql1 = "loveName = ?";
	            Cursor Mycursor1 = getContentResolver().query(LOVE_TB_URI, null, sql1, new String[]{name}, null);
				//如果该频道已经存在喜爱频道中 给用户提示
				if(Mycursor1.moveToNext())
				{
					Mycursor1.close();
					
				    this.showDialog(2);
					return true;
					
				}
				Mycursor1.close();
				ContentValues values = new ContentValues();
				values.put("logicNum", logicNum);
				values.put("loveName", name);
				values.put("serviceId", edit_ServiceId);
				values.put("serviceType", 2);
				getContentResolver().insert(LOVE_TB_URI, values);
				
				loadEditLove();         //重新加载editLove
				//更新bc_edit
				mp.remove("loveImage");
				mp.put("loveImage", R.drawable.rate_star_small_on);
				adapter_edit_bc.notifyDataSetChanged();
				
			}
			else if(isEdit && isLove && edit_love_listView.hasFocus())
			{
				//删除所有喜爱频道
				
				getContentResolver().delete(LOVE_TB_URI, null, null);
				
	    		listEditLove.clear();//删除频道
	    	    
	    		adapter.notifyDataSetChanged();          //刷新频道列表
				loadEditTV();           //重新加载editTV
				loadEditBC();  
			}
			else if(isReveration)
			{
				for(int i=1;i<=map2.size();i++)
				{
					try{
					int sid=map2.get(i+"").getSerId();
					int eventid=map2.get(i+"").getEventId();
					JniLoad.DelOrder(sid, eventid);	//删除预约
					}catch(Exception e)
					{
						continue;
					}
				}
				//删除所有预约
				listReveration.clear();//删除频道
				adapter_reveration.notifyDataSetChanged();          //刷新频道列表
			}
			else if(isPvr)
			{
				for(int i=1;i<=map_pvr.size();i++)
				{
					try{
					int sid=map_pvr.get(i+"").getSerId();
					int eventid=map_pvr.get(i+"").getEventId();
					JniLoad.PVRDel(sid, eventid); //删除预录
					}catch(Exception e)
					{
						continue;
					}
				}
				//删除所有预录
				listPvr.clear();//删除频道
				adapter_pvr.notifyDataSetChanged();          //刷新频道列表
			}
			return true;
		}
		//绿色按键切换日期
		if(keyCode==KeyEvent.KEYCODE_F)
		{
			if(isEPG){
				
				if(listEPG!=null)
				listEPG.clear();
				if(n==0)
	    		    n=5;
	    		else 
	    			n--;
	    		time_epg.setText(getPeriodDate(n));    //设置节目时间表的日期
	            getSchedule(ServiceId,day+n);
	            //JniLoad.SetFreqset(freq_Period, 6875, 3);
	     		JniLoad.ConfigEPG(freqNum_Period, 256);   //1 表示PF 256表示Schedule
	     		JniLoad.StartEPG();
			}
			else if(isEdit)
        	{
        		if(isVideo)
        		{
        			if(listEditBC.size()!=0)
        			{
        				edit_video_txt.setBackgroundColor(0);
        				edit_bc_txt.setBackgroundResource(R.drawable.channel_edit_menu_checked);
        				isVideo=false;
        				edit_video_listView.setVisibility(View.INVISIBLE);
        				isBC=true;
        				edit_bc_listView.setVisibility(View.VISIBLE);
        				edit_love_title.setVisibility(View.VISIBLE);
        			}
        			else
        			{
        				if(listEditLove.size()!=0)
        				{
        					edit_video_txt.setBackgroundColor(0);
            				edit_love_txt.setBackgroundResource(R.drawable.channel_edit_menu_checked);
            				isVideo=false;
            				edit_video_listView.setVisibility(View.INVISIBLE);
            				isLove=true;
            				edit_love_listView.setVisibility(View.VISIBLE);
            				edit_video_bottom.setVisibility(View.INVISIBLE);
            				edit_love_bottom.setVisibility(View.VISIBLE);
            				
            				edit_love_title.setVisibility(View.INVISIBLE);
        				}
        				
        			}
        		}
        		else if(isBC)
        		{
        			if(listEditLove.size()!=0)
        			{
        				edit_bc_txt.setBackgroundColor(0);
        				edit_love_txt.setBackgroundResource(R.drawable.channel_edit_menu_checked);
        				isBC=false;
        				edit_bc_listView.setVisibility(View.INVISIBLE);
        				isLove=true;
        				edit_love_listView.setVisibility(View.VISIBLE);
        				
        				edit_video_bottom.setVisibility(View.INVISIBLE);
        				edit_love_bottom.setVisibility(View.VISIBLE);
        				edit_love_title.setVisibility(View.INVISIBLE);
        			}
        			else
        			{
        				if(listEditTV.size()!=0)
        				{
        					edit_bc_txt.setBackgroundColor(0);
            				edit_video_txt.setBackgroundResource(R.drawable.channel_edit_menu_checked);
            				isBC=false;
            				edit_bc_listView.setVisibility(View.INVISIBLE);
            				isVideo=true;
            				edit_video_listView.setVisibility(View.VISIBLE);
            				edit_love_title.setVisibility(View.VISIBLE);
        				}
        			}
        		}
        		else if(isLove)
        		{
        			if(listEditTV.size()!=0)
        			{
        				edit_love_txt.setBackgroundColor(0);
        				edit_video_txt.setBackgroundResource(R.drawable.channel_edit_menu_checked);
        				isLove=false;
        				edit_love_listView.setVisibility(View.INVISIBLE);
        				isVideo=true;
        				edit_video_listView.setVisibility(View.VISIBLE);
        				
        				edit_video_bottom.setVisibility(View.VISIBLE);
        				edit_love_bottom.setVisibility(View.INVISIBLE);
        				edit_love_title.setVisibility(View.VISIBLE);
        				
        			}
        			else
        			{
        				if(listEditBC.size()!=0)
        				{
        					edit_love_txt.setBackgroundColor(0);
            				edit_bc_txt.setBackgroundResource(R.drawable.channel_edit_menu_checked);
            				isLove=false;
            				edit_love_listView.setVisibility(View.INVISIBLE);
            				isBC=true;
            				edit_bc_listView.setVisibility(View.VISIBLE);
            				edit_love_title.setVisibility(View.VISIBLE);
        				}
        			}
        		}
        	}
			
			else if(isPvr)
			{
				//停止录制
				if(pvr_state == Pvr.pvr_doing)
				{
				   JniLoad.PVRStop();
				   pvr_state=Pvr.pvr_end;
				   setPVRList();
				}
			}
			return true;
		}
        if(keyCode==KeyEvent.KEYCODE_0 && isFullscreen){
			
			numberSet(0);
			return true;
		}
		if(keyCode==KeyEvent.KEYCODE_1 && isFullscreen){
			numberSet(1);
    		return true;
		}
		if(keyCode==KeyEvent.KEYCODE_2 && isFullscreen){
			numberSet(2);
    		return true;
		}
		if(keyCode==KeyEvent.KEYCODE_3 && isFullscreen){
			numberSet(3);
			return true;	
		}
		if(keyCode==KeyEvent.KEYCODE_4 && isFullscreen){
			numberSet(4);
			return true;
			
		}
		if(keyCode==KeyEvent.KEYCODE_5 && isFullscreen){
			numberSet(5);
			return true;
		}
		if(keyCode==KeyEvent.KEYCODE_6 && isFullscreen){
			numberSet(6);
			return true;
		}
		if(keyCode==KeyEvent.KEYCODE_7 && isFullscreen){
			numberSet(7);
			return true;
		}
		if(keyCode==KeyEvent.KEYCODE_8 && isFullscreen){
			numberSet(8);
			return true;
		}
		if(keyCode==KeyEvent.KEYCODE_9 && isFullscreen){
			numberSet(9);
			return true;
		}
    	
		//频道-
		if(keyCode==KeyEvent.KEYCODE_C && isFullscreen){
			
			if(View.VISIBLE == linearLayoutFull.getVisibility())
        	{
        		linearLayoutFull.setVisibility(View.INVISIBLE);
        	}
			 if(pvr_state == Pvr.pvr_doing)
			 {
			    isUpDown =1;	
				showDialog(6);
			 }
			 else 
			 {
				 setDown_check();
			 }
			
				return true;
		}
		//频道+
		if(keyCode==KeyEvent.KEYCODE_D && isFullscreen){
			
			if(View.VISIBLE == linearLayoutFull.getVisibility())
        	{
        		linearLayoutFull.setVisibility(View.INVISIBLE);
        	}
			if(pvr_state == Pvr.pvr_doing)
			{
				isUpDown = 2;
				showDialog(6);
			}
			else
			{
			     setUp_check();
			}
		        return true;		
		}
    	return super.onKeyDown(keyCode, event);

    }
    
    private void setUp_check()
    {
    	if(nowChannel==mapHD.size())
		{
			nowChannel=1;
		}
		else
		{
			nowChannel++;
		}
		    String ChannelNumTempt=nowChannel+"";
		if(ChannelNumTempt.length()==1)
		{
			ChannelNumTempt = "00"+ChannelNumTempt;
		}
		else if(ChannelNumTempt.length()==2)
		{
			ChannelNumTempt = "0"+ChannelNumTempt;
		}
			channelN.setText("频道"+ChannelNumTempt);
			
			send(ChannelNumTempt);
			
			handlerCloseView.removeMessages(msg_close);
	    	handlerCloseView.sendMessageDelayed(handlerCloseView.obtainMessage(msg_close), 5000);
	    	
	    	handlerChannelPlayer.removeMessages(msg_channel_play);
	    	handlerChannelPlayer.sendMessageDelayed(handlerChannelPlayer.obtainMessage(msg_channel_play), 1000);
	
    }
    
    private void setDown_check()
    {
    	if(nowChannel==0)
		{
			nowChannel=mapHD.size();
		}
		else
		{
			nowChannel--;
		}
		if(nowChannel!=0)
		{
		
			String ChannelNumTempt=nowChannel+"";
			if(ChannelNumTempt.length()==1)
			{
				ChannelNumTempt = "00"+ChannelNumTempt;
			}
			else if(ChannelNumTempt.length()==2)
			{
				ChannelNumTempt = "0"+ChannelNumTempt;
			}
			
			channelN.setText("频道"+ChannelNumTempt);
			send(ChannelNumTempt);
			handlerCloseView.removeMessages(msg_close);
	    	handlerCloseView.sendMessageDelayed(handlerCloseView.obtainMessage(msg_close), 3000);
	    	
	    	handlerChannelPlayer.removeMessages(msg_channel_play);
	    	handlerChannelPlayer.sendMessageDelayed(handlerChannelPlayer.obtainMessage(msg_channel_play), 1000);
		}
    }
  //返回键换台时判断是否在录制
    private void setBack_check()
    {
    	   int count = saveChannels.size();
           if(count==2)
           {
           int sidOne = saveChannels.get(0).getServiceId();
           int sidTwo = saveChannels.get(1).getServiceId();
           if(ServiceId == sidTwo)
           {
         	  nowChannel = saveChannels.get(0).getChannelNum(); //更新频道号
         	  String tempt=nowChannel+"";
         	  isSaveState = "one";
         	  
         	  savechannelPlay(tempt);
           }
           else if(ServiceId == sidOne)
           {
         	  nowChannel = saveChannels.get(1).getChannelNum();
         	  String tempt = nowChannel+"";
         	  isSaveState = "two";
         	 
         	  savechannelPlay(tempt);
           }
          }
    }
    //发送频道号到前面板
    public void send(String msg) {
    	  LocalSocketAddress l;
    	  LocalSocket mSocket = new LocalSocket();
    	  l = new LocalSocketAddress("mysocket", // mysocket为socket服务端的名称
    	    LocalSocketAddress.Namespace.RESERVED);
    	  try {
    	   mSocket.connect(l);// 连接本地socket
    	   BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
    	   mSocket.getOutputStream()));
    	   writer.write("~C" +msg+ "\n");
    	   writer.flush();
    	  } catch (Exception e) {
    	  }
    	 }
    /**
     * save channel play msg
     */
    private void savechannelPlay(String numTempt)
    {
    	   
	    	if(numTempt.length()==1)
	    	{
	    		numTempt="00"+numTempt;
	    	}
	    	else if(numTempt.length()==2)
	    	{
	    		numTempt="0"+numTempt;
	    	}
			
	    	send(numTempt);
	    	channelN.setText("频道 "+numTempt);
	    	handlerCloseView.removeMessages(msg_close);
	    	handlerCloseView.sendMessageDelayed(handlerCloseView.obtainMessage(msg_close), 5000);
	    	handlerSavePlay.removeMessages(msg_save_play);
	    	handlerSavePlay.sendMessageDelayed(handlerSavePlay.obtainMessage(msg_save_play), 1000);
    }
    /**
     * channel+  channel- 
     */
    private void channelUpandDown()
    {
    	String sql = "_id = ?";
    	Cursor c = getContentResolver().query(DTVMain.CHANNEL_INFO_TB_URI, new String[]{"MyServiceId","MyServiceType"}, sql, new String[]{nowChannel+""}, null);
    	if(c.moveToFirst())
    	{	
			ServiceId = c.getInt(c.getColumnIndex("MyServiceId"));
			channel_type_tempt = c.getInt(c.getColumnIndex("MyServiceType"));
			
			new Thread(new Runnable(){
				
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("UpAndDown defaultPath");
					defaultPath(ServiceId);
					
				}}).start();
			
			changePF();
            if(channel_type_tempt == 1)
            {
                period_position = map_channelList.get(ServiceId);	
            }
            else if(channel_type_tempt == 2)
            {
            	period_position = map_bcList.get(ServiceId);
            }
			
            SaveChannel sc=new SaveChannel();
            sc.setChannelNum(nowChannel);
            sc.setServiceId(ServiceId);
            saveChannels.add(sc);
            if(saveChannels.size()>2)
            {
               saveChannels.remove(0);
            }
            
		}
    	c.close();
    }
    
    
    /**
     * save channel play
     */
    private void savePlay(){
    	System.out.println("serviceType = 1"+map_channelList.get(ServiceId));
    	if(isSaveState.equals("two"))
    	{
    		ServiceId = saveChannels.get(1).getServiceId();
    		set_timer_changePF();                               //延时器
    		new Thread(new Runnable(){

				public void run() {
					// TODO Auto-generated method stub
					System.out.println("saveTwo defaultPath");
					defaultPath(ServiceId);
					
				}}).start();
    	}
    	else if(isSaveState.equals("one"))
    	{
    		ServiceId = saveChannels.get(0).getServiceId();
    		set_timer_changePF();                               //延时器
    		new Thread(new Runnable(){

				public void run() {
					// TODO Auto-generated method stub
					System.out.println("SaveOne defaultPath");
					defaultPath(ServiceId);
					
				}}).start();
    	}
    	channel_type_tempt = queryServiceTypeByServiceId();
    	if(channel_type_tempt == 1)
    	{
    		period_position = map_channelList.get(ServiceId);	
    	}
    	else if(channel_type_tempt == 2)
    	{
    		period_position = map_bcList.get(ServiceId);
    	}
    	
    }
    
    private int queryServiceTypeByServiceId()
    {
    	Cursor c = getContentResolver().query(CHANNEL_INFO_TB_URI, new String[]{"MyServiceType"}, "MyServiceId = ?", new String[]{ServiceId+""}, null);
    	int count = 0;
    	if(c.moveToFirst())
    		count = c.getInt(c.getColumnIndex("MyServiceType"));
    	c.close();
    	return count;
    }
    /**
	 * 数字键输入频道号处理
	 * @param n  (0-9)
	 */
	
	private void numberSet(int n){
		        if(View.VISIBLE == linearLayoutFull.getVisibility())
    	        {
    		        linearLayoutFull.setVisibility(View.INVISIBLE);
    	        }
		        if (channelNumber.length() < 3) {
					channelNumber += n;
				}
				else
				{
					String num_tempt=channelNumber.substring(0,1);
					if(num_tempt.equals("0"))
					    channelNumber=channelNumber.substring(1)+n;
					else
						channelNumber = n+"";
						
				}
			    
				if(channelNumber.length()==1)
		    	{
		    		channelNumber="00"+channelNumber;
		    	}
		    	else if(channelNumber.length()==2)
		    	{
		    		channelNumber="0"+channelNumber;
		    	}
				
				send(channelNumber);
		    	channelN.setText("频道 "+channelNumber);
		    	handlerCloseView.removeMessages(msg_close);
		    	handlerCloseView.sendMessageDelayed(handlerCloseView.obtainMessage(msg_close), 3000);
		    	handlerPlayer.removeMessages(msg_num_play);
			    handlerPlayer.sendMessageDelayed(handlerPlayer.obtainMessage(msg_num_play), 1000);
			
	}
	
	
	
	/**
	 * 数字键play
	 */
	private void numPlay()
	{
		
		String sql = "MyLogicNum = ?";
		Cursor c = getContentResolver().query(DTVMain.CHANNEL_INFO_TB_URI, new String[]{"_id","MyServiceType"}, sql, new String[]{channelNumber}, null);
		
		int id = 0,serviceType = 0;
		if(c.moveToFirst())
		{
			  id = c.getInt(c.getColumnIndex("_id"));
		      serviceType = c.getInt(c.getColumnIndex("MyServiceType"));
			  c.close();
		
			  ServiceId_num_tempt = Integer.parseInt(mapHD.get(channelNumber));
			  if(ServiceId_num_tempt == ServiceId) 
			  {
				  channelNumber = "";
				  return;
			  }
			  
			  channel_num_tempt = id;             //暂时保存频道号
			  channel_type_tempt = serviceType;  //暂时保存频道类型
			  channelNumber = ""; 
			  System.out.println("numPlay======================");
			  if(pvr_state == Pvr.pvr_doing)
			  {
					isUpDown = 4;
				    showDialog(6);
			  }
			  else
			  {
		            numPlay_check();
			  }
		}
		else
		{
			c.close();
			channelNumber = ""; 
			Message msg1 = new Message();
			msg1.obj = nowChannel;
			handlerShowView.sendMessage(msg1);
		}
	}
	
	//录制时提示换台
	private void numPlay_check()
	{
		 ServiceId = ServiceId_num_tempt;  
		 new Thread(new Runnable(){

				public void run(){
					// TODO Auto-generated method stub
					System.out.println("numPlay defaultPath");
					defaultPath(ServiceId);
					
				}}).start();
			   
			   nowChannel=channel_num_tempt;         //更新当前频道号 int
			   changePF();
			   if(channel_type_tempt == 1)
			       period_position = map_channelList.get(ServiceId);	
			   else if(channel_type_tempt == 2)
				   period_position = map_bcList.get(ServiceId);
			   //save channel
			   SaveChannel sc=new SaveChannel();
			   sc.setChannelNum(nowChannel);
			   sc.setServiceId(ServiceId);
			   saveChannels.add(sc);
			   if(saveChannels.size()>2)
			   {
				   saveChannels.remove(0);
			   }
	}
	
	  /**
	   * 
	   * 根據用戶的選擇 判斷是否有該頻道
	   * @param id   ServiceId
	   * @return
	   */
  	private boolean queryChannel(int sid)
  	{
  		boolean hasNum=false;
  		
  		String sql = "ServiceId = ?";
  		Cursor cursor =getContentResolver().query(CHANNEL_TB_URI, null, sql, new String[]{sid+""}, null);
  		
  		if(cursor.moveToFirst())
  		{
  			hasNum=true;
  		}
  		
  		cursor.close();
  		
  		return hasNum;
  	}
  	
	  	/**
		 * 切换PF延迟
		 */
		private Handler handlerDelay =new Handler(){
			public void handleMessage(Message msg) {
				if(msg.what==msg_pf)
				{
				 changePF();
				}
			};
			
		};
		private Handler handlerDelay_TV =new Handler(){
			public void handleMessage(Message msg) {
				
				if(msg.what==msg_list_play)
				{
				 new Thread(new Runnable(){

					public void run() {
						// TODO Auto-generated method stub
						System.out.println("handleDelay defaultPath");
						defaultPath(ServiceId);
					}}).start();
				 updateNowChannel(ServiceId+"");
				 changePF();
				 changeSchedule(ServiceId);
			     //save channel
	  		     SaveChannel sc=new SaveChannel();
	  	         sc.setChannelNum(nowChannel);
	  	         sc.setServiceId(ServiceId);
	  	         saveChannels.add(sc);
	  	         if(saveChannels.size()>2)
	  	         {
	  	             saveChannels.remove(0);
	  	         }
				}
			};
			
		};
		private Handler handlerDelay_schudle = new Handler()
		{
			public void handleMessage(Message msg) {
				if(msg.what == msg_schudle)
				{
					changeSchedule(ServiceId);
				}
			};
		};
		/**
		    * 切换pf延时
		    */
		private void set_timer_changePF()
		{
			// TODO Auto-generated method stub
			handlerDelay.removeMessages(msg_pf);
			handlerDelay.sendMessageDelayed(handlerDelay.obtainMessage(msg_pf), 1000);
		}
		  /**
		   * 
		   */
		  private void set_timer_changeTV()
		  {
				// TODO Auto-generated method stub
				handlerDelay_TV.removeMessages(msg_list_play);
				handlerDelay_TV.sendMessageDelayed(handlerDelay_TV.obtainMessage(msg_list_play), 500);
		  }
		  private void set_timer_changeSchudle()
		  {
			  handlerDelay_schudle.removeMessages(msg_schudle);
			  handlerDelay_schudle.sendMessageDelayed(handlerDelay_schudle.obtainMessage(msg_schudle), 500);
		  }
		  
	  	/**
		 * 
		 * 根据serviceId 查询pf需要的参数并更新PF
		 * @param id  serviceid
		 */
		private void changePF(){
			getPF(ServiceId);
			
			int freq=0;        //频点值
			int freqNum=0;     //频点序号
			
			
			String sql = "ServiceId = ?";
			Cursor Mycursor = getContentResolver().query(CHANNEL_TB_URI, new String[]{"FreqSeq"},sql, new String[]{ServiceId+""},null);
			if(Mycursor.moveToNext()) {
	      		  freqNum=Mycursor.getInt(Mycursor.getColumnIndex("FreqSeq"));     		  
			}
			Mycursor.close();
			
			
			/*String sql1 = "freqSeq = ?";
			Cursor Mycursor1 = getContentResolver().query(FRELIST_TB_URI, new String[]{"freq"},sql1, new String[]{freqNum+""},null);
			
			if(Mycursor1.moveToFirst()){
				freq=Mycursor1.getInt(Mycursor1.getColumnIndex("freq"));
			}
			Mycursor1.close();*/
		
			//JniLoad.SetFreqset(freq, 6875, 3);
			JniLoad.ConfigEPG(freqNum, 1);   //1 表示PF
			JniLoad.StartEPG();
	    }
		
	      /**
	  	 * 
	  	 * 数字键   显示频道号
	  	 */
	  	private Handler handlerShowView =new Handler(){
	  		    public void handleMessage(Message msg) {
	  		    	
	  		    	String tempt=msg.obj.toString();
	  		    	if(tempt.length()==1)
	  		    	{
	  		    		tempt="00"+tempt;
	  		    	}
	  		    	else if(tempt.length()==2)
	  		    	{
	  		    		tempt="0"+tempt;
	  		    	}
	  		    	channelN.setText("频道 "+tempt);
	  		    	handlerCloseView.removeMessages(msg_close);
	  		    	handlerCloseView.sendMessageDelayed(handlerCloseView.obtainMessage(msg_close), 5000);
	  		    	
	  		    };
	  	};
	  	/**
	  	 * channel+   channel-
	  	 */
	  	private Handler handlerShowChannelNum = new Handler()
	  	{
	  		public void handleMessage(Message msg) 
	  		{
	  			String ChannelNumTempt=msg.obj.toString();
	  			if(ChannelNumTempt.length()==1)
	  			{
	  				ChannelNumTempt = "00"+ChannelNumTempt;
	  			}
	  			else if(ChannelNumTempt.length()==2)
	  			{
	  				ChannelNumTempt = "0"+ChannelNumTempt;
	  			}
	  			channelN.setText("频道"+ChannelNumTempt);
	  			
	  			handlerCloseView.removeMessages(msg_close);
  		    	handlerCloseView.sendMessageDelayed(handlerCloseView.obtainMessage(msg_close), 5000);
  		    	
  		    	/*handlerChannelPlayer.removeMessages(msg_channel_play);
  		    	handlerChannelPlayer.sendMessageDelayed(handlerChannelPlayer.obtainMessage(msg_channel_play), 1000);*/
	  		};
	  	};
	  	/**
		 * 
		 * 隐藏频道号
		 */
		private Handler handlerCloseView =new Handler(){
			public void handleMessage(Message msg) {
				channelN.setText("");
				
			};
		};
		
		/**
		 * 音量调节
		 */
		private Handler handler_volume = new Handler(){
			public void handleMessage(Message msg) {
				media_auto_layout.setVisibility(View.GONE);
			};
		};
		private Handler handler_epg_intruduce = new Handler()
		{
			public void handleMessage(Message msg) {
				linearLayout_epg_introduce.setVisibility(View.INVISIBLE);
				isEPGIntroduce=false;
				
			};
		};
	  	/**
		 * 
		 * play
		 */
		private Handler handlerPlayer =new Handler(){
			public void handleMessage(Message msg){
				numPlay();  //数字键play
			}
		};
		
		private Handler handlerChannelPlayer = new Handler()
		{
			public void handleMessage(Message msg) 
			{
				
				        channelUpandDown();//频道+ - play
			};
		};
		private Handler handlerSavePlay = new Handler()
		{
			public void handleMessage(Message msg) 
			{
				savePlay();  // save play
			};
		};
		
		/**
		 * 
		 * 显示页面时间
		 */
		private Handler mHandler = new Handler() {

			public void handleMessage(Message msg) {
				// 接受Message 传过来的本地时间值
				Bundle b=msg.getData();
				String[] times=b.getStringArray("setTime");
				fullTime.setText(times[0]);
				edit_time.setText(times[1]);
				reveration_time.setText(times[1]);
				pvr_time.setText(times[1]);
				time_epg_now.setText(times[0]);
	        }
		};
		
    /**
	 * 
	 * 默认播放频道
	 * @param sid
	 */
	private void defaultPath(int sid)
	{
		int freq=0;
		int sysmbol = 0;
		int videoPid=0;
		int videoType=0;
		int audioPid=0;
		int audioType=0;
		
		System.out.println("defaultPath start serviceId=?");
		
		int tsId=0,pmtPid=0,serviceId=0,freqSeq=0,id=0;
       
		String sql = "ServiceId = ?";
		Cursor Mycursor = getContentResolver().query(CHANNEL_TB_URI, new String[]{"_id","FreqSeq","TsId","ServiceId","PmtPid","EleVideoPid","VideoMediaType"}, sql, new String[]{sid+""}, null);
		if(Mycursor.moveToNext())
		{
      		 id = Mycursor.getInt(Mycursor.getColumnIndex("_id"));
			 freqSeq = Mycursor.getInt(Mycursor.getColumnIndex("FreqSeq"));
			 tsId = Mycursor.getInt(Mycursor.getColumnIndex("TsId"));
      		 serviceId=Mycursor.getInt(Mycursor.getColumnIndex("ServiceId"));
      		 pmtPid = Mycursor.getInt(Mycursor.getColumnIndex("PmtPid"));
			 videoPid=Mycursor.getInt(Mycursor.getColumnIndex("EleVideoPid"));
			 videoType = Mycursor.getInt(Mycursor.getColumnIndex("VideoMediaType"));
		}
		

		Mycursor.close();
		
		System.out.println("defaultPath start freqSeq=?");
		
		String sql1 = "freqSeq = ?";
		Cursor Mycursor1 = getContentResolver().query(FRELIST_TB_URI, new String[]{"freq","sysmbol"}, sql1, new String[]{freqSeq+""}, null);
		if(Mycursor1.moveToNext())
		{
			freq=Mycursor1.getInt(Mycursor1.getColumnIndex("freq"));
			sysmbol=Mycursor1.getInt(Mycursor1.getColumnIndex("sysmbol"));
			System.out.println("freq="+freq+"/sysmbol="+sysmbol);
		}
		Mycursor1.close();
		
		System.out.println("defaultPath start channel_id=?");
		
		String sql2 = "channel_id = ?";
		Cursor Mycursor2 = getContentResolver().query(AUDIO_TB_URI, new String[]{"enAudioMediaType","eleAudioPid"}, sql2, new String[]{id+""}, null);
		if(Mycursor2.moveToNext()){
			
			audioPid = Mycursor2.getInt(Mycursor2.getColumnIndex("eleAudioPid"));
			audioType = Mycursor2.getInt(Mycursor2.getColumnIndex("enAudioMediaType")); 
		}
		Mycursor2.close();
		
		System.out.println("defaultPath start CA=?");
		Cursor Mycursor3 = getContentResolver().query(CA_TB_URI, null, sql2, new String[]{id+""}, null);
		if(!Mycursor3.moveToFirst()){
			//如果没有加密  后三个参数为0
			tsId=0;
			pmtPid=0;
			serviceId=0;
			isFree = true;
		}
		Mycursor3.close();
		/*if(!isFree)
		{
			Message msg_password = new Message();
			handlerPassword.handleMessage(msg_password);
		}
		isFree = false;		*/
		path="mpeg2ts:///"+freq+"/"+sysmbol+"/"+videoPid+"/"+audioPid+"/"+tsId+"/"+pmtPid+"/"+serviceId+"/";
		
		System.out.println("changeChannel() path="+path);
		JniLoad.SetFreqset(freq, 6875, 3);
		JniLoad.SetAV(audioPid, videoPid, pmtPid, tsId, serviceId);
		//JniPlayer.changeProgram(freq*1000, 0, sysmbol*1000, 0, 0, pmtPid, videoPid, audioPid, 0, serviceId, tsId);
		//JniPlayer.changeProgram(freq, videoPid, videoType, audioPid, audioType, tsId, pmtPid, serviceId);
	}

	private Handler handlerPassword = new Handler(){
		public void handleMessage(Message msg) {
			
			Toast.makeText(DTVMain.this, "该节目加密！", Toast.LENGTH_SHORT).show();
		};
	};
	
	/**
     * 
     * 获取PF信息
     * @param SId
     */
    private void getPF(int SId){
    	
    	fullscreenNow.setText("");
    	fullscreenNext.setText("");
    	String tempt=nowChannel+"";
		if(tempt.length()==1)
		{
			tempt="00"+tempt;
		}
		else if(tempt.length()==2)
		{
			tempt="0"+tempt;
		}
		String name="";
		
		String sql = "ServiceId = ?";
		Cursor Mycursor = getContentResolver().query(CHANNEL_TB_URI, new String[]{"ServiceName","ServiceType"}, sql, new String[]{ServiceId+""}, null);
        if(Mycursor.moveToNext()) {
		
        	byte[] s=Mycursor.getBlob(0);
			
			try {
				name = new String(s,"gb2312");
				ServiceType = Mycursor.getInt(Mycursor.getColumnIndex("ServiceType"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        }
        Mycursor.close();
	    fullName.setText(tempt+"  "+name);	
    	fullTitle.setText(tempt+"  "+name);
    	try {
			EPGInfo[] e = JniLoad.EpgArray(SId);
    	
		//當前節目
    	//开始时间调整
		String starth1="";
		String startm1="";
		
		//取得系统时区偏移量
		final java.util.Calendar cal = java.util.Calendar.getInstance();   
		final int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET)/3600000; 
			
		if (e[0] != null) {
			
            if(e[0].getProgramStartTime()==0 && e[0].getProgramDurationTime()==0 && e[0].getProgramDate().equals("0.0.0")){
            	fullscreenNow.setText(""); 
            	
            } 
            else
            {
            int starth = e[0].getProgramStartTime() / 3600 + zoneOffset;
            if(starth>=24){
				starth=starth%24;
			}
			if (e[0].getProgramStartTime() % 3600 == 0) {
				if (starth < 10) {
					starth1 = "0" + starth;
				} else {
					starth1 = starth + "";
				}
				startm1 = "00";
			} else {

				if (starth < 10) {
					starth1 = "0" + starth;
				} else {
					starth1 = starth + "";
				}
				int startm = e[0].getProgramStartTime() % 3600 / 60;

				if (startm < 10) {
					startm1 = "0" + startm;
				} else {
					startm1 = startm + "";
				}
			}

			// 结束时间调整
			String endh1 = "";
			String endm1 = "";
			int endh = (e[0].getProgramStartTime() + e[0]
					.getProgramDurationTime()) / 3600 + zoneOffset;
			if(endh>=24){
				endh=endh%24;
			}
			if ((e[0].getProgramStartTime() + e[0]
					.getProgramDurationTime()) % 3600 == 0) {
				if (endh < 10) {
					endh1 = "0" + endh;
				} else {
					endh1 = endh + "";
				}
				endm1 = "00";
			} else {
				if (endh < 10) {
					endh1 = "0" + endh;
				} else {
					endh1 = endh + "";
				}

				int endm = (e[0].getProgramStartTime() + e[0]
						.getProgramDurationTime()) % 3600 / 60;

				if (endm < 10) {
					endm1 = "0" + endm;
				} else {
					endm1 = endm + "";
				}
			}
			fullscreenNow.setText( starth1 + ":" + startm1 + "-"
						+ endh1 + ":" + endm1+" "+e[0].getProgramName());	
			full_introduce_eventId=e[0].getEventID();
		    nameAndDate.setText(e[0].getProgramName());
		    edit_now_time.setText(starth1 + ":" + startm1 + "-"
						+ endh1 + ":" + endm1);
		    edit_now_name.setText(e[0].getProgramName());
            }
			}
		
		//后續節目
		//开始时间调整
		if (e[1] != null) {
			if(e[1].getProgramStartTime()==0 && e[1].getProgramDurationTime()==0 && e[1].getProgramDate().equals("0.0.0")){
				fullscreenNext.setText("");
        		
            } 
			String starth2 = "";
			String startm2 = "";
			int sh = e[1].getProgramStartTime() / 3600 + zoneOffset;
			if(sh>=24){
				sh=sh%24;
			}
			if (e[1].getProgramStartTime() % 3600 == 0) {
				if (sh < 10) {
					starth2 = "0" + sh;
				} else {
					starth2 = sh + "";
				}
				startm2 = "00";
			} else {

				if (sh < 10) {
					starth2 = "0" + sh;
				} else {
					starth2 = sh + "";
				}
				int sm = e[1].getProgramStartTime() % 3600 / 60;

				if (sm < 10) {
					startm2 = "0" + sm;
				} else {
					startm2 = sm + "";
				}
			}

			// 结束时间调整
			String endh2 = "";
			String endm2 = "";
			int eh = (e[1].getProgramStartTime() + e[1]
					.getProgramDurationTime()) / 3600 + zoneOffset;
			if(eh>=24){
				eh=eh%24;
			}
			if ((e[1].getProgramStartTime() + e[1]
					.getProgramDurationTime()) % 3600 == 0) {
				if (eh < 10) {
					endh2 = "0" + eh;
				} else {
					endh2 = eh + "";
				}
				endm2 = "00";
			} else {
				if (eh < 10) {
					endh2 = "0" + eh;
				} else {
					endh2 = eh + "";
				}

				int em = (e[1].getProgramStartTime() + e[1]
						.getProgramDurationTime()) % 3600 / 60;

				if (em < 10) {
					endm2 = "0" + em;
				} else {
					endm2 = em + "";
				}
			}
            
			fullscreenNext.setText(starth2+":"+startm2+"-"+endh2+":"+endm2+" "+e[1].getProgramName());
			edit_next_time.setText(starth2+":"+startm2+"-"+endh2+":"+endm2);
			edit_next_name.setText(e[1].getProgramName());
			
		}
		}
    	catch (Exception e)
		{
//			fullscreenNow.setText("");
//			fullscreenNext.setText("");
			return;
		}
		finally
		{
     	  if(ServiceType == 1)
	      {
			//显示电视banner条
			full_list_type.setText(getString(R.string.main_sd));
			if(isFullscreen)
			{
			isFull=true;
			linearLayoutFull.setVisibility(View.VISIBLE);
			if(isIntroduce)
			{
				isIntroduce=false;
				linearLayout_introduce1.setVisibility(View.INVISIBLE);
				linearLayout_introduce1.getBackground().setAlpha(180);
			}
			if(!isbanner)
			{
			isbanner=true;
			linearLayout_banner.setVisibility(View.VISIBLE);
			linearLayout_banner2.getBackground().setAlpha(180);
			}
			}
			handlerPF.removeMessages(pf_close);
			handlerPF.sendMessageDelayed(handlerPF.obtainMessage(pf_close), 3000);
			
		    
		  }
		  else if(ServiceType == 2)
		  {
			//显示广播banner条
			full_list_type.setText(getString(R.string.main_bc));
			if(isFullscreen)
			{
			isFull=true;
			linearLayoutFull.setVisibility(View.VISIBLE);
			if(isIntroduce)
			{
				isIntroduce=false;
				linearLayout_introduce1.setVisibility(View.INVISIBLE);
				linearLayout_introduce1.getBackground().setAlpha(180);
			}
			if(!isbanner)
			{
			isbanner=true;
			linearLayout_banner.setVisibility(View.VISIBLE);
			linearLayout_banner2.getBackground().setAlpha(180);
			}
			}
		   }
		}
    }
    private Handler handlerPF = new Handler(){
    	public void handleMessage(Message msg) {
    		linearLayoutFull.setVisibility(View.INVISIBLE);
			isFull=false;
    	};
    };
    //check DB
    private boolean checkDB()
    {
    	Cursor Mycursor = getContentResolver().query(CHANNEL_TB_URI, null, null, null, null);
    	
		if(Mycursor.getCount()>0)
		{
			Mycursor.close();
			return true;
		}
		else 
		{
			Mycursor.close();
			//return false;
			return true;
		}
    }
    
    private void initMenu()
    {
    	
    	menu_adapter = new SimpleAdapter(DTVMain.this, getData(),
    			R.layout.menu_content, new String[] {"name"}, new int[] { R.id.menu_name});
    	
    	menu_listview.setAdapter(menu_adapter);      
    	menu_listview.setDividerHeight(0);
    	menu_listview.setOnItemSelectedListener( new ListView.OnItemSelectedListener()
	    	{
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id)
				{
					// TODO Auto-generated method stub
					
				}
	
				public void onNothingSelected(AdapterView<?> parent)
				{
					// TODO Auto-generated method stub	
				}
			}
    	);
	    	
    }
    
	/**
	 * load data and play
	 */
	public void init()
	{
		isNoData = true; 
		System.out.println("getTVInfo() start:"+sDateFormat.format(new java.util.Date()));
		initMenu();
		getLogicNum();
		System.out.println("getTVInfo() center:"+sDateFormat.format(new java.util.Date()));
		// init play
		SharedPreferences sharedata = getSharedPreferences(TV_SET, 0);
		
    	//设置默认值
    	ServiceId = sharedata.getInt("channel_serviceId", Integer.parseInt(mapHD.get("001")));
    	System.out.println("ServiceId="+ServiceId);
    	JniPlayer.setWindowSize(0, 0, 0, 0);            //设置全屏
    	new Thread(new Runnable()
	    	{
	
				public void run()
				{
					// TODO Auto-generated method stub
					System.out.println("init defaultPath");
					defaultPath(ServiceId); 
					
				}
			}
    	).start();
    	
    	nowChannel=sharedata.getInt("channel_num", 1);
    	channel_type_tempt = sharedata.getInt("channel_type", 1);
    	eventId_pvr = sharedata.getInt("eventId_pvr", 0);
    	loadPeriodTV();
		loadChannelList();
		loadBCList();
		if(channel_type_tempt == 1)
		{
			period_position = map_channelList.get(ServiceId);
			
		}
		else if(channel_type_tempt == 2)
		{
			period_position = map_bcList.get(ServiceId);
		}
    	//save channel
		SaveChannel sc=new SaveChannel();
		sc.setChannelNum(nowChannel);
		sc.setServiceId(ServiceId);
		saveChannels.add(sc);
		if(saveChannels.size()>2)
		{
			 saveChannels.remove(0);
		}
		
    	changePF();
    	System.out.println("getTVInfo() end:"+sDateFormat.format(new java.util.Date()));
	}
	
	// get mapHD
	private void getLogicNum(){
		if(mapHD!=null)
			mapHD.clear();
		//保存 ServiceId  nowChannel
		
		int sid =0;
		String logicNum = "";
		Cursor Mycursor = getContentResolver().query(CHANNEL_INFO_TB_URI, new String[]{"MyLogicNum","MyServiceId"},null, null, null);
		while(Mycursor.moveToNext()) {
			
			logicNum = Mycursor.getString(Mycursor.getColumnIndex("MyLogicNum"));
			sid = Mycursor.getInt(Mycursor.getColumnIndex("MyServiceId"));
			mapHD.put(logicNum,Integer.toString(sid));
		}
		Mycursor.close();
	}
	
	/**
     * 
     * 根据id 查询Schedule需要的参数并更新Schedule
    */
	 private void changeSchedule(int id){
		
	 	String sql = "ServiceId = ?";
	 	Cursor Mycursor = getContentResolver().query(CHANNEL_TB_URI, new String[]{"FreqSeq"}, sql, new String[]{id+""}, null);
		if(Mycursor.moveToNext()) {
     		  freqNum_Period=Mycursor.getInt(Mycursor.getColumnIndex("FreqSeq"));
     		       		  
		}

		Mycursor.close();
		
		/*String sql1 = "freqseq = ?";
		Cursor Mycursor1 = getContentResolver().query(FRELIST_TB_URI, new String[]{"freq"}, sql1, new String[]{freqNum_Period+""}, null);
		if(Mycursor1.moveToNext()){
			freq_Period=Mycursor1.getInt(Mycursor1.getColumnIndex("freq"));
		}
		Mycursor1.close();*/
		
		
		//更新Schedule
		getSchedule(ServiceId,day);
		
//		JniLoad.SetFreqset(freq_Period, 6875, 3);
		JniLoad.ConfigEPG(freqNum_Period, 256);   //1 表示PF 256表示Schedule
		JniLoad.StartEPG();
		
	 }
	 /**
		 * 获取一天的schedule信息
		 * @param sid
		 * @param day
		 */
	      
	     private void getSchedule(int sid,int day){
	        	
			try{
				
			EPGInfo[] e=JniLoad.ScheduleEpgArray(sid, day);
			//开始时间调整
			String starth1="";
			String startm1="";
			final java.util.Calendar cal = java.util.Calendar.getInstance();   
			final int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET)/3600000;   
			int num = 1;
			
			Time time = new Time();     
		    time.setToNow(); 
		    int hour = time.hour;
            int min = time.minute;
            int now_day=time.monthDay;
			System.out.println("hour="+hour+",min="+min);
			for (int i=0;i<e.length;i++) {
			    
				//取得系统时区偏移量
				int starth = e[i].getProgramStartTime() / 3600 + zoneOffset;
				if(starth>=24){
					
					starth=starth%24;
				}
				
				if (e[i].getProgramStartTime() % 3600 == 0) {
					if (starth < 10) {
						starth1 = "0" + starth;
					} else {
						starth1 = starth + "";
					}
					startm1 = "00";
				} else {

					if (starth < 10) {
						starth1 = "0" + starth;
					} else {
						starth1 = starth + "";
					}
					int startm = e[i].getProgramStartTime() % 3600 / 60;

					if (startm < 10) {
						startm1 = "0" + startm;
					} else {
						startm1 = startm + "";
					}
				}

				// 结束时间调整
				String endh1 = "";
				String endm1 = "";
				int endm = 0;
				int endh = (e[i].getProgramStartTime() + e[i]
						.getProgramDurationTime()) / 3600 + zoneOffset;
	            if(endh>=24){
	            	endh=endh%24;
	            }
				if ((e[i].getProgramStartTime() + e[i]
						.getProgramDurationTime()) % 3600 == 0) {
					
					
					if (endh < 10) {
						endh1 = "0" + endh;
					} else {
						endh1 = endh + "";
					}
					endm1 = "00";
				} else {
					if (endh < 10) {
						endh1 = "0" + endh;
					} else {
						endh1 = endh + "";
					}

					endm = (e[i].getProgramStartTime() + e[i]
							.getProgramDurationTime()) % 3600 / 60;

					if (endm < 10) {
						endm1 = "0" + endm;
					} else {
						endm1 = endm + "";
					}
				}
	            
				   
                 
				 if(now_day == day)
				 {
	                if(endh<hour)
	                {
	            	  continue;	
	                }
	                else if(endh == hour)
	                {
	            	  if(endm<min)continue;
	                }
				 }
				String listTime=starth1 + ":" + startm1 + "-"+ endh1 + ":" + endm1;
				String listName=e[i].getProgramName();
				mapEPG = new HashMap<String, Object>();
				mapEPG.put("num", num);
				mapEPG.put("name", listName);
				mapEPG.put("time", listTime);
				if(e[i].getOrderFlag()==1)
				{
					mapEPG.put("periodImage", R.drawable.jog_tab_target_red);
				}else
				{
					mapEPG.put("periodImage", R.drawable.jog_tab_target_gray);
				}
				
				mapEPG.put("pvrImage", R.drawable.jog_tab_target_gray);
				
				//保存eventId
				mapEPG.put("eventId", e[i].getEventID());
				
				listEPG.add(mapEPG);
				num++;
				}
			    
			adapter_epg = new SimpleAdapter(DTVMain.this, listEPG,
					R.layout.period_list_content, new String[] { "num","name","time","periodImage","pvrImage" }, new int[] { R.id.period_list_num,R.id.period_list_name,R.id.period_list_time,R.id.period_list_periodPic,R.id.period_list_pvrPic});
	        if(!adapter_epg.isEmpty())
			listview_period_epg.setAdapter(adapter_epg);
				
			}catch(Exception e){
				return;
			}
	    	
	    }
	     /**
	 	 * schedule 信息对应的日期
	 	 * @param d  (1-6)
	 	 * @return
	 	 */
	 	private String getPeriodDate(int d){
	 		
	 		Calendar calendar=Calendar.getInstance();
	 		calendar.add(Calendar.DATE, d);
	 		SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日EEE");
	 		String sDate=df1.format(calendar.getTime()).replace("周", "星期");
	 		return sDate;
	 	}
	 	
	 	//加载预约列表
	 	private void setOrderList(){
	 		if(listReveration!=null)
	 		listReveration.clear();
	 		if(map2!=null)
 				map2.clear();
	 		HashMap<String, Object> map_reveration;
	 		try{
	 		EventBean[] eventBean = JniLoad.GetOrderInfo();
	 		
	 		Cursor Mycursor = null;
	 		for(int i=0;i<eventBean.length;i++){
	 			
	 			map_reveration = new HashMap<String,Object>();
	 			map_reveration.put("num", (i+1)+"");
	 			map_reveration.put("event_name", eventBean[i].getEventName());
	 			int sid = eventBean[i].getSerId();
	 			String cn_tempt="";
	 			Iterator<String> it= mapHD.keySet().iterator();              
	 	        while(it.hasNext())
	 	        {
	 	             String keyString=it.next();
	 	             if(mapHD.get(keyString).equals(sid+"")) 
	 	             {
	 	            	 cn_tempt=keyString;
	 	                 break;
	 	             }
	 	        }
	 	        if(cn_tempt.length()==1)
	 	        {
	 	        	cn_tempt = "00"+cn_tempt;
	 	        }
	 	        else if(cn_tempt.length()==2)
	 	        {
	 	        	cn_tempt = "0"+cn_tempt;
	 	        }
	 			map_reveration.put("channel_num", cn_tempt);
		      
	 			String sql = "ServiceId = ?";
	 			Mycursor = getContentResolver().query(CHANNEL_TB_URI, new String[]{"ServiceName"}, sql, new String[]{sid+""}, null);
		        while (Mycursor.moveToNext()) {
			    byte[] s=Mycursor.getBlob(0);
			    String tvName1 = null;
			    try {
				tvName1 = new String(s,"gb2312");
			    } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			    }
			    map_reveration.put("channel_name", tvName1);
	 			String starth1="";
	 			String startm1="";
	 			int starth = eventBean[i].getEventStartTime()/ 3600 + 8;
	 			if(starth>=24){
	 				starth=starth%24;
	 			}
	 			
	 			if (eventBean[i].getEventStartTime() % 3600 == 0) {
	 				if (starth < 10) {
	 					starth1 = "0" + starth;
	 				} else {
	 					starth1 = starth + "";
	 				}
	 				startm1 = "00";
	 			} else {

	 				if (starth < 10) {
	 					starth1 = "0" + starth;
	 				} else {
	 					starth1 = starth + "";
	 				}
	 				int startm = eventBean[i].getEventStartTime() % 3600 / 60;

	 				if (startm < 10) {
	 					startm1 = "0" + startm;
	 				} else {
	 					startm1 = startm + "";
	 				}
	 			}
	 		// 结束时间调整
				String endh1 = "";
				String endm1 = "";
				int endh = (eventBean[i].getEventStartTime() + eventBean[i]
						.getEventDuration()) / 3600 + 8;
	            if(endh>=24){
	            	endh=endh%24;
	            }
				if ((eventBean[i].getEventStartTime() +eventBean[i]
				        .getEventDuration()) % 3600 == 0) {
					if (endh < 10) {
						endh1 = "0" + endh;
					} else {
						endh1 = endh + "";
					}
					endm1 = "00";
				} else {
					if (endh < 10) {
						endh1 = "0" + endh;
					} else {
						endh1 = endh + "";
					}

					int endm = (eventBean[i].getEventStartTime() + eventBean[i]
							.getEventDuration()) % 3600 / 60;

					if (endm < 10) {
						endm1 = "0" + endm;
					} else {
						endm1 = endm + "";
					}
				}
	            
				String listTime=starth1 + ":" + startm1 + "-"+ endh1 + ":" + endm1;
	 			
	 			map_reveration.put("event_time",listTime);
	 			
	 			listReveration.add(map_reveration);
	 			
	 			//保存预约列表信息
	 			
	 			EventBean en=new EventBean();
	 			en.setSerId(eventBean[i].getSerId());
	 			en.setEventId(eventBean[i].getEventId());
	 			map2.put(Integer.toString(i+1), en);
	 		}
	 		}
	 		Mycursor.close();
	 		
	 		adapter_reveration = new SimpleAdapter(DTVMain.this, listReveration,
	 				R.layout.reveration_content, new String[] { "num","channel_num","channel_name","event_name","event_time"}, new int[] { R.id.reveration_num,R.id.reveration_channel_num,R.id.reveration_channel_name,R.id.reveration_event_name,R.id.reveration_event_time});

	 		listView_reveration.setAdapter(adapter_reveration);
	 		}catch(NullPointerException e){
	 			adapter_reveration = new SimpleAdapter(DTVMain.this, listReveration,
		 				R.layout.reveration_content, new String[] { "num","channel_num","channel_name","event_name","event_time"}, new int[] { R.id.reveration_num,R.id.reveration_channel_num,R.id.reveration_channel_name,R.id.reveration_event_name,R.id.reveration_event_time});

	 			listView_reveration.setAdapter(adapter_reveration);
	 			
	 			
	 			return;
	 		}

	 	}	
    
  //广播接收器      接收   预约 和  预录提示信息
    private BroadcastReceiver MsgReceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Bundle b=arg1.getExtras();
			Message msg=new Message();
			msg.setData(b);
			handlerDVBMsg.sendMessage(msg);
			
		}
		    	
	};
	
	
	
	/**
	 * 
	 * 处理epg,预约和预录信息
	 */
	private Handler handlerDVBMsg =new Handler(){
		public void handleMessage(Message msg) {
			
			Bundle b=msg.getData();
			for (String type : b.keySet()) {

				String[] args = b.getStringArray(type);
				
				
				if(type.equals(DVB.MESSAGE_EIT_RECV_PF)&& Integer.parseInt(args[0])==ServiceId) {
		                
					getPF(ServiceId);
					
				}
				else if(type.equals(DVB.MESSAGE_EIT_RECV_SCHEDULE)&& Integer.parseInt(args[0])==ServiceId){
					
					 if(isEPG){
						 if(listEPG!=null)
						 {
							 listEPG.clear();
							 if(adapter_epg!=null)
								 adapter_epg.notifyDataSetChanged();
						 }
						 System.out.println("get schedule info 20000033"+ServiceId);
						 getSchedule(ServiceId,day);
		            	
		            }
				}else if(type.equals(DVB.MESSAGE_ORDER_REMIND)) {
	                
					period_ServiceId_tempt=Integer.parseInt(args[0]);
					
					handlerPeriod_delayPlay.removeMessages(msg_period); // 默认定时20s播放
					handlerPeriod_delayPlay.sendMessageDelayed(handlerPeriod_delayPlay.obtainMessage(msg_period), 20*1000);
					
					DTVMain.this.showDialog(8);
				
				}else if(type.equals(DVB.MESSAGE_ORDER_PVR_REMIND)){
					eventId_pvr=Integer.parseInt(args[1]); 
					serviceId_pvr = Integer.parseInt(args[0]);
					
					handlerPvr_delayPlay.removeMessages(msg_pvr); // 默认定时20s录制
					handlerPvr_delayPlay.sendMessageDelayed(handlerPvr_delayPlay.obtainMessage(msg_pvr), 20*1000);
					
				    DTVMain.this.showDialog(9);
				}
				else if(type.equals("20000006")) {

					freq.setText(args[0]);
					
				} else if (type.equals("20000011")) {
					
									
					//设置当前搜索到的频点数
					freqcount.setText(barMax+ "/" + args[0]);
					

				} else if (type.equals("20000008")) {
					tvandbc.setText(args[0] + "/" + args[1]);

				}else if(type.equals("20000014")){
					barMax=Integer.parseInt(args[0]);
					
				} else if(type.equals("20000012")) {
					
					// 设置进度条
					bar.setProgress(Integer.parseInt(args[0].trim()));
					
					if (Integer.parseInt(args[0]) == 100) {

						searchResult.setText(getString(R.string.search_end).toString());
						
					} else {
						searchResult.setText(getString(R.string.search_ing).toString());
					}
				}else if(type.equals("20000009"))
				{
					isGetTuner=false;
					linearLayout_auto_notice.setVisibility(View.INVISIBLE);
					
					Intent intent_set = new Intent(DTVMain.this,SettingActivity.class);
					startActivity(intent_set);
				}
				else if(type.equals("20000013")){
					isGetTuner = false;
					JniLoad.Stop();
					saveData();
				}
			}			
		};
	};
	
	private void saveData()
	{
		
		DBFactory.insertData(DTVMain.this, CHANNEL_TB_URI, CHANNEL_INFO_TB_URI);
		searchResult.setText("");
		linearLayout_auto_notice.setVisibility(View.INVISIBLE);
		init();
		
	}
	private Handler handlerPeriod_delayPlay = new Handler(){
		public void handleMessage(Message msg) {
			
			if(msg.what == msg_period)
			{
				 if(!isPeriodPlay)
				  {
					 DTVMain.this.dismissDialog(8); 
					 ServiceId = period_ServiceId_tempt;
				      new Thread(new Runnable(){

						public void run() {
							// TODO Auto-generated method stub
							System.out.println("handlePeriodDelay defaultPath");
							defaultPath(ServiceId);         //根据serviceid 改变path换台
							
						}}).start();
                      
				      updateNowChannel(ServiceId+""); //更新当前频道号
                      changePF();                     //换台更新PF
                   
                   //save channel
           		   SaveChannel sc=new SaveChannel();
           		   sc.setChannelNum(nowChannel);
           		   sc.setServiceId(ServiceId);
           		   saveChannels.add(sc);
           		   if(saveChannels.size()>2)
           		   {
           			   saveChannels.remove(0);
           		   }
					 
				  }
				  else 
				  {
					  isPeriodPlay = false;
				  }
			}
		};
		
	};
	
	private Handler handlerPvr_delayPlay = new Handler()
	{
		public void handleMessage(Message msg)
		{	
			if(msg.what == msg_pvr)
			{
				 if(!isPvrPlay)
				 {
					 DTVMain.this.dismissDialog(9); 
					 if(ServiceId!=serviceId_pvr)
					 {
					  ServiceId = serviceId_pvr;
				      new Thread(new Runnable()
				      {

						public void run()
						{
							// TODO Auto-generated method stub
							System.out.println("handlePVRDelay defaultPath");
							defaultPath(ServiceId);         //根据serviceid 改变path换台
							
						}}).start();
                     
				      updateNowChannel(ServiceId+"");       //更新当前频道号
                      changePF();                           //换台更新PF
                  
                     //save channel
          		     SaveChannel sc=new SaveChannel();
          		     sc.setChannelNum(nowChannel);
          		     sc.setServiceId(ServiceId);
          		     saveChannels.add(sc);
          		     if(saveChannels.size()>2)
          		     {
          			   saveChannels.remove(0);
          		     }
				    }
					 
					 pvr_state=Pvr.pvr_doing;  
					 setPVR();               //启动节目录制
					 
				  }
				  else 
				  {
					  isPvrPlay = false;
				  }
			}
		};
		
	};
    
	/**
     * 
     * 启动节目录制
     */
    private void setPVR()
    {
    	int pvr_videoType=0,pvr_audioType=0,pvr_pcrPid=0,pvr_videoPid=0,pvr_audioPid=0;
    	int pvr_id=0;
    	
    	String sql = "ServiceId = ?";
    	Cursor Mycursor = getContentResolver().query(CHANNEL_TB_URI, new String[]{"_id","VideoMediaType","PcrPID","EleVideoPid"}, sql, new String[]{serviceId_pvr+""}, null);
    	
		if(Mycursor.moveToNext())
		{
			  pvr_id = Mycursor.getInt(Mycursor.getColumnIndex("_id"));
      		  
			  pvr_videoType = Mycursor.getInt(Mycursor.getColumnIndex("VideoMediaType"));
      		  pvr_pcrPid = Mycursor.getInt(Mycursor.getColumnIndex("PcrPID"));
      		  pvr_videoPid = Mycursor.getInt(Mycursor.getColumnIndex("EleVideoPid"));
		}
		
		Mycursor.close();
		
		String sql1 = "channel_id = ?";
		Cursor Mycursor1 = getContentResolver().query(AUDIO_TB_URI, new String[]{"enAudioMediaType","eleAudioPid"},sql1, new String[]{pvr_id+""}, null);
		
		if(Mycursor1.moveToNext())
		{
			pvr_audioType = Mycursor1.getInt(Mycursor1.getColumnIndex("enAudioMediaType"));
		    pvr_audioPid = Mycursor1.getInt(Mycursor1.getColumnIndex("eleAudioPid"));
		}
		Mycursor1.close();
			
    	JniLoad.PVRSetMediaType(pvr_videoType, pvr_audioType);
    	JniLoad.PVRSetMediaPID(pvr_pcrPid, pvr_videoPid, pvr_audioPid);
    	EventBean pvr_bean=JniLoad.PVRGetBySerIdEventId(serviceId_pvr, eventId_pvr, 1);
    	String pvrPath = pvr_bean.getEventName()+getNowTime()+".ts";
    	JniLoad.PVRSetMediaName(pvrPath);
    	JniLoad.PVRStart();
    	
    	//显示录制时间
    	set_timer();
    	//定时结束录制
    	pvrTimeSet(pvr_bean.getEventDuration());
    	
    }
    
   private void set_timer()
   {
    	
	    isPvring = true;
    	new Thread(new Runnable()
    	{
            
    		int i = 0;
            public void run()
            {
				// TODO Auto-generated method stub
				while(isPvring)
				{
					Message msg = new Message();
					msg.what = i;
					handlerPvrTime.sendMessage(msg);
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					i++;
			        System.out.println("i="+i);
				}
			}}).start();
    	
    }
    
    private Handler handlerPvrTime = new Handler()
    {
    	public void handleMessage(Message msg)
    	{
            int count = msg.what;
            
            int hh_tempt = count/3600;
            int min_tempt = count%3600/60;
            int ss_tempt = count%60;
            
            if(hh_tempt<10)
            {
            	hh = "0"+hh_tempt;
            }
            else
            {
                hh = hh_tempt+"";	
            }
            if(min_tempt<10)
            {
            	min = "0" + min_tempt;
            }
            else
            {
            	min = min_tempt+"";
            }
            if(ss_tempt<10)
            {
            	ss = "0"+ss_tempt;
            }
            else
            {
            	ss =ss_tempt+"";
            }
            
            pvr_notice.setText(getString(R.string.main_prv_ing)+" "+hh+":"+min+":"+ss);
            if(isFullscreen || isChannelList)
        	{
        	  pvr_notice.setVisibility(View.VISIBLE);
        	  
        	}
            else
            {
            	pvr_notice.setVisibility(View.INVISIBLE);
            }
    	};
    	
    };
    
    /**
     * 获取当前时间作为预录文件的名称
     * @return
     */
    private String getNowTime()
    {
    	String now_pvr="";
    	Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
    	int year_pvr=calendar.get(Calendar.YEAR);
		int month_pvr=calendar.get(Calendar.MONTH);
		int date_pvr=calendar.get(Calendar.DAY_OF_MONTH);
		int hour_pvr=calendar.get(Calendar.HOUR);
    	int min_pvr=calendar.get(Calendar.MINUTE);
    	int sec_pvr=calendar.get(Calendar.SECOND);
    	now_pvr=""+year_pvr+month_pvr+date_pvr+hour_pvr+min_pvr+sec_pvr;
		
		return now_pvr;
    }
    /**
	 * 预录节目定时结束
	 * @param duration
	 */
	private void pvrTimeSet(int duration)
	{
		
		t1.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				if(pvr_state == Pvr.pvr_doing)
				{
					
					 Message msg=new Message();
					 msg.what=1;
					 handlerBanner.sendMessage(msg);
					
				}
			}

		}, duration*1000);
	}
    
    private void saveInfo()
    {
    	
    	if(pvr_state == Pvr.pvr_doing)
		{
    		 JniLoad.PVRStop();
    		 pvr_state = Pvr.pvr_end;
			 isPvring = false;
			 pvr_notice.setText("");
	    }
    	if(isGetTuner)
		{
        	System.out.println("clear data CHANNEL_INFO_TB_URI");
        	isGetTuner = false;
        	JniLoad.Stop();
        	linearLayout_auto_notice.setVisibility(View.INVISIBLE);
        	
        	getContentResolver().delete(CHANNEL_TB_URI, null, null);
		}
        if(mapHD.size()>0)
        {
            SharedPreferences.Editor sharedata = getSharedPreferences(TV_SET, 0).edit();
            sharedata.putInt("channel_serviceId", ServiceId);
            sharedata.putInt("channel_num", nowChannel);
            sharedata.putInt("channel_type", channel_type_tempt);
            sharedata.putInt("eventId_pvr", eventId_pvr);
            sharedata.commit();
        }
        JniPlayer.stopProgram();
        unregisterReceiver(MsgReceiver);//取消注册
    }
    
    @Override
    protected void onPause()
    {
    	Log.i(TAG, "on pause");
        super.onPause();
        beforeExit();
         //System.exit(0);
    }
    
    @Override
    protected void onStop()
    {
	 	super.onStop();
	 	Log.i(TAG, "on stop");
//    	 	this.finish();
    }
    
    private void beforeExit(){
    	if(!isExit){
	    	JniLoad.DelAV();
	    	saveInfo();
		 	if(!isClickOrder)
		 	JniLoad.StopEPG();
		 	isExit = true;
    	}
    }
    
    public void handleIncomingCall(){
    	Log.i(TAG, "handleIncomingCall");
    	beforeExit();
	 	//send broadcast
		Intent intent = new Intent("lorent.stb.tv.phone");
        intent.putExtra("operate", "finish");
        sendBroadcast(intent);
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG, "on destory");
    }

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{
		// TODO Auto-generated method stub
		//System.out.println("main surface changed");
		//System.out.println(System.out.format("main surface changed, format:%d, %d, %d", format, width, height));
    	//JniPlayer.setVideoSurface(holder.getSurface());
	}

	public void surfaceCreated(SurfaceHolder holder)
	{
		// TODO Auto-generated method stub	
	}

	public void surfaceDestroyed(SurfaceHolder holder)
	{
		// TODO Auto-generated method stub	
	}

}

