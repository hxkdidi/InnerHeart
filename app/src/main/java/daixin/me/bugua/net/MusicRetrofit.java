package daixin.me.bugua.net;

import java.util.List;

import daixin.me.bugua.api.ApiService;
import daixin.me.bugua.api.ConfigKey;
import daixin.me.bugua.ui.model.music.MusicResult;
import daixin.me.bugua.ui.model.music.Songlist;
import daixin.me.bugua.utils.DateFormat;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by shidai on 2016/5/7.
 */
public class MusicRetrofit extends BaseRetrofit{

    private ApiService apiService;
    private static final MusicRetrofit MUSIC_INSTANCE = new MusicRetrofit();

    public static MusicRetrofit getSingleton(){
        return MUSIC_INSTANCE;
    }

    private MusicRetrofit() {
        apiService = getRetrofit().create(ApiService.class);
    }

    public void  getMusicPageList(Subscriber<List<Songlist>> subscriber){
        apiService.getMusicResult(18230,new DateFormat().getCurrentTime(),5, ConfigKey.SIGNKEY)
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
    @Override
    protected String acquireBaseApiUrl() {
        return ConfigKey.NEWS_AND_MUSIC_URL;
    }

}
