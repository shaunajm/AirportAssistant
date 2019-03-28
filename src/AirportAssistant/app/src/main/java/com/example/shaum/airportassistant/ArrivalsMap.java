package com.example.shaum.airportassistant;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.*;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ArrivalsMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Button btProgress;
    public TravelMode travelMode;
    public FusedLocationProviderClient mFusedLocationProviderClient;
    private static final int DEFAULT_ZOOM = 9;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    public Location mCurrentLocation;
    public CameraPosition mCameraPosition;
    private Bundle bundle = new Bundle();
    public ArrayList<LatLng> listpoints;
    public String address;
    public double arrivallat;
    public double arrivallng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivals_map);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            travelMode = (TravelMode) bundle.getSerializable("mode");
            address = bundle.getString("address");
        }

        if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }


        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ArrivalsMap.this, "Your journey is complete.",
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(ArrivalsMap.this, Home.class);
                startActivity(i);
                finish();
            }

        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        listpoints = new ArrayList<>();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {

            @Override
            public void onMapLoaded() {
                if(listpoints.size() == 2){
                    listpoints.clear();
                    mMap.clear();
                }
                getLocationFromAddress(address);

                LatLng origin = new LatLng(51.4700256,-0.4564895);

                LatLng destination = new LatLng(arrivallat, arrivallng);
                //Save first points select
                listpoints.add(origin);
                listpoints.add(destination);
                //Create marker
                MarkerOptions currentOptions = new MarkerOptions();
                currentOptions.position(origin);
                currentOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                MarkerOptions destinationOptions = new MarkerOptions();
                destinationOptions.position(destination);
                destinationOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                mMap.addMarker(destinationOptions);
                mMap.addMarker(currentOptions);

                DirectionsResult dr = getDirectionsResult(origin, destination);
                com.google.android.gms.maps.model.LatLng target = new com.google.android.gms.maps.model.LatLng(51.4700256, -0.4564895);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(target,
                        DEFAULT_ZOOM));
            }
        });
    }


    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setQueryRateLimit(3).setApiKey(getString(R.string.directionsApiKey)).setConnectTimeout(3, TimeUnit.SECONDS).setReadTimeout(3, TimeUnit.SECONDS).setWriteTimeout(3, TimeUnit.SECONDS);
        return geoApiContext;
    }


    public DirectionsResult getDirectionsResult(LatLng origin, LatLng destination) {

        DateTime now = new DateTime();

        try {
            getGeoContext();
            DirectionsResult dr = DirectionsApi.newRequest(getGeoContext())
                    .origin(new com.google.maps.model.LatLng(origin.latitude, origin.longitude))
                    .destination(new com.google.maps.model.LatLng(destination.latitude, destination.longitude))
                    .mode(travelMode)
                    .departureTime(now)
                    .await();
            if (dr != null) {
                addPolyline(dr);
                displayEndLocationTitle(dr);
            }
            return dr;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ArrivalsMap.this, "EXCEPTION: " + address,
                    Toast.LENGTH_LONG).show();
            return null;
        }

    }

    private void displayEndLocationTitle(DirectionsResult results){
        Duration duration = results.routes[0].legs[0].duration;
        Distance distance = results.routes[0].legs[0].distance;

        TextView tvDuration = (TextView) findViewById(R.id.timeDetails);
        TextView tvDistance = (TextView) findViewById(R.id.distanceDetails);

        tvDuration.setText("Duration: \n"+ duration.humanReadable);
        tvDistance.setText("Distance: \n" + distance.humanReadable);

    }

    private void addPolyline(DirectionsResult results) {
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[0].overviewPolyline.getEncodedPath());
        mMap.addPolyline(new PolylineOptions().addAll(decodedPath));
    }

    public void getLocationFromAddress(String strAddress) {
        try {
            Log.d("Address", strAddress);
            GeoApiContext context = getGeoContext();
            GeocodingResult[] result = GeocodingApi.geocode(context, strAddress).await();
            //address = coder.getFromLocationName(strAddress, 1);
            if (result == null) {
                Log.d("GeoCoder", "Could Not Find " + strAddress);
                return;
            }
            arrivallat = result[0].geometry.location.lat;
            arrivallng = result[0].geometry.location.lng;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
