package com.alphacuetech.xian.palki_driver.ui.BidResults;

import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alphacuetech.xian.palki_driver.R;
import com.alphacuetech.xian.palki_driver.databinding.FragmentBidresultsBinding;
import com.alphacuetech.xian.palki_driver.databinding.FragmentLivebidsBinding;
import com.alphacuetech.xian.palki_driver.ui.LiveBids.dataModel;
import com.alphacuetech.xian.palki_driver.ui.LiveBids.livebids_adaptor;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class BidresultsFragment extends Fragment {

    RecyclerView recyclerView;
    bidresult_adapter adapter;
    private FragmentBidresultsBinding binding;
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference().child("BIDRESULTS");
    ArrayList<bidresultModel> bid_result_details = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_bidresults, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.bidresult_rcycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new bidresult_adapter(bid_result_details,this.getContext());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getActivity());

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                bidresultModel bidresult = snapshot.getValue(bidresultModel.class);
                bid_result_details.add(bidresult);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        return  view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }


}