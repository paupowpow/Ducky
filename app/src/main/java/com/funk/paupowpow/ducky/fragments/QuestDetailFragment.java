package com.funk.paupowpow.ducky.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.funk.paupowpow.ducky.R;

public class QuestDetailFragment extends Fragment {

//    @Bind(R.id.quest_list)
//    RecyclerView questList;

//    private Context context;
//    private QuestDetailListAdapter adapter;

    public QuestDetailFragment() {
        // Required empty public constructor
    }

    public static QuestDetailFragment newInstance() {
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
        Log.d("QuestDetailFragment", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_quest_detail, container, false);

//        ButterKnife.bind(this, view);

//        setupRecycler();
//
//        setRealmAdapter(DuckyDatabaseHandler.getInstance().getQuests());

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

//    private void setupRecycler() {
//        questList.setHasFixedSize(true);
//
//        // quest items -> linear layout manager is neededed
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        questList.setLayoutManager(layoutManager);
//
//        //create an empty adapter and add it to the recycler view
//        adapter = new QuestDetailListAdapter(getActivity());
//        questList.setAdapter(adapter);
//    }
//
//    private void setRealmAdapter(RealmResults<Quest> quests) {
//        RealmQuestAdapter realmQuestAdapter = new RealmQuestAdapter(this.getActivity(), quests, true);
//
//        adapter.setRealmAdapter(realmQuestAdapter);
//        adapter.notifyDataSetChanged();
//    }
}
