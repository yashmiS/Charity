package com.example.anonymouscharityapp;

public class Card {


    public String donations, names, cards, dates,cvns;

    public Card(){

    }

    public Card(String donations, String names, String cards, String dates, String cvns){
        this.donations = donations;
        this.names = names;
        this.cards = cards;
        this.dates = dates;
        this.cvns = cvns;
    }

    public String getDonations() {
        return donations;
    }

    public void setDonations(String donations) {
        this.donations = donations;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getCvns() {
        return cvns;
    }

    public void setCvns(String cvns) {
        this.cvns = cvns;
    }
}
