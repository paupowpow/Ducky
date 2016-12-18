package com.funk.paupowpow.ducky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.funk.paupowpow.ducky.camera.CameraHandler;
import com.funk.paupowpow.ducky.fragments.DuckyFragmentManager;
import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;
import com.funk.paupowpow.ducky.model.data.Quest;
import com.funk.paupowpow.ducky.onboarding.Onboarding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DuckyFragmentManager.initialize(this, getSupportFragmentManager());
        DuckyFragmentManager.getInstance().startQuestOverviewFragment();

        DuckyDatabaseHandler.initialize(this);
        Onboarding.createOnboardingQuest();

        CameraHandler.initialize(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Quest questToOpen = CameraHandler.getInstance().handleResult(requestCode, resultCode, data);

        if (questToOpen != null) {
            DuckyFragmentManager.getInstance().startQuestDetailFragment(questToOpen);
        }
    }



}
