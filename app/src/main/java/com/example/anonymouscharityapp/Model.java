package com.example.anonymouscharityapp;

public class Model {
    String names,donations;

    public Model() {
    }

    public Model(String names, String donations) {
        this.names = names;
        this.donations = donations;
    }

    public String getNames() {
        return names;
    }

    public String getDonations() {
        return donations;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setDonations(String donations) {
        this.donations = donations;
    }
}
