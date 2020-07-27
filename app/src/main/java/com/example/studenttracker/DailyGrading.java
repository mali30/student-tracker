package com.example.studenttracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DailyGrading extends AppCompatActivity {

    RecyclerView recyclerView;
    Button addStudent;


    private DatabaseReference myRef;

    public ArrayList<Students> students;
    private GradingRecyclerAdapter recyclerAdapter;
    private Button orderStudents;

    private EditText mEditTextAge;
    private EditText mEditTextAssignment;

//    Button passButton;
//    Button failButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_grading);


//        passButton = findViewById(R.id.PASS);
//        failButton = findViewById(R.id.FAIL);
//
//        passButton.setVisibility(View.VISIBLE);
//        failButton.setVisibility(View.VISIBLE);



        recyclerView = findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();

        students = new ArrayList<>();

        ClearAll();

        GetDataFromFirebase();


    }

    // fetches images and name from firebase
    private void GetDataFromFirebase() {
        Query query = myRef.child("student");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Students student = new Students();
                    if (snapshot.child("url").getValue() == null) {
                        student.setImageUrl(snapshot.child("imageUrl").getValue().toString());
                    } else {
                        student.setImageUrl(snapshot.child("url").getValue().toString());

                    }
                    student.setName(snapshot.child("name").getValue().toString());
                    students.add(student);
                }
                recyclerAdapter = new GradingRecyclerAdapter(getApplicationContext(), students);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // will clear recyclerAdapter
    private void ClearAll() {
        if (students != null) {
            students.clear();

            if (recyclerAdapter != null) {
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        students = new ArrayList<>();
    }


}
