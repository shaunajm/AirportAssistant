package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.google.maps.model.TravelMode;


public class Arrivals extends AppCompatActivity {


    private Bundle bundle = new Bundle();
    public TravelMode travelMode;
    private RadioGroup modeOptions;
    private RadioGroup yesnoOptions;
    public String yesnoDecision;
    public String address;
    public EditText editAddress;
    public TextView tvWhereTravelling;
    public TextView tvHowTravelling;
    public EditText etWhereTravelling;
    public Button btProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivals);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Arrivals.this, Onboard.class);
                startActivity(i);
                finish();
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.progressBar);

        editAddress = (EditText)findViewById(R.id.address);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        modeOptions = (RadioGroup) findViewById(R.id.radioMode);
        modeOptions.setVisibility(View.INVISIBLE);
        yesnoOptions = (RadioGroup) findViewById(R.id.radioDecision);
        tvWhereTravelling = (TextView) findViewById(R.id.travelFromAirportQuestion);
        tvWhereTravelling.setVisibility(View.INVISIBLE);
        etWhereTravelling = (EditText) findViewById(R.id.address);
        etWhereTravelling.setVisibility(View.INVISIBLE);
        tvHowTravelling = (TextView) findViewById(R.id.travelAirportQuestion);
        tvHowTravelling.setVisibility(View.INVISIBLE);

        yesnoOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                onRadioButtonClicked(i);
            }
        });

        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesnoOptions.getCheckedRadioButtonId() != -1) {
                    onYesNoRadioButtonClicked();
                    if (yesnoDecision.equals("Yes")) {
                        if (modeOptions.getCheckedRadioButtonId() != -1) {

                            onTransportRadioButtonClicked();

                            if( TextUtils.isEmpty(editAddress.getText())){
                                editAddress.setError("Destination is required!");
                            }
                            else {
                                address = editAddress.getText().toString();

                                Intent i = new Intent(Arrivals.this, ArrivalsMap.class);
                                //TAKE IN ATTRIBUTES FROM VARIABLE QUESTIONS//

                                Log.d("mode", travelMode.toString());
                                bundle.putSerializable("mode", travelMode);
                                bundle.putString("address", address);
                                i.putExtras(bundle);
                                startActivity(i);
                                finish();
                            }

                        } else {
                            Toast.makeText(Arrivals.this, "Please select a mode of transport.",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Intent i = new Intent(Arrivals.this, Home.class);
                        //TAKE IN ATTRIBUTES FROM VARIABLE QUESTIONS//
                        startActivity(i);
                        finish();
                    }
                }
                else{
                    Toast.makeText(Arrivals.this, "Please select whether you would like directions.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void onTransportRadioButtonClicked() {
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

    public void onYesNoRadioButtonClicked() {
        // Is the button now checked?
        RadioButton checked = (RadioButton) findViewById(yesnoOptions.getCheckedRadioButtonId());

        // Check which radio button was clicked
        switch(checked.getText().toString()) {
            case "Yes":
                modeOptions.setVisibility(View.VISIBLE);
                tvWhereTravelling.setVisibility(View.VISIBLE);
                etWhereTravelling.setVisibility(View.VISIBLE);
                tvHowTravelling.setVisibility(View.VISIBLE);
                yesnoDecision = "Yes";
                break;
            case "No":
                modeOptions.setVisibility(View.INVISIBLE);
                tvWhereTravelling.setVisibility(View.INVISIBLE);
                etWhereTravelling.setVisibility(View.INVISIBLE);
                tvHowTravelling.setVisibility(View.INVISIBLE);
                yesnoDecision = "No";
                break;
            default:
                yesnoDecision = "No Selection";
                break;
        }
    }
    public void onRadioButtonClicked(int id) {
        // Is the button now checked?
        RadioButton checked = (RadioButton) findViewById(yesnoOptions.getCheckedRadioButtonId());

        // Check which radio button was clicked
        switch (checked.getText().toString()) {
            case "Yes":
                modeOptions.setVisibility(View.VISIBLE);
                tvWhereTravelling.setVisibility(View.VISIBLE);
                etWhereTravelling.setVisibility(View.VISIBLE);
                tvHowTravelling.setVisibility(View.VISIBLE);
                break;
            case "No":
                modeOptions.setVisibility(View.INVISIBLE);
                tvWhereTravelling.setVisibility(View.INVISIBLE);
                etWhereTravelling.setVisibility(View.INVISIBLE);
                tvHowTravelling.setVisibility(View.INVISIBLE);
                break;
            default:
                break;

        }

    }
}
