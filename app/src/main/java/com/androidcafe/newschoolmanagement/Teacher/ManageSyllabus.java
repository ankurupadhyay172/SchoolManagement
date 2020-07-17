package com.androidcafe.newschoolmanagement.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidcafe.newschoolmanagement.Common.GetFireStoreData;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.SyllabusModel;
import com.androidcafe.newschoolmanagement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ManageSyllabus extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    List<SyllabusModel> list;

    GetFireStoreData getFireStoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_syllabus);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageSyllabus.this,AddSyallabus.class));
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getFireStoreData = new GetFireStoreData();
        getFireStoreData.getSyllabusData(this,recyclerView);



        if (common.user_type.equals("Student"))
            fab.setVisibility(View.GONE);


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


}
