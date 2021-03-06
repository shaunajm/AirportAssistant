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

public class SecurityARCubeResults extends AppCompatActivity {

    public DatabaseReference mUserRef;
    public FirebaseAuth mAuth;
    public DatabaseReference mRootRef;
    public DataSnapshot data;
    public ARUtil arHelper = new ARUtil();
    public Button btProgress;
    public Button btRepeatScan;
    public Button btLogo;

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
                DataSnapshot user = data.child("users").child(mAuth.getUid());
                if (user != null) {
                    double liquiddimensions1 = user.child("liquiddimensions/2").getValue(Double.class);
                    double liquiddimensions2 = user.child("liquiddimensions/1").getValue(Double.class);
                    double liquiddimensions3 = user.child("liquiddimensions/0").getValue(Double.class);
                    passLiquidSize(liquiddimensions1, liquiddimensions2, liquiddimensions3);
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

        btLogo = (Button) findViewById(R.id.btlogo);
        btLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecurityARCubeResults.this, PopUp.class));
            }
        });
    }

    public void passLiquidSize(double d1, double d2, double d3) {
        double total = arHelper.getCubeVolume(d1, d2, d3);
        boolean checkResult = arHelper.passFailResult(total);
        displayToScreen(checkResult);
    }

    public void displayToScreen(boolean checkResult){
        TextView tv3 = (TextView) findViewById(R.id.tvARResults);
        String result;
        if (checkResult) {
            result = "Pass";
            tv3.setText("Your AR Result: " + result);
        } else {
            result = "Fail";
            tv3.setText("Your AR Result: " + result);
        }
    }
}
