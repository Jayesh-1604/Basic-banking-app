package com.example.pbbank;

public class Customer {
    private String bUserName;
    private String bUserLocation;
    private int bCustomerId;

    public Customer(int customerId,String UserName,String UserLocation){
        this.bUserName = UserName;
        this.bCustomerId = customerId;
        this.bUserLocation = UserLocation;
    }


    public int getbCustomerId() {
        return bCustomerId;
    }

    public String getbUserName(){
        return bUserName;
    }


    public String getbUserLocation() {
        return bUserLocation;
    }
}
