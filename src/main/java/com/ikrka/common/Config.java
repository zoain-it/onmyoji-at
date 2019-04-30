package com.ikrka.common;

/**
 * 配置类
 * 
 * @author
 */
public class Config {

    public static String libPath = "/usr/local/Cellar/opencv/4.1.0_1/share/java/opencv4/libopencv_java410.dylib";

    public static String screenShotSavePath = "/Users/lizhaofa/Downloads/screen.png";

    public static String screenShotSavePath1 = "/Users/lizhaofa/Downloads/screen1.png";

    public static String screenShotSavePath2 = "/Users/lizhaofa/Downloads/screen2.png";

    public static String tempPath = "/Users/lizhaofa/Downloads/resources/temp/";

    public static String adb = "/Users/lizhaofa/Library/Nemu/adb";

    public final static String[] gauntletParam = { "一层", "二层", "三层", "四层", "五层", "六层", "七层", "八层", "九层", "十层" };

    static {

        libPath = "/usr/local/Cellar/opencv/4.1.0_1/share/java/opencv4/libopencv_java410.dylib";

        screenShotSavePath = "/Users/lizhaofa/Downloads/screen.png";

        tempPath = "/Users/lizhaofa/Downloads/resources/temp/";

        adb = "/Users/lizhaofa/Library/Nemu/adb";

    }

}