package com.knowledge.domain;

import com.knowledge.domain.XieChengDomains.*;

/**
 * 携程酒店应用数据总体结构
 * 作为静态数据的混合体
 *
 */
public class XieChengHotelApplicationDomain {

    private XieChengHotel xieChengHotel;

    private XieChengAroundFacility xieChengAroundFacility;

    private XieChengShopStatistics xieChengShopStatistics;

    private XieChengHotelAllRooms xieChengHotelAllRooms;

    private XieChengCombinationHotelIntro combinationHotelIntro;

    public XieChengHotelApplicationDomain() {
    }

    public XieChengHotelApplicationDomain(XieChengHotel xieChengHotel, XieChengAroundFacility xieChengAroundFacility, XieChengShopStatistics xieChengShopStatistics, XieChengHotelAllRooms xieChengHotelAllRooms, XieChengCombinationHotelIntro combinationHotelIntro) {
        this.xieChengHotel = xieChengHotel;
        this.xieChengAroundFacility = xieChengAroundFacility;
        this.xieChengShopStatistics = xieChengShopStatistics;
        this.xieChengHotelAllRooms = xieChengHotelAllRooms;
        this.combinationHotelIntro = combinationHotelIntro;
    }

    public XieChengHotel getXieChengHotel() {
        return xieChengHotel;
    }

    public void setXieChengHotel(XieChengHotel xieChengHotel) {
        this.xieChengHotel = xieChengHotel;
    }

    public XieChengAroundFacility getXieChengAroundFacility() {
        return xieChengAroundFacility;
    }

    public void setXieChengAroundFacility(XieChengAroundFacility xieChengAroundFacility) {
        this.xieChengAroundFacility = xieChengAroundFacility;
    }

    public XieChengShopStatistics getXieChengShopStatistics() {
        return xieChengShopStatistics;
    }

    public void setXieChengShopStatistics(XieChengShopStatistics xieChengShopStatistics) {
        this.xieChengShopStatistics = xieChengShopStatistics;
    }

    public XieChengHotelAllRooms getXieChengHotelAllRooms() {
        return xieChengHotelAllRooms;
    }

    public void setXieChengHotelAllRooms(XieChengHotelAllRooms xieChengHotelAllRooms) {
        this.xieChengHotelAllRooms = xieChengHotelAllRooms;
    }

    public XieChengCombinationHotelIntro getCombinationHotelIntro() {
        return combinationHotelIntro;
    }

    public void setCombinationHotelIntro(XieChengCombinationHotelIntro combinationHotelIntro) {
        this.combinationHotelIntro = combinationHotelIntro;
    }
}
