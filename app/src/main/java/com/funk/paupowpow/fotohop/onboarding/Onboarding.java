package com.funk.paupowpow.fotohop.onboarding;

import com.funk.paupowpow.fotohop.model.data.FotohopDatabaseHandler;

/**
 * Created by paulahaertel on 10.12.16.
 */

public class Onboarding {

    public static void createOnboardingQuest() {
        FotohopDatabaseHandler.getInstance().createQuest("create a picture", null, null);
    }

}
