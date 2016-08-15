package com.example.benji.myp2p;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.benji.myp2p.common.AppManager;
import com.example.benji.myp2p.fragment.HomeFragment;
import com.example.benji.myp2p.fragment.InvestFragment;
import com.example.benji.myp2p.fragment.MoreFragment;
import com.example.benji.myp2p.fragment.MyvalueFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.fl_main_framlayout)
    FrameLayout flMainFramlayout;
    @BindView(R.id.iv_main_home)
    ImageView ivMainHome;
    @BindView(R.id.tv_main_home)
    TextView tvMainHome;
    @BindView(R.id.ll_main_home)
    LinearLayout llMainHome;
    @BindView(R.id.iv_main_invest)
    ImageView ivMainInvest;
    @BindView(R.id.tv_main_invest)
    TextView tvMainInvest;
    @BindView(R.id.ll_main_invest)
    LinearLayout llMainInvest;
    @BindView(R.id.iv_main_me)
    ImageView ivMainMe;
    @BindView(R.id.tv_main_me)
    TextView tvMainMe;
    @BindView(R.id.ll_main_me)
    LinearLayout llMainMe;
    @BindView(R.id.iv_main_more)
    ImageView ivMainMore;
    @BindView(R.id.tv_main_more)
    TextView tvMainMore;
    @BindView(R.id.ll_main_more)
    LinearLayout llMainMore;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    //主页的fragment
    private HomeFragment homeFragment;
    private FragmentTransaction transaction;
    private InvestFragment investFragment;
    private MyvalueFragment myvalueFragment;
    private MoreFragment moreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//这句是和mainactivity进行数据绑定
        //程序一打开便显示主页面，同时在初始化布局的时候也可以在这个方法中执行

        initData();
        //将当前的Activity添加到栈管理中
        AppManager.getInstance().addActivity(this);

    }



    private void initData() {
        //显示主页面
        switchToFragment(0);
    }

    @OnClick({R.id.ll_main_home, R.id.ll_main_invest, R.id.ll_main_me, R.id.ll_main_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main_home:
                Log.e("TAG", "MainActivity onClick()首页被点击了");
                //在变色之前。先将上一次点击过的图标还原成原来的样子
                reSetAllView();
                //点击的布局的图片变成蓝色。字体也变成蓝色
                selectTab(0);
                //切换至首页的fragment
                switchToFragment(0);

                break;
            case R.id.ll_main_invest:
                //在变色之前。先将上一次点击过的图标还原成原来的样子
                reSetAllView();
                //点击的布局的图片变成蓝色。字体也变成蓝色
                selectTab(1);
                //切换至投资fragment
                switchToFragment(1);
                break;
            case R.id.ll_main_me:
                //在变色之前。先将上一次点击过的图标还原成原来的样子
                reSetAllView();
                //点击的布局的图片变成蓝色。字体也变成蓝色
                selectTab(2);
                //切换至我的资产fragment
                switchToFragment(2);
                break;
            case R.id.ll_main_more:
                //在变色之前。先将上一次点击过的图标还原成原来的样子
                reSetAllView();
                //点击的布局的图片变成蓝色。字体也变成蓝色
                selectTab(3);
                //切换至更多fragment
                switchToFragment(3);
                break;
        }
    }

    private void reSetAllView() {
        ivMainHome.setImageResource(R.drawable.bid02);
        tvMainHome.setTextColor(getResources().getColor(R.color.home_back_unselected));

        ivMainMe.setImageResource(R.drawable.bid04);
        tvMainMe.setTextColor(getResources().getColor(R.color.home_back_unselected));

        ivMainInvest.setImageResource(R.drawable.bid06);
        tvMainInvest.setTextColor(getResources().getColor(R.color.home_back_unselected));

        ivMainMore.setImageResource(R.drawable.bid08);
        tvMainMore.setTextColor(getResources().getColor(R.color.home_back_unselected));

    }

    private void selectTab(int i) {
        switch (i) {
            case 0:

                ivMainHome.setImageResource(R.drawable.bid01);
                tvMainHome.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 1:
                ivMainInvest.setImageResource(R.drawable.bid03);
                tvMainInvest.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 2:
                ivMainMe.setImageResource(R.drawable.bid05);
                tvMainMe.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 3:
                ivMainMore.setImageResource(R.drawable.bid07);
                tvMainMore.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
        }
    }

    private void switchToFragment(int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        //隐藏所有的布局
        hideAllFragment();
        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_main_framlayout, homeFragment);
                }
                //如果已经存在了直接将原来隐藏的fragment显示就好了
                showFragment(0);
                break;
            case 1:
                if (investFragment == null) {
                    investFragment = new InvestFragment();
                    transaction.add(R.id.fl_main_framlayout, investFragment);
                }
                showFragment(1);
                break;
            case 2:
                if (myvalueFragment == null) {
                    myvalueFragment = new MyvalueFragment();
                    transaction.add(R.id.fl_main_framlayout, myvalueFragment);
                }
                showFragment(2);
                break;
            case 3:

                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.fl_main_framlayout, moreFragment);
                }
                showFragment(3);
                break;
        }
        transaction.commit();
    }

    private void hideAllFragment() {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (investFragment != null) {
            transaction.hide(investFragment);
        }
        if (myvalueFragment != null) {
            transaction.hide(myvalueFragment);
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
    }

    //显示
    private void showFragment(int i) {
        switch (i) {
            case 0:
                transaction.show(homeFragment);
                break;
            case 1:
                transaction.show(investFragment);
                break;
            case 2:
                transaction.show(myvalueFragment);
                break;
            case 3:
                transaction.show(moreFragment);
                break;
        }
    }
}
