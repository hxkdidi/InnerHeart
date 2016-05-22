package daixin.me.bugua;

import android.os.Bundle;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import daixin.me.bugua.view.ItemImage;

/**
 * Created by shidai on 2016/5/18.
 */
public class MeizhiAcivity extends BaseActivity {
    @Bind(R.id.content_meizhi)
    ItemImage contentMeizhi;

    @Override
    protected int getContentId() {
        return R.layout.acitivity_meizhi;
    }

    @Override
    protected void initializeEvent() {
        String imgurl = getIntent().getStringExtra("imgurl");
        int width = getIntent().getIntExtra("width",0);
        int height = getIntent().getIntExtra("height",0);
        contentMeizhi.setOriginalSize(width,height);
        Picasso.with(MeizhiAcivity.this).load(imgurl).into(contentMeizhi);
    }

    @Override
    protected void onPublish(int percent) {

    }

    @Override
    protected void onChange(int currentPostion) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
