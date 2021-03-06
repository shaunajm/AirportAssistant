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

public class Onboard extends AppCompatActivity {

    public DatabaseReference mRootRef;
    public DataSnapshot data;
    public FirebaseAuth mAuth;
    public DatabaseReference mUserRef;
    public String flightNumber;
    public String airline;
    public String inflightLink;
    public String destination;
    public Button btProgress;
    public Button btLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Onboard.this,DirectionsToGateMap.class);
                startActivity(i);
                finish();
            }
        });

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
                    String flightNumber = user.child("flightNumber").getValue(String.class);

                    DataSnapshot flight = data.child("flight").child(flightNumber);
                    if (flight != null) {
                        String scheduledTime = flight.child("scheduledTime").getValue(String.class);
                        String airline = flight.child("airline").getValue(String.class);
                        String destination = flight.child("destination").getValue(String.class);

                        DataSnapshot airlineInfo = data.child("airline").child(airline);
                        if (airlineInfo != null) {
                            inflightLink = airlineInfo.child("inflightlink").getValue(String.class);
                            displayFlightTime(scheduledTime, destination);
                            displayInflightLink(inflightLink);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Onboard.this, Arrivals.class);
                startActivity(i);
                finish();
            }

        });

        btLogo = (Button) findViewById(R.id.btlogo);
        btLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Onboard.this, PopUp.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void displayFlightTime(String scheduledTime, String destination){
            TextView tv = (TextView) findViewById(R.id.flightTime);
            tv.setText("Flight Time: " + scheduledTime);
            TextView tv2 = (TextView) findViewById(R.id.destination);
            tv2.setText("Destination: " + destination);
    }



    public void displayInflightLink(String inflightLink){
        TextView tv = (TextView) findViewById(R.id.onboardInfoLink);
        tv.setText(inflightLink);
    }
}
