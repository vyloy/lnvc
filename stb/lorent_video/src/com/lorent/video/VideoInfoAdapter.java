package com.lorent.video;

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

	private List<VideoInfo> datas;
	private int resource;
	private LayoutInflater inflater;
	private Context context;
	private static final String TAG = "VideoInfoAdapter";
	private AsyncImageLoader asyncImageLoader;
	private GridView gridView;
	
	public VideoInfoAdapter(Context context,List<VideoInfo> datas,int resource,GridView gridView){
		this.datas = datas;
		this.resource = resource;
		this.context = context;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		asyncImageLoader = new AsyncImageLoader();
		this.gridView = gridView;
	}
	
	
	public void setDatas(List<VideoInfo> datas){
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
		imageView = cacheView.imageView;
		textView = cacheView.textView;
		VideoInfo info = datas.get(position);
		imageView.setTag(info.getImageUrl());
		Drawable cachedImage = asyncImageLoader.loadDrawable(info.getImageUrl(), new ImageCallback() {  
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {  
                ImageView imageViewByTag = (ImageView) gridView.findViewWithTag(imageUrl);  
                if (imageViewByTag != null) {  
//                    imageViewByTag.setImageDrawable(imageDrawable);  
                }  
            }  
        });  
        if (cachedImage == null) {  
            imageView.setImageResource(R.drawable.ic_launcher);  
        }else{  
            imageView.setImageDrawable(cachedImage);  
        }
//		imageView.setImageResource(R.drawable.ic_launcher);
		textView.setText(info.getTitle());
		Log.i(TAG, position+"");
		return convertView;
	}
	
	class CacheView{
		public ImageView imageView;
		public TextView textView;
	}

}
