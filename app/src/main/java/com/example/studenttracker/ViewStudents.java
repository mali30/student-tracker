package com.example.studenttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;


public class ViewStudents extends AppCompatActivity {


    RecyclerView recyclerView;
    Button addStudent;


    private DatabaseReference myRef;

    public ArrayList<Students> students;
    private RecyclerAdapter recyclerAdapter;
    private Button orderStudents;

    private EditText mEditTextAge;
    private EditText mEditTextAssignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        recyclerView = findViewById(R.id.recyclerView);
        addStudent = findViewById(R.id.addStudentButton);
        mEditTextAge = findViewById(R.id.EditTextAge);
        mEditTextAssignment = findViewById(R.id.EditTextAssignment);
        orderStudents = findViewById(R.id.orderStudents);



        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewStudents.this, AddStudent.class));
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();

        students = new ArrayList<>();

        ClearAll();

        GetDataFromFirebase();



    }
    private void GetDataFromFirebase() {
        Query query = myRef.child("student");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Students student = new Students();
                    if (snapshot.child("url").getValue() == null) {
                        student.setImageUrl(Objects.requireNonNull(snapshot.child("imageUrl").getValue()).toString());
                    }
                    else {
                        student.setImageUrl(snapshot.child("url").getValue().toString());

                    }
//                    student.setAge(mEditTextAge.getText().toString());
//                    student.setAssignment(mEditTextAssignment.getText().toString().trim());
                    student.setName(snapshot.child("name").getValue().toString());
                    students.add(student);
                }
                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), students);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ClearAll() {
        if (students != null) {
            students.clear();

            if(recyclerAdapter != null) {
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        students = new ArrayList<>();
    }

    public void orderStudents(View view) {
        Collections.sort( students, new Comparator<Students>() {
            @Override
            public int compare( Students o1, Students o2 ) {
                return o1.name.compareTo( o2.name );
            }
        });
        recyclerAdapter.notifyDataSetChanged();

    }

}