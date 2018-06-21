package com.knowledge.domain.QunaerDomains;

import com.knowledge.Annotations.FieldMethodAnnotation;

public class QunaerHotel {

    @FieldMethodAnnotation(FieldReallyName = "_id",MethodName = "set_id")
    private String _id;

    @FieldMethodAnnotation(FieldReallyName = "shop_room_recommend_all",MethodName = "setShop_room_recommend_all")
    private String shop_room_recommend_all;

    @FieldMethodAnnotation(FieldReallyName = "shop_name",MethodName = "setShop_name")
    private String shop_name;

    @FieldMethodAnnotation(FieldReallyName = "shop_grade",MethodName = "setShop_grade",ParameterType = float.class)
    private float shop_grade;

    @FieldMethodAnnotation(FieldReallyName = "crawl_time",MethodName = "setCrawl_time")
    private String crawl_time;

    @FieldMethodAnnotation(FieldReallyName = "data_website",MethodName = "setData_website")
    private String data_website;

    @FieldMethodAnnotation(FieldReallyName = "shop_traffic",MethodName = "setShop_traffic")
    private String shop_traffic;

    @FieldMethodAnnotation(FieldReallyName = "shop_curr_url",MethodName = "setShop_curr_url")
    private String shop_curr_url;

    @FieldMethodAnnotation(FieldReallyName = "shop_facilities",MethodName = "setShop_facilities")
    private String shop_facilities;

    @FieldMethodAnnotation(FieldReallyName = "shop_comment_num",MethodName = "setShop_comment_num",ParameterType = int.class)
    private int shop_comment_num;

    @FieldMethodAnnotation(FieldReallyName = "shop_rate",MethodName = "setShop_rate")
    private String shop_rate;

    @FieldMethodAnnotation(FieldReallyName = "shop_statistics", MethodName = "setShop_statistics")
    private String shop_statistics;

    @FieldMethodAnnotation(FieldReallyName = "data_source", MethodName = "setData_source")
    private String data_source;

    @FieldMethodAnnotation(FieldReallyName = "data_region",MethodName = "setData_region")
    private String data_region;

    @FieldMethodAnnotation(FieldReallyName = "shop_address",MethodName = "setShop_address")
    private String shop_address;

    @FieldMethodAnnotation(FieldReallyName = "shop_price",MethodName ="setShop_price",ParameterType = float.class)
    private float shop_price;

    public QunaerHotel() {
    }

    public QunaerHotel(String _id, String shop_room_recommend_all, String shop_name, float shop_grade, String crawl_time, String data_website, String shop_traffic, String shop_curr_url, String shop_facilities, int shop_comment_num, String shop_rate, String shop_statistics, String data_source, String data_region, String shop_address, float shop_price) {
        this._id = _id;
        this.shop_room_recommend_all = shop_room_recommend_all;
        this.shop_name = shop_name;
        this.shop_grade = shop_grade;
        this.crawl_time = crawl_time;
        this.data_website = data_website;
        this.shop_traffic = shop_traffic;
        this.shop_curr_url = shop_curr_url;
        this.shop_facilities = shop_facilities;
        this.shop_comment_num = shop_comment_num;
        this.shop_rate = shop_rate;
        this.shop_statistics = shop_statistics;
        this.data_source = data_source;
        this.data_region = data_region;
        this.shop_address = shop_address;
        this.shop_price = shop_price;
    }

    @Override
    public String toString() {
        return "QunaerHotel{" +
                "_id='" + _id + '\'' +
                ", shop_room_recommend_all='" + shop_room_recommend_all + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_grade=" + shop_grade +
                ", crawl_time='" + crawl_time + '\'' +
                ", data_website='" + data_website + '\'' +
                ", shop_traffic='" + shop_traffic + '\'' +
                ", shop_curr_url='" + shop_curr_url + '\'' +
                ", shop_facilities='" + shop_facilities + '\'' +
                ", shop_comment_num=" + shop_comment_num +
                ", shop_rate='" + shop_rate + '\'' +
                ", shop_statistics='" + shop_statistics + '\'' +
                ", data_source='" + data_source + '\'' +
                ", data_region='" + data_region + '\'' +
                ", shop_address='" + shop_address + '\'' +
                ", shop_price=" + shop_price +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getShop_room_recommend_all() {
        return shop_room_recommend_all;
    }

    public void setShop_room_recommend_all(String shop_room_recommend_all) {
        this.shop_room_recommend_all = shop_room_recommend_all;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public float getShop_grade() {
        return shop_grade;
    }

    public void setShop_grade(float shop_grade) {
        this.shop_grade = shop_grade;
    }

    public String getCrawl_time() {
        return crawl_time;
    }

    public void setCrawl_time(String crawl_time) {
        this.crawl_time = crawl_time;
    }

    public String getData_website() {
        return data_website;
    }

    public void setData_website(String data_website) {
        this.data_website = data_website;
    }

    public String getShop_traffic() {
        return shop_traffic;
    }

    public void setShop_traffic(String shop_traffic) {
        this.shop_traffic = shop_traffic;
    }

    public String getShop_curr_url() {
        return shop_curr_url;
    }

    public void setShop_curr_url(String shop_curr_url) {
        this.shop_curr_url = shop_curr_url;
    }

    public String getShop_facilities() {
        return shop_facilities;
    }

    public void setShop_facilities(String shop_facilities) {
        this.shop_facilities = shop_facilities;
    }

    public int getShop_comment_num() {
        return shop_comment_num;
    }

    public void setShop_comment_num(int shop_comment_num) {
        this.shop_comment_num = shop_comment_num;
    }

    public String getShop_rate() {
        return shop_rate;
    }

    public void setShop_rate(String shop_rate) {
        this.shop_rate = shop_rate;
    }

    public String getShop_statistics() {
        return shop_statistics;
    }

    public void setShop_statistics(String shop_statistics) {
        this.shop_statistics = shop_statistics;
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

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public float getShop_price() {
        return shop_price;
    }

    public void setShop_price(float shop_price) {
        this.shop_price = shop_price;
    }
}
