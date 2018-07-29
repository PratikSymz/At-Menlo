package com.symzdev.android.atmenlo.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.symzdev.android.atmenlo.R;
import com.symzdev.android.atmenlo.activities.PlaceDetailsActivity;
import com.symzdev.android.atmenlo.models.Place;

import java.util.ArrayList;


public class PlaceAdapter extends ArrayAdapter<Place> {
    /**
     * Get the parent context
     */
    private Context context = getContext();

    public PlaceAdapter(Activity context, ArrayList<Place> places) {
        super(context, 0, places);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.place_list_items, parent, false);
        }

        // Setting a custom TypeFace for various TextView.
        Typeface nameTypeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/Timeline.ttf");
        Typeface addressTypeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/Nunito-Regular.ttf");

        // Get the {@link Place} object located at this position in the list
        Place currentPlace = getItem(position);

        // Find the name of the place and set it on the appropriate view
        String name = currentPlace.getPlaceName();
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.list_item_name);
        nameTextView.setText(name);
        nameTextView.setTypeface(nameTypeFace);

        // Find the address of the place and set it on the appropriate view
        String address = currentPlace.getPlaceAddress();
        TextView addressTextView = (TextView) listItemView.findViewById(R.id.list_item_address);
        addressTextView.setText(address);
        addressTextView.setTypeface(addressTypeFace);

        // Find the imageURL of the place and set it on the appropriate view
        final int imageID = currentPlace.getPlaceImageResourceID();
        final ImageButton itemImageButton = (ImageButton)
                listItemView.findViewById(R.id.list_item_image);
        itemImageButton.setImageResource(imageID);

        /** Find the description, siteURL, and location of the place and pass it to another
         *  activity through an intent
         */
        String description = currentPlace.getPlaceDescription();
        String siteURL = currentPlace.getPlaceSiteURL();
        String location = currentPlace.getPlaceLocation();

        /** Create an implicit intent to display the detailed place information if the user
         *  clicks on the list item
         */
        final Intent itemIntent = new Intent(context, PlaceDetailsActivity.class);

        //Put the properties of the Object to the intent
        itemIntent.putExtra(context.getString(R.string.name_var), name);
        itemIntent.putExtra(context.getString(R.string.desc_var), description);
        itemIntent.putExtra(context.getString(R.string.img_var), imageID);
        itemIntent.putExtra(context.getString(R.string.site_var), siteURL);
        itemIntent.putExtra(context.getString(R.string.loc_var), location);

        // Start the intent if the user clicks on the list item
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(itemIntent);
            }
        });

        return listItemView;
    }
}
