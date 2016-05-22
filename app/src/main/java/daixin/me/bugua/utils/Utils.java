package daixin.me.bugua.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by shidai on 2016/4/24.
 */
public class Utils {
    private static final String FILE_NAME = "share_music";

    public static void openActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    public static void put(Context context, String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }

        editor.commit();
    }

    public static String formatTime(int time) {
        time /= 1000;
        int minute = time / 60;
        int second = time % 60;
        minute %= 60;
        return String.format("%02d:%02d", minute, second);
    }
}

