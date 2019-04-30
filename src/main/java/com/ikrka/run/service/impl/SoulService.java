package com.ikrka.run.service.impl;

import java.util.Map;
import java.util.concurrent.Future;

import com.ikrka.common.Config;
import com.ikrka.common.TempImgConfig;
import com.ikrka.run.service.base.BaseHandler;
import com.ikrka.run.service.base.BaseService;
import com.ikrka.util.ADBUtil;

import cn.hutool.core.thread.ThreadUtil;

/**
 * 御魂功能实现
 * 
 * @author zoain
 */
public class SoulService implements BaseService, Runnable {

    private int tier;

    public SoulService() {
        this(1);
    }

    public SoulService(int tier) {
        this.tier = tier;
    }

    /**
     * [探险 -> 御魂 -> 选择层数 -> 开始]<br/>
     * 暂时去掉 {锁定阵容} 步骤(原步骤 [探险 -> 御魂 -> 选择层数 -> 锁定阵容 -> 开始])
     */
    @Override
    public void run() {
        getHandlerInstance().enterExplore().sleep(2000).enterSoul().sleep(1000).enterGossipSnake().sleep(1000)
                .selectTier(tier).sleep(500);
        while (true) {
            getHandlerInstance().enterGauntlet().sleep(2000).clickPrepare().sleep(35000).clickReward().sleep(3000);
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

        private Handler enterSoul() {
            click(TempImgConfig.SOUL[0]);
            return this;
        }

        private Handler enterGossipSnake() {
            click(TempImgConfig.GOSSIP_SNAKE[0]);
            return this;
        }

        @Deprecated(since = "1.0")
        private Handler enterLock() {
            click(TempImgConfig.LOCK[0]);
            return this;
        }

        private Handler enterGauntlet() {
            click(TempImgConfig.GAUNTLET[0]);
            return this;
        }

        private Handler selectTier(int tier) {
            adjustTier(tier);
            click(TempImgConfig.SOUL_COORDINATE[--tier]);
            return this;
        }

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
                ADBUtil.click(x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Handler clickPrepare() {
            try {
                while (true) {
                    ADBUtil.screenShot();
                    ADBUtil.copyScreenShot(Config.screenShotSavePath1);
                    Future<Map<String, Integer>> future1 = ThreadUtil
                            .execAsync(() -> getCoordinate(TempImgConfig.PREPARE[0]));
                    Future<Map<String, Integer>> future2 = ThreadUtil
                            .execAsync(() -> getCoordinate(TempImgConfig.PREPARE[1], Config.screenShotSavePath1));
                    Map<String, Integer> prepare1 = future1.get();
                    Map<String, Integer> prepare2 = future2.get();
                    int xDiff = prepare2.get("x") - prepare1.get("x");
                    int yDiff = prepare2.get("y") - prepare1.get("y");
                    if (TempImgConfig.PREPARE_LIMIT[0] <= xDiff && xDiff <= TempImgConfig.PREPARE_LIMIT[1]
                            && TempImgConfig.PREPARE_LIMIT[2] <= yDiff && yDiff <= TempImgConfig.PREPARE_LIMIT[3]) {
                        ADBUtil.click(prepare1.get("x"), prepare1.get("y"));
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this;
        }

        private Handler clickReward() {
            try {
                while (true) {
                    ADBUtil.screenShot();
                    ADBUtil.copyScreenShot(Config.screenShotSavePath1);
                    ADBUtil.copyScreenShot(Config.screenShotSavePath2);
                    Future<Map<String, Integer>> future1 = ThreadUtil
                            .execAsync(() -> getCoordinate(TempImgConfig.REWARD[0]));
                    Future<Map<String, Integer>> future2 = ThreadUtil
                            .execAsync(() -> getCoordinate(TempImgConfig.REWARD[1], Config.screenShotSavePath1));
                    Future<Map<String, Integer>> future3 = ThreadUtil
                            .execAsync(() -> getCoordinate(TempImgConfig.REWARD[2], Config.screenShotSavePath2));
                    Map<String, Integer> reward1 = future1.get();
                    Map<String, Integer> reward2 = future2.get();
                    Map<String, Integer> reward3 = future3.get();
                    int x1Diff = reward2.get("x") - reward1.get("x");
                    int x2Diff = reward3.get("x") - reward2.get("x");
                    int x3Diff = reward3.get("x") - reward1.get("x");
                    int y1Diff = reward2.get("y") - reward1.get("y");
                    int y2Diff = reward3.get("y") - reward2.get("y");
                    int y3Diff = reward3.get("y") - reward1.get("y");
                    if (TempImgConfig.REWARD_LIMIT[0] <= x1Diff && x1Diff <= TempImgConfig.REWARD_LIMIT[1]
                            && TempImgConfig.REWARD_LIMIT[2] <= x2Diff && x2Diff <= TempImgConfig.REWARD_LIMIT[3]
                            && TempImgConfig.REWARD_LIMIT[4] <= x3Diff && x3Diff <= TempImgConfig.REWARD_LIMIT[5]
                            && TempImgConfig.REWARD_LIMIT[6] <= y1Diff && y1Diff <= TempImgConfig.REWARD_LIMIT[7]
                            && TempImgConfig.REWARD_LIMIT[8] <= y2Diff && y2Diff <= TempImgConfig.REWARD_LIMIT[9]
                            && TempImgConfig.REWARD_LIMIT[10] <= y3Diff && y3Diff <= TempImgConfig.REWARD_LIMIT[11]) {
                        ADBUtil.click(reward1.get("x"), reward1.get("y"));
                        break;
                    }
                }
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