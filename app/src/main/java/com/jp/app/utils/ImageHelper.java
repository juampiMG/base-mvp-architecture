package com.jp.app.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class ImageHelper {

    public static void loadImage(Context context, Uri uri, ImageView imageView, View progressView) {
        RequestBuilder requestBuilder = Glide.with(context)
                .load(uri)
                .transition(DrawableTransitionOptions.withCrossFade());
        loadProgressView(requestBuilder, progressView);
        requestBuilder.into(imageView);
    }

    private static void loadProgressView(RequestBuilder<Drawable> requestBuilder, final View progressView) {
        requestBuilder.listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                if (progressView != null) {
                    progressView.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (progressView != null) {
                    progressView.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

}
