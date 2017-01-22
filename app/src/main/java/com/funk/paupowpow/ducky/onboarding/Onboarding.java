package com.funk.paupowpow.ducky.onboarding;

import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;

/**
 * Created by paulahaertel on 10.12.16.
 */

public class Onboarding {

    public static void createOnboardingQuest() {
        DuckyDatabaseHandler.getInstance().createQuest("create a picture", null);
    }

}
