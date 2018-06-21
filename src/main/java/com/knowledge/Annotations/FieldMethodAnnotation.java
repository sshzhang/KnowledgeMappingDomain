package com.knowledge.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( ElementType.FIELD )
//字段对应的方法注解
public @interface FieldMethodAnnotation {
    String FieldReallyName() default "";//字段的真实名称
    String MethodName();  //没有参数的成员
    //参数类型
    Class<?> ParameterType() default  String.class;
    //选取元素的个数 0:length:1 表示从第1个元素开始，长度
    String SelectElementsTypes() default "0:-1:1";

}
