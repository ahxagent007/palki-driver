package com.alphacuetech.xian.palki_driver.utility;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

public class turnOnLocation extends AppCompatActivity {

    public static final int REQUEST_CHECK_SETTING = 1001;
    public void showLocationPrompt(Activity activity){
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        builder.setAlwaysShow(true);


        SettingsClient client = LocationServices.getSettingsClient(activity.getApplicationContext());
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
//                    Toast.makeText(activity.getApplicationContext(), "Location is On", Toast.LENGTH_SHORT).show();
                } catch (ApiException e) {
                   switch (e.getStatusCode()){
                       case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                           try {
                               ResolvableApiException resolvableApiException = (ResolvableApiException)e;
                               resolvableApiException.startResolutionForResult(activity,REQUEST_CHECK_SETTING);
                           } catch (IntentSender.SendIntentException ex) {

                           }
                           break;

                       case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                           break;
                   }
                }
            }
        });
        task.addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(activity,
                                RESULT_OK);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CHECK_SETTING){
            switch (resultCode){
                case Activity.RESULT_OK:
                    Toast.makeText(this, "Location has turn on", Toast.LENGTH_SHORT).show();
                    break;

                case Activity.RESULT_CANCELED:
                    Toast.makeText(this, "Location Should be Turn On", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
