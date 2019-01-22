package com.knowledge.Utils.practicePreProcess;

public class UserCommodityPair {

    private String reviewerID;

    private String asin;

    public UserCommodityPair(String reviewerID, String asin) {
        this.reviewerID = reviewerID;
        this.asin = asin;
    }
    public void setAsin(String asin) {
        this.asin = asin;
    }

    public void setReviewerID(String reviewerID) {
        this.reviewerID = reviewerID;
    }

    public String getAsin() {
        return asin;
    }

    public String getReviewerID() {
        return reviewerID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserCommodityPair) {
            UserCommodityPair newpairs = (UserCommodityPair) obj;
            return this.reviewerID.equals(newpairs.reviewerID) && this.asin.equals(newpairs.asin);
        }

        return false;
    }
}
