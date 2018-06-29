package com.knowledge.domain.XieChengDomains.Sight;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 携程数据中的单个玩乐
 */

public class XieChengSightSpecificPlay {


    @JSONField(name = "价格")
    private String price;

    @JSONField(name = "名称")
    private String name;

    @JSONField(name = "优惠")
    private String discount;

    public XieChengSightSpecificPlay() {
    }

    public XieChengSightSpecificPlay(String price, String name, String discount) {
        this.price = price;
        this.name = name;
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "XieChengSightSpecificPlay{" +
                "price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", discount='" + discount + '\'' +
                '}';
    }
}
