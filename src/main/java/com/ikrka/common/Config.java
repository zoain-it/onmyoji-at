package com.ikrka.common;

import cn.hutool.core.io.FileUtil;

/**
 * 配置类
 * 
 * @author zoain
 */
public class Config {

    private Config() {

    }

    private final static String TEMP_DIR = FileUtil.getTmpDirPath();

    // OpenCV驱动地址
    public final static String[] libPath = { "" };

    // ADB截图保存路径(多个截图文件是为了避免多线程操作时文件占用的问题)
    public final static String[] screenShotSavePath = { TEMP_DIR + "1.png" };

    // ADB截图保存路径1
    public final static String[] screenShotSavePath1 = { TEMP_DIR + "2.png" };

    // ADB截图保存路径2
    public final static String[] screenShotSavePath2 = { TEMP_DIR + "3.png" };

    // 模板图路径(需要带最后一个斜杠)
    public final static String[] tempPath = { "" };

    // adb命令路径
    public final static String[] adb = { "adb" };

    public final static String[] GAUNTLET_PARAM = { "一层", "二层", "三层", "四层", "五层", "六层", "七层", "八层", "九层", "十层", "悲鸣" };

    public final static String[] SOUL_MODEL_PARAM = { "八岐大蛇", "业原火" };

    public final static String[] FIRE_MODEL_PARAM = { "贪之阵", "嗔之阵", "痴之阵" };

    public final static String[] MODEL_PARAM = { "火麒麟", "风麒麟", "水麒麟", "雷麒麟" };

}
