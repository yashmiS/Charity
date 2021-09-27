package com.example.anonymouscharityapp;
public class Req {

    public String requestTitle,requestLocation,requestStartDate,requestPerson,requestContact,requestDescription,requestEmail;

    public Req(){
    }
    public Req(String requestTitle,String requestLocation,String requestStartDate,String requestPerson,String requestContact,String requestDescription,String requestEmail){
        this.requestTitle = requestTitle;
        this.requestLocation = requestLocation;
        this.requestStartDate = requestStartDate;
        this.requestPerson = requestPerson;
        this.requestContact = requestContact;
        this.requestDescription = requestDescription;
        this.requestEmail = requestEmail;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public String getRequestLocation() {
        return requestLocation;
    }

    public void setRequestLocation(String requestLocation) {
        this.requestLocation = requestLocation;
    }

    public String getRequestStartDate() {
        return requestStartDate;
    }

    public void setRequestStartDate(String requestStartDate) {
        this.requestStartDate = requestStartDate;
    }

    public String getRequestPerson() {
        return requestPerson;
    }

    public void setRequestPerson(String requestPerson) {
        this.requestPerson = requestPerson;
    }

    public String getRequestContact() {
        return requestContact;
    }

    public void setRequestContact(String requestContact) {
        this.requestContact = requestContact;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getRequestEmail() {
        return requestEmail;
    }

    public void setRequestEmail(String requestEmail) {
        this.requestEmail = requestEmail;
    }


}