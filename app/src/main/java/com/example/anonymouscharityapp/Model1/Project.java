package com.example.anonymouscharityapp.Model1;

public class Project {
 String  requestDescription,  requestLocation,  requestStartDate, requestTitle;

    public Project() {
    }

    public Project(String requestDescription, String requestLocation, String requestStartDate, String requestTitle) {
        this.requestDescription = requestDescription;
        this.requestLocation = requestLocation;
        this.requestStartDate = requestStartDate;
        this.requestTitle = requestTitle;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
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

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    @Override
    public String toString() {
        return "Project{" +
                "requestDescription='" + requestDescription + '\'' +
                ", requestLocation='" + requestLocation + '\'' +
                ", requestStartDate='" + requestStartDate + '\'' +
                ", requestTitle='" + requestTitle + '\'' +
                '}';
    }
}
