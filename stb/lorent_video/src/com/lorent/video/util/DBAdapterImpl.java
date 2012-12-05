package com.lorent.video.util;


import java.util.ArrayList;
import java.util.List;

import com.lorent.common.dto.DatabaseBean;
import com.lorent.common.dto.MonitorInfo;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapterImpl {
//	public static final String KEY_ROWID = "_id";
//	public static final String KEY_ISBN = "isbn";
//	public static final String KEY_TITLE = "title";
//	public static final String KEY_PUBLISHER = "publisher";
	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "lorentvideo";
//	private static final String DATABASE_TABLE = "titles";
	private static final int DATABASE_VERSION = 1;
//	public static final String COL_ID = "_id";
//	public static final String COL_TITLE = "title";
//	public static final String COL_MONITORURL = "monitor_url";
//	public static final String TABLE_NAME = "monitorinfo";
	public static final int PAGE_SIZE = 10;
//	
//	private static final String DATABASE_CREATE = "create table " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, "
//			+ COL_TITLE + " text not null, " + COL_MONITORURL + " text not null"
//			+ ");";
	
	
	public static DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	
	public static void init(Context ctx){
		synchronized(DBAdapterImpl.class){
			if(DBHelper==null || !(DBHelper instanceof DatabaseHelper)){
				DBHelper = new DatabaseHelper(ctx);
			}
		}
	}
	

	static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			Log.i(TAG, "DatabaseHelper");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i(TAG, "initial database");
			for(String sql:DatabaseBean.getSqls()){
				db.execSQL(sql);
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
//			db.execSQL("DROP TABLE IF EXISTS titles");
//			onCreate(db);
		}
	}

	// ---打开数据库---
	public void open() throws SQLException {
		db = DBHelper.getWritableDatabase();
	}

	// ---关闭数据库---
	public void close() {
		DBHelper.close();
	}
	
//	public MonitorInfo save(MonitorInfo info){
//		ContentValues initialValues = new ContentValues();
//		initialValues.put(COL_TITLE, info.title);
//		initialValues.put(COL_MONITORURL, info.monitoUrl);
//		open();
//		info.id = db.insert(TABLE_NAME, null, initialValues);
//		close();
//		return info;
//	}
//	
//	public boolean deleteById(long id){
//		open();
//		boolean flag = db.delete(TABLE_NAME, COL_ID + "=" + id, null) > 0;
//		close();
//		return flag;
//	}
//	
//	public List<MonitorInfo> getMonitorFromPosition(int startp) {
//		open();
//		List<MonitorInfo> infos = new ArrayList<MonitorInfo>();
//		String sql= "select * from " + TABLE_NAME 
//		+ " limit "+String.valueOf(PAGE_SIZE)+ " offset " +String.valueOf(startp);    
//        Cursor rec = db.rawQuery(sql, null);
//        while(rec.moveToNext()){
//        	MonitorInfo info = new MonitorInfo();
//        	info.id = rec.getLong(rec.getColumnIndex(COL_ID));
//        	info.title = rec.getString(rec.getColumnIndex(COL_TITLE));
//        	info.monitoUrl = rec.getString(rec.getColumnIndex(COL_MONITORURL));
//        	infos.add(info);
//        }
//        rec.close();
//        close();
//        return infos;
//	}
	
//	private class Bussiness{
//		public Object process(Object...objects){
//			return null;
//		}
//	}
//	
//	private class DBAdaterHandler implements InvocationHandler{
//
//		private DBAdapter obj = null;  
//	  
//	    public DBAdaterHandler(DBAdapter realSubject) {  
//	        this.obj = realSubject;  
//	    }  
//		
//		@Override
//		public Object invoke(Object proxy, Method m, Object[] args)
//				throws Throwable {
//			Object result = null;  
//	        try {
//	        	obj.open();
//	            result = m.invoke(proxy, args);  
//	        }finally{
//	        	obj.close();
//	        } 
//	        return result;  
//		}
//		
//	}
	
//	public Object execute(String methodName,Object...objects ) throws Exception{
//		try{
//			open();
//			Method method = null;
//			if(objects!=null&&objects.length>0){
//				Class[] clazz = new Class[objects.length];
//				method = this.getClass().getMethod(methodName, clazz);
//			}else{
//				method = this.getClass().getMethod(methodName);
//			}
//			return method==null?null:method.invoke(this, objects);
//		}catch(Exception ex){
//			throw ex;
//		}finally{
//			close();
//		}
//		
//	}

	// ---向数据库中插入一个标题---
	/*public long insertTitle(String isbn, String title, String publisher) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ISBN, isbn);
		initialValues.put(KEY_TITLE, title);
		initialValues.put(KEY_PUBLISHER, publisher);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// ---删除一个指定标题---
	public boolean deleteTitle(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	// ---检索所有标题---
	public Cursor getAllTitles() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_ISBN,
				KEY_TITLE, KEY_PUBLISHER }, null, null, null, null, null);
	}

	// ---检索一个指定标题---
	public Cursor getTitle(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_ISBN, KEY_TITLE, KEY_PUBLISHER }, KEY_ROWID
				+ "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// ---更新一个标题---
	public boolean updateTitle(long rowId, String isbn, String title,
			String publisher) {
		ContentValues args = new ContentValues();
		args.put(KEY_ISBN, isbn);
		args.put(KEY_TITLE, title);
		args.put(KEY_PUBLISHER, publisher);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}*/
}
