package com.funk.paupowpow.fotohop.controller;

import com.funk.paupowpow.fotohop.model.data.FotohopDatabaseHandler;

/**
 * Created by paulahaertel on 19.12.16.
 */

public class QuestCreateViewController {

    public void createQuest(String questText) {
        FotohopDatabaseHandler.getInstance().createQuest(questText, null, null);
    }
}
