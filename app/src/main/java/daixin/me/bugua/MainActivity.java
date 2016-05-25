package daixin.me.bugua;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import daixin.me.bugua.api.MusicService;
import daixin.me.bugua.ui.adapter.CustomFragmentManageAdapter;
import daixin.me.bugua.ui.fragment.HomeFragment;
import daixin.me.bugua.ui.fragment.MeiZhiFragment;
import daixin.me.bugua.ui.fragment.MusicFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav)
    NavigationView mNavigationView;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.tablayout)
    TabLayout mTabLayout;

    private String[] mTitles = {"实时热点","清新妹纸","热门音乐","滚动其他","滚动其他"};
    private List<Fragment> mFragments;

    @Override
    public void initializeEvent() {
        initializeView();
        initializePager();
    }

    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBindMusicService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onBindMusicService();
    }

    @Override
    protected void onPublish(int percent) {
        if (mViewPager!=null){
            if(mViewPager.getCurrentItem() == 2) {
                ((MusicFragment)mFragments.get(2)).setProgress(percent);
            }
        }
    }

    @Override
    protected void onChange(int position) {
        if (mViewPager!=null){
            if(mViewPager.getCurrentItem() == 2) {
                ((MusicFragment)mFragments.get(2)).onPlay(position);
             }
        }
    }

    public MusicService getPlayService() {
        return musicService;
    }

    private void initializePager() {
        mFragments = new ArrayList<>();
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0;i<mTitles.length;i++){
            if (i==1){
                mFragments.add(new MeiZhiFragment());
            }else if (i==2){
                mFragments.add(new MusicFragment());
            }else{
                mFragments.add(new HomeFragment());
            }
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[i]));
        }

        CustomFragmentManageAdapter adapter = new CustomFragmentManageAdapter(this,getSupportFragmentManager(), mFragments,mTitles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initializeView() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.donate) {
            Toast.makeText(MainActivity.this, R.string.setting, Toast.LENGTH_SHORT).show();
        }else if (itemId == R.id.search){
            Toast.makeText(MainActivity.this, R.string.serach, Toast.LENGTH_SHORT).show();
        }else if(itemId == R.id.notify){
            Toast.makeText(MainActivity.this, R.string.notify, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_about) {
            Toast.makeText(MainActivity.this,"about us", Toast.LENGTH_SHORT).show();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
