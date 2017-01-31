package com.funk.paupowpow.fotohop.controller;

import android.graphics.Bitmap;

import com.funk.paupowpow.fotohop.model.data.FotohopDatabaseHandler;
import com.funk.paupowpow.fotohop.model.data.Quest;

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
        return FotohopDatabaseHandler.getInstance().getQuestPicture(quest);
    }

}
