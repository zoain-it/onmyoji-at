package com.ikrka.common;

/**
 * 配置类
 * 
 * @author zoain
 */
public class Config {

    // OpenCV驱动地址
    public static String libPath = "";

    // ADB截图保存路径(多个截图文件是为了避免多线程操作时文件占用的问题)
    public static String screenShotSavePath = "";

    // ADB截图保存路径1
    public static String screenShotSavePath1 = "";

    // ADB截图保存路径2
    public static String screenShotSavePath2 = "";

    // 模板图路径(需要带最后一个斜杠)
    public static String tempPath = "";

    // adb命令路径
    public static String adb = "";

    public final static String[] gauntletParam = { "一层", "二层", "三层", "四层", "五层", "六层", "七层", "八层", "九层", "十层" };

}
