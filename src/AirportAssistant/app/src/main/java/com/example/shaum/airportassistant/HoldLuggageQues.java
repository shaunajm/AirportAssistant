package com.example.shaum.airportassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.shaum.airportassistant.R;
import android.content.Intent;

public class HoldLuggageQues extends AppCompatActivity {

    public Button btSaveJourney;
    public Button btBeginJourney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold_luggage_ques);

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
