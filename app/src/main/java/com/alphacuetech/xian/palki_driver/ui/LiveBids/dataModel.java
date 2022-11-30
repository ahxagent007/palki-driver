package com.alphacuetech.xian.palki_driver.ui.LiveBids;

import java.util.HashMap;
import java.util.Map;

public class dataModel {
    String from, round_trip, to, user_id, vehicle;
    long auction_id;
    boolean round_trip_bool, date_future;
//    Map from_latLng, to_latLng;
    public HashMap<String, Double> from_latLng;
    public HashMap<String, Double> to_latLng;

    public dataModel() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRound_trip() {
        return round_trip;
    }

    public void setRound_trip(String round_trip) {
        this.round_trip = round_trip;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(long auction_id) {
        this.auction_id = auction_id;
    }

    public boolean isRound_trip_bool() {
        return round_trip_bool;
    }

    public void setRound_trip_bool(boolean round_trip_bool) {
        this.round_trip_bool = round_trip_bool;
    }

    public boolean isDate_future() {
        return date_future;
    }

    public void setDate_future(boolean date_future) {
        this.date_future = date_future;
    }

    public Map getFrom_latLng() {
        return from_latLng;
    }

    public void setFrom_latLng(HashMap<String, Double> from_latLng) {
        this.from_latLng = from_latLng;
    }

    public Map getTo_latLng() {
        return to_latLng;
    }

    public void setTo_latLng(HashMap<String, Double> to_latLng) {
        this.to_latLng = to_latLng;
    }

    public dataModel(long auction_id, boolean date_future, String from, HashMap<String, Double> from_latLng, String round_trip, boolean round_trip_bool, String to, HashMap<String, Double> to_latLng, String user_id, String vehicle) {
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
}
