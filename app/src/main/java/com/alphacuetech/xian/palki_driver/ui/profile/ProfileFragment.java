package com.alphacuetech.xian.palki_driver.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alphacuetech.xian.palki_driver.Activities.AfterLogin;
import com.alphacuetech.xian.palki_driver.Activities.ProfileDetails;
import com.alphacuetech.xian.palki_driver.R;
import com.alphacuetech.xian.palki_driver.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageButton btn_profile_profile=root.findViewById(R.id.profile_profile);
        ImageButton btn_profile_credit=root.findViewById(R.id.profile_credit);
        btn_profile_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ProfileDetails.class);
                i.putExtra("key",3);
                startActivity(i);

            }
        });
        btn_profile_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ProfileDetails.class);
                i.putExtra("key",4);
                startActivity(i);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}