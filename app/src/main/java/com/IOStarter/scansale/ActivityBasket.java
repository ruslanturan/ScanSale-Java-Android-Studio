package com.IOStarter.scansale;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityBasket extends AppCompatActivity {
    public static ListView basketList;
    public static Context context;
    public static String desc;
    public static ImageButton goToPay;
    public static String price;
    public static TextView total;
    public static Float totalNum = Float.valueOf(0.0f);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_basket);
        context = this;
        basketList = (ListView) findViewById(R.id.basketList);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(0, ((ActivityMain.android_width * 10) / 54) + 50, 0, 0);
        basketList.setLayoutParams(layoutParams);
        basketList.setAdapter(new ListAdapterBasket(this, R.layout.adaper_basket, ActivityHome.basket));
        TextView textView = (TextView) findViewById(R.id.total);
        total = textView;
        StringBuilder sb = new StringBuilder();
        sb.append("სულ: ");
        sb.append(totalNum.toString());
        sb.append(" GEL");
        textView.setText(sb.toString());
        LayoutParams layoutParams2 = new LayoutParams((ActivityMain.android_width * 2) / 3, ActivityMain.android_width / 6);
        layoutParams2.setMargins(0, 20, 0, 0);
        total.setLayoutParams(layoutParams2);
        goToPay = (ImageButton) findViewById(R.id.goToPay);
        LayoutParams layoutParams3 = new LayoutParams(ActivityMain.android_width / 3, ActivityMain.android_width / 6);
        layoutParams3.setMargins((ActivityMain.android_width * 2) / 3, 20, 0, 0);
        goToPay.setLayoutParams(layoutParams3);
        goToPay.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str = " GEL";
                int i = 0;
                Integer valueOf = Integer.valueOf(Math.round(Float.valueOf(Float.parseFloat(ActivityBasket.total.getText().toString().split(str)[0].substring(4)) * 100.0f).floatValue()));
                StringBuilder sb = new StringBuilder();
                String str2 = "";
                sb.append(str2);
                sb.append(valueOf);
                ActivityBasket.price = sb.toString();
                ActivityBasket.desc = str2;
                while (true) {
                    String str3 = " / ";
                    if (i < ActivityHome.basket.size()) {
                        Float valueOf2 = Float.valueOf(((float) ((ClassBasket) ActivityHome.basket.get(i)).getItem_count().intValue()) * Float.valueOf(Float.parseFloat(((ClassBasket) ActivityHome.basket.get(i)).getCost())).floatValue());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(ActivityBasket.desc);
                        sb2.append(str3);
                        sb2.append(((ClassBasket) ActivityHome.basket.get(i)).getName());
                        sb2.append(" - ");
                        sb2.append(((ClassBasket) ActivityHome.basket.get(i)).getItem_count());
                        sb2.append(" X ");
                        sb2.append(((ClassBasket) ActivityHome.basket.get(i)).getCost());
                        sb2.append(" GEL = ");
                        sb2.append(valueOf2);
                        sb2.append(str);
                        ActivityBasket.desc = sb2.toString();
                        i++;
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(ActivityBasket.desc);
                        sb3.append(str3);
                        ActivityBasket.desc = sb3.toString();
                        ActivityBasket.this.startActivity(new Intent(ActivityBasket.this.getApplicationContext(), ActivityBuyer.class));
                        return;
                    }
                }
            }
        });
    }
}
