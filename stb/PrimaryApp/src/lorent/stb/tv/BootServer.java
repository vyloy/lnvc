package lorent.stb.tv;

import java.text.SimpleDateFormat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootServer extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		 
		Intent myIntent=new Intent();//intent对象用于启动服务
        myIntent.setAction("lorent.stb.tv.MyServer");
        context.startService(myIntent);//开机 启动服务
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("启动service"+ sDateFormat.format(new java.util.Date()));
	}

}
