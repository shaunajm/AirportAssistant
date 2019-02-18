package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modeOptions.getCheckedRadioButtonId() != 0) {
                    onRadioButtonClicked();

                    Intent i = new Intent(TransportToAirport1.this, TransportToAirportMap.class);
                    //TAKE IN ATTRIBUTES FROM VARIABLE QUESTIONS//

                    Log.d("mode", travelMode.toString());
                    bundle.putSerializable("mode", travelMode);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(TransportToAirport1.this, "Please select a mode of transport.",
                            Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    public void onRadioButtonClicked() {
        // Is the button now checked?
        RadioButton checked = (RadioButton) findViewById(modeOptions.getCheckedRadioButtonId());

        // Check which radio button was clicked
        switch(checked.getText().toString()) {
            case "Driving":
                travelMode = TravelMode.DRIVING;
                break;
            case "Public Transport":
                travelMode = TravelMode.TRANSIT;
                break;
            case "Walking":
                travelMode = TravelMode.WALKING;
                break;
            case "Cycling":
                travelMode = TravelMode.BICYCLING;
                break;
            default:
                travelMode = TravelMode.UNKNOWN;
                break;
        }
    }
}
