package com.example.studenttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText emailAddress, password;
    Button signInButton;
    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireBaseAuth = FirebaseAuth.getInstance();
        emailAddress = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);
        signInButton = findViewById(R.id.signInButton);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFireBaseAuth.getCurrentUser();
                if(mFirebaseUser!=null) {
                    Toast.makeText(MainActivity.this, "Already logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()) {
                    emailAddress.setError("Please enter an email address.");
                    emailAddress.requestFocus();
                }
                else if(pwd.isEmpty()) {
                    password.setError("Please enter your password.");
                    password.requestFocus();
                }
                else {
                    mFireBaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login Failed. Please try Again.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent toMainMenu = new Intent(MainActivity.this, MainMenu.class);
                                startActivity(toMainMenu);
                            }
                        }
                    });
                }


            }
        });

    }
    protected void onStart() {
        super.onStart();
        mFireBaseAuth.addAuthStateListener(mAuthStateListener);
    }
}