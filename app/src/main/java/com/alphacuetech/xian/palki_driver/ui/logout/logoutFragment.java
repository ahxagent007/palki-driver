package com.alphacuetech.xian.palki_driver.ui.logout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphacuetech.xian.palki_driver.Activities.loggin;
import com.alphacuetech.xian.palki_driver.R;
import com.google.firebase.auth.FirebaseAuth;

public class logoutFragment extends Fragment {

    FirebaseAuth fAuth;
    public static logoutFragment newInstance() {
        return new logoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        new AlertDialog.Builder(logoutFragment.this.getContext())
                .setTitle("Confirm Logout?")
                .setMessage("Are you sure to Logout?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        fAuth.getInstance().signOut();
                        Intent i = new Intent(logoutFragment.this.getActivity(), loggin.class);
                        startActivity(i);
                    }
                }).create().show();

        return view;
    }


}