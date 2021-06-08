package com.IOStarter.scansale;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;

public class ActivityScanQr extends AppCompatActivity {
    public static ImageButton imageButton;
    public static ImageView imgRect;
    public static Context context;
    private int PERMISSION_CODE = 1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        context = this;
        setContentView((int) R.layout.activity_scan_qr);
        imgRect = (ImageView) findViewById(R.id.imgRect);
        LayoutParams layoutParams = new LayoutParams(ActivityMain.android_width, (ActivityMain.android_width * 410) / 396);
        layoutParams.setMargins(0, (ActivityMain.android_height * 7) / 73, 0, 0);
        imgRect.setLayoutParams(layoutParams);
        imageButton = (ImageButton) findViewById(R.id.btn_start_scan);
        LayoutParams layoutParams2 = new LayoutParams((ActivityMain.android_width * 7) / 10, ((ActivityMain.android_width * 7) / 10) / 4);
        layoutParams2.setMargins((ActivityMain.android_width - ((ActivityMain.android_width * 7) / 10)) / 2, 0, 0, 0);
        imageButton.setLayoutParams(layoutParams2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(context.getApplicationContext(), "android.permission.CAMERA") == 0) {
                    context.startActivity(new Intent(context.getApplicationContext(), ActivityScanning.class));
                }
                else {
                    new AlertDialog.Builder(context).setTitle("ნებართვაა საჭირო").setMessage("ScanSale მიიღებს კამერას QR კოდის წასაკითხად")
                            .setPositiveButton("ნება დართე", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.CAMERA},PERMISSION_CODE);
                                }
                            })
                            .setNegativeButton("უარყო", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();

                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                context.startActivity(new Intent(context.getApplicationContext(), ActivityScanning.class));
            }
            else {
                Toast.makeText(this,"პრობლემა",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(this, ActivityHome.class));
    }

}
