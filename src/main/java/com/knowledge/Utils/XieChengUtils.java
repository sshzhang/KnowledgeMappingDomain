package com.knowledge.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.knowledge.Annotations.FieldMethodAnnotation;
import com.knowledge.domain.XieChengDomains.*;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 携程工具类
 */
public class XieChengUtils {

    private static final  ExecutorService executorService = Executors.newFixedThreadPool(10);
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


    //把携程酒店静态数据写入到图数据库

    private  static void  WriteXieChengStaticDatatToNeo4j() {
        MongoClient localServiceClient = null;
        try{
          localServiceClient=
                   MongoDBConnectionUtils.getLocalServiceClient();
           MongoCollection<Document> collection =
                   localServiceClient.getDatabase("hotel").getCollection("xiechenghotel");
           MongoCursor<Document> iterator =
                   collection.find().iterator();
           while (iterator.hasNext()) {
               Document document = iterator.next();
               XieChengHotel xichotel = (XieChengHotel) DocumentConvextToModel("com.knowledge.domain.XieChengDomains.XieChengHotel", document);
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
               Neo4jUtils neo4jUtils = new Neo4jUtils(null);
               neo4jUtils.CreateXieChengDataToNeo4jNode(xichotel,AroundFacility, statistics, xieRooms,combinationHotelIntro);
           }
       }catch (Exception ex){
            ex.printStackTrace();
       }finally {
           if(localServiceClient!=null)
            localServiceClient.close();
        }
    }



    public static void main(String... args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {


        WriteXieChengStaticDatatToNeo4j();
        MongoClient localServiceClient = MongoDBConnectionUtils.getLocalServiceClient();
        MongoCollection<Document> collection = localServiceClient.getDatabase("hotel").getCollection("xiechenghotelcomment");
        MongoCursor<Document> iterator = collection.find().noCursorTimeout(true).iterator();
        while (iterator.hasNext()) {
            Document document = iterator.next();
            XieChengHotelComments xieChengHotelComments = (XieChengHotelComments) DocumentConvextToModel("com.knowledge.domain.XieChengDomains.XieChengHotelComments", document);
            System.out.println("------------------------------");
            System.out.println(xieChengHotelComments.getComment_user_name()+"  "+xieChengHotelComments.getComment_user_check_in());
            System.out.println("------------------------------");
            executorService.submit(new Neo4jUtils(xieChengHotelComments));
            //neo4jUtils.CreateXieChengCommentDataToNeo4jNode(xieChengHotelComments);
        }
        executorService.shutdown();
        localServiceClient.close();

    }
}
