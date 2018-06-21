package com.knowledge.domain.QunaerDomains;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 去哪儿酒店位置交通
 */
public class QunaerHotel_LocationShopTraffic {

    @JSONField(name = "qunaerLocationShopTraficSepcifics")
    private List<Qunaer_LocationShopTrafic_Sepcific> qunaerLocationShopTraficSepcifics;



    public QunaerHotel_LocationShopTraffic() {
    }

    public QunaerHotel_LocationShopTraffic(List<Qunaer_LocationShopTrafic_Sepcific> qunaerLocationShopTraficSepcifics) {
        this.qunaerLocationShopTraficSepcifics = qunaerLocationShopTraficSepcifics;
    }

    @Override
    public String toString() {
        return "QunaerHotel_LocationShopTraffic{" +
                "qunaerLocationShopTraficSepcifics=" + qunaerLocationShopTraficSepcifics +
                '}';
    }

    public List<Qunaer_LocationShopTrafic_Sepcific> getQunaerLocationShopTraficSepcifics() {
        return qunaerLocationShopTraficSepcifics;
    }

    public void setQunaerLocationShopTraficSepcifics(List<Qunaer_LocationShopTrafic_Sepcific> qunaerLocationShopTraficSepcifics) {
        this.qunaerLocationShopTraficSepcifics = qunaerLocationShopTraficSepcifics;
    }
}
