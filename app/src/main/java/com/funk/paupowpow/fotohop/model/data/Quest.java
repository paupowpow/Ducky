package com.funk.paupowpow.fotohop.model.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by paulahaertel on 10.12.16.
 */

public class Quest extends RealmObject {
    @PrimaryKey
    private String questId;

    private String questText;

    private Boolean isCompleted;

    private int hopCounter;

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public String getQuestText() {
        return questText;
    }

    public void setQuestText(String questText) {
        this.questText = questText;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getQuestInfo() {
        return questText + "!@#$" + questId + "$#@!" + hopCounter;
    }

    public int getHopCounter() {
        return hopCounter;
    }

    public void setHopCounter(int hopCounter) {
        this.hopCounter = hopCounter;
    }
}
