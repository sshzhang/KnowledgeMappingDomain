package com.knowledge.domain.XieChengDomains;

import com.knowledge.Annotations.FieldMethodAnnotation;

/**
 * 携程组合介绍信息结构
 *   酒店介绍
 *   酒店政策
 *   酒店设施
 *
 */
public class XieChengCombinationHotelIntro {
    @FieldMethodAnnotation(FieldReallyName = "酒店介绍", MethodName = "setIntro", ParameterType = XieChengIntro.class)
    private XieChengIntro intro;

    @FieldMethodAnnotation(FieldReallyName = "酒店政策",MethodName = "setPolicy",ParameterType = XieChengPolicy.class)
    private XieChengPolicy policy;

    @FieldMethodAnnotation(FieldReallyName = "酒店设施",MethodName = "setFacility",ParameterType = XieChengFacility.class)
    private XieChengFacility facility;
    public XieChengCombinationHotelIntro() {
    }
    public XieChengCombinationHotelIntro(XieChengIntro intro, XieChengPolicy policy, XieChengFacility facility) {
        this.intro = intro;
        this.policy = policy;
        this.facility = facility;
    }

    @Override
    public String toString() {
        return "XieChengCombinationHotelIntro{" +
                "intro=" + intro +
                ", policy=" + policy +
                ", facility=" + facility +
                '}';
    }

    public XieChengIntro getIntro() {
        return intro;
    }

    public void setIntro(XieChengIntro intro) {
        this.intro = intro;
    }

    public XieChengPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(XieChengPolicy policy) {
        this.policy = policy;
    }

    public XieChengFacility getFacility() {
        return facility;
    }

    public void setFacility(XieChengFacility facility) {
        this.facility = facility;
    }
}
