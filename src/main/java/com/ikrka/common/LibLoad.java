package com.ikrka.common;

/**
 * 驱动加载
 * 
 * @author zoain
 */
public class LibLoad {

    private final static String osName = System.getProperty("os.name");

    public static void load() {
        if ("Mac OS X".endsWith(osName)) {
            System.load(Config.libPath);
        } else if ("".endsWith(osName)) {
            System.load(Config.libPath);
        } else {
            throw new RuntimeException("load lib error");
        }
    }

}