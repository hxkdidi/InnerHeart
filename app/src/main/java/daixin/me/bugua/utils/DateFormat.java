package daixin.me.bugua.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shidai on 2016/4/24.
 */
public class DateFormat {

    public String  getCurrentTime(){
        Date now = new Date();
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowTime = sdFormatter.format(now);
        return nowTime;
    }
}
