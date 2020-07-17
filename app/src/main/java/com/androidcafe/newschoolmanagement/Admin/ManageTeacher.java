package com.androidcafe.newschoolmanagement.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidcafe.newschoolmanagement.Adapters.NewManageFacultyAdapter;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.FacultyModel;
import com.androidcafe.newschoolmanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ManageTeacher extends AppCompatActivity {

    FirebaseFirestore db;
    List<FacultyModel> list;
    NewManageFacultyAdapter adapter;
    RecyclerView recyclerView;

    FloatingActionButton fab;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);

        db = FirebaseFirestore.getInstance();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Manage Teacher");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        getDatafromFirestore();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageTeacher.this, AddTeacher.class));
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return super.onSupportNavigateUp();
    }

    private void getDatafromFirestore() {

        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_teacher_db).orderBy("teacher_name", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {


                    if (task.getResult().isEmpty())
                    {

                    }
                    list = new ArrayList<>();

                    for (QueryDocumentSnapshot document:task.getResult())
                    {




                        FacultyModel model = document.toObject(FacultyModel.class);
                        list.add(model);

                    }
                    updateRecyclerview(list);



                }
                else
                {

                }



            }
        });





    }

    private void updateRecyclerview(List<FacultyModel> list)
    {

        adapter = new NewManageFacultyAdapter(this,list);
        recyclerView.setAdapter(adapter);



    }

}
