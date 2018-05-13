package com.example.android.newsapps1v2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ArticleCategoryAdapter extends FragmentPagerAdapter {

    private Context articleContext;

    public ArticleCategoryAdapter(Context context,FragmentManager fm) {
        super(fm);
        articleContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new LatestNewsFragment();
        } else if(position == 1){
            return new LatestNewsFragment();
        } else {
            return new LatestNewsFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return articleContext.getString(R.string.latest_news);
        } else if (position == 1){
            return articleContext.getString(R.string.politics);
        } else if(position == 2){
            return articleContext.getString(R.string.sports);
        } else {
            return articleContext.getString(R.string.lifestyle);
        }
    }
}
