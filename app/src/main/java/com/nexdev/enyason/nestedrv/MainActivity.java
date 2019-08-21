package com.nexdev.enyason.nestedrv;

import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nexdev.enyason.nestedrv.Adapter.HomeAdapter;
import com.nexdev.enyason.nestedrv.Api.RetrofitSingleton;
import com.nexdev.enyason.nestedrv.Model.Data;
import com.nexdev.enyason.nestedrv.Model.HomeBodyResponse;
import com.nexdev.enyason.nestedrv.Model.Movie;
import com.nexdev.enyason.nestedrv.Model.Resurantlistdatahorizontal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";


    private List<Data> dataList;

    ArrayList<Movie> bannerlist;
    ArrayList<Resurantlistdatahorizontal> horizontalList;
    //android viewa
    private static ProgressBar progressBar;
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private static RecyclerView.LayoutManager layoutManager;
    private Data dm = null;


    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 1; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dataList = new ArrayList<>();
        bannerlist = new ArrayList<>();
        horizontalList = new ArrayList<>();
        loadresturantlistview();

        recyclerView = findViewById(R.id.rv_main);
        progressBar = findViewById(R.id.pb_home);

        adapter = new HomeAdapter(dataList, this);
        layoutManager = new LinearLayoutManager(this);
        //layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        int TOTAL_PAGES = 2;


//
//        Call<HomeBodyResponse> responseCall = RetrofitSingleton.getInstance().getApi().getMovieByCategory();
//
//        responseCall.enqueue(new Callback<HomeBodyResponse>() {
//            @Override
//            public void onResponse(Call<HomeBodyResponse> call, Response<HomeBodyResponse> response) {
//                progressBar.setVisibility(View.GONE);
//
//                for (Data data : response.body().getData().getData()) {
//
//                    dataList.add(data);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<HomeBodyResponse> call, Throwable t) {
//
//                progressBar.setVisibility(View.GONE);
//
//            }
//        });


