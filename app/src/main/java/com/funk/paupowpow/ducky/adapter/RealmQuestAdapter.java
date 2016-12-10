package com.funk.paupowpow.ducky.adapter;

import android.content.Context;

import com.funk.paupowpow.ducky.model.data.Quest;

import io.realm.RealmResults;

/**
 * Created by paulahaertel on 10.12.16.
 */

public class RealmQuestAdapter extends RealmModelAdapter<Quest> {
    public RealmQuestAdapter(Context context, RealmResults<Quest> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}
