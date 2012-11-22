package com.lorent.vovo;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lorent.LCCUtil;
import com.lorent.vovo.bean.FriendBean;
import com.lorent.vovo.bean.RecordBean;
import com.lorent.vovo.bean.SetupBean;
import com.lorent.vovo.utils.DBProvider;
import com.lorent.vovo.utils.PlayAudio;
import com.phonecommand.PhoneCommander;

public class LCCActivity extends Activity {

	Dialog dialog = null;
	private int isRecordOrFriend = 0;// 标示从联系人还是从通话记录打开历史记录
    
	/**
	 * pageSize 每页显示联系人记录数据数目 pageCount 总页数 nowPage 当前页数 count 总数据数目
	 * 
	 **/
	private int count = 0;
	private int pageSize = 4;
	private int pageCount = 0;
	private int nowPage = 1;

	/**
	 * pageSize_black 每页显示黑名单记录数据数目 pageCount_black 总页数 nowPage_black 当前页数
	 * count_black 总数据数目
	 * 
	 **/
	private int count_black = 0;
	private int pageSize_black = 4;
	private int pageCount_black = 0;
	private int nowPage_black = 1;
	/**
	 * pageSize_record 每页显示通话记录数据数目 pageCount_record 总页数 nowPage_record 当前页数
	 * count_record 总数据数目
	 * 
	 **/
	private int count_record = 0;
	private int pageSize_record = 5;
	private int pageCount_record = 0;
	private int nowPage_record = 1;

	private int requestCode = 1;
	
	private Button friend_prv_btn,friend_next_btn;
	private Button history_prv_btn,history_next_btn;
	private Button black_prv_btn,black_next_btn;
	
	private boolean isFriendPrv = false;    
    private boolean isFriendNext = false;
    
	// private String name = "",num = ""; //添加黑名单时从联系人中选中的个人信息
	private boolean isRefresh = false; // 从联系人中选取个人信息标志
	private TextView state_page, state_page_black, state_page_record;
	private TextView name_and_num;
	private EditText name_or_num, name_or_num_black;
//	private ManageCenter mc = null;
	/*
	 * private Camera mCamera; private SurfaceView mpreview; private
	 * SurfaceHolder mSurfaceHolder;
	 */
	private int counttime = 0; // 通话时长
	private EditText username;
	private EditText serverip;
	private EditText password;
//	private TextView dev_state, account_state;
	private EditText port;
	private EditText videowidth;
	private EditText videoheight;
	private EditText bitrate;
	private boolean isCallOut = false; // 正在呼叫（呼出）

	private TextView menu_dail_txt, menu_hostory_txt, menu_friend_txt,
			menu_black_txt, menu_sipset_txt;
	private boolean isLimit = false;
	Timer timer = new Timer();
	private int msg_dnd = 13;
	private EditText start_time, end_time;
	private int mDelayTime = 0;
	private HashMap<String, Integer> times = new HashMap<String, Integer>();
	private static final int TIME_PICKER_ID = 14;
	boolean isTure = true; // 闀挎寜鍒犻櫎
	private boolean isDelClick = true;
	private boolean isDelHistoryListClick = true;
	private LinearLayout logo_layout;
	private Button call_btn, record_btn, list_btn, black_btn, register_btn,
			back_btn;
	private TextView set_title;
	private int index = 0;
	private TextView txt_register; // 娉ㄥ唽閫氱煡
	private ImageView imageView_ring;
	private AnimationDrawable animaition;
	private boolean isBlacklist = false; // 鏈夋病鏈夊姞鍏ラ粦鍚嶅崟
	private TextView out_connetion;

	private final String noticeClassName = "com.lorent.NoticeServer";

	private int timeout_msg = 1;

	public static final Uri FRIEND_TB_URI = Uri
			.parse("content://com.lorent.lcc/friend_tb");
	public static final Uri HISTORY_TB_URI = Uri
			.parse("content://com.lorent.lcc/history_tb");

	public boolean IS_MOTO_DEVICE = false;
	private View dialLayout;
	private View friendLayout;
	private View historyLayout;
	private View historylistLayout;
	private View blackLayout;
	private View registerLayout;
	private View // noticeCallin,
			noticeCallout,
			out_callLayout;

	private Button recieve_time;
	private boolean isInClick = true;
	private TextView out_calling;
	private String min = "00";
	private String ss = "00";

	private ListView friendLv;
	private ListView historyListLv;
	private ListView recordLv;
	private ListView blackLv;

	private Button img_Open;
	// private DatabaseHelper dbHelper;
	private EditText callEdit;
	private ArrayList<FriendBean> FriendCount = new ArrayList<FriendBean>();
	private List<Map<String, Object>> friendList = new ArrayList<Map<String, Object>>();
	private FriendAdapter friend_adapter;

	private List<Map<String, Object>> historyList = new ArrayList<Map<String, Object>>();
	private HistoryListAdapter historylist_adapter;

	private ArrayList<RecordBean> RecordCount = new ArrayList<RecordBean>();
	private List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
	private RecordAdapter record_adapter;

	private ArrayList<FriendBean> BlackCount = new ArrayList<FriendBean>();
	private List<Map<String, Object>> blackList = new ArrayList<Map<String, Object>>();
	private BlackAdapter black_adapter;

	public static final int ADD_FRIEND_DIALOG = 0;
	public static final int EDIT_FRIEND_DIALOG = 1;
	public static final int DELETE_FRIEND_DIALOG = 3;
	public static final int FRIEND_OPER_DIALOG = 4;
	public static final int CALL_IN_DIALOG = 5;
	public static final int CALL_OUT_DIALOG = 6;
	public static final int SETUP_DIALOG = 7;
	public static final int EXIT_DIALOG = 8;
	public static final int CALL_SHOW_INFO = 9;
	public static final int HISTORY_OPER_DIALOG = 10;
	public static final int DELETE_HISTORY_DIALOG = 11;
	public static final int DELETE_BLACK_DIALOG = 12;
	public static final int BLACK_OPER_DIALOG = 13;
	public static final int DELETE_HISTORYLIST_DIALOG = 14;
	public static final int ADD_BLACK_DIALOG = 15;
	public static final int DELETE_RECORD_DIALOG = 16;
	public static final int DELETE_ALL_RECORD = 17;
	public static final int ADD_FRIEND_FROM_RECORD = 18;
	public static final int DELETE_ALL_HISTORY_RECORD = 19;

	private static final String TAG = "LCCActivity";

	private int friendSelectPos;
	private int historySelectPos;
	private int blackSelectPos;
	private int recordSelectPos;

	private LCCUtil lccUtil = null;
	private SurfaceView myvideo;
	private SurfaceView mycameravideo;
	private boolean needSetVideoWindowServiceStart = false;
	private boolean isLive = false;
	private Map<String, Object> map = new HashMap<String, Object>();

	private static final String MY_DND = "my_dnd";
	public static final String PHONE_COMMAND_ACTION = "com.lorent.phonecommand";

