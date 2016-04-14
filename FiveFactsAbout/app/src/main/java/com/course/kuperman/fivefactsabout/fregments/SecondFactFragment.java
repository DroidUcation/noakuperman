package com.course.kuperman.fivefactsabout.fregments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.course.kuperman.fivefactsabout.R;

public class SecondFactFragment extends Fragment {

    public SecondFactFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second_fact,container,false);
        return rootView;
    }
}
