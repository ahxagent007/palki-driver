package com.alphacuetech.xian.palki_driver.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alphacuetech.xian.palki_driver.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class loggin extends AppCompatActivity {
    Button login_to_home;
    Button signin_page;

    private TextInputLayout user_email;
    private TextInputLayout user_password;
    FirebaseAuth mAuth;
//    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        user_email=findViewById(R.id.email);
        user_password=findViewById(R.id.password);
        login_to_home = findViewById(R.id.login);
        signin_page = findViewById(R.id.sigin_page);

        mAuth = FirebaseAuth.getInstance();
        login_to_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//
//                startActivity(new Intent(loggin.this, AfterLogin.class));
//                finish();

                String email= user_email.getEditText().getText().toString().trim();
                String password= user_password.getEditText().getText().toString().trim();
                if(email.isEmpty())
                {
                    user_email.setError("Email Field Can't be Empty");
                    user_email.requestFocus();
                    return;
                }

                if(password.isEmpty())
                {
                    user_password.setError("Password Field Can't be Empty");
                    user_password.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    user_password.setError("Length of password is more than 6");
                    user_password.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(loggin.this,"You Have Successfully logged In", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(loggin.this, AfterLogin.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(loggin.this,"Wrong Email or Password", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
        signin_page.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loggin.this, Registration.class);
                startActivity(intent);
            }
        });
    }

//    public void  approve_status(String email){
//
//        db = FirebaseFirestore.getInstance();
//        DocumentReference documentReference = db.collection("users").document(email);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                String status = value.getString("Status");
//                String userType = value.getString("UserType");
//                if(status.matches("Approved")){
//                    startActivity(new Intent(loggin.this, AfterLogin.class));
//                }else{
//                    startActivity(new Intent(loggin.this, needApproval.class));
//                    mAuth.getInstance().signOut();
//                }
//            }
//        });
//
//    }

}