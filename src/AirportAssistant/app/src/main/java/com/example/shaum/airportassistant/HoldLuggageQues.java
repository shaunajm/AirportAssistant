package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HoldLuggageQues extends AppCompatActivity {

    public Button btSaveJourney;
    public Button btBeginJourney;
    private FirebaseAuth mAuth;
    public EditText editBookedWeight;
    public EditText editActualWeight;
    public String resultBookedWeight;
    public String resultActualWeight;
    public DatabaseReference mUserRef;
    public String checkBagChoice;
    public RadioGroup modeOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold_luggage_ques);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HoldLuggageQues.this, JourneyUpload.class);
                startActivity(i);
                finish();
            }
        });

        mUserRef = FirebaseDatabase.getInstance().getReference("users");

        mAuth = FirebaseAuth.getInstance();

        modeOptions = (RadioGroup) findViewById(R.id.radioMode);

        editBookedWeight = (EditText)findViewById(R.id.bookedWeightNumber);
        editActualWeight = (EditText)findViewById(R.id.actualWeightNumber);



        btSaveJourney = (Button) findViewById(R.id.btSaveJourney);
        btSaveJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HoldLuggageQues.this, Home.class);
                //Take in attributes about hold luggage//

                //add if statement for if radio button no
                setWeightValues();
                startActivity(i);
                finish();
            }

        });


        btBeginJourney = (Button) findViewById(R.id.btBeginJourney);
        btBeginJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modeOptions.getCheckedRadioButtonId() != 0) {
                    onRadioButtonClicked();
                    if (checkBagChoice.equals("Yes")) {
                        if (TextUtils.isEmpty(editBookedWeight.getText())) {
                            editBookedWeight.setError("Booked Weight is required!");
                        } else if (TextUtils.isEmpty(editActualWeight.getText())) {
                            editActualWeight.setError("Actual Weight is required!");
                            setWeightValues();
                        } else {
                            setWeightValues();
                            Intent i = new Intent(HoldLuggageQues.this, ConfirmJourney.class);
                            startActivity(i);
                            finish();
                        }

                    } else {
                        Intent i = new Intent(HoldLuggageQues.this, ConfirmJourney.class);
                        startActivity(i);
                        finish();
                    }
                }
            }


        });

    }

    public void setWeightValues(){
        FirebaseUser user = mAuth.getCurrentUser();

        //add ifs
        if (checkBagChoice.equals("Yes")) {
            resultBookedWeight = editBookedWeight.getText().toString();
            resultActualWeight = editActualWeight.getText().toString();
            mUserRef.child(user.getUid()).child("bookedWeight").setValue(resultBookedWeight);
            mUserRef.child(user.getUid()).child("actualWeight").setValue(resultActualWeight);
        }
    }

    public void onRadioButtonClicked() {
        // Is the button now checked?
        RadioButton checkedButton = (RadioButton) findViewById(modeOptions.getCheckedRadioButtonId());

        // Check which radio button was clicked
        switch(checkedButton.getText().toString()) {
            case "Yes":
                checkBagChoice ="Yes";
                break;
            case "No":
                checkBagChoice ="No";
                break;
            default:
                checkBagChoice = null;
        }
    }
}
