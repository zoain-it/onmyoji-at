package com.ikrka.run.service.impl;

import com.ikrka.common.TempImgConfig;
import com.ikrka.run.service.base.BaseHandler;
import com.ikrka.run.service.base.BaseService;

/**
 * 觉醒实现
 * 
 * @author zoain
 */
public class WakeUpService extends Thread implements BaseService {

    private Integer tier;

    private Integer model;

    public WakeUpService(Integer tier, Integer model) {
        this.tier = tier;
        this.model = model;
    }

    @Override
    public void run() {
        getHandlerInstance().enterExplore().sleep(2000).enterWakeUp().sleep(500).enterModel(model).sleep(500)
                .selectTier(tier, 1).sleep(500);
        while (true) {
            getHandlerInstance().enterGauntlet(1).sleep(2000).clickPrepare().sleep(10000).clickReward(1);
        }
    }

    private Handler getHandlerInstance() {
        return Handler.handler;
    }

    private final static class Handler extends BaseHandler<Handler> {

        private final static Handler handler = new Handler();

        private Handler() {
        }

        private Handler enterWakeUp() {
            click(TempImgConfig.WAKE_UP[0]);
            return this;
        }

        private Handler enterModel(int model) {
            click(TempImgConfig.WAKE_UP_MODEL[model]);
            return this;
        }

    }

}