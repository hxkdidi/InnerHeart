package daixin.me.bugua.net;

import java.util.List;

import daixin.me.bugua.api.ConfigKey;
import daixin.me.bugua.api.ApiService;
import daixin.me.bugua.ui.model.meizhi.Img;
import daixin.me.bugua.ui.model.meizhi.MeiZhiResult;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by shidai on 2016/5/7.
 */
public class MeizhiRetrofit extends BaseRetrofit{
    private Retrofit retrofit;
    private ApiService apiService;

    private static final MeizhiRetrofit INSTANCE1 = new MeizhiRetrofit();

    public static MeizhiRetrofit getSingleton(){
        return INSTANCE1;
    }
    private MeizhiRetrofit() {
        retrofit = getRetrofit();
        apiService = retrofit.create(ApiService.class);
    }

    public void  getMeizhiPageList(Subscriber<List<Img>> subscriber,int pn){
            apiService.getMeiZhiResult(pn)
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

    @Override
    protected String acquireBaseApiUrl() {
        return ConfigKey.MEIZHI_URL;
    }
}
