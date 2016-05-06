package com.course.kuperman.fivefactsabout.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by yosinoa on 04/05/2016.
 */
public class FiveFactsProvider extends ContentProvider {


    private static final UriMatcher sURIMatcher = buildUriMatcher();


    private FiveFactsDataBaseHelper fiveFactsDataBaseHelper;
    static final int Fact = 1;

    private SQLiteDatabase db;
    @Override
    public boolean onCreate() {

        fiveFactsDataBaseHelper = new FiveFactsDataBaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(FiveFactsContract.FiveFactsEntry.TABLE_NAME);

        int uriType = sURIMatcher.match(uri);

        switch (uriType)
        {
            case Fact :
                queryBuilder.appendWhere(FiveFactsContract.FiveFactsEntry.COLUMN_FACT_NUMBER + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        Cursor cursor = queryBuilder.query(fiveFactsDataBaseHelper.getReadableDatabase(),projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        //TODO
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int uriType = sURIMatcher.match(uri);

        db = fiveFactsDataBaseHelper.getWritableDatabase();

        long id =0;

        switch (uriType)
        {
            case Fact :
                id=db.insert(FiveFactsContract.FiveFactsEntry.TABLE_NAME,null,values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //TODO
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //TODO
        return 0;
    }

    static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FiveFactsContract.AUTHORITY;

        matcher.addURI(authority, FiveFactsContract.FiveFactsEntry.TABLE_NAME, Fact);
        matcher.addURI(authority, FiveFactsContract.FiveFactsEntry.TABLE_NAME + "/*", Fact);
        return matcher;
    }
}
