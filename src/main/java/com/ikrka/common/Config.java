package com.ikrka.common;

import com.ikrka.util.PropertiesUtil;

/**
 * 配置类
 * 
 * @author zoain
 */
public class Config {

    // OpenCV驱动地址
    public static String libPath = null;

    // ADB截图保存路径(多个截图文件是为了避免多线程操作时文件占用的问题)
    public static String screenShotSavePath = null;

    // ADB截图保存路径1
    public static String screenShotSavePath1 = null;

    // ADB截图保存路径2
    public static String screenShotSavePath2 = null;

    // 模板图路径(需要带最后一个斜杠)
    public static String tempPath = null;

    // adb命令路径
    public static String adb = null;

    public final static String[] GAUNTLET_PARAM = { "一层", "二层", "三层", "四层", "五层", "六层", "七层", "八层", "九层", "十层", "悲鸣" };

    public final static String[] MODEL_PARAM = { "火麒麟", "风麒麟", "水麒麟", "雷麒麟" };

    public static void init(String configPath) {

        PropertiesUtil.init(configPath);
        var temp = PropertiesUtil.getByKey("screenShotSavePath");

        if (temp != null && !"".equals(temp)) {
            screenShotSavePath = temp;
        }

        temp = PropertiesUtil.getByKey("screenShotSavePath1");
        if (temp != null && !"".equals(temp)) {
            screenShotSavePath1 = temp;
        }

        temp = PropertiesUtil.getByKey("screenShotSavePath2");
        if (temp != null && !"".equals(temp)) {
            screenShotSavePath2 = temp;
        }

        temp = PropertiesUtil.getByKey("tempPath");
        if (temp != null && !"".equals(temp)) {
            tempPath = temp;
        }

        temp = PropertiesUtil.getByKey("adb");
        if (temp != null && !"".equals(temp)) {
            adb = temp;
        }

        temp = PropertiesUtil.getByKey("libPath");
        if (temp != null && !"".equals(temp)) {
            libPath = temp;
        }

    }

}
