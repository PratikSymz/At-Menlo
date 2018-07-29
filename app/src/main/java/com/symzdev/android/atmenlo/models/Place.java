package com.symzdev.android.atmenlo.models;

public class Place {

    // Constant representing that no image resource ID has been provided
    private static final int NO_IMAGE_PROVIDED = -1;

    // The name of the place
    private String mPlaceName;

    // The address of the place
    private String mPlaceAddress;

    // The description of the place
    private String mPlaceDescription;

    // The ID of the image resource of the place
    private int mPlaceImageResourceID = NO_IMAGE_PROVIDED;

    // The site URL of the place
    private String mPlaceSiteURL;

    // The location coordinates of the place
    private String mPlaceLocation;


    // Public constructor of the Place object.
    public Place(String placeName, String placeAddress, String placeDescription,
                 int placeImageResourceID, String placeSiteUrl, String placeLocation) {

        mPlaceName = placeName;
        mPlaceAddress = placeAddress;
        mPlaceImageResourceID = placeImageResourceID;
        mPlaceDescription = placeDescription;
        mPlaceSiteURL = placeSiteUrl;
        mPlaceLocation = placeLocation;
    }

    // Get the name of the place
    public String getPlaceName() {
        return mPlaceName;
    }

    // Get the address of the place
    public String getPlaceAddress() {
        return mPlaceAddress;
    }

    // Get the description of the place
    public String getPlaceDescription() {
        return mPlaceDescription;
    }

    //Get the image resource ID of the place
    public int getPlaceImageResourceID() {
        return mPlaceImageResourceID;
    }

    // Get the site URL of the place
    public String getPlaceSiteURL() {
        return mPlaceSiteURL;
    }

    // Get the location coordinates of the place
    public String getPlaceLocation() {
        return mPlaceLocation;
    }
}