package com.nexdev.enyason.nestedrv.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexdev.enyason.nestedrv.Model.HomeWrapper;
import com.nexdev.enyason.nestedrv.Model.RecylerViewType;
import com.nexdev.enyason.nestedrv.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter{


    public List<HomeWrapper> getItems() {
        return items;
    }

    private List<HomeWrapper> items;
    private Context context;

    public MainAdapter(List<HomeWrapper> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        switch (viewType){
            case RecylerViewType.VIEW_BANNER:{
                vh = new ViewHolderBanner(LayoutInflater.from(context).inflate(R.layout.item_banner,parent,false));
                break;
            }
            case RecylerViewType.VIEW_MOVIE:{
                vh = new ViewHolderMovie(LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false));
                break;
            }
            default:
                vh = new ViewHolderMovie(LayoutInflater.from(context).inflate(R.layout.item_fake,parent,false));
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Always use 'holder.getAdapterPosition()' not 'position'
        final HomeWrapper homeWrapper = items.get(holder.getAdapterPosition());
        switch (homeWrapper.getViewType()){
            case RecylerViewType.VIEW_BANNER:{
                ((ViewHolderBanner) holder).tvBanner.setText(String.valueOf(holder.getAdapterPosition()));
                ((ViewHolderBanner) holder).ivBanner.setImageResource(R.drawable.ic_launcher_background);
                break;
            }
            case RecylerViewType.VIEW_MOVIE:{
                ((ViewHolderMovie) holder).tvTitle.setText(homeWrapper.getMovie().getName());
                ((ViewHolderMovie) holder).rvMovie.setAdapter(new MovieAdapter2());
                ((ViewHolderMovie) holder).rvMovie.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                break;
            }
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Banner
    static class ViewHolderBanner extends RecyclerView.ViewHolder{
        TextView tvBanner;
        ImageView ivBanner;
        ViewHolderBanner(View itemView) {
            super(itemView);
            tvBanner =  itemView.findViewById(R.id.tvBanner);
            ivBanner =  itemView.findViewById(R.id.ivBanner);
        }
    }

    // Movie
    static class ViewHolderMovie extends RecyclerView.ViewHolder{
        TextView tvTitle;
        RecyclerView rvMovie;
        ViewHolderMovie(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            rvMovie = itemView.findViewById(R.id.rvMovie);
        }
    }

    public void addMoreData(List<HomeWrapper> items){
      this.items.addAll(items);
      notifyDataSetChanged();
    }
}
