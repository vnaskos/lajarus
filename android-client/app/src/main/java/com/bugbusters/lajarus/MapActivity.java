package com.bugbusters.lajarus;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bugbusters.lajarus.entity.Session;
import com.bugbusters.lajarus.manager.SessionManager;
import com.bugbusters.lajarus.util.ThreadPerTaskExecutor;
import com.bugbusters.lajarus.ws.AsyncWebSocketMessageSender;
import com.bugbusters.lajarus.ws.WebSocketMessageFetcher;
import com.bugbusters.lajarus.ws.WebSocketMessageListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mGoogleMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;

    private Session session;
    private Map<String, Marker> playerMarkers;
    private Map<String, Marker> questMarkers;
    public Button but1;
    private Button profileBtn;

    private void init(){
        but1 = (Button)findViewById(R.id.button4);
        but1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent toy = new Intent(MapActivity.this,ItemsActivity.class);
                startActivity(toy);
            }
        });
        profileBtn = (Button) findViewById(R.id.button3);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileActivity = new Intent(MapActivity.this, ProfileActivity.class);
                startActivity(profileActivity);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
        ProgressBar xp = (ProgressBar) findViewById(R.id.progressBar9);
        xp.setProgress(50);
        init();
        WebSocketMessageListener wsListener = new WebSocketMessageListener() {
            @Override
            public void onMessage(JSONObject msg) throws JSONException {
                Log.d("onWsMessage", msg.toString());

                String action = msg.getString("ACTION");

                if(action.equals("NEARBY_PLAYERS")) {
                    JSONArray players = msg.getJSONArray("players");
                    updatePlayerPositionsOnMap(players);
                }

                if(action.equals("NEARBY_QUESTS")) {
                    JSONArray quests = msg.getJSONArray("quests");
                    updateQuestPositionsOnMap(quests);
                }
            }
        };

        WebSocketMessageFetcher messageFetcher = new WebSocketMessageFetcher();
        messageFetcher.addListener(wsListener);
        new ThreadPerTaskExecutor().execute(messageFetcher);

        session = SessionManager.getInstance().getActiveSession();
        playerMarkers = new HashMap<>();
        questMarkers = new HashMap<>();
    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                this, R.raw.map_style));

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLastLocation != null) {
            LatLng loc = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        String request = "";

        //Update user location on server
        try {
            JSONObject updateLocationRequest = new JSONObject();
            updateLocationRequest.put("ACTION", "UPDATE_LOCATION");
            updateLocationRequest.put("FROM", session.getActivePlayer().getName());
            updateLocationRequest.put("latitude", latLng.latitude+"");
            updateLocationRequest.put("longitude", latLng.longitude+"");
            request = updateLocationRequest.toString();
        } catch (Exception e) {
            Log.e("MapActivity", "websocket update location " + e.getCause());
        }

        new AsyncWebSocketMessageSender().execute(request);

        try {
            JSONObject updateLocationRequest = new JSONObject();
            updateLocationRequest.put("ACTION", "NEARBY_PLAYERS");
            updateLocationRequest.put("FROM", session.getActivePlayer().getName());
            request = updateLocationRequest.toString();
        } catch (Exception e) {
            Log.e("MapActivity", "websocket update location " + e.getCause());
        }

        new AsyncWebSocketMessageSender().execute(request);

        try {
            JSONObject updateLocationRequest = new JSONObject();
            updateLocationRequest.put("ACTION", "NEARBY_QUESTS");
            updateLocationRequest.put("FROM", session.getActivePlayer().getName());
            request = updateLocationRequest.toString();
        } catch (Exception e) {
            Log.e("MapActivity", "websocket update location " + e.getCause());
        }

        new AsyncWebSocketMessageSender().execute(request);
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void updatePlayerPositionsOnMap(JSONArray players) throws JSONException {
        final Map<String, LatLng> nearbyPlayers = new HashMap<>();

        //Parse JSON Array elements to Map key:name val:location
        for(int i=0;i<players.length();i++) {
            JSONObject player = players.getJSONObject(i);
            final String name = player.getString("name");
            Double latitude = player.getDouble("lat");
            Double longitude = player.getDouble("long");
            final LatLng latLng = new LatLng(latitude, longitude);

            //Skip current player
            if (name.equals(session.getActivePlayer().getName())) continue;

            nearbyPlayers.put(name, latLng);
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //Update positions of already visible players
                //Or delete them in case they disappear
                for(String name : playerMarkers.keySet()) {
                    Marker m = playerMarkers.get(name);

                    if(nearbyPlayers.get(name) != null) {
                        m.setPosition(nearbyPlayers.get(name));
                        nearbyPlayers.remove(name);
                    } else {
                        m.remove();
                        playerMarkers.remove(name);
                    }
                }

                //Add new visible players
                for(String name : nearbyPlayers.keySet()) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(nearbyPlayers.get(name));
                    markerOptions.title("Player: " + name);
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.barbarian));
                    Marker m = mGoogleMap.addMarker(markerOptions);
                    playerMarkers.put(name, m);
                }
            }
        });
    }

    private void updateQuestPositionsOnMap(JSONArray quests) throws JSONException {
        final Map<String, LatLng> nearbyQuests = new HashMap<>();

        //Parse JSON Array elements to Map key:name val:location
        for(int i=0;i<quests.length();i++) {
            JSONObject quest = quests.getJSONObject(i);
            final String name = quest.getString("name");
            Double latitude = quest.getDouble("lat");
            Double longitude = quest.getDouble("long");
            final LatLng latLng = new LatLng(latitude, longitude);

            nearbyQuests.put(name, latLng);
        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //delete quests in case they disappear
                for(String name : questMarkers.keySet()) {
                    Marker m = questMarkers.get(name);

                    if(nearbyQuests.get(name) != null) {
                        nearbyQuests.remove(name);
                    } else {
                        m.remove();
                        questMarkers.remove(name);
                    }
                }

                //Add new visible quest
                for(String name : nearbyQuests.keySet()) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(nearbyQuests.get(name));
                    markerOptions.title(name);
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    Marker m = mGoogleMap.addMarker(markerOptions);
                    questMarkers.put(name, m);
                    mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            String name= marker.getTitle();

                            if (name.equalsIgnoreCase("myQuest1"))
                            {
                                Intent intent = new Intent(MapActivity.this,QuestActivity.class);
                                startActivity(intent);
                            }
                            return false;
                        }
                    });
                }
            }
        });
    }
}