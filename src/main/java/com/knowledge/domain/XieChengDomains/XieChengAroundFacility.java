package com.knowledge.domain.XieChengDomains;

import com.alibaba.fastjson.annotation.JSONField;
import com.knowledge.Annotations.FieldMethodAnnotation;

import java.util.List;

/**
 * 周边设施
 * 每个类型信息
 * 进贤湾水上乐园，水下古城文化科技主题乐园
 *
 */
public class XieChengAroundFacility {

    @FieldMethodAnnotation(MethodName = "getSights")
    @JSONField(name = "景点")
    private List<String> sights;

    @FieldMethodAnnotation(MethodName = "getEnterTainments")
    @JSONField(name = "娱乐")
    private List<String> enterTainments;

    @FieldMethodAnnotation(MethodName = "getCaterings")
    @JSONField(name = "餐饮")
    private List<String> caterings;

    @FieldMethodAnnotation(MethodName = "getShoppings")
    @JSONField(name = "购物")
    private List<String> shoppings;

    public XieChengAroundFacility() {
    }

    public XieChengAroundFacility(List<String> sights, List<String> enterTainments, List<String> caterings, List<String> shoppings) {
        this.sights = sights;
        this.enterTainments = enterTainments;
        this.caterings = caterings;
        this.shoppings = shoppings;
    }

    public List<String> getSights() {
        return sights;
    }

    @Override
    public String toString() {
        return "XieChengAroundFacility{" +
                "sights=" + sights +
                ", enterTainments=" + enterTainments +
                ", caterings=" + caterings +
                ", shoppings=" + shoppings +
                '}';
    }

    public void setSights(List<String> sights) {
        this.sights = sights;
    }

    public List<String> getEnterTainments() {
        return enterTainments;
    }

    public void setEnterTainments(List<String> enterTainments) {
        this.enterTainments = enterTainments;
    }

    public List<String> getCaterings() {
        return caterings;
    }

    public void setCaterings(List<String> caterings) {
        this.caterings = caterings;
    }

    public List<String> getShoppings() {
        return shoppings;
    }

    public void setShoppings(List<String> shoppings) {
        this.shoppings = shoppings;
    }
}
