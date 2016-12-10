package com.funk.paupowpow.ducky.controller;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.funk.paupowpow.ducky.R;
import com.funk.paupowpow.ducky.model.data.Quest;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by paulahaertel on 10.12.16.
 */
public class QuestOverviewItemViewController {

    private View itemView;
    private Activity activity;
    private Quest quest;

    @Bind(R.id.quest_overview_item_text)
    TextView questText;

    public QuestOverviewItemViewController(View itemView, Activity activity) {
        this.itemView = itemView;
        this.activity = activity;

        ButterKnife.bind(this, itemView);
    }

    public void bindData(Quest quest) {
        this.quest = quest;
        this.questText.setText(quest.getQuestText());

        Log.d("QUEST", "" + quest.getQuestText());
    }

    public void onViewRecycled() {
//        recycleEvent.onNext(null);
    }

    public void onViewAttachedToWindow() {
    }

    public void onViewDetachedFromWindow() {
    }
}
