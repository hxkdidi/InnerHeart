package daixin.me.bugua.api;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import daixin.me.bugua.listeners.OnMusicListener;
import daixin.me.bugua.ui.model.music.Songlist;
import daixin.me.bugua.utils.MusicTools;
import daixin.me.bugua.utils.Utils;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by shidai on 2016/5/11.
 */
public class MusicService extends Service implements MediaPlayer.OnCompletionListener{

    public static final String TAG = "MusicService";
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private SensorManager mSensorManager;
    private MediaPlayer mediaPlayer;
    private OnMusicListener musicListener;
    private int needMusicListPosition;
    private Songlist songlist;

    public  class MusicBinder extends Binder{
        public MusicService getMusicService(){
            return MusicService.this;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(this);
    }

    /**
     * 接口回调
     * @param onMusicListener
     */
    public void setOnMusicListener(OnMusicListener onMusicListener){
        this.musicListener = onMusicListener;
    }

    /**
     * @param position 音乐播放列表位置索引
     * @return
     */
    public int play(int position){
        if (position<0) position = 0;
        if (position>MusicTools.musicList.size()) position = MusicTools.musicList.size()-1;
        songlist = MusicTools.musicList.get(position);
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(MusicTools.musicList.get(position).getUrl());
            mediaPlayer.prepare();
            start();
            if (musicListener!=null) musicListener.onPublish(position);
        } catch (IOException e) {
            e.printStackTrace();
        }
        needMusicListPosition = position;
        Utils.put(getApplicationContext(),"playing_position",needMusicListPosition);
        return needMusicListPosition;
    }

    public Observable<Songlist> getSongInfoObserable() {
        Observable<Songlist> songlistObservable = Observable.create(new Observable.OnSubscribe<Songlist>() {
            @Override
            public void call(Subscriber<? super Songlist> subscriber) {
                subscriber.onNext(songlist);
            }
        });
        return songlistObservable;
    }

    public int continuePlay(){
        if (mediaPlayer.isPlaying())
            return -1;
        mediaPlayer.start();
        return needMusicListPosition;
    }

    public int pausePlay(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            return -1;
        }else{
            mediaPlayer.start();
        }
        return needMusicListPosition;
    }

    public int nextMusic(){
        if (needMusicListPosition>MusicTools.musicList.size()-1){
            return play(0);
        }
        return play(needMusicListPosition+1);
    }

    public int previousMusic(){
        if (needMusicListPosition<=0){
            return play(MusicTools.musicList.size()-1);
        }
        return play(needMusicListPosition-1);
    }

    public boolean isPlaying(){
        return null!=mediaPlayer && mediaPlayer.isPlaying();
    }
    private void start() {
        mediaPlayer.start();
    }

    public int getCurrentPostion() {
        return needMusicListPosition;
    }

    public int getMediaPlayerCurrentPostion() {
        return mediaPlayer.getCurrentPosition();
    }

    public long getMusicDuration(){
        if (!mediaPlayer.isPlaying())
            return  0;
        return mediaPlayer.getDuration();
    }

    public void seekMusic(int position){
        if (!mediaPlayer.isPlaying()) return;
        mediaPlayer.seekTo(position);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mSensorManager.registerListener(mSensorEventListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
        return new MusicBinder();
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        nextMusic();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mSensorManager.unregisterListener(mSensorEventListener);
        return true;
    }



    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        if (musicListener!=null){
            musicListener.onPublish(needMusicListPosition);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!executorService.isShutdown()){
            executorService.shutdown();
        }
        executorService = null;
        if (mediaPlayer!=null){
            mediaPlayer.release();
        }
        mediaPlayer = null;
    }

    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private class MyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(
                    MusicService.class.getSimpleName())) {
                switch (intent.getIntExtra("BUTTON_NOTI", 0)) {
                    case 1:
                        previousMusic();
                        break;
                    case 2:
                        if (isPlaying()) {
                            pausePlay(); // 暂停
                        } else {
                            continuePlay(); // 播放
                        }
                        break;
                    case 3:
                        nextMusic();
                        break;
                    case 4:
                        if (isPlaying()) {
                            pausePlay();
                        }
                        break;
                    default:
                        break;
                }
            }
            if (musicListener != null) {
                musicListener.onChange(getCurrentPostion());
            }
        }
    }

}
