package com.example.benji.myp2p.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benji.myp2p.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by benji on 2016/8/12.
 */
public class InvestFragment extends Fragment {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.investfragmetn, null);
        ButterKnife.bind(this, view);
        //初始化头布局
        initTittle();
        return view;

        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initTittle() {
        tvTitle.setText("我的资投资");
        ivTitleBack.setVisibility(View.GONE);
        ivTitleSetting.setVisibility(View.GONE);
    }
}
