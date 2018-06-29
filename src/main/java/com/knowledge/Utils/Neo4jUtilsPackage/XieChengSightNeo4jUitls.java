package com.knowledge.Utils.Neo4jUtilsPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.knowledge.Utils.CommonUtilsPackage.DataTransformateCommonUtils;
import com.knowledge.Utils.ConstructDataTypePackage.MyNode;
import com.knowledge.domain.XieChengDomains.Sight.*;
import org.neo4j.driver.v1.*;

import java.lang.reflect.Type;
import java.util.List;

public class XieChengSightNeo4jUitls extends Neo4jUtils {


    private static final List<String> allXieChengHotelsName = DataTransformateCommonUtils.getAllXieChengHotelName();

    @Override
    public String call() throws Exception {
        return null;
    }

    /**
     * 把景点静态数据写入到图数据库
     *
     * @param xieChengSightDomain            表示景点数据结构domain
     * @param xieChengSightCombinationInfos  表示携程景点介绍数据的组合类型
     * @param xieChengSightCombinationTicket 表示 携程景点 相应的 买票 和优惠套餐价格组合
     */
    public void CreateXieChengSightDataToNeo4jNode(final XieChengSightDomain xieChengSightDomain, XieChengSightCombinationInfos xieChengSightCombinationInfos, XieChengSightCombinationTicket xieChengSightCombinationTicket) {

        XieChengSightIntroduce introducecom =
                xieChengSightCombinationInfos.getIntroduce();

        //预定须知
        final XieChengSightOrderNecessityKnow necessityKnow =
                xieChengSightCombinationInfos.getNecessityKnow();

        final List<XieChengSightSpecificPlay> plays =
                xieChengSightCombinationTicket.getPlays();

        final List<XieChengSightSpecificTicketAndHotel> ticketAndHotel = xieChengSightCombinationTicket.getTicketAndHotel();

        final MyNode root =
                xieChengSightCombinationTicket.getRoot();

         //交通信息状况
        String transportGuidence = xieChengSightCombinationInfos.getTransportGuidence();
        final String shop_name = xieChengSightDomain.getShop_name();
        final String introduce = introducecom.getIntroduce();
        final String features = introducecom.getFeatures();
        final String shop_service = xieChengSightDomain.getShop_service();

        Session session =
                this.driver.session();


        session.writeTransaction(new TransactionWork<Object>() {
            @Override
            public Object execute(Transaction transaction) {
                //创建Sight节点
                transaction.run("merge(hc:Sight{shop_name:\"" + shop_name+ "\",data_region:\"" + xieChengSightDomain.getData_region() + "\",data_source:\"" + xieChengSightDomain.getData_source() + "\",shop_address:\"" + xieChengSightDomain.getShop_address() + "\",shop_url:\"" + xieChengSightDomain.getShop_url() + "\",data_website:\"" + xieChengSightDomain.getData_website() + "\",shop_comment_num:" + xieChengSightDomain.getShop_comment_num() + ",shop_grade:" + xieChengSightDomain.getShop_grade() + ",shop_img:\"" + xieChengSightDomain.getShop_img() + "\",shop_time:\"" + xieChengSightDomain.getShop_time() + "\",shop_price:" + xieChengSightDomain.getShop_price() + ",shop_rate:\"" + xieChengSightDomain.getShop_rate() + "\",shop_introduce:\"" + introduce + "\"})");

                //创建特色节点
                List<String> feastrs =
                        DataTransformateCommonUtils.StringConvertToList(features);
                for (String feastr : feastrs) {
                    transaction.run("merge(hm:SightFeatures{description:\"" + feastr + "\"})");
                    //创建关系
                    transaction.run("match(hm:SightFeatures{description:\"" + feastr + "\"}),(hc:Sight{shop_name:\"" + shop_name + "\"}) merge(hc)-[:SightRelationIncludeFeatures{name:\"景点包含的特色信息\"}]->(hm)");
                }

                //创建承诺保障节点
                List<String> servicestrs= DataTransformateCommonUtils.StringConvertToList(shop_service);
                for (String servicestr : servicestrs){
                    transaction.run("merge(hser:SightServiceCommitment{description:\"" + servicestr + "\"})");
                    transaction.run("match(hser:SightServiceCommitment{description:\"" + servicestr + "\"}),(hc:Sight{shop_name:\"" + shop_name + "\"}) merge (hc)-[:SightRelationIncluseServiceCommitment{name:\"景点包含的服务承诺\"}]->(hser)");
                }


                //创建 优惠套餐信息  门票 玩乐 门票酒店   AllInformationAboutPlayTicketOthers    Tickets  PlayAndEntertainment   TicketAndHotel

                //玩乐

                for (XieChengSightSpecificPlay play : plays) {

                    String discount = play.getDiscount();//""
                    String price = play.getPrice();// ¥198 起
                    String name = play.getName();
                    String strPrice = DataTransformateCommonUtils.getTheNumberPriceFromstr(price,"([0-9]+\\.?[0-9]*)");
                    int numberPrice = "".equals(strPrice) ? -1 : Integer.parseInt(strPrice);
                    transaction.run("merge(play:AllInformationAboutPlayTicketOthers:PlayAndEntertainment{name:\"" + name + "\",price:" + numberPrice + "})");
                    transaction.run("match(play:AllInformationAboutPlayTicketOthers:PlayAndEntertainment{name:\"" + name + "\",price:" + numberPrice + "}),(hc:Sight{shop_name:\""+shop_name+"\"}) merge (hc)-[:SightRelationInclude{name:\"景点包含的玩乐信息\"}]->(play)");
                }

                //门票+酒店
                for (XieChengSightSpecificTicketAndHotel ticketAndHote : ticketAndHotel) {


                    String name = ticketAndHote.getName();

                    String price = ticketAndHote.getPrice();

                    String distance = ticketAndHote.getDistance();

                    String strPrice = DataTransformateCommonUtils.getTheNumberPriceFromstr(price, "([0-9]+\\.?[0-9]*)");
                    int numberPrice = "".equals(strPrice) ? -1 : Integer.parseInt(strPrice);
                    String strDistance = DataTransformateCommonUtils.getTheNumberPriceFromstr(distance, "([0-9]+\\.?[0-9]*[km|m]*)");

                    String reallHotelName="";
                    //找到相应的酒店信息
                    for (String allXieChengHotelName : allXieChengHotelsName) {

                        if (name.contains(allXieChengHotelName)) {

                            reallHotelName = allXieChengHotelName;
                            break;
                        }
                    }

                    //测试此reallHotelName 是否存在
                    StatementResult run = transaction.run("match(hm:XCHotel{data_website:\"携程\",Shope_name:\""+reallHotelName+"\"}) return hm.hotel_id as hotel_id");
                    run.hasNext();
                    Record record = run.next();
                    Value hotel_id = record.get("hotel_id");
                    if (hotel_id == null) {
                        throw new RuntimeException("酒店找不到:" + reallHotelName);
                    }

                    //创建 PlayAndEntertainment
                    transaction.run("merge(pel:AllInformationAboutPlayTicketOthers:PlayAndEntertainment{name:\"" + name + "\",price:" + numberPrice + ",distance:\"" + distance + "\"})");
                    transaction.run("match(pel:AllInformationAboutPlayTicketOthers:PlayAndEntertainment{name:\"" + name + "\",price:" + numberPrice + ",distance:\"" + distance + "\"}),(hc:Sight{shop_name:\"" + shop_name + "\"})  merge (hc)-[:SightRelationIncludeTicketAndHotel{name:\"景点包含的门票+酒店活动\"}]->(pel)");
                    //创建景点 酒店距离
                    transaction.run("match(hc:Sight{shop_name:\"" + shop_name + "\"}),(hm:XCHotel{data_website:\"携程\",Shope_name:\"" + reallHotelName + "\"}) merge (hc)-[:HotelAndSightDistance{name:\"酒店和景点之间的距离\"}]-(hm)");

                }

                //门票数据的写入
                 DataTransformateCommonUtils.TraversalTheTree(root);
                List<List<String>> alllistNodes = DataTransformateCommonUtils.alllistNodes;
                for (List<String> alllistNode : alllistNodes) {
                    //alllistNode.size() - 1元素为门票
                    //大标题
                    String NameTitle = alllistNode.get(alllistNode.size() - 2);

                    String secondNode = alllistNode.get(1);

                    if (secondNode.contains("{")) {//两层

                        XieChengSightTicketSpecific xieChengSightTicketSpecific = JSON.parseObject(secondNode, XieChengSightTicketSpecific.class);

                        String TypeName = alllistNode.get(alllistNode.size() - 3);

                        //名称  成人票
                        String OneTypePersonName = xieChengSightTicketSpecific.getName();

                        Type type = new TypeReference<List<XieChengSightTicketSpecific>>() { }.getType();
                        List<XieChengSightTicketSpecific> allxieChengSightTicketSpecifics = JSON.parseObject(alllistNode.get(0), type);
                        if (allxieChengSightTicketSpecifics != null)
                            for (XieChengSightTicketSpecific allxieChengSightTicketSpecific : allxieChengSightTicketSpecifics) {

                                //商家名称
                                String bussinerName = allxieChengSightTicketSpecific.getBussinerName();

                                //商品名称
                                String name = allxieChengSightTicketSpecific.getName();

                                //支付方式
                                String payway = allxieChengSightTicketSpecific.getPayway();

                                //价格
                                String price = allxieChengSightTicketSpecific.getPrice();

                                String strPrice = DataTransformateCommonUtils.getTheNumberPriceFromstr(price, "([0-9]+\\.?[0-9]*)");
                                int numberPrice = "".equals(strPrice) ? -1 : Integer.parseInt(strPrice);
                                transaction.run("merge(play:AllInformationAboutPlayTicketOthers:Tickets{abstractTypeName:\"" + NameTitle + "\",littlSpecificTypeName:\"" + TypeName + "\",detailTypeName:\"" + OneTypePersonName + "\",bussinerName:\"" + bussinerName + "\",productName:\"" + name + "\",payway:\"" + payway + "\",price:\"" + numberPrice + "\"})");
                                transaction.run("match(play:AllInformationAboutPlayTicketOthers:Tickets{abstractTypeName:\"" + NameTitle + "\",littlSpecificTypeName:\"" + TypeName + "\",detailTypeName:\"" + OneTypePersonName + "\",bussinerName:\"" + bussinerName + "\",productName:\"" + name + "\",payway:\"" + payway + "\",price:\"" + numberPrice + "\"}),(hc:Sight{shop_name:\"" + shop_name + "\"}) merge (hc)-[:SightRelationIncludeTickets{name:\"酒店包含的门票\"}]-(play)");

                            }

                    }else {//直接一层
                        Type type = new TypeReference<List<XieChengSightTicketSpecific>>() { }.getType();
                        List<XieChengSightTicketSpecific> allxieChengSightTicketSpecifics = JSON.parseObject(alllistNode.get(0), type);
                        if(allxieChengSightTicketSpecifics!=null)
                        for (XieChengSightTicketSpecific allxieChengSightTicketSpecific : allxieChengSightTicketSpecifics) {

                            String productName = allxieChengSightTicketSpecific.getName();

                            String payway = allxieChengSightTicketSpecific.getPayway();

                            String price = allxieChengSightTicketSpecific.getPrice();
                            String strPrice = DataTransformateCommonUtils.getTheNumberPriceFromstr(price, "([0-9]+\\.?[0-9]*)");
                            int numberPrice = "".equals(strPrice) ? -1 : Integer.parseInt(strPrice);
                            transaction.run("merge(play:AllInformationAboutPlayTicketOthers:Tickets{abstractTypeName:\"" + NameTitle + "\",productName:\"" + productName + "\",payway:\"" + payway + "\",price:\"" + numberPrice + "\"})");
                            transaction.run("match(play:AllInformationAboutPlayTicketOthers:Tickets{abstractTypeName:\"" + NameTitle + "\",productName:\"" + productName + "\",payway:\"" + payway + "\",price:\"" + numberPrice + "\"}),(hc:Sight{shop_name:\"" + shop_name + "\"}) merge (hc)-[:SightRelationIncludeTickets{name:\"酒店包含的门票\"}]-(play)");
                        }

                     }

                }

                //包含的预定须知信息
                String careful = necessityKnow.getCareful();
                String goodPolicy = necessityKnow.getGoodPolicy();
                String safeGuidence = necessityKnow.getSafeGuidence();
                String orderPolicy = necessityKnow.getOrderPolicy();
                transaction.run("merge(nkno:OrderNecessityKnow{treatBetterPolicy:\"" + goodPolicy + "\",orderConstraint:\"" + orderPolicy + "\",warmReminder:\"" + careful + "\",safeGuidence:\"" + safeGuidence + "\"})");
                transaction.run("match(nkno:OrderNecessityKnow{treatBetterPolicy:\"" + goodPolicy + "\",orderConstraint:\"" + orderPolicy + "\",warmReminder:\"" + careful + "\",safeGuidence:\"" + safeGuidence + "\"}),(sfh:Sight{shop_name:\"" + shop_name + "\"}) merge (sfh)-[:SightRelationIncludeOrderNecessityKnow{name:\"景点包含的预定须知\"}]->(nkno)");

                return null;
            }

        });



    }
}
