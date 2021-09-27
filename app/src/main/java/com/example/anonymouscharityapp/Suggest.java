package com.example.anonymouscharityapp;

public class Suggest {

    public String fdnames, fdexperiences, fdsuggestions;

    Suggest(){

    }

    public Suggest(String fdnames, String fdexperiences, String fdsuggestions){
        this.fdnames = fdnames;
        this.fdexperiences = fdexperiences;
        this.fdsuggestions = fdsuggestions;
    }

    public String getFdnames() {
        return fdnames;
    }

    public void setFdnames(String fdnames) {
        this.fdnames = fdnames;
    }

    public String getFdexperiences() {
        return fdexperiences;
    }

    public void setFdexperiences(String fdexperiences) {
        this.fdexperiences = fdexperiences;
    }

    public String getFdsuggestions() {
        return fdsuggestions;
    }

    public void setFdsuggestions(String fdsuggestions) {
        this.fdsuggestions = fdsuggestions;
    }
}
