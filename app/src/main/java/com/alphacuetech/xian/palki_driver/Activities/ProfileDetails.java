package com.alphacuetech.xian.palki_driver.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.alphacuetech.xian.palki_driver.R;

public class ProfileDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("key");
            if(value==3){
                setContentView(R.layout.profiledetails);


            }
            else if(value==4){
                setContentView(R.layout.credit);


            }
            //The key argument here must match that used in the other activity
        }
        ImageButton backbtn=findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}