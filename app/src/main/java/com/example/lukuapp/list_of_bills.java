package com.example.lukuapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lukuapp.Bill;
import com.example.lukuapp.R;

import java.util.List;

public class list_of_bills extends ArrayAdapter<Bill>
{
    private Activity context;
    private List<Bill> billList;
    String meter;
    bills myB= (bills) getContext();


public list_of_bills(Activity context,List<Bill> billList,String meter){
    super(context, R.layout.lists,billList);
    this.billList=billList;
    this.context=context;
    this.meter=meter;


}


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listItems =inflater.inflate(R.layout.lists,null,true);

        LinearLayout layout=listItems.findViewById(R.id.myLinearLayout);

        ProgressDialog progressDialog =new ProgressDialog(context);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Fetching data");
        progressDialog.show();
        
        Bill bill =billList.get(position);

        if (meter.equals(bill.getMeter_no())) {
            final TextView bill_id = new TextView(context);
            bill_id.setText("Bill id: "+bill.getBill_id());
            layout.addView(bill_id);

            final TextView bill_date = new TextView(context);
            bill_date.setText("Date: "+bill.getDate());
            layout.addView(bill_date);

            final TextView bill_price = new TextView(context);
            bill_price.setText("Price: " +bill.getPrice());
            layout.addView(bill_price);

            final TextView bill_units = new TextView(context);
            bill_units.setText("Units: "+bill.getUnits());

            layout.addView(bill_units);


        }
        else
            myB.myText.setText("No History!!");


    return listItems;
    }

}
