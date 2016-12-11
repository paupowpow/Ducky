package com.funk.paupowpow.ducky.controller;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    public void bindData(Quest quest) {
        this.quest = quest;
        this.questText.setText(quest.getQuestText());

        Log.d("QUEST", "" + quest.getQuestText());

        snapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                    activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
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
