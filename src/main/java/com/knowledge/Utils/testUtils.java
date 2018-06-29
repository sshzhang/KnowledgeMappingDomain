package com.knowledge.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.knowledge.domain.XieChengDomains.Sight.XieChengSightTicketSpecific;

import java.lang.reflect.Type;
import java.util.List;

public class testUtils {

    public static void main(String... args) {

        String str = "[{\"\": \"预订\", \"支付方式\": \"在线支付\", \"商家名称\": \"携程代理\", \"价格\": \"¥205\", \"优惠\": \"\", \"商品名称\": \"(旺季 官方自动取票机、快速取票)最早可订明日票有条件退\"}, {\"\": \"预订\", \"支付方式\": \"在线支付\", \"商家名称\": \"携程代理\", \"价格\": \"¥205\", \"优惠\": \"\", \"商品名称\": \"(旺季)最早可订明日票随时退\"}]";


        Type type = new TypeReference<List<XieChengSightTicketSpecific>>() {
        }.getType();

        List<XieChengSightTicketSpecific> object = JSON.parseObject(str, type);

        System.out.println(object);
    }
}
