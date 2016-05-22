package daixin.me.bugua.net;

import java.util.List;
import java.util.concurrent.TimeUnit;

import daixin.me.bugua.api.WeixinService;
import daixin.me.bugua.ui.model.meizhi.Img;
import daixin.me.bugua.ui.model.meizhi.MeiZhiResult;
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
public class MeizhiRetrofit {
    private static final String BASE_MEIZHI_URL = "http://image.baidu.com/";
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;
    private WeixinService weixinService;

    private static final MeizhiRetrofit INSTANCE1 = new MeizhiRetrofit();

    //Because there is more than a base_url,so must to be create many retrofit object
    //if you do this on server,you can  take advantage of server forwarding to create singleton.
    public static MeizhiRetrofit getSingleton(){
        return INSTANCE1;
    }
    private MeizhiRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_MEIZHI_URL)
                .build();
        weixinService = retrofit.create(WeixinService.class);
    }

    public void  getMeizhiPageList(Subscriber<List<Img>> subscriber,int pn){
            weixinService.getMeiZhiResult(pn)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Func1<MeiZhiResult, List<Img>>() {
                        @Override
                        public List<Img> call(MeiZhiResult meiZhiResult) {
                            return meiZhiResult.getImgs();
                        }
                    }).subscribe(subscriber);
    }

}
