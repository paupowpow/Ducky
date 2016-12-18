package com.funk.paupowpow.ducky.model.data;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
/**
 * Created by paulahaertel on 10.12.16.
 */

public class DuckyDatabaseHandler {
    Realm myRealm;

    String mCurrentPhotoPath;

    private static DuckyDatabaseHandler instance;
    private static Activity activity;

    public static void initialize(Activity activity) {
        if (instance == null) {
            instance = new DuckyDatabaseHandler(activity);
        }
    }

    private DuckyDatabaseHandler(Activity activity) {
        RealmConfiguration defaultConfiguration = new RealmConfiguration.Builder(activity)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(defaultConfiguration);
        myRealm = Realm.getInstance(defaultConfiguration);
        this.activity = activity;
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
