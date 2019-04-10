package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;


public class SecurityARCubeGuide extends AppCompatActivity {

    public Button btProgressScan;
    public Button btSkipStage;
    public Button btLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_ar_cube_guide);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SeekBar seekBar = (SeekBar) findViewById(R.id.progressBar);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecurityARCubeGuide.this, SecurityARShapeCheck.class);
                startActivity(i);
                finish();
            }
        });

        btProgressScan = (Button) findViewById(R.id.btProgressScan);
        btProgressScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecurityARCubeGuide.this, SecurityARCubeScan.class);
                startActivity(i);
                finish();
            }

        });

        btSkipStage = (Button) findViewById(R.id.btSkipStage);
        btSkipStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecurityARCubeGuide.this, Security.class);
                startActivity(i);
                finish();
            }

        });

        btLogo = (Button) findViewById(R.id.btlogo);
        btLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecurityARCubeGuide.this, PopUp.class));
            }
        });
    }
}
