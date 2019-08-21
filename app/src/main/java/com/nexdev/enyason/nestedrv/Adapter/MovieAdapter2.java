package com.nexdev.enyason.nestedrv.Adapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nexdev.enyason.nestedrv.R;

public class MovieAdapter2 extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

     static class ViewHolder extends RecyclerView.ViewHolder{

         public ViewHolder(View itemView) {
             super(itemView);
         }
     }
}
