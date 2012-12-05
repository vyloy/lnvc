package com.lorent.video;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lorent.common.dto.MonitorInfo;

public class MonitorInfoAdapter extends BaseAdapter {

	private List<MonitorInfo> datas;
	private int resource;
	private LayoutInflater inflater;
	private Context context;
	
	
	public MonitorInfoAdapter(Context context,List<MonitorInfo> datas,int resource){
		this.datas = datas;
		this.resource = resource;
		this.context = context;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
	
	class CacheView{
		public TextView titleV;
		public TextView urlV;
		public Button button;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CacheView cacheView;
		if(convertView==null){
			convertView = inflater.inflate(resource, null);
			cacheView = new CacheView();
			cacheView.titleV = (TextView)convertView.findViewById(R.id.monitorTitleT);
			cacheView.urlV = (TextView)convertView.findViewById(R.id.monitorUrlT);
			cacheView.button = (Button)convertView.findViewById(R.id.deleteMonitorB);
			convertView.setTag(cacheView);
		}
		cacheView = (CacheView)convertView.getTag();
		MonitorInfo info = datas.get(position);
		cacheView.titleV.setText(info.title);
		cacheView.urlV.setText(info.monitoUrl);
		cacheView.button.setTag(info);
		return convertView;
	}

}
