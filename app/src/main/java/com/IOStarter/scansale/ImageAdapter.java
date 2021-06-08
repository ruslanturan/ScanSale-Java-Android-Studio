package com.IOStarter.scansale;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends PagerAdapter {
    public static List<Bitmap> mImageLogos = new ArrayList();
    /* access modifiers changed from: private */
    public Context mContext;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    ImageAdapter(Context context, List<Bitmap> list) {
        this.mContext = context;
        mImageLogos = list;
    }

    public int getCount() {
        return mImageLogos.size();
    }

    public Object instantiateItem(ViewGroup viewGroup, final int i) {
        ImageView imageView = new ImageView(this.mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageBitmap((Bitmap) mImageLogos.get(i));
        viewGroup.addView(imageView, 0);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ActivityImage.imgSrc = (Bitmap) ImageAdapter.mImageLogos.get(i);
                ImageAdapter.this.mContext.startActivity(new Intent(ImageAdapter.this.mContext, ActivityImage.class));
            }
        });
        return imageView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((ImageView) obj);
    }
}