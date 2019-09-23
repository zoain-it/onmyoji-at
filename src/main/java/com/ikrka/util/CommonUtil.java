package com.ikrka.util;

import java.security.SecureRandom;

public class CommonUtil {

    private final static SecureRandom random = new SecureRandom();

    private CommonUtil() {

    }

    public static int randomScope(int max, int min) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

}