package com.lorent.video;

import com.lorent.common.dto.LCMVideoClip;
import com.lorent.video.R;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lorent.video.bean.VideoInfo;
import com.lorent.video.util.AsyncImageLoader;
import com.lorent.video.util.AsyncImageLoader.ImageCallback;

public class VideoInfoAdapter extends BaseAdapter {

	private List<LCMVideoClip> datas;
	private int resource;
	private LayoutInflater inflater;
	private Context context;
	private static final String TAG = "VideoInfoAdapter";
	private AsyncImageLoader asyncImageLoader;
	private GridView gridView;
//	private static boolean flag = true;
//	
//	public static void setFlag(boolean f){
//		flag = f;
//	} 
	
	public VideoInfoAdapter(Context context,List<LCMVideoClip> datas,int resource,GridView gridView){
		this.datas = datas;
		this.resource = resource;
		this.context = context;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		asyncImageLoader = new AsyncImageLoader(context);
		this.gridView = gridView;
	}
	
	
	public void setDatas(List<LCMVideoClip> datas){
		this.datas = datas;
	}
	
	@Override
	public int getCount() {
		return datas!=null?datas.size():0;
	}

	@Override
	public Object getItem(int position) {
		return datas!=null?datas.get(position):null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	

//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//		ImageView imageView;
//		TextView textView;
//		CacheView cacheView;
//		if(convertView==null){
//			convertView = inflater.inflate(resource, null);
//			cacheView = new CacheView();
//			cacheView.imageView = (ImageView)convertView.findViewById(R.id.ItemImage);
//			cacheView.textView = (TextView)convertView.findViewById(R.id.ItemText);
//			convertView.setTag(cacheView);
//		}
//		cacheView = (CacheView)convertView.getTag();
//		final CacheView cacheView1 = cacheView;
//		imageView = cacheView.imageView;
//		textView = cacheView.textView;
//		final LCMVideoClip info = datas.get(position);
//		resetCacheView(cacheView);
//		Log.i("reflashimage", "clear:"+info.getId()+"");
//		imageView.setTag(info.getThumbnailUrl());
//		imageView.setImageResource(R.drawable.video_no_pic);
//		Log.i("LCMVideoClip", info.getThumbnailUrl());
//		asyncImageLoader.loadDrawable(position,info.getId(),info.getThumbnailUrl(), new ImageCallback() {  
//            public void imageLoaded(Drawable imageDrawable, String imageUrl) {  
//            	if (cacheView1.imageView != null) {  
//        			if (imageDrawable != null) {
//              		Log.i("reflashimage", "reflash:"+info.getId()+";"+imageDrawable.toString());
//              		cacheView1.imageView.setImageDrawable(imageDrawable);
//                  }else{
//                  	cacheView1.imageView.setImageResource(R.drawable.video_no_pic);
//                  }
//              	cacheView1.textView.setText(info.getTitle());
//              }
//                
//            }  
//        });  
////		imageView.setImageResource(R.drawable.ic_launcher);
//		textView.setText(info.getTitle());
////		textView.setText("的发的的发佛IAD风");
//		Log.i(TAG, position+"");
//		return convertView;
//	}
	
	static List<Integer> lstPosition=new ArrayList<Integer>();  
    static List<View> lstView=new ArrayList<View>();
    
    public static void clearCacheView(){
    	if(lstPosition!=null){
    		lstPosition.clear();
    	}
    	if(lstView!=null){
    		lstView.clear();
    	}
    }
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ImageView imageView = null;
		TextView textView = null;
		final LCMVideoClip info = datas.get(position);
		if (lstPosition.contains(position) == false) {  
            if(lstPosition.size()>75)//这里设置缓存的Item数量  
            {  
                lstPosition.remove(0);//删除第一项  
                lstView.remove(0);//删除第一项  
            }  
            convertView = inflater.inflate(resource, null);  
            textView = (TextView) convertView.findViewById(R.id.ItemText);  
            imageView = (ImageView) convertView.findViewById(R.id.ItemImage); 
              
            lstPosition.add(position);//添加最新项  
            lstView.add(convertView);//添加最新项  
        } else  
        {  
            convertView = lstView.get(lstPosition.indexOf(position));
//            textView = (TextView) convertView.findViewById(R.id.ItemText);  
//            textView.setText(info.getTitle());
//            Log.i(TAG, info.getTitle()+"bbb");
            return convertView;
        }
		Log.i("reflashimage", "clear:"+info.getId()+"");
		final ImageView imageView1 = imageView;
		Log.i("LCMVideoClip", info.getThumbnailUrl());
		asyncImageLoader.loadDrawable(position,info.getId(),info.getThumbnailUrl(), new ImageCallback() {  
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {  
            	if (imageView1 != null) {  
        			if (imageDrawable != null) {
              		Log.i("reflashimage", "reflash:"+info.getId()+";"+imageDrawable.toString());
              		imageView1.setImageDrawable(imageDrawable);
                  }else{
                	  imageView1.setImageResource(R.drawable.video_no_pic);
                  }
              }
                
            }  
        });
		Log.i(TAG, info.getTitle()+"aaaa");
//		imageView.setImageResource(R.drawable.ic_launcher);
		textView.setText(info.getTitle());
//		textView.setText("的发的的发佛IAD风");
		Log.i(TAG, position+"");
		return convertView;
	}
	
	private void resetCacheView(CacheView viewcache){
		viewcache.textView.setText(null);
		viewcache.imageView.setImageDrawable(null);
	}
	
	class CacheView{
		public ImageView imageView;
		public TextView textView;
	}

}
