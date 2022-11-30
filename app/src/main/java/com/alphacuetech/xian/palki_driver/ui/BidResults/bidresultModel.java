package com.alphacuetech.xian.palki_driver.ui.BidResults;


import com.firebase.ui.database.FirebaseRecyclerOptions;

public class bidresultModel {
    // TODO: Implement the ViewModel
    String from,to;
    long auction_id,price;

    public bidresultModel(){

    }
    public bidresultModel(long auction_id, String from, String to, long price) {
        this.auction_id = auction_id;
        this.from = from;
        this.price =price;
        this.to = to;
    }




    public long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(long auction_id) {this.auction_id = auction_id;}

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
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


}