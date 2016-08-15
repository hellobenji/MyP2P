package com.example.benji.myp2p.util;

/**
 * Created by benji on 2016/8/13.
 */

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.example.benji.myp2p.common.MyApplication;

/**
 * 专门提供为处理一些UI相关的问题而创建的工具类
 */
public class UIUtils {

    public static Context getContext(){
        return MyApplication.context;
    }

    public static Handler getHandler(){
        return MyApplication.handler;
    }

    public static int getColor(int colorId){
        return getContext().getResources().getColor(colorId);
    }

    public static View getView(int viewId){
        return View.inflate(getContext(), viewId, null);
    }

    public static String[] getStringArr(int arrId){
        return getContext().getResources().getStringArray(arrId);
    }

    //与屏幕分辨率相关的
    public static int dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }

    public static int px2dp(int px){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

}

