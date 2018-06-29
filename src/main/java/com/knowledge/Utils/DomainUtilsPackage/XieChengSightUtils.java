package com.knowledge.Utils.DomainUtilsPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.knowledge.Utils.CommonUtilsPackage.DataTransformateCommonUtils;
import com.knowledge.Utils.CommonUtilsPackage.MongoDBConnectionUtils;
import com.knowledge.Utils.ConstructDataTypePackage.MyNode;
import com.knowledge.Utils.Neo4jUtilsPackage.XieChengSightNeo4jUitls;
import com.knowledge.domain.XieChengDomains.Sight.XieChengSightCombinationInfos;
import com.knowledge.domain.XieChengDomains.Sight.XieChengSightCombinationTicket;
import com.knowledge.domain.XieChengDomains.Sight.XieChengSightDomain;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.lang.reflect.InvocationTargetException;

public class XieChengSightUtils {




    public static void main(String... args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {


        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("shops");
        MongoCursor<Document> iterator =
                collection.find(Filters.and(Filters.eq("data_source", "景点"), Filters.eq("data_website", "携程"))).iterator();

//        Document document = collection.find(Filters.and(Filters.eq("data_source", "景点"), Filters.eq("data_website", "携程"))).first();

        while (iterator.hasNext()) {
            Document document= iterator.next();
            XieChengSightDomain xieChengSightDomain = (XieChengSightDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.XieChengDomains.Sight.XieChengSightDomain", document);
            System.out.println(xieChengSightDomain);
            //门票 吃喝 玩乐的组合
            XieChengSightCombinationTicket xieChengSightCombinationTicket = JSON.parseObject(xieChengSightDomain.getShop_ticket(), XieChengSightCombinationTicket.class);
            System.out.println(xieChengSightDomain.getShop_url());
            //景点介绍
            String shop_info = xieChengSightDomain.getShop_info();
            System.out.println(shop_info);
            XieChengSightCombinationInfos xieChengSightCombinationInfos = JSON.parseObject(shop_info, XieChengSightCombinationInfos.class);
            System.out.println(xieChengSightCombinationInfos);

            if (xieChengSightCombinationTicket != null) {
                JSONObject tickets =
                        xieChengSightCombinationTicket.getTickets();
                JSONObject ticket = (JSONObject) tickets.get("ticket");
                if(ticket==null) continue;
                JSONArray arrays= (JSONArray) ticket.get("title");
                System.out.println(arrays);

                MyNode<String> root = new MyNode<>("门票");
                root.setIsstart(true);
                if(arrays!=null)
               DataTransformateCommonUtils.CreateTheTree(root, arrays);
                xieChengSightCombinationTicket.setRoot(root);
                System.out.println("-------------");
                DataTransformateCommonUtils.TraversalTheTree(root);

        }
             //XieChengSightNeo4jUitls neo4jUtils = new XieChengSightNeo4jUitls();
             //neo4jUtils.CreateXieChengSightDataToNeo4jNode(xieChengSightDomain, xieChengSightCombinationInfos, xieChengSightCombinationTicket);
            //清除之前数据
           DataTransformateCommonUtils.alllistNodes.clear();
       }
        remoteServiceClient.close();
    }


}
