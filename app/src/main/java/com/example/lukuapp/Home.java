package com.example.lukuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Home extends AppCompatActivity {

    TextView textView;
    CardView cardView,cardView_nunua,cardView_salio,cardView_history;
    unit_dialog units;
    buying_dialog buying;
    deposit_dialog deposit;
    FirebaseDatabase database;
    DatabaseReference myRef,myReference;
    Intent intent;
    String current_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Bills");
        cardView_salio=findViewById(R.id.weka_salio);
        cardView_nunua=findViewById(R.id.nunua);
        cardView =findViewById(R.id.unit);
        units=new unit_dialog();
        textView=findViewById(R.id.textView);
        buying=new buying_dialog();
        deposit=new deposit_dialog();
        cardView_history=findViewById(R.id.history);



        intent=getIntent();
        final String meterno=intent.getStringExtra("meter_no");
        current_price=intent.getStringExtra("price");
        textView.setText("Salio Lako ni "+current_price+" tsh");



        myReference=database.getReference("Meter").child(meterno).child("current_price");


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                units.show(getSupportFragmentManager(),"units");
            }
        });


        cardView_nunua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buying.show(getSupportFragmentManager(),"Buying");
            }
        });

    cardView_salio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            deposit.show(getSupportFragmentManager(),"Deposit");
        }
    });


    cardView_history.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(Home.this,bills.class);
            intent.putExtra("Meter_Number",meterno);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    });

    }




    public void transaction(float price){

        myReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                current_price=dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


        float value=Float.valueOf(current_price);

        float units= (float) ((price-(price*0.22))/229.59);

        //Setting the date
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(currentTime);

        intent=getIntent();
        String meterno=intent.getStringExtra("meter_no");

        if (value<price) {
            Toast.makeText(getApplicationContext(),"You dont have enough Funds",Toast.LENGTH_LONG).show();
        }

        else{

            float newvalue=value-price;
            textView.setText("Salio Lako ni "+newvalue+" tsh");
            myReference.setValue(String.valueOf(newvalue));


            String id = myRef.push().getKey();
            Bill bill =new Bill(String.valueOf(price),String.valueOf(units),strDate,meterno,id);
            myRef.child(id).setValue(bill);

        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);


    }
}
