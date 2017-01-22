package com.funk.paupowpow.ducky.controller;

import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;

/**
 * Created by paulahaertel on 19.12.16.
 */

public class QuestCreateViewController {

    public void createQuest(String questText) {
        DuckyDatabaseHandler.getInstance().createQuest(questText, null);
    }
}
