package com.example.benji.myp2p.common;

/**
 * Created by benji on 2016/8/13.
 */
/**
 * 配置网络请求相关的地址
 */
public class AppNetConfig {
//固定ip
    public static final String HOST = "192.168.1.188";
    //手机wifiip
  //  public static final String HOST = "192.168.1.188";


    public static final String URL = "http://" + HOST + ":8080/P2PInvest/";

    public static final String LOGIN = URL + "login";

    public static final String INDEX = URL + "index";

    public static final String PRODUCT = URL + "product";

    public static final String TEST = URL + "test";
}

