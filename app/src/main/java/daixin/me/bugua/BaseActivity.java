package daixin.me.bugua;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;
import daixin.me.bugua.api.MusicService;
import daixin.me.bugua.listeners.OnMusicListener;

/**
 * Created by shidai on 2016/5/14.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected MusicService musicService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentId());
        ButterKnife.bind(this);
        initializeEvent();
    }

    protected abstract int getContentId();

    protected abstract void initializeEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    //绑定音乐服务
    public void onBindMusicService(){
        bindService(new Intent(BaseActivity.this, MusicService.class), serviceConn, Context.BIND_AUTO_CREATE);
    }

    public MusicService getMusicService(){
        return  musicService;
    }

    private ServiceConnection serviceConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService =  ((MusicService.MusicBinder) service).getMusicService();
            musicService.setOnMusicListener(musicListener);
            onChange(musicService.getCurrentPostion());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };


    public void unBindMusicService(){
        unbindService(serviceConn);
    }

    public OnMusicListener musicListener = new OnMusicListener() {
        @Override
        public void onPublish(int percent) {
            BaseActivity.this.onPublish(percent);
        }

        @Override
        public void onChange(int position) {
            BaseActivity.this.onChange(position);

        }
    };

    protected abstract void onPublish(int percent);

    protected abstract void onChange(int currentPostion);

}
