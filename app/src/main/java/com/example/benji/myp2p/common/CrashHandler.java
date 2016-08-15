package com.example.benji.myp2p.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by benji on 2016/8/14.
 *
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    //捕获全局异常类
    private static CrashHandler crashHandler = null;
    private Context mContext;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private CrashHandler(){

    }
    //懒汉式实现单利模式
    public static  CrashHandler getInstance(){
        if(crashHandler==null){
            crashHandler = new CrashHandler();
        }
        return  crashHandler;
    }
    public void init(Context context){
        this.mContext = context;
        //获取系统默认的异常捕获对象
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前自己定义的设置为默认，那么当系统出现未捕获异常的时候会执行本类的uncaughtException方法
        Thread.setDefaultUncaughtExceptionHandler(this);
    }



    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(ex == null){
            //没有异常
            defaultUncaughtExceptionHandler.uncaughtException(thread,ex);
        }else{

            handleException(thread, ex);
        }

    }

    private void handleException(Thread thread, Throwable ex) {
        //注意异常处理类是单独开辟了一个线程。因此如果想要toast需要在主线程中进行
        new Thread(new Runnable() {
            @Override
            public void run() {
                //注意 再prepare和loop中间的代码是执行在主线程的
                Looper.prepare();

                Toast.makeText(mContext,"亲亲出现了一个小错误哟", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "CrashHandler run()吐司已经执行了");
                Looper.loop();
            }
        }).start();
        //收集错误信息。可以上传至服务器
        collectionError(ex);
        try {
            thread.sleep(2000);
            //关闭各种资源
            AppManager.getInstance().removeAll();
            //关闭进程
            android.os.Process.killProcess(android.os.Process.myPid());
            //关闭虚拟机，释放所有内存:参数0代表正常退出
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void collectionError(Throwable ex) {
        final String deviceInfo = Build.DEVICE + ":" + Build.VERSION.SDK_INT + ":" + Build.MODEL + ":" + Build.PRODUCT;
        final String message = ex.getMessage();
        //如果上传服务器需要联网 所以需要新建一个分线程
        new Thread(){
            public void run(){
                //可以通过联网将信息发送给后台，所以在分线程执行
                Log.e("TAG", "deviceInfo:" + deviceInfo + ",message:" + message);
            }
        }.start();


    }
}
