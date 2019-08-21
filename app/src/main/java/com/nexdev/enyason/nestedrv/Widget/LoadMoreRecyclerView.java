package com.nexdev.enyason.nestedrv.Widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.nexdev.enyason.nestedrv.R;


/**
 * RecyclerView  Load More Implementation
 * <p>
 * Only LinearLayoutManager is Supported
 */
public class LoadMoreRecyclerView extends LinearLayout {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private int itemCount;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoadingProgress;

    public LoadMoreRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.load_more_recyler_view, this, true);
        recyclerView = view.findViewById(R.id.recyclerViewLM);
        progressBar = view.findViewById(R.id.progressBarLM);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE: {
                        final LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                        if (null == lm) return;

                        final int lastCompletelyVisibleItemPosition = lm.findLastCompletelyVisibleItemPosition();
                        if (!isLoadingProgress && lastCompletelyVisibleItemPosition == itemCount - 1) {
                            setLoadingProgress(true);
                            progressBar.setVisibility(VISIBLE);
                            onLoadMoreListener.onLoadMore();
                        }
                        break;
                    }
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        progressBar.setVisibility(View.GONE);
    }

    /**
     * @param itemCount no of items in adapter
     */
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * @param onLoadMoreListener callback function
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    /**
     * @param loadingProgress progress status
     */
    public void setLoadingProgress(boolean loadingProgress) {
        isLoadingProgress = loadingProgress;
    }

    /**
     * @param adapter recycler view adapter
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        setItemCount(adapter.getItemCount());
    }

    /**
     * @param manager recycler view manager
     */
    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        recyclerView.setLayoutManager(manager);
    }

    /**
     * @param itemDecoration item decoration
     */
    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        recyclerView.addItemDecoration(itemDecoration);
    }

    public void onLoadMoreComplete() {
        setLoadingProgress(false);
        progressBar.setVisibility(GONE);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
