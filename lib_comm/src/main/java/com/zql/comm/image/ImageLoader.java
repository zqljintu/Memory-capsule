package com.zql.comm.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zql.base.utils.DisplayUtils;

/**
 * Create by Totoro
 * 2019-11-18 14:29
 * 需要自己往里加 图片加载
 **/
public class ImageLoader {


    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    public static void loadImageRound(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(new RequestOptions().centerCrop().override(DisplayUtils.dip2px(108), DisplayUtils.dip2px(108))).into(imageView);
    }
}
