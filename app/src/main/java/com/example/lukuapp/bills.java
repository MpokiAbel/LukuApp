package com.example.lukuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bills extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Bill> billlist;
    ListView listview ;
    Intent intent;
    String meterno;
    TextView myText;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);

        progressDialog =new ProgressDialog(bills.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Fetching data");
        progressDialog.show();

        myText= findViewById(R.id.nohistoryText);

        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Bills");
        intent=getIntent();
        meterno=intent.getStringExtra("Meter_Number");


        listview=(ListView)findViewById(R.id.bill_list);
        billlist=new ArrayList<>();



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                billlist.clear();



                for(DataSnapshot mysnapshot : dataSnapshot.getChildren()){

                    Bill bill =mysnapshot.getValue(Bill.class);
                    billlist.add(bill);

                }

                list_of_bills adapter =new list_of_bills(bills.this,billlist,meterno);

                listview.setAdapter(adapter);
                if (progressDialog.isShowing())
                    progressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}
