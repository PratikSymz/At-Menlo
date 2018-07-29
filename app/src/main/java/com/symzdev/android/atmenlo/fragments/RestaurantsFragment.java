package com.symzdev.android.atmenlo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.symzdev.android.atmenlo.R;
import com.symzdev.android.atmenlo.adapters.PlaceAdapter;
import com.symzdev.android.atmenlo.models.Place;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsFragment extends Fragment {


    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the list
        View rootView = inflater.inflate(R.layout.place_list, container, false);

        // Create the ArrayList for the restaurants
        ArrayList<Place> restaurants = new ArrayList<>();

        // Add the restaurants to the ArrayList
        restaurants.add(new Place(getString(R.string.R1_name_Evvia), getString(R.string.R1_address_Evvia),
                getString(R.string.R1_desc_Evvia), R.drawable.r1_evvia_final, getString(R.string.R1_site_Evvia),
                getString(R.string.R1_location_Evvia)));
        restaurants.add(new Place(getString(R.string.R2_name_Orens), getString(R.string.R2_address_Orens),
                getString(R.string.R2_desc_Orens), R.drawable.r2_orens_final, getString(R.string.R2_site_Orens),
                getString(R.string.R2_location_Orens)));
        restaurants.add(new Place(getString(R.string.R3_name_Refuge), getString(R.string.R3_address_Refuge),
                getString(R.string.R3_desc_Refuge), R.drawable.r3_refuge_final, getString(R.string.R3_site_Refuge),
                getString(R.string.R3_location_Refuge)));
        restaurants.add(new Place(getString(R.string.R4_name_Tamarine), getString(R.string.R4_address_Tamarine),
                getString(R.string.R4_desc_Tamarine), R.drawable.r4_tamarine_final, getString(R.string.R4_site_Tamarine),
                getString(R.string.R4_location_Tamarine)));
        restaurants.add(new Place(getString(R.string.R5_name_MenloGrill), getString(R.string.R5_address_MenloGrill),
                getString(R.string.R5_desc_MenloGrill), R.drawable.r5_menlogrill_final, getString(R.string.R5_site_MenloGrill),
                getString(R.string.R5_location_MenloGrill)));
        restaurants.add(new Place(getString(R.string.R6_name_Sundance), getString(R.string.R6_address_Sundance),
                getString(R.string.R6_desc_Sundance), R.drawable.r6_sundance_final, getString(R.string.R6_site_Sundance),
                getString(R.string.R6_location_Sundance)));
        restaurants.add(new Place(getString(R.string.R7_name_Madera), getString(R.string.R7_address_Madera),
                getString(R.string.R7_desc_Madera), R.drawable.r7_madera_final, getString(R.string.R7_site_Madera),
                getString(R.string.R7_location_Madera)));
        restaurants.add(new Place(getString(R.string.R8_name_Flea), getString(R.string.R8_address_Flea),
                getString(R.string.R8_desc_Flea), R.drawable.r8_flea_final, getString(R.string.R8_site_Flea),
                getString(R.string.R8_location_Flea)));
        restaurants.add(new Place(getString(R.string.R9_name_Stacks), getString(R.string.R9_address_Stacks),
                getString(R.string.R9_desc_Stacks), R.drawable.r9_stacks_final, getString(R.string.R9_site_Stacks),
                getString(R.string.R9_location_Stacks)));
        restaurants.add(new Place(getString(R.string.R10_name_SqueezeIn), getString(R.string.R10_address_SqueezeIn),
                getString(R.string.R10_desc_SqueezeIn), R.drawable.r10_squeezein_final, getString(R.string.R10_site_SqueezeIn),
                getString(R.string.R10_location_SqueezeIn)));


        // Create a new adapter that takes the list of restaurants as input
        PlaceAdapter adapter = new PlaceAdapter(getActivity(), restaurants);

        // Find a reference to the {@link ListView} in the layout
        ListView restaurantListView = (ListView) rootView.findViewById(R.id.place_list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        restaurantListView.setAdapter(adapter);

        // Return the fragment rootView
        return rootView;
    }

}