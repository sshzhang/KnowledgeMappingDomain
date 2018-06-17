package com.knowledge.domain.XieChengDomains;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 具体房型的描述信息
 */
public class XieChengHotelRoomInfoListSpecific {

    @JSONField(name = "入住人数")
    private String livePeopleNums;

    @JSONField(name = "宽带")
    private String internet;

    @JSONField(name = "床型")
    private String bedType;

    @JSONField(name = "房价")
    private String homePrice;

    @JSONField(name = "政策")
    private String policy;

    @JSONField(name = "早餐")
    private String breakfast;

    @Override
    public String toString() {
        return "XieChengHotelRoomInfoListSpecific{" +
                "livePeopleNums='" + livePeopleNums + '\'' +
                ", internet='" + internet + '\'' +
                ", bedType='" + bedType + '\'' +
                ", homePrice='" + homePrice + '\'' +
                ", policy='" + policy + '\'' +
                ", breakfast='" + breakfast + '\'' +
                ", satisfactionDegree='" + satisfactionDegree + '\'' +
                '}';
    }

    @JSONField(name = "满意度")
    private String satisfactionDegree;

    public XieChengHotelRoomInfoListSpecific() {
    }

    public XieChengHotelRoomInfoListSpecific(String livePeopleNums, String internet, String bedType, String homePrice, String policy, String breakfast, String satisfactionDegree) {
        this.livePeopleNums = livePeopleNums;
        this.internet = internet;
        this.bedType = bedType;
        this.homePrice = homePrice;
        this.policy = policy;
        this.breakfast = breakfast;
        this.satisfactionDegree = satisfactionDegree;
    }


    public String getLivePeopleNums() {
        return livePeopleNums;
    }

    public void setLivePeopleNums(String livePeopleNums) {
        this.livePeopleNums = livePeopleNums;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getHomePrice() {
        return homePrice;
    }

    public void setHomePrice(String homePrice) {
        this.homePrice = homePrice;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getSatisfactionDegree() {

        //预处理以下数据
        satisfactionDegree=satisfactionDegree.replace("\"","'");
        return satisfactionDegree;
    }

    public void setSatisfactionDegree(String satisfactionDegree) {
        this.satisfactionDegree = satisfactionDegree;
    }


    public static void main(String... args) {

        XieChengHotelRoomInfoListSpecific xieChengHotelRoomInfoListSpecific = new XieChengHotelRoomInfoListSpecific();
        String stm = "(\"氦气球体验\"套餐)闪住 礼预订满意度 91%";
        System.out.println(stm);
        String stn=stm.replace("\"","'");
        System.out.println(stn);
        xieChengHotelRoomInfoListSpecific.setSatisfactionDegree("\""+stn+"\",snnn");
        System.out.println(xieChengHotelRoomInfoListSpecific.getSatisfactionDegree());
    }

}
