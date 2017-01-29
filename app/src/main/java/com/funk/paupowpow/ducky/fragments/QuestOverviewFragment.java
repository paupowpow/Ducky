package com.funk.paupowpow.ducky.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.funk.paupowpow.ducky.R;
import com.funk.paupowpow.ducky.adapter.QuestOverviewListAdapter;
import com.funk.paupowpow.ducky.adapter.RealmQuestAdapter;
import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;
import com.funk.paupowpow.ducky.model.data.Quest;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class QuestOverviewFragment extends Fragment {

    @Bind(R.id.quest_list)
    RecyclerView questList;

    @Bind(R.id.quest_create_button)
    Button createQuestButton;

    private Context context;
    private QuestOverviewListAdapter adapter;

    private BroadcastReceiver dbChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            adapter.notifyDataSetChanged();
        }
    };

    public QuestOverviewFragment() {
        // Required empty public constructor
    }

    public static QuestOverviewFragment newInstance() {
        QuestOverviewFragment fragment = new QuestOverviewFragment();
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

        View view = inflater.inflate(R.layout.fragment_quest_overview, container, false);

        ButterKnife.bind(this, view);

        setupRecycler();

        setRealmAdapter(DuckyDatabaseHandler.getInstance().getQuests());

        setupCreateButton();

        setupDBChangeReceiver();

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(dbChangeReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        questList.setHasFixedSize(true);

        // quest items -> linear layout manager is neededed
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        questList.setLayoutManager(layoutManager);

        //create an empty adapter and add it to the recycler view
        adapter = new QuestOverviewListAdapter(getActivity());
        questList.setAdapter(adapter);
    }

    private void setRealmAdapter(RealmResults<Quest> quests) {
        RealmQuestAdapter realmQuestAdapter = new RealmQuestAdapter(this.getActivity(), quests, true);

        adapter.setRealmAdapter(realmQuestAdapter);
        adapter.notifyDataSetChanged();
    }

    private void setupCreateButton() {
        createQuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open fragment with fragment manager
                DuckyFragmentManager.getInstance().startQuestCreateFragment();
            }
        });
    }

    private void setupDBChangeReceiver() {
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(dbChangeReceiver, new IntentFilter("dbChange"));
    }
}
