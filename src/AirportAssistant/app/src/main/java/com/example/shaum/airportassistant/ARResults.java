package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

public class ARResults extends AppCompatActivity {

    public Button btProgress;
    public Button btRepeatScan;
    public DatabaseReference mUserRef;
    private FirebaseAuth mAuth;
    public DatabaseReference mRootRef;
    public DataSnapshot data;
    public float userhandLuggage1;
    public float userhandLuggage2;
    public float userhandLuggage3;
    public float airlinehandLuggage1;
    public float airlinehandLuggage2;
    public float airlinehandLuggage3;
    public String flightNumber;
    public DatabaseReference mFlightRef;
    public DatabaseReference mAirlineRef;
    public String airline;
    public Float airlinehandluggage1;
    public String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arresults);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SeekBar seekBar = (SeekBar) findViewById(R.id.progressBar);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        mUserRef = FirebaseDatabase.getInstance().getReference("users");

        mAuth = FirebaseAuth.getInstance();

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot;
                passUserHandLuggage();
                getFlightNumber();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARResults.this, ARStart.class);
                startActivity(i);
                finish();
            }
        });


        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARResults.this, TransportToAirport1.class);
                startActivity(i);
                finish();
            }

        });

        btRepeatScan = (Button) findViewById(R.id.btRepeatScan);
        btRepeatScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARResults.this, ARStart.class);
                startActivity(i);
                finish();
            }

        });
    }

    public void passUserHandLuggage(){
        DataSnapshot user = data.child("users").child(mAuth.getUid());
        if (user != null) {
            userhandLuggage1 = user.child("handluggage/2").getValue(Float.class);
            userhandLuggage2 = user.child("handluggage/1").getValue(Float.class);
            userhandLuggage3 = user.child("handluggage/0").getValue(Float.class);
            Log.d("handluggage1", "handluggage1: " + userhandLuggage1);
            Log.d("handluggage2", "handluggage2: " + userhandLuggage2);
            Log.d("handluggage3", "handluggage3: " + userhandLuggage3);
        }
    }

    public void getFlightNumber(){
        DataSnapshot user = data.child("users").child(mAuth.getUid());
        if (user != null) {
            flightNumber = user.child("flightNumber").getValue(String.class);
            getAirline();
        }
    }

    public void getAirline() {
        DataSnapshot flight = data.child("flight").child(flightNumber);
        if (flight != null) {
            airline = flight.child("airline").getValue(String.class);
            passAirlineHandLuggage();
        }
    }

    public void passAirlineHandLuggage() {
        DataSnapshot airlineInfo = data.child("airline").child(airline);
        if(airlineInfo != null){
            airlinehandLuggage1 = airlineInfo.child("handLuggage/0").getValue(Float.class);
            airlinehandLuggage2 = airlineInfo.child("handLuggage/1").getValue(Float.class);
            airlinehandLuggage3 = airlineInfo.child("handLuggage/2").getValue(Float.class);
            Log.d("handluggage1", "handluggageair1: " + airlinehandLuggage1);
            Log.d("handluggage2", "handluggageair2: " + airlinehandLuggage2);
            Log.d("handluggage3", "handluggageair3: " + airlinehandLuggage3);
            passfailResult();
            displayToScreen();
        }
    }

    public void passfailResult(){
        if(userhandLuggage1 <= airlinehandLuggage1 && userhandLuggage2 <= airlinehandLuggage2 && userhandLuggage3 <= airlinehandLuggage3) {
            result = "Pass";
        }
        else{
            result = "Fail";
        }

        Log.d("resultpassfail", "resultpassfail: " + result);
    }

    public void displayToScreen(){

        TextView tv1 = (TextView) findViewById(R.id.yourBagDimensions);
        TextView tv2 = (TextView) findViewById(R.id.airlineBagDimensions);
        TextView tv3 = (TextView) findViewById(R.id.passFailStatus);
        tv1.setText("Your Bag Dimensions: \n" + Math.round(userhandLuggage1) + "cm x " + Math.round(userhandLuggage2) + "cm x " + Math.round(userhandLuggage3) + "cm");
        tv2.setText("Airline Bag Dimensions: \n" + Math.round(airlinehandLuggage1) + "cm x " + Math.round(airlinehandLuggage2) + "cm x " + Math.round(airlinehandLuggage3) + "cm" );
        tv3.setText("Your AR Result: " + result);
    }



}
