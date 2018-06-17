package com.knowledge.domain.XieChengDomains;

import java.io.Serializable;
import java.util.List;

/**
 * 酒店介绍
 */
public class XieChengIntro  implements Serializable{

    private String other;

    private List<String> label;

    private List<String> info;

    @Override
    public String toString() {
        return "XieChengIntro{" +
                "other='" + other + '\'' +
                ", label=" + label +
                ", info=" + info +
                '}';
    }

    public XieChengIntro() {
    }

    public XieChengIntro(String other, List<String> label, List<String> info) {
        this.other = other;
        this.label = label;
        this.info = info;
    }

    public String getOther() {
        other = other.replace("\"", "'");
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }
}
