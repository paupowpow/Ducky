package com.funk.paupowpow.ducky.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.funk.paupowpow.ducky.R;
import com.funk.paupowpow.ducky.controller.QuestCreateViewController;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuestCreateFragment extends Fragment {

    private static QuestCreateViewController controller;

    public QuestCreateFragment() {
        // Required empty public constructor
    }

    @Bind(R.id.quest_create_input_text)
    EditText inputQuestText;

    @Bind(R.id.quest_submit_button)
    Button submitQuestButton;

    public static QuestCreateFragment newInstance() {
        QuestCreateFragment fragment = new QuestCreateFragment();
        controller = new QuestCreateViewController();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quest_create, container, false);
        ButterKnife.bind(this, view);
        setupSubmitButton();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setupSubmitButton() {
        submitQuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.createQuest(inputQuestText.getText().toString());
                DuckyFragmentManager.getInstance().popFragment();
            }
        });
    }
}