//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0) {
//
//                    visibleItemCount = recyclerView.getChildCount();
//                    totalItemCount = layoutManager.getItemCount();
//                    firstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
//                    //System.out.println("totalitem" + totalItemCount + "\nvisibuleitem" + visibleItemCount + "\nfirstVisibleItem" + firstVisibleItem);
//
//                    if (loading) {
//                        if (totalItemCount >= previousTotal + 1) {
//                            System.out.println("loading false");
//                            loading = false;
//                            previousTotal = totalItemCount;
//                        }
//                    }
////                    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
////                        // End has been reached
////                        // Do something
////                        current_page++;
////                        System.out.println("currentpage" + current_page);
////                        //onLoadMore(current_page);
////                        loading = true;
////                    }
//
//                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
//
//                        if (!loading) {
//                            if (current_page <= TOTAL_PAGES) {
//                                current_page++;
//                                System.out.println("loadmore=========="+current_page);
//                                onLoadMore(current_page);
//                                loading = true;
//                            }
//
//                        }
////                        current_page++;
////                        System.out.println("currentpage" + current_page);
////                        onLoadMore(current_page);
////                        loading = true;
//                    }
//
//
//                } else if (dy < 0) {
//
//                }
//
//            }
//
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//
//        });
        implementScrollListener();

    }


    // Implement scroll listener
    private void implementScrollListener() {
        recyclerView
                .addOnScrollListener(new RecyclerView.OnScrollListener() {

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView,
                                                     int newState) {

                        super.onScrollStateChanged(recyclerView, newState);

                        // If scroll state is touch scroll then set userScrolled
                        // true
                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                            loading = true;

                        }

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx,
                                           int dy) {

                        super.onScrolled(recyclerView, dx, dy);
                        // Here get the child count, item count and visibleitems
                        // from layout manager

                        visibleItemCount = layoutManager.getChildCount();
                        totalItemCount = layoutManager.getItemCount();
                        firstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                        System.out.println("visibleItemCount"+visibleItemCount);

                        // Now check if userScrolled is true and also check if
                        // the item is end then update recycler view and set
                        // userScrolled to false
                        if (loading
                                && (visibleItemCount + firstVisibleItem) == totalItemCount) {
                            loading = false;
                            System.out.println("lastposition");
                            //updateRecyclerView();
                        }

                    }

                });

    }


    public void onLoadMore(int current_page) {
        //add progress item
        dataList.add(null);
        Handler handler = new Handler();
        adapter.notifyItemInserted(dataList.size());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //remove progress item
                dataList.remove(dataList.size() - 1);
                adapter.notifyItemRemoved(dataList.size());
                loadresturantlistviewnext();
                //adapter.notifyItemInserted(dataList.size());
                adapter.notifyDataSetChanged();

                //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
            }
        }, 2000);
        System.out.println("load");
    }

    private void loadresturantlistview() {


        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("mobile", 0);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://www.vervedeveloper.com/DOW/restaurant?key=5207", null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressBar.setVisibility(View.GONE);
                            //progressBar.setVisibility(View.GONE);
                            Log.e("Response", response.toString());
                            //System.out.println("Response" + response.toString());

                            JSONObject jObject = new JSONObject(response.toString());
                            JSONArray bannerarry = jObject.optJSONArray("banner");
                            for (int i = 0; i < bannerarry.length(); i++) {
                                //getting product object from json array
                                JSONObject banerobj = bannerarry.getJSONObject(i);
                                //Adding banner list
                                bannerlist.add(new Movie(
                                        banerobj.getString("id"),
                                        banerobj.getString("bannerName"),

                                        banerobj.getString("bannerImageName"),
                                        banerobj.getString("bannerText")

                                ));

                            }


                            // horizontal data get here


//                            JSONObject hjson = jObject.getJSONObject("hheader");
//                            int restviewtypeh = hjson.optInt("viewtype");
//                            String restcounth = hjson.optString("hheadertext");
//                            Log.e("restcounth", restcounth);
//                            dm.setHeaderTitle(restcounth);

                            JSONArray jsonArrayh = jObject.optJSONArray("hdata");
                            for (int j = 0; j < jsonArrayh.length(); j++) {
                                //getting product object from json array
                                JSONObject resthlist = jsonArrayh.getJSONObject(j);
                                //adding the product to product list
                                horizontalList.add(new Resurantlistdatahorizontal(
                                        resthlist.getInt("id"),
                                        resthlist.getInt("viewid"),
                                        resthlist.getString("name"),
                                        resthlist.getString("resDesc"),
                                        resthlist.getString("offerDesc"),
                                        resthlist.getString("rating"),
                                        resthlist.getString("closingTime"),
                                        resthlist.getString("openingTime"),
                                        resthlist.getString("path"),
                                        resthlist.getString("discount"),
                                        resthlist.getString("baudget"),
                                        resthlist.getString("ribbonDiscount"),
                                        resthlist.getString("del_charge"),
                                        resthlist.getString("del_time"),
                                        resthlist.getString("pak_charge"),
                                        resthlist.getString("coupan"),
                                        resthlist.getString("gst"),
                                        resthlist.getString("resCharge"),
                                        resthlist.getString("resMobile")

                                ));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        dm = new Data();
                        dm.setList(bannerlist);
                        dm.setListh(horizontalList);
                        dataList.add(dm);
                        adapter.notifyDataSetChanged();


                    }


                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        //adapter.showRetry(true, fetchErrorMessage(error));
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(JsonObjectRequest);

    }


    private void loadresturantlistviewnext() {


        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("mobile", 0);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://www.vervedeveloper.com/DOW/restaurant?key=5207", null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressBar.setVisibility(View.GONE);
                            //progressBar.setVisibility(View.GONE);
                            Log.e("Responsenext", response.toString());
                            // System.out.println("Responsenext" + response.toString());

                            JSONObject jObject = new JSONObject(response.toString());
                            JSONArray bannerarry = jObject.optJSONArray("banner");
                            for (int i = 0; i < bannerarry.length(); i++) {
                                //getting product object from json array
                                JSONObject banerobj = bannerarry.getJSONObject(i);
                                //Adding banner list
                                bannerlist.add(new Movie(
                                        banerobj.getString("id"),
                                        banerobj.getString("bannerName"),

                                        banerobj.getString("bannerImageName"),
                                        banerobj.getString("bannerText")

                                ));

                            }


                            // horizontal data get here


//                            JSONObject hjson = jObject.getJSONObject("hheader");
//                            int restviewtypeh = hjson.optInt("viewtype");
//                            String restcounth = hjson.optString("hheadertext");
//                            Log.e("restcounth", restcounth);
//                            dm.setHeaderTitle(restcounth);

                            JSONArray jsonArrayh = jObject.optJSONArray("hdata");
                            for (int j = 0; j < jsonArrayh.length(); j++) {
                                //getting product object from json array
                                JSONObject resthlist = jsonArrayh.getJSONObject(j);
                                //adding the product to product list
                                horizontalList.add(new Resurantlistdatahorizontal(
                                        resthlist.getInt("id"),
                                        resthlist.getInt("viewid"),
                                        resthlist.getString("name"),
                                        resthlist.getString("resDesc"),
                                        resthlist.getString("offerDesc"),
                                        resthlist.getString("rating"),
                                        resthlist.getString("closingTime"),
                                        resthlist.getString("openingTime"),
                                        resthlist.getString("path"),
                                        resthlist.getString("discount"),
                                        resthlist.getString("baudget"),
                                        resthlist.getString("ribbonDiscount"),
                                        resthlist.getString("del_charge"),
                                        resthlist.getString("del_time"),
                                        resthlist.getString("pak_charge"),
                                        resthlist.getString("coupan"),
                                        resthlist.getString("gst"),
                                        resthlist.getString("resCharge"),
                                        resthlist.getString("resMobile")

                                ));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        //dm = new Data();
                        dm.setList(bannerlist);
                        dm.setListh(horizontalList);
                        dataList.add(dm);
                        //adapter.notifyItemInserted(dataList.size());
                        //adapter.notifyDataSetChanged();


                    }


                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        //adapter.showRetry(true, fetchErrorMessage(error));
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(JsonObjectRequest);

    }
}
