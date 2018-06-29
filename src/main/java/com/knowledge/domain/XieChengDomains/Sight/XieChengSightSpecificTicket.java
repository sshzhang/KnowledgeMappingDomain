package com.knowledge.domain.XieChengDomains.Sight;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 携程单个门票
 * <p>
 * <p>
 * "content": "[{\"\": \"预订\", \"支付方式\": \"在线支付\", \"商家名称\": \"携程自营\", \"商品名称\": \"标准价可订今日票出票1小时后可用有条件退无需取票\", \"优惠\": \"\", \"价格\": \"¥260\"}]",
 * "@name": "{\"参考门市价\": \"¥325\", \"名称\": \"成人票+九龙溪漂流\", \"价格\": \"¥260起\"}"
 */
public class XieChengSightSpecificTicket {

    @JSONField(name = "@name")
    private String name;

    @JSONField(name = "content")
    private String content;


    public XieChengSightSpecificTicket() {
    }

    public XieChengSightSpecificTicket(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "XieChengSightSpecificTicket{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

