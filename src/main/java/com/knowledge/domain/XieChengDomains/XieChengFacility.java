package com.knowledge.domain.XieChengDomains;

import com.alibaba.fastjson.annotation.JSONField;
import com.knowledge.Annotations.FieldMethodAnnotation;

import java.util.List;

/**
 * 酒店设施
 */
public class XieChengFacility {


    @FieldMethodAnnotation(MethodName = "getTransportServices")
    @JSONField(name = "交通服务")
    private List<String> transportServices;

    @Override
    public String toString() {
        return "XieChengFacility{" +
                "transportServices=" + transportServices +
                ", transportFacilities=" + transportFacilities +
                ", leisureEntertainments=" + leisureEntertainments +
                ", childrenFacilities=" + childrenFacilities +
                ", otherServices=" + otherServices +
                ", fontServices=" + fontServices +
                ", businessServices=" + businessServices +
                ", internetServices=" + internetServices +
                ", commonFacilities=" + commonFacilities +
                ", cateriServices=" + cateriServices +
                '}';
    }

    @FieldMethodAnnotation(MethodName = "getTransportFacilities")
    @JSONField(name = "交通设施")
    private List<String> transportFacilities;

    @FieldMethodAnnotation(MethodName = "getLeisureEntertainments")
    @JSONField(name = "休闲娱乐")
    private List<String> leisureEntertainments;

    @FieldMethodAnnotation(MethodName = "getChildrenFacilities")
    @JSONField(name = "儿童设施")
    private List<String> childrenFacilities;

    @FieldMethodAnnotation(MethodName = "getOtherServices")
    @JSONField(name = "其他服务")
    private List<String> otherServices;

    @FieldMethodAnnotation(MethodName = "getFontServices")
    @JSONField(name = "前台服务")
    private List<String> fontServices;

    @FieldMethodAnnotation(MethodName = "getBusinessServices")
    @JSONField(name = "商务服务")
    private List<String> businessServices;

    @FieldMethodAnnotation(MethodName = "getInternetServices")
    @JSONField(name = "网站")
    private List<String> internetServices;

    @FieldMethodAnnotation(MethodName = "getCommonFacilities")
    @JSONField(name = "通用设施")
    private List<String> commonFacilities;

    @FieldMethodAnnotation(MethodName = "getCateriServices")
    @JSONField(name = "餐饮服务")
    private List<String> cateriServices;

    public XieChengFacility() {
    }

    public XieChengFacility(List<String> transportServices, List<String> transportFacilities, List<String> leisureEntertainments, List<String> childrenFacilities, List<String> otherServices, List<String> fontServices, List<String> businessServices, List<String> internetServices, List<String> commonFacilities, List<String> cateriServices) {
        this.transportServices = transportServices;
        this.transportFacilities = transportFacilities;
        this.leisureEntertainments = leisureEntertainments;
        this.childrenFacilities = childrenFacilities;
        this.otherServices = otherServices;
        this.fontServices = fontServices;
        this.businessServices = businessServices;
        this.internetServices = internetServices;
        this.commonFacilities = commonFacilities;
        this.cateriServices = cateriServices;
    }

    public List<String> getTransportServices() {
        return transportServices;
    }

    public void setTransportServices(List<String> transportServices) {
        this.transportServices = transportServices;
    }

    public List<String> getTransportFacilities() {
        return transportFacilities;
    }

    public void setTransportFacilities(List<String> transportFacilities) {
        this.transportFacilities = transportFacilities;
    }

    public List<String> getLeisureEntertainments() {
        return leisureEntertainments;
    }

    public void setLeisureEntertainments(List<String> leisureEntertainments) {
        this.leisureEntertainments = leisureEntertainments;
    }

    public List<String> getChildrenFacilities() {
        return childrenFacilities;
    }

    public void setChildrenFacilities(List<String> childrenFacilities) {
        this.childrenFacilities = childrenFacilities;
    }

    public List<String> getOtherServices() {
        return otherServices;
    }

    public void setOtherServices(List<String> otherServices) {
        this.otherServices = otherServices;
    }

    public List<String> getFontServices() {
        return fontServices;
    }

    public void setFontServices(List<String> fontServices) {
        this.fontServices = fontServices;
    }

    public List<String> getBusinessServices() {
        return businessServices;
    }

    public void setBusinessServices(List<String> businessServices) {
        this.businessServices = businessServices;
    }

    public List<String> getInternetServices() {
        return internetServices;
    }

    public void setInternetServices(List<String> internetServices) {
        this.internetServices = internetServices;
    }

    public List<String> getCommonFacilities() {
        return commonFacilities;
    }

    public void setCommonFacilities(List<String> commonFacilities) {
        this.commonFacilities = commonFacilities;
    }

    public List<String> getCateriServices() {
        return cateriServices;
    }

    public void setCateriServices(List<String> cateriServices) {
        this.cateriServices = cateriServices;
    }
}
