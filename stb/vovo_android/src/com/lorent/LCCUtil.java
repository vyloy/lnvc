package com.lorent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;

import com.lorent.video.LntVideoWindow;
import com.lorent.vovo.LCCActivity;
import com.lorent.vovo.R;
import com.lorent.vovo.VideoScreen;
import com.lorent.vovo.utils.DBProvider;
import com.lorent.vovo.utils.LockScreenTool;


public class LCCUtil extends Service {
	
	public static final Uri HISTORY_TB_URI = Uri
	.parse("content://com.lorent.lcc/history_tb");
	
//	private ManageCenter m = ManageCenter.getInstance();
	private static String TAG = "LCCUtil";
	private static final String reciverMsg = "com.lorent.lcc.oper";
    
	public static final String REGISTEROKCB = "registerokcb";
	public static final String INCOMINGCB = "incomingcb";
	public static final String CONNECTEDCB = "connectedcb";
	public static final String HANGUPCB = "hangupcb";
	public static final String CALLERRORCB = "callerrorcb";
	public static final String UNINTCOMPLETECB = "unintcompletecb";
	public static final String SERVICE_START = "serverstart";

	public static final String ALARM_CALL = "alarmcall";
	public static LCCUtil lccUtil;

	public static boolean isInit = false;
	public boolean isRegister = false;
	public boolean needRegister = false;
	private static int call_index2;
	public static final int dnd_msg = 1;
	private LntVideoWindow window = new LntVideoWindow();
	
	// é»˜è®¤æ³¨å†Œä¿¡æ¯
	public String userName = "110";
	public String password  ="110";
	public String sipip = "192.168.0.1";
	public int port = 5090;
	public int width = 640;
	public int height = 480;
	public int bitrate = 256;
	
	public int call_state = 0;  // °²·Àºô½Ð   1 £¬ Ã»ÓÐ 0
	public static String callin_no = ""; //À´µçºÅÂë
	public String calltype = ""; //ºô½ø ºÍºô³ö  in ºÍ out 
	private BroadcastReceiver unregister_Rec = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			handler_unregist.sendEmptyMessage(1);
			
		}
		
	};
   private Handler handler_unregist = new Handler(){
	   public void handleMessage(Message msg) {
		   
		   unregister();
		   
//		   int result = m.regDev();
//		   System.out.println("dev register :   result = "+result);
		   Intent in = new Intent();
//		   in.putExtra("dev_register", result);
		   in.setAction("lorent.set.dev.register");
		   sendBroadcast(in);
	   };
   };
	private BroadcastReceiver networkMsg = new BroadcastReceiver() {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			boolean isBreak = arg1.getBooleanExtra(
					ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
			if (isBreak) {
				// ç½‘ç»œæ–­å¼€
				// setsipport(5090);
				// reg(userName, userPwd, sipip); // é»˜è®¤æ³¨å†Œå?
			}
		}

	};
	
	private BroadcastReceiver AlarmReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			System.out.println("---------------------------receiver alarm");
			
			call_state = 1;
			Intent in = new Intent(LCCUtil.this,LCCActivity.class);
			in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			startActivity(in);
