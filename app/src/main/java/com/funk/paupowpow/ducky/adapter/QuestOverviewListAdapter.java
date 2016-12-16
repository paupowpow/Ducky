package com.funk.paupowpow.ducky.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.funk.paupowpow.ducky.R;
import com.funk.paupowpow.ducky.model.data.DuckyDatabaseHandler;
import com.funk.paupowpow.ducky.model.data.Quest;
import com.funk.paupowpow.ducky.viewholder.QuestOverviewItemViewHolder;

import io.realm.Realm;

/**
 * Created by paulahaertel on 10.12.16.
 */

public class QuestOverviewListAdapter extends RealmRecyclerViewAdapter<Quest> {

    private Realm realm;
    private final Activity activity;

    public QuestOverviewListAdapter(Activity activity) {
        this.activity = activity;
    }

    // create new views
    @Override
    public QuestOverviewItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_quest_overview_item, parent, false);
        return new QuestOverviewItemViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = DuckyDatabaseHandler.getInstance().getRealm();

        final Quest quest = getItem(position);

        // cast generic view holder to specific view holder
        final QuestOverviewItemViewHolder holder = (QuestOverviewItemViewHolder) viewHolder;

        // the adapter telling the ViewHolder to do what it can do best,
        // i.e. binding the quest data (retrieved by the adapter) to the corresponding view
        holder.bind(quest);

    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {

        final QuestOverviewItemViewHolder viewHolder = (QuestOverviewItemViewHolder) holder;
        viewHolder.onViewRecycled();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {

        final QuestOverviewItemViewHolder viewHolder = (QuestOverviewItemViewHolder) holder;
        viewHolder.onViewAttachedToWindow();
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {

        final QuestOverviewItemViewHolder viewHolder = (QuestOverviewItemViewHolder) holder;
        viewHolder.onViewDetachedFromWindow();
    }

}
