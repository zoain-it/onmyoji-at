package com.ikrka.util;

import java.util.HashMap;
import java.util.Map;

import com.ikrka.common.Config;

import cn.hutool.core.util.RandomUtil;

/**
 * ADB工具类
 * 
 * @author zoain
 */
@SuppressWarnings("all")
public class ADBUtil {

    private final static Map<String, Object> param = new HashMap<>(2);

    static {
        try {
            ExecUtil.getInstance().exec(Config.adb + " devices");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ADBUtil() {
    }

    public static void initConnect() throws Exception {
        ExecUtil.getInstance().exec(Config.adb + " connect " + param.get("ip") + ":" + param.get("porn"));
    }

    private final static class ADBUtilInstance {
        private final static ADBUtil adbUtil = new ADBUtil();
    }

    public static ADBUtil getInstance() {
        return ADBUtilInstance.adbUtil;
    }

    public static void click(int x, int y) throws Exception {
        ExecUtil.getInstance().exec(Config.adb + " shell input tap " + RandomUtil.randomInt(x, x + 20) + " "
                + RandomUtil.randomInt(y, y + 20));
    }

    public static void slide(int x1, int y1, int x2, int y2) throws Exception {
        ExecUtil.getInstance()
                .exec(Config.adb + " shell input swipe " + RandomUtil.randomInt(x1, x1 + 20) + " "
                        + RandomUtil.randomInt(y1, y1 + 20) + " " + RandomUtil.randomInt(x2, x2 + 20) + " "
                        + RandomUtil.randomInt(y2, y2 + 20));
    }

    public static void screenShot() throws Exception {
        ExecUtil.getInstance().exec(Config.adb + " shell screencap -p /data/data/screen.png");
        ExecUtil.getInstance().exec(Config.adb + " pull /data/data/screen.png " + Config.screenShotSavePath);
    }

    public static void copyScreenShot(String path) throws Exception {
        ExecUtil.getInstance().exec(Config.adb + " pull /data/data/screen.png " + path);
    }

    public static String getADBIP() {
        return (String) param.get("ip");
    }

    public static int getADBPorn() {
        return (int) param.get("porn");
    }

    public static void setADBIP(String ip) {
        param.put("ip", ip);
    }

    public static void setADBPorn(int porn) {
        param.put("porn", porn);
    }

}