	private Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			handlerMsg(msg);
		}
	};

	// public Handler getMyHandler() {
	// return myHandler;
	// }

	private static boolean isRegister = false;
	private boolean needRegister = false;
	private boolean isCall = false;

	// public static LCCActivity thislcc;

	List<ActivityManager.RunningServiceInfo> mServiceList;

	public void dialBtnClick(View v) {
		/*
		 * if(isCall){ // dialLayout.setVisibility(View.GONE); //
		 * incallLayout.setVisibility(View.VISIBLE); }else{
		 * dialLayout.setVisibility(View.VISIBLE); //
		 * incallLayout.setVisibility(View.GONE); }
		 */
		dialLayout.setVisibility(View.VISIBLE);
		logo_layout.setVisibility(View.GONE);
		friendLayout.setVisibility(View.GONE);
		historyLayout.setVisibility(View.GONE);
		// timeoutSet();
	}

	public void blackBtnClick() {
		// timeoutSet();
		
		isFriendPrv = false;
        isFriendNext = false;
        
		dialLayout.setVisibility(View.GONE);
		logo_layout.setVisibility(View.GONE);
		friendLayout.setVisibility(View.GONE);
		historyLayout.setVisibility(View.GONE);
		blackLayout.setVisibility(View.VISIBLE);
		registerLayout.setVisibility(View.GONE);
		historylistLayout.setVisibility(View.GONE);

		LoadBlackData();
		initValueBlack(0, pageSize_black);
		if (BlackCount.size() > 0) {
			nowPage_black = 1;
		} else {
			nowPage_black = 0;
		}
		
		black_adapter = new BlackAdapter(LCCActivity.this);
		blackLv.setAdapter(black_adapter);
		Message msg = new Message();
		msg.what = 2;
		mHandler.sendMessage(msg);
		
//		blackLv.setOnItemClickListener(new ListView.OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				LCCActivity.this.blackSelectPos = position;
//				// LCCActivity.this.showDialog(BLACK_OPER_DIALOG);
//			}
//		});

	}

	public void friendBtnClick(View v) {
		// timeoutSet();
		isFriendPrv = false;
        isFriendNext = false;
		historylistLayout.setVisibility(View.GONE);
			
		dialLayout.setVisibility(View.GONE);
		logo_layout.setVisibility(View.GONE);
		friendLayout.setVisibility(View.VISIBLE);
		historyLayout.setVisibility(View.GONE);
		blackLayout.setVisibility(View.GONE);
		registerLayout.setVisibility(View.GONE);

		LoadFriendData();
		initValue(0, pageSize);
		friend_adapter = new FriendAdapter(LCCActivity.this);
		friendLv.setAdapter(friend_adapter);
		if (FriendCount.size() > 0) {
			nowPage = 1;
		} else {
			nowPage = 0;
		}
//		String info = getResources().getString(R.string.state_page, nowPage,
//				pageCount);
//		state_page.setText(info);
		Message msg = new Message();
		msg.what = 1;
		mHandler.sendMessage(msg);
		// friendLv.setOnItemClickListener(new OnItemClickListener() {
		//
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// handler_msg.removeMessages(timeout_msg);
		// LCCActivity.this.friendSelectPos = position;
		//
		// /*
		// * Map<String, Object> map = friendList.get(friendSelectPos);
		// * String num = map.get("friendlccno").toString(); if
		// * (queryBlackByNo(num)) { isBlacklist = true; } else {
		// * isBlacklist = false; }
		// */
		// LCCActivity.this.showDialog(FRIEND_OPER_DIALOG);
		// }
		//
		// });

	}

	// 黑名单条件查询
	public void searchBlackClick(View v) {

		try {

			String search = name_or_num_black.getText().toString().trim();
			if (BlackCount.size() > 0) {
				BlackCount.clear();
			}
			FriendBean fb = null;
			Cursor cursor = LCCActivity.this.getContentResolver().query(
					DBProvider.BLACKLIST_TB_URI, null, "lccno like ? or name like ? ",
					new String[] { "%" + search + "%", "%" + search + "%" }, null);
			
			while (cursor.moveToNext()) {
				int id = cursor.getInt(0);
				String no = cursor.getString(cursor.getColumnIndex("lccno"));
				String name = cursor.getString(cursor.getColumnIndex("name"));

				fb = new FriendBean();

				fb.setId(id);
				fb.setLccno(no);
				fb.setName(name);
				BlackCount.add(fb);
			}

			cursor.close();
			count_black = BlackCount.size();
			if (count_black % pageSize_black == 0)
				pageCount_black = count_black / pageSize_black;
			else
				pageCount_black = count_black / pageSize_black + 1;
			if(nowPage_black > pageCount_black){
				nowPage_black--;
			}
            if(count_black > 0){
            	nowPage_black = 1;
            }else{
            	nowPage_black = 0;
            }
			initValueBlack(0, pageSize_black);
			black_adapter.notifyDataSetChanged();
			Message msg = new Message();
			msg.what = 2;
			mHandler.sendMessage(msg);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void add_black_click(View v) {

		handler_msg.removeMessages(timeout_msg);
		this.showDialog(ADD_BLACK_DIALOG);
	}

	public void add_friend_click(View v) {
		handler_msg.removeMessages(timeout_msg);
		this.showDialog(ADD_FRIEND_DIALOG);
	}

	// 联系人条件查询
	public void searchClick(View v) {

		try {
			String search = name_or_num.getText().toString().trim();
			if (FriendCount.size() > 0) {
				FriendCount.clear();
			}

			Cursor cursor = LCCActivity.this.getContentResolver().query(
					FRIEND_TB_URI, null, "lccno like ? or name like ? ",
					new String[] { "%" + search + "%","%" + search + "%" }, null);
			System.out.println("friendCount :cursor.getcount()="
					+ cursor.getCount());
			while (cursor.moveToNext()) {

				String no = cursor.getString(2);
				String name = cursor.getString(1);
				
				FriendBean fb = new FriendBean();
				fb.setId(cursor.getInt(0));
				fb.setLccno(no);
				fb.setName(name);
				FriendCount.add(fb);
			}

			cursor.close();
			count = FriendCount.size();
			if (count % pageSize == 0)
				pageCount = count / pageSize;
			else
				pageCount = count / pageSize + 1;
			
			if (nowPage > pageCount) {
				nowPage--;
			}
			 if(count > 0){
	            	nowPage = 1;
	         }else{
	            	nowPage = 0;
	         }
			initValue(0, pageSize);
			
			friend_adapter.notifyDataSetChanged();
			Message msg = new Message();
			msg.what = 1;
			mHandler.sendMessage(msg);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void record_nextPage() {
		if (nowPage_record < pageCount_record) {

			nowPage_record++;
			initValueRecord(nowPage_record * pageSize_record - pageSize_record,
					nowPage_record * pageSize_record);
			record_adapter.notifyDataSetChanged();
			Message msg = new Message();
			msg.what = 3;
			mHandler.sendMessage(msg);
		}
	}

	private void record_backPage() {
		if (nowPage_record > 1) {
			nowPage_record--;
			initValueRecord(nowPage_record * pageSize_record - pageSize_record,
					nowPage_record * pageSize_record);
			record_adapter.notifyDataSetChanged();
			Message msg = new Message();
			msg.what = 3;
			mHandler.sendMessage(msg);
		}
	}

	private void black_nextPage() {
		if (nowPage_black < pageCount_black) {

			nowPage_black++;
			initValueBlack(nowPage_black * pageSize_black - pageSize_black,
					nowPage_black * pageSize_black);
			black_adapter.notifyDataSetChanged();
			Message msg = new Message();
			msg.what = 2;
			mHandler.sendMessage(msg);
		}
	}

	private void black_backPage() {
		if (nowPage_black > 1) {
			nowPage_black--;
			initValueBlack(nowPage_black * pageSize_black - pageSize_black,
					nowPage_black * pageSize_black);
			black_adapter.notifyDataSetChanged();
			Message msg = new Message();
			msg.what = 2;
			mHandler.sendMessage(msg);
		}

	}

	private void nextPage() {
		if (nowPage < pageCount) {

			nowPage++;
			initValue(nowPage * pageSize - pageSize, nowPage * pageSize);
			friend_adapter.notifyDataSetChanged();
			Message msg = new Message();
			msg.what = 1;
			mHandler.sendMessage(msg);
		}
	}

	private void backPage() {
		if (nowPage > 1) {
			nowPage--;
			initValue(nowPage * pageSize - pageSize, nowPage * pageSize);
			friend_adapter.notifyDataSetChanged();
			Message msg = new Message();
			msg.what = 1;
			mHandler.sendMessage(msg);
		}
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			int result = msg.what;
			switch (result) {
			case 1:
				String info = getResources().getString(R.string.state_page,
						nowPage, pageCount);
				state_page.setText(info);
				if(nowPage == 1 || nowPage == 0){
					isFriendPrv = true;
					friend_prv_btn.setBackgroundResource(R.drawable.prv_invalid);
				}else{
					isFriendPrv = false;
					friend_prv_btn.setBackgroundResource(R.drawable.pre_page_selector);
				}
				if(nowPage == pageCount || pageCount == 0){
					isFriendNext  = true;
					friend_next_btn.setBackgroundResource(R.drawable.next_invalid);
				}else{
					isFriendNext = false;
					friend_next_btn.setBackgroundResource(R.drawable.next_page_selector);
				} 
				break;
			case 2:
				
				String info_black = getResources().getString(
						R.string.state_page, nowPage_black, pageCount_black);
				state_page_black.setText(info_black);
				if(nowPage_black == 1 || nowPage_black == 0){
					isFriendPrv = true;
					black_prv_btn.setBackgroundResource(R.drawable.prv_invalid);
				}else{
					isFriendPrv = false;
					black_prv_btn.setBackgroundResource(R.drawable.pre_page_selector);
				}
				if(nowPage_black == pageCount_black || pageCount_black == 0){
					isFriendNext  = true;
					black_next_btn.setBackgroundResource(R.drawable.next_invalid);
				}else{
					isFriendNext = false;
					black_next_btn.setBackgroundResource(R.drawable.next_page_selector);
				} 
				
				break;
			case 3:
				String info_record = getResources().getString(
						R.string.state_page, nowPage_record, pageCount_record);
				state_page_record.setText(info_record);
				if(nowPage_record == 1 || nowPage_record == 0){
					
					isFriendPrv = true;
					history_prv_btn.setBackgroundResource(R.drawable.prv_invalid);
				}else{
					
					isFriendPrv = false;
					history_prv_btn.setBackgroundResource(R.drawable.prv);
				}
				if(nowPage_record == pageCount_record || pageCount_record == 0 ){
					
					isFriendNext  = true;
					history_next_btn.setBackgroundResource(R.drawable.next_invalid);
				}else{
					
					isFriendNext = false;
					history_next_btn.setBackgroundResource(R.drawable.next);
				} 
				break;
			}
		};
	};

	/**
	 * 
	 * 通话记录数据分页加载
	 * 
	 * @param pageStart
	 *            起始数
	 * @param pageEnd
	 *            结束数
	 * 
	 **/
	public void initValueRecord(int pageStart, int pageEnd) {
		if (recordList != null)
			recordList.clear();
		Map<String, Object> map = null;
		for (int i = pageStart; i < pageEnd; i++) {
			if (i < count_record) {

				map = new HashMap<String, Object>();
				map.put("id", RecordCount.get(i).getId());
				map.put("recordlccno", RecordCount.get(i).getLccno());
				map.put("recordname", RecordCount.get(i).getName());
				map.put("recordtype", RecordCount.get(i).getType());
				map.put("recordtime", RecordCount.get(i).getCalltime());
				recordList.add(map);

			} else {
				break;
			}
		}
		System.out.println("initValue: recordList.size() = "
				+ recordList.size());

		

	}

	/**
	 * 
	 * 联系人数据分页加载
	 * 
	 * @param pageStart
	 *            起始数
	 * @param pageEnd
	 *            结束数
	 * 
	 **/
	public void initValue(int pageStart, int pageEnd) {
		if (friendList != null)
			friendList.clear();
		Map<String, Object> map = null;
		for (int i = pageStart; i < pageEnd; i++) {
			if (i < count) {

				map = new HashMap<String, Object>();
				map.put("id", FriendCount.get(i).getId());
				map.put("friendlccno", FriendCount.get(i).getLccno());
				map.put("friendadress", FriendCount.get(i).getName());

				friendList.add(map);

			} else {
				break;
			}
		}
		System.out.println("initValue: friendList.size() = "
				+ friendList.size());

		// friend_adapter.notifyDataSetChanged();

	}

	/**
	 * 
	 * 黑名单数据分页加载
	 * 
	 * @param pageStart
	 *            起始数
	 * @param pageEnd
	 *            结束数
	 * 
	 **/
	public void initValueBlack(int pageStart, int pageEnd) {
		if (blackList != null)
			blackList.clear();
		Map<String, Object> map = null;
		for (int i = pageStart; i < pageEnd; i++) {
			if (i < count_black) {

				map = new HashMap<String, Object>();
				map.put("blackid", BlackCount.get(i).getId());
				map.put("blacklccno", BlackCount.get(i).getLccno());
				map.put("blackname", BlackCount.get(i).getName());
				blackList.add(map);

			} else {
				break;
			}
		}

		// friend_adapter.notifyDataSetChanged();

	}

	private FriendAdapter createFriendLvData() {
		// friendList = new ArrayList<Map<String, Object>>();
		if (friendList.size() > 0)
			friendList.clear();
		Map<String, Object> map = null;

		Cursor cursor = LCCActivity.this.getContentResolver().query(
				FRIEND_TB_URI, null, null, null, null);
		while (cursor.moveToNext()) {
			String no = cursor.getString(2);
			String adress = cursor.getString(1);
			map = new HashMap<String, Object>();
			map.put("id", cursor.getInt(0));
			map.put("friendlccno", no);
			map.put("friendadress", adress);

			if (queryBlackByNo(no)) {
				map.put("friendblack", 1);
			} else {
				map.put("friendblack", 0);
			}
			friendList.add(map);
		}
		cursor.close();
		FriendAdapter fa = new FriendAdapter(LCCActivity.this);
		return fa;
	}

	public void historyListBackClick(View v) {
		if (isRecordOrFriend == 1) {

			historylistLayout.setVisibility(View.GONE);
			friendLayout.setVisibility(View.VISIBLE);
		} else if (isRecordOrFriend == 2) {
			historylistLayout.setVisibility(View.GONE);
			historyLayout.setVisibility(View.VISIBLE);
		}
	}

	private void LoadFriendData() {

		if (FriendCount.size() > 0) {
			FriendCount.clear();
		}

		Cursor cursor = LCCActivity.this.getContentResolver().query(
				FRIEND_TB_URI, null, null, null, null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String no = cursor.getString(2);
			String name = cursor.getString(1);

			FriendBean fb = new FriendBean();
			fb.setId(id);
			fb.setName(name);
			fb.setLccno(no);
			FriendCount.add(fb);

		}

		cursor.close();
		count = FriendCount.size();
		if (count % pageSize == 0)
			pageCount = count / pageSize;
		else
			pageCount = count / pageSize + 1;

	}

	private void LoadRecordData() {

		if (RecordCount.size() > 0) {
			RecordCount.clear();
		}

		Cursor cursor = LCCActivity.this.getContentResolver().query(
				HISTORY_TB_URI, null, null, null, "id desc");
		while (cursor.moveToNext()) {

			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String no = cursor.getString(cursor.getColumnIndex("lccno"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String calltime = cursor.getString(cursor
					.getColumnIndex("counttime"));
			int state = cursor.getInt(cursor.getColumnIndex("state"));

			RecordBean fb = new RecordBean();
			fb.setId(id);
			fb.setName(name);
			fb.setLccno(no);
			fb.setCalltime(calltime);
			Bitmap type = null;
			if (state == 1) {
				// 已接
				type = BitmapFactory.decodeResource(getResources(),
						R.drawable.lvd1610_call_in);
			} else if (state == 0) {
				// 未接
				type = BitmapFactory.decodeResource(getResources(),
						R.drawable.lvd1610_unrecieve);
			} else if (state == 2) {
				// 拨出
				type = BitmapFactory.decodeResource(getResources(),
						R.drawable.lvd1610_call_out);
			} else if (state == 3) {
				// 拒接
				type = BitmapFactory.decodeResource(getResources(),
						R.drawable.lvd1610_refuse);
			}
			fb.setType(type);
			RecordCount.add(fb);

		}

		cursor.close();
		count_record = RecordCount.size();
		if (count_record % pageSize_record == 0)
			pageCount_record = count_record / pageSize_record;
		else
			pageCount_record = count_record / pageSize_record + 1;

	}

	private void LoadBlackData() {

		if (BlackCount.size() > 0) {
			BlackCount.clear();
		}
		FriendBean fb = null;
		Cursor cursor = LCCActivity.this.getContentResolver().query(
				DBProvider.BLACKLIST_TB_URI, null, null, null, null);
		while (cursor.moveToNext()) {

			int id = cursor.getInt(0);
			String no = cursor.getString(1);
			String name = cursor.getString(2);
			fb = new FriendBean();
			fb.setId(id);
			fb.setLccno(no);
			fb.setName(name);
			BlackCount.add(fb);

		}

		cursor.close();
		count_black = BlackCount.size();
		if (count_black % pageSize_black == 0)
			pageCount_black = count_black / pageSize_black;
		else
			pageCount_black = count_black / pageSize_black + 1;
	}

	private boolean queryBlackByNo(String no) {
		Cursor c = getContentResolver().query(DBProvider.BLACKLIST_TB_URI,
				null, "lccno = ?", new String[] { no }, null);
		if (c.getCount() > 0) {
			c.close();
			return true;
		} else {
			c.close();
			return false;
		}
	}

	private boolean queryFriendByNo(String no) {

		Cursor c = getContentResolver().query(FRIEND_TB_URI, null, "lccno = ?",
				new String[] { no }, null);
		if (c.getCount() > 0) {
			c.close();
			return true;
		} else {
			c.close();
			return false;
		}
	}

	public void historyBtnClick(View v) {
		// timeoutSet();
        
		isFriendPrv = false;
        isFriendNext = false;
		logo_layout.setVisibility(View.GONE);
		dialLayout.setVisibility(View.GONE);
		friendLayout.setVisibility(View.GONE);
		
		historyLayout.setVisibility(View.VISIBLE);
		
		blackLayout.setVisibility(View.GONE);
		registerLayout.setVisibility(View.GONE);
		historylistLayout.setVisibility(View.GONE);

		LoadRecordData();
		initValueRecord(0, pageSize_record);
		
		record_adapter = new RecordAdapter(LCCActivity.this);
		recordLv.setAdapter(record_adapter);
		
		if (RecordCount.size() > 0) {
			nowPage_record = 1;
		} else {
			nowPage_record = 0;
		}
		Message msg = new Message();
		msg.what = 3;
		mHandler.sendMessage(msg);
		

	}

	/** 清除所有通话记录 */
	public void clearRecordClick(View v) {
		
		showDialog(DELETE_ALL_RECORD);
		
	}

	/**
	 * 加载通话记录
	 * 
	 * @return
	 */
	private void loadHistoryData(List<Map<String, Object>> list,
			SimpleAdapter adapter, ListView listView, int state) {

		if (list.size() > 0)
			list.clear();
		Map<String, Object> map_history = null;
		Cursor cursor = getContentResolver().query(HISTORY_TB_URI, null,
				"state = ?", new String[] { state + "" }, "id desc");
		while (cursor.moveToNext()) {
			map_history = new HashMap<String, Object>();
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String lccno = cursor.getString(cursor.getColumnIndex("lccno"));
			String time = cursor.getString(cursor.getColumnIndex("calltime"));
			map_history.put("historyid", id);
			map_history.put("historylccno", lccno);
			map_history.put("historytime", time);

			list.add(map_history);
		}
		adapter = new SimpleAdapter(LCCActivity.this, list,
				R.layout.historylist, new String[] { "historytime",
						"historylccno" }, new int[] { R.id.historytime,
						R.id.historynum });

		listView.setAdapter(adapter);

		cursor.close();
	}

	// 挂断
	public void hangupClick(View v) {

		hangup();
	}

	private void hangup() {
		/*
		 * if (lccUtil != null) lccUtil.hangup();
		 */
		lccUtil.hangup();
	}

	public void dialNumClick(View v) {

		if (v.getId() == R.id.callBtn) {
			handler_msg.removeMessages(timeout_msg);
		} else {
			// timeoutSet();
		}
		switch (v.getId()) {
		case R.id.no0Btn:
			callEdit.getText().append("0");
			break;
		case R.id.no1Btn:
			callEdit.getText().append("1");
			break;
		case R.id.no2Btn:
			callEdit.getText().append("2");
			break;
		case R.id.no3Btn:
			callEdit.getText().append("3");
			break;
		case R.id.no4Btn:
			callEdit.getText().append("4");
			break;
		case R.id.no5Btn:
			callEdit.getText().append("5");
			break;
		case R.id.no6Btn:
			callEdit.getText().append("6");
			break;
		case R.id.no7Btn:
			callEdit.getText().append("7");
			break;
		case R.id.no8Btn:
			callEdit.getText().append("8");
			break;
		case R.id.no9Btn:
			callEdit.getText().append("9");
			break;
		case R.id.nostarBtn:

			// this.showDialog(SETUP_DIALOG);
			callEdit.getText().append("*");

			break;
		case R.id.noshotBtn:
			// callEdit.setText("");
			callEdit.getText().append("#");

			// lccUtil.incometest();
			// if(openVideo){
			// lccUtil.setVideo(0);
			// openVideo = false;
			// }else{
			// lccUtil.setVideo(1);
			// openVideo = true;
			// }
			break;
		case R.id.del_number:
			if (callEdit.getText().toString().length() > 0) {
				String s = callEdit.getText().toString();
				callEdit.getText().delete(s.length() - 1, s.length());
			}
			break;
		case R.id.callBtn:

//			System.out.println("dialNumClick():callBtn");

			doCall(callEdit.getText().toString());
			/*
			 * if(!isRegister){ Toast.makeText(this.getApplicationContext(),
			 * R.string.isnotregister, 2000); }else{
			 * 
			 * doCall(callEdit.getText().toString()); }
			 */
			break;

		}

	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		setIntent(intent);
	}

	/**
	 * 呼叫
	 * 
	 * @param callnum
	 */
	private void doCall(String callnum) {
		if (LCCUtil.lccUtil.isRegister) {
			if (callnum.length() == 0) {

//				String magCenter_houseno_tempt = null;
//				try {
////					magCenter_houseno_tempt = mc
////							.GetCfgInfo(ManageCenter.SYS_PARA_CENTERHOUSENO);
//				} catch (Exception e) {
//
//					Toast.makeText(this, "管理中心号码没有设置！", Toast.LENGTH_SHORT)
//							.show();
//					return;
//				}
//
//				map.put("calltype", "out");
//				map.put("outcallnum", magCenter_houseno_tempt);
//				insertHistory(DBProvider.CALL_OUT, magCenter_houseno_tempt, 2);
//				lccUtil.call2(magCenter_houseno_tempt);
//
//				callout(magCenter_houseno_tempt);

				return;
			} else if (callnum.equals(LCCUtil.lccUtil.userName)) {
				Toast.makeText(this, R.string.localnum, Toast.LENGTH_SHORT)
						.show();

			} else {

				isLimit = true;
				Log.i(TAG, "docall out:" + callnum);
				map.put("calltype", "out");
				map.put("outcallnum", callnum);
				insertHistory(DBProvider.CALL_OUT, callnum, 2);
				isCallOut = true;
				callout(callnum);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lccUtil.call2(callnum);
			}

//			callEdit.setText("");

		} else {
			Toast.makeText(this, R.string.isregister, Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void callout(String callnum) {


//		findViewById(R.id.mainlayout_right).setVisibility(View.GONE);
//		// myvideo.setVisibility(View.VISIBLE);
//		out_callLayout.setVisibility(View.VISIBLE);
//		// out_calling.setText(getString(R.string.call_out) + callnum);
//		out_calling.setText(callnum);
//		// noticeCallin.setVisibility(View.GONE);
//		noticeCallout.setVisibility(View.GONE);
//
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				/** 监听系统有没有休眠 */
//
//			}
//		}).start();
		Intent intent = new Intent(this, VideoScreen.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		
	}

	public void videoClick(View v) {
		/*
		 * View left = findViewById(R.id.mainlayout_right);
		 * 
		 * if (View.GONE == left.getVisibility()) {
		 * findViewById(R.id.mainlayout_right).setVisibility(View.VISIBLE);
		 * FrameLayout.LayoutParams lp1 = (FrameLayout.LayoutParams) myvideo
		 * .getLayoutParams(); lp1.leftMargin = 2; lp1.topMargin = 2; lp1.width
		 * = 716; lp1.height = 600;
		 * 
		 * myvideo.setLayoutParams(lp1); if (isCall) {
		 * noticeFullLayout.setVisibility(View.VISIBLE); }
		 * 
		 * }
		 */

	}

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		/*
		 * requestWindowFeature(Window.FEATURE_NO_TITLE);
		 * getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 * WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 */

		setContentView(R.layout.main_lcc);
		Log.d(TAG, "onCreate");
		txt_register = (TextView) findViewById(R.id.register_info_notice);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				registerReceiver(lccMessage1, new IntentFilter(
						"com.lorent.lcc.register"));
				registerReceiver(OutReceiver, new IntentFilter(
						"com.lorent.lcc.oper"));

				ActivityManager mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

				mServiceList = mActivityManager.getRunningServices(30);

//				mc = ManageCenter.getInstance();
				calendar = Calendar.getInstance();

				am = (AlarmManager) getSystemService(ALARM_SERVICE);
			}
		}).start();

		// thislcc = this;
		init_on_create();
		phoneCommandReceiver = new PhoneCommandReceiver();

	}

	private void startRingAnim() {
		// 鏄惁浠呬粎鍚姩涓?锛?
		animaition.setOneShot(false);
		if (animaition.isRunning()) {
			animaition.stop();
		}

		animaition.start();

	}

	TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub

			System.out.println(hourOfDay + "-" + minute);
			String hour = hourOfDay + "";
			String min = minute + "";
			if (hour.length() == 1) {
				hour = "0" + hour;
			}
			if (min.length() == 1) {
				min = "0" + min;
			}

			if (start_time.hasFocus()) {
				start_time.setText(hour + ":" + min);
				times.put("start_hour", hourOfDay);
				times.put("start_min", minute);

			} else if (end_time.hasFocus()) {
				end_time.setText(hour + ":" + min);
				times.put("end_hour", hourOfDay);
				times.put("end_min", minute);
			}
		}

	};
	class PrvOnTouchListener implements View.OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if(isFriendPrv)return false;
			int action = event.getAction();
			if(action == MotionEvent.ACTION_DOWN){
				
				v.setBackgroundResource(R.drawable.prv_);
			}else if(action == MotionEvent.ACTION_UP){
				v.setBackgroundResource(R.drawable.prv);
			}
			
			return false;
		}}
    class NextOnTouchListener implements View.OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if(isFriendNext) return false;
			int action  = event.getAction();
			if(action == MotionEvent.ACTION_DOWN){
				v.setBackgroundResource(R.drawable.next_);
			}else if(action == MotionEvent.ACTION_UP){
				v.setBackgroundResource(R.drawable.next);
			}
			return false;
		}}
	private void init_on_create() {

		// mpreview = (SurfaceView) this.findViewById(R.id.camera_preview);
		// mSurfaceHolder = mpreview.getHolder();
		// mSurfaceHolder.addCallback(this);
		// mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        
		friend_prv_btn = (Button)findViewById(R.id.friend_page_prv_btn);
        friend_next_btn = (Button)findViewById(R.id.friend_page_next_btn);
        history_prv_btn = (Button)findViewById(R.id.history_page_prv_btn);
        history_next_btn = (Button)findViewById(R.id.history_page_next_btn);
        black_prv_btn = (Button)findViewById(R.id.black_page_prv_btn);
        black_next_btn = (Button)findViewById(R.id.black_page_next_btn);
        
        friend_prv_btn.setOnTouchListener(new PrvOnTouchListener());
        friend_next_btn.setOnTouchListener(new NextOnTouchListener());
        
        history_prv_btn.setOnTouchListener(new PrvOnTouchListener());
        history_next_btn.setOnTouchListener(new NextOnTouchListener());
        
        black_prv_btn.setOnTouchListener(new PrvOnTouchListener());
        black_next_btn.setOnTouchListener(new NextOnTouchListener());
        
		
		state_page = (TextView) findViewById(R.id.state_page);
		state_page_black = (TextView) findViewById(R.id.state_page_black);
		state_page_record = (TextView) findViewById(R.id.state_page_record);

		name_and_num = (TextView) findViewById(R.id.name_and_num);
		name_or_num = (EditText) findViewById(R.id.search_txt);
		name_or_num_black = (EditText) findViewById(R.id.search_black_txt);

		menu_dail_txt = (TextView) findViewById(R.id.dialBtn_txt);
		menu_hostory_txt = (TextView) findViewById(R.id.historyBtn_txt);
		menu_friend_txt = (TextView) findViewById(R.id.friendBtn_txt);
		menu_black_txt = (TextView) findViewById(R.id.blackList_txt);
		menu_sipset_txt = (TextView) findViewById(R.id.sipSet_txt);

		username = (EditText) findViewById(R.id.username);
		serverip = (EditText) findViewById(R.id.serverip);
		password = (EditText) findViewById(R.id.password);
//		dev_state = (TextView) findViewById(R.id.dev_state);
//		account_state = (TextView) findViewById(R.id.account_state);

		port = (EditText) findViewById(R.id.port);
		videowidth = (EditText) findViewById(R.id.videowidth);
		videoheight = (EditText) findViewById(R.id.videoheight);
		bitrate = (EditText) findViewById(R.id.bitrate);

		/*
		 * start_time = (EditText) findViewById(R.id.start_time); end_time =
		 * (EditText) findViewById(R.id.end_time);
		 * start_time.setInputType(InputType.TYPE_NULL);
		 * end_time.setInputType(InputType.TYPE_NULL);
		 * 
		 * start_time.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub showDialog(TIME_PICKER_ID); } });
		 * end_time.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub showDialog(TIME_PICKER_ID); } });
		 */
		call_btn = (Button) findViewById(R.id.dialBtn);
		record_btn = (Button) findViewById(R.id.historyBtn);
		list_btn = (Button) findViewById(R.id.friendBtn);
		black_btn = (Button) findViewById(R.id.blackList);
		register_btn = (Button) findViewById(R.id.sipSet);
		back_btn = (Button) findViewById(R.id.backBtn);
//		call_btn.setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//				int action = event.getAction();
//				if (action == MotionEvent.ACTION_DOWN) {
//					/*
//					 * call_btn
//					 * .setBackgroundResource(R.drawable.lvd1600_sip_menu_02_);
//					 * record_btn
//					 * .setBackgroundResource(R.drawable.lvd1600_sip_menu_03);
//					 * list_btn
//					 * .setBackgroundResource(R.drawable.lvd1600_sip_menu_04);
//					 * black_btn
//					 * .setBackgroundResource(R.drawable.lvd1600_sip_menu_05);
//					 * register_btn
//					 * .setBackgroundResource(R.drawable.lvd1600_sip_menu_06);
//					 * back_btn
//					 * .setBackgroundResource(R.drawable.lvd1600_sip_menu_07);
//					 */
//
//					callBtnClick();
//
//				}
//
//				return false;
//			}
//		});
//		record_btn.setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//				int action = event.getAction();
//				if (action == MotionEvent.ACTION_DOWN) {
//					historyBtnClick();
//				}
//				return false;
//			}
//		});
//		list_btn.setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//				int action = event.getAction();
//				if (action == MotionEvent.ACTION_DOWN) {
//					friendBtnClick();
//				}
//				return false;
//			}
//		});
//		black_btn.setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//				int action = event.getAction();
//				if (action == MotionEvent.ACTION_DOWN) {
//					blacklistBtnClick();
//				}
//				return false;
//			}
//		});

//		register_btn.setOnTouchListener(new OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//				int action = event.getAction();
//				if (action == MotionEvent.ACTION_DOWN) {
//					setupClick();
//					// setDnd();
//				}
//				return false;
//			}
//		});
//		register_btn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				setupClick();
//			}
//		});
		

		
		/*
		 * back_btn.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub
		 * 
		 * onBackPressed(); } });
		 */

		set_title = (TextView) findViewById(R.id.set_title);

		logo_layout = (LinearLayout) this.findViewById(R.id.lvd_logo_layout);
		dialLayout = (View) this.findViewById(R.id.dialLayout);
		friendLayout = (View) this.findViewById(R.id.friendLayout);
		historylistLayout = (View) this.findViewById(R.id.historyListLayout);
		blackLayout = (View) this.findViewById(R.id.blackLayout);
		registerLayout = (View) this.findViewById(R.id.registerLayout);
		historyLayout = (View) this.findViewById(R.id.historyLayout);
		callEdit = (EditText) this.findViewById(R.id.callEdit);
		// noticeCallin = (View) this
		// .findViewById(R.id.notice_callin_linearLayout);
		noticeCallout = (View) this
				.findViewById(R.id.notice_callout_linearLayout);
		out_callLayout = (View) this.findViewById(R.id.out_calling_layout);
		out_calling = (TextView) findViewById(R.id.out_calling_txt);

		friendLv = (ListView) this.findViewById(R.id.friendLv);
		historyListLv = (ListView) this.findViewById(R.id.historyListLv);
		recordLv = (ListView) this.findViewById(R.id.recordLv);

		blackLv = (ListView) this.findViewById(R.id.blackLv);

		/*
		 * recieve_time = (Button) findViewById(R.id.notice_recieve);
		 * recieve_time.setOnTouchListener(new OnTouchListener() {
		 * 
		 * @Override public boolean onTouch(View v, MotionEvent event) { if
		 * (isInClick) { int action = event.getAction(); if (action ==
		 * MotionEvent.ACTION_DOWN) { recieve_time
		 * .setBackgroundResource(R.drawable.lvd1600_sip_ring_); } else if
		 * (action == MotionEvent.ACTION_UP) { recieve_time
		 * .setBackgroundResource(R.drawable.lvd1600_sip_calling); } } return
		 * false; } });
		 */

		findViewById(R.id.del_number).setOnLongClickListener(
				new OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub

						/*
						 * isTure = true; new Thread(new Runnable(){
						 * 
						 * @Override public void run() { // TODO Auto-generated
						 * method stub while(isTure) { try {
						 * handler_del_num.handleMessage(new Message());
						 * Thread.sleep(1500); } catch (InterruptedException e)
						 * { // TODO Auto-generated catch block
						 * e.printStackTrace(); } } }}).start();
						 */
						return true;
					}
				});

		findViewById(R.id.del_number).setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				/*
				 * int action = event.getAction(); if (action ==
				 * MotionEvent.ACTION_UP) { isTure = false;
				 * System.out.println("isTure = false"); }
				 */
				return false;
			}
		});

		img_Open = (Button) findViewById(R.id.open_lock);
		out_connetion = (Button) findViewById(R.id.out_connetion_state);

		myvideo = (SurfaceView) this.findViewById(R.id.myVideo);
