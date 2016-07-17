package com.course.kuperman.fivefactsabout.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yosinoa on 04/05/2016.
 */
public class FiveFactsDataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "fiveFacts.db";

    private static final String SQL_CREATION = "CREATE TABLE "+FiveFactsContract.FiveFactsEntry.TABLE_NAME
                                                + "( "
                                                    +FiveFactsContract.FiveFactsEntry._ID+ " INTEGER PRIMARY KEY ,"
                                                    +FiveFactsContract.FiveFactsEntry.COLUMN_FACT_NUMBER + " TEXT ,"
                                                    +FiveFactsContract.FiveFactsEntry.COLUMN_FACT + " TEXT "
                                                + " ) ";

    public FiveFactsDataBaseHelper(Context context) {
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
