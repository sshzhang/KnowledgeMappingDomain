package com.knowledge.Utils;

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
}
