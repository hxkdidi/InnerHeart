package daixin.me.bugua.net;

import java.util.List;
import java.util.concurrent.TimeUnit;

import daixin.me.bugua.api.ConfigKey;
import daixin.me.bugua.api.WeixinService;
import daixin.me.bugua.ui.model.music.MusicResult;
import daixin.me.bugua.ui.model.music.Songlist;
import daixin.me.bugua.utils.DateFormat;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by shidai on 2016/5/7.
 */
public class MusicRetrofit {
    private static final String BASE_MUSIC_URL = "https://route.showapi.com/";
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;
    private WeixinService weixinService;

    private static final MusicRetrofit INSTANCE1 = new MusicRetrofit();

    //Because there is more than a base_url,so must to be create many retrofit object
    //if you do this on server,you can  take advantage of server forwarding to create singleton.
    public static MusicRetrofit getSingleton(){
        return INSTANCE1;
    }
    private MusicRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_MUSIC_URL)
                .build();
        weixinService = retrofit.create(WeixinService.class);
    }

    public void  getMusicPageList(Subscriber<List<Songlist>> subscriber){
        weixinService.getMusicResult(18230,new DateFormat().getCurrentTime(),5, ConfigKey.SIGNKEY)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Func1<MusicResult, List<Songlist>>() {
                        @Override
                        public List<Songlist> call(MusicResult musicResult) {
                            return musicResult.getShowapiResBody().getPagebean().getSonglist();
                        }
                    }).subscribe(subscriber);
    }

}
