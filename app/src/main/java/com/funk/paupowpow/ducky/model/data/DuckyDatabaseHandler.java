package com.funk.paupowpow.ducky.model.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.funk.paupowpow.ducky.p2pkit.P2pkitHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static java.io.File.separator;

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

    public void createQuest(String questText, @Nullable String questId, @Nullable Integer hopCount) {
        myRealm.beginTransaction();
        Quest quest = myRealm.createObject(Quest.class);

        if(questId == null) {
            quest.setQuestId(generateQuestId());
        } else {
            quest.setQuestId(questId);
            showToast("quest created");
        }

        if(hopCount == null) {
            quest.setHopCounter(0);
        } else {
            quest.setHopCounter(hopCount + 1);
        }

        quest.setQuestText(questText);
        quest.setCompleted(false);
        myRealm.commitTransaction();

    }


    public RealmResults<Quest> getQuests() {
        return myRealm.where(Quest.class).findAll();
    }

    public RealmResults<Quest> getQuest(String id) {
        return myRealm
                .where(Quest.class)
                .equalTo("questId", id)
                .findAll();
    }

    public Uri createImageFileUri() throws IOException {

        String[] files = activity.fileList();
        Log.d("files" , Arrays.toString(files));

        File storageDir = activity.getFilesDir();
        Log.d("storage directory", "" + storageDir.getPath());

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File image = new File(
                storageDir.getPath() +
                separator + "ducky_" +
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

        // p2pkit needs to update its discovery info
        P2pkitHandler.getInstance().updateDiscoveryInfo();

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

    private String generateQuestId() {
        String uuidString = UUID.randomUUID().toString();
        String timeString = String.valueOf(System.currentTimeMillis());
        return uuidString + "_" + timeString;
    }

    public void checkQuest(String questInfo) {
        //parts[0] should be questText
        //parts[1] should be questId
        //createQuest(String questText, @Nullable String questId)

        String separatorA = "!@#$";
        String separatorB = "$#@!";

        if(questInfo.contains(separatorA) && questInfo.contains(separatorB)) {

            int questTextStop = questInfo.indexOf(separatorA);
            String questText = questInfo.substring(0, questTextStop);

            int questIdStart = questTextStop + separatorA.length();
            int questIdStop = questInfo.indexOf(separatorB);
            String questId = questInfo.substring(questIdStart, questIdStop);

            int hopCountStart = questIdStop + separatorB.length();
            String hopCountString = "";
            int hopCount = 0;
            if(questInfo.length() >= hopCountStart) {
                hopCountString = questInfo.substring(hopCountStart, questInfo.length() - 1);
                hopCount = Integer.parseInt(hopCountString);
            }

            if(getQuest(questId).isEmpty()) {
                if(questId == "") {
                    createQuest(questText, null, null);
                } else {
                    createQuest(questText, questId, hopCount);
                }
            }
        } else {
            Log.d("4711", "tried to check quest: " + questInfo);
        }

    }

    private void showToast(String text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(activity, text, duration);
        toast.show();
    }

    public boolean isP2pkitStateEnabled() {
        SharedPreferences preferences = activity.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        boolean result = preferences.getBoolean("P2pkitState", true);
        return result;
    }

    public void updateP2pkitState() {
        SharedPreferences preferences = activity.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (isP2pkitStateEnabled()) {
            editor.putBoolean("P2pkitState", false);
        } else {
            editor.putBoolean("P2pkitState", true);
        }
        editor.commit();
    }
}
