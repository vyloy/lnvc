package com.phonecommand;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PhoneCommander implements Runnable {

	public static final int KEY_STAR = 1002;
	public static final int KEY_PLUS = 1003;
	public static final int KEY_CALL = 1000;
	public static final int KEY_HANGUP = 1001;
	public static final int KEY_CLEAN = 1004;
	public static final String PHONE_COMMAND_ACTION = "com.lorent.phonecommand";
	
	private static PhoneCommander instance;
	public static PhoneCommander getInstance(Context context){
		if(instance == null){
			instance = new PhoneCommander(context);
		}
		return instance;
	}
	
     DatagramSocket socket;
     DatagramPacket packet;// 从客户端接收到的UDP包
     DatagramPacket sendPkt;// 转发给另一个客户端的UDP包
     private static final String TAG = "PhoneCommandReceiver";

     byte[] pktBuffer = new byte[320];
     int bufferSize = 320;
     boolean isRunning = false;
     int myport = 5657;
     private static FileOutputStream fos;
     private Context context;
     
     private PhoneCommander(Context context) {
         try {
        	 this.context = context;
             socket = new DatagramSocket(myport);
             packet = new DatagramPacket(pktBuffer, bufferSize);

         } catch (Exception e) {
        	 Log.e(TAG, "PhoneCommandReceiver()", e);
         }
         Log.i(TAG, "server initializationg finish!");
     }

     public void startServer() {
    	 if(!this.isRunning){
	         this.isRunning = true;
	         new Thread(this).start();
    	 }
     }

     public void run() {
         try {
             while (isRunning) {
//            	 Log.i(TAG, "Number receive before");
                 socket.receive(packet);
//                 Log.i(TAG, "Number receive after");
//                 Log.i(TAG, "Number ClientAddress:"+packet.getSocketAddress());
                 byte[] temp = new byte[8];
                 temp = packet.getData();
                 int head=BytesToInt(temp,0);
                 int number=BytesToInt(temp,4);
//                 Log.i(TAG, "Number head="+head);
                 Log.i(TAG, "Number number="+number);
                 if(number < 1000){
                	 execute2(number);                	 
                 }else{
                	 Intent intent = new Intent(PHONE_COMMAND_ACTION);
                	 intent.putExtra("key", number);
                	 context.sendBroadcast(intent);
                 }
             }
 			fos.close();
         } catch (IOException e) {
        	 Log.e(TAG, "run()", e);
         }
     }
     
     private synchronized void execute(int num){
    	 try{
			Process p = Runtime.getRuntime().exec(
					new String[] { "sendevent", "/dev/input/event0",
							"1", num + "", "1" });
			p.waitFor();
			p.getErrorStream().close();
			p.getInputStream().close();
			p.getOutputStream().close();
			p = Runtime.getRuntime().exec(
					new String[] { "sendevent", "/dev/input/event0",
							"1", num + "", "0" });
			p.waitFor();
			p.getErrorStream().close();
			p.getInputStream().close();
			p.getOutputStream().close();
    	 }catch(Exception e){
    		 Log.e(TAG, "execute", e);
    	 }
     }
     
     private void execute2(int num){
    	 	if(fos == null){
    	 		try{
	     			File file = new File("/dev/input/event0"); //"/dev/input/event0
	     			fos  = new FileOutputStream(file);
    	 		}catch(Exception e){
    	 			Log.e(TAG, "execute2", e);
    	 		}
    	 	}
			
			byte[] bs = new byte[16];
			for(int i = 0; i < 8; i++){
				bs[i] = 0;
			}
			bs[8] = 1;
			bs[9] = 0;
			
			bs[10] = (byte)num;
			bs[11] = 0;
			
			bs[12] = 1;
			bs[13] = 0;
			bs[14] = 0;
			bs[15] = 0;
			
			try{
				fos.write(bs);
				fos.flush();
				
				bs[12] = 0;
				fos.write(bs);
				fos.flush();
			}catch(Exception e){
				Log.e(TAG, "execute2", e);
			}
			

     }
     
     public  int BytesToInt(byte[] buf,int pos) {  
 		int firstByte = 0;  	
 		int secondByte = 0;  
 		int thirdByte = 0;  
 		int fourthByte = 0;  			
 		int index = pos;  	
 		firstByte=(0x000000FF&((int) buf[index]));  	
 		secondByte=(0x000000FF&((int) buf[index+1]));  	
 		thirdByte=(0x000000FF & ((int)buf[index+2]));  	
 		fourthByte=(0x000000FF&((int)buf[index+3]));  	
 		return ((int)(fourthByte<<24|thirdByte<<16|secondByte<<8|firstByte));  
     }  
     
}