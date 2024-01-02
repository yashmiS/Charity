package com.example.anonymouscharityapp;

public class ModelComplete {
    String eventName,image;

    public ModelComplete() {
    }

    public ModelComplete(String eventName, String image) {
        this.eventName = eventName;
        this.image = image;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
