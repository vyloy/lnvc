package com.lorent.video.dao;

import android.database.sqlite.SQLiteDatabase;

public class BaseDao {

	protected SQLiteDatabase db;

	public SQLiteDatabase getDb() {
		return db;
	}

	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}
	
	
}
