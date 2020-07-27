package com.example.studenttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity {
    Button logoutButton;
    FirebaseAuth mFireBaseAuth;
    Button viewStudents;
    Button dailyGrading;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        logoutButton = findViewById(R.id.logoutButton);
        viewStudents = findViewById(R.id.viewAddStudents);
        dailyGrading = findViewById(R.id.dailyGrading);

        viewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toView = new Intent(MainMenu.this, ViewStudents.class);
                startActivity(toView);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent backToSignIn = new Intent(MainMenu.this, MainActivity.class);
                startActivity(backToSignIn);
            }
        });

        dailyGrading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dailyGrading = new Intent(MainMenu.this, DailyGrading.class);
                startActivity(dailyGrading);
            }
        });

    }
}