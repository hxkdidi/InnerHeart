package daixin.me.bugua.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import daixin.me.bugua.ContentActivity;
import daixin.me.bugua.R;
import daixin.me.bugua.listeners.RecyclerItemClickListener;
import daixin.me.bugua.net.DaixinRetrofit;
import daixin.me.bugua.ui.adapter.LoveRecycleAdapter;
import daixin.me.bugua.ui.model.Contentlist;
import daixin.me.bugua.ui.model.Pagebean;
import rx.Subscriber;

/**
 * Created by shidai on 2016/4/23.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "HomeFragment";
    @Bind(R.id.recycleview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private Context mContext;
    private List<Contentlist> mContentlist = new ArrayList<>();
    private Subscriber<Pagebean> subscriber;
    private  boolean isRefresh;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItemPosition;
    private LoveRecycleAdapter loveRecycleAdapter;
    private int mPage = 1;
    private List<Contentlist> contentlists;
    private int mFragmentIndex = 0;


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
        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        loveRecycleAdapter = new LoveRecycleAdapter(mContext,mContentlist);
        mRecyclerView.setAdapter(loveRecycleAdapter);
    }

    private void initRefresh() {

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==recyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == loveRecycleAdapter.getItemCount()){
                    mPage+=1;
                    getPageList(mPage);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                 lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mContext, (view, position) -> {
            Intent intent = new Intent(mContext, ContentActivity.class);
            intent.putExtra("ural",contentlists.get(position).url);
            startActivity(intent);
        }));
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

    public void getPageList(int mPageItem) {
        subscriber = new Subscriber<Pagebean>() {


            @Override
            public void onCompleted() {
                loveRecycleAdapter.addMoreData(contentlists);
                //防止 点击角标越界
                loveRecycleAdapter.setLoadMoreListener((o)->{
                    contentlists = (List<Contentlist>) o;
                    return null;
                });
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext,"服务器繁忙",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Pagebean pagebean) {
                    contentlists = pagebean.contentlist;
            }
        };
        DaixinRetrofit.getSingleton().getWeixinPageList(subscriber,mPageItem,18230,0);
    }

    @Override
    public void onRefresh() {
        mPage+=1;
        DaixinRetrofit.getSingleton().getWeixinPageList(subscriber,1,18230,0);
        swipeRefreshLayout.setRefreshing(false);
    }
}
