package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DirectionsToGate1 extends AppCompatActivity {

    public Button btProgress;
    public DatabaseReference mUserRef;
    private FirebaseAuth mAuth;
    public EditText editGateNumber;
    public String resultGateNumber;

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

        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editGateNumber.getText())) {
                    editGateNumber.setError("Gate Number is required!");
                } else {
                    setGateNumber();
                    Intent i = new Intent(DirectionsToGate1.this, DirectionsToGate2.class);
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
}