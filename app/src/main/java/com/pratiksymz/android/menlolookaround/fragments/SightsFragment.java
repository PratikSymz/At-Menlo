package com.pratiksymz.android.menlolookaround.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pratiksymz.android.menlolookaround.R;
import com.pratiksymz.android.menlolookaround.adapters.PlaceAdapter;
import com.pratiksymz.android.menlolookaround.models.Place;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SightsFragment extends Fragment {

    // Initialize an ArrayList for sights
    public ArrayList<Place> sights;

    public SightsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the list
        View rootView = inflater.inflate(R.layout.place_list, container, false);

        // Create the ArrayList for the sights
        sights = new ArrayList<>();

        // Add all the sights to the ArrayList
        sights.add(new Place(getString(R.string.S1_name_Facebook), getString(R.string.S1_address_Facebook),
                getString(R.string.S1_desc_Facebook), R.drawable.s1_facebook_final, getString(R.string.S1_site_Facebook),
                getString(R.string.S1_location_Facebook)));
        sights.add(new Place(getString(R.string.S2_name_Keplers), getString(R.string.S2_address_Keplers),
                getString(R.string.S2_desc_Keplers), R.drawable.s2_keplers_final, getString(R.string.S2_site_Keplers),
                getString(R.string.S2_location_Keplers)));
        sights.add(new Place(getString(R.string.S3_name_CheekyMToys), getString(R.string.S3_address_CheekyMToys),
                getString(R.string.S3_desc_CheekyMToys), R.drawable.s3_cheeky_final, getString(R.string.S3_site_CheekyMToys),
                getString(R.string.S3_location_CheekyMToys)));
        sights.add(new Place(getString(R.string.S4_name_SharonPark), getString(R.string.S4_address_SharonPark),
                getString(R.string.S4_desc_SharonPark), R.drawable.s4_sharonpark_final, getString(R.string.S4_site_SharonPark),
                getString(R.string.S4_location_SharonPark)));
        sights.add(new Place(getString(R.string.S5_name_Guild), getString(R.string.S5_address_Guild),
                getString(R.string.S5_desc_Guild), R.drawable.s5_guild_final, getString(R.string.S5_site_Guild),
                getString(R.string.S5_location_Guild)));
        sights.add(new Place(getString(R.string.S6_name_DutchGoose), getString(R.string.S6_address_DutchGoose),
                getString(R.string.S6_desc_DutchGoose), R.drawable.s6_dutchgoose_final, getString(R.string.S6_site_DutchGoose),
                getString(R.string.S6_location_DutchGoose)));
        sights.add(new Place(getString(R.string.S7_name_DrivingThrAtherton), getString(R.string.S7_address_DrivingThrAtherton),
                getString(R.string.S7_desc_DrivingThrAtherton), R.drawable.s7_atherton_final, getString(R.string.S7_site_DrivingThrAtherton),
                getString(R.string.S7_location_DrivingThrAtherton)));
        sights.add(new Place(getString(R.string.S8_name_PaceAnT), getString(R.string.S8_address_PaceAnT),
                getString(R.string.S8_desc_PaceAnT), R.drawable.s8_pace_final, getString(R.string.S8_site_PaceAnT),
                getString(R.string.S8_location_PaceAnT)));
        sights.add(new Place(getString(R.string.S9_name_AutoVino), getString(R.string.S9_address_AutoVino),
                getString(R.string.S9_desc_AutoVino), R.drawable.s9_autovino_final, getString(R.string.S9_site_AutoVino),
                getString(R.string.S9_location_AutoVino)));
        sights.add(new Place(getString(R.string.S10_name_MilitaryVT), getString(R.string.S10_address_MilitaryVT),
                getString(R.string.S10_desc_MilitaryVT), R.drawable.s10_military_final, getString(R.string.S10_site_MilitaryVT),
                getString(R.string.S10_location_MilitaryVT)));

        // Create a new adapter that takes the list of sights as input
        PlaceAdapter adapter = new PlaceAdapter(getActivity(), sights);

        // Find a reference to the {@link ListView} in the layout
        ListView sightListView = (ListView) rootView.findViewById(R.id.place_list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        sightListView.setAdapter(adapter);

        // Return the fragment rootView
        return rootView;
    }
}