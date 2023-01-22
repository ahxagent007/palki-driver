package com.alphacuetech.xian.palki_driver.ui.LiveBids;

public class dataModel {
    String auction_id, carType, currentLoc, date_, destLoc,from_latLng, roundTrip, running, seatcap, time_,to_latLng, user_id;

    public dataModel(){

    }

    public String getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(String auction_id) {
        this.auction_id = auction_id;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCurrentLoc() {
        return currentLoc;
    }

    public void setCurrentLoc(String currentLoc) {
        this.currentLoc = currentLoc;
    }

    public String getDate_() {
        return date_;
    }

    public void setDate_(String date_) {
        this.date_ = date_;
    }

    public String getDestLoc() {
        return destLoc;
    }

    public void setDestLoc(String destLoc) {
        this.destLoc = destLoc;
    }

    public String getFrom_latLng() {
        return from_latLng;
    }

    public void setFrom_latLng(String from_latLng) {
        this.from_latLng = from_latLng;
    }

    public String getRoundTrip() {
        return roundTrip;
    }

    public void setRoundTrip(String roundTrip) {
        this.roundTrip = roundTrip;
    }

    public String getRunning() {
        return running;
    }

    public void setRunning(String running) {
        this.running = running;
    }

    public String getSeatcap() {
        return seatcap;
    }

    public void setSeatcap(String seatcap) {
        this.seatcap = seatcap;
    }

    public String getTime_() {
        return time_;
    }

    public void setTime_(String time_) {
        this.time_ = time_;
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

    public dataModel(String auction_id, String carType, String currentLoc, String date_, String destLoc, String from_latLng, String roundTrip, String running, String seatcap, String time_, String to_latLng, String user_id) {
        this.auction_id = auction_id;
        this.carType = carType;
        this.currentLoc = currentLoc;
        this.date_ = date_;
        this.destLoc = destLoc;
        this.from_latLng = from_latLng;
        this.roundTrip = roundTrip;
        this.running = running;
        this.seatcap = seatcap;
        this.time_ = time_;
        this.to_latLng = to_latLng;
        this.user_id = user_id;
    }
}
