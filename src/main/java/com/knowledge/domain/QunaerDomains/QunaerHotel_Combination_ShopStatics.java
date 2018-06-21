package com.knowledge.domain.QunaerDomains;

import com.alibaba.fastjson.annotation.JSONField;

public class QunaerHotel_Combination_ShopStatics {
    @JSONField(name = "statics")
    private QunaerHotel_ShopStatics statics;

    public QunaerHotel_Combination_ShopStatics() {
    }

    public QunaerHotel_Combination_ShopStatics(QunaerHotel_ShopStatics statics) {
        this.statics = statics;
    }

    @Override
    public String toString() {
        return "QunaerHotel_Combination_ShopStatics{" +
                "statics=" + statics +
                '}';
    }

    public QunaerHotel_ShopStatics getStatics() {
        return statics;
    }

    public void setStatics(QunaerHotel_ShopStatics statics) {
        this.statics = statics;
    }
}
