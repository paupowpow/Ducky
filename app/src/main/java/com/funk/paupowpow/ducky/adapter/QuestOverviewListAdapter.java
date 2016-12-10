package com.funk.paupowpow.ducky.adapter;

import android.app.Activity;

import com.funk.paupowpow.ducky.model.data.Quest;

/**
 * Created by paulahaertel on 10.12.16.
 */

public class QuestOverviewListAdapter extends RealmRecyclerViewAdapter<Quest> {


    public QuestOverviewListAdapter(Activity activity) {
        this.activity = activity;
    }


}
