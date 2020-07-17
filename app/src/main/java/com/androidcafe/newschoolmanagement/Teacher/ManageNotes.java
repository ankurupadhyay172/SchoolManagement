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
import com.androidcafe.newschoolmanagement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ManageNotes extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_syllabus);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GetFireStoreData getFireStoreData = new GetFireStoreData();
        getFireStoreData.getNotesData(this,recyclerView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (common.user_type.equals("Student"))
        {
            fab.setVisibility(View.GONE);

        }

        fab.setOnClickListener(this);

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {


        startActivity(new Intent(this,AddNotes.class));

    }
}
