package com.gelisim.konumkayituygulamasi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.ZoomControls;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.gelisim.konumkayituygulamasi.databinding.ActivityIstanbulharitaBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class istanbulharita extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;


    private Location mLastKnownLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final String KEY_LOCATION = "location";

    private ClusterManager clusterManager;
    static final LatLng istanbul = new LatLng(41.0085910189041, 28.980185711045113);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        setContentView(R.layout.activity_istanbulharita);

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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(istanbul,10));

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
                .position(new LatLng(41.011624723839645, 28.983389626163465)).title("Burası Topkapı Sarayı").snippet("Cankurtaran, 34122 Fatih/İstanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.01190653399439, 28.968232521005536)).title("Burası Kapalı Çarşı").snippet("Beyazıt, Kalpakçılar Cd. No:22, 34126 Fatih/İstanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.01611498300721, 28.95561299732762)).title("Burası Buzdoğan Kemeri").snippet("Kalenderhane, Avrupa Yakası, 34083 Fatih/İstanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.00848922884852, 28.977867268491327)).title("Burası Yerebatan Sarnıcı").snippet("Alemdar, Yerebatan Cd. 1/3, 34110 Fatih/İstanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.02582012318243, 28.974038251295887)).title("Burası Galata Kulesi").snippet("Bereketzade, Galata Kulesi, 34421 Beyoğlu/İstanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.085135948343456, 29.056669855737)).title("Burası Rumeli Hisarı").snippet("Rumeli Hisarı, Yahya Kemal Cd., 34470 Sarıyer/İstanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.03930994129743, 29.000523770344373)).title("Burası Dolmabahçe Sarayı").snippet("Vişnezade, Dolmabahçe Cd., 34357 Beşiktaş/İstanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.01933729069414, 29.008945012671784)).title("Burası Kız Kulesi").snippet("Salacak, 34668 Üsküdar/İstanbul"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.0085910189041, 28.980185711045113)).title("Burası Ayasofya Camii").snippet("Sultan Ahmet, Ayasofya Meydanı No:1, 34122 Fatih/İstanbul"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.00618309734031, 28.97472009732756)).title("Burası Türk ve İslam Eserleri Müzesi").snippet("Binbirdirek, Atmeydanı Cd. No:12, 34122 Fatih/İstanbul"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.05899653689788, 28.94922230102433)).title("Burası Miniatürk").snippet("Örnektepe, İmrahor Cd. No:7, 34445 Beyoğlu/İstanbul"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.07062040082164, 28.92210485635015)).title("Burası Vialand Tema Park").snippet("Yeşilpınar, Şht. Metin Kaya Sk. No: 11/1, 34065 Eyüpsultan/İstanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.9651077484427, 28.798970333872592)).title("Burası İstanbul Akvaryum").snippet("Şenlikköy, Yeşilköy Halkalı Cd. No:93, 34153 Florya/İstanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.10865221880105, 29.05305131290311)).title("Burası Reşitpaşa, Emirgan Sk., 34467 Sarıyer/İstanbul").snippet("Reşitpaşa, Emirgan Sk., 34467 Sarıyer/İstanbul"));



        return markerLocationList;
    }
}