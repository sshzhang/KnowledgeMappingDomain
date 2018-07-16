package com.knowledge.domain.dazhongdianpingDomains.dianpingcatering;

import com.alibaba.fastjson.annotation.JSONField;
import com.knowledge.Annotations.FieldMethodAnnotation;

/**
 * 大众点评餐单Domain
 */
public class DianpingMenuDomain {

    @FieldMethodAnnotation(FieldReallyName = "价格", MethodName = "setPrice")
    private String price;

    @FieldMethodAnnotation(FieldReallyName = "图片", MethodName = "setPicture")
    private String picture;

    @FieldMethodAnnotation(FieldReallyName = "推荐数", MethodName = "setSuggestNumber")
    private String suggestNumber;

    public DianpingMenuDomain() {

    }
    public DianpingMenuDomain(String price, String picture, String suggestNumber) {
        this.price = price;
        this.picture = picture;
        this.suggestNumber = suggestNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSuggestNumber() {
        return suggestNumber;
    }

    public void setSuggestNumber(String suggestNumber) {
        this.suggestNumber = suggestNumber;
    }
}
