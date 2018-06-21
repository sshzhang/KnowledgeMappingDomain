package com.knowledge.domain.QunaerDomains;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 位置交通具体的信息数据
 */
public class Qunaer_LocationShopTrafic_Sepcific {

    @JSONField(name = "neighbor_list")
    private List<String> neighbor_list;

    @JSONField(name = "type")
    private String type;

    public Qunaer_LocationShopTrafic_Sepcific() {
    }

    public Qunaer_LocationShopTrafic_Sepcific(List<String> neighbor_list, String type) {
        this.neighbor_list = neighbor_list;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Qunaer_LocationShopTrafic_Sepcific{" +
                "neighbor_list=" + neighbor_list +
                ", type='" + type + '\'' +
                '}';
    }

    public List<String> getNeighbor_list() {
        return neighbor_list;
    }

    public void setNeighbor_list(List<String> neighbor_list) {
        this.neighbor_list = neighbor_list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
