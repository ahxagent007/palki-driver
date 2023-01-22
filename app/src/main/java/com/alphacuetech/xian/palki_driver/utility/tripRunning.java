package com.alphacuetech.xian.palki_driver.utility;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class tripRunning {

    public boolean isRunning(String currentUser) {
        final boolean[] isRun = new boolean[1];
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference().child("BIDRESULT2");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map value =(Map) dataSnapshot.getValue();

                for (Object key : value.keySet()) {
                    Map driverIdMap =(Map) value.get(key);
                    String driverId =(String) driverIdMap.get("driver_id");
                    if(driverId.equals(currentUser)){
                        isRun[0] = true;
                        Log.d("isRun******",String.valueOf(isRun[0]));
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle the error
            }
        });
        try {
            Thread.sleep(1000);  // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("before return", String.valueOf(isRun[0]));
        return isRun[0];
    }
}
