package com.knowledge.domain.XieChengDomains;

import com.alibaba.fastjson.annotation.JSONField;
import com.knowledge.Annotations.FieldMethodAnnotation;

import java.io.Serializable;
import java.util.List;

public class XieChengPolicy implements Serializable {


    @FieldMethodAnnotation(MethodName = "getChildrenPolicy", SelectElementsTypes = "0:1:1")
    @JSONField(name = "儿童政策")
    private List<String> childrenPolicy;

    @FieldMethodAnnotation(MethodName = "getLeavAndLiveTime", SelectElementsTypes = "0:1:1")
    @JSONField(name = "入住和离店")
    private List<String> leavAndLiveTime;

    @FieldMethodAnnotation(MethodName = "getPayways")
    @JSONField(name = "可用支付方式")
    private List<String> payways;

    @FieldMethodAnnotation(MethodName = "getPets",SelectElementsTypes = "0:1:1")
    @JSONField(name = "宠物")
    private List<String> pets;

    @FieldMethodAnnotation(MethodName = "getDietArragment",SelectElementsTypes = "0:1:1")
    @JSONField(name = "膳食安排")
    private List<String> dietArragment;

    public XieChengPolicy() {
    }
    public XieChengPolicy(List<String> childrenPolicy, List<String> leavAndLiveTime, List<String> payways, List<String> pets, List<String> dietArragment) {
        this.childrenPolicy = childrenPolicy;
        this.leavAndLiveTime = leavAndLiveTime;
        this.payways = payways;
        this.pets = pets;
        this.dietArragment = dietArragment;
    }

    @Override
    public String toString() {
        return "XieChengPolicy{" +
                "childrenPolicy=" + childrenPolicy +
                ", leavAndLiveTime=" + leavAndLiveTime +
                ", payways=" + payways +
                ", pets=" + pets +
                ", dietArragment=" + dietArragment +
                '}';
    }

    public List<String> getChildrenPolicy() {
        return childrenPolicy;
    }

    public void setChildrenPolicy(List<String> childrenPolicy) {
        this.childrenPolicy = childrenPolicy;
    }

    public List<String> getLeavAndLiveTime() {
        return leavAndLiveTime;
    }

    public void setLeavAndLiveTime(List<String> leavAndLiveTime) {
        this.leavAndLiveTime = leavAndLiveTime;
    }

    public List<String> getPayways() {
        return payways;
    }

    public void setPayways(List<String> payways) {
        this.payways = payways;
    }

    public List<String> getPets() {
        return pets;
    }

    public void setPets(List<String> pets) {
        this.pets = pets;
    }

    public List<String> getDietArragment() {
        return dietArragment;
    }

    public void setDietArragment(List<String> dietArragment) {
        this.dietArragment = dietArragment;
    }
}
