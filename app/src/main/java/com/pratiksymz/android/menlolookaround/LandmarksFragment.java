package com.pratiksymz.android.menlolookaround;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LandmarksFragment extends Fragment {


    public LandmarksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the list
        View rootView = inflater.inflate(R.layout.place_list, container, false);

        // Create the ArrayList for the places
        ArrayList<Place> places = new ArrayList<>();

        // Add the places to the ArrayList
        places.add(new Place(getString(R.string.L1_name_Fremont), getString(R.string.L1_address_Fremont),
                getString(R.string.L1_desc_Fremont), R.drawable.l1_fremont_final, getString(R.string.L1_site_Fremont),
                getString(R.string.L1_location_Fremont)));
        places.add(new Place(getString(R.string.L2_name_PaloAltoTW), getString(R.string.L2_address_PaloAltoTW),
                getString(R.string.L2_desc_PaloAltoTW), R.drawable.l2_paloaltotower_final, getString(R.string.L2_site_PaloAltoTW),
                getString(R.string.L2_location_PaloAltoTW)));
        places.add(new Place(getString(R.string.L3_name_Hanna), getString(R.string.L3_address_Hanna),
                getString(R.string.L3_desc_Hanna), R.drawable.l3_hanna_final, getString(R.string.L3_site_Hanna),
                getString(R.string.L3_location_Hanna)));
        places.add(new Place(getString(R.string.L4_name_AngelOG), getString(R.string.L4_address_AngelOG),
                getString(R.string.L4_desc_AngelOG), R.drawable.l4_angelofg_final, getString(R.string.L4_site_AngelOG),
                getString(R.string.L4_location_AngelOG)));
        places.add(new Place(getString(R.string.L5_name_StoneRiverAG), getString(R.string.L5_address_StoneRiverAG),
                getString(R.string.L5_desc_StoneRiverAG), R.drawable.l5_stone_final, getString(R.string.L5_site_StoneRiverAG),
                getString(R.string.L5_location_StoneRiverAG)));
        places.add(new Place(getString(R.string.L6_name_Rengstorff), getString(R.string.L6_address_Rengstorff),
                getString(R.string.L6_desc_Rengstorff), R.drawable.l6_rengstorff_final, getString(R.string.L6_site_Rengstorff),
                getString(R.string.L6_location_Rengstorff)));
        places.add(new Place(getString(R.string.L7_name_HP), getString(R.string.L7_address_HP),
                getString(R.string.L7_desc_HP), R.drawable.l7_hp_final, getString(R.string.L7_site_HP),
                getString(R.string.L7_location_HP)));
        places.add(new Place(getString(R.string.L8_name_HooverT), getString(R.string.L8_address_HooverT),
                getString(R.string.L8_desc_HooverT), R.drawable.l8_hoover_final, getString(R.string.L8_site_HooverT),
                getString(R.string.L8_location_HooverT)));
        places.add(new Place(getString(R.string.L9_name_CourthouseSq), getString(R.string.L9_address_CourthouseSq),
                getString(R.string.L9_desc_CourthouseSq), R.drawable.l9_courthouse_final, getString(R.string.L9_site_CourthouseSq),
                getString(R.string.L9_location_CourthouseSq)));
        places.add(new Place(getString(R.string.L10_name_Lathrop), getString(R.string.L10_address_Lathrop),
                getString(R.string.L10_desc_Lathrop), R.drawable.l10_lathrop_final, getString(R.string.L10_site_Lathrop),
                getString(R.string.L10_location_Lathrop)));


        // Create a new adapter that takes the list of landmarks(places) as input
        PlaceAdapter adapter = new PlaceAdapter(getActivity(), places);

        // Find a reference to the {@link ListView} in the layout
        ListView landmarkListView = (ListView) rootView.findViewById(R.id.place_list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        landmarkListView.setAdapter(adapter);

        // Return the fragment rootView
        return rootView;
    }

}
