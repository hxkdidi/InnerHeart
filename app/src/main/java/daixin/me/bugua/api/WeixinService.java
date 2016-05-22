package daixin.me.bugua.api;

import daixin.me.bugua.ui.model.WeixinResult;
import daixin.me.bugua.ui.model.meizhi.MeiZhiResult;
import daixin.me.bugua.ui.model.music.MusicResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by shidai on 2016/4/24.
 */
public interface WeixinService  {
    @GET("/582-2/")
    Observable<WeixinResult> getResult(
            @Query("key") String key,
            @Query("page") int page,
            @Query("showapi_appid") int showapi_appid,
            @Query("showapi_timestamp") String showapi_timestamp,
            @Query("typeId") int typeId,
            @Query("showapi_sign") String showapi_sign);

    @GET("/data/imgs?tag=%e5%85%a8%e9%83%a8&from=1&rn=20&col=%e7%be%8e%e5%a5%b3")
    Observable<MeiZhiResult> getMeiZhiResult(
            @Query("pn") int pn
    );

    @GET("/213-4/")
    Observable<MusicResult> getMusicResult(
            @Query("showapi_appid") int showapi_appid,
            @Query("showapi_timestamp") String showapi_timestamp,
            @Query("topid") int topid,
            @Query("showapi_sign") String showapi_sign);

}
