package com.pratiksymz.android.menlolookaround.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pratiksymz.android.menlolookaround.models.Place;
import com.pratiksymz.android.menlolookaround.R;
import com.pratiksymz.android.menlolookaround.activities.PlaceDetailsActivity;

import java.util.ArrayList;


public class PlaceAdapter extends ArrayAdapter<Place> {

    private Context context = getContext();

    // Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private Animator mCurrentAnimator;

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;

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

        // Hook up clicks on the thumbnail views.
        final View thumbView = listItemView.findViewById(R.id.list_item_image);
        final View finalListItemView = listItemView;
        thumbView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbView, imageID, finalListItemView);
            }
        });

        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = getContext().getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        return listItemView;
    }

    private void zoomImageFromThumb(final View thumbView, int imageResId, View listItemView) {

        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView)
                listItemView.findViewById(R.id.expanded_image);
        if (imageResId != -1) {
            expandedImageView.setImageResource(imageResId);
        } else {
            expandedImageView.setImageResource(R.drawable.shadow);
        }

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        listItemView.findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}