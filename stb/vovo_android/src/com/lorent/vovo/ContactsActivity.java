package com.lorent.vovo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ContactsActivity extends Activity {

	public static final Uri FRIEND_TB_URI = Uri
			.parse("content://com.lorent.lcc/friend_tb");

	private ListView listView;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//	private SimpleAdapter adapter;
	private MyAdapter adapter;
	private EditText edit_num;

	private int selectedPos = 0;// 标示选中的项

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.contacts);
		edit_num = (EditText) findViewById(R.id.contact);

		listView = (ListView) findViewById(R.id.contact_listView);
//		adapter = new SimpleAdapter(this, getData(), R.layout.contact_item,
//				new String[] {"icon", "name", "num" }, new int[] { R.id.contact_icon, R.id.contact_name,
//						R.id.contact_num });
		getData();
		adapter = new MyAdapter(ContactsActivity.this);
		
		listView.setAdapter(adapter);

		/*listView.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
//				selectedPos = arg2;
//				list.get(arg2).put("icon", R.drawable.btn_radio_on_pressed);
//				adapter.notifyDataSetChanged();
				
                
			}
		});*/
	}

	private List<Map<String, Object>> getData() {
        if(list.size()>0)list.clear();
		Map<String, Object> map = null;

		Cursor cursor = ContactsActivity.this.getContentResolver().query(
				FRIEND_TB_URI, null, null, null, null);
		while (cursor.moveToNext()) {

			String num = cursor.getString(cursor.getColumnIndex("lccno"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			map = new HashMap<String, Object>();
			map.put("icon", R.drawable.btn_radio_off);
			map.put("name", name);
			map.put("num", num);
			list.add(map);
		}

		cursor.close();

		return list;
	}

	public void searchClick(View v) {
		try {
			String num = edit_num.getText().toString();
			System.out.println("num ---------------------:" + num);
			searchByNum(num);
		} catch (Exception e) {
			Toast.makeText(ContactsActivity.this, "请输入联系人号码！",
					Toast.LENGTH_SHORT).show();
		}
	}

	private void searchByNum(String lccno) {
		if (list.size() > 0)
			list.clear();
		Map<String, Object> map = null;

		Cursor cursor = ContactsActivity.this.getContentResolver().query(
				FRIEND_TB_URI, null, "lccno like ? or name like ? ",
				new String[] { "%" + lccno + "%", "%" + lccno + "%" }, null);
		while (cursor.moveToNext()) {

			String num = cursor.getString(cursor.getColumnIndex("lccno"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("num", num);
			list.add(map);
		}

		cursor.close();
		adapter.notifyDataSetChanged();

	}

	public void confirmOnClick(View v) {
		
		Map<String, Object> map_friend = list
		.get(selectedPos);
		
		String name = map_friend.get("name").toString();
		String num = map_friend.get("num").toString();
		String[] info = { name, num };
		Intent intent = new Intent();

		intent.putExtra("contact_info", info);
		ContactsActivity.this.setResult(RESULT_OK, intent);

		ContactsActivity.this.finish();
	}

	public void cancelOnClick(View v) {

		ContactsActivity.this.finish();
	}

	class MyAdapter extends BaseAdapter {

		Activity c;
		LayoutInflater mInflater;
		private int temp = -1;


		MyAdapter(Activity context) {
			c = context;
			mInflater = getLayoutInflater();
		}

		public int getCount() {
			return list.size();
		}

		public Object getItem(int position) {
			return list.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			final int pos = position;
			if (convertView == null) {
				
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.contact_item, null);
				
				holder.list_img = (RadioButton) convertView
				.findViewById(R.id.radioButton1);
				holder.list_num_txt = (TextView) convertView
				.findViewById(R.id.contact_num);
				holder.list_name_txt = (TextView) convertView
				.findViewById(R.id.contact_name);
				holder.list_img.setChecked(false);
				
				convertView.setTag(holder);
				
			}else {

	            holder = (ViewHolder) convertView.getTag();

	        }

			holder.list_num_txt.setText(list.get(position).get("num")
					.toString());
			holder.list_name_txt.setText(list.get(position).get("name")
					.toString());

			holder.list_img.setId(position);

		    holder.list_img.setOnCheckedChangeListener(new OnCheckedChangeListener() {

		            @Override

		            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		                if (isChecked) {

		                	System.out.println("pos = "+pos);
		                	selectedPos = pos;
		                    if (temp != -1) {

		                        RadioButton tempButton = (RadioButton) c.findViewById(temp);

		                        if (tempButton != null) {

		                            tempButton.setChecked(false);

		                        }

		                    }

		                    temp = buttonView.getId();

		                }

		            }

		        });

		 

		        if (position == temp) {

		            holder.list_img.setChecked(true);

		        } else {

		            holder.list_img.setChecked(false);

		        }



			return convertView;
		}

		class ViewHolder {
			RadioButton list_img;
			TextView list_num_txt;
			TextView list_name_txt;

		}

	}
}
