package com.pratiksymz.android.menlolookaround.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pratiksymz.android.menlolookaround.R;


public class PlaceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details);

        /** Get the properties of the Place Object
         *  received from the intent sent by PlaceAdapter
         */
        Bundle bundle = getIntent().getExtras();

        // Retrieve the name of the place from the intent
        String placeName = bundle.getString(getString(R.string.name_var));
        // Retrieve the description of the place from the intent
        String placeDescription = bundle.getString(getString(R.string.desc_var));
        // Retrieve the imageID of the place from the intent
        int placeImage = bundle.getInt(getString(R.string.img_var));
        // Retrieve the site of the place from the intent
        final String placeSite = bundle.getString(getString(R.string.site_var));
        // Retrieve the location of the place from the intent
        final String placeLocation = bundle.getString(getString(R.string.loc_var));

        // Set the name of the place as the title of the screen
        this.setTitle(placeName);

        Typeface aboutTypeFace = Typeface.createFromAsset(getAssets(), "fonts/Afton James.ttf");
        Typeface DescTypeFace = Typeface.createFromAsset(getAssets(), "fonts/mytype.ttf");
        Typeface moreInfoTypeFace = Typeface.createFromAsset(getAssets(), "fonts/Nunito-Bold.ttf");

        // Activate Up Button to enable the navigation back to the MainActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set the image to the ImageView in the activity_place_details.xml
        ImageView imageView = (ImageView) findViewById(R.id.detailed_image);
        imageView.setImageResource(placeImage);

        // Set the description to the TextView in the activity_place_details.xml
        TextView descriptionTextView = (TextView) findViewById(R.id.detailed_desc);
        descriptionTextView.setText(placeDescription);
        descriptionTextView.setTypeface(DescTypeFace);

        // Set the about text to the TextView in the activity_place_details.xml
        TextView aboutTextView = (TextView) findViewById(R.id.about);
        aboutTextView.setTypeface(aboutTypeFace);

        // Set the more info text to the TextView in the activity_place_details.xml
        TextView moreInfoTextView = (TextView) findViewById(R.id.more_info);
        moreInfoTextView.setTypeface(moreInfoTypeFace);

        // Create an intent for the website of the Place Object
        LinearLayout linkView = (LinearLayout) findViewById(R.id.link_webpage);
        linkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If clicking on the icon, goes to the site of the place
                Uri webPage = Uri.parse(placeSite);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                if (webIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(webIntent);
                }
            }
        });

        // Create an intent for the location of the Place Object
        LinearLayout mapView = (LinearLayout) findViewById(R.id.link_map);
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clicking on the icon, goes to the location of the place
                Uri geoLocation = Uri.parse("http://maps.google.com/maps?daddr=" + placeLocation + "?z=16");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoLocation);
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }
}