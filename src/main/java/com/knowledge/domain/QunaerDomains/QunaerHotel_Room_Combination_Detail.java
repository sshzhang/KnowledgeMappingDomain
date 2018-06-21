package com.knowledge.domain.QunaerDomains;

import java.util.List;

/**
 * 去哪儿酒店房型 detail
 */
public class QunaerHotel_Room_Combination_Detail {

    private List<String> facility_list;

    private List<String> room_area;

    public QunaerHotel_Room_Combination_Detail() {
    }

    public QunaerHotel_Room_Combination_Detail(List<String> facility_list, List<String> room_area) {
        this.facility_list = facility_list;
        this.room_area = room_area;
    }

    @Override
    public String toString() {
        return "QunaerHotel_Room_Combination_Detail{" +
                "facility_list=" + facility_list +
                ", room_area=" + room_area +
                '}';
    }

    public List<String> getFacility_list() {
        return facility_list;
    }

    public void setFacility_list(List<String> facility_list) {
        this.facility_list = facility_list;
    }

    public List<String> getRoom_area() {
        return room_area;
    }

    public void setRoom_area(List<String> room_area) {
        this.room_area = room_area;
    }
}
