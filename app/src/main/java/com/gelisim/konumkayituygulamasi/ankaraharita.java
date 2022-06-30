package com.gelisim.konumkayituygulamasi;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ZoomControls;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;


public class ankaraharita extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private static final String TAG = ankaraharita.class.getSimpleName();


    private Location mLastKnownLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private boolean mLocationPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int DEFAULT_ZOOM = 15;

    private static final String KEY_LOCATION = "location";

    private ClusterManager clusterManager;
    static final LatLng ankara = new LatLng(39.93843038640428, 32.861970899485996);


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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ankara,12));

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
                .position(new LatLng(39.925613486677044, 32.83720139205738)).title("Burası Anıtkabir").snippet("Yücetepe, Akdeniz Cd. No:31, 06570 Çankaya/Ankara"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.93843038640428, 32.861970899485996)).title("Burası Anadolu Medeniyetleri Müzesi").snippet("Kale, Gözcü Sk. No:2, 06240 Ulus/Altındağ/Ankara"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.942188974204115, 32.86259152647173)).title("Burası Ankara Kalesi").snippet("Kale, 06240 Altındağ/Ankara"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.932901177488034, 32.85539252647141)).title("Burası Etnoğrafya Müzesi").snippet("Hacettepe, 06230 Altındağ/Ankara"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.93509378447427, 32.85096022647139)).title("Burası Gençlik Parkı Şehri Ahter").snippet("Doğanbey Mahallesi, Talatpaşa Blv No:38, 06050 Altındağ/Ankara"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.916781683875726, 32.86072765530657)).title("Burası Kocatepe Camii").snippet("Kültür, Dr. Mediha Eldem Sk. No:67, 06420 Çankaya/Ankara"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.9446306408263, 32.85792812462173)).title("Burası Hacı Bayram-ı Velî Camii").snippet("Hacı Bayram, Sarıbağ Sk. No:13, 06050 Altındağ/Ankara"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.826899974898275, 32.832384971597335)).title("Burası Eymir Gölü").snippet("Oran, 06450 Çankaya/Ankara"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.937454410631005, 32.86387278414298)).title("Burası Rahmi Koç Müzesi").snippet("Kale, 06250 Altındağ/Ankara"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.94458464465756, 32.85837874366473)).title("Burası Augustus Tapınağı").snippet("Hacı Bayram, 06030 Altındağ/Ankara"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.94768965601704, 32.7044655688004)).title("Burası Türk Hava Kuvvetleri Müzesi").snippet("12. Km, İstanbul Yolu, 06500 Etimesgut/Ankara"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.806438919957785, 32.96442595530286)).title("Burası Elmadağ Kayak Merkezi").snippet("Yakupabdal, Elmadağ Kayak Merkezi, 06705 Çankaya/Ankara"));



        return markerLocationList;
    }



    }
