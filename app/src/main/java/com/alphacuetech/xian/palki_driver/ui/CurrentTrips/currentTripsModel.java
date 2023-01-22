package com.alphacuetech.xian.palki_driver.ui.CurrentTrips;


public class currentTripsModel {
    // TODO: Implement the ViewModel
    String amount, from,to, auction_id, bid_id, driver_id,to_latLng, user_id;

    public currentTripsModel(){

    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(String auction_id) {
        this.auction_id = auction_id;
    }

    public String getBid_id() {
        return bid_id;
    }

    public void setBid_id(String bid_id) {
        this.bid_id = bid_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getTo_latLng() {
        return to_latLng;
    }

    public void setTo_latLng(String to_latLng) {
        this.to_latLng = to_latLng;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public currentTripsModel(String amount, String auction_id, String bid_id, String driver_id, String from, String to, String to_latLng, String user_id) {
        this.amount = amount;
        this.auction_id = auction_id;
        this.bid_id =bid_id;
        this.driver_id = driver_id;
        this.from = from;
        this.to = to;
        this.to_latLng = to_latLng;
        this.user_id = user_id;
    }




}