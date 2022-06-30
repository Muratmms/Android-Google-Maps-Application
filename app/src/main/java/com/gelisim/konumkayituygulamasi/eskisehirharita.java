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
import com.gelisim.konumkayituygulamasi.databinding.ActivityEskisehirharitaBinding;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class eskisehirharita extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private Location mLastKnownLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final String KEY_LOCATION = "location";

    private ClusterManager clusterManager;
    static final LatLng eskisehir = new LatLng(39.76263833766282, 30.524775654956933);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        setContentView(R.layout.activity_eskisehirharita);

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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eskisehir,12));

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
                .position(new LatLng(39.763169125903815, 30.52578398189393)).title("Burası Kurşunlu Cami ve Külliyesi").snippet("Paşa, Mücellit Sk., 26030 Odunpazarı/Eskişehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76652596880428, 30.47590325495713)).title("Burası Sazova Bilim Parkı").snippet("Osmangazi, Uzunçarşı Cd., 16010 Osmangazi/Bursa"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76724222044303, 30.473247268451022)).title("Burası Eskişehir Büyükşehir Belediyesi Hayvanat Bahçesi").snippet("Sazova, Ulusal Egemenlik Blv. 209/F, 26000 Karagözler/Tepebaşı/Eskişehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76534671844745, 30.52191215495698)).title("Burası Eskişehir Büyükşehir Belediyesi Yılmaz Büyükerşen Balmumu Heykeller Müzesi").snippet("Şarkiye, Atatürk Blv. No:43, 26010 Odunpazarı/Eskişehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.766064495004564, 30.51338377209567)).title("Burası Eskişehir ETİ Arkeoloji Müzesi").snippet("Akarbaşı, Atatürk Blv. No:64, 26020 Odunpazarı/Eskişehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.77524654778546, 30.54953594697935)).title("Burası Eskişehir Büyükşehir Belediyesi Kentpark").snippet("Şeker, Sivrihisar-2 Cd., 26120 Tepebaşı/Eskişehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.763553769917586, 30.52487536660338)).title("Burası Odunpazarı Evleri").snippet("Paşa, Çürükhoca Sk No:3, 26030, 26030 Odunpazarı/Eskişehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76294614332347, 30.52497189727642)).title("Burası Eskişehir Lületaşı Müzesi").snippet("Deliklitaş, Alaeddin Cd., 26090 Odunpazarı/Eskişehir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.78636608146841, 30.50046389543954)).title("Burası Vecihi Hürkuş Havacılık Müzesi ve Teknoloji Parkı").snippet("Yenibağlar, Uludağ Sk. No:10, 26170 Tepebaşı/Eskişehir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.732882730282775, 30.447130210779154)).title("Burası Karacahisar Kalesi").snippet("Karacaşehir, 26004 Odunpazarı/Eskişehir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76263833766282, 30.524775654956933)).title("Burası Osman Yaşar Tanaçan Fotoğraf Müzesi").snippet("Paşa, Kemal Zeytinoğlu Cd. No:6, 26030 Odunpazarı/Eskişehir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76494671440262, 30.47572721078022)).title("Burası Esminyatürk").snippet("Sazova, 26150 Tepebaşı/Eskişehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76206463211884, 30.52571015495689)).title("Burası Kazan Tatarları Müze Evi").snippet("Dede, Yeşil Efendi Sk. No:5, 26030 Odunpazarı/Eskişehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.778694362086426, 30.50637408379284)).title("Burası TCDD Müzesi").snippet("Hoşnudiye, Demirsoy Sk. No:8, 26130 Tepebaşı/Eskişehir"));



        return markerLocationList;
    }
}