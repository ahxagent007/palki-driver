package com.alphacuetech.xian.palki_driver.utility;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class update_location extends AppCompatActivity {
    private final int PERMISSION_FINE_LOCATION = 99;
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;
    public double newLocation;

    public update_location(Activity activity) {
        driver_location(activity);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    driver_location();
                } else {
                    Toast.makeText(this, "This app requires permission to be granted in order to work properly", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
                    }

                }
        }
    }

    public void driver_location(Activity activity) {

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        if (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {

                @Override
                public void onSuccess(Location location) {
//                    Intent intent = new Intent(progressbar.this, AfterLogin.class);
//                    startActivity(intent);
//                    finish();
                    newLocation = location.getLongitude();
                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
            }
        }
    }
}
