package com.IOStarter.scansale;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ActivityCategories extends AppCompatActivity {
    public static Context context;
    public static ListView list;
    public static ProgressDialog progressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_categories);
        context = this;
        list = (ListView) findViewById(R.id.list);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, ActivityMain.android_height - 140);
        layoutParams.setMargins(0, 140, 0, 0);
        list.setLayoutParams(layoutParams);
        context = this;
        new fetchCategories().execute(new Void[0]);
    }
}
