package com.gelisim.konumkayituygulamasi;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.ZoomControls;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gelisim.konumkayituygulamasi.databinding.ActivityAntalyaharitaBinding;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class antalyaharita extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private Location mLastKnownLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final String KEY_LOCATION = "location";

    private ClusterManager clusterManager;
    static final LatLng antalya = new LatLng(36.89032160466458, 30.702813282173455);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        setContentView(R.layout.activity_antalyaharita);

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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(antalya,12));

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
                .position(new LatLng(36.8861480439039, 30.679644852361637)).title("Burası Antalya Arkeoloji Müzesi").snippet("Bahçelievler, Konyaaltı Cd. No:88, 07050 Muratpaşa/Antalya"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.88533993735199, 30.708478526030767)).title("Burası Hadrian Kale Kapısı").snippet("07100 Muratpaşa/Antalya"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.88142670650812, 30.70365826585438)).title("Burası Hıdırlık Kulesi").snippet("Kılınçarslan, Hıdırlık Sk. No:50, 07100 Muratpaşa/Antalya"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.886680685861656, 30.704458753018596)).title("Burası Yivliminare Cami").snippet("Selçuk, İskele Cd., 07100 Muratpaşa/Antalya"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.99827334665879, 30.72496542418661)).title("Burası Lyrboton Kome Arkeopark Projesi").snippet("Kirişçiler, 07025 Kepez/Antalya"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.988164375366345, 30.579823268363207)).title("Burası Evdirhan").snippet("Düzlerçamı, Evdirhanı Cd., 07190 Döşemealtı/Antalya"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.91300926633376, 30.675352610690098)).title("Burası Dokumapark").snippet("Fabrikalar, Namık Kemal Blv. No:225, 07090 Kepez/Antalya"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.84892073230646, 30.814258539523397)).title("Burası Sandland").snippet("Guzeloba Mahallesi Lara Caddesi Lara Birlik Halk Plajı, 07230 Muratpaşa/Antalya"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.87987559287169, 30.704923112536637)).title("Burası Karaalioğlu Parkı").snippet("Kılınçarslan, Park Sk., 07100 Muratpaşa/Antalya"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.911680455619745, 30.675962254866885)).title("Kepez Açık Hava Müzesi").snippet("Fabrikalar, Dokumapark, Namık Kemal Blv. No:225, 07090 Kepez/Antalya"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.88710456689626, 30.704484254209216)).title("Burası Mevlevihane Müzesi").snippet("Selçuk, Selçuk Mah No:36, 07100 Muratpaşa/Antalya"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(36.885778774957764, 30.701770168359953)).title("Burası Antalya Oyuncak Müzesi").snippet("Selçuk Mah Yat Limanı Sok. No:112, 07000 Muratpaşa/Antalya"));



        return markerLocationList;
    }
}