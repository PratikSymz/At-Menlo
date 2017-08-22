package com.pratiksymz.android.menlolookaround;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapViewFragment extends Fragment {

    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private String[] mLatLong;
    private double mLatitude, mLongitude;
    private String mLocationName;
    private String mLocationAddress;

    public MapViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_fragment, container, false);
        mMapView = (MapView) rootView.findViewById(R.id.map_view);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();    // Needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;

                // For showing a move to my location button
                mGoogleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                mLatLong = getString(R.string.C1_location_CBorrone).trim().split(",");
                mLatitude = Double.parseDouble(mLatLong[0]);
                mLongitude = Double.parseDouble(mLatLong[1]);

                mLocationName = getString(R.string.C1_name_CBorrone);
                mLocationAddress = getString(R.string.C1_address_CBorrone);

                LatLng c1Cafe = new LatLng(mLatitude, mLongitude);
                mGoogleMap.addMarker(new MarkerOptions()
                        .position(c1Cafe)
                        .title(mLocationName)
                        .snippet(mLocationAddress)
                );

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(c1Cafe).zoom(12).build();
                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}
