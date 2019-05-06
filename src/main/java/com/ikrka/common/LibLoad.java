package com.ikrka.common;

/**
 * 加载驱动
 * 
 * @author zoain
 */
public class LibLoad {

    public static void load() {
        try {
            System.load(Config.libPath);
        } catch (Exception e) {
            throw new RuntimeException("load lib error", e);
        }
    }

}