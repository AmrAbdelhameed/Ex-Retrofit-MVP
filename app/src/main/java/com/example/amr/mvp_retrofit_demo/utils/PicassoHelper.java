package com.example.amr.mvp_retrofit_demo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.amr.mvp_retrofit_demo.R;
import com.squareup.picasso.Picasso;

public class PicassoHelper {
    public void loadImage(Context context, String imageURL, ImageView imageView) {
        Picasso.with(context)
                .load(imageURL)
                .placeholder(R.drawable.default_image)
                .into(imageView);
    }
}