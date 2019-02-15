package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.google.maps.model.TravelMode;



public class TransportToAirport1 extends AppCompatActivity {

    public Button btProgress;
    private Bundle bundle = new Bundle();
    public TravelMode travelMode;
    private RadioGroup modeOptions ;
    public Button selectMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_to_airport1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransportToAirport1.this, ARResults.class);
                startActivity(i);
                finish();
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.progressBar);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        modeOptions = (RadioGroup) findViewById(R.id.radioMode);

        selectMode = (Button) findViewById(R.id.btProgress);
        selectMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Make sure that the user has selected a break length
                if (modeOptions.getCheckedRadioButtonId() != 0) {
                    onRadioButtonClicked(v);
                } else {
                    Toast.makeText(TransportToAirport1.this, "Please select a mode of transport.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransportToAirport1.this, CheckIn.class);
                //TAKE IN ATTRIBUTES FROM VARIABLE QUESTIONS//
                bundle.putSerializable("mode", travelMode);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }

        });

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioDriving:
                if (checked)
                    travelMode = TravelMode.DRIVING;
                    break;
            case R.id.radioPublicTransport:
                if (checked)
                    travelMode = TravelMode.TRANSIT;
                    break;
            case R.id.radioWalking:
                if (checked)
                    travelMode = TravelMode.WALKING;
                    break;
            case R.id.radioCycling:
                travelMode = TravelMode.BICYCLING;
                    break;
            default:
                travelMode = null;
        }
    }
}
