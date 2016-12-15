package com.funk.paupowpow.ducky;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.funk.paupowpow.ducky.fragments.DuckyFragmentManager;
import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;
import com.funk.paupowpow.ducky.onboarding.Onboarding;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DuckyFragmentManager.initialize(this, getSupportFragmentManager());
        DuckyFragmentManager.getInstance().startQuestOverviewFragment();

        DuckyDatabaseHandler.initialize(this);
        Onboarding.createOnboardingQuest();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            DuckyFragmentManager.getInstance().startQuestDetailFragment();
        }
    }
}
