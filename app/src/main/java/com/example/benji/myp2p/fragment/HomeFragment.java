package com.example.benji.myp2p.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.benji.myp2p.R;
import com.example.benji.myp2p.bean.Image;
import com.example.benji.myp2p.bean.Index;
import com.example.benji.myp2p.bean.Product;
import com.example.benji.myp2p.common.AppNetConfig;
import com.example.benji.myp2p.ui.RoundProgress;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by benji on 2016/8/12.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.textView1)
    TextView textView1;

    @BindView(R.id.p_yearlv)
    TextView pYearlv;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.myscrollview)
    ScrollView myscrollview;
    @BindView(R.id.vp_homectopcentent)
    ViewPager vpHomectopcentent;
    @BindView(R.id.cp_pointhome)
    CirclePageIndicator cpPointhome;
    @BindView(R.id.rp_customprogresss)
    RoundProgress rpCustomprogresss;

    private AsyncHttpClient client = new AsyncHttpClient();
    private MyAdapter adapter;
    private List<Image> images;
    private Index index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.mainfragmetn, null);
        ButterKnife.bind(this, view);
        //初始化头布局
        initTittle();
        //联网请求数据
        initData();
        return view;
        //800054015
    }

    private void initData() {

        client.post(AppNetConfig.INDEX, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                index = new Index();
                super.onSuccess(content);
                Log.e("TAG", "HomeFragment onSuccess()联网成功" + content);
                //content就是返回的json数据
                JSONObject jsonObject = JSON.parseObject(content);
                String proInfo = jsonObject.getString("proInfo");
                //解析这个数据 得到prduct类
                Product product = JSON.parseObject(proInfo, Product.class);
                //解析数据 得到 image类
                String imageArr = jsonObject.getString("imageArr");
                //解析过来也是一个image类型的集合
                images = JSON.parseArray(imageArr, Image.class);
                //填充数据,现在自己创建的bean里边已经有数据了
                index.product = product;
                index.images = images;

                //将得到的数据显示在viewpager当中
                adapter = new MyAdapter();
                vpHomectopcentent.setAdapter(adapter);
                //显示白色小圆圈
                cpPointhome.setViewPager(vpHomectopcentent);

            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                Toast.makeText(getActivity(), "联网请求失败" + error, Toast.LENGTH_SHORT).show();
                Log.e("TAG", "HomeFragment onFailure()" + "联网请求失败" + error);
            }
        });


    }

    private void initTittle() {
        tvTitle.setText("首页");
        ivTitleBack.setVisibility(View.GONE);
        ivTitleSetting.setVisibility(View.GONE);
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return index.images == null ? 0 : index.images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(getContext());
           /* Picasso picasso = Picasso.with(getActivity());
            RequestCreator load = picasso.load(index.images.get(position).IMAPAURL);
            load.into(imageView);*/
            //Picasso.with(getActivity()).load(index.images.get(position).IMAPAURL).into(imageView);
            Picasso.with(getActivity()).load(index.images.get(position).IMAURL).into(imageView);
            //添加到viewpager当中
            container.addView(imageView);
            return imageView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
