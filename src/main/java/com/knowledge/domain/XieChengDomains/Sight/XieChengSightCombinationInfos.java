package com.knowledge.domain.XieChengDomains.Sight;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 携程景点shop_info数据的组合
 */
public class XieChengSightCombinationInfos {


    @JSONField(name = "交通指南")
    private String transportGuidence;

    @JSONField(name = "预订须知")
    private XieChengSightOrderNecessityKnow necessityKnow;

    @JSONField(name = "景点简介")
    private XieChengSightIntroduce introduce;


    public XieChengSightCombinationInfos() {
    }

    public XieChengSightCombinationInfos(String transportGuidence, XieChengSightOrderNecessityKnow necessityKnow, XieChengSightIntroduce introduce) {
        this.transportGuidence = transportGuidence;
        this.necessityKnow = necessityKnow;
        this.introduce = introduce;
    }

    public String getTransportGuidence() {
        return transportGuidence;
    }

    public void setTransportGuidence(String transportGuidence) {
        this.transportGuidence = transportGuidence;
    }

    public XieChengSightOrderNecessityKnow getNecessityKnow() {
        return necessityKnow;
    }

    public void setNecessityKnow(XieChengSightOrderNecessityKnow necessityKnow) {
        this.necessityKnow = necessityKnow;
    }

    public XieChengSightIntroduce getIntroduce() {
        return introduce;
    }

    public void setIntroduce(XieChengSightIntroduce introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "XieChengSightCombinationInfos{" +
                "transportGuidence='" + transportGuidence + '\'' +
                ", necessityKnow=" + necessityKnow +
                ", introduce=" + introduce +
                '}';
    }
}
