package com.lk.syxl.customview.view;

import android.graphics.Color;

/**
 * Created by likun on 2017/9/29.
 */

public class BouncingBallConfig {
    public int ballCount;
    public int ballColor;
    public int ballRadius;
    public int cycleTime;

    public static class Builder{
        private int ballCount = 6;
        private int ballColor = Color.BLUE;
        private int ballRadius = 30;
        private int cycleTime = 1000;

        public Builder setBallCount(int ballCount) {
            this.ballCount = ballCount;
            return this;
        }

        public Builder setBallColor(int ballColor) {
            this.ballColor = ballColor;
            return this;
        }

        public Builder setBallRadius(int ballRadius) {
            this.ballRadius = ballRadius;
            return this;
        }

        public Builder setCycleTime(int cycleTime) {
            this.cycleTime = cycleTime;
            return this;
        }
        public void applyConfig(BouncingBallConfig config){
            config.ballColor = ballCount;
            config.ballColor = ballColor;
            config.ballRadius = ballRadius;
            config.cycleTime = cycleTime;
        }
        public BouncingBallConfig create(){
            BouncingBallConfig config = new BouncingBallConfig();
            applyConfig(config);
            return config;
        }
    }
}
