package daixin.me.bugua;

import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

import butterknife.Bind;

/**
 * Created by shidai on 2016/5/17.
 */
public class ContentActivity extends BaseActivity {
    @Bind(R.id.detail_webview)
    WebView detailWebview;

    @Override
    protected int getContentId() {
        return R.layout.activity_content;
    }

    @Override
    protected void initializeEvent() {
        Log.e("daixin","url:"+getIntent().getStringExtra("ural"));
        detailWebview.getSettings().setJavaScriptEnabled(true);
        detailWebview.getSettings().setSupportMultipleWindows(true);
        detailWebview.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放
        detailWebview.loadUrl(getIntent().getStringExtra("ural"));
    }

    @Override
    protected void onPublish(int percent) {
        //TODO
    }

    @Override
    protected void onChange(int currentPostion) {
        //TODO
    }

    //按返回键时，不退出程序而是返回上一浏览页面
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && detailWebview.canGoBack()) {
            detailWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
