package com.funk.paupowpow.ducky.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by paulahaertel on 22.01.17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                QuestOverviewFragment tab1 = new QuestOverviewFragment();
                return tab1;
            case 1:
                SettingsFragment tab2 = new SettingsFragment();
                return tab2;
            case 2:
                NotificationsFragment tab3 = new NotificationsFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
