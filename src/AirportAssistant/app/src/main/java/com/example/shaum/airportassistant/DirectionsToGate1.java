package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;


public class DirectionsToGate1 extends AppCompatActivity {

    public Button btProgress;
    public DatabaseReference mUserRef;
    private FirebaseAuth mAuth;
    public EditText editGateNumber;
    public String resultGateNumber;
    public DatabaseReference mRootRef;
    public DataSnapshot data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_to_gate1);

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

        editGateNumber = (EditText) findViewById(R.id.gateNumberEnter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DirectionsToGate1.this, DutyFreeMap.class);
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
                if (TextUtils.isEmpty(editGateNumber.getText())) {
                    editGateNumber.setError("Gate Number is required!");
                } else {
                    setGateNumber();
                    Intent i = new Intent(DirectionsToGate1.this, DirectionsToGateMap.class);
                    startActivity(i);
                    finish();
                }


            }

            public void setGateNumber() {
                FirebaseUser user = mAuth.getCurrentUser();

                resultGateNumber = editGateNumber.getText().toString();

                mUserRef.child(user.getUid()).child("gateNumber").setValue(resultGateNumber);
            }
        });
    }

    public void displayFlightInfo(){
        DataSnapshot user = data.child("users").child(mAuth.getUid());


        if (user != null) {
            String flightNumber = user.child("flightNumber").getValue(String.class);
            DataSnapshot flight = data.child("flight").child(flightNumber);
            String destination = flight.child("destination").getValue(String.class);
            String airline = flight.child("airline").getValue(String.class);
            String scheduledTime = flight.child("scheduledTime").getValue(String.class);

            TextView tv = (TextView) findViewById(R.id.flightDetails);
            tv.setText("Flight number: " + flightNumber + "\n" +
                    "Destination: " + destination + "\n" +
                    "Airline: " + airline + "\n" +
                    "Flight Time: " + scheduledTime);
        }
    }
}