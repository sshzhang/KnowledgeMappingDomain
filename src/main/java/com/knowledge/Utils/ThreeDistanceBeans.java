package com.knowledge.Utils;

public class ThreeDistanceBeans {

    private int status;

    private Locations[] result;

    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Locations[] getResult() {
        return result;
    }

    public void setResult(Locations[] result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

class  Locations{

    private Discription distance;

    private Discription duration;

    public Discription getDistance() {
        return distance;
    }

    public void setDistance(Discription distance) {
        this.distance = distance;
    }

    public Discription getDuration() {
        return duration;
    }

    public void setDuration(Discription duration) {
        this.duration = duration;
    }
}


class  Discription{

    private String text;

    private String value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
