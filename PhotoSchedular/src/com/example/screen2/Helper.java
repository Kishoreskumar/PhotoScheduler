package com.example.screen2;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Helper extends SQLiteOpenHelper {

	private static final String db_name="screen2";
	private static final String tb_name="login";
	private static final int DATABASE_VERSION=1;
	private static final String CREATE_TABLE ="INSERT INTO login VALUES (value1,value2,value3,...);";
	private Context context;
	public Helper(Context context) {
		super(context, db_name, null, DATABASE_VERSION);
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		//string query="CREATE TABLE YET(_id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR((255));";

		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldversion, int newversion) {
		// TODO Auto-generated method stub
		
	}

}
