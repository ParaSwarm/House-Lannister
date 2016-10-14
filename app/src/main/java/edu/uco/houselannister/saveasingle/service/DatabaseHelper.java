package edu.uco.houselannister.saveasingle.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "LoginDatabase.db";
	private static final int SCHEMA = 1;

	static final String LoginID = "id";
	static final String password = "password";
	static final String TABLE_login = "Logins";


	static final String LoginID_ForImages = "id";
	static final String nameOFImages = "nameOFImages";
	static final String TABLE_ListofImages = "ListofImages";

	private static DatabaseHelper _instance =null;

	@Override
	public void onCreate(SQLiteDatabase db)
	{

		db.execSQL("CREATE TABLE Logins (id TEXT,password TEXT);");

		db.execSQL("CREATE TABLE ListofImages (id TEXT,nameOFImages TEXT);");

		ContentValues cv=new ContentValues();

		cv.put(LoginID, "123");
		cv.put(password, "345");
		db.insert(TABLE_login, LoginID, cv);

		cv.put(LoginID, "6688");
		cv.put(password, "6688");
		db.insert(TABLE_login, LoginID, cv);

	}

	public synchronized static DatabaseHelper getInstance(Context context)
	{
		if (_instance == null)
		{
			_instance = new DatabaseHelper(context.getApplicationContext());
		}
		return(_instance);
	}

	private DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, SCHEMA);

//		super(context,
//				context.getExternalFilesDir(null).getAbsolutePath() + File.separator + DATABASE_NAME,
//				null,
//				SCHEMA);
//
		Log.d("SQLiteHelper", "Path: " + this.getReadableDatabase().getPath());
	}



	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}
}