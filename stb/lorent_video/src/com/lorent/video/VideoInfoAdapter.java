package com.lorent.video;

import com.lorent.common.dto.LCMVideoClip;
import com.lorent.video.R;

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
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		TextView textView;
		CacheView cacheView;
		if(convertView==null){
			convertView = inflater.inflate(resource, null);
			cacheView = new CacheView();
			cacheView.imageView = (ImageView)convertView.findViewById(R.id.ItemImage);
			cacheView.textView = (TextView)convertView.findViewById(R.id.ItemText);
			convertView.setTag(cacheView);
		}
		cacheView = (CacheView)convertView.getTag();
		resetCacheView(cacheView);
		imageView = cacheView.imageView;
		textView = cacheView.textView;
		LCMVideoClip info = datas.get(position);
		imageView.setTag(info.getThumbnailUrl());
		final ImageView imageView1 = imageView;
//		imageView.setImageResource(R.drawable.ic_launcher);
		Log.i("LCMVideoClip", info.getThumbnailUrl());
		asyncImageLoader.loadDrawable(info.getThumbnailUrl(), new ImageCallback() {  
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {  
//                ImageView imageView = (ImageView) gridView.findViewWithTag(imageUrl);
                if (imageView1 != null) {  
//                    imageViewByTag.setImageDrawable(imageDrawable);
                	if (imageDrawable != null) {
                		Log.i("reflashimage", imageDrawable.toString());
                		imageView1.setImageDrawable(imageDrawable);
                    }else{
                    	imageView1.setImageResource(R.drawable.video_no_pic);
                    }
                }
            }  
        });  
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
