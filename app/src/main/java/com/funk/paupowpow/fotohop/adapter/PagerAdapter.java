package com.funk.paupowpow.fotohop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.funk.paupowpow.fotohop.fragments.NotificationsFragment;
import com.funk.paupowpow.fotohop.fragments.QuestOverviewFragment;
import com.funk.paupowpow.fotohop.fragments.SettingsFragment;

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
                SettingsFragment tab2 = new SettingsFragment();
                return tab2;
            case 1:
                QuestOverviewFragment tab1 = new QuestOverviewFragment();
                return tab1;
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
