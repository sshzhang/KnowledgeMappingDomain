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
import com.knowledge.domain.XieChengDomains.Sight.XieChengSightComments;
import com.knowledge.domain.XieChengDomains.Sight.XieChengSightDomain;
import com.knowledge.domain.XieChengSightApplicationDomain;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class XieChengSightUtils {

    private static  ExecutorService executorService = Executors.newFixedThreadPool(10);

    //每次运行完后记得释放 alllistNodes.clear()  每次迭代的树的遍历值
    public  static final  List<List<String>> alllistNodes = new ArrayList<>();

    /**
     * 查找 携程所有酒店的名称集合
     * @return
     */
    public  static List<String> getAllXieChengHotelName() {

        List<String> hotels = new ArrayList<>();
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("shops");
        //后期合并所有  此处查找所有酒店名称
        MongoCursor<Document> iterator = collection.find(Filters.eq("data_source", "酒店")).projection(Projections.include("shop_name")).iterator();
        while (iterator.hasNext()) {
            Document document = iterator.next();
            hotels.add(document.get("shop_name").toString());
        }
        remoteServiceClient.close();
        return hotels;
    }




    /**
     * 递归遍历 买票 结构树
     *
     * 运行完此函数后 记得释放 alllistNodes.clear()
     * @param root   树节点
     */
    public static void TraversalTheTree(MyNode<String> root) {
        if (root == null) return;
        if (root.isIsend()) {
            System.out.println("***************");
            //获取 逆序的买票结构
            alllistNodes.add(CreateTheInverseListAllNodes(root));
            System.out.println("***************");
        }
        List<MyNode> childMyNodes = root.getChildMyNodes();
        if(childMyNodes!=null)
            for (MyNode<String> childrenMyNode : childMyNodes) {
                TraversalTheTree(childrenMyNode);
            }else{

            TraversalTheTree(null);
        }
    }

    //逆序
    private static List<String> CreateTheInverseListAllNodes(MyNode<String> root) {
        List<String> listNode = new ArrayList<String>();
        listNode.add(root.getMyNodeEntity());
        MyNode<String> parentMyNode = root.getParentMyNode();
        while (parentMyNode != null) {
            listNode.add(parentMyNode.getMyNodeEntity());
            parentMyNode = parentMyNode.getParentMyNode();
        }
        return listNode;
    }



    /**
     * 递归构建 买票结构树
     * @param root
     * @param object
     */
    public   static void CreateTheTree(MyNode root, Object object) {

        if (object == null) {
            return;
        }
        if (object.getClass().isAssignableFrom(JSONArray.class)) {

            JSONArray objects = (JSONArray) object;
            for (int i = 0; i < objects.size(); i++) {
                Object o = objects.get(i);
                CreateTheTree(root, o);
            }

        } else if (object.getClass().isAssignableFrom(JSONObject.class)) {

            JSONObject obj = (JSONObject) object;
            String name = obj.get("@name").toString();
            MyNode newNode = new MyNode<String>(name);
            root.addChildMyNode(newNode);
            newNode.setParentMyNode(root);
            Set<String> strings = obj.keySet();
            if (strings.contains("content")) {
                MyNode endNode = new MyNode(obj.get("content"));
                newNode.addChildMyNode(endNode);
                endNode.setParentMyNode(newNode);
                endNode.setIsend(true);
            } else {
                CreateTheTree(newNode, obj.get("title"));
            }
        } else {
            throw new RuntimeException("数据格式异常，出现未处理的类型信息" + object.getClass());
        }
    }


    /** 把携程景点数据写入到图数据库
     *
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     */
        private static void WirteTheXieChengSightToNeo4j() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException {

            MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
            MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("shops");
            MongoCursor<Document> iterator =
                    collection.find(Filters.and(Filters.eq("data_source", "景点"), Filters.eq("data_website", "携程"))).iterator();
            while (iterator.hasNext()) {
                Document document = iterator.next();
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
                    if (ticket == null) continue;
                    JSONArray arrays = (JSONArray) ticket.get("title");
                    System.out.println(arrays);
                    MyNode<String> root = new MyNode<>("门票");
                    root.setIsstart(true);
                    if (arrays != null) CreateTheTree(root, arrays);
                    xieChengSightCombinationTicket.setRoot(root);
                    System.out.println("-------------");
                    TraversalTheTree(root);

                }
                //XieChengSightNeo4jUitls neo4jUtils = new XieChengSightNeo4jUitls(null);
                //neo4jUtils.CreateXieChengSightDataToNeo4jNode(xieChengSightDomain, xieChengSightCombinationInfos, xieChengSightCombinationTicket);
                executorService.submit(new XieChengSightNeo4jUitls(null, new XieChengSightApplicationDomain(xieChengSightDomain, xieChengSightCombinationInfos, xieChengSightCombinationTicket), 1));
                //清除之前数据
               alllistNodes.clear();
            }
            executorService.shutdown();
            try {
                if(!executorService.awaitTermination(20, TimeUnit.MINUTES))
                    executorService.shutdownNow();
            } catch (InterruptedException e) {
                e.printStackTrace();
                executorService.shutdownNow();
            }
            remoteServiceClient.close();
    }


    public static void main(String... args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, InterruptedException {

        WirteTheXieChengSightToNeo4j();
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("comments");
        MongoCursor<Document> iterator =
                collection.find(Filters.and(Filters.eq("data_source", "景点"), Filters.eq("data_website", "携程"))).noCursorTimeout(true).iterator();
        int count = 0;
        executorService = Executors.newFixedThreadPool(10);
        while (iterator.hasNext()) {
            Document document = iterator.next();
            XieChengSightComments sightComments = (XieChengSightComments) DataTransformateCommonUtils.DocumentConvextToModel("com.knowledge.domain.XieChengDomains.Sight.XieChengSightComments", document);
            System.out.println(sightComments);
            executorService.submit(new XieChengSightNeo4jUitls(sightComments, null, 0));
            System.out.println("------------end---" + ++count + "-----------");
        }
        System.out.println("------------end1---" + count + "-----------");
        System.out.println("以及调用shutdown");
        executorService.shutdown();
        System.out.println("以及调用shutdown");
        //所有任务都结束的时候，返回true
        if (!executorService.awaitTermination(50, TimeUnit.MINUTES)) {
            executorService.shutdownNow();
        }
        System.out.println("关闭");
        remoteServiceClient.close();
    }
}
