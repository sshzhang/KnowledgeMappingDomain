package com.knowledge.domain.XieChengDomains;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 具体的房型概括信息
 */
public class XieChengHotelRoom_detailSpecific implements Serializable {

    @JSONField(alternateNames = {"可加床", "不可加床"})
    private String increaseBed;

    @JSONField(name = "便利设施")
    private String convientFacility;

    @JSONField(name = "床型")
    private String bedType;

    @JSONField(name = "建筑面积")
    private String buidingArea;

    @JSONField(name = "房型")
    private String homeType;

    @JSONField(name = "楼层")
    private String floor;

    @JSONField(name = "浴室")
    private String bedthroom;

    @JSONField(alternateNames = {"该房可无烟处理","不可吸烟"})
    private String noSmaokingMeasure;

    public XieChengHotelRoom_detailSpecific() {
    }

    @Override
    public String toString() {
        return "XieChengHotelRoom_detailSpecific{" +
                "increaseBed='" + increaseBed + '\'' +
                ", convientFacility='" + convientFacility + '\'' +
                ", bedType='" + bedType + '\'' +
                ", buidingArea='" + buidingArea + '\'' +
                ", homeType='" + homeType + '\'' +
                ", floor='" + floor + '\'' +
                ", bedthroom='" + bedthroom + '\'' +
                ", noSmaokingMeasure='" + noSmaokingMeasure + '\'' +
                '}';
    }

    public XieChengHotelRoom_detailSpecific(String increaseBed, String convientFacility, String bedType, String buidingArea, String homeType, String floor, String bedthroom, String noSmaokingMeasure) {
        this.increaseBed = increaseBed;
        this.convientFacility = convientFacility;
        this.bedType = bedType;
        this.buidingArea = buidingArea;
        this.homeType = homeType;
        this.floor = floor;
        this.bedthroom = bedthroom;
        this.noSmaokingMeasure = noSmaokingMeasure;
    }


    public String getIncreaseBed() {
        return increaseBed;
    }

    public void setIncreaseBed(String increaseBed) {
        this.increaseBed = increaseBed;
    }

    public String getConvientFacility() {
        return convientFacility;
    }

    public void setConvientFacility(String convientFacility) {
        this.convientFacility = convientFacility;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBuidingArea() {
        return buidingArea;
    }

    public void setBuidingArea(String buidingArea) {
        this.buidingArea = buidingArea;
    }

    public String getHomeType() {
        if (homeType != null && (!"".equals(homeType))) {
            homeType = homeType.split(" ")[0];
        }
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBedthroom() {
        return bedthroom;
    }

    public void setBedthroom(String bedthroom) {
        this.bedthroom = bedthroom;
    }

    public String getNoSmaokingMeasure() {
        return noSmaokingMeasure;
    }

    public void setNoSmaokingMeasure(String noSmaokingMeasure) {
        this.noSmaokingMeasure = noSmaokingMeasure;
    }
}
