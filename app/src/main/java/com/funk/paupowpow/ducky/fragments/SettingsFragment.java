package com.funk.paupowpow.ducky.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.funk.paupowpow.ducky.R;
import com.funk.paupowpow.ducky.p2pkit.P2PKitEnabledCallback;
import com.funk.paupowpow.ducky.p2pkit.P2pkitHandler;

public class SettingsFragment extends Fragment {

    public interface ConsoleListener {
        void enableKit(final boolean startP2PService, P2PKitEnabledCallback p2PKitEnabledCallback);

        void disableKit();

        void startP2pDiscovery();

        void stopP2pDiscovery();

    }

    private ConsoleListener listener;

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

//        Bundle args = getArguments();

        Switch kitSwitch = (Switch) view.findViewById(R.id.p2pkit_switch);

//        kitSwitch.setChecked(args.getBoolean(KIT_ENABLED_KEY, false));
        kitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    listener.enableKit(false, new P2PKitEnabledCallback() {
                        @Override
                        public void onEnabled() {
//                            mP2pSwitch.setEnabled(true);
//                            mGeoSwitch.setEnabled(true);
                        }
                    });
                } else {
                    listener.disableKit();

//                    mP2pSwitch.setEnabled(false);
//                    mGeoSwitch.setEnabled(false);
//                    mP2pSwitch.setChecked(false);
//                    mGeoSwitch.setChecked(false);
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ConsoleListener) P2pkitHandler.getInstance();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(context.toString() + " must implement ConsoleListener, e: " + e.getMessage() , e);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
