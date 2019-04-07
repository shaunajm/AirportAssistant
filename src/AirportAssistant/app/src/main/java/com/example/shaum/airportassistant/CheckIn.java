package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;


public class CheckIn extends AppCompatActivity {


    public Button btProgress;
    public DatabaseReference mRootRef;
    public DataSnapshot data;
    public FirebaseAuth mAuth;
    public Button btLogo;
    public String scheduledTime;
    public String flightNumber;
    public String destination;
    public Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SeekBar seekBar = (SeekBar) findViewById(R.id.progressBar);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckIn.this, TransportToAirport1.class);
                startActivity(i);
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot;
                displayBaggageInfo();
                displayFlightInfo();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckIn.this, SecurityARCheck.class);
                startActivity(i);
                finish();
            }

        });



        btLogo = (Button) findViewById(R.id.btlogo);
        btLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast = Toast.makeText(CheckIn.this, "Flight Time: " + scheduledTime + "\nDestination: " + destination + "\nFlight Number: " + flightNumber,
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
                toast.show();
            }
        });

    }

    public void displayBaggageInfo(){
        DataSnapshot user = data.child("users").child(mAuth.getUid());
        if (user != null) {
            String actualWeight = user.child("actualWeight").getValue(String.class);
            String bookedWeight = user.child("bookedWeight").getValue(String.class);

            TextView tv = (TextView) findViewById(R.id.bagWeightVExpected);
            tv.setText("Your bag's weight is: " + actualWeight + " kg" +"\n" +
                       "Your booked weight is: " + bookedWeight + " kg");
        }
    }

    public void displayFlightInfo(){
        DataSnapshot user = data.child("users").child(mAuth.getUid());


        if (user != null) {
            flightNumber = user.child("flightNumber").getValue(String.class);
            DataSnapshot flight = data.child("flight").child(flightNumber);
            destination = flight.child("destination").getValue(String.class);
            String airline = flight.child("airline").getValue(String.class);
            scheduledTime = flight.child("scheduledTime").getValue(String.class);

            TextView tv = (TextView) findViewById(R.id.flightDetails);
            tv.setText("Flight number: " + flightNumber + "\n" +
                    "Destination: " + destination + "\n" +
                    "Airline: " + airline + "\n" +
                    "Flight Time: " + scheduledTime);
        }
    }
}