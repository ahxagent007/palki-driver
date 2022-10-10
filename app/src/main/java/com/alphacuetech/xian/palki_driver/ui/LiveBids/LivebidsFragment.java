package com.alphacuetech.xian.palki_driver.ui.LiveBids;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alphacuetech.xian.palki_driver.R;
import com.alphacuetech.xian.palki_driver.databinding.FragmentLivebidsBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class LivebidsFragment extends Fragment {

    RecyclerView recyclerView;
    livebids_adaptor adapter;
    private FragmentLivebidsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentLivebidsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

//        View view= inflater.inflate(R.layout.fragment_live_bids, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.livebids_rcycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<dataModel> options =
                new FirebaseRecyclerOptions.Builder<dataModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AUCTION"), dataModel.class)
                        .build();

        adapter = new livebids_adaptor(options);
        recyclerView.setAdapter(adapter);
        return  view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}