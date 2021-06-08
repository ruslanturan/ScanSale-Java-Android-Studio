package com.IOStarter.scansale;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityImage extends AppCompatActivity {
    public static ImageView image;
    public static Bitmap imgSrc;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_image);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        image = imageView;
        imageView.setImageBitmap(imgSrc);
    }
}
