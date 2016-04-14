package com.course.kuperman.fivefactsabout.fregments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.course.kuperman.fivefactsabout.R;

/**
 * Created by yosinoa on 06/04/2016.
 */
public class MenuFragment extends Fragment {

    int [] btns = {R.id.firstbtn,R.id.secondbtn,R.id.thirdbtn,R.id.fourthbtn,R.id.fifthbtn};
    Fragment frag;
    FragmentTransaction fragTransaction;

    public MenuFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.menu,container,false);
        if(savedInstanceState == null) {
            frag = new FirstFactFragment();
            fragTransaction = getFragmentManager().beginTransaction().add(R.id.container, frag);
            fragTransaction.commit();
            setBtnColor(R.id.firstbtn, view);

            Button btnFirst = (Button) view.findViewById(R.id.firstbtn);
            btnFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frag = new FirstFactFragment();
                    fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                    fragTransaction.commit();
                    setBtnColor(R.id.firstbtn, view);
                }
            });

            Button btnSec = (Button) view.findViewById(R.id.secondbtn);
            btnSec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frag = new SecondFactFragment();
                    fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                    fragTransaction.commit();
                    setBtnColor(R.id.secondbtn, view);
                }
            });

            Button btnThird = (Button) view.findViewById(R.id.thirdbtn);
            btnThird.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frag = new ThirdFactFragment();
                    fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                    fragTransaction.commit();
                    setBtnColor(R.id.thirdbtn, view);
                }
            });

            Button btnFourth = (Button) view.findViewById(R.id.fourthbtn);
            btnFourth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frag = new FourthFactFragment();
                    fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                    fragTransaction.commit();
                    setBtnColor(R.id.fourthbtn, view);
                }
            });

            Button btnFifth = (Button) view.findViewById(R.id.fifthbtn);
            btnFifth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    frag = new FifthFactFragment();
                    fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                    fragTransaction.commit();
                    setBtnColor(R.id.fifthbtn,view);
                }
            });
        }

        return view;
    }

    private void setBtnColor(int selected,View view )
    {
        for(int i=0;i<btns.length;i++)
        {
            Button btn =(Button)  view.findViewById(btns[i]);
            if(btns[i] == selected) {
                btn.setBackgroundColor(Color.GREEN);
            }
            else
            {
                btn.setBackgroundColor(Color.GRAY);
            }
        }
    }
}
