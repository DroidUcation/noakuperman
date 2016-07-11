package com.application.schedulit.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yosinoa on 04/05/2016.
 */
public class SchedulitDataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "fiveFacts.db";

    private static final String SQL_CREATION = "CREATE TABLE "+SchedulitContract.SchedulitEntry.TABLE_NAME
                                                + "( "
                                                    +SchedulitContract.SchedulitEntry._ID+ " INTEGER PRIMARY KEY ,"
                                                    +SchedulitContract.SchedulitEntry.COLUMN_CONTACTS + " TEXT ,"
                                                    +SchedulitContract.SchedulitEntry.COLUMN_MSG + " TEXT "
                                                + " ) ";

    public SchedulitDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATION);
            //createInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO in next version
    }


}
