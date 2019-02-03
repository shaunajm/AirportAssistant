package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class HoldLuggageQues extends AppCompatActivity {

    public Button btSaveJourney;
    public Button btBeginJourney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold_luggage_ques);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HoldLuggageQues.this, JourneyUpload.class);
                startActivity(i);
                finish();
            }
        });

        btSaveJourney = (Button) findViewById(R.id.btSaveJourney);
        btSaveJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HoldLuggageQues.this, Home.class);
                //Take in attributes about hold luggage//
                startActivity(i);
                finish();
            }

        });


        btBeginJourney = (Button) findViewById(R.id.btBeginJourney);
        btBeginJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HoldLuggageQues.this, ConfirmJourney.class);
                //Take in attributes about hold luggage//
                startActivity(i);
                finish();
            }

        });

    }
}
