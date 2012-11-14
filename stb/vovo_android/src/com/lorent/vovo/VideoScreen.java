package com.lorent.vovo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.lorent.LCCUtil;
import com.lorent.vovo.utils.DBProvider;
import com.lorent.vovo.utils.PlayAudio;

public class VideoScreen extends Activity{
	
	private int counttime = 0; //记录通话时间
	public static final Uri HISTORY_TB_URI = Uri
	.parse("content://com.lorent.lcc/history_tb");
	public static final Uri FRIEND_TB_URI = Uri
	.parse("content://com.lorent.lcc/friend_tb");
	private String callingNum = "";
	private boolean isRefuse = false; //是否拒接
	private static final int UNRECIEVE = 0;//未接
	private static final int RECIEVE = 1;  //已接
	private static final int REFUSE = 3;   //拒接
	private Button open_lock;
	private SurfaceView mSurfaceView;
	private Button recieve_time;
	private static final String TAG = "VideoScreen";
	private boolean isCall = false;
	private String min = "",ss = "";
	private View hangupBtn;
//	private ManageCenter mc = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vedio_screen);
		hangupBtn = this.findViewById(R.id.notice_handoff);
		Log.d(TAG, "onCreate()");
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
//				mc = ManageCenter.getInstance();
				IntentFilter mIntentFilter = new IntentFilter();
				mIntentFilter.addAction("lorent.lccUtil.hangup");
				mIntentFilter.addAction("lorent.lccUtil.connected");
				registerReceiver(video_msg,mIntentFilter);
				
			}}).start();
		init();
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isRefuse = false;
				insertHistory(DBProvider.CALL_IN, callingNum, UNRECIEVE);
				PlayAudio.play(VideoScreen.this);
			}}).start();

		hangupBtn.requestFocus();
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume");
		
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		
		super.onStop();
		new Thread(new Runnable(){

			@Override
			public void run() {
				if(isCall){
					LCCUtil.lccUtil.hangup();
					isCall = false;
				}
				PlayAudio.stop();
				
			}}).start();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		PlayAudio.stop();
		unregisterReceiver(video_msg);
	}
	private void init(){
		
//		open_lock = (Button)findViewById(R.id.open_lock);
//		open_lock.setOnTouchListener(new View.OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				int action = event.getAction();
//				if(action == MotionEvent.ACTION_DOWN){
//					open_lock.setBackgroundResource(R.drawable.lvd1600_sip_lock_valid);
//				}else if(action == MotionEvent.ACTION_UP){
//					
//					open_lock.setBackgroundResource(R.drawable.lvd1600_sip_lock);
//				}
//				return false;
//			}
//		});
		mSurfaceView = (SurfaceView)findViewById(R.id.myVideo);
		LCCUtil.lccUtil.setSurfaceView(mSurfaceView);
//		
//		SurfaceHolder holder = mSurfaceView.getHolder();
//		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//		holder.addCallback(new SurfaceHolder.Callback() {
//			@Override
//			public void surfaceDestroyed(SurfaceHolder holder) {
//				// TODO Auto-generated method stub
//				
//					LCCUtil.lccUtil.setSurfaceView(null);
//				
//		}
//			
//			@Override
//			public void surfaceCreated(SurfaceHolder holder) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void surfaceChanged(final SurfaceHolder holder, int format, int width,
//					int height) {
//				// TODO Auto-generated method stub
//				
//			new Thread(){public void run() {LCCUtil.setVideoWindowId(holder.getSurface());};}.start();	
//				
//				
//				
//			}
//		});
		
		recieve_time = (Button) findViewById(R.id.notice_recieve);
		recieve_time.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
					int action = event.getAction();
					if(!isCall)
					{
					if (action == MotionEvent.ACTION_DOWN) {
						recieve_time
								.setBackgroundResource(R.drawable.lvd1600_sip_ring_);
					} else if (action == MotionEvent.ACTION_UP) {
						recieve_time
								.setBackgroundResource(R.drawable.lvd1600_sip_calling);
					}
					}
				return false;
			}
		});
		if(LCCUtil.calltype.equals("in")){			
			callingNum = LCCUtil.callin_no;
		}else{
			callingNum = LCCUtil.callout_no;
		}
		recieve_time.setText(callingNum);
	}
	public void recieveClick(View v) {
		
			if(!isCall) LCCUtil.lccUtil.answer(); 
		
	}
	public void hangupClick(View v) {

		    isRefuse = true;
		    isCall = false;
			LCCUtil.lccUtil.hangup();
			this.finish();
	}
	
	// 通话计时
	private void set_timer() {

		new Thread(new Runnable() {
			int i = 0;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (isCall) {
					Message msg = new Message();
					msg.what = i;
					handlerTime.sendMessage(msg);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					i++;
					System.out.println("i=" + i);
				}
				
			}
		}).start();

	}
	private Handler handlerTime = new Handler() {
		public void handleMessage(Message msg) {

			int count = msg.what;
			counttime = count;
			int min_tempt = count / 60;
			int ss_tempt = count % 60;
			if (min_tempt < 10) {
				min = "0" + min_tempt;
			} else {
				min = min_tempt + "";
			}
			if (ss_tempt < 10) {
				ss = "0" + ss_tempt;
			} else {
				ss = ss_tempt + "";
			}
			recieve_time.setText(min + ":" + ss);
			
		};

	};
	public void OpenLock(View v) {
		
		connetiontFtp();
	}
	// open door
	private synchronized void connetiontFtp() {
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
//				String gateIp = LCCUtil.getCallIP();
//				String houseNo = LCCUtil.callin_no;
//				System.out.println("ip="+gateIp+",houseNo="+houseNo);
				
//				int result = mc.openDoor(gateIp, houseNo);
//				System.out.println("open door: result ="+result);
//				Message msg = new Message();
//				msg.what = result;
//				handler_opendoor.sendMessage(msg);
				
			}}).start();
		
	
	}
	private Handler handler_opendoor = new Handler(){
		public void handleMessage(Message msg) {
			int result = msg.what;
			if(result == 0)
			{
				open_lock.setBackgroundResource(R.drawable.lvd1600_sip_lock_);
				mHandler.removeMessages(1);
				mHandler.sendMessageDelayed(mHandler.obtainMessage(1), 1000);
			}
			else if(result == 1)
			{
				open_lock.setBackgroundResource(R.drawable.lvd1600_sip_lock);
			}
			
		};
	};
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			int result = msg.what;
			if(result == 1)
			{
				open_lock.setBackgroundResource(R.drawable.lvd1600_sip_lock);
			}
		};
	};
	private void updateHistoryState(int state) {
		int id = 0;
		Cursor c = getContentResolver().query(HISTORY_TB_URI,
				new String[] { "id" }, "lccno = ?",
				new String[] {callingNum}, "id desc");
		if (c.moveToFirst()) {
			id = c.getInt(c.getColumnIndex("id"));
		}
		c.close();
		ContentValues values = new ContentValues();
		values.put("state", state);
		getContentResolver().update(HISTORY_TB_URI, values, "id = ?",
				new String[] { id + "" });
	}
	
	private boolean queryHistoryState(){
		int id = 0;
		Cursor c = getContentResolver().query(HISTORY_TB_URI,
				new String[] { "id" }, "lccno = ?",
				new String[] {callingNum}, "id desc");
		if (c.moveToFirst()) {
			id = c.getInt(c.getColumnIndex("id"));
		}
		c.close();
	    c = getContentResolver().query(HISTORY_TB_URI,new String[]{"state"}, "id = ?", new String[]{id+""}, null);
	    int state = 0;
	    if(c.moveToFirst()){
	    	
	    	state = c.getInt(c.getColumnIndex("state"));
	    }
	    c.close();
	    if(state == RECIEVE)
	    {
	    	return false;
	    }
	    else{
	    	return true;
	    }
	
	}
	private void insertHistory(String type, String num, int state) {

		Cursor cursor = getContentResolver().query(HISTORY_TB_URI, null, "state = ?",
				new String[]{state+""}, null);
		if (cursor.getCount() >= 50) {		
			cursor.moveToNext();
			int firstid = cursor.getInt(0);
			cursor.close();

			getContentResolver().delete(HISTORY_TB_URI, "id = ?",
					new String[] { firstid + "" });
		}
		cursor.close();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date   curDate   =   new   Date(System.currentTimeMillis());
		System.out.println(sdf.format(curDate));
		String name = getNameFromLccno(num);
		
		
		ContentValues values = new ContentValues();
		values.put("calltype", type);
		values.put("calltime", sdf.format(new Date()));
		values.put("lccno", num);
		values.put("name", name);
		values.put("state", state);
		values.put("counttime", "00:00");
		getContentResolver().insert(HISTORY_TB_URI, values);

	}
    private String getNameFromLccno(String num) {
		
		Cursor cursor = getContentResolver().query(FRIEND_TB_URI,
				new String[] { "name" }, "lccno = ?", new String[] { num },
				null);
		String name = "unkown";
		while (cursor.moveToNext()) {
			name = cursor.getString(0);
		}
		cursor.close();
		return name;
	}
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    	
    	LCCUtil.lccUtil.hangup();
    }
    
    private void countCallTime(){
    	System.out.println("counttime = "+counttime);
    	if(counttime != 0)
    	{
    		new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					setCallTime();
				}}).start();
    	}
    }
    
    private void setCallTime(){
    	
    	/*String count_time_min = String.valueOf(counttime/60);
    	String count_time_sec = String.valueOf(counttime%60);
    	if(count_time_min.length() == 1)
    	{
    		count_time_min = "0"+count_time_min;
    	}
    	if(count_time_sec.length() == 1)
    	{
    		count_time_sec = "0"+count_time_sec;
    	}
        String count_time = count_time_min+":"+count_time_sec;*/
    	String count_time = recieve_time.getText().toString().trim();
        System.out.println("count_time = "+count_time);
    	int id = 0;
		Cursor c = getContentResolver().query(HISTORY_TB_URI,
				new String[] { "id" }, "lccno = ?",
				new String[] {callingNum}, "id desc");
		if (c.moveToFirst()) {
			id = c.getInt(c.getColumnIndex("id"));
		}
		c.close();
		ContentValues values = new ContentValues();
		values.put("counttime",count_time );
		getContentResolver().update(HISTORY_TB_URI, values, "id = ?",
				new String[] { id + "" });
    }
	private BroadcastReceiver video_msg = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if(action.equals("lorent.lccUtil.hangup"))
			{
				System.out.println("videoscreen: hangup");
				PlayAudio.stop();
				isCall = false;
				countCallTime();
				//判断是否拒接
				if(queryHistoryState()){
					if(isRefuse){
						//更新状态为拒接
						updateHistoryState(REFUSE);
					}
				}
				
				counttime = 0;
				VideoScreen.this.finish();
			}
			else if(action.equals("lorent.lccUtil.connected"))
			{
			    
				new Thread(){
					public void run() {
						PlayAudio.stop();
						updateHistoryState(RECIEVE);
						
					};}.start();
				recieve_time.setText("");
			    
			    isCall = true;
				set_timer();
			}
		}};
	  
	  

}
