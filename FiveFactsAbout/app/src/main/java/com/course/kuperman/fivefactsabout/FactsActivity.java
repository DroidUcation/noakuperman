package com.course.kuperman.fivefactsabout;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.course.kuperman.fivefactsabout.db.FiveFactsContract;
import com.course.kuperman.fivefactsabout.fregments.MenuFragment;

public class FactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        if(savedInstanceState ==null){
            InsertFactsToDB();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.menu_container,new MenuFragment()).commit();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void  InsertFactsToDB()
    {

        insertFact(1,R.string.fact_one);
        insertFact(2,R.string.fact_two);
        insertFact(3,R.string.fact_three);
        insertFact(4,R.string.fact_four);
        insertFact(5,R.string.fact_five);
    }

    private void insertFact(int factNumber,int fact)
    {
        ContentValues values = new ContentValues();
        values.put(FiveFactsContract.FiveFactsEntry.COLUMN_FACT_NUMBER,factNumber);
        values.put(FiveFactsContract.FiveFactsEntry.COLUMN_FACT,fact);
        Uri uri = getContentResolver().insert(FiveFactsContract.CONTENT_URI,values);

    }




}
