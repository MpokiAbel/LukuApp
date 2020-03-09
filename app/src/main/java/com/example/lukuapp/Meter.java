package com.example.lukuapp;

public class Meter {


    String sim_no,current_price;
    long Customer_no, meter_no;

    public Meter() {

    }

    public Meter(String sim_no, String current_price, long customer_no, long meter_no) {
        this.sim_no = sim_no;
        this.current_price = current_price;
        Customer_no = customer_no;
        this.meter_no = meter_no;
    }

    public String getSim_no() {
        return sim_no;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public long getCustomer_no() {
        return Customer_no;
    }

    public long getMeter_no() {
        return meter_no;
    }
}
