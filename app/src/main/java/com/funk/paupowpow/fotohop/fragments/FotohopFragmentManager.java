package com.funk.paupowpow.fotohop.fragments;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.funk.paupowpow.fotohop.R;
import com.funk.paupowpow.fotohop.model.data.Quest;

/**
 * Created by paulahaertel on 10.12.16.
 */

public class FotohopFragmentManager {

    private static final int FRAGMENT_CONTAINER_ID = R.id.activity_main;

    private final FragmentManager manager;
    private final AppCompatActivity activity;

    private static FotohopFragmentManager instance;

    public static void initialize(AppCompatActivity activity, FragmentManager manager){
        if (instance == null) {instance = new FotohopFragmentManager(activity, manager); }
    }

    public static FotohopFragmentManager getInstance() {
        return instance;
    }

    private FotohopFragmentManager(AppCompatActivity activity, FragmentManager manager) {
        this.activity = activity;
        this.manager = manager;
    }

    private void safeCommitTransaction(FragmentTransaction transaction) {
        try {
            transaction.commit();
        } catch(IllegalStateException e) {
            Log.e("FotohopFragmentManager", "Error in FragmentTransaction: " + e.getMessage());
        }
    }

    public void startQuestOverviewFragment() {
        QuestOverviewFragment questOverviewFragment = QuestOverviewFragment.newInstance();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(FRAGMENT_CONTAINER_ID, questOverviewFragment);
        fragmentTransaction.addToBackStack(null);
//        int count = manager.getBackStackEntryCount();
        safeCommitTransaction(fragmentTransaction);
    }

    public void startQuestDetailFragment(Quest quest) {
        QuestDetailFragment questDetailFragment = QuestDetailFragment.newInstance(quest);
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(FRAGMENT_CONTAINER_ID, questDetailFragment);
        fragmentTransaction.addToBackStack(null);
//        int count = manager.getBackStackEntryCount();
        safeCommitTransaction(fragmentTransaction);
    }

    public void startQuestCreateFragment() {
        QuestCreateFragment questCreateFragment = QuestCreateFragment.newInstance();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(FRAGMENT_CONTAINER_ID, questCreateFragment);
        fragmentTransaction.addToBackStack(null);
        int count = manager.getBackStackEntryCount();
        Log.d("HEY", String.valueOf(count));
        safeCommitTransaction(fragmentTransaction);
    }

    public void popFragment() {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.popBackStack();
    }
}
