package com.lorent.video;


import io.vov.utils.StringUtils;
import com.lorent.video.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.lorent.video.util.DialogUtil;
import com.lorent.video.util.TimeUtil;
import com.lorent.video.util.VideoScaleUtil;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
//与SurfaceView相关的包
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
//与MediaPlayer相关的包
import android.media.MediaPlayer;
//播放完毕
import android.media.MediaPlayer.OnCompletionListener;
//error信息
import android.media.MediaPlayer.OnErrorListener;
//info信息
import android.media.AudioManager;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;

public class SurfaceViewPlayVideo extends Activity implements
		OnCompletionListener, OnErrorListener, OnInfoListener,
		OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener,
		SurfaceHolder.Callback, OnBufferingUpdateListener {
	private static final String TAG = "SurfaceViewPlayVideo";
	private Display currentDisplay = null;
	private SurfaceView surfaceView = null;
	private SurfaceHolder surfaceHoler = null;
	private MediaPlayer mPlayer = null;
	private int videoWidth = 0, videoHeight = 0;
	private boolean readyToPlay = false;
	private LayoutInflater inflater;
	private View mediacontrollerView;
	private ImageButton playBtn;
	private RelativeLayout rlayout;
	private TextView currentTimeTextView;
	private TextView totalTimeTextView;
	private SeekBar seekBar;
	private TextView fileNameTextView;
	private TimerTask mTimerTask;
	private boolean isChanging=false;//互斥变量，防止定时器与SeekBar拖动时进度冲突
	private Timer mTimer;
	private FrameLayout frameLayout;
	private RelativeLayout controlLayout;
	private ControlHandler controlHandler = new ControlHandler();
	private static final int FADE_OUT = 1;
	private static final int SHOW_PROGRESS = 2;
	private static final int defaultTimeout = 3000;
	private PlayHandler playHandler = new PlayHandler();
	private int currentP;
	private int videoTimeLen;
	private boolean isDragSeekBar = false;
	private AudioManager mAM;
	private boolean mCanPause;
    private boolean mCanSeekBack;
    private boolean mCanSeekForward;
    private String filePath;
    private ProgressDialog dialog;
    private boolean isExit = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.videosurfaceview);
		frameLayout = (FrameLayout)findViewById(R.id.FrameLayout1);
		// SurfaceView设置
		surfaceView = (SurfaceView) findViewById(R.id.videoSurfaceView);
		surfaceHoler = surfaceView.getHolder();
		surfaceHoler.addCallback(this);
		surfaceHoler.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//		surfaceHoler.setFixedSize(100, 100);
//        getWindow().clearFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		
		// MediaPlayer设置
		mPlayer = new MediaPlayer();
		mPlayer.setOnCompletionListener(this);
		mPlayer.setOnErrorListener(this);
		mPlayer.setOnInfoListener(this);
		mPlayer.setOnPreparedListener(this);
		mPlayer.setOnSeekCompleteListener(this);
		mPlayer.setOnVideoSizeChangedListener(this);
		// 播放歌曲路径
		Intent intent = this.getIntent();
		String videoUrl = intent.getExtras().getString("videoUrl");
		String fileName = intent.getExtras().getString("fileName");
		Log.i("videoUrl", videoUrl);
		filePath = videoUrl;//"rtsp://10.168.250.12:554/z.mp4";//"rtsp://10.168.250.12:554/sample_100kbit.mp4";//"rtsp://10.168.250.12:554/z.mp4";//"rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp";//Environment.getExternalStorageDirectory().getPath() + "/a.3gp";
		
		
		currentDisplay = getWindowManager().getDefaultDisplay();
		
