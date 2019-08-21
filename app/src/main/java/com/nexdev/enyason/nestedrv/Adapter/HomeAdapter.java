package com.nexdev.enyason.nestedrv.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nexdev.enyason.nestedrv.Model.Data;
import com.nexdev.enyason.nestedrv.Model.HomeData;
import com.nexdev.enyason.nestedrv.Model.Movie;
import com.nexdev.enyason.nestedrv.R;

import java.util.List;


/**
 * Created by enyason on 10/4/18.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_ITEM2 = 2;
    private final int VIEW_PROG = 0;

    private Context context;
    private List<Data> data;
    private MovieAdapter horizontalAdapter;
    private ResurantliastadapterH horizontal;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public HomeAdapter(List<Data> data, Context context) {
        this.data = data;
        this.context = context;

        recycledViewPool = new RecyclerView.RecycledViewPool();

    }


//    @NonNull
//    @Override
//    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View theView = LayoutInflater.from(context).inflate(R.layout.row_layout_home, parent, false);
//
//        return new HomeViewHolder(theView);
//    }


//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View theView = LayoutInflater.from(context).inflate(R.layout.row_layout_home, parent, false);
//
//        return new HomeViewHolder(theView);
//    }


    @Override
    public int getItemViewType(int position) {
        return data.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(context).inflate(R.layout.row_layout_home, parent, false);
            vh = new HomeViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress_item, parent, false);
            vh = new ProgressViewHolder(v);
        }
        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeViewHolder) {
            Data object = data.get(position);
            ((HomeViewHolder) holder).textViewCategory.setText(data.get(position).getGenre());
            System.out.println("==============");
            horizontalAdapter = new MovieAdapter(data.get(position).getList(), context);
            ((HomeViewHolder) holder).recyclerViewHorizontal.setAdapter(horizontalAdapter);
            ((HomeViewHolder) holder).recyclerViewHorizontal.setRecycledViewPool(recycledViewPool);

            LinearLayoutManager cheildlayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            ((HomeViewHolder) holder).recyclerViewHorizontal2.setLayoutManager(cheildlayoutManager);
            horizontal = new ResurantliastadapterH(object.getListh(), context);
            ((HomeViewHolder) holder).recyclerViewHorizontal2.setAdapter(horizontal);
            ((HomeViewHolder) holder).recyclerViewHorizontal2.setRecycledViewPool(recycledViewPool);
        } else {
            System.out.println("==============progressbar");
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }


//    @Override
//    public void onBindViewHolder(@NonNull HomeViewHolder holder, final int position) {
//
//        holder.textViewCategory.setText(data.get(position).getGenre());
//        System.out.println("==============");
//        horizontalAdapter = new MovieAdapter(data.get(position).getList(), context);
//        holder.recyclerViewHorizontal.setAdapter(horizontalAdapter);
//        holder.recyclerViewHorizontal.setRecycledViewPool(recycledViewPool);
//
//        horizontal = new ResurantliastadapterH(data.get(position).getListh(), context);
//        holder.recyclerViewHorizontal2.setAdapter(horizontal);
//        holder.recyclerViewHorizontal2.setRecycledViewPool(recycledViewPool);
//
//
//    }


    @Override
    public int getItemCount() {
        return data.size();

    }


    public class HomeViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerViewHorizontal;
        private RecyclerView recyclerViewHorizontal2;
        private TextView textViewCategory;

        private LinearLayoutManager horizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        private LinearLayoutManager horizontalManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        public HomeViewHolder(View itemView) {
            super(itemView);

            recyclerViewHorizontal = itemView.findViewById(R.id.home_recycler_view_horizontal);
            recyclerViewHorizontal.setHasFixedSize(true);
            recyclerViewHorizontal.setNestedScrollingEnabled(false);
            recyclerViewHorizontal.setLayoutManager(horizontalManager);
            recyclerViewHorizontal.setItemAnimator(new DefaultItemAnimator());
            textViewCategory = itemView.findViewById(R.id.tv_movie_category);




            recyclerViewHorizontal2 = itemView.findViewById(R.id.horizontalrecycle);
            recyclerViewHorizontal2.setHasFixedSize(true);
            recyclerViewHorizontal2.setNestedScrollingEnabled(false);
            recyclerViewHorizontal2.setLayoutManager(horizontalManager2);
            recyclerViewHorizontal2.setItemAnimator(new DefaultItemAnimator());


        }


    }


//    public class HomeViewHolder extends RecyclerView.ViewHolder {
//
//
//        private RecyclerView recyclerViewHorizontal2;
//        private LinearLayoutManager horizontalManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//
//        public HomeViewHolder(View itemView) {
//            super(itemView);
//
//            recyclerViewHorizontal2 = itemView.findViewById(R.id.horizontalrecycle);
//            recyclerViewHorizontal2.setHasFixedSize(true);
//            recyclerViewHorizontal2.setNestedScrollingEnabled(false);
//            recyclerViewHorizontal2.setLayoutManager(horizontalManager2);
//            recyclerViewHorizontal2.setItemAnimator(new DefaultItemAnimator());
//
//
//        }
//
//
//    }


    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }


}
