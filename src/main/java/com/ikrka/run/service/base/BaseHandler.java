package com.ikrka.run.service.base;

import java.util.Map;

import com.ikrka.common.Config;
import com.ikrka.core.ImgMatch;
import com.ikrka.util.ADBUtil;

public class BaseHandler {

    protected void click(String tempImg) {
        try {
            // 截图
            ADBUtil.screenShot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 匹配坐标
        Map<String, Integer> result = ImgMatch.match(tempImg, Config.screenShotSavePath);
        try {
            // 点击该坐标
            ADBUtil.click(result.get("x") + 10, result.get("y") + 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Map<String, Integer> getCoordinate(String tempImg) {
        // 匹配坐标并返回
        return ImgMatch.match(tempImg, Config.screenShotSavePath);
    }

    protected Map<String, Integer> getCoordinate(String tempImg, String tagetImg) {
        // 匹配坐标并返回
        return ImgMatch.match(tempImg, Config.screenShotSavePath);
    }

}