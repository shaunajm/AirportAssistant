package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.example.shaum.airportassistant.util.ARUtil;

public class ARResults extends AppCompatActivity {


    public DataSnapshot data;
    public String result;
    public ARUtil arHelper = new ARUtil();
    public FirebaseAuth mAuth;
    public DatabaseReference mUserRef;
    public DatabaseReference mRootRef;
    public Button btRepeatScan;
    public Button btProgress;

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
                DataSnapshot user = data.child("users").child(mAuth.getUid());
                if (user != null) {
                    double userd1 = user.child("handluggage/2").getValue(Double.class);
                    double userd2 = user.child("handluggage/1").getValue(Double.class);
                    double userd3 = user.child("handluggage/0").getValue(Double.class);
                    String flightNumber = user.child("flightNumber").getValue(String.class);
                    getAirline(flightNumber, userd1, userd2, userd3);
                }
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

    public void getAirline(String flightNumber, double userd1, double userd2, double userd3) {
        DataSnapshot flight = data.child("flight").child(flightNumber);
        if (flight != null) {
            String airline = flight.child("airline").getValue(String.class);
            DataSnapshot airlineInfo = data.child("airline").child(airline);
            if(airlineInfo != null){
                double airlined1 = airlineInfo.child("handLuggage/0").getValue(Float.class);
                double airlined2 = airlineInfo.child("handLuggage/1").getValue(Float.class);
                double airlined3 = airlineInfo.child("handLuggage/2").getValue(Float.class);
                boolean checkResult = arHelper.luggageResult(userd1, userd2, userd3, airlined1, airlined2, airlined3);
                displayToScreen(checkResult, userd1, userd2, userd3, airlined1, airlined2, airlined3);
            }
        }
    }


    public void displayToScreen(boolean result, double userd1, double userd2, double userd3, double airlined1, double airlined2, double airlined3){

        TextView tv1 = (TextView) findViewById(R.id.yourBagDimensions);
        TextView tv2 = (TextView) findViewById(R.id.airlineBagDimensions);
        TextView tv3 = (TextView) findViewById(R.id.passFailStatus);
        tv1.setText("Your Bag Dimensions: \n" + Math.round(userd1) + "cm x " + Math.round(userd2) + "cm x " + Math.round(userd3) + "cm");
        tv2.setText("Airline Bag Dimensions: \n" + Math.round(airlined1) + "cm x " + Math.round(airlined2) + "cm x " + Math.round(airlined3) + "cm" );

        if(result){
            tv3.setText("Your AR Result: Pass");
        }
        else{
            tv3.setText("Your AR Result: Fail");
        }
    }
}