//		lccUtil.setSurfaceView(myvideo);
//		SurfaceHolder holder = myvideo.getHolder();
//		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//		holder.addCallback(new SurfaceHolder.Callback() {
//			@Override
//			public void surfaceDestroyed(SurfaceHolder holder) {
//				// TODO Auto-generated method stub
//				// if(lccUtil != null){
//				// lccUtil.setVideoWindowId(null);
//				// }
//				lccUtil.setSurfaceView(null);
//			}
//
//			@Override
//			public void surfaceCreated(SurfaceHolder holder) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void surfaceChanged(final SurfaceHolder holder, int format,
//					int width, int height) {
//				// TODO Auto-generated method stub
//
//				new Thread() {
//					public void run() {
//						lccUtil.setSurfaceView(holder.getSurface());
//					};
//				}.start();
//				// if(lccUtil != null){
//				// lccUtil.setVideoWindowId(holder.getSurface());
//				// }
//			}
//		});
		
		

		/*
		 * // 璁剧疆鍔ㄧ敾 imageView_ring = (ImageView)
		 * findViewById(R.id.recieve_floater); // 璁剧疆鍔ㄧ敾鑳屾櫙
		 * imageView_ring.setBackgroundResource(R.anim.animation_list); //
		 * 鑾峰緱鍔ㄧ敾瀵硅薄 animaition = (AnimationDrawable)
		 * imageView_ring.getBackground();
		 * imageView_ring.setVisibility(View.INVISIBLE);
		 */
	}
	
	protected void blacklistBtnClick() {
		menu_dail_txt.setTextColor(Color.WHITE);
		menu_hostory_txt.setTextColor(Color.WHITE);
		menu_friend_txt.setTextColor(Color.WHITE);
		menu_black_txt.setTextColor(Color.YELLOW);
		menu_sipset_txt.setTextColor(Color.WHITE);
		name_or_num_black.setText("");
		blackBtnClick();
	}

	protected void friendBtnClick() {
		menu_dail_txt.setTextColor(Color.WHITE);
		menu_hostory_txt.setTextColor(Color.WHITE);
		menu_friend_txt.setTextColor(Color.YELLOW);
		menu_black_txt.setTextColor(Color.WHITE);
		menu_sipset_txt.setTextColor(Color.WHITE);
		name_or_num.setText("");
		
		friendBtnClick(null);
	}

	protected void historyBtnClick() {
		menu_dail_txt.setTextColor(Color.WHITE);
		menu_hostory_txt.setTextColor(Color.YELLOW);
		menu_friend_txt.setTextColor(Color.WHITE);
		menu_black_txt.setTextColor(Color.WHITE);
		menu_sipset_txt.setTextColor(Color.WHITE);

		
		historyBtnClick(null);
	}

	protected void dialBtnClick() {
		menu_dail_txt.setTextColor(Color.YELLOW);
		menu_hostory_txt.setTextColor(Color.WHITE);
		menu_friend_txt.setTextColor(Color.WHITE);
		menu_black_txt.setTextColor(Color.WHITE);
		menu_sipset_txt.setTextColor(Color.WHITE);

		dialLayout.setVisibility(View.VISIBLE);
		logo_layout.setVisibility(View.GONE);
		friendLayout.setVisibility(View.GONE);
		historyLayout.setVisibility(View.GONE);
		blackLayout.setVisibility(View.GONE);
		registerLayout.setVisibility(View.GONE);
		historylistLayout.setVisibility(View.GONE);
	}

	private void setupClick(){
		Log.i(TAG, "setupClick");
		menu_dail_txt.setTextColor(Color.WHITE);
		menu_hostory_txt.setTextColor(Color.WHITE);
		menu_friend_txt.setTextColor(Color.WHITE);
		menu_black_txt.setTextColor(Color.WHITE);
		menu_sipset_txt.setTextColor(Color.YELLOW);

		dialLayout.setVisibility(View.GONE);
		logo_layout.setVisibility(View.GONE);
		friendLayout.setVisibility(View.GONE);
		historyLayout.setVisibility(View.GONE);
		blackLayout.setVisibility(View.GONE);
		registerLayout.setVisibility(View.VISIBLE);

		historylistLayout.setVisibility(View.GONE);

		setRegister();
	}

	private Handler handler_del_num = new Handler() {

		public void handleMessage(Message msg) {
			if (callEdit.getText().toString().length() > 0) {
				String s = callEdit.getText().toString();
				callEdit.getText().delete(s.length() - 1, s.length());
				System.out.println("del ===========================i");
			} else {

				isTure = false;
			}

		};
	};

	@Override
	protected void onStart() {
		Log.d(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onPause() {
//		handler_msg.removeMessages(timeout_msg);
//		isLive = true; // 鍏抽棴瑙︽懜灞忔崟锟?
		Log.d(TAG, "onPause");
		// wakeUnLock();
		super.onPause();
		// if(lccUtil!=null)
		// lccUtil.setPreview(0);
		// if (isCall)
		// isCall = false;
		if (isLimit)
			isLimit = false;

		// LockScreenTool.cancelLock();
		unregisterReceiver(phoneCommandReceiver);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "onKeyDown keyCode = " + keyCode);
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Log.d(TAG, "keycode = back");
			if (isCallOut)
				return true;
			onBackPressed();

		}else if(keyCode == KeyEvent.KEYCODE_0){
			if(dialLayout.getVisibility() == View.VISIBLE)
				this.callEdit.append("0");
		}else if(keyCode == KeyEvent.KEYCODE_1){
			if(dialLayout.getVisibility() == View.VISIBLE)
				this.callEdit.append("1");
		}else if(keyCode == KeyEvent.KEYCODE_2){
			if(dialLayout.getVisibility() == View.VISIBLE)
				this.callEdit.append("2");
		}else if(keyCode == KeyEvent.KEYCODE_3){
			if(dialLayout.getVisibility() == View.VISIBLE)
				this.callEdit.append("3");
		}else if(keyCode == KeyEvent.KEYCODE_4){
			if(dialLayout.getVisibility() == View.VISIBLE)
				this.callEdit.append("4");
		}else if(keyCode == KeyEvent.KEYCODE_5){
			if(dialLayout.getVisibility() == View.VISIBLE)
				this.callEdit.append("5");
		}else if(keyCode == KeyEvent.KEYCODE_6){
			if(dialLayout.getVisibility() == View.VISIBLE)
				this.callEdit.append("6");
		}else if(keyCode == KeyEvent.KEYCODE_7){
			if(dialLayout.getVisibility() == View.VISIBLE)
				this.callEdit.append("7");
		}else if(keyCode == KeyEvent.KEYCODE_8){
			if(dialLayout.getVisibility() == View.VISIBLE)
				this.callEdit.append("8");
		}else if(keyCode == KeyEvent.KEYCODE_9){
			if(dialLayout.getVisibility() == View.VISIBLE)
				this.callEdit.append("9");
		}else if(keyCode == KeyEvent.KEYCODE_DEL){
			if(dialLayout.getVisibility() == View.VISIBLE){
				if (callEdit.getText().toString().length() > 0) {
					String s = callEdit.getText().toString();
					callEdit.getText().delete(s.length() - 1, s.length());
				}
			}
		}
//		else if(keyCode == KeyEvent.KEYCODE_DPAD_UP
//				|| keyCode == KeyEvent.KEYCODE_DPAD_LEFT
//				|| keyCode == KeyEvent.KEYCODE_DPAD_RIGHT
//				|| keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
//			Log.i(TAG, "focus on " + this.getCurrentFocus().toString());
//		}

		return super.onKeyDown(keyCode, event);
	}

	public void backClick(View v) {
		onBackPressed();
	}

	public void menuClick(View v) {
		if(v.getId() == R.id.sipSet){
			setupClick();
		}else if(v.getId() == R.id.blackList){
			blacklistBtnClick();
		}else if(v.getId() == R.id.friendBtn){
			friendBtnClick();
		}else if(v.getId() == R.id.historyBtn){
			historyBtnClick();
		}else if(v.getId() == R.id.dialBtn){
			dialBtnClick();
		}
	}

	private void updateRegister() {
		if (LCCUtil.lccUtil.isRegister) {
			
			isRegister = true;
			txt_register.setVisibility(View.VISIBLE);
			String num = LCCUtil.lccUtil.userName;
			txt_register.setText(num);
			
			findViewById(R.id.register_img).setBackgroundResource(
					R.drawable.lvd1600_sip_key_);
//			account_state.setText("已注册");
			
		} else {

			txt_register.setVisibility(View.GONE);
			findViewById(R.id.register_img).setBackgroundResource(
					R.drawable.lvd1600_sip_key);
//			account_state.setText("未注册");
		}
	}

	@Override
	protected void onResume() {

		super.onResume();
		Log.i(TAG, "onresume()");
		this.registerReceiver(phoneCommandReceiver, new IntentFilter(PhoneCommander.PHONE_COMMAND_ACTION));
		// 锁屏
		// LockScreenTool.lockScreen(this);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				isLive = false;
				Log.d(TAG, "onResume");
				timeoutSet();
				if (LCCUtil.lccUtil == null) {
					System.out.println("lccUtil = null");
					startService(new Intent(LCCActivity.this, LCCUtil.class));
//					startService(new Intent(LCCActivity.this,
//							NoticeServer.class));
				} else {
					System.out.println("lccUtil != null");
					lccUtil = LCCUtil.lccUtil;
				}

			}
		}).start();

		if (LCCUtil.lccUtil == null)
			return;
		updateRegister();
		// 安防报警 呼叫管理中心
		if (LCCUtil.lccUtil.call_state == 1) {

			LCCUtil.lccUtil.call_state = 0;
			// doCall("124");
			try {
//				String call_num = mc
//						.GetCfgInfo(ManageCenter.SYS_PARA_CENTERHOUSENO);
//				System.out.println(" alarm  managerCenter  :call_num = "
//						+ call_num);
//				if (call_num.length() > 0) {
//					doCall(call_num);
//
//				}
			} catch (Exception e) {
				System.out.println("call_num = null");
			}
		}
		// 有未接来电显示该号码的历史记录
	/*	if (NoticeServer.haveUnreciever) {
			menu_dail_txt.setTextColor(Color.WHITE);
			menu_hostory_txt.setTextColor(Color.YELLOW);
			menu_friend_txt.setTextColor(Color.WHITE);
			menu_black_txt.setTextColor(Color.WHITE);
			menu_sipset_txt.setTextColor(Color.WHITE);

			historyBtnClick(null);
			String lccno = LCCUtil.callin_no;
			String name = getNameFromLccno(lccno);
			isRecordOrFriend = 2;
			historyLayout.setVisibility(View.INVISIBLE);
			historylistLayout.setVisibility(View.VISIBLE);

			// String lccno = recordList.get(pos).get("recordlccno")
			// .toString().trim();
			// String name = recordList.get(pos).get("recordname")
			// .toString().trim();

			String info = getResources().getString(R.string.name_and_num, name,
					lccno);
			name_and_num.setText(info);

			loadHistoryList(lccno);

			historylist_adapter = new HistoryListAdapter(LCCActivity.this);
			historyListLv.setAdapter(historylist_adapter);
			NoticeServer.haveUnreciever = false;
		}*/
