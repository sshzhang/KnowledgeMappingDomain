package com.knowledge.Utils.Neo4jUtilsPackage;

import com.knowledge.Utils.CommonUtilsPackage.LogsUtils;
import com.knowledge.domain.DianpingCateringApplicationDomain;
import com.knowledge.domain.dazhongdianpingDomains.dianpingcatering.*;
import org.neo4j.driver.v1.*;

import java.beans.ExceptionListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DianpingCateringNeo4jUtils extends Neo4jUtils<String, DianpingCateringApplicationDomain> {

    public DianpingCateringNeo4jUtils(String CommenT, DianpingCateringApplicationDomain allStaticContent, int status) {
        super(CommenT, allStaticContent, status);
    }

    @Override
    protected boolean CreateApplicationCommentDataToNeo4jNode(String CommentT) {
        return false;
    }

    @Override
    protected boolean CreateApplicationStaticContentDataToNeo4jNode(DianpingCateringApplicationDomain allStaticContent) {


        return CreateDianpingCateringDataToNeo4jNode(allStaticContent.getCateringDomain(), allStaticContent.getDianpingshopMenuDomain(), allStaticContent.getShopPromotionDomain(), allStaticContent.getShopStatisticDomain(),allStaticContent.getShop_tags());
    }

    public boolean CreateDianpingCateringDataToNeo4jNode(final CateringDomain cateringDomain, final DianpingshopMenuDomain dianpingshopMenuDomain, final Map<String, DianpingCateringShopPromotionDomain> shopPromotionDomain, final DianpingCateringShopStatisticDomain shopStatisticDomain, final Map<String,Float> shop_tags) {

        Session session = null;
        try {

             session =
                    super.driver.session();
            session.writeTransaction(new TransactionWork<Object>() {

                @Override
                public Object execute(Transaction tx) {
                    String shop_name = cateringDomain.getShop_name();
                    String shop_url = cateringDomain.getShop_url();
                    String subtype_name = cateringDomain.getSubtype_name();
                    String ncrawl_time = cateringDomain.getCrawl_time();
                    String shop_phone = cateringDomain.getShop_phone();
                    float shop_price = cateringDomain.getShop_price();
                    String shop_address = cateringDomain.getShop_address();
                    String shop_time = cateringDomain.getShop_time();
                    String shop_rate = cateringDomain.getShop_rate();
                    String id = cateringDomain.get_id();
                    try {
                        StatementResult run = tx.run("match(sf:Catering{shop_name:\"" + shop_name + "\",shop_url:\"" + shop_url + "\"}) return labels(sf) as label,sf.crawl_time as crawl_time");
                        if (run.hasNext()) {//存在数据记录
                            Record record = run.next();
                            List<Object> labels = record.get("label").asList();
                            if (labels.contains(subtype_name)) {//存在相应的标签记录  看是否更新数据
                                String crawl_time = record.get("crawl_time").toString().replace("\"", "");
                                if (!ncrawl_time.equals(crawl_time)) { //时间不相等就更新
                                    System.out.println("match(sf:Catering{shop_name:\"" + shop_name + "\",shop_url:\"" + shop_url + "\"}) set sf.shop_phone=\"" + shop_phone + "\",sf.crawl_time=\"" + crawl_time + "\",sf.shop_price=" + shop_price + ",sf.shop_address=\"" + shop_address + "\",sf.shop_time=\"" + shop_time + "\",sf.shop_comment_num=" + cateringDomain.getShop_comment_num() + "");
                                    tx.run("match(sf:Catering{shop_name:\"" + shop_name + "\",shop_url:\"" + shop_url + "\"}) set sf.shop_phone=\"" + shop_phone + "\",sf.crawl_time=\"" + crawl_time + "\",sf.shop_price=" + shop_price + ",sf.shop_address=\"" + shop_address + "\",sf.shop_time=\"" + shop_time + "\",sf.shop_comment_num=" + cateringDomain.getShop_comment_num() + "");
                                    //先所有关系节点，再重新构建
                                    tx.run("match(sf:Catering{shop_name:\"" + shop_name + "\",shop_url:\"" + shop_url + "\"})-[r]->(n) delete r,n");
                                    CreateCateringRelationship(tx, shop_name, shop_url, id, shop_tags, dianpingshopMenuDomain, shopPromotionDomain, shopStatisticDomain);
                                }
                            }else{//不存在相应的记录   添加标签类别信息
                                tx.run("match(sf:Catering{shop_name:\""+shop_name+"\",shop_url:\""+shop_url+"\"}) set sf:"+subtype_name+" return sf" );
                            }
                        } else {//不存在数据记录  添加
                            tx.run("create(sf:Catering:" + subtype_name + "{id:\"" + cateringDomain.get_id() + "\",shop_rate:\"" + shop_rate + "\",shop_name:\"" + shop_name + "\",crawl_time:\"" + ncrawl_time + "\",data_website:\"" + cateringDomain.getData_website() + "\",shop_price:" + shop_price + ",data_source:\"" + cateringDomain.getData_source() + "\",data_region:\"" + cateringDomain.getData_region() + "\",shop_url:\"" + shop_url + "\",shop_address:\"" + shop_address + "\",shop_time:\"" + shop_time + "\",shop_phone:\"" + shop_phone + "\"}) return sf");

                            System.out.println("create(sf:Catering:" + subtype_name + "{id:\"" + cateringDomain.get_id() + "\",shop_rate:\"" + shop_rate + "\",shop_name:\"" + shop_name + "\",crawl_time:\"" + ncrawl_time + "\",data_website:\"" + cateringDomain.getData_website() + "\",shop_price:" + shop_price + ",data_source:\"" + cateringDomain.getData_source() + "\",data_region:\"" + cateringDomain.getData_region() + "\",shop_url:\"" + shop_url + "\",shop_address:\"" + shop_address + "\",shop_time:\"" + shop_time + "\",shop_phone:\"" + shop_phone + "\"}) return sf");
                            CreateCateringRelationship(tx, shop_name, shop_url, id, shop_tags, dianpingshopMenuDomain, shopPromotionDomain, shopStatisticDomain);

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    return null;
                }

            });
        } catch (Exception ex) {
          ex.printStackTrace();
            LogsUtils.WriteTheDataToFile(cateringDomain.getShop_url() + "\n" + ex.getMessage() + "\n\n", "/home/xiujiezhang/IdeaProjects/KnowledgeMappingDomain/src/resources/promationrror.txt");

        }finally {
            if (session != null)
            session.close();
        }


        return false;
    }

    /**
     * 构建餐饮关系节点
     * @param tx
     * @param shop_name
     * @param shop_url
     * @param id
     * @param shop_tags
     * @param dianpingshopMenuDomain
     * @param shopPromotionDomain
     * @param shopStatisticDomain
     */
    private void CreateCateringRelationship(Transaction tx, String shop_name, String shop_url, String id, Map<String, Float> shop_tags, DianpingshopMenuDomain dianpingshopMenuDomain, Map<String, DianpingCateringShopPromotionDomain> shopPromotionDomain, DianpingCateringShopStatisticDomain shopStatisticDomain) {
        if (shop_tags != null) {
            Set<String> keys = shop_tags.keySet();
            for (String key : keys) {
                float value = shop_tags.get(key);
                tx.run("create(si:Shope_gust_impression{name:\"" + key + "\",shuxing:" + value + ",id:\"" + id + "\"})");
                System.out.println("create(si:Shope_gust_impression{name:\"" + key + "\",shuxing:" + value + ",id:\"" + id + "\"})");
                System.out.println("match(sf:Catering{shop_name:\"" + shop_name + "\",shop_url:\"" + shop_url + "\"}),(si:Shope_gust_impression{id:\"" + id + "\",name:\"" + key + "\",shuxing:" + value + "}) merge(sf)-[:CateringIncludeShopTags{name:\"餐饮包含的评价标签\"}]->(si) return si");
                tx.run("match(sf:Catering{shop_name:\"" + shop_name + "\",shop_url:\"" + shop_url + "\"}),(si:Shope_gust_impression{id:\"" + id + "\",name:\"" + key + "\",shuxing:" + value + "}) merge(sf)-[:CateringIncludeShopTags{name:\"餐饮包含的评价标签\"}]->(si) return si");
            }
        }

        if (dianpingshopMenuDomain != null) {
            Map<String, DianpingMenuDomain> recommenDishList = dianpingshopMenuDomain.getRecommenDishList();
            if (recommenDishList != null) {
                Set<String> keySet = recommenDishList.keySet();
                for (String key : keySet) {
                    DianpingMenuDomain dianpingMenuDomain = recommenDishList.get(key);
                    String picture = dianpingMenuDomain.getPicture();
                    String price = dianpingMenuDomain.getPrice();
                    String suggestNumber = dianpingMenuDomain.getSuggestNumber();
                    tx.run("create(sm:CateringMenu{name:\"" + key + "\",picture:\"" + picture + "\",price:\"" + price + "\",number:\"" + suggestNumber + "\",id:\"" + id + "\"})");
                    tx.run("match(sf:Catering{shop_name:\"" + shop_name + "\",shop_url:\"" + shop_url + "\"}),(sm:CateringMenu{name:\"" + key + "\",picture:\"" + picture + "\",price:\"" + price + "\",number:\"" + suggestNumber + "\",id:\"" + id + "\"}) merge(sf)-[:CateringIncludeMenu{name:\"餐饮包含的菜单\"}]->(sm)");
                }
            }
            //TODO 环境  价目表
        }

        if (shopPromotionDomain != null) {

            Set<String> keySet = shopPromotionDomain.keySet();
            for (String key : keySet) {
                DianpingCateringShopPromotionDomain dianpingCateringShopPromotionDomain = shopPromotionDomain.get(key);
                String currPrice = dianpingCateringShopPromotionDomain.getCurrPrice();
                String hadSaled = dianpingCateringShopPromotionDomain.getHadSaled();
                String originalPrice = dianpingCateringShopPromotionDomain.getOriginalPrice();
                tx.run("create(ss:CateringPromotion{name:\"" + key + "\",currPrice:\"" + currPrice + "\",originalPrice:\"" + originalPrice + "\",hadSaled:\"" + hadSaled + "\",id:\"" + id + "\"})");
                tx.run("match(sf:Catering{shop_name:\"" + shop_name + "\",shop_url:\"" + shop_url + "\"}),(ss:CateringPromotion{name:\"" + key + "\",currPrice:\"" + currPrice + "\",originalPrice:\"" + originalPrice + "\",hadSaled:\"" + hadSaled + "\",id:\"" + id + "\"}) merge (sf)-[:CateringIncludePromotion{name:\"餐饮包含的促销优惠\"}]->(ss)");
            }
        }
        if (shopStatisticDomain != null) {

            Map<String, List<DianpingCateringPeopleReviewDataDomain>> peopleAllThink = shopStatisticDomain.getPeopleAllThink();
            Map<String, List<DianpingCateringPeopleReviewDataDomain>> reviewInfoDomainMap = shopStatisticDomain.getReviewInfoDomainMap();

            if (peopleAllThink != null && peopleAllThink.size() > 0) {

                List<DianpingCateringPeopleReviewDataDomain> allStrs = peopleAllThink.get("大家认为");
                for (DianpingCateringPeopleReviewDataDomain allstr : allStrs) {
                    String name = allstr.getName();
                    int attribute = allstr.getAttribute();
                    tx.run("create(si:Shope_gust_impression{name:\"" + name + "\",shuxing:" + attribute + ",id:\"" + id + "\"})");
                    tx.run("match(si:Shope_gust_impression{name:\"" + name + "\",shuxing:" + attribute + ",id:\"" + id + "\"}),(sf:Catering{shop_name:\"" + shop_name + "\",shop_url:\"" + shop_url + "\"}) merge (sf)-[:CateringIncludeReviewNum{name:\"餐饮包含的评论个数\"}]->(si)");
                }
            }

            if (reviewInfoDomainMap != null&&reviewInfoDomainMap.size()>0) {
                List<DianpingCateringPeopleReviewDataDomain> reviewDataDomains = reviewInfoDomainMap.get("评价");
                for (DianpingCateringPeopleReviewDataDomain reviewDataDomain : reviewDataDomains) {
                    String name = reviewDataDomain.getName();
                    int attribute = reviewDataDomain.getAttribute();
                    tx.run("create(si:Shope_gust_impression{name:\"" + name + "\",shuxing:\"" + attribute + "\",id:\"" + id + "\"})");
                    tx.run("match(si:Shope_gust_impression{name:\"" + name + "\",shuxing:\"" + attribute + "\",id:\"" + id + "\"}),(sf:Catering{shop_name:\""+shop_name+"\",shop_url:\""+shop_url+"\"}) merge(sf)-[:CateringIncludeReviewNum{name:\"餐饮包含的评论个数\"}]->(si)");
                }
            }
        }
    }


}
