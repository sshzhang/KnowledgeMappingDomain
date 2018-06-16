package com.knowledge.domain.XieChengDomains;

import java.util.List;

/**
 * 所有的房型信息列表
 *
 */
public class XieChengHotelAllRooms {

    private List<XieChengHotelRoom_detail> all_room;

    public void setAll_room(List<XieChengHotelRoom_detail> all_room) {
        this.all_room = all_room;
    }

    @Override
    public String toString() {
        return "XieChengHotelAllRooms{" +
                "all_room=" + all_room +
                '}';
    }

    public List<XieChengHotelRoom_detail> getAll_room() {
        return all_room;
    }

    public XieChengHotelAllRooms() {
    }

    public XieChengHotelAllRooms(List<XieChengHotelRoom_detail> all_room) {
        this.all_room = all_room;
    }
}
