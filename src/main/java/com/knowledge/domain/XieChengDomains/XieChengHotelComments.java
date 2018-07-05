package com.knowledge.domain.XieChengDomains;

import com.knowledge.Annotations.FieldMethodAnnotation;

/**
 * 携程评论数据格式
 */
public class XieChengHotelComments {

    @FieldMethodAnnotation(FieldReallyName = "_id", MethodName = "set_id")
    private String _id;

    @FieldMethodAnnotation(FieldReallyName = "shop_name", MethodName = "setShop_name")
    private String shop_name;

    @FieldMethodAnnotation(FieldReallyName = "data_region", MethodName = "setData_region")
    private String data_region;

    @FieldMethodAnnotation(FieldReallyName = "comment_user_num", MethodName = "setComment_user_num")
    private String comment_user_num;

    @FieldMethodAnnotation(FieldReallyName = "comment_content", MethodName = "setComment_content")
    private String comment_content;

    @FieldMethodAnnotation(FieldReallyName = "data_website", MethodName = "setData_website")
    private String data_website;

    @FieldMethodAnnotation(FieldReallyName = "crawl_time", MethodName = "setCrawl_time")
    private String crawl_time;

    @FieldMethodAnnotation(FieldReallyName = "comment_time", MethodName = "setComment_time")
    private String comment_time;

    @FieldMethodAnnotation(FieldReallyName = "comment_pic_list", MethodName = "setComment_pic_list")
    private String comment_pic_list;

    @FieldMethodAnnotation(FieldReallyName = "comment_score_text", MethodName = "setComment_score_text")
    private String comment_score_text;

    @FieldMethodAnnotation(FieldReallyName = "comment_type", MethodName = "setComment_type")
    private String comment_type;

    @FieldMethodAnnotation(FieldReallyName = "comment_replay", MethodName = "setComment_replay")
    private String comment_replay;

    @FieldMethodAnnotation(FieldReallyName = "comment_score", MethodName = "setComment_score",ParameterType = float.class)
    private float comment_score;

    @FieldMethodAnnotation(FieldReallyName = "comment_user_name", MethodName = "setComment_user_name")
    private String comment_user_name;

    @FieldMethodAnnotation(FieldReallyName = "comment_user_img", MethodName = "setComment_user_img")
    private String comment_user_img;

    @FieldMethodAnnotation(FieldReallyName = "comment_user_check_in", MethodName = "setComment_user_check_in")
    private String comment_user_check_in;

    @FieldMethodAnnotation(FieldReallyName = "data_source", MethodName = "setData_source")
    private String data_source;

    @FieldMethodAnnotation(FieldReallyName = "comment_user_room", MethodName = "setComment_user_room")
    private String comment_user_room;


    public XieChengHotelComments() {
    }

    public XieChengHotelComments(String _id, String shop_name, String data_region, String comment_user_num, String comment_content, String data_website, String crawl_time, String comment_time, String comment_pic_list, String comment_score_text, String comment_type, String comment_replay, float comment_score, String comment_user_name, String comment_user_img, String comment_user_check_in, String data_source, String comment_user_room) {
        this._id = _id;
        this.shop_name = shop_name;
        this.data_region = data_region;
        this.comment_user_num = comment_user_num;
        this.comment_content = comment_content;
        this.data_website = data_website;
        this.crawl_time = crawl_time;
        this.comment_time = comment_time;
        this.comment_pic_list = comment_pic_list;
        this.comment_score_text = comment_score_text;
        this.comment_type = comment_type;
        this.comment_replay = comment_replay;
        this.comment_score = comment_score;
        this.comment_user_name = comment_user_name;
        this.comment_user_img = comment_user_img;
        this.comment_user_check_in = comment_user_check_in;
        this.data_source = data_source;
        this.comment_user_room = comment_user_room;
    }

    @Override
    public String toString() {
        return "XieChengHotelComments{" +
                "_id='" + _id + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", data_region='" + data_region + '\'' +
                ", comment_user_num='" + comment_user_num + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", data_website='" + data_website + '\'' +
                ", crawl_time='" + crawl_time + '\'' +
                ", comment_time='" + comment_time + '\'' +
                ", comment_pic_list='" + comment_pic_list + '\'' +
                ", comment_score_text='" + comment_score_text + '\'' +
                ", comment_type='" + comment_type + '\'' +
                ", comment_replay='" + comment_replay + '\'' +
                ", comment_score=" + comment_score +
                ", comment_user_name='" + comment_user_name + '\'' +
                ", comment_user_img='" + comment_user_img + '\'' +
                ", comment_user_check_in='" + comment_user_check_in + '\'' +
                ", data_source='" + data_source + '\'' +
                ", comment_user_room='" + comment_user_room + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getData_region() {
        return data_region;
    }

    public void setData_region(String data_region) {
        this.data_region = data_region;
    }

    public String getComment_user_num() {
        return comment_user_num;
    }

    public void setComment_user_num(String comment_user_num) {
        this.comment_user_num = comment_user_num;
    }

    public String getComment_content() {
        return  comment_content.replace("\"", "'");
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getData_website() {
        return data_website;
    }

    public void setData_website(String data_website) {
        this.data_website = data_website;
    }

    public String getCrawl_time() {
        return crawl_time;
    }

    public void setCrawl_time(String crawl_time) {
        this.crawl_time = crawl_time;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_pic_list() {
        return comment_pic_list;
    }

    public void setComment_pic_list(String comment_pic_list) {
        this.comment_pic_list = comment_pic_list;
    }

    public String getComment_score_text() {
        return comment_score_text;
    }

    public void setComment_score_text(String comment_score_text) {
        this.comment_score_text = comment_score_text;
    }

    public String getComment_type() {
        return comment_type;
    }

    public void setComment_type(String comment_type) {
        this.comment_type = comment_type;
    }

    public String getComment_replay() {
        return comment_replay;
    }

    public void setComment_replay(String comment_replay) {
        this.comment_replay = comment_replay;
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

    public String getComment_user_img() {
        return comment_user_img;
    }

    public void setComment_user_img(String comment_user_img) {
        this.comment_user_img = comment_user_img;
    }

    public String getComment_user_check_in() {
        return comment_user_check_in;
    }

    public void setComment_user_check_in(String comment_user_check_in) {
        this.comment_user_check_in = comment_user_check_in;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getComment_user_room() {
        return comment_user_room;
    }

    public void setComment_user_room(String comment_user_room) {
        this.comment_user_room = comment_user_room;
    }
}
