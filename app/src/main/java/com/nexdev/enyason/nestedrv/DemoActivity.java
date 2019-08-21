package com.nexdev.enyason.nestedrv;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.nexdev.enyason.nestedrv.Adapter.MainAdapter;
import com.nexdev.enyason.nestedrv.Model.FakeDataGenerator;
import com.nexdev.enyason.nestedrv.Model.HomeWrapper;
import com.nexdev.enyason.nestedrv.Widget.LoadMoreRecyclerView;

import java.util.List;

public class DemoActivity extends AppCompatActivity {

    private LoadMoreRecyclerView lv;
    private MainAdapter adapter;
    private Handler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        setContentView(R.layout.activity_demo);
        lv = findViewById(R.id.lv);
        setAdapter();
    }

    private void setAdapter(){
        adapter = new MainAdapter(FakeDataGenerator.getHomePageData(12),this);
        lv.setAdapter(adapter);
        lv.setLayoutManager(new LinearLayoutManager(this));
        lv.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.e("onLoadMore",String.valueOf(adapter.getItemCount()));
                // call api here
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setMoreData();
                    }
                },2000);
            }
        });
    }

    private void setMoreData(){
        // update fresh data
        List<HomeWrapper> freshData = FakeDataGenerator.getHomePageData(12);
        adapter.addMoreData(freshData);
        lv.setItemCount(adapter.getItemCount());
        lv.onLoadMoreComplete();
        Log.e("setAdapter",String.valueOf(adapter.getItemCount()));
    }
}
