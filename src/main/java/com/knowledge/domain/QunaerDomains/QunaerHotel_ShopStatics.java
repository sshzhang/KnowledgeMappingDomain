package com.knowledge.domain.QunaerDomains;

import java.util.List;

public class QunaerHotel_ShopStatics {

    private List<String> comment_num_list;
    private List<String> tag_list;

    public QunaerHotel_ShopStatics(List<String> comment_num_list, List<String> tag_list) {
        this.comment_num_list = comment_num_list;
        this.tag_list = tag_list;
    }

    @Override
    public String toString() {
        return "QunaerHotel_ShopStatics{" +
                "comment_num_list=" + comment_num_list +
                ", tag_list=" + tag_list +
                '}';
    }

    public QunaerHotel_ShopStatics() {
    }

    public List<String>  getComment_num_list() {
        return comment_num_list;
    }

    public void setComment_num_list(List<String>  comment_num_list) {
        this.comment_num_list = comment_num_list;
    }

    public List<String> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<String> tag_list) {
        this.tag_list = tag_list;
    }
}
