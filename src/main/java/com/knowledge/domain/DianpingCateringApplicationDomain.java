package com.knowledge.domain;

import com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringDomain;
import com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.DianpingCateringShopPromotionDomain;
import com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.DianpingCateringShopStatisticDomain;
import com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.DianpingshopMenuDomain;

import java.util.HashMap;
import java.util.Map;

public class DianpingCateringApplicationDomain {

    private CateringDomain cateringDomain;

    private DianpingCateringShopStatisticDomain shopStatisticDomain;

    private Map<String, DianpingCateringShopPromotionDomain> shopPromotionDomain;

    private DianpingshopMenuDomain dianpingshopMenuDomain;

    private Map<String, Float> shop_tags;

    public DianpingCateringApplicationDomain() {
    }

    public DianpingCateringApplicationDomain(CateringDomain cateringDomain, DianpingCateringShopStatisticDomain shopStatisticDomain, Map<String, DianpingCateringShopPromotionDomain> shopPromotionDomain, DianpingshopMenuDomain dianpingshopMenuDomain,Map<String,Float>shop_tags) {
        this.cateringDomain = cateringDomain;
        this.shopStatisticDomain = shopStatisticDomain;
        this.shopPromotionDomain = shopPromotionDomain;
        this.dianpingshopMenuDomain = dianpingshopMenuDomain;
        this.shop_tags = shop_tags;
    }

    public void setShop_tags(Map<String, Float> shop_tags) {
        this.shop_tags = shop_tags;
    }

    public Map<String, Float> getShop_tags() {
        return shop_tags;
    }
    public CateringDomain getCateringDomain() {
        return cateringDomain;
    }

    public void setCateringDomain(CateringDomain cateringDomain) {
        this.cateringDomain = cateringDomain;
    }

    public DianpingCateringShopStatisticDomain getShopStatisticDomain() {
        return shopStatisticDomain;
    }

    public void setShopStatisticDomain(DianpingCateringShopStatisticDomain shopStatisticDomain) {
        this.shopStatisticDomain = shopStatisticDomain;
    }

    public Map<String, DianpingCateringShopPromotionDomain> getShopPromotionDomain() {
        return shopPromotionDomain;
    }

    public void setShopPromotionDomain(Map<String, DianpingCateringShopPromotionDomain> shopPromotionDomain) {
        this.shopPromotionDomain = shopPromotionDomain;
    }

    public DianpingshopMenuDomain getDianpingshopMenuDomain() {
        return dianpingshopMenuDomain;
    }

    public void setDianpingshopMenuDomain(DianpingshopMenuDomain dianpingshopMenuDomain) {
        this.dianpingshopMenuDomain = dianpingshopMenuDomain;
    }
}
