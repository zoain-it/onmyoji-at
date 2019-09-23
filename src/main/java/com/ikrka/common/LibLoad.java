package com.ikrka.common;

/**
 * 加载驱动
 * 
 * @author zoain
 */
public class LibLoad {

    private LibLoad() {

    }

    public static void load() {
        try {
            System.load(Config.libPath[0]);
        } catch (Exception e) {
            throw new RuntimeException("load lib error", e);
        }
    }

}