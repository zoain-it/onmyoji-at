package com.ikrka.run.service.base;

import java.util.Map;
import java.util.concurrent.Future;

import com.ikrka.common.Config;
import com.ikrka.common.TempImgConfig;
import com.ikrka.core.ImgMatch;
import com.ikrka.util.ADBUtil;

import cn.hutool.core.thread.ThreadUtil;

@SuppressWarnings("all")
public class BaseHandler<T extends BaseHandler<T>> {

    public T sleep(Number millis) {
        ThreadUtil.sleep(millis);
        return (T) this;
    }

    /**
     * 点击探险按钮
     * 
     * @return
     */
    public T enterExplore() {
        click(TempImgConfig.EXPLORE[0]);
        return (T) this;
    }

    /**
     * 点击层数
     * 
     * @param tier 层数
     * @return
     */
    public T selectTier(int tier, int model) {
        if (model == 1) {
            adjustTier(tier);
        }
        click(model == 1 ? TempImgConfig.SOUL_COORDINATE[--tier] : TempImgConfig.FIRE_MODEL[--tier]);
        return (T) this;
    }

    /**
     * 当前层数区间调整到需点击的层数对应区间
     * 
     * @param tier 层数
     */
    public void adjustTier(int tier) {
        try {
            ADBUtil.screenShot();
            var result = getCoordinate(TempImgConfig.TODAY_FALL[0]);
            var x = result.get("x");
            var y = result.get("y");
            var mvX1 = x + 60;
            var mvX2 = x + 80;
            var mvY1 = y - 15;
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
     * 点击挑战按钮
     * 
     * @return
     */
    public T enterGauntlet(int model) {
        try {
            var preSecond = System.currentTimeMillis();
            Map<String, Integer> result1 = null;
            while (true) {
                if (System.currentTimeMillis() - preSecond >= 10000) {
                    break;
                }
                ADBUtil.screenShot();
                ADBUtil.copyScreenShot(Config.screenShotSavePath1);
                Future<Map<String, Integer>> future1, future2;
                future1 = ThreadUtil.execAsync(() -> getCoordinate(TempImgConfig.GAUNTLET[0]));
                future2 = ThreadUtil.execAsync(() -> getCoordinate(TempImgConfig.RANKS[0], Config.screenShotSavePath1));
                result1 = future1.get();
                if (model != 1) {
                    break;
                }
                var result2 = future2.get();
                var mvX = result1.get("x") - result2.get("x");
                var mvY = result1.get("y") - result2.get("y");
                if (TempImgConfig.GAUNTLET_LIMIT[0] <= mvX && mvX <= TempImgConfig.GAUNTLET_LIMIT[1]
                        && TempImgConfig.GAUNTLET_LIMIT[2] <= mvY && mvY <= TempImgConfig.GAUNTLET_LIMIT[3]) {
                    break;
                }
            }
            ADBUtil.click(result1.get("x"), result1.get("y"));
        } catch (Exception e) {
        }
        click(TempImgConfig.GAUNTLET[0]);
        return (T) this;
    }

    /**
     * 点击准备按钮
     * 
     * @return
     */
    public T clickPrepare() {
        try {
            var preSecond = System.currentTimeMillis();
            Map<String, Integer> prepare1 = null;
            while (true) {
                // 超时判断
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
                var prepare2 = future2.get();
                var xDiff = prepare2.get("x") - prepare1.get("x");
                var yDiff = prepare2.get("y") - prepare1.get("y");
                if (TempImgConfig.PREPARE_LIMIT[0] <= xDiff && xDiff <= TempImgConfig.PREPARE_LIMIT[1]
                        && TempImgConfig.PREPARE_LIMIT[2] <= yDiff && yDiff <= TempImgConfig.PREPARE_LIMIT[3]) {
                    break;
                }
            }
            ADBUtil.click(prepare1.get("x"), prepare1.get("y"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) this;
    }

    /**
     * 点击奖励界面
     * 
     * @return
     */
    public T clickReward(int model) {
        try {
            var preSecond = System.currentTimeMillis();
            Map<String, Integer> reward1 = null;
            while (true) {
                // 超时判断
                if (System.currentTimeMillis() - preSecond >= (model == 1 ? 60000 : 180000)) {
                    break;
                }
                ADBUtil.screenShot();
                ADBUtil.copyScreenShot(Config.screenShotSavePath1);
                Future<Map<String, Integer>> future1, future2;
                future1 = ThreadUtil.execAsync(() -> getCoordinate(TempImgConfig.REWARD[0]));
                future2 = ThreadUtil
                        .execAsync(() -> getCoordinate(TempImgConfig.REWARD[1], Config.screenShotSavePath1));
                reward1 = future1.get();
                var reward2 = future2.get();
                var x1Diff = reward2.get("x") - reward1.get("x");
                var y1Diff = reward2.get("y") - reward1.get("y");
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
        return (T) this;
    }

    public void click(String tempImg) {
        try {
            // 截图
            ADBUtil.screenShot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 匹配坐标
        var result = ImgMatch.match(tempImg, Config.screenShotSavePath);
        try {
            // 点击该坐标
            ADBUtil.click(result.get("x") + 10, result.get("y") + 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getCoordinate(String tempImg) {
        // 匹配坐标并返回
        return ImgMatch.match(tempImg, Config.screenShotSavePath);
    }

    public Map<String, Integer> getCoordinate(String tempImg, String tagetImg) {
        // 匹配坐标并返回
        return ImgMatch.match(tempImg, Config.screenShotSavePath);
    }

}