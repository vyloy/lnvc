package com.lorent.video.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import com.lorent.video.MainActivity;
import com.lorent.video.R;

public class MeasureUtil {

	public static int getPageSize(MainActivity activity){
		DisplayMetrics metrics = new DisplayMetrics();

		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		int widthPx = metrics.widthPixels;
		int heightPx = metrics.heightPixels;

		// 取得当前屏幕的宽度
		int screenwDp = (int)(widthPx/metrics.density);
		int screenhDp = (int)(heightPx/metrics.density);
		
		LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View convertView = inflater.inflate(R.layout.video_item, null);
//		TypedArray a = activity.obtainStyledAttributes(attrs,R.styleable.ListView, defStyle, 0);
		float width = activity.getResources().getDimension(R.dimen.gridview_columnWidth);
		float hs = activity.getResources().getDimension(R.dimen.gridview_horizontalSpacing);
		
		int itemWidth = (int)(width/metrics.density);//Integer.parseInt(activity.getResources().getString(R.string.item_imageview_width).replaceAll("dp", ""));
//		int itemHeight = Integer.parseInt(activity.getResources().getString(R.string.item_imageview_height).replaceAll("dp", ""));
		int horizontalSpacing = (int)(hs/metrics.density);//Integer.parseInt(activity.getResources().getString(R.string.item_horizontalSpacing));
//		int verticalSpacing = Integer.parseInt(activity.getResources().getString(R.string.item_verticalSpacing));
		// 获取每行最多显示几列
		int c = (screenwDp - horizontalSpacing)/(itemWidth + horizontalSpacing);
//		int r = (screenwDp - horizontalSpacing)/(itemWidth + horizontalSpacing);
		return c * 3;
	}
	
}
