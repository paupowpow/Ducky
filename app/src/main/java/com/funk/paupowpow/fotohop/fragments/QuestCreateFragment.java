package com.funk.paupowpow.fotohop.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.funk.paupowpow.fotohop.R;
import com.funk.paupowpow.fotohop.controller.QuestCreateViewController;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quest_create, container, false);
        ButterKnife.bind(this, view);
        setupSubmitButton();
        return view;
    }

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
                closeKeyboard(getActivity(), inputQuestText.getWindowToken());
                FotohopFragmentManager.getInstance().popFragment();
            }
        });
    }

    public static void closeKeyboard(Context c, IBinder windowToken) {
        InputMethodManager inputMethodManager = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
    }


}