package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ConfirmJourney extends AppCompatActivity {

    public Button btstartJourney;
    public Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_journey);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmJourney.this, HoldLuggageQues.class);
                startActivity(i);
                finish();
            }
        });


        btstartJourney = (Button) findViewById(R.id.btstartJourney);
        btstartJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmJourney.this, ARStart.class);
                // SOMETHING IN HERE ABOUT JOURNEY WE STARTING DETAILS TO TAKE IN//
                startActivity(i);
                finish();
            }
        });

        btCancel = (Button) findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmJourney.this, Home.class);
                startActivity(i);
                finish();
            }
        });

    }
}
