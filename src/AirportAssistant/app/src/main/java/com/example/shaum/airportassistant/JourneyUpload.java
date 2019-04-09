package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JourneyUpload extends AppCompatActivity {

    public DatabaseReference mUserRef;
    private FirebaseAuth mAuth;
    public EditText editFlightNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_upload);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mUserRef = FirebaseDatabase.getInstance().getReference("users");

        mAuth = FirebaseAuth.getInstance();

        editFlightNumber = (EditText)findViewById(R.id.flightNumber);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i = new Intent(JourneyUpload.this, Home.class);
                startActivity(i);
                finish();
            }
        });



        Button btUploadJourney = (Button) findViewById(R.id.btUploadJourney);
        btUploadJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( TextUtils.isEmpty(editFlightNumber.getText())){
                    editFlightNumber.setError( "Flight Number is required!" );
                }
                else{
                    setFlightNumber();
                    Intent i = new Intent(JourneyUpload.this, HoldLuggageQues.class);
                    startActivity(i);
                    finish();
                }
            }

        });

    }

    public void setFlightNumber(){
        FirebaseUser user = mAuth.getCurrentUser();

        String resultFlightNumber = editFlightNumber.getText().toString();

        mUserRef.child(user.getUid()).child("flightNumber").setValue(resultFlightNumber);
        }
}

