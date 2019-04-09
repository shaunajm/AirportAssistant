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

public class SecurityARCubeResults extends AppCompatActivity {

    public Button btProgress;
    public Button btRepeatScan;
    public DatabaseReference mUserRef;
    private FirebaseAuth mAuth;
    public DatabaseReference mRootRef;
    public DataSnapshot data;
    public float liquiddimensions1;
    public float liquiddimensions2;
    public float liquiddimensions3;
    public float liquiddimensionstotal;
    public String flightNumber;
    public String airline;
    public String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_ar_cube_results);


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
                passLiquidSize();
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
                Intent i = new Intent(SecurityARCubeResults.this, SecurityARCubeGuide.class);
                startActivity(i);
                finish();
            }
        });


        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecurityARCubeResults.this, Security.class);
                startActivity(i);
                finish();
            }

        });

        btRepeatScan = (Button) findViewById(R.id.btRepeatScan);
        btRepeatScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecurityARCubeResults.this, SecurityARCubeScan.class);
                startActivity(i);
                finish();
            }

        });
    }

    public void passLiquidSize(){
        DataSnapshot user = data.child("users").child(mAuth.getUid());
        if (user != null) {
            liquiddimensions1= user.child("liquiddimensions/1").getValue(Float.class);
            liquiddimensions2 = user.child("liquiddimensions/0").getValue(Float.class);
            liquiddimensionstotal = liquiddimensions1*liquiddimensions2*liquiddimensions3;
            Log.d("totalmeasure", "totalmeasureval"+ liquiddimensionstotal);
            passfailResult();
        }

    }



    public void passfailResult(){
        if(liquiddimensionstotal<= 200) {
            result = "Pass";
        }
        else{
            result = "Fail";
        }
        displayToScreen();

    }

    public void displayToScreen(){


        TextView tv3 = (TextView) findViewById(R.id.tvARResults);
        tv3.setText("Your AR Result: " + result);
    }



}
