package com.example.benji.myp2p.common;

/**
 * Created by benji on 2016/8/13.
 */

import android.app.Activity;

import java.util.Stack;

/**
 * 统一应用程序中所有的Activity的栈管理
 * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
 */
public class AppManager {

    private Stack<Activity> activityStack = new Stack<>();

    //设置当前类是单例的 饿汉式
    /*
    * 1.首先创建一个空参对象
    * 2.提供一个公共的得到该对象的方法。这个样的话每次调用该方法都是同一个实例
    * 也就达到了单利模式的效果
    *
    * */
    private static AppManager appManager = new AppManager();

    private AppManager(){}

    public static AppManager getInstance(){
        return appManager;
    }

    public void addActivity(Activity activity){
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity){
        for(int i = activityStack.size() - 1; i >= 0 ; i--) {
            //遍历一圈。从中找出要移除的activity
            if(activityStack.get(i).getClass().equals(activity.getClass())){
                //最终还是要调用finish方法
                activity.finish();
                activityStack.remove(i);//同样栈集合中也要移除该activity
                break;
            }
        }
    }

    public void removeCurrent(){
//        Activity activity = activityStack.get(activityStack.size() - 1);
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    public void removeAll(){
        for(int i = activityStack.size() - 1; i >= 0 ; i--) {
            activityStack.get(i).finish();
            activityStack.remove(i);
        }
    }

    public int size(){
        return activityStack.size();
    }
}

