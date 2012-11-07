package com.lorent.vovo.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class DBProvider extends ContentProvider{
	
	public static final Uri SIP_ACCOUNT_TB_URI = Uri
	.parse("content://com.lorent.lcc/sipaccount_tb");
	
	public static final Uri BLACKLIST_TB_URI = Uri
	.parse("content://com.lorent.lcc/blacklist_tb");
	
	public static final Uri DND_TB_URI = Uri
	.parse("content://com.lorent.lcc/dnd_tb");
	
	private static final String DATABASE_NAME = "lcc.db";
	private static final int DATABASE_VERSION = 1;
	
	private DatabaseHelper mOpenHelper;
	private static UriMatcher urimatcher;
	public static final String AUTHORITY = "com.lorent.lcc";
	
	private static final int TB_FRIEND = 1;
	private static final int TB_HISTORY = 2;
	private static final int TB_BLACKLIST = 3;
	private static final int TB_SIP_ACCOUNT = 4;
	private static final int TB_DND = 5;
	
	private static final String TABLE_FRIEND = "friend_tb";
	private static final String TABLE_HISTORY = "history_tb";
	private static final String TABLE_BLACKLIST = "blacklist_tb";
	public static final String TABLE_SIP_ACCOUNT = "sipaccount_tb";
	public static final String TABLE_DND = "dnd_tb";
	
	public static final String CALL_IN = "in";
	public static final String CALL_OUT = "out";
	static {
		
		urimatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		urimatcher.addURI(AUTHORITY, TABLE_FRIEND, TB_FRIEND);
		urimatcher.addURI(AUTHORITY, TABLE_HISTORY, TB_HISTORY);
        urimatcher.addURI(AUTHORITY, TABLE_BLACKLIST, TB_BLACKLIST);
        urimatcher.addURI(AUTHORITY, TABLE_SIP_ACCOUNT, TB_SIP_ACCOUNT);
        urimatcher.addURI(AUTHORITY, TABLE_DND, TB_DND);
	}
	public static class DatabaseHelper extends SQLiteOpenHelper {
		
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			init(db);
		
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			Log.v("DatabaseHelper", "onUpgrade()");
			
			String deleteSql = "drop table " + TABLE_FRIEND;
			db.execSQL(deleteSql);
			
			deleteSql = "drop table " + TABLE_HISTORY;
			db.execSQL(deleteSql);
			
			deleteSql = "drop table " + TABLE_BLACKLIST;
			db.execSQL(deleteSql);
			
			deleteSql = "drop table "+TABLE_SIP_ACCOUNT;
			db.execSQL(deleteSql);
			
			deleteSql = "drop table "+TABLE_DND;
			db.execSQL(deleteSql);
						
			init(db);
			
		}
		public void init(SQLiteDatabase db){
			String createSql = 
				"CREATE TABLE " + TABLE_FRIEND + " (" +
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"name VARCHAR(50), " +
				"lccno VARCHAR(20)," +
				"status VARCHAR(10)" +
				");";
			Log.i("create friend ", createSql);
			db.execSQL(createSql);
			createSql = 
				"CREATE TABLE " + TABLE_HISTORY + " (" +
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"calltype VARCHAR(10), " +
				"calltime VARCHAR(20), " +
	            "lccno VARCHAR(20)," +
	            "name VARCHAR(50)," +
	            "state INTEGER," +                   //  1 已接       0 未接     2 拨出    3拒接
	            "counttime VARCHAR(20)"+
	            ");";
			Log.i("create history ", createSql);
			db.execSQL(createSql);
			createSql = 
				"CREATE TABLE " + TABLE_BLACKLIST + " (" +
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	            "lccno VARCHAR(20)," +
	            "name VARCHAR(50)" +
	            ");";
			Log.i("create blacklist",createSql);
			db.execSQL(createSql);
			createSql = 
				"CREATE TABLE " + TABLE_SIP_ACCOUNT + " (" +
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	            "username VARCHAR(20)," +
	            "userpwd VARCHAR(50)," +
	            "serviceip VARCHAR(50)" +
	            ");";
			Log.i("create sip_account",createSql);
			db.execSQL(createSql);
			
			createSql = 
				"CREATE TABLE " + TABLE_DND + " (" +
				"id INTEGER PRIMARY KEY, " +
	            "begintime VARCHAR(20)," +
	            "endtime VARCHAR(20)" +
	            ");";
			Log.i("create dnd_tb",createSql);
			db.execSQL(createSql);
			
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count = 0;
		switch (urimatcher.match(uri)) {
		case TB_FRIEND:
			
			count = db.delete(TABLE_FRIEND, selection, selectionArgs);
			break;
		
		case TB_HISTORY:
			count = db.delete(TABLE_HISTORY, selection, selectionArgs);
			break;
		
		case TB_BLACKLIST:
			count = db.delete(TABLE_BLACKLIST, selection, selectionArgs);
			break;
		case TB_SIP_ACCOUNT:
			count = db.delete(TABLE_SIP_ACCOUNT, selection, selectionArgs);
			break;
		case TB_DND:
			count = db.delete(TABLE_DND, selection, selectionArgs);
			break;
		default:
			break;
		}
		return count;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		switch (urimatcher.match(uri)) {
		case TB_FRIEND: 
			db.insert(TABLE_FRIEND, null, values);
			break;
		case TB_HISTORY:
			db.insert(TABLE_HISTORY, null, values);
			break;
		case TB_BLACKLIST:
			db.insert(TABLE_BLACKLIST, null, values);
		    break;
		case TB_SIP_ACCOUNT:
			db.insert(TABLE_SIP_ACCOUNT, null, values);
			break;
		case TB_DND:
			db.insert(TABLE_DND, null, values);
			break;
		default:
			break;
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mOpenHelper = new DatabaseHelper(this.getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		Cursor c = null;
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		switch (urimatcher.match(uri)) {
		
		case TB_FRIEND: 
			
			c = db.query(TABLE_FRIEND, projection, selection, selectionArgs, null, null,sortOrder);
			break;
		case TB_HISTORY:
			
			c = db.query(TABLE_HISTORY, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case TB_BLACKLIST:
			
			c = db.query(TABLE_BLACKLIST, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case TB_SIP_ACCOUNT:
			c = db.query(TABLE_SIP_ACCOUNT, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case TB_DND:
			c = db.query(TABLE_DND, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		default:
			
			break;
		}
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count = 0;
		switch (urimatcher.match(uri)) {
		case TB_FRIEND:
			
			count = db.update(TABLE_FRIEND, values, selection, selectionArgs);
			break;
		case TB_SIP_ACCOUNT:
			
			count = db.update(TABLE_SIP_ACCOUNT, values, selection, selectionArgs);
			break;
		case TB_HISTORY:
			
			count = db.update(TABLE_HISTORY, values, selection, selectionArgs);
			break;
			
		case TB_DND:
			count = db.update(TABLE_DND, values, selection, selectionArgs);
			break;
		default:
			break;
		}
		return count;
	}
}
