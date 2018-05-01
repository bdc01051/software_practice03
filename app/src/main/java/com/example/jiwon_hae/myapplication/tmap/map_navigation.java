package com.example.jiwon_hae.myapplication.tmap;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.jiwon_hae.myapplication.R;
import com.example.jiwon_hae.myapplication.tmap.user_user_location.drunken_person;
import com.example.jiwon_hae.myapplication.tmap.user_user_location.register_current_location;
import com.example.jiwon_hae.myapplication.tmap.user_user_location.request_user_location_volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class map_navigation extends AppCompatActivity implements OnMapReadyCallback{

    private int REQUEST_LOCATION_PERMISSION = 12345;
    private Fragment mapView;

    private ImageButton request_walking_path;
    private ImageButton request_driving_path;


    //Samples
    private String friendsID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_navigation);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        //request_walking_path =(ImageButton)findViewById(R.id.walking_nav_btn);
        //request_driving_path =(ImageButton)findViewById(R.id.car_nav_btn);

        request_walking_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(map_navigation.this, "WALK", Toast.LENGTH_SHORT).show();
            }
        });

        request_driving_path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(map_navigation.this, "CAR", Toast.LENGTH_SHORT).show();
            }
        });

        //request_for_userLocation(friendsID);

    }

    private LatLng request_for_userLocation(String userID){
        final String[] location_information = new String[1];
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("response");

                    if(success){
                        location_information[0] = jsonObject.getString("location_information");
                        Toast.makeText(map_navigation.this, "register_succeess", Toast.LENGTH_SHORT).show();
                        Log.e("request_user_location", location_information.toString());

                    }else{
                        Log.e("request_user_location", "failed");
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        request_user_location_volley request_location = new request_user_location_volley(userID, responseListener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request_location);

        String[] latLng = location_information.toString().split(",");
        double latitude = Double.parseDouble(latLng[0]);
        double longitude = Double.parseDouble(latLng[1]);

        return new LatLng(latitude, longitude);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng place_location = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(place_location).title("Friends")).showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place_location, 16.0f));
        googleMap.setMapType(1);
        googleMap.getUiSettings().setCompassEnabled(false);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
    }
}
