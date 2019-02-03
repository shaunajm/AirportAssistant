package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class ARStart extends AppCompatActivity {

    public Button btProgressScan;
    public Button btSkipStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arstart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARStart.this, ConfirmJourney.class);
                startActivity(i);
                finish();
            }
        });

        btProgressScan = (Button) findViewById(R.id.btProgressScan);
        btProgressScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARStart.this, ARCamera.class);
                startActivity(i);
                finish();
            }

        });

        btSkipStage = (Button) findViewById(R.id.btSkipStage);
        btSkipStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARStart.this, TransportToAirport1.class);
                startActivity(i);
                finish();
            }

        });
    }
}
