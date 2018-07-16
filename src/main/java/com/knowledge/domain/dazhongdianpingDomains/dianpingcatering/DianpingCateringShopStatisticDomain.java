package com.knowledge.domain.dazhongdianpingDomains.dianpingcatering;

import com.knowledge.Annotations.FieldMethodAnnotation;

import java.util.List;
import java.util.Map;

public class DianpingCateringShopStatisticDomain {

    @FieldMethodAnnotation(FieldReallyName = "大家认为")
    private Map<String, List<DianpingCateringPeopleReviewDataDomain>> peopleAllThink;

    @FieldMethodAnnotation(FieldReallyName = "评价")
    private Map<String,List<DianpingCateringPeopleReviewDataDomain>> reviewInfoDomainMap;

    public DianpingCateringShopStatisticDomain() {
    }
    public DianpingCateringShopStatisticDomain(Map<String, List<DianpingCateringPeopleReviewDataDomain>> peopleAllThink, Map<String, List<DianpingCateringPeopleReviewDataDomain>> reviewInfoDomainMap) {
        this.peopleAllThink = peopleAllThink;
        this.reviewInfoDomainMap = reviewInfoDomainMap;
    }

    public Map<String, List<DianpingCateringPeopleReviewDataDomain>> getPeopleAllThink() {
        return peopleAllThink;
    }

    public void setPeopleAllThink(Map<String, List<DianpingCateringPeopleReviewDataDomain>> peopleAllThink) {
        this.peopleAllThink = peopleAllThink;
    }

    public Map<String, List<DianpingCateringPeopleReviewDataDomain>> getReviewInfoDomainMap() {
        return reviewInfoDomainMap;
    }

    public void setReviewInfoDomainMap(Map<String, List<DianpingCateringPeopleReviewDataDomain>> reviewInfoDomainMap) {
        this.reviewInfoDomainMap = reviewInfoDomainMap;
    }

    @Override
    public String toString() {
        return "DianpingCateringShopStatisticDomain{" +
                "peopleAllThink=" + peopleAllThink +
                ", reviewInfoDomainMap=" + reviewInfoDomainMap +
                '}';
    }
}
