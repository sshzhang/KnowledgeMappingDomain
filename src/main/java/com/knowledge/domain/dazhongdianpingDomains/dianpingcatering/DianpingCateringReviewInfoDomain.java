package com.knowledge.domain.dazhongdianpingDomains.dianpingcatering;

import com.knowledge.Annotations.FieldMethodAnnotation;

public class DianpingCateringReviewInfoDomain {

    @FieldMethodAnnotation(FieldReallyName = "图片",MethodName = "setPictureNum",ParameterType = int.class)
    private int pictureNum;

    @FieldMethodAnnotation(FieldReallyName = "好评", MethodName = "setGoodNum", ParameterType = int.class)
    private int goodNum;

    @FieldMethodAnnotation(FieldReallyName = "差评",MethodName = "setBadNum",ParameterType = int.class)
    private int badNum;

    @FieldMethodAnnotation(FieldReallyName = "中评", MethodName = "setCommonNum", ParameterType = int.class)
    private int commonNum;

    public DianpingCateringReviewInfoDomain() {
    }
    public DianpingCateringReviewInfoDomain(int pictureNum, int goodNum, int badNum, int commonNum) {
        this.pictureNum = pictureNum;
        this.goodNum = goodNum;
        this.badNum = badNum;
        this.commonNum = commonNum;
    }

    public int getPictureNum() {
        return pictureNum;
    }

    public void setPictureNum(int pictureNum) {
        this.pictureNum = pictureNum;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public int getBadNum() {
        return badNum;
    }

    public void setBadNum(int badNum) {
        this.badNum = badNum;
    }

    public int getCommonNum() {
        return commonNum;
    }

    public void setCommonNum(int commonNum) {
        this.commonNum = commonNum;
    }
}
