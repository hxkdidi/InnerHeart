package daixin.me.bugua.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shidai on 2016/4/23.
 */
public class CustomFragmentManageAdapter extends FragmentPagerAdapter {

    private  List<Fragment> mFragments;
    private  Context mContext;
    private String[] mTitles;

    public CustomFragmentManageAdapter(FragmentManager fm) {
        super(fm);
    }

    public CustomFragmentManageAdapter(Context context, FragmentManager fm, List<Fragment> fragments,String[] mTitles) {
        super(fm);
        this.mContext = context;
        this.mFragments = fragments;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
    }
}
