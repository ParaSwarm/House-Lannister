package edu.uco.houselannister.saveasingle.fragments;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.activities.MainActivity;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.helpers.FragmentNavigationManager;

public class MapsActivity extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ArrayList<User> matchingUsers;

    public MapsActivity() {

    }

    public static MapsActivity newInstance(Location location, ArrayList<User> matchingUsers) {
        MapsActivity fragment = new MapsActivity();
        Bundle args = new Bundle();
        args.putParcelable("Location", location);
        args.putParcelableArrayList("matches", matchingUsers);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        View view = inflater.inflate(R.layout.activity_maps, null, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
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

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setOnMarkerClickListener(this);
        Location userLocation = getArguments().getParcelable("Location");
        matchingUsers = getArguments().getParcelableArrayList("matches");
        LatLng userLatLang = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(userLatLang).title("Me"));
        for(int i = 0; i < matchingUsers.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(matchingUsers.get(i).getLocation().getLatitude(), matchingUsers.get(i).getLocation().getLongitude())).title(matchingUsers.get(i).getName()));
        }
        CameraUpdate location= CameraUpdateFactory.newLatLngZoom(userLatLang, 15);
        mMap.animateCamera(location);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        for(int i = 0; i < matchingUsers.size(); i++) {
            if(marker.getTitle().compareTo(matchingUsers.get(i).getName()) == 0) {
                //clicked on someone that matches, load their profile
                FragmentNavigationManager manager = FragmentNavigationManager.getsInstance();
                manager.showFragmentUserProfile(matchingUsers.get(i));
            }
        }
        return false;
    }


}
