package com.funk.paupowpow.fotohop;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.funk.paupowpow.fotohop.camera.CameraHandler;
import com.funk.paupowpow.fotohop.fragments.FotohopFragmentManager;
import com.funk.paupowpow.fotohop.adapter.PagerAdapter;
import com.funk.paupowpow.fotohop.model.data.FotohopDatabaseHandler;
import com.funk.paupowpow.fotohop.model.data.Quest;
import com.funk.paupowpow.fotohop.p2pkit.P2pkitHandler;
import com.funk.paupowpow.fotohop.utils.FotohopConstants;
import com.funk.paupowpow.fotohop.utils.PermissionManager;

public class MainActivity extends AppCompatActivity{

    private PermissionManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FotohopFragmentManager.initialize(this, getSupportFragmentManager());
//        FotohopFragmentManager.getInstance().startQuestOverviewFragment();
        setupPagerAdapter();

        FotohopDatabaseHandler.initialize(this);
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
            FotohopFragmentManager.getInstance().startQuestDetailFragment(questToOpen);
        }
    }

    private void checkPermissions() {
        pm.requestPermissions(
            new String[] {
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
            FotohopConstants.PERMISSION_REQUEST_CODE_CAMERA |
                FotohopConstants.PERMISSION_REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
    }

    private void setupPagerAdapter() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("settings"));
        tabLayout.addTab(tabLayout.newTab().setText("quests"));
        tabLayout.addTab(tabLayout.newTab().setText("notifications"));
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
