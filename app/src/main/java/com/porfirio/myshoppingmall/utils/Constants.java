package com.porfirio.myshoppingmall.utils;

/**
 * Created by Administrator on 2017/3/11.
 */
// configure the url of each Fragment.
public class Constants {
    //网络url
    public static String BASE_URL = "http://192.168.3.36:8080/atguigu";
    public static String HOME_URL = BASE_URL + "/json/HOME_URL.json";
    public static String BASE_URL_IMAGE = BASE_URL + "/img";

    //本地url
    //public static String LOCAL_BASE_URL = "//storage//0000-0000//shoppingmallresources"; //ExtraSDcard
    public static String LOCAL_BASE_URL = "//storage//emulated//0//shoppingmallresources"; //InnerSdcard
    public static String LOCAL_HOME_URL = LOCAL_BASE_URL + "/json/HOME_URL.json";
    public static String LOCAL_BASE_URL_IMAGE = LOCAL_BASE_URL + "/img";

}
