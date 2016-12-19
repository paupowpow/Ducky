package com.funk.paupowpow.ducky;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.funk.paupowpow.ducky.camera.CameraHandler;
import com.funk.paupowpow.ducky.fragments.DuckyFragmentManager;
import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;
import com.funk.paupowpow.ducky.model.data.Quest;
import com.funk.paupowpow.ducky.onboarding.Onboarding;
import com.funk.paupowpow.ducky.utils.DuckyConstants;
import com.funk.paupowpow.ducky.utils.PermissionManager;

public class MainActivity extends AppCompatActivity {

    private PermissionManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DuckyFragmentManager.initialize(this, getSupportFragmentManager());
        DuckyFragmentManager.getInstance().startQuestOverviewFragment();

        DuckyDatabaseHandler.initialize(this);
        Onboarding.createOnboardingQuest();

        CameraHandler.initialize(this);

        pm = new PermissionManager(this);

        checkPermissions();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Quest questToOpen = CameraHandler.getInstance().handleResult(requestCode, resultCode, data);

        if (questToOpen != null) {
            DuckyFragmentManager.getInstance().startQuestDetailFragment(questToOpen);
        }
    }

    private void checkPermissions() {
        pm.requestPermissions(
                new String[] {
                        Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                DuckyConstants.PERMISSION_REQUEST_CODE_CAMERA |
                        DuckyConstants.PERMISSION_REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
    }




}
