package com.knowledge.Utils;


//计算地址和距离

import com.alibaba.fastjson.JSON;
import com.knowledge.Utils.CommonUtilsPackage.ConnectionPoolFactory;
import com.knowledge.Utils.CommonUtilsPackage.LogsUtils;
import org.neo4j.driver.v1.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testUtils  {


    public static void main(String... args) throws InterruptedException, IOException {
        tempt();

    }

    public  static  void tempt() {

        final ArrayList<CacluDistanceBeans> cacluDistanceBeans = new ArrayList<>();


        Driver driverInfo = ConnectionPoolFactory.getDriverInfo(Thread.currentThread().getName());

        final Session session = driverInfo.session();


        //计算所有数据
//        session.writeTransaction(new TransactionWork<Object>() {
//
//            @Override
//            public Object execute(Transaction transaction) {
           Transaction transaction = session.beginTransaction();
                try {

                    StatementResult run = transaction.run("match(n:Shopping) return id(n) as id , n.shop_name as shop_name,n.latitude as latitude, n.longitude as longitude");
                    while (run.hasNext()) {
                        Record next = run.next();
                        String id = next.get("id").toString();
                        String shop_name = next.get("shop_name").toString();
                        String latitude = next.get("latitude").toString().replace("\"","");
                        String longitude = next.get("longitude").toString().replace("\"","");
                        System.out.println(id + " " + shop_name + " " + latitude + " " + longitude);
                        cacluDistanceBeans.add(new CacluDistanceBeans(id, shop_name, Double.parseDouble(longitude), Double.parseDouble(latitude)));

                    }

                    transaction.success();
                    transaction.close();

                    transaction = session.beginTransaction();
                    System.out.println("XCHotel");
                    StatementResult XCHotel = transaction.run("match(n:XCHotel) return id(n) as id , n.Shope_name as shop_name,n.latitude as latitude, n.longitude as longitude");
                    while (XCHotel.hasNext()) {
                        Record next = XCHotel.next();
                        String id = next.get("id").toString();
                        String shop_name = next.get("shop_name").toString();
                        String latitude = next.get("latitude").toString().replace("\"","");
                        String longitude = next.get("longitude").toString().replace("\"","");
                        System.out.println(id + " " + shop_name + " " + latitude + " " + longitude);
                        cacluDistanceBeans.add(new CacluDistanceBeans(id, shop_name,Double.parseDouble(longitude), Double.parseDouble(latitude)));
                    }
                    System.out.println("Catering!");

                    transaction.success();
                    transaction.close();
                    transaction = session.beginTransaction();
                    StatementResult Catering = transaction.run("match(n:Catering) return id(n) as id , n.shop_name as shop_name,n.latitude as latitude, n.longitude as longitude");
                    while (XCHotel.hasNext()) {
                        Record next = Catering.next();
                        String id = next.get("id").toString();
                        String shop_name = next.get("shop_name").toString();
                        if (next.get("latitude") == null) continue;
                        String latitude = next.get("latitude").toString().replace("\"","");
                        String longitude = next.get("longitude").toString().replace("\"","");
                        System.out.println(id + " " + shop_name + " " + latitude + " " + longitude);
                        cacluDistanceBeans.add(new CacluDistanceBeans(id, shop_name,Double.parseDouble(longitude), Double.parseDouble(latitude)));
                    }
                    transaction.success();
                    transaction.close();

                    System.out.println("Entertainment!");

                    transaction = session.beginTransaction();
                    StatementResult Entertainment = transaction.run("match(n:Entertainment) return id(n) as id , n.shop_name as shop_name,n.latitude as latitude, n.longitude as longitude");
                    while (Entertainment.hasNext()) {
                        Record next = Entertainment.next();
                        String id = next.get("id").toString();
                        String shop_name = next.get("shop_name").toString();
                        String latitude = next.get("latitude").toString().replace("\"","");
                        String longitude = next.get("longitude").toString().replace("\"","");
                        System.out.println(id + " " + shop_name + " " + latitude + " " + longitude);
                        cacluDistanceBeans.add(new CacluDistanceBeans(id, shop_name, Double.parseDouble(longitude), Double.parseDouble(latitude)));
                    }

                    transaction.success();
                    transaction.close();
                    transaction = session.beginTransaction();
                    System.out.println("Sight!");
                    StatementResult Sight = transaction.run("match(n:Sight) return id(n) as id , n.shop_name as shop_name,n.latitude as latitude, n.longitude as longitude");
                    while (Sight.hasNext()) {
                        Record next = Sight.next();
                        String id = next.get("id").toString();
                        String shop_name = next.get("shop_name").toString();
                        String latitude = next.get("latitude").toString().replace("\"","");
                        String longitude = next.get("longitude").toString().replace("\"","");
                        System.out.println(id + " " + shop_name + " " + latitude + " " + longitude);
                        cacluDistanceBeans.add(new CacluDistanceBeans(id, shop_name, Double.parseDouble(longitude), Double.parseDouble(latitude)));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }finally {
                    transaction.success();
                    transaction.close();
                }

//        System.out.println(cacluDistanceBeans.size());
//         driverInfo = ConnectionPoolFactory.getDriverInfo(Thread.currentThread().getName());
        System.out.println(cacluDistanceBeans.size());

         transaction= session.beginTransaction();
//        session.writeTransaction(new TransactionWork<Object>() {
//
//            @Override
//            public Object execute(Transaction transaction) {


                for (int i = 0; i < cacluDistanceBeans.size(); i++) {

                    for (int j = i + 1; j < cacluDistanceBeans.size(); j++) {
                        CacluDistanceBeans before = cacluDistanceBeans.get(i);
                        CacluDistanceBeans after = cacluDistanceBeans.get(j);




                        //骑行
                        String url = "http://api.map.baidu.com/routematrix/v2/";

                        StatementResult run = transaction.run("match(n)-[r:ridingDistance]-(m) where id(n)=" + before.getId() + " and id(m)=" + after.getId() + " return r");

                        if (!run.hasNext()) {

                            try {
                                String ridingUrl = url + "riding?" + "output=json&origins=" + before.getLatitude() + "," + before.getLongitude() + "&destinations=" + after.getLatitude() + "," + after.getLongitude() + "&ak=kpwTOTTQzFF7yvdtqhieuG3zpzwLANeW";

                                System.out.println(ridingUrl);
                                URL url1 = new URL(ridingUrl);
                                URLConnection connection = url1.openConnection();
                                InputStream inputStream =
                                        connection.getInputStream();
                                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                String s = bufferedReader.readLine();
                                System.out.println(s);
                                ThreeDistanceBeans mapBeans = JSON.parseObject(s, ThreeDistanceBeans.class);

                                if (mapBeans.getStatus() == 0) {

                                    Locations locations = mapBeans.getResult()[0];
                                    Discription distance = locations.getDistance();
                                    Discription duration = locations.getDuration();
                                    System.out.println("match(n),(m) where id(n)=" + before.getId() + " and id(m)=" + after.getId() + " merge (n)-[r:ridingDistance{distance:\"" + distance.getText() + "-" + distance.getValue() + "\",time:\"" + duration.getText() + "-" + duration.getValue() + "\"}]-(m) ");
                                    transaction.run("match(n),(m) where id(n)=" + before.getId() + " and id(m)=" + after.getId() + " merge (n)-[r:ridingDistance{distance:\"" + distance.getText() + "-" + distance.getValue() + "\",time:\"" + duration.getText() + "-" + duration.getValue() + "\"}]-(m) ");
                                }else{

                                    LogsUtils.WriteTheDataToFile(before.getId()+"  "+after.getId() + "\n" + "\n\n", "src/resources/ridingStatus.txt");
                                    continue;
                                }
                            } catch (Exception ex) {
                                LogsUtils.WriteTheDataToFile(before.getId()+"  "+after.getId() + "\n" +ex.getMessage()+ "\n\n", "src/resources/ridingError.txt");

                            }finally {

                            }

                        }




                        //http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=40.45,116.34&destinations=40.34,116.45&ak=6L6IwXtvhtO0km9sL7xhrMzYtd0XT3jc

                         run = transaction.run("match(n)-[r:walkingDistance]-(m) where id(n)=" + before.getId() + " and id(m)=" + after.getId() + " return r");

                        if (!run.hasNext()) {

                            try {
                                String waljinggUrl = url + "walking?" + "output=json&origins=" + before.getLatitude() + "," + before.getLongitude() + "&destinations=" + after.getLatitude() + "," + after.getLongitude() + "&ak=kpwTOTTQzFF7yvdtqhieuG3zpzwLANeW";

                                URL url1 = new URL(waljinggUrl);
                                URLConnection connection = url1.openConnection();
                                InputStream inputStream =
                                        connection.getInputStream();
                                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                String s = bufferedReader.readLine();
                                System.out.println(s);
                                ThreeDistanceBeans mapBeans = JSON.parseObject(s, ThreeDistanceBeans.class);

                                if (mapBeans.getStatus() == 0) {

                                    Locations locations = mapBeans.getResult()[0];
                                    Discription distance = locations.getDistance();
                                    Discription duration = locations.getDuration();
                                    System.out.println("match(n),(m) where id(n)=" + before.getId() + " and id(m)=" + after.getId() + " merge (n)-[r:walkingDistance{distance:\"" + distance.getText() + "-" + distance.getValue() + "\",time:\"" + duration.getText() + "-" + duration.getValue() + "\"}]-(m) ");
                                    transaction.run("match(n),(m) where id(n)=" + before.getId() + " and id(m)=" + after.getId() + " merge (n)-[r:walkingDistance{distance:\"" + distance.getText() + "-" + distance.getValue() + "\",time:\"" + duration.getText() + "-" + duration.getValue() + "\"}]-(m) ");
                                }else{

                                    LogsUtils.WriteTheDataToFile(before.getId()+"  "+after.getId() + "\n" + "\n\n", "src/resources/walkingStatus.txt");
                                    continue;
                                }
                            } catch (Exception ex) {
                                LogsUtils.WriteTheDataToFile(before.getId()+"  "+after.getId() + "\n" +ex.getMessage()+ "\n\n", "src/resources/walkingError.txt");

                            }finally {

                            }
                        }




                        run = transaction.run("match(n)-[r:walkingDistance]-(m) where id(n)=" + before.getId() + " and id(m)=" + after.getId() + " return r");

                        if (!run.hasNext()) {

                            try {
                                String waljinggUrl = url + "driving?" + "output=json&origins=" + before.getLatitude() + "," + before.getLongitude() + "&destinations=" + after.getLatitude() + "," + after.getLongitude() + "&ak=kpwTOTTQzFF7yvdtqhieuG3zpzwLANeW";

                                URL url1 = new URL(waljinggUrl);
                                URLConnection connection = url1.openConnection();
                                InputStream inputStream =
                                        connection.getInputStream();
                                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                String s = bufferedReader.readLine();
                                System.out.println(s);
                                ThreeDistanceBeans mapBeans = JSON.parseObject(s, ThreeDistanceBeans.class);

                                if (mapBeans.getStatus() == 0) {

                                    Locations locations = mapBeans.getResult()[0];
                                    Discription distance = locations.getDistance();
                                    Discription duration = locations.getDuration();
                                    System.out.println("match(n),(m) where id(n)=" + before.getId() + " and id(m)=" + after.getId() + " merge (n)-[r:drivingDistance{distance:\"" + distance.getText() + "-" + distance.getValue() + "\",time:\"" + duration.getText() + "-" + duration.getValue() + "\"}]-(m) ");
                                    transaction.run("match(n),(m) where id(n)=" + before.getId() + " and id(m)=" + after.getId() + " merge (n)-[r:drivingDistance{distance:\"" + distance.getText() + "-" + distance.getValue() + "\",time:\"" + duration.getText() + "-" + duration.getValue() + "\"}]-(m) ");
                                }else{

                                    LogsUtils.WriteTheDataToFile(before.getId()+"  "+after.getId() + "\n" + "\n\n", "src/resources/drivingStatus.txt");
                                    continue;
                                }
                            } catch (Exception ex) {
                                LogsUtils.WriteTheDataToFile(before.getId()+"  "+after.getId() + "\n" +ex.getMessage()+ "\n\n", "src/resources/drivingError.txt");
                            }finally {
                                transaction.success();
                                transaction.close();
                                transaction = session.beginTransaction();
                            }

                        }

                    }
                }
                transaction.success();
                transaction.close();
//                return null;
//            }
//        });
        session.close();
        ConnectionPoolFactory.close();
    }


    public  static  void CopewithCateringtxtFile() throws  Exception {
        String str = "src/resources/Catering.txt";
        Pattern compile = Pattern.compile("地址解析格式有问题!!!\"(?<shopname>.*)\" (?<longitude>[0-9]+\\.[0-9]+),(?<latitude>[0-9]+\\.[0-9]+)");

        File file = new File(str);
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String buf = "";
            while ((buf = reader.readLine()) != null) {
                Matcher matcher = compile.matcher(buf);
                if (matcher.find()) {
                    System.out.println(matcher.group("shopname")+" "+matcher.group("longitude")+" "+matcher.group("latitude"));

                    final String shopname = matcher.group("shopname");
                    final String longitude = matcher.group("longitude");
                    final String latitude = matcher.group("latitude");

                    Driver driverInfo = ConnectionPoolFactory.getDriverInfo(Thread.currentThread().getName());

                    Session session = driverInfo.session();
                    session.writeTransaction(new TransactionWork<Object>() {
                        @Override
                        public Object execute(Transaction transaction) {
                            transaction.run("MATCH (n:Catering{shop_name:\"" + shopname + "\"}) set n.longitude=\"" + longitude + "\" ,n.latitude=\"" + latitude + "\"  RETURN n");
                            transaction.success();
                            transaction.close();
                            return null;
                        }

                    });
                    session.close();
                    ConnectionPoolFactory.close();
                }
            }


        }else{
            throw new RuntimeException("文件不存在");
        }
    }



    public  static  void CacluteLng() {
        Driver driverInfo = ConnectionPoolFactory.getDriverInfo(Thread.currentThread().getName());
        final Session session =
                driverInfo.session();


        try {
            session.writeTransaction(new TransactionWork<Object>() {//6L6IwXtvhtO0km9sL7xhrMzYtd0XT3jc
                int count = 0;
                @Override
                public Object execute(Transaction transaction) {
                    StatementResult run = transaction.run("match(n:Shopping) return n.shop_name as shop_name,n.shop_address as shop_address");
                    while (run.hasNext()) {
                        try {
                            String url = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=6L6IwXtvhtO0km9sL7xhrMzYtd0XT3jc&city=";
                            String city = URLEncoder.encode("杭州市", "utf-8");
                            Record next = run.next();
                            String shop_name = next.get("shop_name").toString();
                            String shop_address = next.get("shop_address").toString();
                            System.out.println(shop_name + " " + shop_address + " " + ++count);
                            url += city + "&";
                            url += "address=" + URLEncoder.encode(shop_address, "utf-8");
                            System.out.println(url);
                            URL url1 = new URL(url);
                            URLConnection connection = url1.openConnection();
                            InputStream inputStream =
                                    connection.getInputStream();
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            String s = bufferedReader.readLine();
                            System.out.println(s);
                            MapBeans mapBeans = JSON.parseObject(s, MapBeans.class);
                            System.out.println(mapBeans);
                            if (mapBeans == null || mapBeans.getStatus() != 0) {
                                throw new RuntimeException("地址解析格式有问题!!!" + shop_name);
                            }
                            Location location = mapBeans.getResult().location;
                            System.out.println("MATCH (n:Shopping{shop_name:" + shop_name + "}) set n.longitude=\"" + location.lng + "\" ,n.latitude=\"" + location.lat + "\"  RETURN n");
                            transaction.run("MATCH (n:Shopping{shop_name:" + shop_name + "}) set n.longitude=\"" + location.lng + "\" ,n.latitude=\"" + location.lat + "\"  RETURN n");
                        } catch (RuntimeException ex) {
                            LogsUtils.WriteTheDataToFile(ex.getMessage(), "src/resources/Shopping.txt");
                        } catch (Exception ex) {
                        } finally {
                        }
                    }
                    transaction.success();
                    transaction.close();
                    return null;
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            session.close();
            ConnectionPoolFactory.close();
        }
    }
}