//			sendMessage(ALARM_CALL, null);
			
		}};
	private BroadcastReceiver lvdsetMsg = new BroadcastReceiver() {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Bundle b = arg1.getExtras();
			for (String type : b.keySet()) {
				String[] args = b.getStringArray(type);
				if ("register".equals(type)) {
                    System.out.println("get register info args[0]="+args[0]);
                    register(args[0], args[0], args[1], 5090);
//					setsipport(5090);
//					reg(username, password, ip);
					
					/*if (isRegister) {
						System.out.println("isRegister = true");
						needRegister = true;
						unregister();
					} else {
						System.out.println("isRegister = false");
						register(args[0], args[0], args[1], 5090);
					}
					setvideosize(640,480);
					setvideobitrate(256);*/
					
				}
				else if("unregister".equals(type))
				{
					unregister();
				}
			}
		}

	};
	public BroadcastReceiver CancelDnd = new BroadcastReceiver() {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			
			String type_tempt = arg1.getStringExtra("type");
			if(type_tempt.equals("cancel"))
			{
			getContentResolver().delete(DBProvider.DND_TB_URI, null, null);
			System.out.println(" lvdSetCancelDnd:");
			handler_dnd.removeMessages(dnd_msg);
//			setDoNotDisturb(0);
			}
			else if(type_tempt.equals("start"))
			{
//				setDoNotDisturb(1);
			}
			else if(type_tempt.equals("end"))
			{
//				setDoNotDisturb(0);
			}
		}
	};
	
	public Handler handler_dnd = new Handler() {
		public void handleMessage(Message msg) {
//			setDoNotDisturb(0);
			System.out.println("È¡ÏûÃâ´òÈÅ  £º setDoNotDisturb(0)");
		};
	};
	// ÉèÖÃÃâ´òÈÅ
	public BroadcastReceiver MsgSet = new BroadcastReceiver() {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			 
			System.out.println(" msgset: ÉèÖÃÃâ´òÈÅ    setDoNotDisturb(1)");
//			setDoNotDisturb(1);
			long duration_delay = arg1.getLongExtra("duration",0);
			handler_dnd.sendMessageDelayed(handler_dnd.obtainMessage(dnd_msg),
					duration_delay);
		}

	};
//	private BroadcastReceiver AutoBr = new BroadcastReceiver(){
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			int data[] = intent.getIntArrayExtra("data");
//			if (data == null)
//				return;
//			switch (data[0]) {
//			
//			case ManageCenter.LVD_EVENT_REG_LCC:
//				
//				System.out.println("LCCUtil.java :   ManageCenter.LVD_EVENT_REG_LCC");
//				AutoRegister.sendEmptyMessage(0);
//				break;
//			default:
//				break;
//			}
//			
//		}
//	};
		
	private Handler AutoRegister = new Handler(){
		public void handleMessage(Message msg) {
			try{
//				userName = m.GetCfgInfo(ManageCenter.SYS_PARA_LOCALHOUSENO);
//				sipip = m.GetCfgInfo(ManageCenter.SYS_PARA_GATEIP);
//				ParamBean pb = m.getLccInfo();
//				port = pb.getS32SipServerPort();
//				width = pb.getS32SipWidth();
//				height = pb.getS32SipHeight();
//				bitrate = pb.getS32Stream();
				}catch(Exception e){
					System.out.println(" ParamBean : null ");
				}
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						/*
						 * javacallbackinit(LCCUtil.this); lccinit();
						 */
						setvideosize(width, height);
						setvideobitrate(bitrate);
						setsipport(5060);
						reg(userName, password, sipip); 
					}
				}).start();
		};
	};	
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println(TAG + " onCreate()+");
		Log.d(TAG, "onCreate");
		lccUtil = this;
		init();
//		registerReceiver(networkMsg, new IntentFilter(
//				"android.net.conn.CONNECTIVITY_CHANGE"));
		registerReceiver(lvdsetMsg,new IntentFilter("lorent.set.my.register"));
		registerReceiver(CancelDnd,new IntentFilter("lvd.set.cancel.dnd"));
		registerReceiver(AlarmReceiver,new IntentFilter("com.lorent.msg.Alarm"));
		registerReceiver(unregister_Rec, new IntentFilter("lorent.set.unregister"));
