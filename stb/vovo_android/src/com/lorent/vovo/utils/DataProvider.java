package com.lorent.vovo.utils;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class DataProvider extends ContentProvider {

	public static final String DATABASE_NAME = "info.db";
	public static final String AUTHORITY = "lorent.settings";
	/** 情景模式列表 */
	public static final String TABLE_SCENE = "scene";
	/** 设备列表 */
	public static final String TABLE_DEVICE = "device";
	/** 报警方式列表 */
	public static final String TABLE_ALARM = "alarm";
	/** 场景-设备状态关联表 */
	public static final String TABLE_SCENE_DEVICE = "scene_device";
	/** 监控器列表 */
	public static final String TABLE_MONITOR = "monitor";
	/** 模式切换 */
	public static final String TABLE_SCENE_SWITCH = "scene_switch";
	public static final int DATABASE_VERSION = 1;

	private static final int TABLE_CODE_SCENE = 1;
	private static final int TABLE_CODE_DEVICE = 2;
	private static final int TABLE_CODE_SCENE_DEVICE = 3;
	private static final int TABLE_CODE_MONITOR = 4;
	private static final int TABLE_CODE_SCENE_SWITCH = 5;
	private DatabaseHelper mOpenHelper;
	private static final String CREATE_TABLE_SCENE = "create table "
			+ TABLE_SCENE + " (_id INTEGER PRIMARY KEY, name TEXT)";// 创建情景模式表
	private static final String CREATE_TABLE_DEVICE = "create table "
			+ TABLE_DEVICE
			+ " (_id INTEGER PRIMARY KEY, name TEXT, channel INTEGER, type INTEGER, location TEXT)";// 创建设备列表
	private static final String CREATE_TABLE_SCENE_DEVICE = "create table "
			+ TABLE_SCENE_DEVICE
			+ " (scene_id INTEGER, device_id INTEGER, state INTEGER )";// 场景-设备关联表。

	private static final String CREATE_TABLE_MONITOR = "create table "
			+ TABLE_MONITOR + " (_id INTEGER PRIMARY KEY, name TEXT,ip TEXT)";// 创建监控器列表
	private static final String CREATE_TABLE_SCENE_SWITCH = "create table "
			+ TABLE_SCENE_SWITCH
			+ " (_id INTEGER PRIMARY KEY, start INTEGER ,end INTEGER,scene_id INTEGER)";// 创建模式切换列表
	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, TABLE_SCENE, TABLE_CODE_SCENE);
		sURIMatcher.addURI(AUTHORITY, TABLE_DEVICE, TABLE_CODE_DEVICE);
		sURIMatcher.addURI(AUTHORITY, TABLE_SCENE_DEVICE,
				TABLE_CODE_SCENE_DEVICE);
		sURIMatcher.addURI(AUTHORITY, TABLE_MONITOR, TABLE_CODE_MONITOR);
		sURIMatcher.addURI(AUTHORITY, TABLE_SCENE_SWITCH,
				TABLE_CODE_SCENE_SWITCH);
	}
	private static Uri mUri;

	public static Uri getUri(String TableName) {
		mUri = Uri.parse("content://" + AUTHORITY + "/" + TableName);
		return mUri;
	}

	public static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_SCENE); // 创建情景模式表
			db.execSQL(CREATE_TABLE_DEVICE); // 创建感应器表
			db.execSQL(CREATE_TABLE_SCENE_DEVICE); // 创建防区表
			db.execSQL(CREATE_TABLE_MONITOR); // 创建监控列表
			db.execSQL(CREATE_TABLE_SCENE_SWITCH); // 创建模式切换列表
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
	}

	// -----------------------------------------------------------------------------------------

	@Override
	public boolean onCreate() {
		mOpenHelper = new DatabaseHelper(this.getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor c = null;
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		switch (sURIMatcher.match(uri)) {
		case TABLE_CODE_SCENE:
			c = db.query(TABLE_SCENE, projection, selection, null, null, null,
					sortOrder);
			break;
		case TABLE_CODE_DEVICE:
			c = db.query(TABLE_DEVICE, projection, selection, null, null, null,
					sortOrder);
			break;

		case TABLE_CODE_SCENE_DEVICE:
			c = db.query(TABLE_SCENE_DEVICE, projection, selection, null, null,
					null, sortOrder);
			break;
		case TABLE_CODE_MONITOR:
			c = db.query(TABLE_MONITOR, projection, selection, null, null,
					null, sortOrder);
			break;

		case TABLE_CODE_SCENE_SWITCH:
			c = db.query(TABLE_SCENE_SWITCH, projection, selection, null, null,
					null, sortOrder);
			break;
		default:
			break;
		}
		return c;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId = 0;
		switch (sURIMatcher.match(uri)) {
		case TABLE_CODE_SCENE:
			rowId = db.insert(TABLE_SCENE, null, values);
			break;
		case TABLE_CODE_DEVICE:
			rowId = db.insert(TABLE_DEVICE, null, values);
			break;

		case TABLE_CODE_SCENE_DEVICE:
			rowId = db.insert(TABLE_SCENE_DEVICE, null, values);
			break;
		case TABLE_CODE_MONITOR:
			rowId = db.insert(TABLE_MONITOR, null, values);
			break;
		case TABLE_CODE_SCENE_SWITCH:
			rowId = db.insert(TABLE_SCENE_SWITCH, null, values);
			break;
		default:
			break;
		}
		Uri newUrl = ContentUris.withAppendedId(mUri, rowId);
		getContext().getContentResolver().notifyChange(newUrl, null);
		return newUrl;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count = 0;
		switch (sURIMatcher.match(uri)) {
		case TABLE_CODE_SCENE:
			count = db.delete(TABLE_SCENE, selection, selectionArgs);
			break;
		case TABLE_CODE_DEVICE:
			count = db.delete(TABLE_DEVICE, selection, selectionArgs);
			break;
		case TABLE_CODE_SCENE_DEVICE:
			count = db.delete(TABLE_SCENE_DEVICE, selection, selectionArgs);
			break;
		case TABLE_CODE_MONITOR:
			count = db.delete(TABLE_MONITOR, selection, selectionArgs);
			break;
		case TABLE_CODE_SCENE_SWITCH:
			count = db.delete(TABLE_SCENE_SWITCH, selection, selectionArgs);
			break;
		default:
			break;
		}
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = 0;
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		switch (sURIMatcher.match(uri)) {
		case TABLE_CODE_SCENE:
			count = db.update(TABLE_SCENE, values, selection, selectionArgs);
			break;
		case TABLE_CODE_DEVICE:
			count = db.update(TABLE_DEVICE, values, selection, selectionArgs);
			break;
		case TABLE_CODE_SCENE_DEVICE:
			count = db.update(TABLE_SCENE_DEVICE, values, selection,
					selectionArgs);
			break;
		case TABLE_CODE_MONITOR:
			count = db.update(TABLE_MONITOR, values, selection, selectionArgs);
			break;
		case TABLE_CODE_SCENE_SWITCH:
			count = db.update(TABLE_SCENE_SWITCH, values, selection,
					selectionArgs);
			break;
		default:
			break;
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}
