package daixin.me.bugua.net;

import java.util.concurrent.TimeUnit;

import daixin.me.bugua.api.ConfigKey;
import daixin.me.bugua.api.WeixinService;
import daixin.me.bugua.ui.model.Pagebean;
import daixin.me.bugua.utils.DateFormat;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shidai on 2016/4/24.
 */
public class DaixinRetrofit {
    private static final String BASE_URL = "https://route.showapi.com/";
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;
    private WeixinService weixinService;

    private static final DaixinRetrofit INSTANCE = new DaixinRetrofit();

    //Because there is more than a base_url,so must to be create many retrofit object
    //if you do this on server,you can  take advantage of server forwarding to create singleton.
    public static DaixinRetrofit getSingleton(){
        return INSTANCE;
    }
    private DaixinRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        weixinService = retrofit.create(WeixinService.class);
    }

    public void  getWeixinPageList(Subscriber<Pagebean> subscriber,int pageNum,int appId,int type){
        String currentTime =new DateFormat().getCurrentTime();
        weixinService.getResult(null, pageNum, appId, currentTime, type, ConfigKey.SIGNKEY)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .map(result->result.showapiResBody.pagebean).subscribe(subscriber);
    }
}
