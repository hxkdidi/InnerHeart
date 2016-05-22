package daixin.me.bugua.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import daixin.me.bugua.MeizhiAcivity;
import daixin.me.bugua.R;
import daixin.me.bugua.listeners.RecyclerItemClickListener;
import daixin.me.bugua.listeners.OnLoadMoreListener;
import daixin.me.bugua.net.MeizhiRetrofit;
import daixin.me.bugua.ui.adapter.MeiZhiRecycleAdapter;
import daixin.me.bugua.ui.model.meizhi.Img;
import rx.Subscriber;

/**
 * Created by shidai on 2016/4/23.
 */
public class MeiZhiFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "MeiZhiFragment";
    @Bind(R.id.recycleview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private Context mContext;
    private List<Img> mContentlist = new ArrayList<Img>();
    private Subscriber<List<Img>> subscriber;
    private  boolean isRefresh;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private int lastVisibleItemPosition;
    private MeiZhiRecycleAdapter meiZhiRecycleAdapter;
    private int mPage = 20;
    private List<Img> contentlists;
    private int mFragmentIndex = 0;
    public static final int MEIZHISIZE = 4;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common, container, false);
        mContext = container.getContext();
        ButterKnife.bind(this, rootView);
        initView();
        getPageList(mPage);
        initRefresh();
        return rootView;
    }

    private void initView() {
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        meiZhiRecycleAdapter = new MeiZhiRecycleAdapter(mContext,mContentlist);
        mRecyclerView.setAdapter(meiZhiRecycleAdapter);
    }

    private void initRefresh() {

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.addOnScrollListener(isBottomListener(mStaggeredGridLayoutManager));
    }

    private RecyclerView.OnScrollListener isBottomListener(StaggeredGridLayoutManager mStaggeredGridLayoutManager) {
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int postions[] = new int[mStaggeredGridLayoutManager.getSpanCount()];
                boolean isBottomPosition = mStaggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(postions)[1] >= meiZhiRecycleAdapter.getItemCount() - MEIZHISIZE;
                if (isBottomPosition && !swipeRefreshLayout.isRefreshing()){
                    if (!isRefresh){
                        swipeRefreshLayout.setRefreshing(true);
                        mPage += 20;
                        getPageList(mPage);
                        Toast.makeText(mContext,"buttom",Toast.LENGTH_LONG).show();
                    }else{
                        isRefresh = false;
                    }
                }
            }
        };
        return onScrollListener;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getPageList(int pn) {
        subscriber = new Subscriber<List<Img>>() {
            @Override
            public void onCompleted() {
                meiZhiRecycleAdapter.addMoreData(contentlists);
                swipeRefreshLayout.setRefreshing(false);
                meiZhiRecycleAdapter.setLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public Object loadUpdateData(Object o) {
                        contentlists = (List<Img>) o;
                        return null;
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,"服务器繁忙",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(List<Img> imgs) {
                    contentlists = imgs;
            }
        };
        MeizhiRetrofit.getSingleton().getMeizhiPageList(subscriber,pn);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mContext, (view, position) -> {
            Intent intent = new Intent(mContext, MeizhiAcivity.class);
            intent.putExtra("imgurl",contentlists.get(position).getImageUrl());
            intent.putExtra("width",contentlists.get(position).getImageWidth());
            intent.putExtra("height",contentlists.get(position).getImageHeight());
            startActivity(intent);
        }));
    }

    @Override
    public void onRefresh() {
            mPage+=20;
            MeizhiRetrofit.getSingleton().getMeizhiPageList(subscriber,mPage);
            swipeRefreshLayout.setRefreshing(false);
    }
}
