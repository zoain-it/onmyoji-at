package com.ikrka.util;

public class StringUtil {

    private StringUtil() {

    }

    public static int fireModelToInt(String str) {
        int result = 1;
        switch (str) {
        case "嗔之阵":
            result = 2;
            break;
        case "痴之阵":
            result = 3;
            break;
        default:
            break;
        }
        return result;
    }

    public static int soulModelToInt(String str) {
        int result = 1;
        switch (str) {
        case "业原火":
            result = 2;
            break;
        default:
            break;
        }
        return result;
    }

    public static int tierToInt(String str) {
        int result = 1;
        switch (str) {
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
        case "十层":
            result = 10;
            break;
        case "悲鸣":
            result = 11;
            break;
        default:
            break;
        }
        return result;
    }

    public static int wakeUpModelToInt(String str) {
        int result = 0;
        switch (str) {
        case "风麒麟":
            result = 1;
            break;
        case "水麒麟":
            result = 2;
            break;
        case "雷麒麟":
            result = 3;
            break;
        default:
            break;
        }
        return result;
    }

}