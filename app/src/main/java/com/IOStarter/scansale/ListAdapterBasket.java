package com.IOStarter.scansale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListAdapterBasket extends ArrayAdapter<ClassBasket> {
    /* access modifiers changed from: private */
    public static DecimalFormat df = new DecimalFormat("0.0");
    public static String forBack = "";
    /* access modifiers changed from: private */
    public Context Context;
    int Resource;

    public ListAdapterBasket(Context context, int i, ArrayList<ClassBasket> arrayList) {
        super(context, i, arrayList);
        this.Context = context;
        this.Resource = i;
        ActivityBasket.totalNum = Float.valueOf(0.0f);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ActivityBasket.totalNum = Float.valueOf(ActivityBasket.totalNum.floatValue() + (((float) ((ClassBasket) arrayList.get(i2)).getItem_count().intValue()) * Float.parseFloat(((ClassBasket) arrayList.get(i2)).getCost())));
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        final Integer id = ((ClassBasket) getItem(i)).getId();
        final String cost = ((ClassBasket) getItem(i)).getCost();
        final Integer count = ((ClassBasket) getItem(i)).getCount();
        Integer item_count = ((ClassBasket) getItem(i)).getItem_count();
        String name = ((ClassBasket) getItem(i)).getName();
        String photo = ((ClassBasket) getItem(i)).getPhoto();
        String uniqueNum = ((ClassBasket) getItem(i)).getUniqueNum();
        new ClassBasket(id, name, count, cost, photo, ListAdapterProducts.UniqueNum);
        View inflate = LayoutInflater.from(this.Context).inflate(this.Resource, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.textName);
        textView.setText(name);
        final TextView textView2 = (TextView) inflate.findViewById(R.id.textCost);
        Float valueOf = Float.valueOf(((float) item_count.intValue()) * Float.parseFloat(cost));
        StringBuilder sb = new StringBuilder();
        sb.append(item_count);
        sb.append(" X ");
        sb.append(cost);
        sb.append(" GEL = ");
        sb.append(df.format(valueOf));
        sb.append(" GEL");
        textView2.setText(sb.toString());
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.basket_photo);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("https://myoutlet.ge/Images/Product-images/");
        sb2.append(uniqueNum);
        sb2.append("/");
        sb2.append(photo);
        String sb3 = sb2.toString();
        new DownloadImageTask(imageButton).execute(new String[]{sb3});
        ((Button) inflate.findViewById(R.id.remove_from_basket)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                for (int i = 0; i < ActivityHome.basket.size(); i++) {
                    if (id == ((ClassBasket) ActivityHome.basket.get(i)).getId()) {
                        ActivityHome.basket.remove(i);
                    }
                }
                if (ActivityHome.basket.size() < 1) {
                    ActivityBasket.total.setText("სულ: 0.0 GEL");
                    if (ListAdapterBasket.forBack == "Qr") {
                        ListAdapterBasket.this.Context.startActivity(new Intent(ListAdapterBasket.this.Context, ActivityProductswithQr.class));
                    } else {
                        new fetchProducts().execute(new Void[0]);
                        ListAdapterBasket.this.Context.startActivity(new Intent(ListAdapterBasket.this.Context, ActivityProducts.class));
                    }
                }
                ActivityBasket.totalNum = Float.valueOf(ActivityBasket.totalNum.floatValue() - (((float) Integer.valueOf(Integer.parseInt(textView2.getText().toString().split(" ")[0])).intValue()) * Float.parseFloat(cost)));
                TextView textView = ActivityBasket.total;
                StringBuilder sb = new StringBuilder();
                sb.append("სულ: ");
                sb.append(ListAdapterBasket.df.format(ActivityBasket.totalNum));
                sb.append(" GEL");
                textView.setText(sb.toString());
                ActivityBasket.basketList.setAdapter(new ListAdapterBasket(ListAdapterBasket.this.Context, R.layout.adaper_basket, ActivityHome.basket));
            }
        });
        final int i2 = i;
        ((Button) inflate.findViewById(R.id.decrease)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Integer valueOf = Integer.valueOf(Integer.parseInt(textView2.getText().toString().split(" ")[0]));
                if (valueOf.intValue() > 1) {
                    Integer valueOf2 = Integer.valueOf(valueOf.intValue() - 1);
                    ((ClassBasket) ListAdapterBasket.this.getItem(i2)).setItem_count(valueOf2);
                    Float valueOf3 = Float.valueOf(((float) valueOf2.intValue()) * Float.parseFloat(cost));
                    TextView textView = textView2;
                    StringBuilder sb = new StringBuilder();
                    sb.append(valueOf2);
                    sb.append(" X ");
                    sb.append(cost);
                    sb.append(" GEL = ");
                    sb.append(ListAdapterBasket.df.format(valueOf3));
                    String str = " GEL";
                    sb.append(str);
                    textView.setText(sb.toString());
                    ActivityBasket.totalNum = Float.valueOf(ActivityBasket.totalNum.floatValue() - Float.parseFloat(cost));
                    TextView textView2 = ActivityBasket.total;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("სულ: ");
                    sb2.append(ListAdapterBasket.df.format(ActivityBasket.totalNum));
                    sb2.append(str);
                    textView2.setText(sb2.toString());
                }
            }
        });
        Button button = (Button) inflate.findViewById(R.id.increase);
        final TextView textView3 = textView2;
        OnClickListener r0 = new OnClickListener() {
            public void onClick(View view) {
                Integer valueOf = Integer.valueOf(Integer.parseInt(textView3.getText().toString().split(" ")[0]));
                if (valueOf.intValue() < count.intValue()) {
                    Integer valueOf2 = Integer.valueOf(valueOf.intValue() + 1);
                    ((ClassBasket) ListAdapterBasket.this.getItem(i2)).setItem_count(valueOf2);
                    Float valueOf3 = Float.valueOf(((float) valueOf2.intValue()) * Float.parseFloat(cost));
                    TextView textView = textView3;
                    StringBuilder sb = new StringBuilder();
                    sb.append(valueOf2);
                    sb.append(" X ");
                    sb.append(cost);
                    sb.append(" GEL = ");
                    sb.append(ListAdapterBasket.df.format(valueOf3));
                    String str = " GEL";
                    sb.append(str);
                    textView.setText(sb.toString());
                    ActivityBasket.totalNum = Float.valueOf(ActivityBasket.totalNum.floatValue() + Float.parseFloat(cost));
                    TextView textView2 = ActivityBasket.total;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("სულ: ");
                    sb2.append(ListAdapterBasket.df.format(ActivityBasket.totalNum));
                    sb2.append(str);
                    textView2.setText(sb2.toString());
                }
            }
        };
        button.setOnClickListener(r0);
        CardView cardView = (CardView) inflate.findViewById(R.id.crdProductImg);
        LayoutParams layoutParams = new LayoutParams(ActivityMain.android_width / 3, ActivityMain.android_width / 3);
        layoutParams.setMargins((ActivityMain.android_width - (ActivityMain.android_width / 3)) / 2, (ActivityMain.android_width / 9) + 10, 0, 0);
        cardView.setLayoutParams(layoutParams);
        CardView cardView2 = (CardView) inflate.findViewById(R.id.crdDecrease);
        LayoutParams layoutParams2 = new LayoutParams(ActivityMain.android_width / 12, ActivityMain.android_width / 12);
        layoutParams2.setMargins((ActivityMain.android_width * 1) / 9, ((ActivityMain.android_width * 2) / 9) + 10, 0, 0);
        cardView2.setLayoutParams(layoutParams2);
        CardView cardView3 = (CardView) inflate.findViewById(R.id.crdIncrease);
        LayoutParams layoutParams3 = new LayoutParams(ActivityMain.android_width / 12, ActivityMain.android_width / 12);
        layoutParams3.setMargins((ActivityMain.android_width * 7) / 9, ((ActivityMain.android_width * 2) / 9) + 10, 0, 0);
        cardView3.setLayoutParams(layoutParams3);
        CardView cardView4 = (CardView) inflate.findViewById(R.id.crdRemove_from_basket);
        LayoutParams layoutParams4 = new LayoutParams(ActivityMain.android_width / 12, ActivityMain.android_width / 12);
        layoutParams4.setMargins(ActivityMain.android_width - (((ActivityMain.android_width * 1) / 9) + 10), 10, 0, 0);
        cardView4.setLayoutParams(layoutParams4);
        LayoutParams layoutParams5 = new LayoutParams(-1, -2);
        layoutParams5.setMargins(0, ((ActivityMain.android_width * 5) / 9) + 20, 0, 0);
        textView2.setLayoutParams(layoutParams5);
        LayoutParams layoutParams6 = new LayoutParams(-1, -2);
        layoutParams6.setMargins(0, ((ActivityMain.android_width * 4) / 9) + 10, 0, 0);
        textView.setLayoutParams(layoutParams6);
        return inflate;
    }
}