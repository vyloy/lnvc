package lorent.stb.tv;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class MyProvider extends ContentProvider
{
	
	private static final String DATABASE_NAME = "TV.db";
	private static final int DATABASE_VERSION = 1;
	
	//频道列表
	private static final String channel_tb="create table channel_tb(_id Integer primary key,FreqSeq integer" +
			",LogicNum integer, OnId integer,TsId Integer,PmtPid integer,ServiceId integer,RefServiceId integer" +
			",PcrPID integer,EleVideoPid integer,VideoMediaType integer,ServiceType integer,BouquetName text" +
			",ServicePName text,ServiceName text,EITScheduleFlag integer,EITPFFlag integer,RuningStatus integer" +
			",FreeCAMode integer,VolumeSet integer,TrackSet integer,ProgramGroup integer)";
	
	
	//频道名称语音表
	private static final String language_tb="create table language_tb(_id integer primary key,code text" +
			",name text,channel_id integer)";
	
	//频点序号表
	private static final String frelist_tb="create table frelist_tb(freqseq integer primary key" +
			",tsid integer,onid integer,freq integer,sysmbol integer,enqam integer)";
	
	//CA 表
	private static final String ca_tb="create table ca_tb(_id Integer primary key" +
			",casystemId integer,caPid integer,channel_id integer)";
	
	//音频表
	private static final String audio_tb="create table audio_tb(_id Integer primary key,enAudioMediaType integer" +
			",eleAudioPid integer,channel_id integer)";
	
	
	//频道信息表
	private static final String channel_info_tb = "create table channel_info_tb(_id Integer primary key" +
			",MyLogicNum text,MyServiceId Integer,MyServiceType Integer,MyServiceName text)";
	
	//喜爱频道表
	private static final String love_tb="create table love_tb(_id Integer primary key" +
			",logicNum text,loveName text,serviceId integer,serviceType Integer)";
	
	//加锁频道表
	private static final String lock_tb="create table lock_tb(_id Integer primary key,serviceId Integer)";
	
	private DatabaseHelper mOpenHelper;
	private static UriMatcher urimatcher;
	public static final String AUTHORITY = "stb.info";
	
	private static final int TB_CHANNEL = 1;
	private static final int TB_LOVE = 2;
	private static final int TB_LOCK = 3;
	private static final int TB_FRELIST = 4;
	private static final int TB_AUDIO = 5;
	private static final int TB_CA = 6;
	private static final int TB_CHANNEL_INFO = 7;
	
	private static final String TABLE_CHANNEL = "channel_tb";
	private static final String TABLE_LOVE = "love_tb";
	private static final String TABLE_LOCK = "lock_tb";
	private static final String TABLE_FRELIST = "frelist_tb";
    private static final String TABLE_AUDIO = "audio_tb";
    private static final String TABLE_CA = "ca_tb";
    private static final String TABLE_CHANNEL_INFO = "channel_info_tb";
    
	static
	{
		urimatcher = new UriMatcher(UriMatcher.NO_MATCH);
		urimatcher.addURI(AUTHORITY, TABLE_CHANNEL, TB_CHANNEL);
		urimatcher.addURI(AUTHORITY, TABLE_LOVE, TB_LOVE);
        urimatcher.addURI(AUTHORITY, TABLE_LOCK, TB_LOCK);
        urimatcher.addURI(AUTHORITY, TABLE_AUDIO, TB_AUDIO);
        urimatcher.addURI(AUTHORITY, TABLE_CA, TB_CA);
        urimatcher.addURI(AUTHORITY, TABLE_FRELIST, TB_FRELIST);
        urimatcher.addURI(AUTHORITY, TABLE_CHANNEL_INFO, TB_CHANNEL_INFO);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs)
	{
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count = 0;
		switch (urimatcher.match(uri))
		{
		case TB_LOVE:
			count = db.delete(TABLE_LOVE, selection, selectionArgs);
			break;
		case TB_CHANNEL_INFO:
			count = db.delete(TABLE_CHANNEL_INFO, selection, selectionArgs);
			break;
		case TB_CA:
			count = db.delete(TABLE_CA, selection, selectionArgs);
			break;
		case TB_AUDIO:
			count = db.delete(TABLE_AUDIO, selection, selectionArgs);
			break;
		case TB_CHANNEL:
			count = db.delete(TABLE_CHANNEL, selection, selectionArgs);
			break;
		case TB_FRELIST:
			count = db.delete(TABLE_FRELIST, selection, selectionArgs);
			break;
		case TB_LOCK:
			count = db.delete(TABLE_LOCK, selection, selectionArgs);
			break;
		
		default:
			break;
		}
		return count;
	}

	@Override
	public String getType(Uri uri)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values)
	{
		// TODO Auto-generated method stub
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		switch (urimatcher.match(uri))
		{
		case TB_LOVE: 
			db.insert(TABLE_LOVE, null, values);
			break;
			
		case TB_LOCK:
			db.insert(TABLE_LOCK, null, values);
			break;
			
		case TB_CHANNEL_INFO:
			db.insert(TABLE_CHANNEL_INFO, null, values);
			break;
			
		default:
			break;
		}
		return null;
	}

	@Override
	public boolean onCreate()
	{
		// TODO Auto-generated method stub
		mOpenHelper = new DatabaseHelper(this.getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder)
	{
		// TODO Auto-generated method stub
		Cursor c = null;
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		
		switch (urimatcher.match(uri))
		{
		case TB_CHANNEL:
			c = db.query(TABLE_CHANNEL, projection, selection, selectionArgs, null, null,sortOrder);
			break;
			
		case TB_LOVE:	
			c = db.query(TABLE_LOVE, projection, selection, selectionArgs, null, null, sortOrder);
			break;
			
		case TB_LOCK:
			c = db.query(TABLE_LOCK, projection, selection, selectionArgs, null, null, sortOrder);
			break;
			
		case TB_FRELIST:
			c = db.query(TABLE_FRELIST, projection, selection, selectionArgs, null, null, sortOrder);
			break;
			
		case TB_AUDIO:
			c = db.query(TABLE_AUDIO, projection, selection, selectionArgs, null, null, sortOrder);
			break;
			
		case TB_CA:	
			c = db.query(TABLE_CA, projection, selection, selectionArgs, null, null, sortOrder);
			break;
			
		case TB_CHANNEL_INFO:
			c = db.query(TABLE_CHANNEL_INFO, projection, selection, selectionArgs, null, null, sortOrder);
			break;
		default:
			
			break;
		}
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public static class DatabaseHelper extends SQLiteOpenHelper
	{
		
		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			
			db.execSQL(frelist_tb);
			db.execSQL(channel_tb);
			db.execSQL(language_tb);
			db.execSQL(ca_tb);
			db.execSQL(audio_tb);
			db.execSQL(love_tb);
			db.execSQL(lock_tb);
			db.execSQL(channel_info_tb);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			Log.v("DatabaseHelper", "onUpgrade()");
		}
	}

}
