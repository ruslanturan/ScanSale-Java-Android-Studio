package com.IOStarter.scansale;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

public class ActivityMain extends AppCompatActivity {
    public static int SPLASH_TIME_OUT = 4000;
    public static int android_height;
    public static int android_width;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        android_height = displayMetrics.heightPixels;
        android_width = displayMetrics.widthPixels;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ActivityMain.this.startActivity(new Intent(ActivityMain.this.getApplicationContext(), ActivityHome.class));
            }
        }, (long) SPLASH_TIME_OUT);
    }
}
