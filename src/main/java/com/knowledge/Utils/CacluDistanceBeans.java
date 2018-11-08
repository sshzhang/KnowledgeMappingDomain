package com.knowledge.Utils;

import com.knowledge.Utils.CommonUtilsPackage.LogsUtils;

import java.io.*;
import java.net.URL;

public class CacluDistanceBeans {

    private String id;

    private String shop_name;

    private double longitude;

    private double latitude;

    public CacluDistanceBeans() {

    }


    public CacluDistanceBeans(String id, String shop_name, double longitude, double latitude) {
        this.id = id;
        this.shop_name = shop_name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public static void main(String... args) throws IOException {
        InputStream resourceAsStream =
                CacluDistanceBeans.class.getResourceAsStream("/drivingError.txt");

        System.out.println(CacluDistanceBeans.class.getResource("/" + "walkingexclusive.txt").getPath());
        System.out.println(resourceAsStream.available());
        System.out.println(CacluDistanceBeans.class.getProtectionDomain().getCodeSource().getLocation().getPath());

        System.out.println(LogsUtils.class.getResource("/").getPath());
        System.out.println(ClassLoader.getSystemResource("walkingexclusive.txt").getPath());
    }
}
