package com.funk.paupowpow.ducky.model.data;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by paulahaertel on 10.12.16.
 */

public class DuckyDatabaseHandler {
    Realm myRealm;

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

    public Uri createImageFileUri() throws IOException {

        String[] files = activity.fileList();
        Log.d("files" , Arrays.toString(files));

        File storageDir = activity.getFilesDir();
        Log.d("storage directory", "" + storageDir.getPath());

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File image = new File(
                storageDir.getPath() +
                File.separator + "ducky_" +
                timeStamp + ".jpg");

        Uri uri = FileProvider.getUriForFile(activity.getApplicationContext(), "com.funk.paupowpow.ducky", image);
        Log.d("URI", "" + uri);

        return uri;
    }

    public void updateQuest(Quest quest, String uri) {
        createQuestPicture(quest, uri);
        setQuestCompleted(quest);
    }

    public void createQuestPicture(Quest quest, String uri) {
        myRealm.beginTransaction();
        QuestPicture questPicture = myRealm.createObject(QuestPicture.class);
        questPicture.setQuest(quest);
        questPicture.setQuestPictureUri(uri);
        questPicture.setQuestPictureId(System.currentTimeMillis());
        myRealm.commitTransaction();
    }

    public void setQuestCompleted(Quest quest) {
        RealmResults<Quest> result = myRealm.where(Quest.class)
                .equalTo("questId", quest.getQuestId())
                .findAll();
        myRealm.beginTransaction();
        result.first().setCompleted(true);
        myRealm.commitTransaction();
    }

    public Bitmap getQuestPicture(Quest quest) throws IOException {
        RealmResults<QuestPicture> result = myRealm.where(QuestPicture.class)
                .equalTo("quest.questId", quest.getQuestId())
                .findAll();
        Log.d("result.toString", result.toString());

        String questPictureUriString = result.first().getQuestPictureUriString();
        Log.d("pic uri string", questPictureUriString);

        Uri myUri = Uri.parse(questPictureUriString);
        Log.d("myUri", myUri.toString());

//        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath().toString());
        Bitmap myBitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), myUri);

        return myBitmap;

    }
}
