package com.IOStarter.scansale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ListAdapterProducts extends ArrayAdapter<ClassProducts> {

    /* renamed from: Id */
    public static Integer f59Id;
    public static String UniqueNum;
    /* access modifiers changed from: private */
    public Context psContext;
    int psResource;

    public ListAdapterProducts(Context context, int i, ArrayList<ClassProducts> arrayList) {
        super(context, i, arrayList);
        this.psContext = context;
        this.psResource = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        final Integer id = ((ClassProducts) getItem(i)).getId();
        String sale = ((ClassProducts) getItem(i)).getSale();
        String saledCost = ((ClassProducts) getItem(i)).getSaledCost();
        if (saledCost.endsWith(".0")) {
            saledCost = saledCost.substring(0, saledCost.length() - 2);
        }
        String str = saledCost;
        final String uniqueNum = ((ClassProducts) getItem(i)).getUniqueNum();
        String productName = ((ClassProducts) getItem(i)).getProductName();
        String photo = ((ClassProducts) getItem(i)).getPhoto();
        new ClassProducts(id, sale, str, uniqueNum, productName, photo);
        View inflate = LayoutInflater.from(this.psContext).inflate(this.psResource, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.txtName)).setText(productName);
        TextView textView = (TextView) inflate.findViewById(R.id.txtCost);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" GEL");
        textView.setText(sb.toString());
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.crdProduct);
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = (ActivityMain.android_width / 2) - 10;
        relativeLayout.setLayoutParams(layoutParams);
        TextView textView2 = (TextView) inflate.findViewById(R.id.txtSale);
        CardView cardView = (CardView) inflate.findViewById(R.id.crdSale);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("-");
        sb2.append(sale);
        sb2.append(" %");
        textView2.setText(sb2.toString());
        cardView.setVisibility(0);
        if (sale.indexOf("0.0") > -1 && sale.length() == 3) {
            cardView.setVisibility(4);
        }
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageButton2);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListAdapterProducts.this.psContext.startActivity(new Intent(ListAdapterProducts.this.psContext, ActivityProduct.class));
                ListAdapterProducts.f59Id = id;
                ListAdapterProducts.UniqueNum = uniqueNum;
                new fetchProduct().execute(new Void[0]);
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListAdapterProducts.this.psContext.startActivity(new Intent(ListAdapterProducts.this.psContext, ActivityProduct.class));
                ListAdapterProducts.f59Id = id;
                ListAdapterProducts.UniqueNum = uniqueNum;
                new fetchProduct().execute(new Void[0]);
            }
        });
        StringBuilder sb3 = new StringBuilder();
        sb3.append("https://myoutlet.ge/Images/Product-images/");
        sb3.append(uniqueNum);
        sb3.append("/");
        sb3.append(photo);
        String sb4 = sb3.toString();
        new DownloadImageTask(imageView).execute(new String[]{sb4});
        return inflate;
    }
}
