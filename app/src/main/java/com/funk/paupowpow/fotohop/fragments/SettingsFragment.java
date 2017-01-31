package com.funk.paupowpow.fotohop.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.funk.paupowpow.fotohop.R;
import com.funk.paupowpow.fotohop.model.data.FotohopDatabaseHandler;
import com.funk.paupowpow.fotohop.p2pkit.P2pkitHandler;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingsFragment extends Fragment {

    @Bind(R.id.p2pkit_switch)
    Switch kitSwitch;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
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

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);

        setupKitSwitch();

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

    private void setupKitSwitch() {

//        kitSwitch.setEnabled(FotohopDatabaseHandler.getInstance().isP2pkitStateEnabled());

        kitSwitch.setChecked(FotohopDatabaseHandler.getInstance().isP2pkitStateEnabled());

        kitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FotohopDatabaseHandler.getInstance().updateP2pkitState();
                P2pkitHandler.getInstance().setP2pkitState();
            }
        });
    }
}
