package com.nexdev.enyason.nestedrv.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nexdev.enyason.nestedrv.Model.Resurantlistdatahorizontal;
import com.nexdev.enyason.nestedrv.R;


import java.util.List;


import static com.nexdev.enyason.nestedrv.Model.Resurantlistdatahorizontal.hresturantlist_layout;

//public class ResurantliastadapterH {
//}
public class ResurantliastadapterH extends RecyclerView.Adapter {

    private List<Resurantlistdatahorizontal> horizontallist;
    private Context mCtx;

    public ResurantliastadapterH(List<Resurantlistdatahorizontal> horizontallist, Context mCtx ) {
        this.mCtx = mCtx;
        this.horizontallist = horizontallist;
    }

    @Override
    public int getItemViewType(int position) {
        switch (horizontallist.get(position).getViewtype()) {
            case 3:
                return hresturantlist_layout;
            default:
                return -1;

        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.resturantlisthorizontalmodel, parent, false);

        switch (viewtype) {
            case hresturantlist_layout:
                View resturantlistlayout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.resturantlisthorizontalmodel, viewGroup, false);
                return new ResurantliastadapterH.MyViewHolder(resturantlistlayout);

            default:
                return null;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int listPosition) {
        Resurantlistdatahorizontal hdata = horizontallist.get(listPosition);
        switch (horizontallist.get(listPosition).getViewtype()) {
            case hresturantlist_layout:

                Resurantlistdatahorizontal product = horizontallist.get(listPosition);
                ResurantliastadapterH.MyViewHolder vaultItemHolder = (ResurantliastadapterH.MyViewHolder) viewHolder;
                Glide.with(mCtx)
                        .load(product.getImage())
                        .into(vaultItemHolder.imageViewIcon);

                vaultItemHolder.textViewName.setText(product.getName());
                vaultItemHolder.restdesc.setText(product.getResturantshortdesc());
                //vaultItemHolder.rating.setNumStars(product.getRetviw());
                vaultItemHolder.rating.setRating(Float.parseFloat(product.getRetviw()));

                break;

            default:
                return;

        }


        //imageView.setImageResource(Integer.parseInt(horizontallist.get(listPosition).getImage()));
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, restdesc, review;
        RatingBar rating;

        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.txtView);
            this.restdesc = (TextView) itemView.findViewById(R.id.restdesc);
            // this.review = (TextView) itemView.findViewById(R.id.ratingh);

            this.rating = (RatingBar) itemView.findViewById(R.id.ratingBar);
            LayerDrawable stars = (LayerDrawable) rating.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(0).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            stars.getDrawable(1).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

            //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.image);

        }

    }

    @Override
    public int getItemCount() {
        return horizontallist.size();
    }

    public Resurantlistdatahorizontal getItem(int position) {

        return horizontallist.get(position);
    }

}
