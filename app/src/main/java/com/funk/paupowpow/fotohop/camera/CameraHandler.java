package com.funk.paupowpow.fotohop.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.funk.paupowpow.fotohop.model.data.FotohopDatabaseHandler;
import com.funk.paupowpow.fotohop.model.data.Quest;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by paulahaertel on 17.12.16.
 */

public class CameraHandler {

    private static final String TAG = "CameraHandler";

    private static CameraHandler instance;
    private static Activity activity;

    private Uri uri;
    private Quest quest;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private CameraHandler(Activity activity) {
        this.activity = activity;
    }

    public static CameraHandler getInstance() {
        return instance;
    }

    public static void initialize(Activity activity) {
        if(instance == null) {
            instance = new CameraHandler(activity);
        }
    }

    public void openCamera(Quest quest) {

        this.quest = null;
        this.uri = null;

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        this.quest = quest;

        try {
            this.uri = FotohopDatabaseHandler.getInstance().createImageFileUri();
        } catch (IOException ex) {
            //Error
        }

        if  (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            Log.d(TAG, this.uri.getPath());
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, this.uri);
            activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    public Quest handleResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.d(TAG, this.uri.getPath());
            FotohopDatabaseHandler.getInstance().updateQuest(this.quest, this.uri.toString());
            return quest;
        } else {
            return null;
        }
    }
}
