package com.lorent.vovo.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public class CallHistory {

	private static final byte COUNT=50;
	
	private static final Charset charset=Charset.forName("utf-8");
	
	private static final String PHONE="12345678901234567890";
	
	private static final int PHONE_LENGTH=PHONE.getBytes(charset).length;
	
	private byte position;
	
	private byte size;
	
	private RandomAccessFile r;
	
	private List<CallHistoryItem> temp;
	
	private static final CallHistory instance=new CallHistory();
	
	private CallHistory(){}
	
	public static CallHistory getInstance(){
		return instance;
	}

	public List<CallHistoryItem> init() throws IOException{
		File path = new File(System.getProperty("user.dir")+File.separator+"users");
		if(!path.exists()){
			path.mkdirs();
		}
		File file = new File(path,DataUtil.getUserName()+".phoneHistory");
		if(file.exists()){
			r = new RandomAccessFile(file, "rwd");
		}else{
			r = new RandomAccessFile(file, "rwd");
			r.writeByte(0);//size
			r.writeByte(0);//position
			byte[] bytes = PHONE.getBytes(charset);
			for(int i=0;i<COUNT;i++){
				r.writeInt(CallInfo.INVALID.mask);
				r.write(bytes);
				r.writeLong(0L);
			}
			r.seek(0L);
		}
		size = r.readByte();
		position = r.readByte();
		return getAll();
	}
	
	public List<CallHistoryItem> getAll() throws IOException{
		LinkedList<CallHistoryItem> result = new LinkedList<CallHistoryItem>();
		r.seek(0L);
		byte size = r.readByte();
		if(size!=0){
			byte position = r.readByte();
			for(byte i=0;i<size;i++){
				position=getPreviousPosition(position);
				long rPos=getRealPosition(position);
				r.seek(rPos);
				CallHistoryItem phoneHistoryItem = new CallHistoryItem();
				phoneHistoryItem.info=r.readInt();
				byte[] data=new byte[PHONE_LENGTH];
				r.read(data);
				phoneHistoryItem.phone=new String(new String(data,charset).trim());
				phoneHistoryItem.date=r.readLong();
				result.add(phoneHistoryItem);
			}
		}
		temp=result;
		return result;
	}
	
	public void put(CallHistoryItem item) throws IOException{
		if(size+1<=COUNT){
			r.seek(0L);
			r.writeByte(++size);
		}
		
		long rp = getRealPosition(position);
		r.seek(rp);
		
		r.writeInt(item.info);
		
		while(item.phone.length()<PHONE.length()){
			item.phone+=" ";
		}
		r.write(item.phone.getBytes(charset));
		
		r.writeLong(item.date);
		
		r.seek(1L);
		position = getNextPosition(position);
		r.writeByte(position);
		temp.add(0, item);
	}
	
	public List<CallHistoryItem> getTemp(){
		return temp;
	}
	
	private byte getPreviousPosition(byte position){
		return --position==-1?COUNT-1:position;
	}
	
	private byte getNextPosition(byte position){
		return ++position==COUNT?0:position;
	}
	
	private long getRealPosition(byte position){
		return position*(4+PHONE_LENGTH+8)+2;
	}
	
	public void close() throws IOException{
		r.close();
	}
	
	public static class CallHistoryItem{
		int info;
		String phone;
		long date;
		
		private CallHistoryItem(){}
		
		public CallHistoryItem(int info, String phone, long date) {
			this.info = info;
			this.phone = phone;
			this.date = date;
		}

		public int getInfo() {
			return info;
		}

		public String getPhone() {
			return phone;
		}

		public long getDate() {
			return date;
		}

		@Override
		public String toString() {
			return "PhoneHistoryItem [info=" + info + ", phone=" + phone
					+ ", date=" + date + "]";
		}
		
	}
	
	public static enum CallInfo{
		INVALID,CALL_IN,CALL_OUT,MISSED_CALL;
		public final int mask;

		private CallInfo() {
			this.mask = 1<<ordinal();
		}
		
	}
}
