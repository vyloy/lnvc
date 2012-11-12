package com.lorent.video;
import com.lorent.video.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


public class StbShowVideoActivity extends Activity {

	private VideoView videoView;
	private MediaController mediaController;
	private Dialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.stbvideoview);
		videoView = (VideoView)this.findViewById(R.id.stbvideoview);
		mediaController = new MediaController(this,false); 
//		
//        videoView.setVideoPath("rtsp://10.168.250.12:554/jack.mp4");
//        // 设置VideView与MediaController建立关联  
//        videoView.setMediaController(mediaController);  
//        // 设置MediaController与VideView建立关联  
//        mediaController.setMediaPlayer(videoView);  
//        // 开始播放  
//        videoView.start();
//     // 让VideoView获取焦点  
//        videoView.requestFocus();
        
//      VideoView w = (VideoView)findViewById(R.id.videoView1);
		dialog = ProgressDialog.show(this, "视频加载中...", "请您稍候");
        videoView.setVideoPath("rtsp://10.168.250.12:554/jack.mp4");
        //设置媒体控制条
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
//        videoView.start();
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MyOnPreparedListener());
        videoView.setOnErrorListener(new MyOnErrorListener());
	}
	
	private class MyOnErrorListener implements OnErrorListener{

		@Override
		public boolean onError(MediaPlayer mp, int what, int extra) {
			dialog.dismiss();
			Toast.makeText(StbShowVideoActivity.this, R.string.playvideofail, Toast.LENGTH_LONG).show();
			StbShowVideoActivity.this.finish();
			return true;
		}
		
	}
	
	private class MyOnPreparedListener implements OnPreparedListener{

		@Override
		public void onPrepared(MediaPlayer mp) {
			dialog.dismiss();
			videoView.start();
		}
		
	}

}
