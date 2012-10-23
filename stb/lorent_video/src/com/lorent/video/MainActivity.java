package com.lorent.video;



import java.util.ArrayList;
import java.util.List;

//import org.videolan.vlc.AudioService;
//import org.videolan.vlc.AudioServiceController;
//import org.videolan.vlc.MediaLibrary;
//import org.videolan.vlc.VLCCallbackTask;
//import org.videolan.vlc.widget.AudioMiniPlayer;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.lorent.video.bean.VideoInfo;

public class MainActivity extends Activity {

	private GridView gridView;
	VideoInfoAdapter adapter;
	private static final int DIALOG_PROGRESS = 0;
	List<VideoInfo> datas;
	private ProgressDialog mProgressDialog;
	private final static String TAG = "main";
	private int currentPage = 1;
//	private AudioMiniPlayer mAudioPlayer;
//    private AudioServiceController mAudioController;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, this.findViewById(R.id.gridview).getClass().toString());
        
        if(this.findViewById(R.id.gridview) instanceof GridView){
        	gridView = (GridView)this.findViewById(R.id.gridview);
        }
        
        datas = new ArrayList<VideoInfo>();
        gridView.setOnItemClickListener(new GridViewClickListener());
        show();
        
//        mAudioPlayer = new AudioMiniPlayer();
//        mAudioController = AudioServiceController.getInstance();
//        mAudioPlayer.setAudioPlayerControl(mAudioController);
//        mAudioPlayer.update();
    }
    
    protected void onStart () {  
        super.onStart();  
        new GetGridDataTask().execute();//执行获取数据的任务  
    }  
      
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mAudioController.addAudioPlayer(mAudioPlayer);
//        AudioServiceController.getInstance().bindAudioService(this);
//
//        /* FIXME: this is used to avoid having MainActivity twice in the backstack */
//        if (getIntent().hasExtra(AudioService.START_FROM_NOTIFICATION))
//            getIntent().removeExtra(AudioService.START_FROM_NOTIFICATION);
//
//    }  
      
      
    @Override  
    protected Dialog onCreateDialog(int id) {  
        switch (id) {  
        case DIALOG_PROGRESS:  
            mProgressDialog = new ProgressDialog(MainActivity.this);  
            mProgressDialog.setMessage("正在获取数据");  
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  
  
            return mProgressDialog;  
        }  
        return null;  
    }
    
    class GetGridDataTask extends AsyncTask<Void, Void, Void> {  
        
        protected void onPreExecute () {  
            datas.clear();  
            adapter.notifyDataSetChanged();  
              
            showDialog(DIALOG_PROGRESS);//打开等待对话框  
        }  
          
        @Override  
        protected Void doInBackground(Void... params) {  
              
            try {  
                Thread.sleep(2000);//模拟耗时的网络操作  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }
            
            datas = getVideoInfo(currentPage);
            adapter.setDatas(datas);  
            return null;  
        }  
          
        protected void onPostExecute (Void result) {  
        	adapter.notifyDataSetChanged();//通知ui界面更新  
            dismissDialog(DIALOG_PROGRESS);//关闭等待对话框  
        }  
          
    }  
    
    private class GridViewClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
//			VideoInfo item = (VideoInfo) arg0.getItemAtPosition(arg2);
//			setTitle((String)item.getTitle());
//			Intent intent = new Intent(MainActivity.this,ShowVideoActivity.class);
//			intent.putExtra("videoName", "flvplayer.flv");
//			startActivity(intent);
			
//			VLCCallbackTask task = new VLCCallbackTask(MainActivity.this)
//            {
//                @Override
//                public void run() {
//                  AudioServiceController c = AudioServiceController.getInstance();
//                  String s = "rtsp://10.168.250.12:554/sample_100kbit.mp4";//input.getText().toString();
//
//                  /* Use the audio player by default. If a video track is
//                   * detected, then it will automatically switch to the video
//                   * player. This allows us to support more types of streams
//                   * (for example, RTSP and TS streaming) where ES can be
//                   * dynamically adapted rather than a simple scan.
//                   */
//                  ArrayList<String> media = new ArrayList<String>();
//                  media.add(s);
//                  c.append(media);
//                }
//            };
//            task.execute();
		}
    	
    }
    
    private void show(){
//    	datas = getVideoInfo();
    	adapter = new VideoInfoAdapter(this,datas,R.layout.video_item,gridView);
    	gridView.setAdapter(adapter);
    }
    
    private List<VideoInfo> getVideoInfo(int currentPage){
    	int startP = (currentPage-1) * 30;
    	int endP = currentPage * 30;
    	if(datas==null)datas = new ArrayList<VideoInfo>();
    	for(int i=startP;i<endP;i++){
    		VideoInfo info = new VideoInfo();
    		info.setImageUrl(""+i);
    		info.setTitle("title"+i);
    		datas.add(info);
    	}
    	return datas;
    }

    public void showVideo(View v){
    	Intent intent = new Intent(this,ShowVideoActivity.class);
    	intent.putExtra("videoName", "flvplayer.flv");
    	this.startActivity(intent);
    }
    
    
    
    public void loadData(View v){
    	if(v.getId()==R.id.previousbutton){
    		currentPage--;
    	}else{
    		currentPage++;
    	}
    	new GetGridDataTask().execute();//执行获取数据的任务  
    }
}
