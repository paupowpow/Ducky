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



    public Uri createImageFileUri() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        String[] files = activity.fileList();

        Log.d("files" , Arrays.toString(files));

        File storageDir = activity.getFilesDir();

        Log.d("storage directory", "" + storageDir.getPath());

//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );

        File image = new File(
                storageDir.getPath() +
                File.separator + "ducky_" +
                timeStamp + ".jpg");

        Uri uri = FileProvider.getUriForFile(activity.getApplicationContext(), "com.funk.paupowpow.ducky", image);

        Log.d("URIIIII", "" + uri);

        return uri;
    }

    public void createQuestPicture(Quest quest, String uri) {
        myRealm.beginTransaction();
        QuestPicture questPicture = myRealm.createObject(QuestPicture.class);
        questPicture.setQuest(quest);
        questPicture.setQuestPictureUri(uri);
        questPicture.setQuestPictureId(System.currentTimeMillis());
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

//        File imgFile = new File(myUri.getPath());
//        Log.d("img file abs path" , imgFile.getAbsolutePath().toString());
//        Log.d("img file path" , imgFile.getPath().toString());

//        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath().toString());

        Bitmap myBitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), myUri);

        return myBitmap;

    }

}
