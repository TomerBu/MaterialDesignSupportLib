package tomerbu.edu.transitionsdemo;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tomerbuzaglo on 11/07/2016.
 * Copyright 2016 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private Context context;
    String[] dummyTexts = {"Urias persuadere, tanquam superbus vigil.",
            "Paradox doesn’t cheerfully believe any cow — but the master is what disturbs.",
            "Squeezed cauliflower can be made fresh by brushing with honey.",
            "How cloudy. You fire like a girl.",
            "Malfunction tightly like a seismic planet."};

    ArrayList<Integer> images = new ArrayList<>(5);

    public MyRecyclerAdapter(Context context) {
        this.context = context;
        Resources resources = context.getResources();
        for (int i = 0; i < dummyTexts.length; i++) {
            int id = resources.getIdentifier(String.format("cheese_%d", i + 1), "drawable", getClass().getPackage().getName());
            images.add(id);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.demo_items, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(dummyTexts[position % dummyTexts.length]);
        holder.ivImage.setImageResource(images.get(position % images.size()));
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textView);
            ivImage = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}
