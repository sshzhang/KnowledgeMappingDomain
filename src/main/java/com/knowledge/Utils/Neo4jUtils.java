package com.knowledge.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.knowledge.Annotations.FieldMethodAnnotation;
import com.knowledge.domain.XieChengDomains.*;
import org.neo4j.driver.v1.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Neo4j工具类
 */
public class Neo4jUtils {


    private final Driver driver;

    public Neo4jUtils(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    private void close()throws  Exception {
        driver.close();
    }


    public void CreateXieChengDataToNeo4jNode(final XieChengHotel xichotel , final XieChengAroundFacility aroundFacility, final XieChengShopStatistics statistics, final XieChengHotelAllRooms xieRooms, final XieChengCombinationHotelIntro combinationHotelIntro) {

        Session session =
                driver.session();
        final String hotel_id = xichotel.get_id();
        final List<XieChengHotelRoom_detail> all_rooms = xieRooms.getAll_room();

        final XieChengIntro intro = combinationHotelIntro.getIntro();
        final  String Shop_sepcific_introduce = intro.getOther();
        session.writeTransaction(new TransactionWork<Object>() {
            public Object execute(Transaction tx) {
                tx.run("merge(sf:XCHotel{hotel_id:" + hotel_id + ",Shope_name:" + xichotel.getShop_name() + ",Shop_address:" + xichotel.getShop_address() + ",Shop_sepcific_introduce:" + Shop_sepcific_introduce + ",shope_url:" + xichotel.getShop_url() + ",data_website:" + xichotel.getData_website() + ",shop_rate:" + xichotel.getShop_rate() + "}) return sf");
                //所有的房型信息节点
                for (XieChengHotelRoom_detail all_room : all_rooms) {

                    XieChengHotelRoom_detailSpecific room_detail = all_room.getRoom_detail();

                    List<XieChengHotelRoomInfoListSpecific> room_info_lists = all_room.getRoom_info_list();

                    tx.run("merge(ho:Home{HomeType:" + room_detail.getHomeType() + ",beizhuxinxi:" + room_detail.getNoSmaokingMeasure() + ",bianlisheshi:" + room_detail.getConvientFacility() + ",chuanxing:" + room_detail.getBedType() + ",floor:" + room_detail.getFloor() + ",jianzhumianji:" + room_detail.getBuidingArea() + ",kejiachuang:" + room_detail.getIncreaseBed() + ",yushi:" + room_detail.getBedthroom() + "})");
                    tx.run("match(sfm:XCHotel{hotel_id:" + hotel_id + ",Shope_name:" + xichotel.getShop_name() + ",Shop_address:" + xichotel.getShop_address() + ",Shop_sepcific_introduce:" + Shop_sepcific_introduce + ",shope_url:" + xichotel.getShop_url() + ",data_website:" + xichotel.getData_website() + ",shop_rate:" + xichotel.getShop_rate() + "})，(hom:Home{HomeType:" + room_detail.getHomeType() + ",beizhuxinxi:" + room_detail.getNoSmaokingMeasure() + ",bianlisheshi:" + room_detail.getConvientFacility() + ",chuanxing:" + room_detail.getBedType() + ",floor:" + room_detail.getFloor() + ",jianzhumianji:" + room_detail.getBuidingArea() + ",kejiachuang:" + room_detail.getIncreaseBed() + ",yushi:" + room_detail.getBedthroom() + "}) merge(sfm)-[:IncludeHomeTypes{name:”包含的房间类型”}]->(hom)");
                    for (XieChengHotelRoomInfoListSpecific room_info_list : room_info_lists) {
                        tx.run("merge(hs:HomeSpecificInfos){chuangxing:" + room_info_list.getBedType() + ",fangjia:" + room_info_list.getHomePrice() + ",hotel_id:" + hotel_id + ",kuangdai:" + room_info_list.getInternet() + ",mayidu:" + room_info_list.getSatisfactionDegree() + ",ruzhurenshu:" + room_info_list.getLivePeopleNums() + ",zaocan:" + room_info_list.getBreakfast() + ",zhengce:" + room_info_list.getPolicy() + "}");
                        tx.run("match(hom:Home{HomeType:" + room_detail.getHomeType() + ",beizhuxinxi:" + room_detail.getNoSmaokingMeasure() + ",bianlisheshi:" + room_detail.getConvientFacility() + ",chuanxing:" + room_detail.getBedType() + ",floor:" + room_detail.getFloor() + ",jianzhumianji:" + room_detail.getBuidingArea() + ",kejiachuang:" + room_detail.getIncreaseBed() + ",yushi:" + room_detail.getBedthroom() + "}),(hsm:HomeSpecificInfos{chuangxing:" + room_info_list.getBedType() + ",fangjia:" + room_info_list.getHomePrice() + ",hotel_id:" + hotel_id + ",kuangdai:" + room_info_list.getInternet() + ",mayidu:" + room_info_list.getSatisfactionDegree() + ",ruzhurenshu:" + room_info_list.getLivePeopleNums() + ",zaocan:" + room_info_list.getBreakfast() + ",zhengce:" + room_info_list.getPolicy() + "}) merge(hom)-[:HomeSpecficRelationship{name:”某种房间包含的具体型号信息”}]->(hsm)");
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
                    tx.run("merge(hla:HotelLabel{name:" + label + ",description:" + values + "})");
                    tx.run("match(hla:HotelLabel{name:" + label + ",description:" + values + "}),(sfx:XCHotel{hotel_id:" + hotel_id + ",Shope_name:" + xichotel.getShop_name() + ",Shop_address:" + xichotel.getShop_address() + ",Shop_sepcific_introduce:" + Shop_sepcific_introduce + ",shope_url:" + xichotel.getShop_url() + ",data_website:" + xichotel.getData_website() + ",shop_rate:" + xichotel.getShop_rate() + "}) merge(sfx)-[:HotelLabelInfos{name:”宾馆标签信息”}]->(hla) ");
                }


                //设置宾馆设施
                XieChengFacility facility = combinationHotelIntro.getFacility();

                try {
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

                        for (String invoke : invokes) {
                            tx.run("merge(sf:Shope_facility{name:" + name + ",shuxing:" + invoke + "})");
                            tx.run("match(sfacility:Shope_facility{name:" + name + ",shuxing:" + invoke + "}),(xc:XCHotel{hotel_id:" + hotel_id + ",Shope_name:" + xichotel.getShop_name() + ",Shop_address:" + xichotel.getShop_address() + ",Shop_sepcific_introduce:" + Shop_sepcific_introduce + ",shope_url:" + xichotel.getShop_url() + ",data_website:" + xichotel.getData_website() + ",shop_rate:" + xichotel.getShop_rate() + "}) merge(xc)-[:hotelIncludeFacilityRelationship{name:”宾馆包含的设施”}]->(sfacility) ");
                        }
                    }


                    //宾馆政策信息
                    XieChengPolicy policy = combinationHotelIntro.getPolicy();
                    Class<?> Classpolicy = Class.forName("com.knowledge.domain.XieChengDomains.XieChengPolicy");
                    Field[] declaredFieldsPolicy= Classpolicy.getDeclaredFields();
                    Pattern compile = Pattern.compile("(?<startIndex>.*):(?<endIndex>.*):(?<gapIndex>.*)");
                    for (Field field : declaredFieldsPolicy) {
                        JSONField jsonFieldPolicy = field.getAnnotation(JSONField.class);
                        FieldMethodAnnotation fieldAnnotationPolicy = field.getAnnotation(FieldMethodAnnotation.class);
                        String name = jsonFieldPolicy.name();
                        String methodName = fieldAnnotationPolicy.MethodName();
                        String selectElementsTypes = fieldAnnotationPolicy.SelectElementsTypes();
                        Method method = Classpolicy.getMethod(methodName);
                        List<String> invokes = (List<String>) method.invoke(policy);
                        Matcher matcher = compile.matcher(selectElementsTypes);
                        if (matcher.find()) {
                            int startIndex = Integer.parseInt(matcher.group("startIndex"));
                            int endIndex = Integer.parseInt(matcher.group("endIndex"));
                            int gapIndex = Integer.parseInt(matcher.group("gapIndex"));
                            endIndex = endIndex == -1 ? invokes.size() : endIndex;
                            for (int index = startIndex; index < endIndex; ) {
                                tx.run("merge(shpolicy:Shope_policy{name:" + name + ",shuxing:" + invokes.get(index) + "} )");
                                tx.run("match(shpolicy:Shope_policy{name:" + name + ",shuxing:" + invokes.get(index) + "}),(sf:XCHotel{hotel_id:" + hotel_id + ",Shope_name:" + xichotel.getShop_name() + ",Shop_address:" + xichotel.getShop_address() + ",Shop_sepcific_introduce:" + Shop_sepcific_introduce + ",shope_url:" + xichotel.getShop_url() + ",data_website:" + xichotel.getData_website() + ",shop_rate:" + xichotel.getShop_rate() + "}) merge(sf)-[:hotlPolicyRelationship {name:”宾馆包含的政策”}]->(shpolicy)");
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
                                    tx.run("merge(sfm:Shope_around_facility{name:" + name + ",shuxing:" + str + "})");
                                    tx.run("match(sfmt:Shope_around_facility{name:" + name + ",shuxing:" + str + "}),(xsf:XCHotel{hotel_id:" + hotel_id + ",Shope_name:" + xichotel.getShop_name() + ",Shop_address:" + xichotel.getShop_address() + ",Shop_sepcific_introduce:" + Shop_sepcific_introduce + ",shope_url:" + xichotel.getShop_url() + ",data_website:" + xichotel.getData_website() + ",shop_rate:" + xichotel.getShop_rate() + "}) merge(xsf)-[:ShopeAroundFacilityRelationship {name:”宾馆周边设施”}]->(sfmt)");
                                }
                            }
                        }
                    }

                    //住客点评
                    List<String> impressions = statistics.getImpressions();



                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException ex) {
                    ex.printStackTrace();
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }


                return null;
            }
        });
    }








}