//		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		mediacontrollerView = inflater.inflate(R.layout.stbvideoview, null);
//		setContentView(R.layout.videosurfaceview);
		
		controlLayout = (RelativeLayout)findViewById(R.id.controlLayout);
		playBtn = (ImageButton)findViewById(R.id.stb_play_pause);
		currentTimeTextView = (TextView)findViewById(R.id.stb_time_current);
		totalTimeTextView = (TextView)findViewById(R.id.stb_time_total);
		seekBar = (SeekBar)findViewById(R.id.stb_seekbar);
		fileNameTextView = (TextView)findViewById(R.id.stb_file_name);
		fileNameTextView.setText(fileName);
		mTimerTask = new TimerTask() {
			@Override
			public void run() {
				try{
					if(mPlayer!=null && mPlayer.isPlaying()){
						Message message = playHandler.obtainMessage(0, null);
						playHandler.sendMessage(message);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		};
		mTimer = new Timer();
		mTimer.schedule(mTimerTask, 0, 1000);
		controlLayout.setVisibility(View.INVISIBLE);
//		controlLayout.setOnTouchListener(new ControlOnTouchListener());
		seekBar.setOnSeekBarChangeListener(new PlayOnSeekBarChangeListener());
		seekBar.setEnabled(true);
		mAM = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		dialog = DialogUtil.newLoadingVideoDialog(this, fileName + this.getResources().getText(R.string.loadingvideo), this.getResources().getText(R.string.pleasewait));
		dialog.show();
		
	}
	
	private class PlayOnSeekBarChangeListener implements  OnSeekBarChangeListener{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
//			Log.i(TAG, "拖动进度条中");
//			long newposition = (videoTimeLen * progress) / 1000;
//			mPlayer.seekTo((int)newposition);
//			currentTimeTextView.setText(TimeUtil.convertMillisecond((int)newposition));
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			Log.i(TAG, "开始拖动进度条");
			isDragSeekBar = true;
			mPlayer.pause();
			controlShow(60*60*1000);
//			mAM.setStreamMute(AudioManager.STREAM_MUSIC, true);
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
//			Log.i(TAG, "结束拖动进度条:" + (videoTimeLen * seekBar.getProgress()) / 1000);
			Log.i(TAG, "结束拖动进度条:" + seekBar.getProgress());
//			play((videoTimeLen * seekBar.getProgress()) / 1000);
			play(seekBar.getProgress());
			controlShow(defaultTimeout);
//			mAM.setStreamMute(AudioManager.STREAM_MUSIC, false);
			
		}
		
	}
	
	public void playBtnProccess(View v){
		if(mPlayer!=null){
			if(mPlayer.isPlaying()){
				mPlayer.pause();
				currentP = mPlayer.getCurrentPosition();
				playBtn.setImageResource(R.drawable.mediacontroller_play_button);
			}else{
				
				play(currentP);
				playBtn.setImageResource(R.drawable.mediacontroller_pause_button);
				
			}
		}
		controlShow(defaultTimeout);
	}
	
	public void play(int po){
		
		try {
			dialog.show();
			currentP = po;
			mPlayer.reset();
			mPlayer.setDataSource(filePath);
			mPlayer.setDisplay(surfaceHoler);
			mPlayer.prepareAsync();
//			dialog = ProgressDialog.show(this, "视频加载中...", "请您稍候");
//			dialog.dismiss();
			
//			dialog.setOnCancelListener(new DialogOnCancelListener());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private class DialogOnCancelListener implements OnCancelListener{

		@Override
		public void onCancel(DialogInterface dialog) {
			releaseRes();
			finish(); 
		}
		
	}
	
	private class ControlOnTouchListener implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			Log.i(TAG, "touch1");
			controlShow(defaultTimeout);
			Log.i(TAG, "touch2");
			return true;
		}
		
	}
	
	public void showControlView(View v){
//		controlShow(defaultTimeout);
	}
	
	
	private void controlShow(int timeout){
//		controlHandler.removeMessages(SHOW_PROGRESS);
//		controlHandler.sendEmptyMessage(SHOW_PROGRESS);
		if(controlLayout.getVisibility()==View.INVISIBLE){
			controlLayout.setVisibility(View.VISIBLE);
		}
		if (timeout != 0) {
			Log.i(TAG, "xiaoshi1111");
			controlHandler.removeMessages(FADE_OUT);
			controlHandler.sendMessageDelayed(controlHandler.obtainMessage(FADE_OUT), timeout);
		}
	}
	
	private class PlayHandler extends Handler{
		public void handleMessage(Message message) {
			if(isDragSeekBar ||mPlayer==null||!mPlayer.isPlaying())
				return;
			int position = mPlayer.getCurrentPosition();
			long duration = mPlayer.getDuration();
			if(position<currentP){
				return;
			}
			Log.i(TAG, position + ":" +duration);
			if (seekBar != null) {
				if (duration > 0) {
//					long pos = 1000L * position / duration;
					seekBar.setProgress((int) position);
				}
			}
			if(currentTimeTextView!=null){
				currentTimeTextView.setText(TimeUtil.convertMillisecond((int)position));
			}
        }
	}
	
	private class ControlHandler extends Handler{
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case FADE_OUT:
				if(controlLayout.getVisibility() == View.VISIBLE){
					Log.i(TAG, "xiaoshisss");
					controlHandler.removeMessages(SHOW_PROGRESS);
					controlLayout.setVisibility(View.INVISIBLE);
					
				}
				break;
			case SHOW_PROGRESS:
				controlLayout.setVisibility(View.VISIBLE);
				break;
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	// create方法进行准备
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.v(TAG, "surfaceCreated method is called!!");
		mPlayer.reset();
		try {
			mPlayer.setDataSource(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mPlayer.setDisplay(surfaceHoler);
		mPlayer.prepareAsync();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
	}

	@Override
	public void onSeekComplete(MediaPlayer mp) {
	}

	// 播放处理
	@Override
	public void onPrepared(MediaPlayer mp) {
		
		 // Get the capabilities of the player for this stream
//        Metadata data = mp.getMetadata(MediaPlayer.METADATA_ALL
//                                  MediaPlayer.BYPASS_METADATA_FILTER);
//        if (data != null) {
//            mCanPause = !data.has(Metadata.PAUSE_AVAILABLE)
//                    || data.getBoolean(Metadata.PAUSE_AVAILABLE);
//            mCanSeekBack = !data.has(Metadata.SEEK_BACKWARD_AVAILABLE)
//                    || data.getBoolean(Metadata.SEEK_BACKWARD_AVAILABLE);
//            mCanSeekForward = !data.has(Metadata.SEEK_FORWARD_AVAILABLE)
//                    || data.getBoolean(Metadata.SEEK_FORWARD_AVAILABLE);
//        } else {
//            mCanPause = mCanSeekBack = mCanSeekForward = true;
//        }
		videoTimeLen = mPlayer.getDuration();
		DialogUtil.dismissDialog(dialog);
		
		seekBar.setMax(videoTimeLen);
		// 全屏播放
		 int mvideoHeight = currentDisplay.getHeight();
		 int mvideoWidth = currentDisplay.getWidth();
		// 按视频本身大小播放
		videoWidth = mPlayer.getVideoWidth();//mvideoWidth;//mPlayer.getVideoWidth();
		videoHeight = mPlayer.getVideoHeight();//mvideoHeight;//mPlayer.getVideoHeight();
		int result[] = VideoScaleUtil.computeScale(mvideoWidth, mvideoHeight, videoWidth, videoHeight);
		videoWidth = result[0];
		videoHeight = result[1];
		// 在此可以根据情况进行缩放播放设置
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				videoWidth, videoHeight);
		// 居中
		lp.gravity = Gravity.CENTER_HORIZONTAL;
		surfaceView.setLayoutParams(lp);
//		SurfaceHolder mHold = surfaceView.getHolder();
//		mHold.setFixedSize(videoWidth, videoHeight);
//        getWindow().clearFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//        mPlayer.setDisplay(mHold);
		mPlayer.start();
		mPlayer.seekTo(currentP);
		isDragSeekBar = false;
		
		totalTimeTextView.setText(TimeUtil.convertMillisecond(videoTimeLen));
	}

	// Info 信息处理
	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		Log.v(TAG, "onInfo method is called!!");
		if (what == MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING) {
			Log.v(TAG, "Media Info, Media Info Bad Interleaving " + extra);
		} else if (what == MediaPlayer.MEDIA_INFO_NOT_SEEKABLE) {
			Log.v(TAG, "Media Info, Media Info Not Seekable " + extra);
		} else if (what == MediaPlayer.MEDIA_INFO_UNKNOWN) {
			Log.v(TAG, "Media Info, Media Info Unknown " + extra);
		} else if (what == MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING) {
			Log.v(TAG, "MediaInfo, Media Info Video Track Lagging " + extra);
		} else if (what == MediaPlayer.MEDIA_INFO_METADATA_UPDATE) {
			Log.v(TAG, "MediaInfo, Media Info Metadata Update " + extra);
		}
		return false;
	}

	// Error信息处理
	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Log.v(TAG, "onError method is called!!");
		if (what == MediaPlayer.MEDIA_ERROR_SERVER_DIED) {
			Log.v(TAG, "Media Error,Server Died" + extra);
		} else if (what == MediaPlayer.MEDIA_ERROR_UNKNOWN) {
			Log.v(TAG, "Media Error,Error Unknown" + extra);
		}
		releaseRes();
		DialogUtil.dismissDialog(dialog);
		Toast toast = Toast.makeText(this, R.string.playvideofail, 5000);
		toast.setGravity(Gravity.CENTER,0,0);
		toast.show();
		return false;
	}

	// 播放完毕后,finish
	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.v(TAG, "onCompletion method is called!!");
		playBtn.setImageResource(R.drawable.mediacontroller_play_button);
		currentP = 0;
		this.controlLayout.setVisibility(View.VISIBLE);
		releaseRes();
		finish();
	}

	// Activty销毁释放资源
	@Override
	protected void onDestroy() {
		releaseRes();
		super.onDestroy();
		
	}
	
	private void releaseRes(){
		new Thread(){
			public void run(){
				if(mPlayer!=null){
					try{
						if(mPlayer.isPlaying()){
							mPlayer.stop();
						}
						mPlayer.release();
						mPlayer = null;
					}catch(Exception ex){
						
					}
					
				}
				if(mTimer!=null){
					mTimer.cancel();
					mTimer = null;
				}
			}
		}.start();
	}
	
	
	

	
	public void onBufferingUpdate(MediaPlayer arg0, int percent) {
        Log.i(TAG, "onBufferingUpdate percent:" + percent);
    }
}
