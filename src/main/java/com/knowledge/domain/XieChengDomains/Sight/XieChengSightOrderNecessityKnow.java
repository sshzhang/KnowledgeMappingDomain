package com.knowledge.domain.XieChengDomains.Sight;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 携程景点预定须知
 */
public class XieChengSightOrderNecessityKnow {

    @JSONField(name = "安全指南")
    private String safeGuidence="";

    @JSONField(name = "温馨提示")
    private String careful="";

    @JSONField(name = "优待政策")
    private String goodPolicy="";

    @JSONField(name = "预订限制")
    private String orderPolicy="";

    public XieChengSightOrderNecessityKnow() {
    }

    public XieChengSightOrderNecessityKnow(String safeGuidence, String careful, String goodPolicy, String orderPolicy) {
        this.safeGuidence = safeGuidence;
        this.careful = careful;
        this.goodPolicy = goodPolicy;
        this.orderPolicy = orderPolicy;
    }

    public String getSafeGuidence() {
        return safeGuidence.replace("[", "").replace("]", "").replace("\"", "");
    }

    public void setSafeGuidence(String safeGuidence) {
        this.safeGuidence = safeGuidence;
    }

    public String getCareful() {

        return careful.replace("[", "").replace("]", "").replace("\"", "");
    }

    public void setCareful(String careful) {
        this.careful = careful;
    }

    public String getGoodPolicy() {
        return goodPolicy.replace("[", "").replace("]", "").replace("\"", "");
    }

    public void setGoodPolicy(String goodPolicy) {
        this.goodPolicy = goodPolicy;
    }

    public String getOrderPolicy() {
        return orderPolicy.replace("[", "").replace("]", "").replace("\"", "");
    }

    public void setOrderPolicy(String orderPolicy) {
        this.orderPolicy = orderPolicy;
    }


    @Override
    public String toString() {
        return "XieChengSightOrderNecessityKnow{" +
                "safeGuidence='" + safeGuidence + '\'' +
                ", careful='" + careful + '\'' +
                ", goodPolicy='" + goodPolicy + '\'' +
                ", orderPolicy='" + orderPolicy + '\'' +
                '}';
    }
}
