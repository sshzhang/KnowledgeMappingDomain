package com.knowledge.domain.QunaerDomains;

import com.alibaba.fastjson.annotation.JSONField;

public class QunaerHote_ShopStatisc_CommentNumListl {

    @JSONField(name = "全部")
    private String all;

    @JSONField(name = "好评")
    private String good;

    @JSONField(name = "中评")
    private String medium;

    @JSONField(name = "差评")
    private String terrible;

    public QunaerHote_ShopStatisc_CommentNumListl() {
    }

    public QunaerHote_ShopStatisc_CommentNumListl(String all, String good, String medium, String terrible) {
        this.all = all;
        this.good = good;
        this.medium = medium;
        this.terrible = terrible;
    }

    @Override
    public String toString() {
        return "QunaerHote_ShopStatisc_CommentNumListl{" +
                "all='" + all + '\'' +
                ", good='" + good + '\'' +
                ", medium='" + medium + '\'' +
                ", terrible='" + terrible + '\'' +
                '}';
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getTerrible() {
        return terrible;
    }

    public void setTerrible(String terrible) {
        this.terrible = terrible;
    }
}
