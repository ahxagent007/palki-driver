package com.alphacuetech.xian.palki_driver.ui.LiveBids;

public class dataModel {
    String auction_id, date_future, from,from_latLng, round_trip, round_trip_bool, to, to_latLng, user_id, vehicle;

    public dataModel() {
    }

    public dataModel(String auction_id, String date_future, String from, String from_latLng, String round_trip, String round_trip_bool, String to, String to_latLng, String user_id, String vehicle) {
        this.auction_id = auction_id;
        this.date_future = date_future;
        this.from = from;
        this.from_latLng = from_latLng;
        this.round_trip = round_trip;
        this.round_trip_bool = round_trip_bool;
        this.to = to;
        this.to_latLng = to_latLng;
        this.user_id = user_id;
        this.vehicle = vehicle;
    }

    public String getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(String auction_id) {
        this.auction_id = auction_id;
    }

    public String getDate_future() {
        return date_future;
    }

    public void setDate_future(String date_future) {
        this.date_future = date_future;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom_latLng() {
        return from_latLng;
    }

    public void setFrom_latLng(String from_latLng) {
        this.from_latLng = from_latLng;
    }

    public String getRound_trip() {
        return round_trip;
    }

    public void setRound_trip(String round_trip) {
        this.round_trip = round_trip;
    }

    public String getRound_trip_bool() {
        return round_trip_bool;
    }

    public void setRound_trip_bool(String round_trip_bool) {
        this.round_trip_bool = round_trip_bool;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
