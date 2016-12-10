package com.funk.paupowpow.ducky.model.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by paulahaertel on 10.12.16.
 */

public class Quest extends RealmObject {
    @PrimaryKey
    private long questId;

    private String questText;


    public String getQuestText() {
        return questText;
    }

    public void setQuestText(String questText) {
        this.questText = questText;
    }

    public long getQuestId() {
        return questId;
    }

    public void setQuestId(long questId) {
        this.questId = questId;
    }
}
