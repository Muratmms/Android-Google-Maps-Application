package com.gelisim.konumkayituygulamasi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.gelisim.konumkayituygulamasi.databinding.ActivityMapsNeredeBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

public class MapsActivityNerede extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code = 101;
    private double lat,lng;
    ImageButton atm,bank,res,hosp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.gelisim.konumkayituygulamasi.databinding.ActivityMapsNeredeBinding binding = ActivityMapsNeredeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        atm = findViewById(R.id.atm);
        bank = findViewById(R.id.banka);
        res = findViewById(R.id.restorant);
        hosp = findViewById(R.id.hospital);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getApplicationContext());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        atm.setOnClickListener(view -> {

            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + lat + "," + lng +
                    "&radius=1000" +
                    "&type=eczane" +
                    "&sensor=true" +
                    "&key=" + getResources().getString(R.string.google_maps_key);
            Object[] dataFetch = new Object[2];
            dataFetch[0] = mMap;
            dataFetch[1] = url;

            FetchData fetchData = new FetchData();
            fetchData.execute(dataFetch);
        });
        hosp.setOnClickListener(view -> {

            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + lat + "," + lng +
                    "&radius=1000" +
                    "&type=hastane" +
                    "&sensor=true" +
                    "&key=" + getResources().getString(R.string.google_maps_key);
            Object[] dataFetch = new Object[2];
            dataFetch[0] = mMap;
            dataFetch[1] = url;

            FetchData fetchData = new FetchData();
            fetchData.execute(dataFetch);

        });

        res.setOnClickListener(view -> {

            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + lat + "," + lng +
                    "&radius=1000" +
                    "&type=restorant" +
                    "&sensor=true" +
                    "&key=" + getResources().getString(R.string.google_maps_key);
            Object[] dataFetch = new Object[2];
            dataFetch[0] = mMap;
            dataFetch[1] = url;

            FetchData fetchData = new FetchData();
            fetchData.execute(dataFetch);
        });

        bank.setOnClickListener(view -> {

            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + lat + "," + lng +
                    "&radius=1000" +
                    "&type=banka" +
                    "&sensor=true" +
                    "&key=" + getResources().getString(R.string.google_maps_key);
            Object[] dataFetch = new Object[2];
            dataFetch[0] = mMap;
            dataFetch[1] = url;

            FetchData fetchData = new FetchData();
            fetchData.execute(dataFetch);
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getCurrentLocation();
    }

    private void getCurrentLocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions
                    (this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},Request_code);
            return;
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);
        LocationCallback locationCallback = new LocationCallback(){

            @Override
            public void onLocationResult(LocationResult locationResult) {
                Toast.makeText(getApplicationContext(),"konum sonucu:"+locationResult,Toast.LENGTH_LONG).show();
                if(locationResult == null){
                    Toast.makeText(getApplicationContext(),"konum geçersiz",Toast.LENGTH_LONG).show();
                    return;
                }

                for (Location location:locationResult.getLocations()) {
                    if(location != null) {
                        Toast.makeText(getApplicationContext(),"konum sonucu:"+location.getLongitude(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null);
        Task<Location> task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {

            if(location != null) {

                lat=location.getLatitude();
                lng=location.getLongitude();

                LatLng latLng = new LatLng(lat,lng);
                mMap.addMarker(new MarkerOptions().position(latLng).title("mevcut konum"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
            }

        });


    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }
    }
}