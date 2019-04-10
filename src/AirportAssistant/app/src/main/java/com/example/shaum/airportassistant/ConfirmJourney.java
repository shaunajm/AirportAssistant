package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ConfirmJourney extends AppCompatActivity {


    public String flightNumber;
    public DataSnapshot data;
    public FirebaseAuth mAuth;
    public DatabaseReference mFlightRef;
    public String destination;
    public String airline;
    public String scheduledTime;
    public ProgressBar spinner;
    public DatabaseReference mRootRef;
    public Button btstartJourney;
    public Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_journey);

        ProgressSpinner();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmJourney.this, HoldLuggageQues.class);
                startActivity(i);
                finish();
            }
        });

        mFlightRef = FirebaseDatabase.getInstance().getReference("flight");

        mAuth = FirebaseAuth.getInstance();

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot;
                DataSnapshot user = data.child("users").child(mAuth.getUid());
                if (user != null) {
                    flightNumber = user.child("flightNumber").getValue(String.class);
                    new ScrapeTask().execute();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btstartJourney = (Button) findViewById(R.id.btstartJourney);
        btstartJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmJourney.this, ARStart.class);
                // SOMETHING IN HERE ABOUT JOURNEY WE STARTING DETAILS TO TAKE IN//
                startActivity(i);
                finish();
            }
        });

        btCancel = (Button) findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmJourney.this, Home.class);
                startActivity(i);
                finish();
            }
        });

    }

    public class ScrapeTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect("https://www.dublinairport.com/flight-information/live-departures").get();
                Element table = document.select("tbody").first();
                Elements rows = table.select("tr");

                for (Element row : rows) {
                    Elements columns = row.select("td");
                    if (columns.get(3).text().equals(flightNumber)) {
                        destination = columns.get(1).text();
                        airline = columns.get(2).text();
                        scheduledTime = columns.get(4).text();

                        setFlightValues();

                        return null;
                    }

                }

                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //if you had a ui element, you could display the title
            //((TextView)findViewById (R.id.myTextView)).setText (result);
            spinner.setVisibility(View.GONE);
            TextView tv = (TextView) findViewById(R.id.selectedJourneyInfo);
            tv.setText("Flight Number: " + flightNumber + "\n \n" +
                    "Airline: " + airline + "\n \n" +
                    "Destination: " + destination + "\n \n" +
                    "Flight Time: " + scheduledTime );
        }
    }



    public void setFlightValues(){
        FirebaseUser user = mAuth.getCurrentUser();
            mFlightRef.child(flightNumber).child("destination").setValue(destination);
            mFlightRef.child(flightNumber).child("airline").setValue(airline);
            mFlightRef.child(flightNumber).child("scheduledTime").setValue(scheduledTime);
            mFlightRef.child(flightNumber).child("userID").setValue(user.getUid());
        }


    public void ProgressSpinner(){

        spinner = (ProgressBar)findViewById(R.id.progressBar1);

        spinner.setVisibility(View.VISIBLE);

    }
}

