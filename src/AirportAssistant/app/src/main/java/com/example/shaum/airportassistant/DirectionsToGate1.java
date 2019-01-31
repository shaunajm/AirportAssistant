package com.example.shaum.airportassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.shaum.airportassistant.R;
import android.content.Intent;


public class DirectionsToGate1 extends AppCompatActivity {

    public Button btProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_to_gate1);

        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DirectionsToGate1.this, DirectionsToGate2.class);
                // NEED TO TAKE IN GATE NUMBER HERE//
                startActivity(i);
                finish();
            }

        });

    }
}
