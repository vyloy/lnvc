package com.lorent.video;


import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.demo.VideoViewDemo;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.lorent.common.dto.MonitorInfo;
import com.lorent.video.MainActivity.DeviceType;
import com.lorent.video.service.MonitorService;
import com.lorent.video.util.DBAdapterImpl;
import com.lorent.video.util.ImageUtil;

public class MonitorActivity  extends ListActivity  {

	private ListView monitoListV;
	MonitorInfoAdapter infoAdapter;
	View footer;
	MonitorService service;
	int currentPage = 1;
	boolean isLoadFinished = false;
	private List<MonitorInfo> datas = new ArrayList<MonitorInfo>();
	final String TAG = "monitoractivity";
	private LinearLayout addMonitorLayout;
	private LinearLayout monitortoolbar;
	private io.vov.vitamio.widget.VideoView mVideoView;
	private LinearLayout surfaceContainer;
	
	private LayoutParams WClayoutParams =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
	
	@Override
    public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.monitor_main);
        FrameLayout container = (FrameLayout)findViewById(R.id.monitormain);
        container.setPadding(MainActivity.left, MainActivity.top, MainActivity.right, MainActivity.bottom);
//      int cw = container.getWidth();
//      int ch = container.getHeight();
//      Log.i("container", cw + ":" + ch);
        Display display = getWindowManager().getDefaultDisplay();
        
        container.setBackgroundDrawable(MainActivity.getDgDrawable());
//        mVideoView = (io.vov.vitamio.widget.VideoView) findViewById(R.id.monitor_surface_view);
//        Log.i("mVideoView", mVideoView + "");
//        surfaceContainer = (LinearLayout)findViewById(R.id.monitor_surface_container);
        monitortoolbar = (LinearLayout)findViewById(R.id.monitortoolbar);
        addMonitorLayout = (LinearLayout)findViewById(R.id.addMonitorLayout);
        monitoListV = (ListView)findViewById(android.R.id.list);
        monitoListV.requestFocus();
        footer = (View)getLayoutInflater().inflate(R.layout.monitor_loading, null);  
        initMonitorAdapter();
        service = new MonitorService(this);
        new LoadInfoAsyncTask().execute(currentPage);
        monitoListV.setOnScrollListener(new MonitorListVOnScrollListener());
        monitoListV.setOnItemClickListener(new MonitorListVOnItemClickListener());
	}
	
	
	private class MonitorListVOnItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
