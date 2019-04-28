package com.ikrka.run.service.impl;

import java.util.Map;

import com.ikrka.common.Config;
import com.ikrka.common.TempImgConfig;
import com.ikrka.core.ImgMatch;
import com.ikrka.run.service.base.BaseService;
import com.ikrka.util.ADBUtil;

import cn.hutool.core.lang.Console;

public class TenSoulService implements BaseService, Runnable {

    @Override
    public void run() {
        getHandlerInstance().enterExplore();
    }

    private Handler getHandlerInstance() {
        return Handler.handler;
    }

    private final static class Handler {

        private final static Handler handler = new Handler();

        private Handler() {
        }

        private Handler enterExplore() {
            try {
                ADBUtil.screenShot();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map<String, Integer> result = ImgMatch.match(TempImgConfig.explore[0], Config.screenShotSavePath);
            Console.print(result);
            try {
                ADBUtil.click(result.get("x"), result.get("y"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return handler;
        }

    }

}