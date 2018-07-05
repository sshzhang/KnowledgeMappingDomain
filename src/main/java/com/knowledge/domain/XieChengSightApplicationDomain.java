package com.knowledge.domain;

import com.knowledge.domain.XieChengDomains.Sight.XieChengSightCombinationInfos;
import com.knowledge.domain.XieChengDomains.Sight.XieChengSightCombinationTicket;
import com.knowledge.domain.XieChengDomains.Sight.XieChengSightDomain;
/**
 * 携程景点应用数据总体结构
 * 作为静态数据的混合体
 *
 */
public class XieChengSightApplicationDomain {

    private XieChengSightDomain xieChengSightDomain;

    private XieChengSightCombinationInfos xieChengSightCombinationInfos;

    private XieChengSightCombinationTicket xieChengSightCombinationTicket;


    public XieChengSightApplicationDomain() {
    }

    public XieChengSightApplicationDomain(XieChengSightDomain xieChengSightDomain, XieChengSightCombinationInfos xieChengSightCombinationInfos, XieChengSightCombinationTicket xieChengSightCombinationTicket) {
        this.xieChengSightDomain = xieChengSightDomain;
        this.xieChengSightCombinationInfos = xieChengSightCombinationInfos;
        this.xieChengSightCombinationTicket = xieChengSightCombinationTicket;
    }

    public XieChengSightDomain getXieChengSightDomain() {
        return xieChengSightDomain;
    }

    public void setXieChengSightDomain(XieChengSightDomain xieChengSightDomain) {
        this.xieChengSightDomain = xieChengSightDomain;
    }

    public XieChengSightCombinationInfos getXieChengSightCombinationInfos() {
        return xieChengSightCombinationInfos;
    }

    public void setXieChengSightCombinationInfos(XieChengSightCombinationInfos xieChengSightCombinationInfos) {
        this.xieChengSightCombinationInfos = xieChengSightCombinationInfos;
    }

    public XieChengSightCombinationTicket getXieChengSightCombinationTicket() {
        return xieChengSightCombinationTicket;
    }

    public void setXieChengSightCombinationTicket(XieChengSightCombinationTicket xieChengSightCombinationTicket) {
        this.xieChengSightCombinationTicket = xieChengSightCombinationTicket;
    }

    @Override
    public String toString() {
        return "XieChengSightApplicationDomain{" +
                "xieChengSightDomain=" + xieChengSightDomain +
                ", xieChengSightCombinationInfos=" + xieChengSightCombinationInfos +
                ", xieChengSightCombinationTicket=" + xieChengSightCombinationTicket +
                '}';
    }
}
