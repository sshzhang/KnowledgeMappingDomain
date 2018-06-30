package com.knowledge.domain.XieChengDomains.Sight;

import com.alibaba.fastjson.annotation.JSONField;
import com.knowledge.Annotations.FieldMethodAnnotation;

/**
 * 携程千岛湖景点评论数据结构
 */
public class XieChengSightComments {


    @FieldMethodAnnotation(FieldReallyName = "_id", MethodName = "set_id")
    private String _id;

    @FieldMethodAnnotation(FieldReallyName = "comment_content", MethodName = "setComment_content")
    private String comment_content;

    @FieldMethodAnnotation(FieldReallyName = "data_region", MethodName = "setData_region")
    private String data_region;

    @FieldMethodAnnotation(FieldReallyName = "data_source", MethodName = "setData_source")
    private String data_source;

    @FieldMethodAnnotation(FieldReallyName = "crawl_time", MethodName = "setCrawl_time")
    private String crawl_time;

    @FieldMethodAnnotation(FieldReallyName = "shop_name", MethodName = "setShop_name")
    private String shop_name;

    @FieldMethodAnnotation(FieldReallyName = "comment_time", MethodName = "setComment_time")
    private String comment_time;

    @FieldMethodAnnotation(FieldReallyName = "data_website",MethodName = "setData_website")
    private String data_website;

    @FieldMethodAnnotation(FieldReallyName = "comment_score",MethodName = "setComment_score",ParameterType = float.class)
    private float comment_score;

    @FieldMethodAnnotation(FieldReallyName = "comment_user_name",MethodName = "setComment_user_name")
    private String comment_user_name;


    public XieChengSightComments() {
    }

    public XieChengSightComments(String _id, String comment_content, String data_region, String data_source, String crawl_time, String shop_name, String comment_time, String data_website, float comment_score, String comment_user_name) {
        this._id = _id;
        this.comment_content = comment_content;
        this.data_region = data_region;
        this.data_source = data_source;
        this.crawl_time = crawl_time;
        this.shop_name = shop_name;
        this.comment_time = comment_time;
        this.data_website = data_website;
        this.comment_score = comment_score;
        this.comment_user_name = comment_user_name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getData_region() {
        return data_region;
    }

    public void setData_region(String data_region) {
        this.data_region = data_region;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getCrawl_time() {
        return crawl_time;
    }

    public void setCrawl_time(String crawl_time) {
        this.crawl_time = crawl_time;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getData_website() {
        return data_website;
    }

    public void setData_website(String data_website) {
        this.data_website = data_website;
    }

    public float getComment_score() {
        return comment_score;
    }

    public void setComment_score(float comment_score) {
        this.comment_score = comment_score;
    }

    public String getComment_user_name() {
        return comment_user_name;
    }

    public void setComment_user_name(String comment_user_name) {
        this.comment_user_name = comment_user_name;
    }


    @Override
    public String toString() {
        return "XieChengSightComments{" +
                "_id='" + _id + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", data_region='" + data_region + '\'' +
                ", data_source='" + data_source + '\'' +
                ", crawl_time='" + crawl_time + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", comment_time='" + comment_time + '\'' +
                ", data_website='" + data_website + '\'' +
                ", comment_score=" + comment_score +
                ", comment_user_name='" + comment_user_name + '\'' +
                '}';
    }
}
