package com.ikrka.util;

import java.util.Random;

public class CommonUtil {

    private final static Random random = new Random();

    public static int randomScope(int max, int min) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

}