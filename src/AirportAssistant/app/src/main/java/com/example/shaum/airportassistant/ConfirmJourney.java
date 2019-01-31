package com.example.shaum.airportassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.shaum.airportassistant.R;
import android.content.Intent;

public class ConfirmJourney extends AppCompatActivity {

    public Button btstartJourney;
    public Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_journey);

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
