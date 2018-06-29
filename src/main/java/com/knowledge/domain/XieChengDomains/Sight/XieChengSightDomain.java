package com.knowledge.domain.XieChengDomains.Sight;

import com.knowledge.Annotations.FieldMethodAnnotation;

/**
 * 携程景点 JavaBean
 */
public class XieChengSightDomain {


    @FieldMethodAnnotation(FieldReallyName ="_id",MethodName = "set_id")
    private String _id;

    @FieldMethodAnnotation(FieldReallyName = "shop_comment_num", MethodName = "setShop_comment_num", ParameterType = int.class)
    private int shop_comment_num;

    @FieldMethodAnnotation(FieldReallyName = "data_website", MethodName = "setData_website")
    private String data_website;

    @FieldMethodAnnotation(FieldReallyName = "shop_url",MethodName = "setShop_url")
    private String shop_url;

    @FieldMethodAnnotation(FieldReallyName = "crawl_time", MethodName = "setCrawl_time")
    private String crawl_time;

    @FieldMethodAnnotation(FieldReallyName = "shop_feature",MethodName = "setShop_feature")
    private String shop_feature;

    @FieldMethodAnnotation(FieldReallyName = "shop_address",MethodName = "setShop_address")
    private String shop_address;

    @FieldMethodAnnotation(FieldReallyName = "data_source",MethodName = "setData_source")
    private String data_source;

    @FieldMethodAnnotation(FieldReallyName = "data_region",MethodName = "setData_region")
    private String data_region;

    @FieldMethodAnnotation(FieldReallyName = "shop_grade",MethodName = "setShop_grade",ParameterType = float.class)
    private float shop_grade;

    @FieldMethodAnnotation(FieldReallyName = "shop_name",MethodName = "setShop_name")
    private String shop_name;

    @FieldMethodAnnotation(FieldReallyName = "shop_img",MethodName = "setShop_img")
    private String shop_img;

    @FieldMethodAnnotation(FieldReallyName = "shop_service",MethodName ="setShop_service")
    private String shop_service;

    @FieldMethodAnnotation(FieldReallyName = "shop_time",MethodName = "setShop_time")
    private String shop_time;

    @FieldMethodAnnotation(FieldReallyName = "shop_price",MethodName = "setShop_price",ParameterType = float.class)
    private float shop_price;

    @FieldMethodAnnotation(FieldReallyName = "shop_ticket",MethodName = "setShop_ticket")
    private String shop_ticket;

    @FieldMethodAnnotation(FieldReallyName = "shop_info",MethodName = "setShop_info")
    private String shop_info;

    @FieldMethodAnnotation(FieldReallyName = "shop_rate",MethodName = "setShop_rate")
    private String shop_rate;


    public XieChengSightDomain() {
    }

    public XieChengSightDomain(String _id, int shop_comment_num, String data_website,String shop_url, String crawl_time, String shop_feature, String shop_address, String data_source, String data_region, float shop_grade, String shop_name, String shop_img, String shop_service, String shop_time, float shop_price, String shop_ticket, String shop_info, String shop_rate) {
        this._id = _id;
        this.shop_comment_num = shop_comment_num;
        this.data_website = data_website;
        this.shop_url = shop_url;
        this.crawl_time = crawl_time;
        this.shop_feature = shop_feature;
        this.shop_address = shop_address;
        this.data_source = data_source;
        this.data_region = data_region;
        this.shop_grade = shop_grade;
        this.shop_name = shop_name;
        this.shop_img = shop_img;
        this.shop_service = shop_service;
        this.shop_time = shop_time;
        this.shop_price = shop_price;
        this.shop_ticket = shop_ticket;
        this.shop_info = shop_info;
        this.shop_rate = shop_rate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getShop_comment_num() {
        return shop_comment_num;
    }

    public void setShop_comment_num(int shop_comment_num) {
        this.shop_comment_num = shop_comment_num;
    }

    public String getData_website() {
        return data_website;
    }

    public void setData_website(String data_website) {
        this.data_website = data_website;
    }

    public String getShop_url() {
        return shop_url;
    }

    public void setShop_url(String shop_url) {
        this.shop_url = shop_url;
    }

    public String getCrawl_time() {
        return crawl_time;
    }

    public void setCrawl_time(String crawl_time) {
        this.crawl_time = crawl_time;
    }

    public String getShop_feature() {
        return shop_feature;
    }

    public void setShop_feature(String shop_feature) {
        this.shop_feature = shop_feature;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
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

    public float getShop_grade() {
        return shop_grade;
    }

    public void setShop_grade(float shop_grade) {
        this.shop_grade = shop_grade;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    public String getShop_service() {
        return shop_service;
    }

    public void setShop_service(String shop_service) {
        this.shop_service = shop_service;
    }

    public String getShop_time() {
        return shop_time;
    }

    public void setShop_time(String shop_time) {
        this.shop_time = shop_time;
    }

    public float getShop_price() {
        return shop_price;
    }

    public void setShop_price(float shop_price) {
        this.shop_price = shop_price;
    }

    public String getShop_ticket() {
        return shop_ticket;
    }

    public void setShop_ticket(String shop_ticket) {
        this.shop_ticket = shop_ticket;
    }

    public String getShop_info() {
        return shop_info;
    }

    public void setShop_info(String shop_info) {
        this.shop_info = shop_info;
    }

    public String getShop_rate() {
        return shop_rate;
    }

    public void setShop_rate(String shop_rate) {
        this.shop_rate = shop_rate;
    }

    @Override
    public String toString() {
        return "XieChengSightDomain{" +
                "_id='" + _id + '\'' +
                ", shop_comment_num=" + shop_comment_num +
                ", shop_url='" + shop_url + '\'' +
                ", crawl_time='" + crawl_time + '\'' +
                ", shop_feature='" + shop_feature + '\'' +
                ", shop_address='" + shop_address + '\'' +
                ", data_source='" + data_source + '\'' +
                ", data_region='" + data_region + '\'' +
                ", shop_grade=" + shop_grade +
                ", shop_name='" + shop_name + '\'' +
                ", shop_img='" + shop_img + '\'' +
                ", shop_service='" + shop_service + '\'' +
                ", shop_time='" + shop_time + '\'' +
                ", shop_price=" + shop_price +
                ", shop_ticket='" + shop_ticket + '\'' +
                ", shop_info='" + shop_info + '\'' +
                ", shop_rate='" + shop_rate + '\'' +
                '}';
    }
}
