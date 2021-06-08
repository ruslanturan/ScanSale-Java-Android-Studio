package com.IOStarter.scansale;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ActivityProductswithQr extends AppCompatActivity {
    public static ImageButton basketQr;
    public static Context context;
    public static CardView crdBasketQr;
    public static GridView productsListQr;
    public static ProgressDialog progressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        context = this;
        setContentView((int) R.layout.activity_productswith_qr);
        productsListQr = (GridView) findViewById(R.id.ProductswithQrList);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(0, ActivityMain.android_height / 10, 0, 0);
        productsListQr.setLayoutParams(layoutParams);
        basketQr = (ImageButton) findViewById(R.id.basketQr);
        crdBasketQr = (CardView) findViewById(R.id.crdBasketQr);
        LayoutParams layoutParams2 = new LayoutParams(ActivityMain.android_width / 5, ActivityMain.android_width / 5);
        layoutParams2.setMargins(ActivityMain.android_width - ((ActivityMain.android_width / 5) + 50), ActivityMain.android_height - ((ActivityMain.android_width / 5) + 230), 0, 0);
        crdBasketQr.setLayoutParams(layoutParams2);
        new fetchProductswithQr().execute(new Void[0]);
        ImageButton imageButton = (ImageButton) findViewById(R.id.basketQr);
        basketQr = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ActivityHome.basket.size() < 1) {
                    Toast.makeText(ActivityProductswithQr.this.getApplicationContext(), "კალათა ცარიელია", Toast.LENGTH_LONG).show();
                    return;
                }
                ListAdapterBasket.forBack = "Qr";
                ActivityProductswithQr.this.startActivity(new Intent(ActivityProductswithQr.this.getApplicationContext(), ActivityBasket.class));
            }
        });
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String str = "დიახ";
        builder.setMessage((CharSequence) "ფასდაკლებით ფასებს დაკარგავთ. ისევ გსურთ ამ ფანჯრის დახურვა?").setCancelable(false).setNegativeButton((CharSequence) "არა", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setPositiveButton((CharSequence) str, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityProductswithQr.context.startActivity(new Intent(ActivityProductswithQr.context, ActivityHome.class));
            }
        });
        AlertDialog create = builder.create();
        create.setTitle("ყურადღება");
        create.show();
    }
}
