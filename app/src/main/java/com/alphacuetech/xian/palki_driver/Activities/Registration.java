package com.alphacuetech.xian.palki_driver.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


import com.alphacuetech.xian.palki_driver.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity {

    Button s_submit,go_login_btn;
    TextInputLayout s_email, s_password, s_confirm_pass, user_location, user_name, user_phonenumber;
    FirebaseAuth fAuth;


    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private static int RC_SIGN_IN =100;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        go_login_btn = findViewById(R.id.login_page_btn);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this,gso);

        s_email = findViewById(R.id.email);
        s_password = findViewById(R.id.password);
        s_submit = findViewById(R.id.registration_btn);
        user_name = findViewById(R.id.name);
        user_phonenumber = findViewById(R.id.phone_number);
        user_location = findViewById(R.id.address);
        s_confirm_pass = findViewById(R.id.confirm_pass);
        fAuth = FirebaseAuth.getInstance();

        s_submit.setOnClickListener(view -> {
            createUser();
        });

        go_login_btn.setOnClickListener(view ->{
            Intent intent = new Intent(Registration.this, loggin.class);
            startActivity(intent);
        });
    };



    private void createUser(){
        String user_email = s_email.getEditText().getText().toString().trim();
        String pass = s_password.getEditText().getText().toString().trim();
        String name = user_name.getEditText().getText().toString().trim();
        String phonenumber = user_phonenumber.getEditText().getText().toString().trim();
        String location = user_location.getEditText().getText().toString().trim();
        String con_pass = s_confirm_pass.getEditText().getText().toString().trim();

        if(name.isEmpty())
        {
            user_name.setError("Name Field Can't be Empty");
            user_name.requestFocus();
            return;
        }

        if(user_email.isEmpty())
        {
            s_email.setError("Email Field Can't be Empty");
            s_email.requestFocus();
            return;
        }

        if(phonenumber.isEmpty())
        {
            user_phonenumber.setError("Phone Number Field Can't be Empty");
            user_phonenumber.requestFocus();
            return;
        }

        if(location.isEmpty())
        {
            user_location.setError("Location Field Can't be Empty");
            user_location.requestFocus();
            return;
        }

        if(pass.isEmpty())
        {
            s_password.setError("Password Field Can't be Empty");
            s_password.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            s_password.setError("Length of s_password is more than 6");
            s_password.requestFocus();
            return;
        }

        if(!con_pass.equals(pass))
        {
            s_confirm_pass.setError("Both password Should be matched");
            s_confirm_pass.requestFocus();
            return;
        }

        fAuth.createUserWithEmailAndPassword(user_email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    try {
                        Toast.makeText(Registration.this,"Registration Has been Completed",Toast.LENGTH_SHORT).show();

//                        addDataToFirestore(name, phonenumber, location, user_email);
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            public void run() {
//                                fAuth.getInstance().signOut();
//                            }
//                        }, 3000);

                        startActivity(new Intent(Registration.this,AfterLogin.class));
                        finish();
                    }catch (Exception e){
                        Toast.makeText(Registration.this,e.toString(),Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Registration.this,"SignedIn Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    };

//    private void addDataToFirestore(String userName, String phone_number, String location, String email) {
//
//        db = FirebaseFirestore.getInstance();
//        CollectionReference dbUsers = db.collection("users");
//        Map<String,Object> user_info = new HashMap<>();
//
//        user_info.put("Email",email);
//        user_info.put("Name",userName);
//        user_info.put("PhoneNumber",phone_number);
//        user_info.put("Location",location);
//        user_info.put("Status","Approved");
//        user_info.put("UserType","user");
//
////        user_info.put("admin_",false);
//        dbUsers.document(email).set(user_info);
//
//    }

};