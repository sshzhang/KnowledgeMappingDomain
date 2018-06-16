package com.knowledge.domain.XieChengDomains;

import java.util.List;

/**
 * 具体的某种房型信息
 */
public class XieChengHotelRoom_detail {


    private XieChengHotelRoom_detailSpecific room_detail;

    private List<XieChengHotelRoomInfoListSpecific> room_info_list;


    public XieChengHotelRoom_detail() {
    }

    public XieChengHotelRoom_detail(XieChengHotelRoom_detailSpecific room_detail, List<XieChengHotelRoomInfoListSpecific> room_info_list) {
        this.room_detail = room_detail;
        this.room_info_list = room_info_list;
    }

    public XieChengHotelRoom_detailSpecific getRoom_detail() {
        return room_detail;
    }

    @Override
    public String toString() {
        return "XieChengHotelRoom_detail{" +
                "room_detail=" + room_detail +
                ", room_info_list=" + room_info_list +
                '}';
    }

    public void setRoom_detail(XieChengHotelRoom_detailSpecific room_detail) {
        this.room_detail = room_detail;
    }

    public List<XieChengHotelRoomInfoListSpecific> getRoom_info_list() {
        return room_info_list;
    }

    public void setRoom_info_list(List<XieChengHotelRoomInfoListSpecific> room_info_list) {
        this.room_info_list = room_info_list;
    }
}
