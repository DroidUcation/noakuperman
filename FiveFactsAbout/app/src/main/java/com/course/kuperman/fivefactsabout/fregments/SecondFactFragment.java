package com.course.kuperman.fivefactsabout.fregments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.course.kuperman.fivefactsabout.R;
import com.course.kuperman.fivefactsabout.db.FiveFactsContract;

public class SecondFactFragment extends Fragment {

    public SecondFactFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second_fact,container,false);
        Uri uri = Uri.withAppendedPath(FiveFactsContract.CONTENT_URI,"2");
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        if(cursor.moveToFirst())
        {
            String fact = cursor.getString(2);
            TextView view = (TextView)rootView.findViewById(R.id.txtView2);
            view.setText(Integer.parseInt(fact));

        }
        return rootView;
    }
}
