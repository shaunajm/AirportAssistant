package com.example.shaum.airportassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.shaum.airportassistant.R;
import android.content.Intent;


public class ARStart extends AppCompatActivity {

    public Button btProgressScan;
    public Button btSkipStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arstart);

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
