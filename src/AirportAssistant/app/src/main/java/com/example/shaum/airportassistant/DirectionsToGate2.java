package com.example.shaum.airportassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.shaum.airportassistant.R;
import android.content.Intent;

public class DirectionsToGate2 extends AppCompatActivity {

    public Button btViewOnMap;
    public Button btProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_to_gate2);

        btViewOnMap = (Button) findViewById(R.id.btViewOnMap);
        btViewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DirectionsToGate2.this, DirectionsToGate3.class);
                startActivity(i);
                finish();
            }

        });

        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DirectionsToGate2.this, BoardingPass.class);
                startActivity(i);
                finish();
            }

        });

    }
}
