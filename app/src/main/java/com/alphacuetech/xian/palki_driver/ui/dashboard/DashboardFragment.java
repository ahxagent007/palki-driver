package com.alphacuetech.xian.palki_driver.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.alphacuetech.xian.palki_driver.databinding.FragmentDashboardBinding;
import com.alphacuetech.xian.palki_driver.utility.turnOnLocation;
public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        turnOnLocation location_set = new turnOnLocation();
        location_set.showLocationPrompt(this.getActivity());

       // TextView TV_dummy = binding.TVDummy;

       // TV_dummy.setText("KISU EKTA ROHIM");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}