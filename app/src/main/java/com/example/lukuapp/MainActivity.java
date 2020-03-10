package com.example.lukuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText number;
    List<Meter>meter_details;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.mybutton);
        number=findViewById(R.id.meterNo);

        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Meter");
        meter_details = new ArrayList<>();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        meter_details.clear();
                        for(DataSnapshot mysnapshot : dataSnapshot.getChildren()){
                            Meter meter =mysnapshot.getValue(Meter.class);
                            meter_details.add(meter);
                        }


                            for(long i=0; i<dataSnapshot.getChildrenCount(); i++){


                                if (number.getText().toString().equals(String.valueOf(meter_details.get((int) i).meter_no))){

                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                    intent.putExtra("meter_no", number.getText().toString().trim());
                                    intent.putExtra("price",meter_details.get((int)i).current_price);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                }
                                else if(number.getText().toString().isEmpty() || number.getText().toString().length()!=11) {
                                    number.setError("Enter Correct Meter Number");
                                }

                            }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        number.setError("Failed to read value.");

                    }
                });

            }
        });



    }




}
