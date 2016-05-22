package daixin.me.bugua;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.Bind;
import daixin.me.bugua.ui.model.music.Songlist;
import daixin.me.bugua.utils.MusicTools;
import daixin.me.bugua.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by shidai on 2016/5/8.
 */
public class MusicAlbumAcivity extends BaseActivity {

    @Bind(R.id.tv_detail_song_name)
    TextView tvDetailSongName;
    @Bind(R.id.star_name)
    TextView starName;
    @Bind(R.id.player_black_round)
    ImageView playerBlackRound;
    @Bind(R.id.favorite)
    LinearLayout favorite;
    @Bind(R.id.seekbar)
    SeekBar mSeekBar;
    @Bind(R.id.line_seekbar)
    LinearLayout lineSeekbar;
    @Bind(R.id.player_author_img)
    CircleImageView playerAuthorImg;
    @Bind(R.id.pre_music)
    ImageView preMusic;
    @Bind(R.id.play_music)
    ImageButton playMusic;
    @Bind(R.id.next_music)
    ImageView nextMusic;
    @Bind(R.id.song_current_time)
    TextView songCurrentTime;
    @Bind(R.id.song_total_time)
    TextView songTotalTime;
    private Songlist music_singles;
    private Action1<Songlist> action1;
    private Observable<Songlist> songInfoObserable;

    @Override
    protected int getContentId() {
        return R.layout.detail_music;
    }

    @Override
    protected void initializeEvent() {

        initStaus();
        inData();
        preMusic.setOnClickListener((view) -> {
            this.musicService.previousMusic();
            getCurrentSongInfo();
        });
        nextMusic.setOnClickListener((view) -> {
            this.musicService.nextMusic();
            getCurrentSongInfo();
        });

        playMusic.setOnClickListener((view) -> {
            if (this.musicService.isPlaying()) {
                this.musicService.pausePlay();
                playMusic.setImageResource(R.drawable.play_btn);
            } else {
                this.musicService.pausePlay();
                playMusic.setImageResource(R.drawable.play_pause);
            }
        });


    }

    public void getCurrentSongInfo() {
        action1 = (songlist) -> {
            starName.setText(songlist.getSingername());
            tvDetailSongName.setText(songlist.getSongname());
            Picasso.with(MusicAlbumAcivity.this).load(songlist.getAlbumpicBig()).into(playerAuthorImg);
        };
        songInfoObserable = musicService.getSongInfoObserable();
        songInfoObserable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(action1);
    }

    @Override
    protected void onPublish(int percent) {
        mSeekBar.setProgress(percent);
    }

    @Override
    protected void onChange(int currentPostion) {
        onMusicPlay(currentPostion);
    }

    private Handler handler1 = new Handler();

    private void inData() {
        music_singles = (Songlist) getIntent().getSerializableExtra("music_single");
        mSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindMusicService();
        handler1.removeCallbacks(updateThread1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onBindMusicService();

    }

    private void onMusicPlay(int position) {
        int currentPostion = musicService.getCurrentPostion();
        Songlist songlist = MusicTools.musicList.get(currentPostion);
        mSeekBar.setMax((int) musicService.getMusicDuration());
        starName.setText(songlist.getSingername());
        tvDetailSongName.setText(songlist.getSongname());
        songCurrentTime.setText(Utils.formatTime(musicService.getMediaPlayerCurrentPostion()));
        songTotalTime.setText(Utils.formatTime((int) musicService.getMusicDuration()));
        Picasso.with(MusicAlbumAcivity.this).load(songlist.getAlbumpicBig()).into(playerAuthorImg);
        handler1.post(updateThread1);
        if (musicService !=null){
            if(musicService.isPlaying()){
                playMusic.setImageResource(R.drawable.play_pause);
            }else{
                playMusic.setImageResource(R.drawable.play_btn);
            }
        }
    }

    Runnable updateThread1 = new Runnable() {
        public void run() {
            mSeekBar.setProgress(musicService.getMediaPlayerCurrentPostion());
            songTotalTime.setText(Utils.formatTime((int) musicService.getMusicDuration()));
            songCurrentTime.setText(Utils.formatTime(musicService.getMediaPlayerCurrentPostion()));
            handler1.postDelayed(updateThread1, 100);
        }
    };

    private void initStaus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int progress = seekBar.getProgress();
            musicService.seekMusic(progress);
        }
    };
}
