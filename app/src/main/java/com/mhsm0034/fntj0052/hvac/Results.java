package com.mhsm0034.fntj0052.hvac;


public class Results {
    private String id,sound,timestamp;

    public Results(String id, String sound, String timestamp){
        this.setId(id);
        this.setSound(sound);
        this.setTimestamp(timestamp);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
