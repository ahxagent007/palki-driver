package com.alphacuetech.xian.palki_driver.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class current_location extends AppCompatActivity {
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int PERMISSION_FINE_LOCATION = 99;


    public void getCurrentLocation() {

//       locationRequest = new LocationRequest();
//        locationRequest.setInterval(1000 * 30);
//
//        locationRequest.setFastestInterval(1000 * 5);
//
//        locationRequest.setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Toast.makeText(current_location.this.getApplicationContext(),String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();
                    Toast.makeText(current_location.this.getApplicationContext(),String.valueOf(location.getLongitude()), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
            }
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode){
//            case PERMISSION_FINE_LOCATION:
//                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    getLocation();
//                } else {
//                    Toast.makeText(this, "This app requires permission to be granted in order to work properly", Toast.LENGTH_SHORT).show();
//
//                }
//        }
//    }
}
