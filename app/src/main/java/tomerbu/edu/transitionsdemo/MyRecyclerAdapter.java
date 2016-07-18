package tomerbu.edu.transitionsdemo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by tomerbuzaglo on 11/07/2016.
 * Copyright 2016 tomerbuzaglo. All Rights Reserved
 * <p/>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    public static final String EXTRA_CHEESE = "Cheese";
    private Context context;
    ArrayList<Cheese> data = new ArrayList<>();

    public MyRecyclerAdapter(Context context) {
        this.context = context;
        initDummyData(context);
    }

    private void initDummyData(Context context) {
        Resources resources = context.getResources();
        String[] dummyTexts = {"Urias persuadere, tanquam superbus vigil.",
                "Paradox doesn’t cheerfully believe any cow — but the master is what disturbs.",
                "Squeezed cauliflower can be made fresh by brushing with honey.",
                "How cloudy. You fire like a girl.",
                "Malfunction tightly like a seismic planet."};

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < dummyTexts.length; j++) {
                int id = resources.getIdentifier(String.format(Locale.US, "cheese_%d", j + 1), "drawable", getClass().getPackage().getName());
                Cheese cheese = new Cheese(dummyTexts[j], id);
                data.add(cheese);
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.demo_items, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Cheese cheese = data.get(position);

        holder.tvTitle.setText(cheese.getDescription());
        holder.ivImage.setImageResource(cheese.getImageResID());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(EXTRA_CHEESE, cheese);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.tvTitle.setTransitionName("description");
                    holder.ivImage.setTransitionName("image");

                    View tvTitle = holder.tvTitle;
                    View ivImage = holder.ivImage;


                    Pair<View, String> pair1 = Pair.create(tvTitle, tvTitle.getTransitionName());
                    Pair<View, String> pair2 = Pair.create(ivImage, ivImage.getTransitionName());

                    MainActivity act = (MainActivity) context;
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(act, pair1, pair2);

                    Transition transition = TransitionInflater.from(act).inflateTransition(R.transition.arc_bounds);
     /*               Transition transition = new Slide();

                    ArcMotion arc = new ArcMotion();
                    arc.setMinimumHorizontalAngle(90);
                    transition.setPathMotion(arc);*/

                    act.getWindow().setSharedElementEnterTransition(transition);
                    act.getWindow().setSharedElementExitTransition(transition);
                    act.getWindow().setSharedElementReenterTransition(transition);
                    act.getWindow().setSharedElementReturnTransition(transition);


                    ActivityCompat.startActivity(act, intent, options.toBundle());
                } else {
                    context.startActivity(intent);
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemView;
        TextView tvTitle;
        ImageView ivImage;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = (RelativeLayout) itemView.findViewById(R.id.layout);
            tvTitle = (TextView) itemView.findViewById(R.id.textView);
            ivImage = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }


}
