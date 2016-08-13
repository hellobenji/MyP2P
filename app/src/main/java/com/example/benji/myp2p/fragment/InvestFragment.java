package com.example.benji.myp2p.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.benji.myp2p.R;

/**
 * Created by benji on 2016/8/12.
 */
public class InvestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.investfragmetn, null);
        return view;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
