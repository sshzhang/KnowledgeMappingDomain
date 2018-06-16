package com.knowledge.domain.XieChengDomains;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class XieChengShopStatistics {


    @JSONField(name = "印象")
    private List<String> impressions;

    @JSONField(name = "点评")
    private List<String> reviews;

    @JSONField(name = "评论好评统计")
    private List<String> goodReviewCounts;

    @JSONField(name = "评论房型统计")
    private List<String> homeTypeReviewCounts;


    public XieChengShopStatistics() {
    }

    @Override
    public String toString() {
        return "XieChengShopStatistics{" +
                "impressions=" + impressions +
                ", reviews=" + reviews +
                ", goodReviewCounts=" + goodReviewCounts +
                ", homeTypeReviewCounts=" + homeTypeReviewCounts +
                '}';
    }

    public XieChengShopStatistics(List<String> impressions, List<String> reviews, List<String> goodReviewCounts, List<String> homeTypeReviewCounts) {
        this.impressions = impressions;
        this.reviews = reviews;
        this.goodReviewCounts = goodReviewCounts;
        this.homeTypeReviewCounts = homeTypeReviewCounts;
    }

    public List<String> getImpressions() {
        return impressions;
    }

    public void setImpressions(List<String> impressions) {
        this.impressions = impressions;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public List<String> getGoodReviewCounts() {
        return goodReviewCounts;
    }

    public void setGoodReviewCounts(List<String> goodReviewCounts) {
        this.goodReviewCounts = goodReviewCounts;
    }

    public List<String> getHomeTypeReviewCounts() {
        return homeTypeReviewCounts;
    }

    public void setHomeTypeReviewCounts(List<String> homeTypeReviewCounts) {
        this.homeTypeReviewCounts = homeTypeReviewCounts;
    }
}
