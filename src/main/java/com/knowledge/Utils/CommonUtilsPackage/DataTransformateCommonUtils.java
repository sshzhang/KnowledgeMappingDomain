package com.knowledge.Utils.CommonUtilsPackage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.knowledge.Annotations.FieldMethodAnnotation;
import com.knowledge.Utils.ConstructDataTypePackage.MyNode;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataTransformateCommonUtils {


    //每次运行完后记得释放 alllistNodes.clear()
    public  static final  List<List<String>> alllistNodes = new ArrayList<>();


    /**
     * 把 document 转换为 Domain
     *
     * @param className domain对应的类全名
     * @param document  文档结构
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
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


    public static List<String> StringConvertToList(String str) {

        List<String> datas= new ArrayList<>();
        if (str==null||"".equals(str)) return datas;
        String tempt = "";//默认为空
        int  flage = -1;//标志位表示 初始状态
        int count= 0;//统计给定字符出现次数
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '"') {//遇到给定字符
                if (count == 0) {//开始记录数据
                    flage = 0;
                } else if (count == 1) {
                    flage = 1;//表示统计完毕单个数据，可以重新下开始
                }
                count = (count+1) % 2;//count取值
            }

            if (flage == 0&&c!='"') {
                tempt += c + "";
            } else if (flage ==1) {
                datas.add(tempt);
                tempt = "";
                flage = -1;
            }
        }
        System.out.println(datas);
        return datas;

    }

    /**
     * 匹配出str中的数据
     * @param str 需要匹配的字符串
     * @param pattern 模式字符串
     * @return
     */
    public static String getTheNumberPriceFromstr(String str, String pattern) {

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher =
                compile.matcher(str);
        return matcher.find() ? matcher.group() : "";
    }


    /**
     * 查找 携程所有酒店的名称集合
     * @return
     */
    public  static  List<String> getAllXieChengHotelName() {

        List<String> hotels = new ArrayList<>();
        MongoClient remoteServiceClient = MongoDBConnectionUtils.getRemoteServiceClient();
        MongoCollection<Document> collection = remoteServiceClient.getDatabase("dspider2").getCollection("comments");
        MongoCursor<Document> iterator = collection.find(Filters.and(Filters.eq("data_website", "携程"), Filters.eq("data_source", "酒店"))).projection(Projections.include("Shope_name")).iterator();
        while (iterator.hasNext()) {
            Document document = iterator.next();
            hotels.add(document.get("Shope_name").toString());
        }
        remoteServiceClient.close();
        return hotels;
    }




    /**
     * 递归遍历 买票 结构树
     *
     *
     * 运行完此函数后 记得释放 alllistNodes.clear()
     *
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

        if(object==null) {
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
        }else{
            throw new RuntimeException("数据格式异常，出现未处理的类型信息"+object.getClass());
        }



    }

}
