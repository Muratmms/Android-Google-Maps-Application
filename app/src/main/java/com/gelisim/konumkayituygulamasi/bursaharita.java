package com.gelisim.konumkayituygulamasi;

import android.location.Location;
import android.os.Bundle;
import android.widget.ZoomControls;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class bursaharita extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private Location mLastKnownLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final String KEY_LOCATION = "location";

    private ClusterManager clusterManager;
    static final LatLng bursa = new LatLng(40.18397067194835, 29.06173123967029);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        setContentView(R.layout.activity_ankaraharita);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ZoomControls zoom = findViewById(R.id.zoom);
        zoom.setOnZoomOutClickListener(v -> mMap.animateCamera(CameraUpdateFactory.zoomOut()));
        zoom.setOnZoomInClickListener(v -> mMap.animateCamera(CameraUpdateFactory.zoomIn()));

    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bursa,12));

        mMap = googleMap;



        addMarkers();
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    private void addMarkers() {
        ArrayList<MarkerOptions> markerLocationList = getMarkerList();

        clusterManager = new ClusterManager<ClusterItem> (this, mMap);
        clusterManager.setRenderer(new CustomIconRenderer(this, mMap, clusterManager));

        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);


        for(MarkerOptions m: markerLocationList) {
            ClusterMarkerItem cmi = new ClusterMarkerItem(m.getPosition(),m.getTitle(),m.getSnippet());
            cmi.getSnippet();
            cmi.getTitle();
            cmi.setIcon(m.getIcon());
            clusterManager.addItem(cmi);
        }

    }

    private ArrayList<MarkerOptions> getMarkerList() {
        ArrayList<MarkerOptions> markerLocationList = new ArrayList<>();

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.18397067194835, 29.06173123967029)).title("Burası Ulu Camii").snippet("Nalbantoğlu, Ulucami Cd. No:2, 16010 Osmangazi/Bursa"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.184700715578195, 29.06354362798289)).title("Burası Koza Han").snippet("Osmangazi, Uzunçarşı Cd., 16010 Osmangazi/Bursa"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.190441795541815, 29.045240483806342)).title("Burası Muradiye Külliyesi").snippet("Muradiye, Prf. Dr. Halil İnalcık Sk., 16050 Osmangazi/Bursa"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.181544204621616, 29.07486691079391)).title("Burası Yeşil Türbe").snippet("Yeşil, 1. Yeşil Cd. 66-1, 16360 Yıldırım/Bursa"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.18211181567299, 29.07081229729996)).title("Burası Irgandı Köprüsü").snippet("Kurtoğlu, Gökdere Blv. No:14, 16360 Yıldırım/Bursa"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.17618672753018, 29.172158139629065)).title("Burası Cumalıkızık").snippet("Cumalıkızık, 16370 Yıldırım/Bursa"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.1825068276227, 29.066367024287917)).title("Burası Bursa Kent Müzesi").snippet("Hocaalizade, 6. Kültür Sk. No:8, 16010, 16010 Osmangazi/Bursa"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.196022762631024, 29.041068270312657)).title("Burası Bursa Arkeoloji Müzesi").snippet("Gaziakdemir, Kültür Park, Çekirge Cd. 4/11 D:1, 16050 Osmangazi/Bursa"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.19521658876126, 29.039911826135796)).title("Burası Atatürk Müzesi").snippet("Çekirge, Çekirge Cd. No:77, 16265 Osmangazi/Bursa"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.19063051612639, 29.04596591264175)).title("Burası Cem Sultan Türbesi").snippet("Muradiye, 16050 Osmangazi/Bursa"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.18561142162051, 29.05773265312333)).title("Burası Bursa Kalesi").snippet("Kavaklı, Ortapazar Cd. No:1, 16040 Osmangazi/Bursa"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.188366996624744, 29.05260108380623)).title("Burası Bey Sarayı").snippet("Kuruçeşme, 16050 Osmangazi/Bursa"));



        return markerLocationList;
    }
}