package daixin.me.bugua.net;

import daixin.me.bugua.api.ApiService;
import daixin.me.bugua.api.ConfigKey;
import daixin.me.bugua.ui.model.Pagebean;
import daixin.me.bugua.utils.DateFormat;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shidai on 2016/4/24.
 */
public class DaixinRetrofit extends BaseRetrofit{

    private ApiService apiService;
    private static final DaixinRetrofit NEWS_INSTANCE = new DaixinRetrofit();

    public static DaixinRetrofit getSingleton(){
        return NEWS_INSTANCE;
    }
    private DaixinRetrofit() {
        apiService = getRetrofit().create(ApiService.class);
    }

    public void  getWeixinPageList(Subscriber<Pagebean> subscriber,int pageNum,int appId,int type){
        String currentTime =new DateFormat().getCurrentTime();
        apiService.getResult(null, pageNum, appId, currentTime, type, ConfigKey.SIGNKEY)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .map(result->result.showapiResBody.pagebean).subscribe(subscriber);
    }

    @Override
    protected String acquireBaseApiUrl() {
        return ConfigKey.NEWS_AND_MUSIC_URL;
    }
}