//			if(surfaceContainer.getVisibility()!=View.VISIBLE){
//				return;
//			}
				Log.i(TAG, position+"");
				MonitorInfo info = (MonitorInfo)monitoListV.getItemAtPosition(position);
				Log.i(TAG, info.monitoUrl+"");
				showMonitor(info);
		}
		
	}
	
	private class MonitorListVOnScrollListener implements OnScrollListener{

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
//			Log.i(TAG, "onScroll=" + firstVisibleItem + ":" + visibleItemCount + ":" + totalItemCount);
			if(firstVisibleItem<=0){
				return;
			}
			
			// 计算当前加载上来的全部item数目  
			int totalItem = firstVisibleItem + visibleItemCount;  
			if(totalItem == totalItemCount && isLoadFinished && !noContentFlag){
				isLoadFinished = false;
				monitoListV.addFooterView(footer);
				currentPage++;
				new LoadInfoAsyncTask().execute(currentPage);
			}
		}
		
	}
	
	
	private void initMonitorAdapter(){
    	if(infoAdapter==null){
    		monitoListV.addFooterView(footer);
    		infoAdapter = new MonitorInfoAdapter(this,datas,R.layout.monitor_item);
    		monitoListV.setAdapter(infoAdapter);
    		monitoListV.removeFooterView(footer);
    	}
    }
	
	boolean noContentFlag = false;
	private class LoadInfoAsyncTask extends AsyncTask<Integer,Integer,List<MonitorInfo>>{
		
		
        protected void onPostExecute (List<MonitorInfo> result) {  
        	if(result==null){
        		monitoListV.removeFooterView(footer);
        		toastShow("加载失败",2000);
        	}else if(result.size()==0){
        		if(noContentFlag==false){
        			toastShow("没有更加内容加载",2000);
	        		noContentFlag = true;
	        		monitoListV.removeFooterView(footer);
        		}
        	}else if(result.size()>0){
        		datas.addAll(result);
        		monitoListV.removeFooterView(footer);
        		infoAdapter.notifyDataSetChanged();
        		if(result.size()<DBAdapterImpl.PAGE_SIZE){
        			noContentFlag = true;
        		}
        	}
        	
        	isLoadFinished = true;
        }

		@Override
		protected List<MonitorInfo> doInBackground(Integer... params) {
			try {
//				Thread.sleep(1000);
				if(DBAdapterImpl.DBHelper==null){
					DBAdapterImpl.init(MonitorActivity.this);
				}
				List<MonitorInfo> infos = service.getMonitorFromPosition(datas.size());
				return infos;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	AlertDialog.Builder builder;  
    AlertDialog alertDialog;
	public void openAddMonitorDialog(View v){
//		if(surfaceContainer.getVisibility()==View.VISIBLE){
//			return;
//		}
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);  
        View layout = inflater.inflate(R.layout.addmonitor_dialog,null);  
        builder = new AlertDialog.Builder(this);
        builder.setTitle("添加");
        builder.setView(layout);
        
        alertDialog = builder.create();
        alertDialog.show(); 
	}
	
	public void saveMonitorInfo(View v){
		if(v.getId()==R.id.addOkBtn){
			EditText monitorTitleE = (EditText)alertDialog.findViewById(R.id.monitorTitleE);
			EditText monitorUrlE = (EditText)alertDialog.findViewById(R.id.monitorUrlE);
			MonitorInfo info = new MonitorInfo();
			info.title = monitorTitleE.getText().toString();
			info.monitoUrl = monitorUrlE.getText().toString();
			new AddInfoAsyncTask().execute(info);
			alertDialog.dismiss();
		}else{
			alertDialog.dismiss();
		}
	}
	
	public void deleteMonitor(View v){
//		if(surfaceContainer.getVisibility()==View.VISIBLE){
//			return;
//		}
		Button bt = (Button)v;
		MonitorInfo info = (MonitorInfo)bt.getTag();
		this.openDeleteMonitorDialog(info);
	}
	
	private void toastShow(String content,int time){
		Toast errorToast = Toast.makeText(MonitorActivity.this, content, time);
		errorToast.setGravity(Gravity.CENTER,0,0);
		errorToast.show();
	}
	
	private class DeleteInfoAsyncTask extends AsyncTask<Long,Void,Boolean>{
		private Long id;
		DeleteInfoAsyncTask(Long id){
			this.id = id;
		}
		
        protected void onPostExecute (Boolean result) {  
        	if(!result){
        		toastShow("删除失败",2000);
        	}else{
        		toastShow("删除成功",2000);
        		MonitorInfo delInfo = null;
        		for(MonitorInfo info:datas){
        			if(info.id==id){
        				delInfo = info;
        				break;
        			}
        		}
        		datas.remove(delInfo);
        		infoAdapter.notifyDataSetChanged();
        	}
        }

		@Override
		protected Boolean doInBackground(Long... ids) {
			try {
				Boolean result = Boolean.valueOf(service.deleteMonitor(ids[0]));
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	private class AddInfoAsyncTask extends AsyncTask<MonitorInfo,Integer,MonitorInfo>{
		
        protected void onPostExecute (MonitorInfo result) {  
        	if(result==null){
        		toastShow("添加失败",2000);
        	}else{
        		toastShow("添加成功",2000);
        		if(datas.size()<DBAdapterImpl.PAGE_SIZE){
        			datas.add(result);
        			infoAdapter.notifyDataSetChanged();
        		}else{
        			noContentFlag = false;
        		}
        	}
        }

		@Override
		protected MonitorInfo doInBackground(MonitorInfo... infos) {
			try {
				MonitorInfo result = service.saveMonitor(infos[0]);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	int pressCount = 0;
	
	private Handler keyHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			KeyEvent event = (KeyEvent)msg.obj;
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			Log.i(TAG+"presscount", "chu"+pressCount);
			if(pressCount>1){
				Log.i(TAG+"presscount", "获取焦点"+pressCount);
				addMonitorLayout.requestFocus();
			}else{
				Log.i(TAG+"presscount", "播放监控"+pressCount);
				MonitorInfo info = (MonitorInfo)monitoListV.getSelectedItem();
//				Intent intent = new Intent(MonitorActivity.this,VideoViewDemo.class);
//				intent.putExtra("fileName", info.title);
//				intent.putExtra("videoUrl", info.monitoUrl);
//				startActivity(intent);
				if(info!=null){
					showMonitor(info);
				}
			}
			pressCount = 0;
		}
	};
	
	public boolean dispatchKeyEvent(final KeyEvent event){
		Log.i(TAG+"down/on", event.getKeyCode() + "");
//		if(surfaceContainer.getVisibility()!=View.VISIBLE){
			if(monitoListV.isFocused() && event.getAction()==KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
//				Log.i(TAG+"presscount", ""+pressCount);
//				if(pressCount==0){
//					pressCount++;
//					keyHandler.sendMessageDelayed(keyHandler.obtainMessage(100, event),1000);
//				}else{
//					pressCount++;
//				}
//				return true;
			}else if(monitoListV.isFocused() && event.getAction()==KeyEvent.ACTION_UP && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
//				return true;
			}else if(event.getKeyCode() == KeyEvent.KEYCODE_1){
				addMonitorLayout.requestFocus();
				return true;
			}else if(monitoListV.isFocused() && event.getAction()==KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT){
				MonitorInfo info = (MonitorInfo)monitoListV.getSelectedItem();
				if(info!=null){
					openDeleteMonitorDialog(info);
				}
			}
//		}else{
//			if(surfaceContainer.getVisibility()==View.VISIBLE && event.getAction()==KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK){
//				return true;
//			}else if(surfaceContainer.getVisibility()==View.VISIBLE && event.getAction()==KeyEvent.ACTION_UP && event.getKeyCode() == KeyEvent.KEYCODE_BACK){
//				clearVideoView();
//				surfaceContainer.setVisibility(View.INVISIBLE);
//				return true;
//			}
//			return true;
//		}
		return super.dispatchKeyEvent(event);
	}
	
	private void openDeleteMonitorDialog(final MonitorInfo info){
		String title = "是否删除" + info.title + "监控资源";
		DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				new DeleteInfoAsyncTask(info.id).execute(info.id);
			}
			
		};
		new ConfirmDialog().show(this, title, okListener, null);
		
	}
	
	public void openVideoActivity(View v){
//		if(surfaceContainer.getVisibility()==View.VISIBLE){
//			return;
//		}
		Intent intent = new Intent(MonitorActivity.this,MainActivity.class);
//		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}
	
	
	AlertDialog.Builder monitorBuilder;  
    AlertDialog monitorShowDialog;
	public void openMonitorShowDialog(MonitorInfo info){
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);  
        View layout = inflater.inflate(R.layout.monitorshow_dialog,null);  
        mVideoView = (io.vov.vitamio.widget.VideoView) layout.findViewById(R.id.monitor_surface_view);
        Log.i("mVideoView", mVideoView + "");
        surfaceContainer = (LinearLayout)layout.findViewById(R.id.monitor_surface_container);
        showMonitor(info);
        monitorBuilder = new AlertDialog.Builder(this);
        monitorBuilder.setTitle(info.title);
        monitorBuilder.setView(layout);
        
        monitorShowDialog = monitorBuilder.create();
        monitorShowDialog.show();
        
	}
	
	
	public void showMonitor(MonitorInfo info){
		if(MainActivity.device!=DeviceType.STB){
			Intent intent = new Intent(MonitorActivity.this,MonitorShowActivity.class);
			intent.putExtra("fileName", info.title);
			intent.putExtra("videoUrl", info.monitoUrl);
			startActivity(intent);
		}else{
			Intent intent = new Intent(MonitorActivity.this,MonitorShowSZActivity.class);
			intent.putExtra("fileName", info.title);
			intent.putExtra("videoUrl", info.monitoUrl);
			startActivity(intent);
		}
		
		
		/*Intent intent = new Intent(MonitorActivity.this,MonitorShowSZActivity.class);
		intent.putExtra("fileName", info.title);
		intent.putExtra("videoUrl", info.monitoUrl);
		startActivity(intent);*/
		
//		if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
//			return;
//		surfaceContainer.setVisibility(View.VISIBLE);
//		mVideoView.setActivitySizeType(io.vov.vitamio.widget.VideoView.ActivitySizeType.MONITOR);
//		mVideoView.setTitle(info.title);
//		mVideoView.setVideoPath(info.monitoUrl);
//		mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
//		mVideoView.setMediaController(new MediaController(this));
//		mVideoView.setOnCompletionListener(new OnCompletionListener(){
//			@Override
//			public void onCompletion(MediaPlayer arg0) {
//				surfaceContainer.setVisibility(View.INVISIBLE);
//			}
//			
//		});
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (mVideoView != null)
			mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_ZOOM, 0);
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onDestroy() {
		clearVideoView();
		super.onDestroy();
	}
	
	public void clearVideoView(){
		if(mVideoView!=null){
			mVideoView.destroyDrawingCache();
		}
	}
}
