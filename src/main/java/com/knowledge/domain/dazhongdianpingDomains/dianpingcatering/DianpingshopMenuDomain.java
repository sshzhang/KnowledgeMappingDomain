package com.knowledge.domain.dazhongdianpingDomains.dianpingcatering;

import java.util.List;
import java.util.Map;

/**
 * 点评mongodb 中shop_menu对应的字段
 */
public class DianpingshopMenuDomain {

    //推荐菜
    private Map<String, DianpingMenuDomain> recommenDishList;

    //价目表
    private List<DianpingPriceOrEnvironmentDomain> priceList;

    //环境
    private List<DianpingPriceOrEnvironmentDomain> environmentList;


    public DianpingshopMenuDomain() {
    }

    public DianpingshopMenuDomain(Map<String, DianpingMenuDomain> recommenDishList, List<DianpingPriceOrEnvironmentDomain> priceList, List<DianpingPriceOrEnvironmentDomain> environmentList) {
        this.recommenDishList = recommenDishList;
        this.priceList = priceList;
        this.environmentList = environmentList;
    }

    public Map<String, DianpingMenuDomain> getRecommenDishList() {
        return recommenDishList;
    }

    public void setRecommenDishList(Map<String, DianpingMenuDomain> recommenDishList) {
        this.recommenDishList = recommenDishList;
    }

    public List<DianpingPriceOrEnvironmentDomain> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<DianpingPriceOrEnvironmentDomain> priceList) {
        this.priceList = priceList;
    }

    public List<DianpingPriceOrEnvironmentDomain> getEnvironmentList() {
        return environmentList;
    }

    public void setEnvironmentList(List<DianpingPriceOrEnvironmentDomain> environmentList) {
        this.environmentList = environmentList;
    }

    @Override
    public String toString() {
        return "DianpingshopMenuDomain{" +
                "recommenDishList=" + recommenDishList +
                ", priceList=" + priceList +
                ", environmentList=" + environmentList +
                '}';
    }
}
