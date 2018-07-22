package com.knowledge.Utils.DomainUtilsPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.knowledge.Annotations.FieldMethodAnnotation;
import com.knowledge.Utils.CommonUtilsPackage.ConnectionPoolFactory;
import com.knowledge.Utils.CommonUtilsPackage.DataTransformateCommonUtils;
import com.knowledge.Utils.CommonUtilsPackage.LogsUtils;
import com.knowledge.Utils.CommonUtilsPackage.MongoDBConnectionUtils;
import com.knowledge.Utils.Neo4jUtilsPackage.*;
import com.knowledge.domain.DianpingCateringApplicationDomain;
import com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 大众点评美食(娱乐)工具解析类
 */
public class DianPingCateringUtils {

    private static ExecutorService executorService =null;

    public static void main(String... args) {

        CreateCateringStaticContent();
        CreateCateringCommentContent();
    }


    private static void CreateHeathComment() {
        executorService = Executors.newFixedThreadPool(10);
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("comments");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_source", "健康"), Filters.eq("data_website", "大众点评"))).iterator();
        CateringCommentDomain dest = null;
        while (iterator.hasNext()) {
            try {
                Document document = iterator.next();
                dest = (CateringCommentDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringCommentDomain", document);
                System.out.println(dest);
                executorService.submit(new DianpingHealthNeo4jUtils(dest, null, 0));
                System.out.println("------------------------");
            } catch (Exception ex) {
                LogsUtils.WriteTheDataToFile(dest.getShop_name()+" "+dest.getComment_user_name()+"\n"+ex.getMessage(), "src/resources/healthComment.txt");
                ex.printStackTrace();
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(50, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }



    private static void CreateHealthStaticContent() {
        executorService = Executors.newFixedThreadPool(10);
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("shops");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_source", "健康"), Filters.eq("data_website", "大众点评"))).iterator();
        while (iterator.hasNext()) {
            CateringDomain cateringDomain = null;
            try {
                Document document = iterator.next();
                cateringDomain = (CateringDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringDomain", document);
                System.out.println("------------------------");
                System.out.println(cateringDomain.getShop_url());
                DianpingCateringShopStatisticDomain dianpingShopStatisticDomain = getDianpingShopStatisticDomain(cateringDomain.getShop_statistics());
                Map<String, Float> theShopTagesDatas = getTheShopTagesDatas(cateringDomain.getShop_tag());
                Map<String, DianpingCateringShopPromotionDomain> dianpingShopPromotionDomain = getDianpingShopPromotionDomain(cateringDomain.getShop_promotion());
                DianpingshopMenuDomain dianpingShopMenueDomain = getDianpingShopMenueDomain(cateringDomain.getShop_menu());
                executorService.submit(new DianpingHealthNeo4jUtils(null, new DianpingCateringApplicationDomain(cateringDomain, dianpingShopStatisticDomain, dianpingShopPromotionDomain, dianpingShopMenueDomain, theShopTagesDatas), 1));
                System.out.println("------------------------");
            } catch (Exception ex) {
                ex.printStackTrace();
                LogsUtils.WriteTheDataToFile(cateringDomain.getShop_url() + "\n" + ex.getMessage() + "\n\n", "src/resources/healthContent.txt");
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(50, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }




    private static void CreateCarComment() {
        executorService = Executors.newFixedThreadPool(10);
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("comments");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_source", "爱车"), Filters.eq("data_website", "大众点评"))).iterator();
        CateringCommentDomain dest = null;
        while (iterator.hasNext()) {
            try {
                Document document = iterator.next();
                dest = (CateringCommentDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringCommentDomain", document);
                System.out.println(dest);
                executorService.submit(new DianpingCarNeo4jUtils(dest, null, 0));
                System.out.println("------------------------");
            } catch (Exception ex) {
                LogsUtils.WriteTheDataToFile(dest.getShop_name()+" "+dest.getComment_user_name()+"\n"+ex.getMessage(), "src/resources/carComment.txt");
                ex.printStackTrace();
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(50, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }



    private static void CreateCarStaticContent() {
        executorService = Executors.newFixedThreadPool(10);
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("shops");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_source", "爱车"), Filters.eq("data_website", "大众点评"))).iterator();
        while (iterator.hasNext()) {
            CateringDomain cateringDomain = null;
            try {
                Document document = iterator.next();
                cateringDomain = (CateringDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringDomain", document);
                System.out.println("------------------------");
                System.out.println(cateringDomain.getShop_url());
                DianpingCateringShopStatisticDomain dianpingShopStatisticDomain = getDianpingShopStatisticDomain(cateringDomain.getShop_statistics());
                Map<String, Float> theShopTagesDatas = getTheShopTagesDatas(cateringDomain.getShop_tag());
                Map<String, DianpingCateringShopPromotionDomain> dianpingShopPromotionDomain = getDianpingShopPromotionDomain(cateringDomain.getShop_promotion());
                DianpingshopMenuDomain dianpingShopMenueDomain = getDianpingShopMenueDomain(cateringDomain.getShop_menu());
                executorService.submit(new DianpingCarNeo4jUtils(null, new DianpingCateringApplicationDomain(cateringDomain, dianpingShopStatisticDomain, dianpingShopPromotionDomain, dianpingShopMenueDomain, theShopTagesDatas), 1));
                System.out.println("------------------------");
            } catch (Exception ex) {
                ex.printStackTrace();
                LogsUtils.WriteTheDataToFile(cateringDomain.getShop_url() + "\n" + ex.getMessage() + "\n\n", "src/resources/carContent.txt");
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(50, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }




    private static void CreateShoppingComment() {
        executorService = Executors.newFixedThreadPool(10);
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("comments");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_source", "购物"), Filters.eq("data_website", "大众点评"))).iterator();
        CateringCommentDomain dest = null;
        while (iterator.hasNext()) {
            try {
                Document document = iterator.next();
                dest = (CateringCommentDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringCommentDomain", document);
                System.out.println(dest);
                executorService.submit(new DianpingShoppingNeo4jUtils(dest, null, 0));
                System.out.println("------------------------");
            } catch (Exception ex) {
                LogsUtils.WriteTheDataToFile(dest.getShop_name()+" "+dest.getComment_user_name()+"\n"+ex.getMessage(), "src/resources/shoppingComment.txt");
                ex.printStackTrace();
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(50, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }



    private static void CreateShoppingStaticContent() {
        executorService = Executors.newFixedThreadPool(10);
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("shops");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_source", "购物"), Filters.eq("data_website", "大众点评"))).iterator();
        while (iterator.hasNext()) {
            CateringDomain cateringDomain = null;
            try {
                Document document = iterator.next();
                cateringDomain = (CateringDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringDomain", document);
                System.out.println("------------------------");
                System.out.println(cateringDomain.getShop_url());
                DianpingCateringShopStatisticDomain dianpingShopStatisticDomain = getDianpingShopStatisticDomain(cateringDomain.getShop_statistics());
                Map<String, Float> theShopTagesDatas = getTheShopTagesDatas(cateringDomain.getShop_tag());
                Map<String, DianpingCateringShopPromotionDomain> dianpingShopPromotionDomain = getDianpingShopPromotionDomain(cateringDomain.getShop_promotion());
                DianpingshopMenuDomain dianpingShopMenueDomain = getDianpingShopMenueDomain(cateringDomain.getShop_menu());
                executorService.submit(new DianpingShoppingNeo4jUtils(null, new DianpingCateringApplicationDomain(cateringDomain, dianpingShopStatisticDomain, dianpingShopPromotionDomain, dianpingShopMenueDomain, theShopTagesDatas), 1));
                System.out.println("------------------------");
            } catch (Exception ex) {
                ex.printStackTrace();
                LogsUtils.WriteTheDataToFile(cateringDomain.getShop_url() + "\n" + ex.getMessage() + "\n\n", "src/resources/shoppingContent.txt");
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(50, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }





    private static void CreateEntertainmentComment() {
        executorService = Executors.newFixedThreadPool(10);
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("comments");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_source", "娱乐"), Filters.eq("data_website", "大众点评"))).iterator();
        CateringCommentDomain dest = null;
        while (iterator.hasNext()) {
            try {
                Document document = iterator.next();
                dest = (CateringCommentDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringCommentDomain", document);
                System.out.println(dest);
                executorService.submit(new DianpingEntertainmentNeo4jUtils(dest, null, 0));
                System.out.println("------------------------");
            } catch (Exception ex) {
                LogsUtils.WriteTheDataToFile(dest.getShop_name()+" "+dest.getComment_user_name()+"\n"+ex.getMessage(), "src/resources/entertainmentComment.txt");
                ex.printStackTrace();
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(50, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }

    private static void CreateEntertainmentStaticContent() {
        executorService = Executors.newFixedThreadPool(10);
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("shops");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_source", "娱乐"), Filters.eq("data_website", "大众点评"))).iterator();
        while (iterator.hasNext()) {
            CateringDomain cateringDomain = null;
            try {
                Document document = iterator.next();
                cateringDomain = (CateringDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringDomain", document);
                System.out.println("------------------------");
                System.out.println(cateringDomain.getShop_url());
                DianpingCateringShopStatisticDomain dianpingShopStatisticDomain = getDianpingShopStatisticDomain(cateringDomain.getShop_statistics());
                Map<String, Float> theShopTagesDatas = getTheShopTagesDatas(cateringDomain.getShop_tag());
                Map<String, DianpingCateringShopPromotionDomain> dianpingShopPromotionDomain = getDianpingShopPromotionDomain(cateringDomain.getShop_promotion());
                DianpingshopMenuDomain dianpingShopMenueDomain = getDianpingShopMenueDomain(cateringDomain.getShop_menu());
              executorService.submit(new DianpingEntertainmentNeo4jUtils(null, new DianpingCateringApplicationDomain(cateringDomain, dianpingShopStatisticDomain, dianpingShopPromotionDomain, dianpingShopMenueDomain, theShopTagesDatas), 1));
                System.out.println("------------------------");
            } catch (Exception ex) {
                ex.printStackTrace();
                LogsUtils.WriteTheDataToFile(cateringDomain.getShop_url() + "\n" + ex.getMessage() + "\n\n", "src/resources/entertainmentContent.txt");
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(50, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }

    private static void CreateCateringCommentContent() {
        executorService = Executors.newFixedThreadPool(10);
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("comments");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_website", "大众点评"), Filters.eq("data_source", "餐饮"))).noCursorTimeout(true).iterator();
        CateringCommentDomain dest = null;
        while (iterator.hasNext()) {
            try {
                Document document = iterator.next();
                 dest = (CateringCommentDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringCommentDomain", document);
                System.out.println(dest);
                executorService.submit(new DianpingCateringNeo4jUtils(dest, null, 0));
                System.out.println("------------------------");
            } catch (Exception ex) {
                LogsUtils.WriteTheDataToFile(dest.getShop_name()+" "+dest.getComment_user_name()+"\n"+ex.getMessage(), "src/resources/jiexierror.txt");
                ex.printStackTrace();
            }
        }
        executorService.shutdown();
        LogsUtils.WriteTheDataToFile("executorService.shutdown()", "src/resources/cateringComment.txt");
        try {
            if (!executorService.awaitTermination(500, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }
    
    private static void CreateCateringStaticContent() {
        executorService = Executors.newFixedThreadPool(10);
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("shops");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_source", "餐饮"), Filters.eq("data_website", "大众点评"))).iterator();
        while (iterator.hasNext()) {
            CateringDomain cateringDomain = null;
            try {
                Document document = iterator.next();
                cateringDomain = (CateringDomain) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.CateringDomain", document);
                System.out.println("------------------------");
                System.out.println(cateringDomain.getShop_url());
                DianpingCateringShopStatisticDomain dianpingShopStatisticDomain = getDianpingShopStatisticDomain(cateringDomain.getShop_statistics());
                Map<String, Float> theShopTagesDatas = getTheShopTagesDatas(cateringDomain.getShop_tag());
                Map<String, DianpingCateringShopPromotionDomain> dianpingShopPromotionDomain = getDianpingShopPromotionDomain(cateringDomain.getShop_promotion());
                DianpingshopMenuDomain dianpingShopMenueDomain = getDianpingShopMenueDomain(cateringDomain.getShop_menu());
                executorService.submit(new DianpingCateringNeo4jUtils(null, new DianpingCateringApplicationDomain(cateringDomain, dianpingShopStatisticDomain, dianpingShopPromotionDomain, dianpingShopMenueDomain, theShopTagesDatas), 1));
                System.out.println("------------------------");
            } catch (Exception ex) {
                ex.printStackTrace();
                LogsUtils.WriteTheDataToFile(cateringDomain.getShop_url() + "\n" + ex.getMessage() + "\n\n", "src/resources/cateringContent.txt");
            }
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(500, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolFactory.close();
            remoteServiceClient.close();
        }
    }


    /**
     * 解析点评餐饮的shop_tags数据
     * @param shop_tags
     * @return
     */
    private static Map<String, Float> getTheShopTagesDatas(String shop_tags) {

        if(shop_tags==null||"".equals(shop_tags)) return null;
        shop_tags = shop_tags.substring(1, shop_tags.length() - 1);
        String[] splits = shop_tags.split(",");
        Map<String, Float> stringFloatHashMap = new HashMap<>();
        Pattern compile = Pattern.compile("(?<=\")(?<key>.*?)(?<value>[0-9]+[\\.][0-9]*)");
        for (String sp : splits) {
            Matcher matcher =
                    compile.matcher(sp);
            matcher.find();
            stringFloatHashMap.put(matcher.group("key"), Float.parseFloat(matcher.group("value")));
            System.out.println(matcher.group("key")+"  "+matcher.group("value"));
        }
        return stringFloatHashMap;
    }




    private static DianpingCateringShopStatisticDomain getDianpingShopStatisticDomain(String shop_statistic) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        JSONObject object = JSON.parseObject(shop_statistic);
        if (object==null) return null;
        JSONObject peopleThink = (JSONObject) object.get("大家认为");
        DianpingCateringShopStatisticDomain dianpingCateringShopStatisticDomain = new DianpingCateringShopStatisticDomain();
        if (peopleThink != null && (!peopleThink.isEmpty())) {
            Map<String, List<DianpingCateringPeopleReviewDataDomain>> peopleAllThink = new HashMap<>();
            Set<String> keys = peopleThink.keySet();
            List<DianpingCateringPeopleReviewDataDomain> dianpingCateringPeopleReviewDataDomains = new ArrayList<>();
            for (String key : keys) {
                dianpingCateringPeopleReviewDataDomains.add(new DianpingCateringPeopleReviewDataDomain(key, peopleThink.getIntValue(key)));
            }
            peopleAllThink.put("大家认为", dianpingCateringPeopleReviewDataDomains);
            dianpingCateringShopStatisticDomain.setPeopleAllThink(peopleAllThink);
        }
        JSONObject reviewThink = (JSONObject) object.get("评价");

        if (reviewThink != null&&(!reviewThink.isEmpty())) {
            Map<String, List<DianpingCateringPeopleReviewDataDomain>> reviewInfoDomainMap = new HashMap<>();
            List<DianpingCateringPeopleReviewDataDomain> dianpingCateringPeopleReviewDataDomains = new ArrayList<>();
            Set<String> keySet = reviewThink.keySet();
            for (String key : keySet) {
                dianpingCateringPeopleReviewDataDomains.add(new DianpingCateringPeopleReviewDataDomain(key, reviewThink.getIntValue(key)));
            }
            reviewInfoDomainMap.put("评价", dianpingCateringPeopleReviewDataDomains);
            dianpingCateringShopStatisticDomain.setReviewInfoDomainMap(reviewInfoDomainMap);
        }
        return dianpingCateringShopStatisticDomain;
    }

    private static Map<String, DianpingCateringShopPromotionDomain> getDianpingShopPromotionDomain(String shop_promotion) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        JSONObject object = JSON.parseObject(shop_promotion);
        if (object == null) return null;
        Map<String, DianpingCateringShopPromotionDomain> promotionDomainHashMap = new HashMap<>();
        Class<?> aClass = Class.forName("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.DianpingCateringShopPromotionDomain");
        Field[] declaredFields = aClass.getDeclaredFields();

        Set<String> keys = object.keySet();
        for (String key : keys) {
            JSONArray objects = (JSONArray) object.get(key);
            Object dest = aClass.newInstance();
            for (Field declaredField : declaredFields) {
                FieldMethodAnnotation annotation = declaredField.getAnnotation(FieldMethodAnnotation.class);
                String methodName = annotation.MethodName();
                int index = annotation.index();
                if (objects.size() <= index) continue;
                Object value = objects.get(index);
                Method method = aClass.getMethod(methodName, annotation.ParameterType());
                method.invoke(dest, value);
            }
            promotionDomainHashMap.put(key, (DianpingCateringShopPromotionDomain) dest);
        }
        return promotionDomainHashMap;
    }

    /**
     * 从字符串文本shop_menu中解析出Domain对象
     *
     * @param shop_menu
     * @return
     */
    private static DianpingshopMenuDomain getDianpingShopMenueDomain(String shop_menu) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {

        if (shop_menu == null || shop_menu.equals("")) return new DianpingshopMenuDomain();
        JSONObject object = JSON.parseObject(shop_menu);
        JSONObject recommendDishs = (JSONObject) object.get("推荐菜");
        DianpingshopMenuDomain dianpingshopMenuDomain = new DianpingshopMenuDomain();

        if (recommendDishs != null) {
            Map<String, DianpingMenuDomain> menuCollectDomains = new HashMap<>();
            Set<String> keys = recommendDishs.keySet();
            Class<?> aClass = Class.forName("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.DianpingMenuDomain");
            for (String key : keys) {//菜名称
                JSONObject object1 = (JSONObject) recommendDishs.get(key);
                Field[] declaredFields =
                        aClass.getDeclaredFields();
                Object dest = aClass.newInstance();
                for (Field declaredFiled : declaredFields) {
                    FieldMethodAnnotation annotation = declaredFiled.getAnnotation(FieldMethodAnnotation.class);
                    String reallyName = annotation.FieldReallyName();
                    Object obj = object1.get(reallyName);
                    String methodName = annotation.MethodName();
                    Method method = aClass.getMethod(methodName, annotation.ParameterType());
                    method.invoke(dest, obj);
                }
                menuCollectDomains.put(key, (DianpingMenuDomain) dest);
            }
            dianpingshopMenuDomain.setRecommenDishList(menuCollectDomains);
        }


        JSONArray arrays = (JSONArray) object.get("环境");
        if (arrays != null) {
            List<DianpingPriceOrEnvironmentDomain> dianpingPriceOrEnvironmentDomains = dealWithTheData(arrays);
            dianpingshopMenuDomain.setEnvironmentList(dianpingPriceOrEnvironmentDomains);
        }
        JSONArray arrayz = (JSONArray) object.get("价目表");
        if (arrayz != null) {
            List<DianpingPriceOrEnvironmentDomain> dianpingPriceOrEnvironmentDomains = dealWithTheData(arrayz);
            dianpingshopMenuDomain.setEnvironmentList(dianpingPriceOrEnvironmentDomains);
        }

        return dianpingshopMenuDomain;
    }


    private static List<DianpingPriceOrEnvironmentDomain> dealWithTheData(JSONArray arrays) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        List<DianpingPriceOrEnvironmentDomain> dianpingPriceOrEnvironmentDomains = new ArrayList<>();
        Class<?> aClass = Class.forName("com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.DianpingPriceOrEnvironmentDomain");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < arrays.size(); i++) {
            JSONObject obt = (JSONObject) arrays.get(i);
            Object dest = aClass.newInstance();
            for (Field declaredField : declaredFields) {
                FieldMethodAnnotation annotation = declaredField.getAnnotation(FieldMethodAnnotation.class);
                String methodName = annotation.MethodName();
                String reallyName = annotation.FieldReallyName();
                Object value = obt.get(reallyName);
                Method method = aClass.getMethod(methodName, annotation.ParameterType());
                method.invoke(dest, value);
            }
            dianpingPriceOrEnvironmentDomains.add((DianpingPriceOrEnvironmentDomain) dest);
        }
        return dianpingPriceOrEnvironmentDomains;
    }


    public static Map<String, String> getCateringCommentsRateTag(String comment_rate_tag) {
        Map<String, String> stringStringHashMap = new HashMap<>();
        Pattern compile = Pattern.compile("(\"(?<name>.+?)：(?<value>.+?)\")+?");
        Matcher matcher = compile.matcher(comment_rate_tag);
        while (matcher.find()) {
            stringStringHashMap.put(matcher.group("name"), matcher.group("value"));
        }
        return stringStringHashMap;
    }

}
