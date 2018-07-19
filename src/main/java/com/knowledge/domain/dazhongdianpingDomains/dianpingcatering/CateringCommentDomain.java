package com.knowledge.domain.dazhongdianpingDomains.dianpingcatering;

import com.knowledge.Annotations.FieldMethodAnnotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 点评餐饮的评论信息
 */
public class CateringCommentDomain {

    @FieldMethodAnnotation(FieldReallyName = "_id", MethodName = "set_id")
    private String _id;

    @FieldMethodAnnotation(FieldReallyName = "data_region",MethodName = "setData_region")
    private String data_region;

    @FieldMethodAnnotation(FieldReallyName = "shop_url", MethodName = "setShop_url")
    private String shop_url;

    @FieldMethodAnnotation(FieldReallyName = "comment_pic_list", MethodName = "setComment_pic_list", ParameterType = ArrayList.class)
    private List<String> comment_pic_list;

    @FieldMethodAnnotation(FieldReallyName = "comment_user_rate",MethodName = "setComment_user_rate")
    private String comment_user_rate;

    @FieldMethodAnnotation(FieldReallyName = "comment_content",MethodName = "setComment_content")
    private String comment_content;

    @FieldMethodAnnotation(FieldReallyName = "data_source", MethodName = "setData_source")
    private String data_source;

    @FieldMethodAnnotation(FieldReallyName = "comment_rate",MethodName = "setComment_rate")
    private String comment_rate;

    @FieldMethodAnnotation(FieldReallyName = "shop_name",MethodName = "setShop_name")
    private String shop_name;

    @FieldMethodAnnotation(FieldReallyName = "comment_time", MethodName = "setComment_time")
    private String comment_time;

    @FieldMethodAnnotation(FieldReallyName = "data_website",MethodName = "setData_website")
    private String data_website;

    @FieldMethodAnnotation(FieldReallyName = "comment_user_name",MethodName = "setComment_user_name")
    private String comment_user_name;

    @FieldMethodAnnotation(FieldReallyName = "crawl_time",MethodName = "setCrawl_time")
    private String crawl_time;

    @FieldMethodAnnotation(FieldReallyName = "comment_rate_tag",MethodName = "setComment_rate_tag")
    private String comment_rate_tag;

    public CateringCommentDomain() {
    }

    public CateringCommentDomain(String _id, String data_region, String shop_url, List<String> comment_pic_list, String comment_user_rate, String comment_content, String data_source, String comment_rate, String shop_name, String comment_time, String data_website, String comment_user_name, String crawl_time, String comment_rate_tag) {
        this._id = _id;
        this.data_region = data_region;
        this.shop_url = shop_url;
        this.comment_pic_list = comment_pic_list;
        this.comment_user_rate = comment_user_rate;
        this.comment_content = comment_content;
        this.data_source = data_source;
        this.comment_rate = comment_rate;
        this.shop_name = shop_name;
        this.comment_time = comment_time;
        this.data_website = data_website;
        this.comment_user_name = comment_user_name;
        this.crawl_time = crawl_time;
        this.comment_rate_tag = comment_rate_tag;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getData_region() {
        return data_region;
    }

    public void setData_region(String data_region) {
        this.data_region = data_region;
    }

    public String getShop_url() {
        return shop_url;
    }

    public void setShop_url(String shop_url) {
        this.shop_url = shop_url;
    }

    public List<String> getComment_pic_list() {
        return comment_pic_list;
    }

    public void setComment_pic_list(ArrayList<String> comment_pic_list) {
        this.comment_pic_list = comment_pic_list;
    }

    public String getComment_user_rate() {
        return comment_user_rate;
    }

    public void setComment_user_rate(String comment_user_rate) {
        this.comment_user_rate = comment_user_rate;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getComment_rate() {
        return comment_rate;
    }

    public void setComment_rate(String comment_rate) {
        this.comment_rate = comment_rate;
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

    public String getComment_user_name() {
        return comment_user_name;
    }

    public void setComment_user_name(String comment_user_name) {
        this.comment_user_name = comment_user_name;
    }

    public String getCrawl_time() {
        return crawl_time;
    }

    public void setCrawl_time(String crawl_time) {
        this.crawl_time = crawl_time;
    }

    public String getComment_rate_tag() {
        return comment_rate_tag;
    }

    public void setComment_rate_tag(String comment_rate_tag) {
        this.comment_rate_tag = comment_rate_tag;
    }

    @Override
    public String toString() {
        return "CateringCommentDomain{" +
                "_id='" + _id + '\'' +
                ", data_region='" + data_region + '\'' +
                ", shop_url='" + shop_url + '\'' +
                ", comment_pic_list=" + comment_pic_list +
                ", comment_user_rate='" + comment_user_rate + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", data_source='" + data_source + '\'' +
                ", comment_rate='" + comment_rate + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", comment_time='" + comment_time + '\'' +
                ", data_website='" + data_website + '\'' +
                ", comment_user_name='" + comment_user_name + '\'' +
                ", crawl_time='" + crawl_time + '\'' +
                ", comment_rate_tag='" + comment_rate_tag + '\'' +
                '}';
    }
}
