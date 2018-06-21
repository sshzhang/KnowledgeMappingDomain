package com.knowledge.domain.QunaerDomains;

import java.util.List;

public class QunaerHotel_Room_Combination_Home_Entities {
    private List<QunaerHotel_Room_Combination_Home_Entity> entity;

    public QunaerHotel_Room_Combination_Home_Entities() {
    }

    public QunaerHotel_Room_Combination_Home_Entities(List<QunaerHotel_Room_Combination_Home_Entity> entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "QunaerHotel_Room_Combination_Home_Entities{" +
                "entity=" + entity +
                '}';
    }

    public List<QunaerHotel_Room_Combination_Home_Entity> getEntity() {
        return entity;
    }

    public void setEntity(List<QunaerHotel_Room_Combination_Home_Entity> entity) {
        this.entity = entity;
    }
}
