package com.funk.paupowpow.ducky.controller;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.funk.paupowpow.ducky.R;
import com.funk.paupowpow.ducky.camera.CameraHandler;
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

    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;


    @Bind(R.id.quest_overview_item_text)
    TextView questText;

    @Bind(R.id.quest_overview_item_button)
    Button snapButton;

    public QuestOverviewItemViewController(View itemView, Activity activity) {
        this.itemView = itemView;
        this.activity = activity;

        ButterKnife.bind(this, itemView);
    }

    public void bindData(Quest aQuest) {
        this.quest = aQuest;
        this.questText.setText(quest.getQuestText());

        snapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraHandler.getInstance().openCamera(quest);
            }
        });
    }

    public void onViewRecycled() {
//        recycleEvent.onNext(null);
    }

    public void onViewAttachedToWindow() {
    }

    public void onViewDetachedFromWindow() {
    }
}