//		sendBroadcast(new Intent("my.hide.notice"));


		
	} 
	private PhoneCommandReceiver phoneCommandReceiver;
	private class PhoneCommandReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			int key = intent.getIntExtra("key", -1);
			Log.d(TAG, "PhoneCommandReceiver key = " + key);
			if(key != -1){
				if(dialLayout.getVisibility() == View.VISIBLE){
					if(PhoneCommander.KEY_CALL == key){
						doCall(callEdit.getText().toString());
					}else if(PhoneCommander.KEY_PLUS == key){
						callEdit.append("#");
					}else if(PhoneCommander.KEY_STAR == key){
						callEdit.append("*");
					}else if(PhoneCommander.KEY_CLEAN == key){
						callEdit.setText("");
					}
				}
			}
		}
		
	}

	public int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		System.out.println("scale=" + scale);
		return (int) (pxValue / scale + 0.5f);
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
		// dbHelper.close();
		// thislcc = null;
		unregisterReceiver(lccMessage1);
		unregisterReceiver(OutReceiver);
		// unregisterReceiver(AlarmReceiver);
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("****************LCCActivity*************", "onStop");
//		if (isCallOut) {
//			if (isCall)
//				isCall = false;
//			PlayAudio_.stop();
//			LCCUtil.hangup();
//		}
	}

	// connetion ftp
	// private synchronized void connetiontFtp() {
	//		
	// String gateIp = lccUtil.getCallIP();
	// String houseNo = map.get("incallnum").toString();;
	// System.out.println("ip="+gateIp+",houseNo="+houseNo);
	// int result = mc.openDoor(gateIp, houseNo);
	// System.out.println("open door: result ="+result);
	//	
	// }

	private Handler mHander = new Handler() {
		public void handleMessage(Message msg) {

			// img_Open.setBackgroundResource(R.drawable.vovo_open);
			// Toast.makeText(LCCActivity.this, "open lock failed!",
			// Toast.LENGTH_SHORT).show();
			int num = msg.what;
			switch (num) {
			case 0:
				System.out.println("open lock failed!");
				break;
			case 1:
				imageView_ring.setVisibility(View.VISIBLE);
				startRingAnim();// 鍚姩鍔ㄧ敾
				break;
			case 2:
				// 鍏抽棴鍔ㄧ敾
				if (animaition.isRunning()) {
					animaition.stop();
				}
				imageView_ring.setVisibility(View.INVISIBLE);
				break;

			}
		};
	};
	private BroadcastReceiver OutReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Bundle b = intent.getExtras();
			Message msg = new Message();
			msg.setData(b);
			myHandler.sendMessage(msg);
		}

	};

	private synchronized void handlerMsg(Message msg) {
		Bundle b = msg.getData();
		Log.d(TAG, "handler msg size = " + b.size() + "");
		for (String type : b.keySet()) {
			String[] args = b.getStringArray(type);
			if (LCCUtil.REGISTEROKCB.equals(type)) {
				if ("ok".equals(args[0])) {
					Log.d(TAG, "handler 注册成功");
					isRegister = true;
				} else if ("fail".equals(args[0])) {
					Log.d(TAG, "handler 注册失败");
					isRegister = false;
				} else if ("unok".equals(args[0])) {
					isRegister = false;
					if (needRegister) {
						needRegister = false;
						// register
						lccUtil.register((String) map.get("username"),
								(String) map.get("password"), (String) map
										.get("serverip"), Integer.parseInt(map
										.get("port").toString()));
					}
				}
			} else if (LCCUtil.CONNECTEDCB.equals(type)) {
//
//				if ("out".equals(lccUtil.calltype)) {
//
//					handler_msg.removeMessages(timeout_msg);
//					isCall = true;
//					Log.d(TAG, "连接成功");
//					findViewById(R.id.mainlayout_right)
//							.setVisibility(View.GONE);
//
//					out_callLayout.setVisibility(View.GONE);
//					noticeCallout.setVisibility(View.VISIBLE);
//					System.out.println("call out   连接成功");
//					set_timer();
//				}

			} else if (LCCUtil.HANGUPCB.equals(type)) {

				if ("out".equals(lccUtil.calltype)) {
					isCallOut = false;
					isCall = false;
					isLimit = false;
					Log.d(TAG, "handler 挂断");
					countCallTime();
					counttime = 0;
					timeoutSet();
					myvideo.setVisibility(View.GONE);
					noticeCallout.setVisibility(View.GONE);
					findViewById(R.id.mainlayout_right).setVisibility(
							View.VISIBLE);
					out_callLayout.setVisibility(View.GONE);
					
					LoadRecordData();
					initValueRecord(0, pageSize_record);
					Message mg = new Message();
					mg.what = 3;
					mHandler.sendMessage(mg);
					
					System.out.println("out hangup");

				}
			} else if (LCCUtil.SERVICE_START.equals(type)) {
				Log.d(TAG, "handler 服务启动完成");
				lccUtil = LCCUtil.lccUtil;
				System.out.println("lccUtil:" + lccUtil);

			} else if (LCCUtil.CALLERRORCB.equals(type)) {

				if ("out".equals(lccUtil.calltype)) {

					isCall = false;
					isLimit = false;
					timeoutSet();
					Log.d(TAG, "handler call error");
//					String error_type = args[0];
					String error_type = getResources().getString(R.string.callerror);
					Toast.makeText(this, error_type, Toast.LENGTH_SHORT).show();

					myvideo.setVisibility(View.GONE);
					out_callLayout.setVisibility(View.GONE);
					noticeCallout.setVisibility(View.GONE);
					findViewById(R.id.mainlayout_right).setVisibility(
							View.VISIBLE);
				}

			}
		}
	}

	private void countCallTime() {
		if (counttime != 0) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					setCallTime();
				}
			}).start();
		}
	}

	private void setCallTime() {

		String count_time_min = counttime / 60 + "";
		String count_time_sec = counttime % 60 + "";
		if (count_time_min.length() == 1) {
			count_time_min = "0" + count_time_min;
		}
		if (count_time_sec.length() == 1) {
			count_time_sec = "0" + count_time_sec;
		}
		String count_time = count_time_min + ":" + count_time_sec;

		int id = 0;
		String calloutno = map.get("outcallnum").toString();
		Cursor c = getContentResolver().query(HISTORY_TB_URI,
				new String[] { "id" }, "lccno = ?", new String[] { calloutno },
				"id desc");
		if (c.moveToFirst()) {
			id = c.getInt(c.getColumnIndex("id"));
		}
		c.close();
		ContentValues values = new ContentValues();
		values.put("counttime", count_time);
		getContentResolver().update(HISTORY_TB_URI, values, "id = ?",
				new String[] { id + "" });
	}

	public void call_click(View v) {

		String callnum_tempt = name_and_num.getText().toString();
		String callNum = callnum_tempt
				.substring(callnum_tempt.indexOf(":") + 1).trim();
		System.out.println("callNum  =    " + callNum);
		doCall(callNum);
	}

	public void clearClick(View v) {
		
		if(historyList.size()==0){
		  Toast.makeText(LCCActivity.this, "无记录可删除！",Toast.LENGTH_LONG).show();	
		}else{
		  showDialog(DELETE_ALL_HISTORY_RECORD);
		}
	}
	
	private void doDeleteAllHistoryRecord(){
		
		String callnum_tempt = name_and_num.getText().toString();
		String callNum = callnum_tempt
		.substring(callnum_tempt.indexOf(":") + 1).trim();
		getContentResolver().delete(HISTORY_TB_URI, "lccno = ?",
				new String[] { callNum });
		if (historyList != null) {
			historyList.clear();
			historylist_adapter.notifyDataSetChanged();
		}
	}

	/** 妫?煡鏄惁涓烘湭鎺ュ惉 */
	private boolean checkState(String num) {

		Cursor c = getContentResolver().query(HISTORY_TB_URI, null,
				"lccno = ? and calltype = ?",
				new String[] { num, DBProvider.CALL_IN }, null);
		if (c.moveToLast()) {
			int state_tempt = c.getInt(c.getColumnIndex("state"));
			c.close();
			if (state_tempt == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			c.close();
			return false;
		}
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
			/*
			 * if (noticeCallin.getVisibility() == View.VISIBLE) {
			 * recieve_time.setText(min + ":" + ss);
			 * 
			 * } else
			 */if (noticeCallout.getVisibility() == View.VISIBLE) {
				out_connetion.setText(min + ":" + ss);
			}
		};

	};
	public boolean isCN(String str){
        try {
            byte [] bytes = str.getBytes("UTF-8");
            if(bytes.length == str.length()){
                return false;
            }else{
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {

		super.onPrepareDialog(id, dialog);

		if (id == ADD_FRIEND_DIALOG) {
			handler_msg.removeMessages(timeout_msg);

			EditText nameEdit = (EditText) dialog.findViewById(R.id.nameEdit);
			EditText numEdit = (EditText) dialog.findViewById(R.id.numEdit);
			nameEdit.setText("");
//			nameEdit.setFilters(new InputFilter[]{
//					new InputFilter.LengthFilter(4),
//		            new InputFilter(){
//		                
//		                public CharSequence filter(CharSequence source, int start,
//		                        int end, Spanned dest, int dstart, int dend) {
//		                    // TODO Auto-generated method stub
//		                    Log.d(TAG, source.toString()+" start:"+start+" end:"+end);
//		                    Log.d(TAG, dest.toString()+" dstart:"+dstart+" dend:"+dend);
//		                    if(isCN(source.toString())){
//		                       
//		                    	return source;
//		                    }else{
//		                    	return "";
//		                    }
//		                }
//		            }
//			});
			numEdit.setText("");

		} else if (id == ADD_FRIEND_FROM_RECORD) {
			handler_msg.removeMessages(timeout_msg);

			EditText nameEdit = (EditText) dialog
					.findViewById(R.id.nameEdit_from_record);
			EditText numEdit = (EditText) dialog
					.findViewById(R.id.numEdit_from_record);

			Map<String, Object> map = recordList.get(recordSelectPos);
			String num_tempt = map.get("recordlccno").toString();
			numEdit.setText(num_tempt);
			nameEdit.setText("");
//			nameEdit.setFilters(new InputFilter[]{
//			new InputFilter.LengthFilter(4),
//            new InputFilter(){
//                
//                public CharSequence filter(CharSequence source, int start,
//                        int end, Spanned dest, int dstart, int dend) {
//                    // TODO Auto-generated method stub
//                    Log.d(TAG, source.toString()+"\tstart:"+start+"\tend:"+end);
//                    Log.d(TAG, dest.toString()+"\tdstart:"+dstart+"\tdend:"+dend);
//                    if(isCN(source.toString())){
//                       
//                    	return source;
//                    }else{
//                    	return "";
////                       
//                    }
////                  return source.length() < 1 ? dest.subSequence(dstart, dend) : "ATAAW.COM";   
//
//                }
//            }
//			
//			});

		} else if (id == ADD_BLACK_DIALOG) {

			handler_msg.removeMessages(timeout_msg);
			EditText nameEdit = (EditText) dialog
					.findViewById(R.id.add_black_name);
			EditText numEdit = (EditText) dialog
					.findViewById(R.id.add_black_num);

			nameEdit.setText("");
//			nameEdit.setFilters(new InputFilter[]{
//					new InputFilter.LengthFilter(4),
//		            new InputFilter(){
//		                
//		                public CharSequence filter(CharSequence source, int start,
//		                        int end, Spanned dest, int dstart, int dend) {
//		                    // TODO Auto-generated method stub
//		                    Log.d(TAG, source.toString()+"\tstart:"+start+"\tend:"+end);
//		                    Log.d(TAG, dest.toString()+"\tdstart:"+dstart+"\tdend:"+dend);
//		                    if(isCN(source.toString())){
//		                       
//		                    	return source;
//		                    }else{
//		                    	return "";
//		                    }
//		                }
//		            }
//			});
			numEdit.setText("");
		} else if (id == EDIT_FRIEND_DIALOG) {

			EditText nameEdit = (EditText) dialog.findViewById(R.id.nameEdit);
			EditText numEdit = (EditText) dialog.findViewById(R.id.numEdit);
			Map<String, Object> map = friendList.get(this.friendSelectPos);
			nameEdit.setText((String) map.get("friendadress"));
			numEdit.setText((String) map.get("friendlccno"));
			numEdit.setFocusable(false);
			numEdit.setLongClickable(false);
		}

		else if (id == CALL_IN_DIALOG) {
			((AlertDialog) dialog).setMessage(getString(R.string.iscallin)
					+ "..." + getNameFromLccno((String) map.get("incallnum")));
		} else if (id == CALL_OUT_DIALOG) {
			((AlertDialog) dialog).setMessage(getString(R.string.iscallout)
					+ "..." + getNameFromLccno((String) map.get("outcallnum")));
		} else if (id == CALL_SHOW_INFO) {
			((AlertDialog) dialog)
					.setMessage("wrong sip address or can't reach able");
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		if (id == ADD_FRIEND_DIALOG) {

			handler_msg.removeMessages(timeout_msg);
			CustomDialog.Builder builder = new CustomDialog.Builder(this);
			LayoutInflater inflater = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.addfriend, null);

			builder.setContentView(layout).setTitle(R.string.addfriend)
					.setPositiveButton(R.string.storage,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									doAddFriend((Dialog) dialog);
									dialog.dismiss();
									// timeoutSet();
								}
							}).setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
									// timeoutSet();
								}
							});
			dialog = builder.create();

		} else if (id == ADD_FRIEND_FROM_RECORD) {

			handler_msg.removeMessages(timeout_msg);
			CustomDialog.Builder builder = new CustomDialog.Builder(this);
			LayoutInflater inflater = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater
					.inflate(R.layout.addfriend_from_record, null);

			builder.setContentView(layout).setTitle(
					R.string.addfriend_from_record).setPositiveButton(
					R.string.storage, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							doAddFriendFromRecord((Dialog) dialog);
							dialog.dismiss();
							// timeoutSet();
						}
					}).setNegativeButton(R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.dismiss();
							// timeoutSet();
						}
					});
			dialog = builder.create();

		}

		else if (id == ADD_BLACK_DIALOG) {

			handler_msg.removeMessages(timeout_msg);
			CustomDialog.Builder builder = new CustomDialog.Builder(this);
			LayoutInflater inflater = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.addblack, null);
			Button btn = (Button) layout.findViewById(R.id.add_black_browse);
			btn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent in = new Intent(LCCActivity.this,
							ContactsActivity.class);
					startActivityForResult(in, requestCode);
					// LCCActivity.this.dismissDialog(ADD_BLACK_DIALOG);
				}
			});
			builder.setContentView(layout).setTitle(R.string.addblack)
					.setPositiveButton(R.string.storage,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									doAddBlack((Dialog) dialog);
									dialog.dismiss();
									// timeoutSet();
								}
							}).setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
									// timeoutSet();
								}
							});
			dialog = builder.create();

		}

		else if (id == EDIT_FRIEND_DIALOG) {

			handler_msg.removeMessages(timeout_msg);
			CustomDialog.Builder builder = new CustomDialog.Builder(this);
			
			LayoutInflater inflater = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.addfriend, null);

			builder.setContentView(layout).setTitle(R.string.editfriend)
					.setPositiveButton(R.string.confirm,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									doEditFriend((Dialog) dialog);
									dialog.dismiss();
									// timeoutSet();
								}
							}).setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
									// timeoutSet();
								}
							});
			dialog = builder.create();

		} else if (id == DELETE_FRIEND_DIALOG) {
			CustomDialog.Builder builder = new CustomDialog.Builder(this);
			// AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage(R.string.ifornot)
					.setTitle(R.string.deletefriend).setPositiveButton(
							R.string.confirm,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									doDeleteFriend((Dialog) dialog);
									dialog.dismiss();
									isDelClick = true;
									// timeoutSet();
								}
							}).setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
									isDelClick = true;
									// timeoutSet();
								}
							});
			dialog = builder.create();

		} else if (id == DELETE_HISTORYLIST_DIALOG) {

			CustomDialog.Builder builder = new CustomDialog.Builder(this);

			builder.setMessage(R.string.ifornot).setTitle(
					R.string.delete_history_list).setPositiveButton(
					R.string.confirm, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							doDeleteHistory_list((Dialog) dialog);
							dialog.dismiss();
							isDelHistoryListClick = true;
						}
					}).setNegativeButton(R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.dismiss();
							isDelHistoryListClick = true;
						}
					});
			dialog = builder.create();

		}

		else if (id == DELETE_HISTORY_DIALOG) {
			/*
			 * AlertDialog.Builder builder = new AlertDialog.Builder(this);
			 * 
			 * builder.setMessage(R.string.ifornot).setTitle(
			 * R.string.deletehistory).setPositiveButton(R.string.confirm, new
			 * DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int id) {
			 * doDeleteHistory((Dialog) dialog); dialog.dismiss(); timeoutSet();
			 * } }).setNegativeButton(R.string.cancel, new
			 * DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int id) { dialog.dismiss();
			 * timeoutSet(); } }); dialog = builder.create();
			 */
		} else if (id == DELETE_BLACK_DIALOG) {

			CustomDialog.Builder builder = new CustomDialog.Builder(this);

			builder.setMessage(R.string.ifornot).setTitle(R.string.deleteblack)
					.setPositiveButton(R.string.confirm,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									doDeleteBlack((Dialog) dialog);
									dialog.dismiss();
									// timeoutSet();
								}
							}).setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
									// timeoutSet();
								}
							});
			dialog = builder.create();
		} else if (id == DELETE_RECORD_DIALOG) {

			CustomDialog.Builder builder = new CustomDialog.Builder(this);

			builder.setMessage(R.string.delete_record).setTitle(
					R.string.delete).setPositiveButton(R.string.confirm,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							
							doDeleteRecord((Dialog) dialog);
							dialog.dismiss();
							// timeoutSet();
						}
					}).setNegativeButton(R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.dismiss();
							// timeoutSet();
						}
					});
			dialog = builder.create();
		} else if (id == DELETE_ALL_RECORD) {

			CustomDialog.Builder builder = new CustomDialog.Builder(this);

			builder.setMessage(R.string.ifornot).setTitle(
					R.string.delete).setPositiveButton(
					R.string.confirm, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							doDeleteAllRecord((Dialog) dialog);
							dialog.dismiss();
							// timeoutSet();
						}
					}).setNegativeButton(R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.dismiss();
							// timeoutSet();
						}
					});
			dialog = builder.create();
		}else if (id == DELETE_ALL_HISTORY_RECORD) {

			CustomDialog.Builder builder = new CustomDialog.Builder(this);

			builder.setMessage(R.string.delete_all_record).setTitle(
					R.string.delete).setPositiveButton(
					R.string.confirm, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							doDeleteAllHistoryRecord();
							dialog.dismiss();
							// timeoutSet();
						}
					}).setNegativeButton(R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.dismiss();
							// timeoutSet();
						}
					});
			dialog = builder.create();
		}
		else if (id == TIME_PICKER_ID) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis());
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			return new TimePickerDialog(this, onTimeSetListener, hour, minute,
					true);

		} else if (id == FRIEND_OPER_DIALOG) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			final CharSequence[] items = { getString(R.string.add),
					getString(R.string.edit), // getString(R.string.delete),getString(R.string.call),
					getString(R.string.blacklist), getString(R.string.ret) };

			builder.setTitle(R.string.selectOperate);

			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					// Toast.makeText(((Dialog)dialog).getContext(), item + "",
					// Toast.LENGTH_SHORT).show();
					switch (item) {
					case 0:

						LCCActivity.this.showDialog(ADD_FRIEND_DIALOG);
						break;
					case 1:
						LCCActivity.this.showDialog(EDIT_FRIEND_DIALOG);
						break;
					/*
					 * case 2:
					 * LCCActivity.this.showDialog(DELETE_FRIEND_DIALOG); break;
					 */
					/*
					 * case 3: if (isCall) return; if (!isRegister) {
					 * Toast.makeText(LCCActivity.this, R.string.isnotregister,
					 * 2000); } else { Map<String, Object> map = friendList
					 * .get(friendSelectPos);
					 * System.out.println(map.get("friendlccno") .toString());
					 * doCall((String) map.get("friendlccno")); } break;
					 */
					case 2:
						Map<String, Object> map_tempt = friendList
								.get(friendSelectPos);
						/*
						 * Map<String, String> map_tempt = (Map<String, String>)
						 * friendLv .getItemAtPosition(friendSelectPos);
						 */

						String lccno = map_tempt.get("friendlccno").toString();
						String name = map_tempt.get("friendadress").toString();
						getContentResolver().delete(FRIEND_TB_URI, "lccno = ?",
								new String[] { lccno });
						friendList.remove(friendSelectPos);
						friend_adapter.notifyDataSetChanged();
						if (!queryBlackByNo(lccno)) {
							ContentValues values = new ContentValues();
							values.put("lccno", lccno);
							values.put("name", name);
							getContentResolver().insert(
									DBProvider.BLACKLIST_TB_URI, values);
						}
						/*
						 * if (queryBlackByNo(lccno)) {
						 * 
						 * getContentResolver().delete(
						 * DBProvider.BLACKLIST_TB_URI, "lccno = ?", new
						 * String[] { lccno }); TextView name_txt = (TextView)
						 * friendLv.getChildAt( friendSelectPos).findViewById(
						 * R.id.friendadress); TextView lccno_txt = (TextView)
						 * friendLv .getChildAt(friendSelectPos).findViewById(
						 * R.id.friendlccno);
						 * name_txt.setTextColor(R.drawable.item_color);
						 * lccno_txt.setTextColor(R.drawable.item_color);
						 * friend_adapter.notifyDataSetChanged();
						 * 
						 * loadHistoryLvData();
						 * friend_adapter.notifyDataSetChanged();
						 * 
						 * 
						 * 
						 * map_tempt.remove("lockImage");
						 * map_tempt.put("lockImage", R.drawable.ic_secure);
						 * adapter_edit_video.notifyDataSetChanged();
						 * 
						 * } else { System.out
						 * .println("insert into blacklist_tb,lccno=" + lccno);
						 * ContentValues values = new ContentValues();
						 * values.put("lccno", lccno); values.put("name", name);
						 * getContentResolver().insert(
						 * DBProvider.BLACKLIST_TB_URI, values);
						 * 
						 * TextView name_txt = (TextView) friendLv.getChildAt(
						 * friendSelectPos).findViewById( R.id.friendadress);
						 * TextView lccno_txt = (TextView) friendLv
						 * .getChildAt(friendSelectPos).findViewById(
						 * R.id.friendlccno);
						 * name_txt.setTextColor(Color.BLACK);
						 * lccno_txt.setTextColor(Color.BLACK);
						 * 
						 * map_tempt.put("friendblack", 1);
						 * friend_adapter.notifyDataSetChanged();
						 * 
						 * loadHistoryLvData();
						 * friend_adapter.notifyDataSetChanged();
						 * 
						 * }
						 */
						break;

					default:
						dialog.dismiss();
						break;
					}
					dialog.dismiss();
				}
			});
			// LayoutInflater inflater =
			// (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// View layout = inflater.inflate(R.layout.chosefriendoper, null);
			// builder.setView(layout);
			dialog = builder.create();
		} else if (id == BLACK_OPER_DIALOG) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			final CharSequence[] items = { getString(R.string.add_friend),
					getString(R.string.ret) };

			builder.setTitle(R.string.selectOperate);

			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					// Toast.makeText(((Dialog)dialog).getContext(), item + "",
					// Toast.LENGTH_SHORT).show();
					switch (item) {

					case 0:
						Map<String, Object> map_tempt = blackList
								.get(blackSelectPos);
						String lccno = map_tempt.get("blacklccno").toString();
						String name = map_tempt.get("blackname").toString();
						getContentResolver().delete(
								DBProvider.BLACKLIST_TB_URI, "lccno = ?",
								new String[] { lccno });
						blackList.remove(blackSelectPos);
						black_adapter.notifyDataSetChanged();
						if (!queryFriendByNo(lccno)) {
							ContentValues values = new ContentValues();
							values.put("lccno", lccno);
							values.put("name", name);
							getContentResolver().insert(FRIEND_TB_URI, values);
						}

						break;

					default:
						dialog.dismiss();
						break;
					}
					dialog.dismiss();
				}
			});

			dialog = builder.create();
		}

		else if (id == HISTORY_OPER_DIALOG) {

			/*
			 * AlertDialog.Builder builder = new AlertDialog.Builder(this);
			 * final CharSequence[] items = { getString(R.string.delete),
			 * getString(R.string.call), getString(R.string.ret) };
			 * 
			 * builder.setTitle(R.string.selectOperate); builder.setItems(items,
			 * new DialogInterface.OnClickListener() { public void
			 * onClick(DialogInterface dialog, int item) { //
			 * Toast.makeText(((Dialog)dialog).getContext(), item + "", //
			 * Toast.LENGTH_SHORT).show(); switch (item) {
			 * 
			 * case 0: LCCActivity.this.showDialog(DELETE_HISTORY_DIALOG);
			 * break;
			 * 
			 * case 1: if (isCall) return; if (!isRegister) {
			 * Toast.makeText(LCCActivity.this, R.string.isnotregister, 2000); }
			 * else { String lccno = (String) historyList.get(
			 * historySelectPos).get("historynum"); doCall(lccno); } break;
			 * default: dialog.dismiss(); break; } dialog.dismiss(); } });
			 */
			// LayoutInflater inflater =
			// (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// View layout = inflater.inflate(R.layout.chosefriendoper, null);
			// builder.setView(layout);
			// dialog = builder.create();
		} else if (id == CALL_IN_DIALOG) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(
					getString(R.string.iscallin) + "..."
							+ getNameFromLccno((String) map.get("incallnum")))
					.setTitle(R.string.callinprompt).setPositiveButton(
							R.string.accept,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									lccUtil.answer();
									dialog.dismiss();
								}
							}).setNegativeButton(R.string.reject,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									hangupClick(null);
									dialog.dismiss();
								}
							});
			dialog = builder.create();

		} else if (id == CALL_OUT_DIALOG) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage(
					getString(R.string.iscallout) + "..."
							+ getNameFromLccno((String) map.get("outcallnum")))
					.setTitle(R.string.calloutprompt).setNegativeButton(
							R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									hangupClick(null);
									dialog.dismiss();
								}
							});
			dialog = builder.create();
		} else if (id == EXIT_DIALOG) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.ifornot).setTitle(R.string.exit)
					.setPositiveButton(R.string.confirm,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									doExit();
								}
							}).setNegativeButton(R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.dismiss();
								}
							});
			dialog = builder.create();
		} else if (id == CALL_SHOW_INFO) {
			// AlertDialog.Builder builder = new AlertDialog.Builder(this);
			//    	
			// builder.setMessage("wrong sip address or can't reach able")
			// .setTitle(R.string.callstatusnotice)
			// .setNegativeButton(R.string.confirm, new
			// DialogInterface.OnClickListener() {
			// public void onClick(DialogInterface dialog, int id) {
			// dialog.dismiss();
			// }
			// });
			// dialog = builder.create();
			Toast.makeText(this, "wrong sip address or can't reach able", 0)
					.show();
		}
		// dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR);
		// WindowManager.LayoutParams lp=dialog.getWindow().getAttributes();
		// lp.alpha=0.8f;
		// dialog.getWindow().setAttributes(lp);
		return dialog;

	}

	private void doExit() {
		PlayAudio.stop();
		if (!isInClick)
			isInClick = true;
		recieve_time.setBackgroundResource(R.drawable.lvd1600_sip_ring);

		hangup();
		/*
		 * if(isRegister){ lccUtil.unregister(); }
		 */
		// if(lccUtil!=null)
		// lccUtil.setVideoWindowId(null);

		onBackPressed();
		// onKeyDown(KeyEvent.KEYCODE_BACK, null);

		/*
		 * this.stopService(new Intent(this, LCCUtil.class)); System.exit(0);
		 */
		// this.finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == this.requestCode) {
			if (resultCode == RESULT_OK) {

				String[] dateString = data.getExtras().getStringArray(
						"contact_info");
				// Toast.makeText(LCCActivity.this,
				// "name = " + dateString[0] + ",num = " + dateString[1],
				// Toast.LENGTH_SHORT).show();
				String name = dateString[0];
				String num = dateString[1];
				EditText nameEdit = (EditText) dialog
						.findViewById(R.id.add_black_name);
				EditText numEdit = (EditText) dialog
						.findViewById(R.id.add_black_num);
				nameEdit.setText(name);
				numEdit.setText(num);

			}
		}
	}

	// 联系人翻页
	public void backwardClick(View v) {

		backPage();

	}

	public void forwardClick(View v) {

		nextPage();
	}

	// 黑名单翻页
	public void black_backwardClick(View v) {
		black_backPage();
	}

	public void black_forwardClick(View v) {
		black_nextPage();
	}

	// 通话记录翻页
	public void record_backwardClick(View v) {
		record_backPage();
	}

	public void record_forwardClick(View v) {
		record_nextPage();
	}

	private long getLongTime(String myTime) {

		SimpleDateFormat sDate_date = new SimpleDateFormat("yyyy-MM-dd");
		String d = sDate_date.format(new Date());
		String begin = d + " " + myTime + ":00";
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sDate.parse(begin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// date杞垚姣
		long beginTime = date.getTime();

		return beginTime;
	}

	private Calendar calendar;
	private AlarmManager am;
	private PendingIntent pendingIntent;

	// 鍏嶆墦鎵拌缃?
//	public void setDndClick(View v) {
//
//		if (start_time.getText().toString().length() > 0
//				&& end_time.getText().toString().length() > 0) {
//			long start_time_long = getLongTime(start_time.getText().toString());
//			long end_time_long = getLongTime(end_time.getText().toString());
//			long now_time_long = System.currentTimeMillis();
//			long beginTime = 0;
//			if (end_time_long > start_time_long) {
//
//				if (now_time_long < start_time_long) {
//					beginTime = start_time_long;
//				} else if (now_time_long > end_time_long) {
//					beginTime = start_time_long + 24 * 60 * 60 * 1000;
//				} else if (now_time_long >= start_time_long
//						&& now_time_long < end_time_long) {
//
//					beginTime = start_time_long + 24 * 60 * 60 * 1000;
////					System.out
////							.println("onstart : 杩涘叆鍏嶆墦鎵版ā寮?    setDoNotDisturb(1)");
////					lccUtil.setDoNotDisturb(1);
//					long duration = end_time_long - now_time_long;
//					lccUtil.handler_dnd.sendMessageDelayed(lccUtil.handler_dnd
//							.obtainMessage(lccUtil.dnd_msg), duration);
//				}
//				Intent intent = new Intent();
//				intent.setAction("android.alarm.demo.action");
//				long duration = end_time_long - start_time_long;
//				intent.putExtra("duration", duration);
//				pendingIntent = PendingIntent.getBroadcast(LCCActivity.this, 0,
//						intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//				// am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//				// pendingIntent);
//				// 璁剧疆鍛ㄦ湡锛侊紒
//				am.setRepeating(AlarmManager.RTC_WAKEUP, beginTime,
//						(24 * 60 * 60 * 1000), pendingIntent);
//				System.out.println("lccActivity :  鍏嶆墦鎵拌缃垚鍔焏uration = "
//						+ duration);
//
//				saveDnd();
//
//				registerLayout.setVisibility(View.GONE);
//				logo_layout.setVisibility(View.VISIBLE);
//				register_btn
//						.setBackgroundResource(R.drawable.lvd1600_sip_menu_06);
//			} else {
//				System.out.println("error:   start_time >= end_time");
//			}
//		} else {
//			Toast.makeText(this, "杈撳叆涓嶅畬鏁达紒", Toast.LENGTH_SHORT).show();
//		}
//
//	}

	// 淇濆瓨璁剧疆鐨勫厤鎵撴壈鏃堕棿
//	private void saveDnd() {
//
//		String begin = start_time.getText().toString();
//		String end = end_time.getText().toString();
//
//		ContentValues values = new ContentValues();
//
//		Cursor c = getContentResolver().query(DBProvider.DND_TB_URI, null,
//				null, null, null);
//		if (c.getCount() > 0) {
//			values.put("begintime", begin);
//			values.put("endtime", end);
//			getContentResolver().update(DBProvider.DND_TB_URI, values,
//					"id = ?", new String[] { "1" });
//
//			c.close();
//		} else {
//			values.put("id", 1);
//			values.put("begintime", begin);
//			values.put("endtime", end);
//
//			getContentResolver().insert(DBProvider.DND_TB_URI, values);
//			c.close();
//		}
//
//	}

	private long getDuration() {

		String begin = start_time.getText().toString();
		String end = end_time.getText().toString();
		SimpleDateFormat sDate = new SimpleDateFormat("HH:mm");
		Date date = null;
		try {
			date = sDate.parse(begin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// date杞垚姣
		long beginTime = date.getTime();
		try {
			date = sDate.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = date.getTime();

		return endTime - beginTime;
	}

//	public void cancelDndClick(View v) {
//
//		am.cancel(pendingIntent);
//		if (start_time.getText().toString().length() > 0)
//			start_time.setText("");
//		if (end_time.getText().toString().length() > 0)
//			end_time.setText("");
//		lccUtil.handler_dnd.removeMessages(lccUtil.dnd_msg);
////		lccUtil.setDoNotDisturb(0);
//		getContentResolver().delete(DBProvider.DND_TB_URI, null, null);
//		System.out.println("鍏嶆墦鎵板凡鍙栨秷");
//
//		registerLayout.setVisibility(View.GONE);
//		logo_layout.setVisibility(View.VISIBLE);
//		register_btn.setBackgroundResource(R.drawable.lvd1600_sip_menu_06);
//	}

	public void setClick(View v) {

		handler_msg.removeMessages(timeout_msg);
		doSetup();
		// registerLayout.setVisibility(View.GONE);
		// logo_layout.setVisibility(View.VISIBLE);
		// menu_sipset_txt.setTextColor(Color.WHITE);
	}

	public void cancelClick(View v) {
		registerLayout.setVisibility(View.GONE);
		logo_layout.setVisibility(View.VISIBLE);
		menu_sipset_txt.setTextColor(Color.WHITE);
	}

	private void setDnd() {
		Cursor c = getContentResolver().query(DBProvider.DND_TB_URI, null,
				null, null, null);

		String begin = "";
		String end = "";

		if (c.moveToFirst()) {

			begin = c.getString(c.getColumnIndex("begintime"));
			end = c.getString(c.getColumnIndex("endtime"));
		}
		start_time.setText(begin);
		end_time.setText(end);
		c.close();

	}

	private void setRegister() {
		try {
//			String username = mc.GetCfgInfo(ManageCenter.SYS_PARA_LOCALHOUSENO);
//			String serverIp = mc.GetCfgInfo(ManageCenter.SYS_PARA_GATEIP);
//			ParamBean pb = mc.getLccInfo();

//			String serverPort = pb.getS32SipServerPort() + "";
//			String videoWidth = pb.getS32SipWidth() + "";
//			String videoHeight = pb.getS32SipHeight() + "";
//			String bitrate = pb.getS32Stream() + "";
//			int result = mc.getSysProp(ManageCenter.SYS_PROP_REG_FLAG);
//			if (result == 0) {
//				dev_state.setText(getString(R.string.lcc_dev_not_register));
//			} else if (result == 1) {
//				dev_state.setText(getString(R.string.lcc_dev_had_register));
//			}
			SetupBean bean = lccUtil.getRegisterInfo();
			username.setText(bean.userName);
			password.setText(bean.password);
			serverip.setText(bean.sipip);
			port.setText(bean.serverPort + "");
			videowidth.setText(bean.width + "");
			videoheight.setText(bean.height + "");
			bitrate.setText(bean.bitrate + "");
//
//			System.out.println("username = " + username + ",serverip = "
//					+ serverIp + ",port =" + serverPort + ",videowidth = "
//					+ videoWidth + ",videoheight =" + videoHeight + ",bitrate="
//					+ bitrate);
		} catch (Exception e) {
			
			System.out.println("no register info!");
		}
	}

	private void doSetup() {

		// String username = this.username.getText().toString();
		// String serverIp = serverip.getText().toString();
		// String videoWidth = videowidth.getText().toString();
		// String videoHeight = videoheight.getText().toString();
		// String bitrate = this.bitrate.getText().toString();
		// String port = this.port.getText().toString();

		// if (username.length() == 0 || serverIp.length() == 0
		// || videoWidth.length() == 0 || videoHeight.length() == 0
		// || bitrate.length() == 0 || port.length() == 0) {
		// Toast.makeText(LCCActivity.this, "输入不能为空！", Toast.LENGTH_SHORT)
		// .show();
		//
		// } else {
		map.put("username", this.username.getText().toString());
		map.put("serverip", serverip.getText().toString());
		map.put("port", this.port.getText().toString());
		map.put("password", this.password.getText().toString());
		if (isRegister) {
			needRegister = true;
			lccUtil.unregister();
		} else {
			lccUtil.register((String) map.get("username"), (String) map
					.get("password"), (String) map.get("serverip"), Integer
					.parseInt(map.get("port").toString()));
		}

		// setvideo
		// lccUtil.setVideo(0);
		lccUtil.setVideoSize(Integer.parseInt(videowidth.getText().toString()),
				Integer.parseInt(videoheight.getText().toString()));
		lccUtil.setVideoBitrate(Integer.parseInt(this.bitrate.getText()
				.toString()));
		// lccUtil.setVideo(1);
//		System.out.println("register : username = "
//				+ map.get("username").toString() + ",serverip = "
//				+ map.get("serverip").toString() + ",port = "
//				+ map.get("port").toString() + ",videowidth = "
//				+ videowidth.getText().toString() + ",videoheight = "
//				+ videoheight.getText().toString() + ",bitrate = "
//				+ bitrate.getText().toString());
		// }

	}

	private void doAddFriend(Dialog dialog) {

		EditText nameEdit = (EditText) dialog.findViewById(R.id.nameEdit);
		EditText numEdit = (EditText) dialog.findViewById(R.id.numEdit);

		String name = nameEdit.getText().toString();
		String num = numEdit.getText().toString();
		addFriend(num, name);

	}

	private void doAddFriendFromRecord(Dialog dialog) {

		EditText nameEdit = (EditText) dialog
				.findViewById(R.id.nameEdit_from_record);
		EditText numEdit = (EditText) dialog
				.findViewById(R.id.numEdit_from_record);

		String name = nameEdit.getText().toString();
		String num = numEdit.getText().toString();
		addFriend(num, name);
		LoadRecordData();

		initValueRecord((nowPage_record - 1) * pageSize_record, nowPage_record
				* pageSize_record);

		record_adapter.notifyDataSetChanged();
		// friendLv.setAdapter(createFriendLvData());
	}

	private void addFriend(String num, String name) {
		if (name.length() > 0 && num.length() > 0) {

			if (!queryFriendByNo(num)) {
				ContentValues values = new ContentValues();
				values.put("name", name);
				values.put("lccno", num);
				getContentResolver().insert(FRIEND_TB_URI, values);

				updateHistoryList(num, name);// 更新通话记录里此号码对应的联系人姓名

				Toast toast = Toast.makeText(LCCActivity.this,
						R.string.success, Toast.LENGTH_SHORT);
//				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				LoadFriendData();
				initValue(0, pageSize);
				String info = getResources().getString(R.string.state_page, 1,
						pageCount);
				state_page.setText(info);

				friend_adapter.notifyDataSetChanged();
			} else {
				Toast toast = Toast.makeText(LCCActivity.this,
						R.string.add_friend_is_already, Toast.LENGTH_SHORT);
				toast.show();
			}
		} else {
			Toast toast1 = Toast.makeText(LCCActivity.this, R.string.failed,
					Toast.LENGTH_SHORT);
			toast1.setGravity(Gravity.CENTER, 0, 0);
			toast1.show();
		}
	}

	private void updateHistoryList(String lccno, String name) {

		Cursor cursor = getContentResolver().query(HISTORY_TB_URI, null,
				"lccno = ?", new String[] { lccno }, null);
		if (cursor.getCount() > 0) {
			ContentValues values = new ContentValues();
			values.put("name", name);
			getContentResolver().update(HISTORY_TB_URI, values, "lccno = ?",
					new String[] { lccno });
		}
		cursor.close();
	}

	private void doAddBlack(Dialog dialog) {

		EditText nameEdit = (EditText) dialog.findViewById(R.id.add_black_name);
		EditText numEdit = (EditText) dialog.findViewById(R.id.add_black_num);

		String name = nameEdit.getText().toString().trim();
		String num = numEdit.getText().toString().trim();

		if (name.length() > 0 && num.length() > 0) {

			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("lccno", num);
			getContentResolver().insert(DBProvider.BLACKLIST_TB_URI, values);
			if (queryFriendByNo(num)) {
				getContentResolver().delete(FRIEND_TB_URI, "lccno = ?",
						new String[] { num });
			}

			Toast toast = Toast.makeText(dialog.getContext(), R.string.success,
					Toast.LENGTH_SHORT);
//			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			LoadBlackData();
			initValueBlack(0, pageSize_black);

			String info = getResources().getString(R.string.state_page, 1, pageCount_black);
			state_page_black.setText(info);
			black_adapter.notifyDataSetChanged();
		} else {
			Toast toast1 = Toast.makeText(LCCActivity.this, R.string.failed,
					Toast.LENGTH_SHORT);
			toast1.setGravity(Gravity.CENTER, 0, 0);
			toast1.show();
		}

		// friendLv.setAdapter(createFriendLvData());
	}

	private void doEditFriend(Dialog dialog) {
		
		Map<String, Object> map_edit = friendList.get(this.friendSelectPos);
		EditText nameEdit = (EditText) dialog.findViewById(R.id.nameEdit);
		EditText numEdit = (EditText) dialog.findViewById(R.id.numEdit);
		String name_edit = nameEdit.getText().toString();
		String lccno_edit = numEdit.getText().toString();
        
		
		
		ContentValues values = new ContentValues();
		values.put("name", name_edit);
		values.put("lccno", lccno_edit);
        
		getContentResolver().update(FRIEND_TB_URI, values, "id = ?",
				new String[] { map_edit.get("id").toString() });

		Toast.makeText(dialog.getContext(), R.string.success,
				Toast.LENGTH_SHORT).show();
		map_edit.put("friendlccno", lccno_edit);
		map_edit.put("friendadress", name_edit);
		friend_adapter.notifyDataSetChanged();
       
		
		// LoadFriendData();

		// friendLv.setAdapter(createFriendLvData());

	}

	private void doDeleteFriend(Dialog dialog) {
		if (friendList.size() > 0) {
			Map<String, Object> map_friend = friendList
					.get(this.friendSelectPos);

			getContentResolver().delete(FRIEND_TB_URI, "id = ?",
					new String[] { map_friend.get("id").toString() });

			String del_lccno = map_friend.get("friendlccno").toString();
			getContentResolver().delete(HISTORY_TB_URI, "lccno = ?", new String[]{del_lccno});
			
			Toast.makeText(dialog.getContext(), R.string.success,
					Toast.LENGTH_SHORT).show();

			LoadFriendData();
			System.out.println("************pageCount = " + pageCount);
			System.out.println("nowPage = " + nowPage);
			if (nowPage > pageCount) {
				nowPage--;
			}
			System.out.println("***************count = " + count);

			System.out.println("pageStart = " + (nowPage - 1) * pageSize
					+ ",pageEnd = " + nowPage * pageSize);

			if (nowPage == 0) {
				initValue(0, pageSize);
			} else {

				initValue((nowPage - 1) * pageSize, nowPage * pageSize);
			}
			friend_adapter.notifyDataSetChanged();
			
			Message msg = new Message();
			msg.what = 1;
			mHandler.sendMessage(msg);
			
			
		}
	}

	private void doDeleteHistory_list(Dialog dialog) {
		if (historyList.size() > 0) {
			Map<String, Object> map_history = historyList
					.get(this.historySelectPos);

			getContentResolver().delete(HISTORY_TB_URI, "id = ?",
					new String[] { map_history.get("id").toString() });

			Toast.makeText(dialog.getContext(), R.string.success,
					Toast.LENGTH_SHORT).show();

			// LoadFriendData();
			historyList.remove(historySelectPos);
			historylist_adapter.notifyDataSetChanged();
			// friendLv.setAdapter(createFriendLvData());
		}
	}

	private void doDeleteBlack(Dialog dialog) {

		if (blackList.size() > 0) {
			Map<String, Object> map = blackList.get(blackSelectPos);
			getContentResolver().delete(DBProvider.BLACKLIST_TB_URI, "id = ?",
					new String[] { map.get("blackid").toString() });
			Toast.makeText(dialog.getContext(), R.string.success,
					Toast.LENGTH_SHORT).show();
			LoadBlackData();
			if (nowPage_black > pageCount_black) {
				nowPage_black--;
			}
			if (nowPage_black == 0) {

				initValueBlack(0, pageSize_black);
			} else {

				initValueBlack((nowPage_black - 1) * pageSize_black,
						nowPage_black * pageSize_black);
			}
			black_adapter.notifyDataSetChanged();
			Message msg = new Message();
			msg.what = 2;
			mHandler.sendMessage(msg);
		}
	}

	private void doDeleteRecord(Dialog dialog) {

		if (recordList.size() > 0) {

			Map<String, Object> map = recordList.get(recordSelectPos);
			getContentResolver().delete(HISTORY_TB_URI, "id = ?",
					new String[] { map.get("id").toString() });

			Toast.makeText(dialog.getContext(), R.string.success,
					Toast.LENGTH_SHORT).show();

			LoadRecordData();
			if (nowPage_record > pageCount_record) {
				nowPage_record--;
			}
			if (nowPage_record == 0) {
				initValueRecord(0, pageSize_record);
			} else {

				initValueRecord((nowPage_record - 1) * pageSize_record,
						nowPage_record * pageSize_record);
			}
			record_adapter.notifyDataSetChanged();
			Message msg = new Message();
			msg.what = 3;
			mHandler.sendMessage(msg);

		}
	}

	private void doDeleteAllRecord(Dialog dialog) {

		getContentResolver().delete(HISTORY_TB_URI, null, null);
		RecordCount.clear();
		recordList.clear();
		record_adapter.notifyDataSetChanged();
		Toast.makeText(dialog.getContext(), R.string.success,
				Toast.LENGTH_SHORT).show();
		nowPage_record = 0;
		pageCount_record = 0;
		Message msg = new Message();
		msg.what = 3;
		mHandler.sendMessage(msg);
	}

	// 
	private void updateHistoryState() {
		int id = 0;
		Cursor c = getContentResolver().query(HISTORY_TB_URI,
				new String[] { "id" }, "lccno = ?",
				new String[] { map.get("incallnum").toString() }, "id desc");
		if (c.moveToFirst()) {
			id = c.getInt(c.getColumnIndex("id"));
		}
		c.close();
		ContentValues values = new ContentValues();
		values.put("state", 1);
		getContentResolver().update(HISTORY_TB_URI, values, "id = ?",
				new String[] { id + "" });
	}

	private void insertHistory(String type, String num, int state) {

		/*
		 * Cursor cursor = getContentResolver().query(HISTORY_TB_URI, null,
		 * "state = ?", new String[] { state + "" }, null); if
		 * (cursor.getCount() >= 50) { cursor.moveToNext(); int firstid =
		 * cursor.getInt(0); cursor.close();
		 * 
		 * getContentResolver().delete(HISTORY_TB_URI, "id = ?", new String[] {
		 * firstid + "" }); } cursor.close();
		 */

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date curDate = new Date(System.currentTimeMillis());
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
		String name = "unknow";
		while (cursor.moveToNext()) {
			name = cursor.getString(0);
		}
		cursor.close();
		return name;
	}

	/*@Override
	public void onBackPressed() {

		System.out.println(TAG + "   onBackPressed() ");
		if (isCall) {
			hangup();
			isCall = false;
		}
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
		// intent.addCategory(Intent.CATEGORY_HOME);
		intent.addCategory("android.intent.category.HOME");
		this.startActivity(intent);

		
		 * Intent intent = new Intent(); ComponentName cn = new
		 * ComponentName("lorent.intercom.indoor",
		 * "lorent.intercom.indoor.IndoorActivity"); intent.setComponent(cn);
		 * intent.setAction(Intent.ACTION_MAIN); startActivity(intent);
		 
	}*/

	//
	private void timeoutSet() {
		handler_msg.removeMessages(timeout_msg);
		handler_msg.sendMessageDelayed(handler_msg.obtainMessage(timeout_msg),
				30000);
	}

	private Handler handler_msg = new Handler() {
		public void handleMessage(Message msg) {

//			LCCActivity.this.finish();
		};
	};

	private BroadcastReceiver lccMessage1 = new BroadcastReceiver() {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Bundle b = arg1.getExtras();
			Message msg = new Message();
			msg.setData(b);
			handlerLccMsg1.sendMessage(msg);

		}

	};
	private Handler handlerLccMsg1 = new Handler() {
		public void handleMessage(Message msg) {

			Bundle b = msg.getData();
			for (String type : b.keySet()) {
				String[] args = b.getStringArray(type);
				if (type.equals("register")) {
					if (args[0].equals("ok")) {
						
						isRegister = true;
						txt_register.setVisibility(View.VISIBLE);
//						String num = args[1].substring(args[1].length()-4);
						String num = args[1];
						txt_register.setText(num);
						findViewById(R.id.register_img).setBackgroundResource(
								R.drawable.lvd1600_sip_key_);
//						account_state.setText("已注册");

					} else {

						txt_register.setVisibility(View.GONE);
						findViewById(R.id.register_img).setBackgroundResource(
								R.drawable.lvd1600_sip_key);

//						account_state.setText("未注册");
					}
				} else {

				}
			}
		}
	};

	/** load history list */
	private void loadHistoryList(String lccno) {
		if (historyList.size() > 0) {
			historyList.clear();
		}
		Map<String, Object> map = null;

		Cursor cursor = getContentResolver().query(HISTORY_TB_URI, null,
				"lccno = ?", new String[] { lccno }, "id desc");

		while (cursor.moveToNext()) {

			int id = cursor.getInt(cursor.getColumnIndex("id"));
			// String no = cursor.getString(cursor.getColumnIndex("lccno"));
			// String calltype =
			// cursor.getString(cursor.getColumnIndex("calltype"));
			String time = cursor.getString(cursor.getColumnIndex("calltime"));
			int state = cursor.getInt(cursor.getColumnIndex("state"));
			String calltime = cursor.getString(cursor
					.getColumnIndex("counttime"));

			map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("historylist_time", time);
			map.put("historylist_calltime", calltime);
			map.put("historylist_lccno", lccno);
			Bitmap b = null;
			if (state == 1) {
				// 已接
				b = BitmapFactory.decodeResource(getResources(),
						R.drawable.lvd1610_call_in);
			} else if (state == 0) {
				// 未接
				b = BitmapFactory.decodeResource(getResources(),
						R.drawable.lvd1610_unrecieve);
			} else if (state == 2) {
				// 拨出
				b = BitmapFactory.decodeResource(getResources(),
						R.drawable.lvd1610_call_out);
			} else if (state == 3) {
				// 拒接
				b = BitmapFactory.decodeResource(getResources(),
						R.drawable.lvd1610_refuse);
			}
			map.put("historylist_img", b);
			/*
			 * if (queryBlackByNo(no)) { map.put("friendblack", 1); } else {
			 * map.put("friendblack", 0); }
			 */
			historyList.add(map);
		}
		cursor.close();
	}

	class FriendAdapter extends BaseAdapter {

		Context c;
		LayoutInflater mInflater;

		FriendAdapter(Context context) {
			c = context;
			mInflater = getLayoutInflater();
		}

		public int getCount() {
			return friendList.size();
		}

		public Object getItem(int position) {
			return friendList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = new ViewHolder();
			final int pos = position;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.friendlist, null);
			}
			holder.list_img = (ImageView) convertView
					.findViewById(R.id.friendicon);
			holder.list_num_txt = (TextView) convertView
					.findViewById(R.id.friendlccno);
			holder.list_adress_txt = (TextView) convertView
					.findViewById(R.id.friendadress);
			holder.list_docall_img = (Button) convertView
					.findViewById(R.id.frienddocall);
			holder.list_edit_img = (Button) convertView
			        .findViewById(R.id.friendedit);
			holder.list_del_img = (Button) convertView
					.findViewById(R.id.frienddel);
			holder.list_add_black = (Button) convertView
					.findViewById(R.id.friendblack);

			holder.list_num_txt.setText(friendList.get(position).get(
					"friendlccno").toString());
			holder.list_adress_txt.setText(friendList.get(position).get(
					"friendadress").toString());

			holder.list_img.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					isRecordOrFriend = 1;
					friendLayout.setVisibility(View.INVISIBLE);
					historylistLayout.setVisibility(View.VISIBLE);
					String lccno = friendList.get(pos).get("friendlccno")
							.toString().trim();
					String name = friendList.get(pos).get("friendadress")
							.toString().trim();

					String info = getResources().getString(
							R.string.name_and_num, name, lccno);
					name_and_num.setText(info);

					loadHistoryList(lccno);

					historylist_adapter = new HistoryListAdapter(
							LCCActivity.this);
					historyListLv.setAdapter(historylist_adapter);
				}

			});
			holder.list_docall_img
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							String lccno = friendList.get(pos).get(
									"friendlccno").toString();
							doCall(lccno);
						}
					});
			holder.list_edit_img
			       .setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						friendSelectPos = pos;
						showDialog(EDIT_FRIEND_DIALOG);
					}
				});
			holder.list_del_img.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					/* synchronized (this) { */
					if (isDelClick) {
						isDelClick = false;

						friendSelectPos = pos;
						showDialog(DELETE_FRIEND_DIALOG);
					}

				}
			});
			holder.list_add_black
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							String lccno = friendList.get(pos).get(
									"friendlccno").toString().trim();
							String name = friendList.get(pos).get(
									"friendadress").toString().trim();

							if (!queryBlackByNo(lccno)) {
								
								getContentResolver().delete(FRIEND_TB_URI,
										"lccno = ?", new String[] { lccno });
								ContentValues values = new ContentValues();
								values.put("lccno", lccno);
								values.put("name", name);
								getContentResolver().insert(
										DBProvider.BLACKLIST_TB_URI, values);
								
								LoadFriendData();
								System.out.println("************pageCount = " + pageCount);
								System.out.println("nowPage = " + nowPage);
								if (nowPage > pageCount) {
									nowPage--;
								}
								System.out.println("***************count = " + count);

								System.out.println("pageStart = " + (nowPage - 1) * pageSize
										+ ",pageEnd = " + nowPage * pageSize);

								if (nowPage == 0) {
									initValue(0, pageSize);
								} else {

									initValue((nowPage - 1) * pageSize, nowPage * pageSize);
								}
								
//								friendList.remove(pos);
								friend_adapter.notifyDataSetChanged();
								Message msg = new Message();
								msg.what = 1;
								mHandler.sendMessage(msg);
							}
						}
					});
			return convertView;
		}

		class ViewHolder {
			ImageView list_img;
			TextView list_num_txt;
			TextView list_adress_txt;
			Button list_docall_img;
			Button list_edit_img;
			Button list_del_img;
			Button list_add_black;
		}

	}

	class HistoryListAdapter extends BaseAdapter {

		Context c;
		LayoutInflater mInflater;

		HistoryListAdapter(Context context) {
			c = context;
			mInflater = getLayoutInflater();
		}

		public int getCount() {
			return historyList.size();
		}

		public Object getItem(int position) {
			return historyList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = new ViewHolder();
			final int pos = position;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.friend_history_list,
						null);
			}
			holder.historylist_img = (ImageView) convertView
					.findViewById(R.id.historylist_icon);
			holder.historylist_time = (TextView) convertView
					.findViewById(R.id.historylist_time);
			holder.historylist_lccno = (TextView) convertView
					.findViewById(R.id.historylist_lccno);
			holder.historylist_calltime = (TextView) convertView
					.findViewById(R.id.historylist_calltime);
			holder.historylist_del = (Button) convertView
					.findViewById(R.id.historylist_del);

			holder.historylist_img.setImageBitmap((Bitmap) historyList.get(
					position).get("historylist_img"));
			holder.historylist_time.setText(historyList.get(position).get(
					"historylist_time").toString());
			holder.historylist_lccno.setText(historyList.get(position).get(
					"historylist_lccno").toString());
			holder.historylist_calltime.setText(historyList.get(position).get(
					"historylist_calltime").toString());

			holder.historylist_del
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							synchronized (this) {
								if (isDelHistoryListClick) {
									isDelHistoryListClick = false;

									historySelectPos = pos;
									showDialog(DELETE_HISTORYLIST_DIALOG);
								}

							}
						}
					});
			return convertView;
		}

		class ViewHolder {
			ImageView historylist_img;
			TextView historylist_lccno;
			TextView historylist_time;
			TextView historylist_calltime;
			Button historylist_del;

		}

	}

	class BlackAdapter extends BaseAdapter {

		Context c;
		LayoutInflater mInflater;

		BlackAdapter(Context context) {
			c = context;
			mInflater = getLayoutInflater();
		}

		public int getCount() {
			return blackList.size();
		}

		public Object getItem(int position) {
			return blackList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = new ViewHolder();
			final int pos = position;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.blacklist, null);
			}

			holder.black_num_txt = (TextView) convertView
					.findViewById(R.id.blacklccno);
			holder.black_adress_txt = (TextView) convertView
					.findViewById(R.id.blackadress);
			holder.black_add_img = (Button) convertView
					.findViewById(R.id.blackadd);
			holder.black_del_img = (Button) convertView
					.findViewById(R.id.blackdel);
			System.out.println("setText" + position);

			holder.black_num_txt.setText(blackList.get(position).get(
					"blacklccno").toString());
			holder.black_adress_txt.setText(blackList.get(position).get(
					"blackname").toString());
			holder.black_add_img.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					String lccno = blackList.get(pos).get("blacklccno")
							.toString();
					String name = blackList.get(pos).get("blackname")
							.toString();
					
					if (!queryFriendByNo(lccno)) {
						
						getContentResolver().delete(DBProvider.BLACKLIST_TB_URI,
								"lccno = ?", new String[] { lccno });
						ContentValues values = new ContentValues();
						values.put("lccno", lccno);
						values.put("name", name);
						getContentResolver().insert(FRIEND_TB_URI, values);
						
						LoadBlackData();
						if (nowPage_black > pageCount_black) {
							nowPage_black--;
						}
						if (nowPage_black == 0) {

							initValueBlack(0, pageSize_black);
						} else {

							initValueBlack((nowPage_black - 1) * pageSize_black,
									nowPage_black * pageSize_black);
						}
						black_adapter.notifyDataSetChanged();
						Message msg = new Message();
						msg.what = 2;
						mHandler.sendMessage(msg);
					}
					
				}
			});
			holder.black_del_img.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					/* synchronized (this) { */

					blackSelectPos = pos;
					showDialog(DELETE_BLACK_DIALOG);

				}
			});
			return convertView;
		}

		class ViewHolder {

			TextView black_num_txt;
			TextView black_adress_txt;
			Button black_add_img;
			Button black_del_img;
		}
	}

	class RecordAdapter extends BaseAdapter {

		Context c;
		LayoutInflater mInflater;

		RecordAdapter(Context context) {
			c = context;
			mInflater = getLayoutInflater();
		}

		public int getCount() {
			return recordList.size();
		}

		public Object getItem(int position) {
			return recordList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = new ViewHolder();
			final int pos = position;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.historylist, null);
			}
			holder.record_icon = (ImageView) convertView
					.findViewById(R.id.historyicon);
			holder.record_type_img = (ImageView) convertView
					.findViewById(R.id.historytype);
			holder.record_time_txt = (TextView) convertView
					.findViewById(R.id.historytime);
			holder.record_num_txt = (TextView) convertView
					.findViewById(R.id.historynum);
			holder.record_name_txt = (TextView) convertView
					.findViewById(R.id.historyname);
			holder.record_docall_img = (Button) convertView
					.findViewById(R.id.historydocall);
			holder.record_addfriend_img = (Button) convertView
					.findViewById(R.id.historyaddfriend);
			holder.record_del_img = (Button) convertView
					.findViewById(R.id.historydel);

			System.out.println("setText" + position);

			holder.record_num_txt.setText(recordList.get(position).get(
					"recordlccno").toString());
			holder.record_name_txt.setText(recordList.get(position).get(
					"recordname").toString());
			holder.record_time_txt.setText(recordList.get(position).get(
					"recordtime").toString());
			holder.record_type_img.setImageBitmap((Bitmap) recordList.get(
					position).get("recordtype"));
			holder.record_icon.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					isRecordOrFriend = 2;
					historyLayout.setVisibility(View.INVISIBLE);
					historylistLayout.setVisibility(View.VISIBLE);

					String lccno = recordList.get(pos).get("recordlccno")
							.toString().trim();
					String name = recordList.get(pos).get("recordname")
							.toString().trim();

					String info = getResources().getString(
							R.string.name_and_num, name, lccno);
					name_and_num.setText(info);

					loadHistoryList(lccno);

					historylist_adapter = new HistoryListAdapter(
							LCCActivity.this);
					historyListLv.setAdapter(historylist_adapter);
				}
			});
			holder.record_docall_img
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							String lccno = recordList.get(pos).get(
									"recordlccno").toString();
							doCall(lccno);

						}
					});
			holder.record_addfriend_img
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							recordSelectPos = pos;
							showDialog(ADD_FRIEND_FROM_RECORD);
						}
					});

			holder.record_del_img
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub

							/* synchronized (this) { */

							recordSelectPos = pos;
							showDialog(DELETE_RECORD_DIALOG);

						}
					});
			return convertView;
		}

		class ViewHolder {

			ImageView record_icon;
			ImageView record_type_img;
			TextView record_num_txt;
			TextView record_name_txt;
			TextView record_time_txt;
			Button record_docall_img;
			Button record_addfriend_img;
			Button record_del_img;
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (!isLive) {
			timeoutBack();
		}
		return super.dispatchTouchEvent(ev);
	}

	private synchronized void timeoutBack() {

		if (isCall || isLimit) {
			System.out
					.println("synchronized:   handler_msg.removeMessages(timeout_msg)");
			handler_msg.removeMessages(timeout_msg);
		} else {
			timeoutSet();

		}
	}

	@Override
	public void onAttachedToWindow() {
		this.getWindow().setType(
				WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
		super.onAttachedToWindow();
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_HOME) {
			System.out.println("longPress  keycode_home");
			return true;
		}
		return super.onKeyLongPress(keyCode, event);
	}

}
