package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class JourneyUpload extends AppCompatActivity {

    public Button btUploadJourney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_upload);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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



        btUploadJourney = (Button) findViewById(R.id.btUploadJourney);
        btUploadJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JourneyUpload.this, HoldLuggageQues.class);
                //TAKE IN JOURNEY INFO FROM QR CODE//
                startActivity(i);
                finish();
            }

        });

    }
}
