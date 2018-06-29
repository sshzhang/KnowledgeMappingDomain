package com.knowledge.domain.XieChengDomains.Sight;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 携程单个酒店和门票
 */
public class XieChengSightSpecificTicketAndHotel {


    @JSONField(name = "名称")
    private String name;

    @JSONField(name = "携程价")
    private String price;

    @JSONField(name = "酒店距景点距离")
    private String distance;

    @JSONField(name = "优惠")
    private String discount;


    public XieChengSightSpecificTicketAndHotel() {
    }


    public XieChengSightSpecificTicketAndHotel(String name, String price, String distance, String discount) {
        this.name = name;
        this.price = price;
        this.distance = distance;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "XieChengSightSpecificTicketAndHotel{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", distance='" + distance + '\'' +
                ", discount='" + discount + '\'' +
                '}';
    }
}
