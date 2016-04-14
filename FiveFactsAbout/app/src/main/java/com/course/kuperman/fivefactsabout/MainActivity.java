package com.course.kuperman.fivefactsabout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.course.kuperman.fivefactsabout.fregments.MenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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


    public void readClicked(View v){
        Intent intent = new Intent(getApplicationContext(),FactsActivity.class);
        startActivity(intent);
    }
}
