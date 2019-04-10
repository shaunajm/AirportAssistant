package com.example.shaum.airportassistant;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;


public class Security extends AppCompatActivity {

    public ProgressBar spinner;
    public int waitTime;
    public Button btProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        ProgressSpinner();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SeekBar seekBar = (SeekBar) findViewById(R.id.progressBar);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        new ScrapeTask().execute();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Security.this, SecurityARCheck.class);
                startActivity(i);
                finish();
            }
        });

        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Security.this, DutyFreeMap.class);
                startActivity(i);
                finish();
            }
        });
    }

    public class ScrapeTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect("https://www.stuttgart-airport.com/security-wait-times/").get();
                Element table = document.select("tbody").first();
                Element row = table.select("tr").first();
                if (row != null) {
                    Element column = row.select("td").last();
                    if (column != null) {
                        waitTime = Integer.parseInt(column.text().split(" ")[0]);
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
            TextView tv = (TextView) findViewById(R.id.securityWaitIs);
            tv.setText("Security Wait Time is: " + waitTime + " min(s)");
        }
    }

    public void ProgressSpinner(){

        spinner = (ProgressBar)findViewById(R.id.progressBar1);

        spinner.setVisibility(View.VISIBLE);

    }


}