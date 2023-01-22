package com.alphacuetech.xian.palki_driver.ui.BidHistory;


public class bidhistoryModel {
    // TODO: Implement the ViewModel
    String auctionID,bidAmount,bidID,carType,condition, driverID, rating, seatCap;

    public bidhistoryModel(){

    }


    public String getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(String auctionID) {
        this.auctionID = auctionID;
    }

    public String getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(String bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getBidID() {
        return bidID;
    }

    public void setBidID(String bidID) {
        this.bidID = bidID;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSeatCap() {
        return seatCap;
    }

    public void setSeatCap(String seatCap) {
        this.seatCap = seatCap;
    }

    public bidhistoryModel(String auctionID, String bidAmount, String bidID, String carType, String condition, String driverID, String rating, String seatCap) {

        this.auctionID = auctionID;
        this.bidAmount = bidAmount;
        this.bidID = bidID;
        this.carType = carType;
        this.condition = condition;
        this.driverID = driverID;
        this.rating = rating;
        this.seatCap = seatCap;
    }
}