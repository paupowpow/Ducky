package com.funk.paupowpow.ducky;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.funk.paupowpow.ducky.camera.CameraHandler;
import com.funk.paupowpow.ducky.fragments.DuckyFragmentManager;
import com.funk.paupowpow.ducky.fragments.PagerAdapter;
import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;
import com.funk.paupowpow.ducky.model.data.Quest;
import com.funk.paupowpow.ducky.p2pkit.P2pkitHandler;
import com.funk.paupowpow.ducky.utils.DuckyConstants;
import com.funk.paupowpow.ducky.utils.PermissionManager;

public class MainActivity extends AppCompatActivity{

    private PermissionManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DuckyFragmentManager.initialize(this, getSupportFragmentManager());
//        DuckyFragmentManager.getInstance().startQuestOverviewFragment();
        setupPagerAdapter();

        DuckyDatabaseHandler.initialize(this);
//        Onboarding.createOnboardingQuest();

        CameraHandler.initialize(this);

        P2pkitHandler.initialize(this);

        P2pkitHandler.getInstance().setP2pkitState();

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

    private void setupPagerAdapter() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
