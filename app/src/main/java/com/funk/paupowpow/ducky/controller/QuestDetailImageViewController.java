package com.funk.paupowpow.ducky.controller;

import android.graphics.Bitmap;

import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;
import com.funk.paupowpow.ducky.model.data.Quest;

import java.io.IOException;

/**
 * Created by paulahaertel on 16.12.16.
 */

public class QuestDetailImageViewController {

    protected Quest quest;

    public QuestDetailImageViewController(Quest quest) {
        this.quest = quest;
    }

    public Bitmap getQuestPicture() throws IOException {
        return DuckyDatabaseHandler.getInstance().getQuestPicture(quest);
    }

}
