package com.example.lukuapp;

import java.util.Date;

public class Bill {

String price,units,date,meter_no,bill_id;

public Bill(){

}

    public Bill(String price, String units, String date, String meter_no, String Bill_id) {
        this.price = price;
        this.units = units;
        this.date = date;
        this.meter_no = meter_no;
        this.bill_id = Bill_id;
    }


    public String getPrice() {
        return price;
    }

    public String getUnits() {
        return units;
    }

    public String getDate() {
        return date;
    }

    public String getBill_id() {
        return bill_id;
    }

    public String getMeter_no() {
        return meter_no;
    }
}
