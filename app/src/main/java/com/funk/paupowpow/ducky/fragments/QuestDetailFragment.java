package com.funk.paupowpow.ducky.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.funk.paupowpow.ducky.R;
import com.funk.paupowpow.ducky.controller.QuestDetailImageViewController;
import com.funk.paupowpow.ducky.model.data.Quest;

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

        Bitmap image = viewController.getQuestPicture();

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
