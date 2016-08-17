package com.example.benji.myp2p.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by benji on 2016/8/13.
 */

public class MyApplication extends Application {

    public static Context context;
    public static Handler handler;
    public static Thread mainThread;//线程
    public static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        /*//初始化异常管理类
        CrashHandler.getInstance().init(this);
        Log.e("TAG", "MyApplication onCreate()异常管理类已经初始化l");*/

        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();//得到的就是当前thread对象

        /*
    *   android.os.Process.myPid()：获取该进程的ID。
        android.os.Process.myTid()：获取调用进程的ID。
        android.os.Process.myUid()：获取该进程的用户ID
        */
        mainThreadId = android.os.Process.myTid();

    }
}

