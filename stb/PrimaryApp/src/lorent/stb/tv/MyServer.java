package lorent.stb.tv;

import java.text.SimpleDateFormat;

import com.qd.jni.DvbMsg;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class MyServer extends Service
{
   
   	private  Handler MyHandler = null;
	private DvbMsg dvbMsg=new DvbMsg(this);
	private static final String reciverMsg = "lorent.stb.tv.dvb";
	private static String TAG = "MyServer";
	
	private static final String PHONE_ACTION = "com.lorent.vovo";
	private BroadcastReceiver phoneRec = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String operate = intent.getStringExtra("operate");
			Log.i(TAG, "phoneRec operate = " + operate);
			if("callincoming".equals(operate)){
				if(DTVMain.myself != null){
					DTVMain.myself.handleIncomingCall();
				}
			}
		}
	};
	
	private synchronized void handlerMsg(Message msg)
	{

		Bundle bundle = msg.getData();
		Intent intent = new Intent();
        intent.setAction(reciverMsg);
        intent.putExtras(bundle);
        System.out.println("======MyServer.java  return msg=====");
        sendBroadcast(intent);
		
	}
	
	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}
    
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		MyHandler = new Handler()
		{
			public void handleMessage(Message msg)
			{
        		handlerMsg(msg);
        	}
		};
		Log.i(TAG, "registerReceiver phoneRec");
		this.registerReceiver(phoneRec, new IntentFilter(PHONE_ACTION));
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		// TODO Auto-generated method stub
		
		dvbMsg.msgInit();
		DTVMain.myself.init();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("service "+sDateFormat.format(new java.util.Date()));
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		
		Log.i(TAG, "UNregisterReceiver phoneRec");
		this.unregisterReceiver(phoneRec);
	}

	public Handler getMyHandler()
	{
		// TODO Auto-generated method stub
		return MyHandler;
	}
	
	
}
