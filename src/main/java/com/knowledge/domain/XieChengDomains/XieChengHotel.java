package com.knowledge.domain.XieChengDomains;


import com.knowledge.Annotations.FieldMethodAnnotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//携程酒店数据集
public class XieChengHotel {
    @FieldMethodAnnotation(MethodName = "set_id")
    private String _id;//MongoDB的id
    @FieldMethodAnnotation(MethodName = "setCrawl_time")
    private String crawl_time;//爬取时间
    @FieldMethodAnnotation(MethodName = "setData_region")
    private String data_region;//数据区域
    @FieldMethodAnnotation(MethodName = "setData_source")
    private String data_source;//数据类型 酒店
    @FieldMethodAnnotation(MethodName = "setData_website")
    private String data_website;//数据来源站点
    @FieldMethodAnnotation(MethodName = "setShop_active_status")
    private String shop_active_status;//最新时间
    @FieldMethodAnnotation(MethodName = "setShop_address")
    private String shop_address;//酒店地址
    @FieldMethodAnnotation(MethodName = "setShop_around_facilities")
    private String shop_around_facilities;//酒店周边设施
    @FieldMethodAnnotation(MethodName = "setShop_category_name")
    private String shop_category_name;//酒店类别信息
    @FieldMethodAnnotation(MethodName = "setShop_comment_num",ParameterType =int.class)
    private int shop_comment_num;//酒店评论总数
    @FieldMethodAnnotation(MethodName = "setShop_grade",ParameterType =float.class)
    private float shop_grade;//酒店总体评分
    @FieldMethodAnnotation(MethodName = "setShop_grade_text")
    private String shop_grade_text;//酒店总体评价
    @FieldMethodAnnotation(MethodName = "setShop_id",ParameterType =int.class)
    private int  shop_id;//酒店id
    @FieldMethodAnnotation(MethodName = "setShop_img")
    private String shop_img;//酒店图片地址
    @FieldMethodAnnotation(MethodName = "setShop_intro")
    private  String shop_intro;//酒店介绍
    @FieldMethodAnnotation(MethodName = "setShop_name")
    private String shop_name;//酒店名称
    @FieldMethodAnnotation(MethodName = "setShop_phone")
    private String shop_phone;//酒店电话
    @FieldMethodAnnotation(MethodName = "setShop_price",ParameterType =float.class)
    private float shop_price;//价格
    @FieldMethodAnnotation(MethodName = "setShop_rate")
    private String shop_rate;//酒店类似星际的皇冠数量
    @FieldMethodAnnotation(MethodName = "setShop_room_favourable")
    private String shop_room_favourable;//酒店优惠房型
    @FieldMethodAnnotation(MethodName = "setShop_room_recommend_all")
    private String shop_room_recommend_all;//酒店推荐房型
    @FieldMethodAnnotation(MethodName = "setShop_satisfaction_percent")
    private String shop_satisfaction_percent;//酒店的满意比率
    @FieldMethodAnnotation(MethodName = "setShop_statistics")
    private String shop_statistics;//酒店点评数据
    @FieldMethodAnnotation(MethodName = "setShop_url")
    private String shop_url;//酒店url
    private static String rate_desc[];
    static {
        rate_desc=new String[]{"二星级以下/经济", "三星级/舒适", "四星级/高档", "五星级/豪华","六星级"};
    }
    public XieChengHotel() {
    }
    public XieChengHotel(String _id, String crawl_time, String data_region, String data_source, String data_website, String shop_active_status, String shop_address, String shop_around_facilities, String shop_category_name, int shop_comment_num, float shop_grade, String shop_grade_text, int shop_id, String shop_img, String shop_intro, String shop_name, String shop_phone, float shop_price, String shop_rate, String shop_room_favourable, String shop_room_recommend_all, String shop_satisfaction_percent, String shop_statistics, String shop_url) {
        this._id = _id;
        this.crawl_time = crawl_time;
        this.data_region = data_region;
        this.data_source = data_source;
        this.data_website = data_website;
        this.shop_active_status = shop_active_status;
        this.shop_address = shop_address;
        this.shop_around_facilities = shop_around_facilities;
        this.shop_category_name = shop_category_name;
        this.shop_comment_num = shop_comment_num;
        this.shop_grade = shop_grade;
        this.shop_grade_text = shop_grade_text;
        this.shop_id = shop_id;
        this.shop_img = shop_img;
        this.shop_intro = shop_intro;
        this.shop_name = shop_name;
        this.shop_phone = shop_phone;
        this.shop_price = shop_price;
        this.shop_rate = shop_rate;
        this.shop_room_favourable = shop_room_favourable;
        this.shop_room_recommend_all = shop_room_recommend_all;
        this.shop_satisfaction_percent = shop_satisfaction_percent;
        this.shop_statistics = shop_statistics;
        this.shop_url = shop_url;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCrawl_time() {
        return crawl_time;
    }

    public void setCrawl_time(String crawl_time) {
        this.crawl_time = crawl_time;
    }

    public String getData_region() {
        return data_region;
    }

    public void setData_region(String data_region) {
        this.data_region = data_region;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getData_website() {
        return data_website;
    }

    public void setData_website(String data_website) {
        this.data_website = data_website;
    }

    public String getShop_active_status() {
        return shop_active_status;
    }

    public void setShop_active_status(String shop_active_status) {
        this.shop_active_status = shop_active_status;
    }

    public String getShop_address() {
        //处理特殊字符
        shop_address = shop_address.replace("\"", "'");
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_around_facilities() {
        return shop_around_facilities;
    }

    public void setShop_around_facilities(String shop_around_facilities) {
        this.shop_around_facilities = shop_around_facilities;
    }

    public String getShop_category_name() {
        return shop_category_name;
    }

    public void setShop_category_name(String shop_category_name) {
        this.shop_category_name = shop_category_name;
    }

    public int getShop_comment_num() {
        return shop_comment_num;
    }

    public void setShop_comment_num(int shop_comment_num) {
        this.shop_comment_num = shop_comment_num;
    }

    @Override
    public String toString() {
        return "XieChengHotel{" +
                "_id='" + _id + '\'' +
                ", crawl_time='" + crawl_time + '\'' +
                ", data_region='" + data_region + '\'' +
                ", data_source='" + data_source + '\'' +
                ", data_website='" + data_website + '\'' +
                ", shop_active_status='" + shop_active_status + '\'' +
                ", shop_address='" + shop_address + '\'' +
                ", shop_around_facilities='" + shop_around_facilities + '\'' +
                ", shop_category_name='" + shop_category_name + '\'' +
                ", shop_comment_num=" + shop_comment_num +
                ", shop_grade=" + shop_grade +
                ", shop_grade_text='" + shop_grade_text + '\'' +
                ", shop_id=" + shop_id +
                ", shop_img='" + shop_img + '\'' +
                ", shop_intro='" + shop_intro + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_phone='" + shop_phone + '\'' +
                ", shop_price=" + shop_price +
                ", shop_rate='" + shop_rate + '\'' +
                ", shop_room_favourable='" + shop_room_favourable + '\'' +
                ", shop_room_recommend_all='" + shop_room_recommend_all + '\'' +
                ", shop_satisfaction_percent='" + shop_satisfaction_percent + '\'' +
                ", shop_statistics='" + shop_statistics + '\'' +
                ", shop_url='" + shop_url + '\'' +
                '}';
    }

    public float getShop_grade() {
        return shop_grade;
    }

    public void setShop_grade(float shop_grade) {
        this.shop_grade = shop_grade;
    }

    public String getShop_grade_text() {
        return shop_grade_text;
    }

    public void setShop_grade_text(String shop_grade_text) {
        this.shop_grade_text = shop_grade_text;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    public String getShop_intro() {
        return shop_intro;
    }

    public void setShop_intro(String shop_intro) {
        this.shop_intro = shop_intro;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    public float getShop_price() {
        return shop_price;
    }

    public void setShop_price(float shop_price) {
        this.shop_price = shop_price;
    }

    public String getShop_rate() {
        try{
            if (shop_rate != null &&(!"".equals(shop_rate))) {
                String substring = shop_rate.substring(shop_rate.length() - 1, shop_rate.length());
                int rate = Integer.parseInt(substring);
                rate = rate <= 2 ? 0 : rate - 2;
                return rate_desc[rate];
            }else{
                System.out.println("星级数据缺失:" + this.shop_name);
                return "";
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(shop_rate);

        }
        return shop_rate;
    }

    public void setShop_rate(String shop_rate) {
        this.shop_rate = shop_rate;
    }

    public String getShop_room_favourable() {
        return shop_room_favourable;
    }

    public void setShop_room_favourable(String shop_room_favourable) {
        this.shop_room_favourable = shop_room_favourable;
    }

    public String getShop_room_recommend_all() {
        return shop_room_recommend_all;
    }

    public void setShop_room_recommend_all(String shop_room_recommend_all) {
        this.shop_room_recommend_all = shop_room_recommend_all;
    }

    public String getShop_satisfaction_percent() {
        return shop_satisfaction_percent;
    }

    public void setShop_satisfaction_percent(String shop_satisfaction_percent) {
        this.shop_satisfaction_percent = shop_satisfaction_percent;
    }

    public String getShop_statistics() {
        return shop_statistics;
    }

    public void setShop_statistics(String shop_statistics) {
        this.shop_statistics = shop_statistics;
    }

    public String getShop_url() {
        return shop_url;
    }

    public void setShop_url(String shop_url) {
        this.shop_url = shop_url;
    }


    public static void main(String... args) {

        String str = "\"床型\": \"多床\", \"宽带\": \"免费\", \"房价\": \"¥7556\", \"政策\": \"不可取消立即确认\", \"入住人数\": \"每间最多入住4人\", \"早餐\": \"每天四早\", \"满意度\": \"(\\\"约会春天\\\"情侣套餐) 礼预订满意度 97%\"";
        Pattern compile = Pattern.compile("(\"(nihao)\")+?:\\s(\"(.*?)\")+?");
        Matcher matcher =
                compile.matcher(str);
        matcher.find();
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(4));
        Pattern compile1 = Pattern.compile("(?<=\"满意度\":)\\s\"(.*?)\"");
        Matcher matcher1 = compile1.matcher(str);
        matcher1.find();
        System.out.println(matcher1.group(1));


    }
}