//		registerReceiver(AutoBr, new IntentFilter("ManageCenter.ACTION_COMMAND_MANAGECENTER"));
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		registerReceiver(MsgSet, new IntentFilter("android.alarm.demo.action"));
																				
		System.out.println(TAG + "  onStartCommand");
        
		try{
//		userName = m.GetCfgInfo(ManageCenter.SYS_PARA_LOCALHOUSENO);
//		sipip = m.GetCfgInfo(ManageCenter.SYS_PARA_GATEIP);
//		ParamBean pb = m.getLccInfo();
//		port = pb.getS32SipServerPort();
//		width = pb.getS32SipWidth();
//		height = pb.getS32SipHeight();
//		bitrate = pb.getS32Stream();
		}catch(Exception e){
			System.out.println(" ParamBean : null ");
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				/*
				 * javacallbackinit(LCCUtil.this); lccinit();
				 */
				setvideosize(width, height);
				setvideobitrate(bitrate);
				setsipport(5060);
				reg(userName, password, sipip); 
			}
		}).start();
		sendMessage(SERVICE_START, null);

		// è®¾ç½®å…æ‰“æ‰?
		setDnd();

		return super.onStartCommand(intent, flags, startId);
	}

	private void setDnd() {

		Cursor c = getContentResolver().query(DBProvider.DND_TB_URI, null,
				null, null, null);
		
		String begin_time = "";
		String end_time = "";
		int count = c.getCount();
		if (c.moveToFirst()) {
			
			begin_time = c.getString(c.getColumnIndex("begintime"));
			end_time = c.getString(c.getColumnIndex("endtime"));
		}

		c.close();
		if (count > 0) {
			long delayTime = 0;
			long startTime_begin = getLongTime(begin_time);
			long endTime_end = getLongTime(end_time);
			long nowTime = System.currentTimeMillis();
			if(nowTime < startTime_begin)
			{
				delayTime = startTime_begin;
			}
			else if(nowTime >= endTime_end)
			{
				delayTime = startTime_begin+24*60*60*1000;
			}
			else if(nowTime >= startTime_begin && nowTime <endTime_end)
			{
				delayTime = startTime_begin+24*60*60*1000;
				
				System.out.println("onstart : è¿›å…¥å…æ‰“æ‰°æ¨¡å¼?    setDoNotDisturb(1)");
//				setDoNotDisturb(1);
				long duration = endTime_end-nowTime;
				handler_dnd.sendMessageDelayed(handler_dnd.obtainMessage(dnd_msg),duration);
			}
			Intent intent = new Intent();
			intent.setAction("android.alarm.demo.action");
			long duration = getDuration(begin_time,end_time);
			intent.putExtra("duration", duration);
			PendingIntent pendingIntent;
			pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
			
			
			AlarmManager am = null;
			am = (AlarmManager) getSystemService(ALARM_SERVICE);
			
			// è®¾ç½®å‘¨æœŸï¼ï¼
			am.setRepeating(AlarmManager.RTC_WAKEUP, 
					delayTime, (24 * 60 * 60 * 1000), pendingIntent);
			System.out.println("lccUtil:  Ãâ´òÈÅÉèÖÃÍê³É ");
			
			

		} else {
			System.out.println("lccUtil:  Ã»ÓÐÃâ´òÈÅÉèÖÃ");
		}
	}
	
	private long getLongTime(String myTime){
		
		SimpleDateFormat sDate_date = new SimpleDateFormat("yyyy-MM-dd");
		String d = sDate_date.format(new Date());
		String begin = d+" " + myTime + ":00";
		
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sDate.parse(begin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//dateè½¬æˆæ¯«ç§’
		long beginTime = date.getTime();
		
		return beginTime; 
	}
	
	private long getDuration(String begintime, String endtime){
    	
    	SimpleDateFormat sDate = new SimpleDateFormat("HH:mm");
    	Date date = null;
		try {
			date = sDate.parse(begintime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//dateè½¬æˆæ¯«ç§’
    	long beginTime = date.getTime();
    	try {
			date = sDate.parse(endtime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	long endTime = date.getTime();
    	
    	return endTime-beginTime;
    }

	@Override
	public void onDestroy() {

		System.out.println(TAG + "  onDestroy");
		unregisterReceiver(networkMsg);
		unregisterReceiver(MsgSet);
		unregisterReceiver(lvdsetMsg);
		unregisterReceiver(CancelDnd);
		
		unregisterReceiver(AlarmReceiver);
		unregisterReceiver(unregister_Rec);
		
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}
    
	public void init() {
		javacallbackinit(this);
		lccinit();
		enableVideo(true);
		// xiexl add start
		String basepath = getFilesDir().getAbsolutePath();
		String configFile = basepath + "/linphone_config";
		try {
			/* ä¸‹é¢æ‹·è´packageä¸­çš„linphone_configåˆ?data/data/com.lorent/files/ */
			copyIfNotExist(R.raw.linphone_config, configFile);

			// File file = new File(configFile);
			// String path = file.getCanonicalPath();

//			lccinit(configFile, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			lccinit(null, null);
			e.printStackTrace();
		}
		// xiexl add end

		// lccinit();
		isInit = true;
	}

	public void setPreview(int oper) {
		Log.d(TAG, "Ô¤ÀÀ§ˆ");
		System.out.println(TAG + " ÉèÖÃÔ¤ÀÀ§ˆ");
		enablevideopreview(oper);
	}

	public void register(String username, String password, String serverIP, int serverPort) {
		userName = username;
		sipip = serverIP;
		saveRegisterInfo();
		Log.d(TAG, "register username = " + username + " & password = " + password + " & serverIP = " + serverIP + " & serverPort = " + serverPort);
		setsipport(5060);
		reg("sip:" + username + "@" + serverIP + ":" + serverPort, password, "sip:" + serverIP + ":" + serverPort);
	}

	private void saveRegisterInfo() {
//		m.SetCfgInfo(ManageCenter.SYS_PARA_LOCALHOUSENO, userName);
//		m.SetCfgInfo(ManageCenter.SYS_PARA_GATEIP, sipip);
		
//		m.saveCfg();
		/*ContentValues values = new ContentValues();
		values.put("username", userName);
		values.put("userpwd", userPwd);
		values.put("serviceip", sipip);
		
		Cursor c = getContentResolver().query(DBProvider.SIP_ACCOUNT_TB_URI, null, null,
				null, null);
		if (c.getCount() > 0) {
			getContentResolver().update(DBProvider.SIP_ACCOUNT_TB_URI, values, "id = ?",
					new String[] { "1" });
			c.close();
		} else {
			getContentResolver().insert(DBProvider.SIP_ACCOUNT_TB_URI, values);
			c.close();
		}*/
	}

	public void call2(String sipUrl) {
		Log.i(TAG, "call " + sipUrl);
		calltype = "out";
		call_index2 = call(sipUrl);
	}

	public void incometest() {
		Log.d(TAG, "cometest");
		System.out.println(TAG + " incometest()");
		incomingtest();
	}

//	public static String getCallIP() {
//		Log.d(TAG, "getLastCallIP()");
//		return getLastCallIP();
//	}

	public static void hangup() {
		Log.d(TAG, "¹Ò¶Ï");
		System.out.println(TAG + " ¹Ò¶Ï");
		hangup(call_index2);
	}

	public static void answer() {
		Log.d(TAG, "½ÓÌý");
		System.out.println(TAG + " ½ÓÌý");
		answer(call_index2);
	}

	public void unregister() {
		Log.d(TAG, "unregister");
		unreg();
	}

	public void javaregisterokcb(String nMsg) {

		Log.i(TAG, "javaregisterokcb : " + nMsg);
		if(nMsg.equals("ok"))
		{
			isRegister = true;
		}
		else if(nMsg.equals("fail"))
		{
			isRegister = false;
		}
		/*else if(nMsg.equals("unok"))
		{
			isRegister = false;
			if (needRegister) {
				needRegister = false;
				// register
				System.out.println("2 register  -----------------");
				register(userName, userPwd, sipip, 5090);
			}
		}*/
		Intent intent = new Intent();
		Bundle b = new Bundle();
		String[] userInfo = { nMsg, userName, password, sipip };
		b.putStringArray("register", userInfo);
		intent.setAction("com.lorent.lcc.register");
		intent.putExtras(b);
		sendBroadcast(intent);

		String[] args = new String[] { nMsg };
		sendMessage(REGISTEROKCB, args);

		Log.d(TAG, "javaregisterokcb message:" + nMsg);
	}

	public void javaincomingcb(String nMsg) {
		
		Log.i(TAG, "javaincomingcb  " + nMsg);
        
		//ÆÁÄ»¼ÓËø
		LockScreenTool.lockScreen(this);
		// ºÚÃûµ¥¹ýÂË
		Cursor c = this.getContentResolver().query(DBProvider.BLACKLIST_TB_URI,
				new String[] { "lccno" }, "lccno = ?", new String[] { nMsg },
				null);
		if (c.getCount() > 0) {
			c.close();
			return;
		} else {
			c.close();
			call_index2 = getanswercallindex();
//			String[] args = new String[] { nMsg };
            callin_no = nMsg;
            calltype = "in"; //ºô½ø
			Intent intent = new Intent(this, VideoScreen.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);

//			sendMessage(INCOMINGCB, args);
			Log.d(TAG, "javaincomingcb message:" + nMsg);
		}
		
	}

	public void javaconnectedcb(String nMsg) {
		Log.i(TAG, "javaconnectedcb  " + nMsg);
		Intent in = new Intent();
		in.setAction("lorent.lccUtil.connected");
		sendBroadcast(in);
		String[] args = new String[] { nMsg };
		
		sendMessage(CONNECTEDCB, args);
//		Log.d(TAG, "javaconnectedcb message:" + nMsg);
	}

	public void javahangupcb(String nMsg) {
		
		Log.i(TAG, "javahangupcb  " + nMsg);
		LockScreenTool.cancelLock();
		Intent in = new Intent();
		in.setAction("lorent.lccUtil.hangup");
		sendBroadcast(in);
		
		String[] args = new String[] { nMsg };
		sendMessage(HANGUPCB, args);
		
		if("in".equals(calltype) && checkState(callin_no))
		{
			/*boolean isStart = MusicServiceIsStart(mServiceList, noticeClassName); 
			if(!isStart)
			{
			    System.out.println("isStart ="+isStart);
				startService(new Intent(LCCActivity.this, NoticeServer.class));
				
			}*/
			Intent intent = new Intent();
			intent.setAction("my.lcc.notice");
			sendBroadcast(intent);
		}
	}

	private boolean checkState(String num){
		
		Cursor c = getContentResolver().query(HISTORY_TB_URI, null, "lccno = ? and calltype = ?", new String[]{num,DBProvider.CALL_IN} , null);
		if(c.moveToLast())
		{
			int state_tempt = c.getInt(c.getColumnIndex("state"));
			c.close();
			if(state_tempt == 0)
			{
			     return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			c.close();
			return false;
		}
	}

	/*
	 * must deal the called error(wrong sip address, or other error) xiexl add
	 * 2011.12.5 for call wrong number not hang up automatically
	 */
	public void javacallerrorcb(String nMsg) {
		String[] args = new String[] { nMsg };
		sendMessage(CALLERRORCB, args); // if error, hang up
		Log.i(TAG, "javacallerrorcb message:" + nMsg);
	}

	public void javaunintcompletecb(String nMsg) {
		Log.i(TAG, "javaunintcompletecb message:" + nMsg);
	}

	public synchronized void sendMessage(String type, String[] args) {

		// System.out.println("sendMessage  "+type+": "+args[0]);
		/*
		 * Log.d(TAG, "sendMessage " + type); if(LCCActivity.thislcc == null){
		 * Log.d(TAG, "sendMessage LCCActivity null");
		 * 
		 * } boolean flag = true; while(flag){ if(LCCActivity.thislcc != null){
		 * flag = false; } Log.d(TAG, "sendMessage wait LCCActivity start"); }
		 */
	/*	if (type.endsWith(INCOMINGCB) || type.equals(ALARM_CALL)) {
			boolean flag = true;
			while (flag) {
				if (LCCActivity.thislcc != null) {
					flag = false;
				}
			}
			Handler myHandler = LCCActivity.thislcc.getMyHandler();
			Message msg = myHandler.obtainMessage();
			Bundle b = new Bundle();
			b.putStringArray(type, args);
			msg.setData(b);
			myHandler.sendMessage(msg);
			Log.d(TAG, "sendMessage end");
		} else {

			if (LCCActivity.thislcc == null) {
				return;
			}

			Handler myHandler = LCCActivity.thislcc.getMyHandler();
			Message msg = myHandler.obtainMessage();
			Bundle b = new Bundle();
			b.putStringArray(type, args);
			msg.setData(b);
			myHandler.sendMessage(msg);
			Log.d(TAG, "sendMessage end");

		}*/

		/** ºô³ö×´Ì¬£º      µÄ²Ù×÷*/
		 Intent intent = new Intent(); 
		 Bundle b = new Bundle();
		 b.putStringArray(type, args);
		 intent.setAction(reciverMsg);
		 intent.putExtras(b);
		 System.out.println("======LCCUtil : sendMessage()=====");
		 sendBroadcast(intent);
		 
	}

	private void copyIfNotExist(int ressourceId, String target)
			throws IOException {
		File lFileToCopy = new File(target);
		if (!lFileToCopy.exists()) {
			copyFromPackage(ressourceId, lFileToCopy.getName());
		}
	}

	private void copyFromPackage(int ressourceId, String target)
			throws IOException {
		FileOutputStream lOutputStream = openFileOutput(target, 0);
		InputStream lInputStream = getResources().openRawResource(ressourceId);
		int readByte;
		byte[] buff = new byte[8048];
		while ((readByte = lInputStream.read(buff)) != -1) {
			lOutputStream.write(buff, 0, readByte);
		}
		lOutputStream.flush();
		lOutputStream.close();
		lInputStream.close();
	}

	public void setVideoSize(int width, int height){
		Log.i(TAG, "setVideoSize width = " + width + " & height = " + height);
		this.setvideosize(width, height);
	}
	
	public void setVideoBitrate(int bitrate){
		Log.i(TAG, "setVideoBitrate bitrate = " + bitrate);
		this.setvideobitrate(bitrate);
	}
	
	public void enableVideo(boolean enable){
		if(enable){
			this.enablevideo(1, window);
		}else{
			this.enablevideo(0, window);
		}
	}
	
	public void setSurfaceView(SurfaceView view){
		window.setSurfaceView(view);
	}
	//=======================================================
//	private native int lccinit(String configFilePath,
//			String defaultConfigFilePath);
	private native int lccinit();
	private native int uninit();

	private native int preprocess();

	private native int postprocess();

	private native int setsipport(int port);

	private native int reg(String username, String password, String proxy);

	private native int unreg();

	private native int call(String sip_url);

	private native static  int answer(int call_index);

	private native static int hangup(int call_index);

	private native int getwebcamlist();

	private native int setplaybacksoundcard();

	private native int setcapturesoundcard();

	private native int addFriend();

	private native int setvideosize(int width, int height);

	private native int setvideobitrate(int bitrate);

	private native int enablevideoselfview();
	
	private native int enablevideopreview(int enable);

	 private native int enablevideo(int enable,Object hwnd);
//	private native int enablevideo(int enable);

	private native int setwebcam();

	private native int getsoundcardlist();

	private native int getplaybacksoundcard();

	private native int getcapturesoundcard();

	private native int incomingtest();

	private native int javacallbackinit(Object obj);

	private native int getanswercallindex();

//	private native static String getLastCallIP();

//	private static native void setVideoWindowId(Object hwnd);

//	/*ÉèÖÃÃâ´òÈÅ   1 Ãâ´òÈÅ    0 È¡Ïû  */
//	private native void setDoNotDisturb(int isYes);

	static {
		System.loadLibrary("lcccore");
	}
    
}
