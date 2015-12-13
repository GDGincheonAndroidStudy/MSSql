package com.ourincheon.databaseproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ChaeYoungDo on 2015-08-16.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"1학년 1학기", "1학년 2학기", "2학년 1학기", "2학년 2학기", "3학년 1학기"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new SemesterFragment().newInstanse(position);
    }

    public CharSequence getPageTitle(int position){
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }


    public String returnTitle(int i){
        return TITLES[i];
    }

}
