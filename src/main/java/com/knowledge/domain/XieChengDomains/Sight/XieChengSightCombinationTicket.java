package com.knowledge.domain.XieChengDomains.Sight;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.knowledge.Utils.ConstructDataTypePackage.MyNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 玩乐
 * 门票
 * 门票+酒店
 * 三者组合
 */
public class XieChengSightCombinationTicket {


    @JSONField(name = "玩乐")
    private List<XieChengSightSpecificPlay > plays=new ArrayList<>();

    @JSONField(name = "门票+酒店")
    private List<XieChengSightSpecificTicketAndHotel> ticketAndHotel = new ArrayList<>();

    @JSONField(name = "门票")
    private JSONObject tickets;

    //设置门票数据对应的树结构
    private MyNode root;


    public XieChengSightCombinationTicket() {
    }

    public XieChengSightCombinationTicket(List<XieChengSightSpecificPlay> plays, List<XieChengSightSpecificTicketAndHotel> ticketAndHotel, JSONObject tickets) {
        this.plays = plays;
        this.ticketAndHotel = ticketAndHotel;
        this.tickets = tickets;
    }

    public List<XieChengSightSpecificPlay> getPlays() {
        return plays;
    }

    public void setPlays(List<XieChengSightSpecificPlay> plays) {
        this.plays = plays;
    }

    public List<XieChengSightSpecificTicketAndHotel> getTicketAndHotel() {
        return ticketAndHotel;
    }

    public void setTicketAndHotel(List<XieChengSightSpecificTicketAndHotel> ticketAndHotel) {
        this.ticketAndHotel = ticketAndHotel;
    }

    public JSONObject getTickets() {
        return tickets;
    }

    public void setTickets(JSONObject tickets) {
        this.tickets = tickets;
    }

    public void setRoot(MyNode root) {
        this.root = root;
    }

    public MyNode getRoot() {
        return root;
    }


    @Override
    public String toString() {
        return "XieChengSightCombinationTicket{" +
                "plays=" + plays +
                ", ticketAndHotel=" + ticketAndHotel +
                ", tickets=" + tickets +
                '}';
    }
}
