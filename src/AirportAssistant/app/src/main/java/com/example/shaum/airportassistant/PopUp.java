package com.example.shaum.airportassistant;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

public class PopUp extends Activity {

    public DataSnapshot data;
    public FirebaseAuth mAuth;
    public DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_up_window);

        mAuth = FirebaseAuth.getInstance();

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot;
                DataSnapshot user = data.child("users").child(mAuth.getUid());
                if (user != null) {
                    String flightNumber = user.child("flightNumber").getValue(String.class);
                    DataSnapshot flight = data.child("flight").child(flightNumber);
                    String destination = flight.child("destination").getValue(String.class);
                    String airline = flight.child("airline").getValue(String.class);
                    String scheduledTime = flight.child("scheduledTime").getValue(String.class);

                    displayFlightInfo(flightNumber, destination, airline, scheduledTime);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.83), (int)(height*0.26));
    }

    public void displayFlightInfo(String flightNumber, String destination, String airline, String scheduledTime){
        TextView tv = (TextView) findViewById(R.id.popuptext);
        tv.setText("Flight number: " + flightNumber + "\n" +
                "Destination: " + destination + "\n" +
                "Airline: " + airline + "\n" +
                "Flight Time: " + scheduledTime);
    }
}
