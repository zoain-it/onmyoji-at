package com.ikrka.run.service.impl;

import com.ikrka.common.TempImgConfig;
import com.ikrka.run.service.base.BaseHandler;
import com.ikrka.run.service.base.BaseService;

/**
 * 御魂功能实现
 * 
 * @author zoain
 */
public class SoulService extends Thread implements BaseService {

    private int tier;

    private int model;

    public SoulService() {
        this(1, 1);
    }

    public SoulService(int tier, int model) {
        this.tier = tier;
        this.model = model;
    }

    /**
     * [探险 -> 御魂 -> 选择模式 -> 选择层数 -> 开始]<br/>
     * 暂时去掉 {锁定阵容} 步骤(原步骤 [探险 -> 御魂 -> 选择模式 -> 选择层数 -> 锁定阵容 -> 开始])
     */
    @Override
    public void run() {
        getHandlerInstance().enterExplore().sleep(2000).enterSoul().sleep(1000).enterSoulModel(model).sleep(1000)
                .selectTier(tier, model).sleep(500);
        while (true) {
            getHandlerInstance().enterGauntlet(model).sleep(2000).clickPrepare().sleep(15000).clickReward(model);
        }
    }

    private Handler getHandlerInstance() {
        return Handler.handler;
    }

    private final static class Handler extends BaseHandler<Handler> {

        private final static Handler handler = new Handler();

        private Handler() {
        }

        private Handler enterSoul() {
            click(TempImgConfig.SOUL[0]);
            return this;
        }

        private Handler enterSoulModel(int model) {
            click(model == 1 ? TempImgConfig.GOSSIP_SNAKE[0] : TempImgConfig.FIRE[0]);
            return this;
        }

        @Deprecated(since = "1.0")
        private Handler enterLock() {
            click(TempImgConfig.LOCK[0]);
            return this;
        }

    }

}
