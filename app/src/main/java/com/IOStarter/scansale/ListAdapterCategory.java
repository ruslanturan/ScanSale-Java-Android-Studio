package com.IOStarter.scansale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class ListAdapterCategory extends ArrayAdapter<ClassCategory> {

    /* renamed from: Id */
    public static Integer f57Id;
    /* access modifiers changed from: private */
    public Context cContext;
    int cResource;

    public ListAdapterCategory(Context context, int i, ArrayList<ClassCategory> arrayList) {
        super(context, i, arrayList);
        this.cContext = context;
        this.cResource = i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        String name = ((ClassCategory) getItem(i)).getName();
        final Integer id = ((ClassCategory) getItem(i)).getId();
        new ClassCategory(id, name);
        View inflate = LayoutInflater.from(this.cContext).inflate(this.cResource, viewGroup, false);
        Button button = (Button) inflate.findViewById(R.id.btnCategories);
        CardView cardView = (CardView) inflate.findViewById(R.id.crdCategories);
        button.setText(name);
        if (name.length() > 20) {
            ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
            layoutParams.height = 300;
            button.setHeight(300);
            cardView.setLayoutParams(layoutParams);
        }
        button.setId(id.intValue());
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListAdapterCategory.f57Id = id;
                ListAdapterCategory.this.cContext.startActivity(new Intent(ListAdapterCategory.this.cContext, ActivityKinds.class));
                new fetchKinds().execute(new Void[0]);
            }
        });
        return inflate;
    }
}
