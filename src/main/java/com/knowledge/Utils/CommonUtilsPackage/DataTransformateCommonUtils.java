package com.knowledge.Utils.CommonUtilsPackage;

import com.knowledge.Annotations.FieldMethodAnnotation;
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
            //处理为null的特殊情况
            Object value = document.get(strKey);
            if (String.class.isAssignableFrom(classType)) {
                method.invoke(dest, value == null ? "" : value.toString());
            } else if (int.class.isAssignableFrom(classType)) {
                method.invoke(dest, Integer.parseInt(value == null ? "0" : value.toString()));
            } else if (float.class.isAssignableFrom(classType)) {
                method.invoke(dest, Float.parseFloat(value == null ? "0.0" : value.toString()));
            } else if (ArrayList.class.isAssignableFrom(classType)) {
                method.invoke(dest, value);
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
}
