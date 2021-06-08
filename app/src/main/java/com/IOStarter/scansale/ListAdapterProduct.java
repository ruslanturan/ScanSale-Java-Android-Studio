package com.IOStarter.scansale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListAdapterProduct extends ArrayAdapter<ClassProduct> {
    String basket_items_name = "";
    /* access modifiers changed from: private */
    public List<Bitmap> bitmaps = new ArrayList();
    /* access modifiers changed from: private */
    public Integer imgCount = Integer.valueOf(0);
    private Context pContext;
    int pResource;

    private class DownloadImageSliderTask extends AsyncTask<String, Void, Bitmap> {
        Context context;
        List<Bitmap> logos;

        private DownloadImageSliderTask(Context context2, List<Bitmap> list) {
            this.logos = list;
            this.context = context2;
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(String... strArr) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(new URL(strArr[0]).openStream());
                if (ListAdapterProduct.this.imgCount.intValue() > ListAdapterProduct.this.bitmaps.size()) {
                    ListAdapterProduct.this.bitmaps.add(bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            ActivityProduct.viewPager.setAdapter(new ImageAdapter(ActivityProduct.productContext, ListAdapterProduct.this.bitmaps));
        }
    }

    public ListAdapterProduct(Context context, int i, ArrayList<ClassProduct> arrayList) {
        super(context, i, arrayList);
        this.pContext = context;
        this.pResource = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        String str;
        JSONArray jSONArray;
        String str2 = "";
        Integer id = ((ClassProduct) getItem(i)).getId();
        String sale = ((ClassProduct) getItem(i)).getSale();
        String count = ((ClassProduct) getItem(i)).getCount();
        String saledCost = ((ClassProduct) getItem(i)).getSaledCost();
        if (saledCost.endsWith(".0")) {
            saledCost = saledCost.substring(0, saledCost.length() - 2);
        }
        String str3 = saledCost;
        String productName = ((ClassProduct) getItem(i)).getProductName();
        String partnerName = ((ClassProduct) getItem(i)).getPartnerName();
        String description = ((ClassProduct) getItem(i)).getDescription();
        JSONArray galleries = ((ClassProduct) getItem(i)).getGalleries();
        try {
            str = galleries.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(ActivityProduct.productContext, "შეამოწმეთ თქვენი ინტერნეტ კავშირი", Toast.LENGTH_LONG).show();
            str = str2;
        }
        JSONArray jSONArray2 = galleries;
        String str4 = productName;
        new ClassProduct(id, sale, str3, count, productName, partnerName, description, jSONArray2);
        View inflate = LayoutInflater.from(this.pContext).inflate(this.pResource, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.txtpCost);
        textView.setLayoutParams(new RelativeLayout.LayoutParams((ActivityMain.android_width * 16) / 41, ((ActivityMain.android_width * 25) / 41) / 4));
        StringBuilder sb = new StringBuilder();
        sb.append(str3);
        sb.append(" GEL");
        textView.setText(sb.toString());
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.cardProduct);
        String str5 = str4;
        ((TextView) inflate.findViewById(R.id.txtName)).setText(str5);
        TextView textView2 = (TextView) inflate.findViewById(R.id.txtDesc);
        String obj = Html.fromHtml(description).toString();
        while (true) {
            String str6 = "\\r";
            if (!obj.contains(str6)) {
                break;
            }
            obj = obj.replace(str6, str2);
        }
        while (true) {
            String str7 = "\\n";
            if (!obj.contains(str7)) {
                break;
            }
            obj = obj.replace(str7, str2);
        }
        while (true) {
            String str8 = "\n";
            if (!obj.contains(str8)) {
                break;
            }
            obj = obj.replace(str8, str2);
        }
        while (true) {
            String str9 = "\\";
            if (!obj.contains(str9)) {
                break;
            }
            obj = obj.replace(str9, str2);
        }
        textView2.setText(obj);
        this.imgCount = Integer.valueOf(jSONArray2.length());
        TextView textView3 = ActivityProduct.imgCount;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.imgCount);
        sb2.append(" ფოტო");
        textView3.setText(sb2.toString());
        int i2 = 0;
        while (i2 < jSONArray2.length()) {
            JSONException e;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("https://myoutlet.ge/Images/Product-images/");
            sb3.append(ListAdapterProducts.UniqueNum);
            sb3.append("/");
            jSONArray = jSONArray2;
            try {
                sb3.append(jSONArray.get(i2));
                String sb4 = sb3.toString();
                new DownloadImageSliderTask(ActivityProduct.productContext, this.bitmaps).execute(new String[]{sb4});
            } catch (JSONException e2) {
                e = e2;
            }
            i2++;
            jSONArray2 = jSONArray;
        }
        final ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.addToBasket);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((ActivityMain.android_width * 25) / 41, ((ActivityMain.android_width * 25) / 41) / 4);
        layoutParams.setMargins((ActivityMain.android_width * 16) / 41, 0, 0, 0);
        imageButton.setLayoutParams(layoutParams);
        TextView textView4 = (TextView) inflate.findViewById(R.id.on_basket);
        textView4.setLayoutParams(layoutParams);
        final TextView textView5 = textView4;
        String str10 = str5;
        final ClassBasket classBasket = new ClassBasket(id, str5, Integer.valueOf(Integer.parseInt(count)), str3, str, ListAdapterProducts.UniqueNum);
        for (int i3 = 0; i3 < ActivityHome.basket.size(); i3++) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(this.basket_items_name);
            sb5.append(((ClassBasket) ActivityHome.basket.get(i3)).getName());
            this.basket_items_name = sb5.toString();
        }
        if (this.basket_items_name.indexOf(str10) > -1) {
            textView5.setVisibility(0);
            imageButton.setVisibility(4);
        } else {
            textView5.setVisibility(4);
            imageButton.setVisibility(0);
        }
        imageButton.setId(Integer.parseInt(count));
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ActivityHome.basket.add(classBasket);
                imageButton.setVisibility(4);
                textView5.setVisibility(0);
            }
        });
        return inflate;
    }
}
