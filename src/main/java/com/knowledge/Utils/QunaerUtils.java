package com.knowledge.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.knowledge.domain.QunaerDomains.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Iterator;
import java.util.List;

/**
 * 去哪儿工具类
 */
public class QunaerUtils {

    public static void main(String... args) {

        MongoClient localServiceClient = null;
        try {
            localServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
            MongoCollection<Document> collection =
                    localServiceClient.getDatabase("dspider2").getCollection("shops");
            MongoCursor<Document> iterator =
                    collection.find(Filters.and(Filters.eq("data_website", "去哪儿"), Filters.eq("data_source", "酒店"))).iterator();
            while (iterator.hasNext()) {
                Document document = iterator.next();
                QunaerHotel qunaerHotel = (QunaerHotel) XieChengUtils.DocumentConvextToModel("com.knowledge.domain.QunaerDomains.QunaerHotel", document);
                System.out.println(qunaerHotel.getShop_name() + "  " + qunaerHotel.getShop_room_recommend_all());
                if ((!"".equals(qunaerHotel.getShop_room_recommend_all())) && qunaerHotel.getShop_room_recommend_all() != null) {
                    //设置去哪儿房屋信息
                    QunaerHotel_Room_Combination_Home_Entities qunaerHotel_room_combination_home_entities = JSON.parseObject("{" + "entity:" + qunaerHotel.getShop_room_recommend_all() + "}", QunaerHotel_Room_Combination_Home_Entities.class);
                    List<QunaerHotel_Room_Combination_Home_Entity> entity = qunaerHotel_room_combination_home_entities.getEntity();
                    System.out.println(qunaerHotel.getShop_name() + "  " + entity);
                }

                //周边位置交通
                String shop_traffic = qunaerHotel.getShop_traffic();
                System.out.println(shop_traffic);
                if ((!"".equals(shop_traffic)) && shop_traffic != null) {
                    QunaerHotel_LocationShopTraffic qunaerHotel_locationShopTraffic = JSON.parseObject("{" + "qunaerLocationShopTraficSepcifics:" + shop_traffic + "}", QunaerHotel_LocationShopTraffic.class);
                    System.out.println(qunaerHotel_locationShopTraffic);
                }

                //去哪儿设施概况
                System.out.println(qunaerHotel.getShop_facilities());
                JSONObject jsonObject = JSONObject.parseObject("{" + "qunaerLocationShopTraficSepcifics:" + qunaerHotel.getShop_facilities() + "}");
                System.out.println(jsonObject);
                JSONArray qunaerLocationShopTraficSepcifics = (JSONArray) jsonObject.get("qunaerLocationShopTraficSepcifics");
                int size =
                        qunaerLocationShopTraficSepcifics.size();
                Iterator<Object> iterator1 =
                        qunaerLocationShopTraficSepcifics.iterator();
                while (iterator1.hasNext()) {
                    JSONObject object = (JSONObject) iterator1.next();
                    /**
                     * [联系方式]  [["电话0571-64812888"]]
                     [基本信息]  [["招待所"]]
                     [酒店简介]  [["淳安县人民政府招待所办公地址设在千岛湖镇排岭南路４８号，所长钱峰斌，主要经营住宿、餐饮、娱乐服务、公路客运；糖果糕点、罐头食品、酒、饮料、冷饮制品、日用百货、旅游小商品，注册资本30万元，在职员工63名。"]]
                     [房间设施]  [["空调 国际长途电话 吹风机 24小时热水 暖气"]]
                     [酒店服务]  [["接待外宾 行李寄存"]]
                     */
                    System.out.println(object.keySet() + "  " + object.values());
                }
                //统计评论信息
                String shop_statistics = qunaerHotel.getShop_statistics();
                System.out.println("shop_statistics: "+shop_statistics);
                if ((!"".equals(shop_statistics)) && shop_statistics != null) {
                    QunaerHotel_Combination_ShopStatics qunaerHotel_shopStatics = JSON.parseObject("{" + "statics:" + shop_statistics + "}", QunaerHotel_Combination_ShopStatics.class);
                    System.out.println(qunaerHotel_shopStatics);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            localServiceClient.close();
        }
    }
}
