package com.knowledge.Utils.DomainUtilsPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.knowledge.Annotations.FieldMethodAnnotation;
import com.knowledge.Utils.CommonUtilsPackage.DataTransformateCommonUtils;
import com.knowledge.Utils.CommonUtilsPackage.MongoDBConnectionUtils;
import com.knowledge.Utils.Neo4jUtilsPackage.XieChengHotelNeo4jUtils;
import com.knowledge.domain.XieChengDomains.*;
import com.knowledge.domain.XieChengHotelApplicationDomain;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 携程工具类
 */
public class XieChengHotelUtils {

    private static   ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     *   目前只用于 携程XieChengHotel.shop_intro数据转化 domain  XieChengCombinationHotelIntro
     * @param strshop_intro  表示需要解析的json字符串
     * @param className  需要转化为的类名称
     * @return
     */
    public  static  Object XieChengIntroConvertToDomains(String strshop_intro,String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        JSONObject jsonObject = JSON.parseObject(strshop_intro);
        Class<?> aClass = Class.forName(className);
        Object dest = aClass.newInstance();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            FieldMethodAnnotation annotation = field.getAnnotation(FieldMethodAnnotation.class);
            String reallyName = annotation.FieldReallyName();
            String methodName = annotation.MethodName();
            Class<?> parameterType = annotation.ParameterType();
            String obt = jsonObject.get(reallyName).toString();
            //解析获得目的对象
            Object object = JSON.parseObject(obt, parameterType);
            Method method = aClass.getMethod(methodName, parameterType);
            method.invoke(dest, object);
        }
        return dest;
    }


    //把携程酒店静态数据写入到图数据库

    private  static void  WriteXieChengStaticDatatToNeo4j() {
        MongoClient remoteServiceClient = null;
        try{
//
             remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
            MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("shops");
            MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_source", "酒店"), Filters.eq("data_website", "携程"))).iterator();
            while (iterator.hasNext()) {
               Document document = iterator.next();
               XieChengHotel xichotel = (XieChengHotel) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.XieChengDomains.XieChengHotel", document);
               System.out.println("-----------------------------");
               System.out.println(xichotel.getShop_name());
               System.out.println(xichotel.getShop_url());
               System.out.println("------------------------------");
               //解析 酒店介绍信息
               XieChengCombinationHotelIntro combinationHotelIntro = (XieChengCombinationHotelIntro) XieChengIntroConvertToDomains(xichotel.getShop_intro(), "com.knowledge.domain.XieChengDomains.XieChengCombinationHotelIntro");
               System.out.println(combinationHotelIntro);

               //解析周边信息
               XieChengAroundFacility AroundFacility = JSON.parseObject(xichotel.getShop_around_facilities(), XieChengAroundFacility.class);
               System.out.println(AroundFacility);

               //解析携程评论数据
               XieChengShopStatistics statistics = JSON.parseObject(xichotel.getShop_statistics(), XieChengShopStatistics.class);
               System.out.println(statistics);

               //房型信息
               System.out.println(xichotel.getShop_room_recommend_all());
               XieChengHotelAllRooms xieRooms = JSON.parseObject(xichotel.getShop_room_recommend_all(), XieChengHotelAllRooms.class);
               System.out.println(xieRooms);
                //XieChengHotelNeo4jUtils neo4jUtils = new XieChengHotelNeo4jUtils(null);
               //neo4jUtils.CreateXieChengDataToNeo4jNode(xichotel,AroundFacility, statistics, xieRooms,combinationHotelIntro);
                executorService.submit(new XieChengHotelNeo4jUtils(null, new XieChengHotelApplicationDomain(xichotel, AroundFacility, statistics, xieRooms, combinationHotelIntro), 1));

           }
       }catch (Exception ex){
            ex.printStackTrace();
       }finally {
            executorService.shutdown();
            try {
                if(!executorService.awaitTermination(120, TimeUnit.MINUTES))
                    executorService.shutdown();

            } catch (InterruptedException e) {
                e.printStackTrace();
                executorService.shutdown();
            }

            if(remoteServiceClient!=null)
               remoteServiceClient.close();
        }
    }



    public static void main(String... args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, InterruptedException {

        //WriteXieChengStaticDatatToNeo4j();
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("comments");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_website", "携程"), Filters.eq("data_source", "酒店"))).noCursorTimeout(true).iterator();
        int i = 0;
        ConnectionPoolFactory.close();
        executorService = Executors.newFixedThreadPool(10);
        while (iterator.hasNext()) {
            Document document = iterator.next();
            XieChengHotelComments xieChengHotelComments = (XieChengHotelComments) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.XieChengDomains.XieChengHotelComments", document);
            System.out.println("------------------------------");
            System.out.println(xieChengHotelComments.getComment_user_name()+"  "+xieChengHotelComments.getComment_user_check_in());
            System.out.println("------------------------------");
            executorService.submit(new XieChengHotelNeo4jUtils(xieChengHotelComments, null, 0));
            System.out.println("------------"+(++i)+"---------------");
        }
        try {
            executorService.shutdown();
            if (!executorService.awaitTermination(120, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            executorService.shutdownNow();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }
}
