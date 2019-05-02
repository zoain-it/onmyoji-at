package com.ikrka.run.service.impl;

import java.util.Map;
import java.util.concurrent.Future;

import com.ikrka.common.Config;
import com.ikrka.common.TempImgConfig;
import com.ikrka.run.service.base.BaseHandler;
import com.ikrka.run.service.base.BaseService;
import com.ikrka.util.ADBUtil;

import cn.hutool.core.thread.ThreadUtil;

public class WakeUpService extends Thread implements BaseService {

    private Integer model;

    private Integer tier;

    public WakeUpService(Integer model, Integer tier) {
        this.model = model;
        this.tier = tier;
    }

    @Override
    public void run() {
        getHandlerInstance().enterExplore().sleep(2000).enterWakeUp().sleep(500).enterModel(model).sleep(500)
                .selectTier(tier).sleep(500);
        while (true) {
            getHandlerInstance().enterGauntlet().sleep(2000).clickPrepare().sleep(10000).clickReward();
        }
    }

    private Handler getHandlerInstance() {
        return Handler.handler;
    }

    private final static class Handler extends BaseHandler {

        private final static Handler handler = new Handler();

        private Handler() {
        }

        private Handler enterExplore() {
            click(TempImgConfig.EXPLORE[0]);
            return this;
        }

        private Handler enterWakeUp() {
            click(TempImgConfig.WAKE_UP[0]);
            return this;
        }

        private Handler enterModel(int model) {
            click(TempImgConfig.WAKE_UP_MODEL[model]);
            return this;
        }

        /**
         * 点击挑战按钮
         * 
         * @return
         */
        private Handler enterGauntlet() {
            try {
                long preSecond = System.currentTimeMillis();
                Map<String, Integer> result1 = null;
                while (true) {
                    if (System.currentTimeMillis() - preSecond >= 10000) {
                        break;
                    }
                    ADBUtil.screenShot();
                    ADBUtil.copyScreenShot(Config.screenShotSavePath1);
                    Future<Map<String, Integer>> future1, future2;
                    future1 = ThreadUtil.execAsync(() -> getCoordinate(TempImgConfig.GAUNTLET[0]));
                    future2 = ThreadUtil
                            .execAsync(() -> getCoordinate(TempImgConfig.RANKS[0], Config.screenShotSavePath1));
                    result1 = future1.get();
                    Map<String, Integer> result2 = future2.get();
                    int mvX = result1.get("x") - result2.get("x");
                    int mvY = result1.get("y") - result2.get("y");
                    if (TempImgConfig.GAUNTLET_LIMIT[0] <= mvX && mvX <= TempImgConfig.GAUNTLET_LIMIT[1]
                            && TempImgConfig.GAUNTLET_LIMIT[2] <= mvY && mvY <= TempImgConfig.GAUNTLET_LIMIT[3]) {
                        break;
                    }
                }
                ADBUtil.click(result1.get("x"), result1.get("y"));
            } catch (Exception e) {
            }
            click(TempImgConfig.GAUNTLET[0]);
            return this;
        }

        /**
         * 选择层数
         * 
         * @param tier 层数
         * @return
         */
        private Handler selectTier(int tier) {
            adjustTier(tier);
            click(TempImgConfig.SOUL_COORDINATE[--tier]);
            return this;
        }

        /**
         * 当前层数区间调整到需点击的层数对应区间
         * 
         * @param tier 层数
         */
        private void adjustTier(int tier) {
            try {
                ADBUtil.screenShot();
                Map<String, Integer> result = getCoordinate(TempImgConfig.TODAY_FALL[0]);
                int x = result.get("x");
                int y = result.get("y");
                int mvX1 = x + 60;
                int mvX2 = x + 80;
                int mvY1 = y - 15;
                ADBUtil.slide(mvX1, mvY1, mvX2, y - 315);
                if (4 <= tier && tier <= 7) {
                    ADBUtil.slide(mvX1, y - 140, mvX2, mvY1);
                }
                if (1 <= tier && tier <= 4) {
                    ADBUtil.slide(mvX1, y - 280, mvX2, mvY1);
                }
                sleep(250);
                ADBUtil.click(x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 点击准备按钮
         * 
         * @return
         */
        private Handler clickPrepare() {
            try {
                long preSecond = System.currentTimeMillis();
                Map<String, Integer> prepare1 = null;
                while (true) {
                    if (System.currentTimeMillis() - preSecond >= 15000) {
                        break;
                    }
                    ADBUtil.screenShot();
                    ADBUtil.copyScreenShot(Config.screenShotSavePath1);
                    Future<Map<String, Integer>> future1, future2;
                    future1 = ThreadUtil.execAsync(() -> getCoordinate(TempImgConfig.PREPARE[0]));
                    future2 = ThreadUtil
                            .execAsync(() -> getCoordinate(TempImgConfig.PREPARE[1], Config.screenShotSavePath1));
                    prepare1 = future1.get();
                    Map<String, Integer> prepare2 = future2.get();
                    int xDiff = prepare2.get("x") - prepare1.get("x");
                    int yDiff = prepare2.get("y") - prepare1.get("y");
                    if (TempImgConfig.PREPARE_LIMIT[0] <= xDiff && xDiff <= TempImgConfig.PREPARE_LIMIT[1]
                            && TempImgConfig.PREPARE_LIMIT[2] <= yDiff && yDiff <= TempImgConfig.PREPARE_LIMIT[3]) {
                        break;
                    }
                }
                ADBUtil.click(prepare1.get("x"), prepare1.get("y"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this;
        }

        /**
         * 奖励界面点击
         * 
         * @return
         */
        private Handler clickReward() {
            try {
                long preSecond = System.currentTimeMillis();
                Map<String, Integer> reward1 = null;
                while (true) {
                    if (System.currentTimeMillis() - preSecond >= 60000) {
                        break;
                    }
                    ADBUtil.screenShot();
                    ADBUtil.copyScreenShot(Config.screenShotSavePath1);
                    Future<Map<String, Integer>> future1, future2;
                    future1 = ThreadUtil.execAsync(() -> getCoordinate(TempImgConfig.REWARD[0]));
                    future2 = ThreadUtil
                            .execAsync(() -> getCoordinate(TempImgConfig.REWARD[1], Config.screenShotSavePath1));
                    reward1 = future1.get();
                    Map<String, Integer> reward2 = future2.get();
                    int x1Diff = reward2.get("x") - reward1.get("x");
                    int y1Diff = reward2.get("y") - reward1.get("y");
                    if (TempImgConfig.REWARD_LIMIT[0] <= x1Diff && x1Diff <= TempImgConfig.REWARD_LIMIT[1]
                            && TempImgConfig.REWARD_LIMIT[2] <= y1Diff && y1Diff <= TempImgConfig.REWARD_LIMIT[3]) {
                        break;
                    }
                }
                sleep(500);
                ADBUtil.click(reward1.get("x"), reward1.get("y"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this;
        }

        private Handler sleep(Number millis) {
            ThreadUtil.sleep(millis);
            return this;
        }

    }

}