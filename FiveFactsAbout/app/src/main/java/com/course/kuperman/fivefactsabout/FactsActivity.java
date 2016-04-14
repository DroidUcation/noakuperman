package com.course.kuperman.fivefactsabout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.course.kuperman.fivefactsabout.fregments.MenuFragment;

public class FactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        if(savedInstanceState ==null){
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
}
