package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ARCamera extends AppCompatActivity {

    public Button btQuitScan;
    public Button btCompleteScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcamera);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARCamera.this, ARStart.class);
                startActivity(i);
                finish();
            }
        });

        btQuitScan = (Button) findViewById(R.id.btQuitScan);
        btQuitScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARCamera.this, ARStart.class);
                startActivity(i);
                finish();
            }

        });

        btCompleteScan = (Button) findViewById(R.id.btCompleteScan);
        btCompleteScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ARCamera.this, ARResults.class);
                startActivity(i);
                finish();
            }

        });

    }
}
