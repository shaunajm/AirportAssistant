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

    public Button btBeginJourney;
    private FirebaseAuth mAuth;
    public EditText editBookedWeight;
    public EditText editActualWeight;
    public String resultBookedWeight;
    public String resultActualWeight;
    public DatabaseReference mUserRef;
    public String checkBagChoice;
    public RadioGroup modeOptions;
    public TextView tvBookedWeight;
    public TextView tvActualWeight;
    public TextView tvBKG;
    public TextView tvAKG;

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
        modeOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                onRadioButtonClicked(i);
            }
        });

        editBookedWeight = (EditText)findViewById(R.id.bookedWeightNumber);
        editBookedWeight.setVisibility(View.INVISIBLE);
        tvBookedWeight = (TextView)findViewById(R.id.bookedWeightQuestion);
        tvBookedWeight.setVisibility(View.INVISIBLE);
        tvBKG = (TextView)findViewById(R.id.bookedWeightKG);
        tvBKG.setVisibility(View.INVISIBLE);
        editActualWeight = (EditText)findViewById(R.id.actualWeightNumber);
        editActualWeight.setVisibility(View.INVISIBLE);
        tvActualWeight = (TextView)findViewById(R.id.actualWeightQuestion);
        tvActualWeight.setVisibility(View.INVISIBLE);
        tvAKG = (TextView)findViewById(R.id.bookedWeightKG2);
        tvAKG.setVisibility(View.INVISIBLE);

        btBeginJourney = (Button) findViewById(R.id.btBeginJourney);
        btBeginJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modeOptions.getCheckedRadioButtonId() != -1) {
                    onRadioButtonClicked(modeOptions.getCheckedRadioButtonId());
                    setCheckInChoice();
                    if (checkBagChoice.equals("Yes")) {
                        if (TextUtils.isEmpty(editBookedWeight.getText())) {
                            editBookedWeight.setError("Booked Weight is required!");
                        } else if (TextUtils.isEmpty(editActualWeight.getText())) {
                            editActualWeight.setError("Actual Weight is required!");
                            setWeightValues();
                        } else {
                            setWeightValues();
                            setCheckInChoice();
                            Intent i = new Intent(HoldLuggageQues.this, ConfirmJourney.class);
                            startActivity(i);
                            finish();
                        }

                    } else {
                        setCheckInChoice();
                        Intent i = new Intent(HoldLuggageQues.this, ConfirmJourney.class);
                        startActivity(i);
                        finish();
                    }
                }
                else {
                    Toast.makeText(HoldLuggageQues.this, "Please select if you will be checking in luggage.",
                            Toast.LENGTH_LONG).show();
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

    public void setCheckInChoice(){
        FirebaseUser user = mAuth.getCurrentUser();
        if (checkBagChoice.equals("Yes")) {
            mUserRef.child(user.getUid()).child("checkInChoice").setValue("True");
        }
        else{
            mUserRef.child(user.getUid()).child("checkInChoice").setValue("False");
        }

    }

    public void onRadioButtonClicked(int id) {
        // Is the button now checked?
        RadioButton checkedButton = (RadioButton) findViewById(id);

        // Check which radio button was clicked
        switch(checkedButton.getText().toString()) {
            case "Yes":
                editBookedWeight.setVisibility(View.VISIBLE);
                tvBookedWeight.setVisibility(View.VISIBLE);
                tvBKG.setVisibility(View.VISIBLE);
                editActualWeight.setVisibility(View.VISIBLE);
                tvActualWeight.setVisibility(View.VISIBLE);
                tvAKG.setVisibility(View.VISIBLE);
                checkBagChoice ="Yes";
                break;
            case "No":
                editBookedWeight.setVisibility(View.INVISIBLE);
                tvBookedWeight.setVisibility(View.INVISIBLE);
                tvBKG.setVisibility(View.INVISIBLE);
                editActualWeight.setVisibility(View.INVISIBLE);
                tvActualWeight.setVisibility(View.INVISIBLE);
                tvAKG.setVisibility(View.INVISIBLE);
                checkBagChoice ="No";
                break;
            default:
                checkBagChoice = null;
        }
    }
}
