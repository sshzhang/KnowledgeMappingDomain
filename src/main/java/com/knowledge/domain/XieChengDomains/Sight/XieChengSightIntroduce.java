package com.knowledge.domain.XieChengDomains.Sight;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 景点介绍
 */
public class XieChengSightIntroduce {


    @JSONField(name = "特色")
    private String features;

    @JSONField(name = "介绍")
    private String introduce;

    public XieChengSightIntroduce() {
    }

    public XieChengSightIntroduce(String features, String introduce) {
        this.features = features;
        this.introduce = introduce;
    }

    public String getFeatures() {
        return  features.replace("\"", "'");
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getIntroduce() {
        return introduce.replace("\"","'");
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "XieChengSightIntroduce{" +
                "features='" + features + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
