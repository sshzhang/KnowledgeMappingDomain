package com.knowledge.domain.dazhongdianpingDomains.dianpingcatering;

import com.alibaba.fastjson.annotation.JSONField;
import com.knowledge.Annotations.FieldMethodAnnotation;

/**
 * 每个点评餐饮数据中的优惠促销
 */
public class DianpingCateringShopPromotionDomain {


    //自定义格式使用注解

    @FieldMethodAnnotation(index= 1,MethodName = "setOriginalPrice")
    private String originalPrice;

    @FieldMethodAnnotation(index = 0,MethodName = "setCurrPrice")
    private String currPrice;

    @FieldMethodAnnotation(index= 2,MethodName = "setHadSaled")
    private String hadSaled;

    public DianpingCateringShopPromotionDomain() {
    }
    public DianpingCateringShopPromotionDomain(String originalPrice, String currPrice, String hadSaled) {
        this.originalPrice = originalPrice;
        this.currPrice = currPrice;
        this.hadSaled = hadSaled;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(String currPrice) {
        this.currPrice = currPrice;
    }

    public String getHadSaled() {
        return hadSaled;
    }

    public void setHadSaled(String hadSaled) {
        this.hadSaled = hadSaled;
    }
}
