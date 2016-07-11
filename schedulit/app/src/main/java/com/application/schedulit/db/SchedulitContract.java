package com.application.schedulit.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by yosinoa on 10/07/2016.
 */
public class SchedulitContract {

    public static final String AUTHORITY = "com.application.schedulit.db.schedulitrovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + SchedulitEntry.TABLE_NAME);

    public static class SchedulitEntry implements BaseColumns {

        public static  final String TABLE_NAME ="Schedulit";

        public static final String COLUMN_CONTACTS ="Contacts";

        public static final String COLUMN_MSG = "Message";
    }
}
