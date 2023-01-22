package com.alphacuetech.xian.palki_driver.ui.CurrentTrips;

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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class currentTripsFragment extends Fragment {

    RecyclerView recyclerView;
    currentTrips_adapter adapter;
    private currentTripsFragment binding;
    private FirebaseAuth mAuth;
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference().child("BIDRESULT2");
    ArrayList<currentTripsModel> bid_result_details = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_currenttrips, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.currenttrips_rcycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new currentTrips_adapter(bid_result_details,this.getContext());

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                currentTripsModel bidresult = snapshot.getValue(currentTripsModel.class);
                Map map = (Map) snapshot.getValue();
                String driverId =(String) map.get("driver_id");
                mAuth = FirebaseAuth.getInstance();
                String currentUserID = mAuth.getCurrentUser().getUid();
                if(driverId.contentEquals(currentUserID)) {
                    bid_result_details.add(bidresult);
                    adapter.notifyDataSetChanged();
                }
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