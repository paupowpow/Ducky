package com.funk.paupowpow.ducky.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;
import com.funk.paupowpow.ducky.model.data.Quest;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by paulahaertel on 17.12.16.
 */

public class CameraHandler {

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
        // prepare a URI and give to external camera activity
        File imageFile = null;

        try {
            imageFile = DuckyDatabaseHandler.getInstance().createImageFile();
        } catch (IOException ex) {
            //Error
        }

        if  ((takePictureIntent.resolveActivity(activity.getPackageManager()) != null) &&
                (imageFile != null)) {
            this.uri = FileProvider.getUriForFile(activity.getApplicationContext(), "com.funk.paupowpow.ducky", imageFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, this.uri);
            activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public Quest handleResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            DuckyDatabaseHandler.getInstance().createQuestPicture(this.quest, this.uri.toString());
            Log.d("HEY", this.uri.toString());
            return quest;
        } else {
            return null;
        }
    }

//    private boolean saveImageToFile(File file) {
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//                return true;
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return false;
//            }
//        } else {
//            file.delete();
//            try {
//                file.createNewFile();
//                return true;
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return false;
//            }
//    }

}
