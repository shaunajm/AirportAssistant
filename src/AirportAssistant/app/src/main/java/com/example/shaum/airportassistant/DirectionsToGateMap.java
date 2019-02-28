package com.example.shaum.airportassistant;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.*;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DirectionsToGateMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Button btProgress;
    public boolean mLocationPermissionGranted;
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    public Location mLastKnownLocation;
    public FusedLocationProviderClient mFusedLocationProviderClient;
    private static final int DEFAULT_ZOOM = 13;
    public com.google.android.gms.maps.model.LatLng mDefaultLocation;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    public Location mCurrentLocation;
    public CameraPosition mCameraPosition;
    public ArrayList<LatLng> listpoints;
    public DatabaseReference mRootRef;
    public DataSnapshot data;
    public FirebaseAuth mAuth;
    public String gateNumber;
    public String prepgateNumber;
    public String address;
    public double arrivallat;
    public double arrivallng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_to_gate_map);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        getLocationPermission();
        getDeviceLocation();

        mAuth = FirebaseAuth.getInstance();

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot;
                getGateInfo();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }


        btProgress = (Button) findViewById(R.id.btProgress);
        btProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DirectionsToGateMap.this, Onboard.class);
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
                //Reset marker when already 2
                if(listpoints.size() == 2){
                    listpoints.clear();
                    mMap.clear();
                }

                getLocationFromAddress(prepgateNumber);

                LatLng currentLatLng = new LatLng(mLastKnownLocation.getLatitude(),
                        mLastKnownLocation.getLongitude());

                LatLng destination = new LatLng(arrivallat, arrivallng);
                //Save first points select
                listpoints.add(currentLatLng);
                listpoints.add(destination);
                //Create marker
                MarkerOptions currentOptions = new MarkerOptions();
                currentOptions.position(currentLatLng);
                currentOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                MarkerOptions destinationOptions = new MarkerOptions();
                destinationOptions.position(destination);
                destinationOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                mMap.addMarker(currentOptions);
                mMap.addMarker(destinationOptions);

                DirectionsResult dr = getDirectionsResult(currentLatLng, destination);
            }
        });

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
    }


    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setQueryRateLimit(3).setApiKey(getString(R.string.directionsApiKey)).setConnectTimeout(1, TimeUnit.SECONDS).setReadTimeout(1, TimeUnit.SECONDS).setWriteTimeout(1, TimeUnit.SECONDS);
        Log.d("Map", "Geo context request");
        return geoApiContext;
    }


    public DirectionsResult getDirectionsResult(LatLng origin, LatLng destination) {

        DateTime now = new DateTime();
        com.google.maps.model.LatLng googleOrigin = new com.google.maps.model.LatLng(origin.latitude, origin.longitude);
        com.google.maps.model.LatLng googleDestination = new com.google.maps.model.LatLng(destination.latitude, destination.longitude);

        try {
            getGeoContext();
            Log.d("Map", "Geo context found");
            DirectionsResult dr = DirectionsApi.getDirections(getGeoContext(), googleOrigin.toString(), googleDestination.toString())
                    .mode(TravelMode.WALKING)
                    .departureTime(now)
                    .await();
            if (dr != null) {
                addPolyline(dr);
                displayEndLocationTitle(dr);
            }
            return dr;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(DirectionsToGateMap.this, "EXCEPTION GURL",
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

    private void getLocationPermission(){
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        //  updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            System.out.println("Error: " + e);
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                if(locationResult != null){
                    locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                // Set the map's camera position to the current location of the device.
                                mLastKnownLocation = (Location) task.getResult();
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(mLastKnownLocation.getLatitude(),
                                                mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            } else {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                            }
                        }
                    });
                }
            }
        } catch(SecurityException e)  {
            System.out.println("Error: " + e);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    public void getGateInfo(){
        DataSnapshot user = data.child("users").child(mAuth.getUid());

        if (user != null) {
            gateNumber = user.child("gateNumber").getValue(String.class);
        }
        prepgateNumber = "Gate " + gateNumber + " Dublin Airport";
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
