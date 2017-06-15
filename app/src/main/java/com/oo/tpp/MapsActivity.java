package com.oo.tpp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng p1 = new LatLng(45.650106, 13.767817);
        LatLng p2 = new LatLng(45.652605, 13.765500);
        LatLng p3 = new LatLng(45.648145, 13.772070);
        LatLng p4 = new LatLng(45.641021, 13.748276);
        LatLng p5 = new LatLng(45.649177, 13.771725);
        LatLng p6 = new LatLng(45.641048, 13.760923);
        LatLng p7 = new LatLng(45.654261, 13.775756);
        LatLng p8 = new LatLng(45.654371, 13.785728);
        mMap.addMarker(new MarkerOptions().position(p1).title("Piazza Unità D'Italia"));
        mMap.addMarker(new MarkerOptions().position(p2).title("Molo San Carlo"));
        mMap.addMarker(new MarkerOptions().position(p3).title("Il colle di San Giusto"));
        mMap.addMarker(new MarkerOptions().position(p4).title("Punto Franco"));
        mMap.addMarker(new MarkerOptions().position(p5).title("Cittavecchia"));
        mMap.addMarker(new MarkerOptions().position(p6).title("Ponterosso"));
        mMap.addMarker(new MarkerOptions().position(p7).title("Piazza Oberdan"));
        mMap.addMarker(new MarkerOptions().position(p8).title("Giardino Pubblico"));
        float zoom= 13;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p1,zoom));
    }
}
