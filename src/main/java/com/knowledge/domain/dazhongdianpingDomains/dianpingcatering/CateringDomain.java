package com.knowledge.domain.dazhongdianpingDomains.dianpingcatering;

import com.alibaba.fastjson.annotation.JSONField;
import com.knowledge.Annotations.FieldMethodAnnotation;

/**
 * 大众点评美食(餐饮)
 */
public class CateringDomain {

    @FieldMethodAnnotation(FieldReallyName = "_id",MethodName = "set_id")
    private String _id;

    @FieldMethodAnnotation(FieldReallyName = "shop_rate",MethodName = "setShop_rate")
    private String shop_rate;

    @FieldMethodAnnotation(FieldReallyName = "data_website",MethodName = "setData_website")
    private String data_website;

    @FieldMethodAnnotation(FieldReallyName = "data_source",MethodName = "setData_source")
    private String data_source;

    @FieldMethodAnnotation(FieldReallyName = "data_region",MethodName = "setData_region")
    private String data_region;

    @FieldMethodAnnotation(FieldReallyName = "shop_tag",MethodName = "setShop_tag")
    private String shop_tag;

    @FieldMethodAnnotation(FieldReallyName = "shop_comment_num",MethodName = "setShop_comment_num",ParameterType = int.class)
    private int shop_comment_num;

    @FieldMethodAnnotation(FieldReallyName = "shop_recommend_dish",MethodName = "setShop_recommend_dish")
    private String shop_recommend_dish;

    @FieldMethodAnnotation(FieldReallyName = "shop_url", MethodName = "setShop_url")
    private String shop_url;

    @FieldMethodAnnotation(FieldReallyName = "subtype_url",MethodName = "setSubtype_url")
    private String subtype_url;

    @FieldMethodAnnotation(FieldReallyName = "crawl_time", MethodName = "setCrawl_time")
    private String crawl_time;

    @FieldMethodAnnotation(FieldReallyName = "shop_price",MethodName = "setShop_price",ParameterType = float.class)
    private float shop_price;

    @FieldMethodAnnotation(FieldReallyName = "shop_name",MethodName = "setShop_name")
    private String shop_name;

    @FieldMethodAnnotation(FieldReallyName = "subtype_name",MethodName = "setSubtype_name")
    private String subtype_name;

    @FieldMethodAnnotation(FieldReallyName = "shop_phone",MethodName = "setShop_phone")
    private String shop_phone;

    @FieldMethodAnnotation(FieldReallyName = "shop_address",MethodName = "setShop_address")
    private String shop_address;

    @FieldMethodAnnotation(FieldReallyName = "shop_time",MethodName = "setShop_time")
    private String shop_time;

    @FieldMethodAnnotation(FieldReallyName = "shop_promotion",MethodName = "setShop_promotion")
    private String shop_promotion;

    @FieldMethodAnnotation(FieldReallyName = "shop_comment_url",MethodName = "setShop_comment_url")
    private String shop_comment_url;

    @FieldMethodAnnotation(FieldReallyName = "shop_statistics",MethodName = "setShop_statistics")
    private String shop_statistics;

    @FieldMethodAnnotation(FieldReallyName = "shop_menu",MethodName = "setShop_menu")
    private String shop_menu;


    public CateringDomain() {
    }

    public CateringDomain(String _id, String shop_rate, String data_website, String data_source, String data_region, String shop_tag, int shop_comment_num, String shop_recommend_dish, String shop_url, String subtype_url, String crawl_time, float shop_price, String shop_name, String subtype_name, String shop_phone, String shop_address, String shop_time, String shop_promotion, String shop_comment_url, String shop_statistics, String shop_menu) {
        this._id = _id;
        this.shop_rate = shop_rate;
        this.data_website = data_website;
        this.data_source = data_source;
        this.data_region = data_region;
        this.shop_tag = shop_tag;
        this.shop_comment_num = shop_comment_num;
        this.shop_recommend_dish = shop_recommend_dish;
        this.shop_url = shop_url;
        this.subtype_url = subtype_url;
        this.crawl_time = crawl_time;
        this.shop_price = shop_price;
        this.shop_name = shop_name;
        this.subtype_name = subtype_name;
        this.shop_phone = shop_phone;
        this.shop_address = shop_address;
        this.shop_time = shop_time;
        this.shop_promotion = shop_promotion;
        this.shop_comment_url = shop_comment_url;
        this.shop_statistics = shop_statistics;
        this.shop_menu = shop_menu;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getShop_rate() {
        return shop_rate;
    }

    public void setShop_rate(String shop_rate) {
        this.shop_rate = shop_rate;
    }

    public String getData_website() {
        return data_website;
    }

    public void setData_website(String data_website) {
        this.data_website = data_website;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getData_region() {
        return data_region;
    }

    public void setData_region(String data_region) {
        this.data_region = data_region;
    }

    public String getShop_tag() {
        return shop_tag;
    }

    public void setShop_tag(String shop_tag) {
        this.shop_tag = shop_tag;
    }

    public int getShop_comment_num() {
        return shop_comment_num;
    }

    public void setShop_comment_num(int shop_comment_num) {
        this.shop_comment_num = shop_comment_num;
    }

    public String getShop_recommend_dish() {
        return shop_recommend_dish;
    }

    public void setShop_recommend_dish(String shop_recommend_dish) {
        this.shop_recommend_dish = shop_recommend_dish;
    }

    public String getShop_url() {
        return shop_url;
    }

    public void setShop_url(String shop_url) {
        this.shop_url = shop_url;
    }

    public String getSubtype_url() {
        return subtype_url;
    }

    public void setSubtype_url(String subtype_url) {
        this.subtype_url = subtype_url;
    }

    public String getCrawl_time() {
        return crawl_time;
    }

    public void setCrawl_time(String crawl_time) {
        this.crawl_time = crawl_time;
    }

    public float getShop_price() {
        return shop_price;
    }

    public void setShop_price(float shop_price) {
        this.shop_price = shop_price;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getSubtype_name() {

        return subtype_name.replace("/", "");
    }

    public void setSubtype_name(String subtype_name) {
        this.subtype_name = subtype_name;
    }

    public String getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_time() {
        return shop_time;
    }

    public void setShop_time(String shop_time) {
        this.shop_time = shop_time;
    }

    public String getShop_promotion() {
        return shop_promotion;
    }

    public void setShop_promotion(String shop_promotion) {
        this.shop_promotion = shop_promotion;
    }

    public String getShop_comment_url() {
        return shop_comment_url;
    }

    public void setShop_comment_url(String shop_comment_url) {
        this.shop_comment_url = shop_comment_url;
    }

    public String getShop_statistics() {
        return shop_statistics;
    }

    public void setShop_statistics(String shop_statistics) {
        this.shop_statistics = shop_statistics;
    }

    public String getShop_menu() {
        return shop_menu;
    }

    public void setShop_menu(String shop_menu) {
        this.shop_menu = shop_menu;
    }
}
