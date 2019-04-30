package com.ikrka.util;

public class StringUtil {

    public static int toInt(String str) {
        int result = 10;
        switch (str) {
        case "一层":
            result = 1;
            break;
        case "二层":
            result = 2;
            break;
        case "三层":
            result = 3;
            break;
        case "四层":
            result = 4;
            break;
        case "五层":
            result = 5;
            break;
        case "六层":
            result = 6;
            break;
        case "七层":
            result = 7;
            break;
        case "八层":
            result = 8;
            break;
        case "九层":
            result = 9;
            break;
        default:
            break;
        }
        return result;
    }

}