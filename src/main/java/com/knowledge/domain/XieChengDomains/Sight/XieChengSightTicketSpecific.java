package com.knowledge.domain.XieChengDomains.Sight;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 携程景点单个门票domain记录
 * 例如  特惠推荐
 *
 * 商家名称 为"" 表示    参考门市价为“”
 *
 */
public class XieChengSightTicketSpecific {

    @JSONField(alternateNames ={"名称","商品名称"} )
    private String name;

    @JSONField(name = "支付方式")
    private String payway;

    @JSONField(name = "参考门市价")
    private String cnakaomenPrice;

    @JSONField(name = "价格")
    private String price;

    @JSONField(name = "商家名称")
    private String bussinerName;


    public XieChengSightTicketSpecific() {
    }

    public XieChengSightTicketSpecific(String name, String payway, String cnakaomenPrice, String price, String bussinerName) {
        this.name = name;
        this.payway = payway;
        this.cnakaomenPrice = cnakaomenPrice;
        this.price = price;
        this.bussinerName = bussinerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getCnakaomenPrice() {
        return cnakaomenPrice;
    }

    public void setCnakaomenPrice(String cnakaomenPrice) {
        this.cnakaomenPrice = cnakaomenPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBussinerName() {
        return bussinerName;
    }

    public void setBussinerName(String bussinerName) {
        this.bussinerName = bussinerName;
    }


    @Override
    public String toString() {
        return "XieChengSightTicketSpecific{" +
                "name='" + name + '\'' +
                ", payway='" + payway + '\'' +
                ", cnakaomenPrice='" + cnakaomenPrice + '\'' +
                ", price='" + price + '\'' +
                ", bussinerName='" + bussinerName + '\'' +
                '}';
    }
}
