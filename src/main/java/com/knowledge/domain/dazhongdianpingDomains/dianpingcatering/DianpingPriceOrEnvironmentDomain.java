package com.knowledge.domain.dazhongdianpingDomains.dianpingcatering;

import com.knowledge.Annotations.FieldMethodAnnotation;

/**
 * 点评中的价目表和环境
 */
public class DianpingPriceOrEnvironmentDomain {

    @FieldMethodAnnotation(FieldReallyName = "图片", MethodName = "setPicture")
    private String picture;

    @FieldMethodAnnotation(FieldReallyName = "标题", MethodName = "setTitle")
    private String title;

    @FieldMethodAnnotation(FieldReallyName = "链接", MethodName = "setUrl")
    private String url;

    public DianpingPriceOrEnvironmentDomain() {
    }

    public DianpingPriceOrEnvironmentDomain(String picture, String title, String url) {
        this.picture = picture;
        this.title = title;
        this.url = url;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture= picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
