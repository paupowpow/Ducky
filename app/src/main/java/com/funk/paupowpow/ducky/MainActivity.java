package com.funk.paupowpow.ducky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DuckyFragmentManager.initialize(this, getSupportFragmentManager());
        DuckyFragmentManager.getInstance().startQuestOverviewFragment();
    }
}
