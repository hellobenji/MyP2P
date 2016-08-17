package com.example.benji.myp2p.common;

/**
 * Created by benji on 2016/8/13.
 */

/**
 * 配置网络请求相关的地址
 */
public class AppNetConfig {

   /* //外网ip
    public static String HOST = "http://aiandroid.win/p2p";
    public static String INDEX = HOST +"/index.php";
    public static String LOGIN = HOST +"/login.php";
    public static String PRODUCT =HOST + "/product.php";
    public static String INVEST = HOST + "/invest.php";
*/
    //固定ip
    //public static final String HOST = "192.168.10.188";
    //手机wifiip
     public static final String HOST = "192.168.191.1";


    public static final String URL = "http://" + HOST + ":8080/P2PInvest/";

    public static final String LOGIN = URL + "login";

    public static final String INDEX = URL + "index";

    public static final String PRODUCT = URL + "product";

    public static final String TEST = URL + "test";
}

