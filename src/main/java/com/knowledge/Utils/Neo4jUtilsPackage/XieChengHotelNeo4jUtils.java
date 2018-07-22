package com.knowledge.Utils.Neo4jUtilsPackage;

import com.alibaba.fastjson.annotation.JSONField;
import com.knowledge.Annotations.FieldMethodAnnotation;
import com.knowledge.Utils.CommonUtilsPackage.LogsUtils;
import com.knowledge.Utils.DomainUtilsPackage.ConnectionPoolFactory;
import com.knowledge.domain.XieChengDomains.*;
import com.knowledge.domain.XieChengHotelApplicationDomain;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XieChengHotelNeo4jUtils extends Neo4jUtils<XieChengHotelComments,XieChengHotelApplicationDomain> {


    public XieChengHotelNeo4jUtils(XieChengHotelComments xieChengHotelComments,XieChengHotelApplicationDomain xieChengHotelApplicationDomain,int status) {
        super(xieChengHotelComments,xieChengHotelApplicationDomain,status);
    }

    @Override
    protected boolean CreateApplicationStaticContentDataToNeo4jNode(XieChengHotelApplicationDomain allStaticContent) {
       return  CreateXieChengDataToNeo4jNode(allStaticContent.getXieChengHotel(),allStaticContent.getXieChengAroundFacility(),allStaticContent.getXieChengShopStatistics(),allStaticContent.getXieChengHotelAllRooms(),allStaticContent.getCombinationHotelIntro());
    }

    @Override
    protected boolean CreateApplicationCommentDataToNeo4jNode(XieChengHotelComments CommentT) {
       return  CreateXieChengCommentDataToNeo4jNode(CommentT);
    }


    public boolean CreateXieChengDataToNeo4jNode(final XieChengHotel xichotel, final XieChengAroundFacility aroundFacility, final XieChengShopStatistics statistics, final XieChengHotelAllRooms xieRooms, final XieChengCombinationHotelIntro combinationHotelIntro) {

        Session session = null;
        try {

             session = ConnectionPoolFactory.getDriverInfo(Thread.currentThread().getName()).session();
            final String hotel_id = xichotel.get_id();
            final List<XieChengHotelRoom_detail> all_rooms = xieRooms.getAll_room();

            final XieChengIntro intro = combinationHotelIntro.getIntro();
            final String Shop_sepcific_introduce = intro.getOther();
            session.writeTransaction(new TransactionWork<Object>() {
                public Object execute(Transaction tx) {
                    try {

                        tx.run("merge(sf:XCHotel{hotel_id:\"" + hotel_id + "\",Shope_name:\"" + xichotel.getShop_name() + "\",Shop_address:\"" + xichotel.getShop_address() + "\",Shop_sepcific_introduce:\"" + Shop_sepcific_introduce + "\",shope_url:\"" + xichotel.getShop_url() + "\",data_website:\"" + xichotel.getData_website() + "\",shop_rate:\"" + xichotel.getShop_rate() + "\"}) return sf");
                        //所有的房型信息节点
                        for (XieChengHotelRoom_detail all_room : all_rooms) {

                            XieChengHotelRoom_detailSpecific room_detail = all_room.getRoom_detail();

                            List<XieChengHotelRoomInfoListSpecific> room_info_lists = all_room.getRoom_info_list();

                            tx.run("merge(ho:Home{HomeType:\"" + room_detail.getHomeType() + "\",beizhuxinxi:\"" + room_detail.getNoSmaokingMeasure() + "\",bianlisheshi:\"" + room_detail.getConvientFacility() + "\",chuanxing:\"" + room_detail.getBedType() + "\",floor:\"" + room_detail.getFloor() + "\",jianzhumianji:\"" + room_detail.getBuidingArea() + "\",kejiachuang:\"" + room_detail.getIncreaseBed() + "\",yushi:\"" + room_detail.getBedthroom() + "\",hotel_id:\"" + hotel_id + "\"})");
                            tx.run("match(sfm:XCHotel{hotel_id:\"" + hotel_id + "\",Shope_name:\"" + xichotel.getShop_name() + "\",Shop_address:\"" + xichotel.getShop_address() + "\",Shop_sepcific_introduce:\"" + Shop_sepcific_introduce + "\",shope_url:\"" + xichotel.getShop_url() + "\",data_website:\"" + xichotel.getData_website() + "\",shop_rate:\"" + xichotel.getShop_rate() + "\"}),(hom:Home{HomeType:\"" + room_detail.getHomeType() + "\",beizhuxinxi:\"" + room_detail.getNoSmaokingMeasure() + "\",bianlisheshi:\"" + room_detail.getConvientFacility() + "\",chuanxing:\"" + room_detail.getBedType() + "\",floor:\"" + room_detail.getFloor() + "\",jianzhumianji:\"" + room_detail.getBuidingArea() + "\",kejiachuang:\"" + room_detail.getIncreaseBed() + "\",yushi:\"" + room_detail.getBedthroom() + "\",hotel_id:\"" + hotel_id + "\"}) merge(sfm)-[:IncludeHomeTypes{name:\"包含的房间类型\"}]->(hom)");
                            for (XieChengHotelRoomInfoListSpecific room_info_list : room_info_lists) {
                                System.out.println(room_info_list.getSatisfactionDegree());
                                tx.run("merge(hs:HomeSpecificInfos{chuangxing:\"" + room_info_list.getBedType() + "\",fangjia:\"" + room_info_list.getHomePrice() + "\",hotel_id:\"" + hotel_id + "\",kuangdai:\"" + room_info_list.getInternet() + "\",mayidu:\"" + room_info_list.getSatisfactionDegree() + "\",ruzhurenshu:\"" + room_info_list.getLivePeopleNums() + "\",zaocan:\"" + room_info_list.getBreakfast() + "\",zhengce:\"" + room_info_list.getPolicy() + "\"})");
                                tx.run("match(hom:Home{HomeType:\"" + room_detail.getHomeType() + "\",beizhuxinxi:\"" + room_detail.getNoSmaokingMeasure() + "\",bianlisheshi:\"" + room_detail.getConvientFacility() + "\",chuanxing:\"" + room_detail.getBedType() + "\",floor:\"" + room_detail.getFloor() + "\",jianzhumianji:\"" + room_detail.getBuidingArea() + "\",kejiachuang:\"" + room_detail.getIncreaseBed() + "\",yushi:\"" + room_detail.getBedthroom() + "\",hotel_id:\"" + hotel_id + "\"}),(hsm:HomeSpecificInfos{chuangxing:\"" + room_info_list.getBedType() + "\",fangjia:\"" + room_info_list.getHomePrice() + "\",hotel_id:\"" + hotel_id + "\",kuangdai:\"" + room_info_list.getInternet() + "\",mayidu:\"" + room_info_list.getSatisfactionDegree() + "\",ruzhurenshu:\"" + room_info_list.getLivePeopleNums() + "\",zaocan:\"" + room_info_list.getBreakfast() + "\",zhengce:\"" + room_info_list.getPolicy() + "\"}) merge(hom)-[:HomeSpecficRelationship{name:\"某种房间包含的具体型号信息\"}]->(hsm)");
                            }
                        }

                        //所有的标签信息
                        List<String> labels = intro.getLabel();
                        List<String> infos = intro.getInfo();
                        for (String label : labels) {
                            String values = "";
                            if (infos != null && infos.size() != 0) {

                                for (String info : infos) {

                                    if (info.equals(label)) {
                                        values = info.replace(label + ": ", "");
                                        break;
                                    }
                                }
                            }
                            tx.run("merge(hla:HotelLabel{name:\"" + label + "\",description:\"" + values + "\"})");
                            tx.run("match(hla:HotelLabel{name:\"" + label + "\",description:\"" + values + "\"}),(sfx:XCHotel{hotel_id:\"" + hotel_id + "\",Shope_name:\"" + xichotel.getShop_name() + "\",Shop_address:\"" + xichotel.getShop_address() + "\",Shop_sepcific_introduce:\"" + Shop_sepcific_introduce + "\",shope_url:\"" + xichotel.getShop_url() + "\",data_website:\"" + xichotel.getData_website() + "\",shop_rate:\"" + xichotel.getShop_rate() + "\"}) merge(sfx)-[:HotelLabelInfos{name:\"宾馆标签信息\"}]->(hla) ");
                        }


                        //设置宾馆设施
                        XieChengFacility facility = combinationHotelIntro.getFacility();

                        Class<?> aClass = Class.forName("com.knowledge.domain.XieChengDomains.XieChengFacility");
                        Field[] declaredFields =
                                aClass.getDeclaredFields();

                        for (Field field : declaredFields) {

                            FieldMethodAnnotation filedMethodAnnotation = field.getAnnotation(FieldMethodAnnotation.class);
                            JSONField jsonField = field.getAnnotation(JSONField.class);
                            String name = jsonField.name();
                            String methodName = filedMethodAnnotation.MethodName();
                            Method method =
                                    aClass.getMethod(methodName);
                            List<String> invokes = (List<String>) method.invoke(facility);
                            if (invokes != null && invokes.size() != 0)
                                for (String invoke : invokes) {
                                    tx.run("merge(sf:Shope_facility{name:\"" + name + "\",shuxing:\"" + invoke + "\"})");
                                    tx.run("match(sfacility:Shope_facility{name:\"" + name + "\",shuxing:\"" + invoke + "\"}),(xc:XCHotel{hotel_id:\"" + hotel_id + "\",Shope_name:\"" + xichotel.getShop_name() + "\",Shop_address:\"" + xichotel.getShop_address() + "\",Shop_sepcific_introduce:\"" + Shop_sepcific_introduce + "\",shope_url:\"" + xichotel.getShop_url() + "\",data_website:\"" + xichotel.getData_website() + "\",shop_rate:\"" + xichotel.getShop_rate() + "\"}) merge(xc)-[:hotelIncludeFacilityRelationship{name:\"宾馆包含的设施\"}]->(sfacility) ");
                                }
                        }


                        //宾馆政策信息
                        XieChengPolicy policy = combinationHotelIntro.getPolicy();
                        Class<?> Classpolicy = Class.forName("com.knowledge.domain.XieChengDomains.XieChengPolicy");
                        Field[] declaredFieldsPolicy = Classpolicy.getDeclaredFields();
                        Pattern compile = Pattern.compile("(?<startIndex>.*):(?<endIndex>.*):(?<gapIndex>.*)");
                        for (Field field : declaredFieldsPolicy) {
                            JSONField jsonFieldPolicy = field.getAnnotation(JSONField.class);
                            FieldMethodAnnotation fieldAnnotationPolicy = field.getAnnotation(FieldMethodAnnotation.class);
                            String name = jsonFieldPolicy.name();
                            String methodName = fieldAnnotationPolicy.MethodName();
                            String selectElementsTypes = fieldAnnotationPolicy.SelectElementsTypes();
                            Method method = Classpolicy.getMethod(methodName);
                            List<String> invokes = (List<String>) method.invoke(policy);
                            if (invokes == null || invokes.size() == 0) continue;
                            Matcher matcher = compile.matcher(selectElementsTypes);
                            if (matcher.find()) {
                                int startIndex = Integer.parseInt(matcher.group("startIndex"));
                                int endIndex = Integer.parseInt(matcher.group("endIndex"));
                                int gapIndex = Integer.parseInt(matcher.group("gapIndex"));
                                endIndex = endIndex == -1 ? invokes.size() : endIndex;
                                for (int index = startIndex; index < endIndex; ) {
                                    tx.run("merge(shpolicy:Shope_policy{name:\"" + name + "\",shuxing:\"" + invokes.get(index) + "\"} )");
                                    tx.run("match(shpolicy:Shope_policy{name:\"" + name + "\",shuxing:\"" + invokes.get(index) + "\"}),(sf:XCHotel{hotel_id:\"" + hotel_id + "\",Shope_name:\"" + xichotel.getShop_name() + "\",Shop_address:\"" + xichotel.getShop_address() + "\",Shop_sepcific_introduce:\"" + Shop_sepcific_introduce + "\",shope_url:\"" + xichotel.getShop_url() + "\",data_website:\"" + xichotel.getData_website() + "\",shop_rate:\"" + xichotel.getShop_rate() + "\"}) merge(sf)-[:hotlPolicyRelationship {name:\"宾馆包含的政策\"}]->(shpolicy)");
                                    index += gapIndex;
                                }
                            }
                        }


                        //宾馆周边设施

                        Class<?> aClassAroundFacility = Class.forName("com.knowledge.domain.XieChengDomains.XieChengAroundFacility");
                        Field[] declaredFieldsAroundFacility = aClassAroundFacility.getDeclaredFields();
                        for (Field declaredFieldAroundFacility : declaredFieldsAroundFacility) {
                            JSONField jsonFieldAroundFacility = declaredFieldAroundFacility.getAnnotation(JSONField.class);
                            FieldMethodAnnotation fieldMethodAnnotationAroundFacility = declaredFieldAroundFacility.getAnnotation(FieldMethodAnnotation.class);
                            String name = jsonFieldAroundFacility.name();
                            String methodName = fieldMethodAnnotationAroundFacility.MethodName();
                            Method method = aClassAroundFacility.getMethod(methodName);
                            List<String> invokes = (List<String>) method.invoke(aroundFacility);
                            if (invokes != null && invokes.size() != 0) {
                                for (String invoke : invokes) {
                                    String[] strs = invoke.split(",");
                                    for (String str : strs) {
                                        System.out.println(str);
                                        tx.run("merge(sfm:Shope_around_facility{name:\"" + name + "\",shuxing:\"" + str + "\"})");
                                        tx.run("match(sfmt:Shope_around_facility{name:\"" + name + "\",shuxing:\"" + str + "\"}),(xsf:XCHotel{hotel_id:\"" + hotel_id + "\",Shope_name:\"" + xichotel.getShop_name() + "\",Shop_address:\"" + xichotel.getShop_address() + "\",Shop_sepcific_introduce:\"" + Shop_sepcific_introduce + "\",shope_url:\"" + xichotel.getShop_url() + "\",data_website:\"" + xichotel.getData_website() + "\",shop_rate:\"" + xichotel.getShop_rate() + "\"}) merge(xsf)-[:ShopeAroundFacilityRelationship {name:\"宾馆周边设施\"}]->(sfmt)");
                                    }
                                }
                            }
                        }

                        Pattern impressionCompile = Pattern.compile("\\{\"(?<key>.*)\":\"(?<value>.*)\"\\}");

                        //住客印象
                        List<String> impressions = statistics.getImpressions();
                        for (int index = 0; index < impressions.size(); index++) {
                            String impressionstr = impressions.get(index);
                            Matcher matcher = impressionCompile.matcher(impressionstr);
                            if (matcher.find()) {

                                String key = matcher.group("key");
                                String value = matcher.group("value");
                                System.out.println("impression: key=" + key + "  value=" + value);
                                tx.run("merge(sgi:Shope_gust_impression{name:\"" + key + "\",shuxing:\"" + value + "\"})");
                                tx.run("match(sgi:Shope_gust_impression{name:\"" + key + "\",shuxing:\"" + value + "\"}),(sf:XCHotel{hotel_id:\"" + hotel_id + "\",Shope_name:\"" + xichotel.getShop_name() + "\",Shop_address:\"" + xichotel.getShop_address() + "\",Shop_sepcific_introduce:\"" + Shop_sepcific_introduce + "\",shope_url:\"" + xichotel.getShop_url() + "\",data_website:\"" + xichotel.getData_website() + "\",shop_rate:\"" + xichotel.getShop_rate() + "\"}) merge(sf)-[:ShopeZhukeImpressionRelationship{name:\"宾馆住客印象\"}]->(sgi)");
                            }
                        }

                        //住客点评
                        List<String> reviews = statistics.getReviews();
                        for (int index = 5; index < reviews.size(); index++) {
                            String reviewstr = reviews.get(index);
                            Matcher matcher = impressionCompile.matcher(reviewstr);
                            if (matcher.find()) {
                                String key = matcher.group("key");
                                String value = matcher.group("value");
                                System.out.println("reviews: key=" + key + "  value=" + value);
                                tx.run("merge(sgi:Shope_gust_comments{name:\"" + key + "\",shuxing:\"" + value + "\"})");
                                tx.run("match(sgi:Shope_gust_comments{name:\"" + key + "\",shuxing:\"" + value + "\"}),(sf:XCHotel{hotel_id:\"" + hotel_id + "\",Shope_name:\"" + xichotel.getShop_name() + "\",Shop_address:\"" + xichotel.getShop_address() + "\",Shop_sepcific_introduce:\"" + Shop_sepcific_introduce + "\",shope_url:\"" + xichotel.getShop_url() + "\",data_website:\"" + xichotel.getData_website() + "\",shop_rate:\"" + xichotel.getShop_rate() + "\"}) merge(sf)-[:ShopeZhukeImpressionRelationship{name:\"宾馆住客印象\"}]->(sgi)");
                            }
                        }


                        return true;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return false;
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private boolean CreateXieChengCommentDataToNeo4jNode(final XieChengHotelComments xieChengHotelComments) {

        Session session = null;
        Transaction tx = null;
        try {
            final String comment_content = xieChengHotelComments.getComment_content();
            final String comment_score_text = xieChengHotelComments.getComment_score_text();
            final double comment_score = xieChengHotelComments.getComment_score();
            final String comment_time = xieChengHotelComments.getComment_time();
            final String comment_type = xieChengHotelComments.getComment_type();
            final String comment_user_room = xieChengHotelComments.getComment_user_room();
            final String shop_name = xieChengHotelComments.getShop_name();
            //查找对应的酒店信息---->评论对应的房型信息
            //创建评论节点
            //创建关系
             session = ConnectionPoolFactory.getDriverInfo(Thread.currentThread().getName()).session();
            /*session.writeTransaction(new TransactionWork<Object>() {
                @Override
                public Object execute(Transaction tx) {
                    try {
                        StatementResult run = tx.run("match(sn:XCHotel{Shope_name:\"" + shop_name + "\",data_website:\"携程\"}) return sn.hotel_id as hotel_id");
                        System.out.println(shop_name);
                        if(!run.hasNext()) return null;
                        String hotel_id = run.single().get("hotel_id").asString();
                        System.out.println(hotel_id);
                        tx.run("merge(smn:Comments{comment_content:\"" + comment_content + "\",data_website:\"" + xieChengHotelComments.getData_website() + "\",comment_time:\"" + comment_time + "\",comment_score_text:\"" + comment_score_text + "\",comment_type:\"" + comment_type + "\",comment_score:" + comment_score + ",comment_user_name:\"" + xieChengHotelComments.getComment_user_name() + "\",comment_user_check_in:\"" + xieChengHotelComments.getComment_user_check_in() + "\",data_source:\"" + xieChengHotelComments.getData_source() + "\",shop_name:\"" + shop_name + "\"})");
                        tx.run("match(smtn:Comments{comment_content:\"" + comment_content + "\",data_website:\"" + xieChengHotelComments.getData_website() + "\",comment_time:\"" + comment_time + "\",comment_score_text:\"" + comment_score_text + "\",comment_type:\"" + comment_type + "\",comment_score:" + comment_score + ",comment_user_name:\"" + xieChengHotelComments.getComment_user_name() + "\",comment_user_check_in:\"" + xieChengHotelComments.getComment_user_check_in() + "\",data_source:\"" + xieChengHotelComments.getData_source() + "\",shop_name:\"" + shop_name + "\"}),(sfo:Home{hotel_id:\"" + hotel_id + "\",HomeType:\"" + comment_user_room + "\"}) merge(sfo)-[:IncludeComments{name:\"某种房型包含的评论\"}]->(smtn)");
                        System.out.println("match(smtn:Comments{comment_content:\"" + comment_content + "\",data_website:\"" + xieChengHotelComments.getData_website() + "\",comment_time:\"" + comment_time + "\",comment_score_text:\"" + comment_score_text + "\",comment_type:\"" + comment_type + "\",comment_score:" + comment_score + ",comment_user_name:\"" + xieChengHotelComments.getComment_user_name() + "\",comment_user_check_in:\"" + xieChengHotelComments.getComment_user_check_in() + "\",data_source:\"" + xieChengHotelComments.getData_source() + "\",shop_name:\"" + shop_name + "\"}),(sfo:Home{hotel_id:\"" + hotel_id + "\",HomeType:\"" + comment_user_room + "\"}) merge(sfo)-[:IncludeComments{name:\"某种房型包含的评论\"}]->(smtn)");
                        System.out.println();
                    } catch (Exception ex) {
                        LogsUtils.WriteTheDataToFile(ex.getMessage(), "/home/xiujiezhang/IdeaProjects/KnowledgeMappingDomain/src/resources/error.txt");
                        ex.printStackTrace();
                    }
                    return true;
                }
            });*/

             tx= session.beginTransaction();
            StatementResult run = tx.run("match(sn:XCHotel{Shope_name:\"" + shop_name + "\",data_website:\"携程\"}) return sn.hotel_id as hotel_id");
            System.out.println(shop_name);
            if(!run.hasNext()) return true;
            String hotel_id = run.single().get("hotel_id").asString();
            System.out.println(hotel_id);
            tx.run("merge(smn:Comments{comment_content:\"" + comment_content + "\",data_website:\"" + xieChengHotelComments.getData_website() + "\",comment_time:\"" + comment_time + "\",comment_score_text:\"" + comment_score_text + "\",comment_type:\"" + comment_type + "\",comment_score:" + comment_score + ",comment_user_name:\"" + xieChengHotelComments.getComment_user_name() + "\",comment_user_check_in:\"" + xieChengHotelComments.getComment_user_check_in() + "\",data_source:\"" + xieChengHotelComments.getData_source() + "\",shop_name:\"" + shop_name + "\"})");
            tx.run("match(smtn:Comments{comment_content:\"" + comment_content + "\",data_website:\"" + xieChengHotelComments.getData_website() + "\",comment_time:\"" + comment_time + "\",comment_score_text:\"" + comment_score_text + "\",comment_type:\"" + comment_type + "\",comment_score:" + comment_score + ",comment_user_name:\"" + xieChengHotelComments.getComment_user_name() + "\",comment_user_check_in:\"" + xieChengHotelComments.getComment_user_check_in() + "\",data_source:\"" + xieChengHotelComments.getData_source() + "\",shop_name:\"" + shop_name + "\"}),(sfo:Home{hotel_id:\"" + hotel_id + "\",HomeType:\"" + comment_user_room + "\"}) merge(sfo)-[:IncludeComments{name:\"某种房型包含的评论\"}]->(smtn)");
            System.out.println("match(smtn:Comments{comment_content:\"" + comment_content + "\",data_website:\"" + xieChengHotelComments.getData_website() + "\",comment_time:\"" + comment_time + "\",comment_score_text:\"" + comment_score_text + "\",comment_type:\"" + comment_type + "\",comment_score:" + comment_score + ",comment_user_name:\"" + xieChengHotelComments.getComment_user_name() + "\",comment_user_check_in:\"" + xieChengHotelComments.getComment_user_check_in() + "\",data_source:\"" + xieChengHotelComments.getData_source() + "\",shop_name:\"" + shop_name + "\"}),(sfo:Home{hotel_id:\"" + hotel_id + "\",HomeType:\"" + comment_user_room + "\"}) merge(sfo)-[:IncludeComments{name:\"某种房型包含的评论\"}]->(smtn)");
            tx.success();

        } catch (Exception ex) {
            tx.failure();
            LogsUtils.WriteTheDataToFile(ex.getMessage(), "/home/xiujiezhang/IdeaProjects/KnowledgeMappingDomain/src/resources/error.txt");
            ex.printStackTrace();
            return false;
        } finally {
            try {
                tx.close();
                if(session!=null)
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;

    }
}
