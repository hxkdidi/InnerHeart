package daixin.me.bugua;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by shidai on 2016/5/8.
 */
public class DaixinApp extends Application {

    public static Context mContext;
    private WindowManager windowManager;
    public static int widthPixels;
    public static int heightPixels;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        widthPixels = metrics.widthPixels;
        heightPixels = metrics.heightPixels;
    }
}
