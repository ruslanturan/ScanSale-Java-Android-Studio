package com.IOStarter.scansale;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

public class ActivityProduct extends AppCompatActivity {
    public static TextView imgCount;
    public static CardView pictures;
    public static Context productContext;
    public static ListView productInfo;
    public static ProgressDialog progressDialog;
    public static ViewPager viewPager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_product);
        productContext = this;
        productInfo = (ListView) findViewById(R.id.info);
        LayoutParams layoutParams = new LayoutParams(-1, (ActivityMain.android_height * 25) / 37);
        layoutParams.setMargins(0, (ActivityMain.android_height - ((ActivityMain.android_height * 25) / 37)) + 40, 0, 0);
        productInfo.setLayoutParams(layoutParams);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pictures = (CardView) findViewById(R.id.pictures);
        pictures.setLayoutParams(new LayoutParams(-1, (ActivityMain.android_height * 10) / 37));
        imgCount = (TextView) findViewById(R.id.imgCount);
        LayoutParams layoutParams2 = new LayoutParams(-1, 60);
        layoutParams2.setMargins(0, (ActivityMain.android_height * 10) / 37, 0, 0);
        imgCount.setLayoutParams(layoutParams2);
    }
}
