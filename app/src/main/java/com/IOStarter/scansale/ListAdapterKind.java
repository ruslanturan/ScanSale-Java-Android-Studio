package com.IOStarter.scansale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ListAdapterKind extends ArrayAdapter<ClassKind> {

    /* renamed from: Id */
    public static Integer f58Id;
    /* access modifiers changed from: private */
    public Context kContext;
    int kResource;

    public ListAdapterKind(Context context, int i, ArrayList<ClassKind> arrayList) {
        super(context, i, arrayList);
        this.kContext = context;
        this.kResource = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        String name = ((ClassKind) getItem(i)).getName();
        String image = ((ClassKind) getItem(i)).getImage();
        final Integer id = ((ClassKind) getItem(i)).getId();
        new ClassKind(id, name, image);
        View inflate = LayoutInflater.from(this.kContext).inflate(this.kResource, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.txtKindName);
        CardView cardView = (CardView) inflate.findViewById(R.id.btnKind);
        CardView cardView2 = (CardView) inflate.findViewById(R.id.kindImg);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageButton);
        StringBuilder sb = new StringBuilder();
        sb.append("https://myoutlet.ge/Images/Kinds/");
        sb.append(image);
        String sb2 = sb.toString();
        new DownloadImageTask(imageView).execute(new String[]{sb2});
        textView.setText(name);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListAdapterKind.f58Id = id;
                ListAdapterKind.this.kContext.startActivity(new Intent(ListAdapterKind.this.kContext, ActivityProducts.class));
                new fetchProducts().execute(new Void[0]);
            }
        });
        return inflate;
    }
}
