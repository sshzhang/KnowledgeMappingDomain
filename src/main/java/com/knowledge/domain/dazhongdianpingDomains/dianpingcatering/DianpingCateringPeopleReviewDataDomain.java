package com.knowledge.domain.dazhongdianpingDomains.dianpingcatering;

public class DianpingCateringPeopleReviewDataDomain {

    private String name;

    private int attribute;


    public DianpingCateringPeopleReviewDataDomain() {
    }

    public DianpingCateringPeopleReviewDataDomain(String name, int attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }
}
