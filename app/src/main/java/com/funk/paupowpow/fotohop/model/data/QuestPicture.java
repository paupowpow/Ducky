package com.funk.paupowpow.fotohop.model.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by paulahaertel on 17.12.16.
 */

public class QuestPicture extends RealmObject {
    @PrimaryKey
    private long questPictureId;

    private String questPictureUriString;

    private Quest quest;

    public long getQuestPictureId() {
        return questPictureId;
    }

    public void setQuestPictureId(long questPictureId) {
        this.questPictureId = questPictureId;
    }

    public String getQuestPictureUriString() {
        return questPictureUriString;
    }

    public void setQuestPictureUri(String questPictureUri) {
        this.questPictureUriString = questPictureUri;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}
