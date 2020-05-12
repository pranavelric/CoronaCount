package com.corona.coronacount.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.corona.coronacount.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FaqFragment extends Fragment implements View.OnClickListener  {

    @BindView(R.id.expandableView)
    ExpandableLayout expandableLayout;
    @BindView(R.id.arrowBtn)
    Button arrowbtn;

    @BindView(R.id.expandableViewq2)
    ExpandableLayout expandableLayoutQ2;
    @BindView(R.id.arrowBtnq2)
    Button arrowbtnQ2;



    @BindView(R.id.expandableViewq3)
    ExpandableLayout expandableLayoutQ3;
    @BindView(R.id.arrowBtnq3)
    Button arrowbtnQ3;


    @BindView(R.id.expandableViewq4)
    ExpandableLayout expandableLayoutQ4;
    @BindView(R.id.arrowBtnq4)
    Button arrowbtnQ4;


    @BindView(R.id.expandableViewq5)
    ExpandableLayout expandableLayoutQ5;
    @BindView(R.id.arrowBtnq5)
    Button arrowbtnQ5;


    @BindView(R.id.expandableViewq6)
    ExpandableLayout expandableLayoutQ6;
    @BindView(R.id.arrowBtnq6)
    Button arrowbtnQ6;


    @BindView(R.id.expandableViewq7)
    ExpandableLayout expandableLayoutQ7;
    @BindView(R.id.arrowBtnq7)
    Button arrowbtnQ7;


    @BindView(R.id.expandableViewq8)
    ExpandableLayout expandableLayoutQ8;
    @BindView(R.id.arrowBtnq8)
    Button arrowbtnQ8;


    @BindView(R.id.expandableViewq9)
    ExpandableLayout expandableLayoutQ9;
    @BindView(R.id.arrowBtnq9)
    Button arrowbtnQ9;


    @BindView(R.id.expandableViewq10)
    ExpandableLayout expandableLayoutQ10;
    @BindView(R.id.arrowBtnq10)
    Button arrowbtnQ10;


    @BindView(R.id.expandableViewq11)
    ExpandableLayout expandableLayoutQ11;
    @BindView(R.id.arrowBtnq11)
    Button arrowbtnQ11;


    @BindView(R.id.expandableViewq12)
    ExpandableLayout expandableLayoutQ12;
    @BindView(R.id.arrowBtnq12)
    Button arrowbtnQ12;


    @BindView(R.id.expandableViewq13)
    ExpandableLayout expandableLayoutQ13;
    @BindView(R.id.arrowBtnq13)
    Button arrowbtnQ13;







    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_faq, container, false);
        unbinder = ButterKnife.bind(this, v);


        arrowbtn.setOnClickListener(this);
        arrowbtnQ2.setOnClickListener(this);
        arrowbtnQ3.setOnClickListener(this);
        arrowbtnQ4.setOnClickListener(this);
        arrowbtnQ5.setOnClickListener(this);
        arrowbtnQ6.setOnClickListener(this);
        arrowbtnQ7.setOnClickListener(this);
        arrowbtnQ8.setOnClickListener(this);
        arrowbtnQ9.setOnClickListener(this);
        arrowbtnQ10.setOnClickListener(this);
        arrowbtnQ11.setOnClickListener(this);
        arrowbtnQ12.setOnClickListener(this);
        arrowbtnQ13.setOnClickListener(this);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.arrowBtn) {

            expandableLayout.toggle();
            if (expandableLayout.isExpanded()) {
                arrowbtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }
        if (v.getId() == R.id.arrowBtnq2) {

            expandableLayoutQ2.toggle();
            if (expandableLayoutQ2.isExpanded()) {
                arrowbtnQ2.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ2.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }
        if (v.getId() == R.id.arrowBtnq3) {

            expandableLayoutQ3.toggle();
            if (expandableLayoutQ3.isExpanded()) {
                arrowbtnQ3.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ3.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }

        if (v.getId() == R.id.arrowBtnq4) {

            expandableLayoutQ4.toggle();
            if (expandableLayoutQ4.isExpanded()) {
                arrowbtnQ4.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ4.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }

        if (v.getId() == R.id.arrowBtnq5) {

            expandableLayoutQ5.toggle();
            if (expandableLayoutQ5.isExpanded()) {
                arrowbtnQ5.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ5.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }

        if (v.getId() == R.id.arrowBtnq6) {

            expandableLayoutQ6.toggle();
            if (expandableLayoutQ6.isExpanded()) {
                arrowbtnQ6.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ6.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }

        if (v.getId() == R.id.arrowBtnq7) {

            expandableLayoutQ7.toggle();
            if (expandableLayoutQ7.isExpanded()) {
                arrowbtnQ7.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ7.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }

        if (v.getId() == R.id.arrowBtnq8) {

            expandableLayoutQ8.toggle();
            if (expandableLayoutQ8.isExpanded()) {
                arrowbtnQ8.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ8.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }

        if (v.getId() == R.id.arrowBtnq9) {

            expandableLayoutQ9.toggle();
            if (expandableLayoutQ9.isExpanded()) {
                arrowbtnQ9.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ9.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }

        if (v.getId() == R.id.arrowBtnq10) {

            expandableLayoutQ10.toggle();
            if (expandableLayoutQ10.isExpanded()) {
                arrowbtnQ10.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ10.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }

        if (v.getId() == R.id.arrowBtnq11) {

            expandableLayoutQ11.toggle();
            if (expandableLayoutQ11.isExpanded()) {
                arrowbtnQ11.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ11.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }

        if (v.getId() == R.id.arrowBtnq12) {

            expandableLayoutQ12.toggle();
            if (expandableLayoutQ12.isExpanded()) {
                arrowbtnQ12.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ12.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }

        if (v.getId() == R.id.arrowBtnq13) {

            expandableLayoutQ13.toggle();
            if (expandableLayoutQ13.isExpanded()) {
                arrowbtnQ13.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
            } else {
                arrowbtnQ13.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
            }
        }




    }
}
