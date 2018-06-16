package com.knowledge.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.knowledge.Annotations.FieldMethodAnnotation;
import com.knowledge.domain.XieChengDomains.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
/**
 * 携程工具类
 */
public class XieChengUtils {

    public static Object DocumentConvextToModel(String className, Document document) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Set<String> strings = document.keySet();
        Class<?> aClass = Class.forName(className);
        Object dest = aClass.newInstance();
        for (String strKey : strings) {
            Field field = aClass.getDeclaredField(strKey);
            FieldMethodAnnotation annotation = field.getAnnotation(FieldMethodAnnotation.class);
            String methodName = annotation.MethodName();
            Class<?> classType = annotation.ParameterType();
            Method method = aClass.getMethod(methodName, classType);
            if (String.class.isAssignableFrom(classType)) {
                method.invoke(dest, document.get(strKey).toString());
            } else if (int.class.isAssignableFrom(classType)) {
                method.invoke(dest, Integer.parseInt(document.get(strKey).toString()));
            } else if (float.class.isAssignableFrom(classType)) {
                method.invoke(dest, Float.parseFloat(document.get(strKey).toString()));
            }
        }
        return dest;
    }


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






    public static void main(String... args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {
        MongoClient localServiceClient =
                MongoDBConnectionUtils.getLocalServiceClient();
        MongoCollection<Document> collection =
                localServiceClient.getDatabase("hotel").getCollection("xiechenghotel");
        MongoCursor<Document> iterator =
                collection.find().iterator();
        while (iterator.hasNext()) {

            Document document = iterator.next();

            XieChengHotel xichotel = (XieChengHotel) DocumentConvextToModel("com.knowledge.domain.XieChengDomains.XieChengHotel", document);

            //解析 酒店介绍信息
            XieChengCombinationHotelIntro combinationHotelIntro = (XieChengCombinationHotelIntro) XieChengIntroConvertToDomains(xichotel.getShop_intro(), "com.knowledge.domain.XieChengDomains.XieChengCombinationHotelIntro");
            System.out.println(combinationHotelIntro);

            //解析周边信息
            System.out.println(xichotel.getShop_statistics());
            XieChengAroundFacility AroundFacility = JSON.parseObject("{\"景点\": [\"中心湖区景点\"], \"娱乐\": [\"进贤湾水上乐园，水下古城文化科技主题乐园\"]}", XieChengAroundFacility.class);
            System.out.println(AroundFacility);

            //解析携程评论数据
            XieChengShopStatistics statistics = JSON.parseObject(xichotel.getShop_statistics(), XieChengShopStatistics.class);
            System.out.println(statistics);
            //房型信息
            System.out.println(xichotel.getShop_room_recommend_all());
            XieChengHotelAllRooms xieRooms = JSON.parseObject(xichotel.getShop_room_recommend_all(), XieChengHotelAllRooms.class);
            System.out.println(xieRooms);
            Neo4jUtils neo4jUtils = new Neo4jUtils("bolt://192.168.199.202:7687", "neo4j", "09120912");
//        neo4jUtils.CreateXieChengDataToNeo4jNode(xichotel,AroundFacility, statistics, xieRooms,combinationHotelIntro);



        }

    }


}
