package com.funk.paupowpow.ducky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.funk.paupowpow.ducky.fragments.DuckyFragmentManager;
import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;
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
    }
}
