package my.launch;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class PhoneCommandReceiver implements Runnable {

	public static final int NUM_0 = 0;
	public static final int NUM_1 = 1;
	public static final int NUM_2 = 2;
	public static final int NUM_3 = 3;
	public static final int NUM_4 = 4;
	public static final int NUM_5 = 5;
	public static final int NUM_6 = 6;
	public static final int NUM_7 = 7;
	public static final int NUM_8 = 8;
	public static final int NUM_9 = 9;
	public static final int NUM_STAR = 10;
	public static final int NUM_PLUS = 11;
	public static final String BROAD_CAST_ACTION = "com.lorent.phonecommand";
	public static final String EXTRA_KEY = "key";
	
	
     DatagramSocket socket;
     DatagramPacket packet;// 从客户端接收到的UDP包
     DatagramPacket sendPkt;// 转发给另一个客户端的UDP包
     private static final String TAG = "PhoneCommandReceiver";

     byte[] pktBuffer = new byte[320];
     int bufferSize = 320;
     boolean isRunning = false;
     int myport = 5657;
     public PhoneCommandReceiver() {
         try {
             socket = new DatagramSocket(myport);
             packet = new DatagramPacket(pktBuffer, bufferSize);
         } catch (SocketException e) {
        	 Log.e(TAG, "PhoneCommandReceiver()", e);
         }
         Log.i(TAG, "server initializationg finish!");
     }

     public void startServer() {
         this.isRunning = true;
         new Thread(this).start();
     }

     public void run() {
         try {
             while (isRunning) {
            	 Log.i(TAG, "Number receive before");
                 socket.receive(packet);
                 Log.i(TAG, "Number receive after");
                 Log.i(TAG, "Number ClientAddress:"+packet.getSocketAddress());
                 byte[] temp = new byte[8];
                 temp = packet.getData();
                 int head=BytesToInt(temp,0);
                 int number=BytesToInt(temp,4);
                 Log.i(TAG, "Number head="+head);
                 Log.i(TAG, "Number number="+number);
                 execute(number);
             }
         } catch (IOException e) {
        	 Log.e(TAG, "run()", e);
         }
     }
     
     private void execute(int num){
    	 try{
			Process p = Runtime.getRuntime().exec(
					new String[] { "sendevent", "/dev/input/event0",
							"1", num + "", "1" });
			p.waitFor();
			p = Runtime.getRuntime().exec(
					new String[] { "sendevent", "/dev/input/event0",
							"1", num + "", "0" });
			p.waitFor();
    	 }catch(Exception e){
    		 Log.e(TAG, "execute", e);
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