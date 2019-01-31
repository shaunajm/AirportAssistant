package com.example.shaum.airportassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.shaum.airportassistant.R;
import android.content.Intent;

public class QRJourneyUpload extends AppCompatActivity {

    public Button btUploadJourney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrjourney_upload);

        btUploadJourney = (Button) findViewById(R.id.btUploadJourney);
        btUploadJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QRJourneyUpload.this, HoldLuggageQues.class);
                //TAKE IN JOURNEY INFO FROM QR CODE//
                startActivity(i);
                finish();
            }

        });

    }
}
