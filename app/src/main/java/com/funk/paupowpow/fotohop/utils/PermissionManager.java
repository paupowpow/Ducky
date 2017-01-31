package com.funk.paupowpow.fotohop.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Elliott on 18/12/2016.
 */

public class PermissionManager {

    private static final String TAG = "PermissionManager";

    private Activity activity;

    public PermissionManager(Activity activity) {
        this.activity = activity;
    }

    public void requestPermissions(String permissions[], int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode); // Request permission
    }

    public boolean permissionIsGranted(String permission) {
        switch (ContextCompat.checkSelfPermission(activity, permission)) {
            case PackageManager.PERMISSION_GRANTED: // Permission has been granted
                Log.i(TAG, "GRANTED: " + permission);
                return true;
            case PackageManager.PERMISSION_DENIED: // Permission has been denied
                Log.i(TAG, "DENIED: " + permission);
                return false;
            default: // GG
                Log.e(TAG, "Neither denied nor granted: " + permission);
                return false;
        }
    }

}
