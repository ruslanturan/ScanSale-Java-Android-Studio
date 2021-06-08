package com.IOStarter.scansale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;

public class ActivityKinds extends AppCompatActivity {
    public static RecyclerView categories;
    public static Context kindContext;
    public static ListView kindsList;
    public static ProgressDialog progressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_kinds);
        kindContext = this;
        categories = (RecyclerView) findViewById(R.id.categories);
        LayoutParams layoutParams = new LayoutParams(-1, ActivityMain.android_height / 8);
        layoutParams.setMargins(0, ActivityMain.android_height / 10, 0, 0);
        categories.setLayoutParams(layoutParams);
        categories.setHasFixedSize(true);
        kindsList = (ListView) findViewById(R.id.kindList);
        LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        layoutParams2.setMargins(0, ActivityMain.android_height / 5, 0, 0);
        kindsList.setLayoutParams(layoutParams2);
        categories.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }
}
