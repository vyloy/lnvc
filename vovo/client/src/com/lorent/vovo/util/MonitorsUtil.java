package com.lorent.vovo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;

import com.lorent.common.dto.LCMVideoClip;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.LoginInfo;


public class MonitorsUtil {
	private static TreeSet<Monitor> cache=new TreeSet<Monitor>();
	private static AtomicBoolean init=new AtomicBoolean();
	
	public synchronized static void add(Monitor m) throws FileNotFoundException, IOException{
		m.date=new Date().getTime();
		cache.add(m);
		LoginInfo info = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
		String path=Constants.USER_DATA_DIR+File.separator+"monitors"+File.separator+info.getUsername();
		File dic = new File(path);
		if(!dic.exists()){
			dic.mkdirs();
		}
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(dic,String.valueOf(m.date))));
		out.writeObject(m);
		out.flush();
		out.close();
	}
	
	public synchronized static void refresh() throws FileNotFoundException, IOException, ClassNotFoundException{
		cache.clear();
		LoginInfo info = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
		String path=Constants.USER_DATA_DIR+File.separator+"monitors"+File.separator+info.getUsername();
		File dic = new File(path);
		if(!dic.exists()){
			dic.mkdirs();
			return;
		}
		File[] files = dic.listFiles();
		if(null!=files){
			for (File file : files) {
				try{
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
					Object o = in.readObject();
					if(o instanceof Monitor)
						cache.add((Monitor) o);
					in.close();
				}catch(Exception e){
				}
			}
		}
	}
	
	public synchronized static TreeSet<Monitor> get() throws FileNotFoundException, IOException, ClassNotFoundException{
		if(init.get())
			return (TreeSet<Monitor>) cache.clone();
			
		if(init.compareAndSet(false, true))
			refresh();
		return (TreeSet<Monitor>) cache.clone();
	}
	
	public static Monitor[] getArray() throws FileNotFoundException, IOException, ClassNotFoundException{
		TreeSet<Monitor> treeSet = MonitorsUtil.get();
		if(treeSet.isEmpty())
			return null;
		return treeSet.toArray(new Monitor[treeSet.size()]);
	}
	
	public synchronized static int getLength() throws FileNotFoundException, IOException, ClassNotFoundException{
		if(init.get())
			return cache.size();
		
		if(init.compareAndSet(false, true))
			refresh();
		return cache.size();
	}
	
	public synchronized static void delete(Monitor m){
		cache.remove(m);
		LoginInfo info = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
		String path=Constants.USER_DATA_DIR+File.separator+"monitors"+File.separator+info.getUsername();
		File file = new File(path,String.valueOf(m.date));
		if(file.exists())
			file.delete();
	}
	
	public static class Monitor extends LCMVideoClip implements java.io.Serializable,Comparable<Monitor>,Cloneable{
		private static final long serialVersionUID = 1L;
		byte[] data;
		String uri;
		String title;
		String description;
		long date;
		public Monitor(byte[] data, String uri, String title, String description) {
			this.data = data;
			this.uri = uri;
			this.title = title;
			this.description = description;
		}
		public byte[] getData() {
			return data;
		}
		public String getRtspVideoUrlHigh() {
			return uri;
		}
		public String getUri() {
			return uri;
		}
		public String getTitle() {
			return title;
		}
		public String getDescription() {
			return description;
		}
		public long getDate() {
			return date;
		}
		@Override
		public int compareTo(Monitor o) {
			if(date>o.date)
				return -1;
			else if(date<o.date)
				return 1;
			return 0;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (date ^ (date >>> 32));
			result = prime * result
					+ ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			result = prime * result + ((uri == null) ? 0 : uri.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Monitor other = (Monitor) obj;
			if (date != other.date)
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			if (uri == null) {
				if (other.uri != null)
					return false;
			} else if (!uri.equals(other.uri))
				return false;
			return true;
		}
		@Override
		protected Monitor clone(){
			Monitor clone;
			try {
				clone = (Monitor) super.clone();
			} catch (CloneNotSupportedException e) {
				return null;
			}
			clone.description=new String(description);
			clone.title=new String(title);
			clone.uri=new String(uri);
			return clone;
		}
		
	}
}
