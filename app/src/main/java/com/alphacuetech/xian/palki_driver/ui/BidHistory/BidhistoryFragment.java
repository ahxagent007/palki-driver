package com.alphacuetech.xian.palki_driver.ui.BidHistory;

import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alphacuetech.xian.palki_driver.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class BidhistoryFragment extends Fragment {

    RecyclerView recyclerView;
    bidhistory_adapter adapter;
    private BidhistoryFragment binding;
    private FirebaseAuth mAuth;
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference().child("BIDS2");
    ArrayList<bidhistoryModel> bid_history_details = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_bidhistory, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.bidhistory_rcycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new bidhistory_adapter(bid_history_details,this.getContext());

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    for (DataSnapshot child1 : child.getChildren()) {
                        bidhistoryModel bidresult = child1.getValue(bidhistoryModel.class);
                        Map map = (Map) child1.getValue();
                        String driverID =(String) map.get("driverID");
                        mAuth = FirebaseAuth.getInstance();
                        String currentUserId = mAuth.getCurrentUser().getUid();
                        if(driverID.equals(currentUserId)) {
                            bid_history_details.add(bidresult);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

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


}