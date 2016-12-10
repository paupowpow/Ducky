package com.funk.paupowpow.ducky.model.data;

import android.app.Activity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by paulahaertel on 10.12.16.
 */

public class DuckyDatabaseHandler {
    Realm myRealm;

    private static DuckyDatabaseHandler instance;

    public static void initialize(Activity activity) {
        if (instance == null) {instance = new DuckyDatabaseHandler(activity); }
    }

    private DuckyDatabaseHandler (Activity activity) {
        RealmConfiguration defaultConfiguration = new RealmConfiguration.Builder(activity)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(defaultConfiguration);
        myRealm = Realm.getInstance(defaultConfiguration);
    }

    public static DuckyDatabaseHandler getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return myRealm;
    }

    public void createQuest(String questText) {
        myRealm.beginTransaction();
        Quest quest = myRealm.createObject(Quest.class);
        quest.setQuestId(System.currentTimeMillis());
        quest.setQuestText(questText);
        myRealm.commitTransaction();
    }

    public RealmResults<Quest> getQuests() {
        return myRealm.where(Quest.class).findAll();
    }
}
