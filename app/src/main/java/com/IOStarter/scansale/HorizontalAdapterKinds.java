package com.IOStarter.scansale;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.RecyclerView.Adapter;

public class HorizontalAdapterKinds extends Adapter<ViewHolder> {
    private List<ClassKind> items;
    private Context kContext;

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        public LinearLayout nTextView;
        public TextView nameTextView;

        public ViewHolder(View view) {
            super(view);
            this.nameTextView = (TextView) view.findViewById(R.id.btnHorizontalKinds);
            this.nTextView = (LinearLayout) view.findViewById(R.id.hor_kinds);
        }
    }

    public HorizontalAdapterKinds(Context context, List<ClassKind> list) {
        this.kContext = context;
        this.items = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_adapter_kinds, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ViewHolder viewHolder = (ViewHolder)holder;
        ClassKind classKind = (ClassKind) this.items.get(i);
        final TextView textView = viewHolder.nameTextView;
        LinearLayout linearLayout = viewHolder.nTextView;
        textView.setText(classKind.getName());
        textView.setId(classKind.getId().intValue());
        Integer valueOf = Integer.valueOf(((classKind.getName().length() * ActivityMain.android_width) / 24) + (ActivityMain.android_width / 12));
        if (textView.getId() == ListAdapterKind.f58Id.intValue()) {
            textView.setTextColor(Color.rgb(225, 130, 32));
            textView.setTypeface(null, 1);
            ActivityProducts.kinds.smoothScrollToPosition(Integer.valueOf(fetchKinds.kinds.indexOf(classKind)).intValue());
        }
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(valueOf.intValue(), ActivityMain.android_height / 9));
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ListAdapterKind.f58Id = Integer.valueOf(textView.getId());
                new fetchProducts().execute(new Void[0]);
            }
        });
    }

    public int getItemCount() {
        return this.items.size();
    }
}

