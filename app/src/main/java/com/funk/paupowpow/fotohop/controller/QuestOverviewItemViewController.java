package com.funk.paupowpow.fotohop.controller;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.funk.paupowpow.fotohop.R;
import com.funk.paupowpow.fotohop.camera.CameraHandler;
import com.funk.paupowpow.fotohop.model.data.Quest;

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

    @Bind(R.id.quest_overview_item_button)
    ImageButton snapButton;

    public QuestOverviewItemViewController(View itemView, Activity activity) {
        this.itemView = itemView;
        this.activity = activity;

        ButterKnife.bind(this, itemView);
    }

    public void bindData(Quest aQuest) {
        quest = aQuest;
        questText.setText(quest.getQuestText());

//        quest completed -> disable button
//        quest not completed -> set onClickListener
        if(quest.getCompleted() == true) {
            snapButton.setEnabled(false);
        } else {
            snapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CameraHandler.getInstance().openCamera(quest);
                }
            });
        }
    }

    public void onViewRecycled() {
//        recycleEvent.onNext(null);
    }

    public void onViewAttachedToWindow() {
    }

    public void onViewDetachedFromWindow() {
    }

}
