package com.IOStarter.scansale;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityProducts extends AppCompatActivity {
    public static ImageButton basket;
    public static CardView crdBasket;
    public static RecyclerView kinds;
    public static Context productsContext;
    public static GridView productsList;
    public static ProgressDialog progressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_products);
        productsContext = this;
        productsList = (GridView) findViewById(R.id.ProductsList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.h_kinds);
        kinds = recyclerView;
        recyclerView.setHasFixedSize(true);
        kinds.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        basket = (ImageButton) findViewById(R.id.basket);
        crdBasket = (CardView) findViewById(R.id.crdBasket);
        LayoutParams layoutParams = new LayoutParams(ActivityMain.android_width / 5, ActivityMain.android_width / 5);
        layoutParams.setMargins(ActivityMain.android_width - ((ActivityMain.android_width / 5) + 50), ActivityMain.android_height - ((ActivityMain.android_width / 5) + 230), 0, 0);
        crdBasket.setLayoutParams(layoutParams);
        LayoutParams layoutParams2 = new LayoutParams(-1, ActivityMain.android_height / 8);
        layoutParams2.setMargins(0, ActivityMain.android_height / 10, 0, 0);
        kinds.setLayoutParams(layoutParams2);
        LayoutParams layoutParams3 = new LayoutParams(-1, -1);
        layoutParams3.setMargins(0, ActivityMain.android_height / 5, 0, 0);
        productsList.setLayoutParams(layoutParams3);
        basket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ActivityHome.basket.size() < 1) {
                    Toast.makeText(ActivityProducts.this.getApplicationContext(), "კალათა ცარიელია", Toast.LENGTH_LONG).show();
                    return;
                }
                ListAdapterBasket.forBack = "noQr";
                ActivityProducts.this.startActivity(new Intent(ActivityProducts.this.getApplicationContext(), ActivityBasket.class));
            }
        });
    }
}
