package com.IOStarter.scansale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.common.util.CrashUtils;
import java.util.ArrayList;

public class ActivityHome extends AppCompatActivity {
    public static ArrayList<ClassBasket> basket = new ArrayList<>();
    public static Context context;
    ImageButton btnQR;
    ImageButton btnSales;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_home);
        context = this;
        this.btnSales = (ImageButton) findViewById(R.id.btnSales);
        LayoutParams layoutParams = new LayoutParams((ActivityMain.android_width * 7) / 10, ((ActivityMain.android_width * 7) / 10) / 4);
        layoutParams.setMargins((ActivityMain.android_width - ((ActivityMain.android_width * 7) / 10)) / 2, ActivityMain.android_height - ((ActivityMain.android_width * 5) / 10), 0, 0);
        this.btnSales.setLayoutParams(layoutParams);
        this.btnQR = (ImageButton) findViewById(R.id.btnQr);
        LayoutParams layoutParams2 = new LayoutParams((ActivityMain.android_width * 7) / 10, ((ActivityMain.android_width * 7) / 10) / 4);
        layoutParams2.setMargins((ActivityMain.android_width - ((ActivityMain.android_width * 7) / 10)) / 2, ActivityMain.android_height - ((ActivityMain.android_width * 7) / 10), 0, 0);
        this.btnQR.setLayoutParams(layoutParams2);
        this.btnSales.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ActivityHome.this.startActivity(new Intent(ActivityHome.this.getApplicationContext(), ActivityCategories.class));
            }
        });
        this.btnQR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ActivityHome.this.startActivity(new Intent(ActivityHome.this.getApplicationContext(), ActivityScanQr.class));
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(CrashUtils.ErrorDialogData.BINDER_CRASH);
        startActivity(intent);
    }
}
