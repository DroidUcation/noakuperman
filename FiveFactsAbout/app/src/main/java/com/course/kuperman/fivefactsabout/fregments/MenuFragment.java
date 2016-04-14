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
public class MenuFragment extends Fragment implements View.OnClickListener {

    int [] btns = {R.id.firstbtn,R.id.secondbtn,R.id.thirdbtn,R.id.fourthbtn,R.id.fifthbtn};
    Fragment frag;
    FragmentTransaction fragTransaction;
    View view;
    public MenuFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.menu,container,false);
        if(savedInstanceState == null) {
            /*frag = new FirstFactFragment();
            fragTransaction = getFragmentManager().beginTransaction().add(R.id.container, frag);
            fragTransaction.commit();*/


            Button btnFirst = (Button) view.findViewById(R.id.firstbtn);
            btnFirst.setOnClickListener(this);
            onClick(btnFirst);
            //setBtnColor(R.id.secondbtn, btnFirst);

            Button btnSec = (Button) view.findViewById(R.id.secondbtn);
            btnSec.setOnClickListener(this);

            Button btnThird = (Button) view.findViewById(R.id.thirdbtn);
            btnThird.setOnClickListener(this);

            Button btnFourth = (Button) view.findViewById(R.id.fourthbtn);
            btnFourth.setOnClickListener(this);

            Button btnFifth = (Button) view.findViewById(R.id.fifthbtn);
            btnFifth.setOnClickListener(this);

        }

        return view;
    }

    private void setBtnColor(int selected )
    {

        for(int i=0;i<btns.length;i++)
        {
            Button  btn =(Button)  view.findViewById(btns[i]);
            if(btns[i] == selected) {
                btn.setBackgroundColor(Color.GREEN);
            }
            else
            {
                btn.setBackgroundColor(Color.GRAY);
            }
        }
    }

    @Override
    public void onClick(View v) {
        //setBtnColor(v.getId(), v);
        switch (v.getId()){
            case R.id.firstbtn:
                frag = new FirstFactFragment();
                fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                fragTransaction.commit();
                setBtnColor(v.getId());
                break;
            case R.id.secondbtn:
                frag = new SecondFactFragment();
                fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                fragTransaction.commit();
                setBtnColor(v.getId());
                break;
            case R.id.thirdbtn:
                frag = new ThirdFactFragment();
                fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                fragTransaction.commit();
                setBtnColor(v.getId());
                break;
            case R.id.fourthbtn:
                frag = new FourthFactFragment();
                fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                fragTransaction.commit();
                setBtnColor(v.getId());
                break;
            case R.id.fifthbtn:
                frag = new FifthFactFragment();
                fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                fragTransaction.commit();
                setBtnColor(v.getId());
                break;
            default:return;

        }


    }
}
