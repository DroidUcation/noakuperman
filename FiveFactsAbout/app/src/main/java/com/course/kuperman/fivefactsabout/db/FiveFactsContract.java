package com.course.kuperman.fivefactsabout.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by yosinoa on 04/05/2016.
 */
public class FiveFactsContract {

    public static final String AUTHORITY = "com.course.kuperman.fivefactsabout.db.FiveFactsProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + FiveFactsEntry.TABLE_NAME);

    public static class FiveFactsEntry implements BaseColumns{

            public static  final String TABLE_NAME ="FiveFacts";

            public static final String COLUMN_FACT_NUMBER ="FactNumber";

            public static final String COLUMN_FACT = "fact";
    }


}
