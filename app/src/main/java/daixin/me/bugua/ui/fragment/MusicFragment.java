package daixin.me.bugua.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import daixin.me.bugua.MainActivity;
import daixin.me.bugua.MusicAlbumAcivity;
import daixin.me.bugua.R;
import daixin.me.bugua.api.MusicService;
import daixin.me.bugua.listeners.RecyclerItemClickListener;
import daixin.me.bugua.net.MusicRetrofit;
import daixin.me.bugua.ui.adapter.MusicRecycleAdapter;
import daixin.me.bugua.ui.model.music.Songlist;
import daixin.me.bugua.utils.MusicTools;
import rx.Subscriber;

/**
 * Created by shidai on 2016/4/23.
 */
public class MusicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "MeiZhiFragment";
    @Bind(R.id.recycleview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.iv_album_showy)
    ImageView ivAlbumShowy;
    @Bind(R.id.tv_song_name_showy)
    TextView tvSongNameShowy;
    @Bind(R.id.play_progress)
    SeekBar mSeekBar;
    @Bind(R.id.play_song)
    ImageButton playSong;
    @Bind(R.id.list_music)
    RelativeLayout listMusic;
    private Context mContext;
    private List<Songlist> mContentlist = new ArrayList<Songlist>();
    private Subscriber<List<Songlist>> subscriber;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private MusicRecycleAdapter musicRecycleAdapter;
    private List<Songlist> contentlists;
    public static final int MEIZHISIZE = 4;
    private MusicService musicService;
    private boolean isPause;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common, container, false);
        ButterKnife.bind(this,rootView);
        mContext = container.getContext();
        initView();
        getPageList();
        initRefresh();
        OnClickItem();
        return rootView;
    }

    private void OnClickItem() {
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mContext, (view, position) -> {
            play(position);
            playOrPauseMusic();
        }));
        playOrPauseMusic();
    }

    private void playOrPauseMusic(){
        musicService = mainActivity.getMusicService();
        if (musicService!=null) {
            if (musicService.isPlaying()) {
                playSong.setImageResource(R.drawable.play_pause);
            } else {
                playSong.setImageResource(R.drawable.play_btn);
            }
        }

        playSong.setOnClickListener((view) -> {
            if (musicService!=null) {
                    if (musicService.isPlaying()) {
                        musicService.pausePlay();
                        playSong.setImageResource(R.drawable.play_btn);
                    } else {
                        musicService.pausePlay();
                        playSong.setImageResource(R.drawable.play_pause);
                    }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        isPause = false;
        mainActivity.onBindMusicService();

    }

    @Override
    public void onPause() {
        isPause = true;
        super.onPause();
    }

    /**
     * stop时， 回调通知activity解除绑定歌曲播放服务
     */
    @Override
    public void onStop() {
        super.onStop();
    }

    private void play(int position) {
            Toast.makeText(mContext,""+position,Toast.LENGTH_SHORT).show();
            int playMusicPosition = mainActivity.getPlayService().play(position);
            onPlay(playMusicPosition);
    }

    private void initView() {
        musicService = mainActivity.getPlayService();
        listMusic.setVisibility(View.VISIBLE);
        //开启音乐服务
        Intent intent = new Intent(mContext, MusicService.class);
        mContext.startService(intent);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        musicRecycleAdapter = new MusicRecycleAdapter(mContext, mContentlist);
        mRecyclerView.setAdapter(musicRecycleAdapter);
    }

    private void initRefresh() {

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
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
                boolean isBottomPosition = mStaggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(postions)[1] >= musicRecycleAdapter.getItemCount() - MEIZHISIZE;
                if (isBottomPosition && !swipeRefreshLayout.isRefreshing()) {
                    Toast.makeText(mContext, R.string.no_more_data,Toast.LENGTH_SHORT).show();
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

    public void getPageList() {
        subscriber = new Subscriber<List<Songlist>>() {
            @Override
            public void onCompleted() {
                musicRecycleAdapter.addMoreData(contentlists);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Songlist> imgs) {
                contentlists = imgs;
                MusicTools.musicList.clear();
                MusicTools.musicList.addAll(contentlists);
            }
        };
        MusicRetrofit.getSingleton().getMusicPageList(subscriber);
    }

    @Override
    public void onRefresh() {
        MusicRetrofit.getSingleton().getMusicPageList(subscriber);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setProgress(int progress) {
        if (isPause){
            return;
        }
        mSeekBar.setProgress(progress);
    }
    private  Handler handler = new Handler();
    public void onPlay(int position) {
        if (musicService!=null) {
            if (musicService.isPlaying()) {
                playSong.setImageResource(R.drawable.play_pause);
            } else {
                playSong.setImageResource(R.drawable.play_btn);
            }
        }

        if(position<0 || MusicTools.musicList.isEmpty()) return;
        Songlist songlist = MusicTools.musicList.get(position);
        if (songlist.getAlbumpicBig()!=null){
            Picasso.with(mContext).load(songlist.getAlbumpicBig()).into(ivAlbumShowy);
        }else {
            ivAlbumShowy.setImageResource(R.drawable.default_author);
        }
        ivAlbumShowy.setOnClickListener((headImg)->{
            Intent intent = new Intent(mContext, MusicAlbumAcivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("music_single",songlist);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        tvSongNameShowy.setText(songlist.getSongname());
        mSeekBar.setMax((int) mainActivity.getPlayService().getMusicDuration());
        handler.post(updateThread);
    }

    Runnable updateThread = new Runnable() {
        public void run() {
            //获得歌曲现在播放位置并设置成播放进度条的值
            mSeekBar.setProgress(mainActivity.getPlayService().getMediaPlayerCurrentPostion());
            handler.postDelayed(updateThread, 100);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateThread);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }
}
