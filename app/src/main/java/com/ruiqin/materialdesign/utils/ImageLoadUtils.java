package com.ruiqin.materialdesign.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class ImageLoadUtils {
    public static void load(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).into(imageView);
    }

    public static void load(Context context, int imageId, ImageView imageView) {
        Glide.with(context).load(imageId).into(imageView);
    }
}
