package com.funk.paupowpow.fotohop.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.funk.paupowpow.fotohop.R;
import com.funk.paupowpow.fotohop.controller.QuestDetailImageViewController;
import com.funk.paupowpow.fotohop.model.data.Quest;

import java.io.IOException;

public class QuestDetailFragment extends Fragment {

    private static QuestDetailImageViewController viewController;

    public QuestDetailFragment() {
        // Required empty public constructor
    }

    public static QuestDetailFragment newInstance(Quest quest) {
        viewController = new QuestDetailImageViewController(quest);
        QuestDetailFragment fragment = new QuestDetailFragment();
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

        View view = inflater.inflate(R.layout.fragment_quest_detail, container, false);

        Bitmap image = null;
        try {
            image = viewController.getQuestPicture();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        imageView.setImageBitmap(image);

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

}
