package com.alphacuetech.xian.palki_driver.ui.LiveBids;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alphacuetech.xian.palki_driver.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class LivebidsFragment extends Fragment {

    RecyclerView recyclerView;
    livebids_adaptor adapter;
    private LivebidsFragment binding;
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference reference = db.getReference().child("AUCTION2");
    ArrayList<dataModel> bids_details = new ArrayList<>();

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_livebids, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.livebids_rcycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new livebids_adaptor(bids_details,this.getContext());
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getActivity());

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                dataModel live_bid = snapshot.getValue(dataModel.class);
                Map map = (Map) snapshot.getValue();

                String running = (String) map.get("running");
                String from_latLng_string =(String) map.get("from_latLng");
                from_latLng_string =(from_latLng_string.split(":"))[1].replaceAll("[()]","");

                double from_lat = Double.parseDouble((from_latLng_string.split(","))[0]);
                double from_lon =  Double.parseDouble((from_latLng_string.split(","))[1]);

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    fusedLocationProviderClient.getLastLocation().addOnSuccessListener( new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
<<<<<<< HEAD
                            double distance_KM = distance(from_lat,from_lon,location.getLatitude(),location.getLongitude()) * 1.60934;
                            if(distance_KM <= 10.00 && running.equals("Yes")){
                                bids_details.add(live_bid);
                                adapter.notifyDataSetChanged();
                            }
=======
                                Log.d("star","**************************************************************"+running);

                                double distance_KM = distance(from_lat,from_lon,location.getLatitude(),location.getLongitude()) * 1.60934;
                                Log.d("distance Miles", String.valueOf(distance_KM));

                                if(distance_KM <= 10.00 && running.equals("Yes")){
                                    bids_details.add(live_bid);
                                    adapter.notifyDataSetChanged();
                               }
>>>>>>> 384aba8f124ae9f7845718ace7b26677cac65f81
                        }
                    });
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


    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
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

