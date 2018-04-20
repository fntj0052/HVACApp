package com.mhsm0034.fntj0052.hvac;

public class Results2 {

    private String id,temp,timestamp;

    public Results2(String id, String temp, String timestamp){

        this.setId(id);
        this.setTemp(temp);
        this.setTimestamp(timestamp);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
