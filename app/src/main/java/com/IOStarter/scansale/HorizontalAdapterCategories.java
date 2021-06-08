package com.IOStarter.scansale;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import java.util.List;

public class HorizontalAdapterCategories extends Adapter<ViewHolder> {
    private List<ClassCategory> items;
    private Context kContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout nTextView;
        public TextView nameTextView;

        public ViewHolder(View view) {
            super(view);
            this.nameTextView = (TextView) view.findViewById(R.id.btnHorizontalCategories);
            this.nTextView = (LinearLayout) view.findViewById(R.id.hor_cats);
        }
    }

    public HorizontalAdapterCategories(Context context, List<ClassCategory> list) {
        this.kContext = context;
        this.items = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_adapter_categories, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ViewHolder viewHolder = (ViewHolder)holder;
        ClassCategory classCategory = (ClassCategory) this.items.get(i);
        final TextView textView = viewHolder.nameTextView;
        LinearLayout linearLayout = viewHolder.nTextView;
        textView.setText(classCategory.getName());
        textView.setId(classCategory.getId().intValue());
        Integer valueOf = Integer.valueOf(0);
        linearLayout.setId(valueOf.intValue());
        Integer.valueOf(valueOf.intValue() + 1);
        Integer valueOf2 = Integer.valueOf(((classCategory.getName().length() * ActivityMain.android_width) / 24) + (ActivityMain.android_width / 12));
        int id = textView.getId();
        textView.setTextColor(Color.rgb(104, 104, 104));
        textView.setTypeface(null, 0);
        if (id == ListAdapterCategory.f57Id.intValue()) {
            textView.setTextColor(Color.rgb(225, 130, 32));
            textView.setTypeface(null, 1);
            ActivityKinds.categories.smoothScrollToPosition(Integer.valueOf(fetchCategories.categories.indexOf(classCategory)).intValue());
        }
        linearLayout.setLayoutParams(new LayoutParams(valueOf2.intValue(), ActivityMain.android_height / 9));
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ListAdapterCategory.f57Id = Integer.valueOf(textView.getId());
                new fetchKinds().execute(new Void[0]);
            }
        });
    }




    public int getItemCount() {
        return this.items.size();
    }
}
