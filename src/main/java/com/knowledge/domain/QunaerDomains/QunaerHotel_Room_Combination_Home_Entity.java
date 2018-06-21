package com.knowledge.domain.QunaerDomains;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class QunaerHotel_Room_Combination_Home_Entity {

    @JSONField(name = "detail")
    private QunaerHotel_Room_Combination_Detail detail;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "room_list")
    private List<String> room_list;

    public QunaerHotel_Room_Combination_Home_Entity() {
    }

    public QunaerHotel_Room_Combination_Home_Entity(QunaerHotel_Room_Combination_Detail detail, String name, List<String> room_list) {
        this.detail = detail;
        this.name = name;
        this.room_list = room_list;
    }

    @Override
    public String toString() {
        return "QunaerHotel_Room_Combination_Home_Entity{" +
                "detail=" + detail +
                ", name='" + name + '\'' +
                ", room_list=" + room_list +
                '}';
    }

    public QunaerHotel_Room_Combination_Detail getDetail() {
        return detail;
    }

    public void setDetail(QunaerHotel_Room_Combination_Detail detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoom_list() {
        return room_list;
    }

    public void setRoom_list(List<String> room_list) {
        this.room_list = room_list;
    }
}
