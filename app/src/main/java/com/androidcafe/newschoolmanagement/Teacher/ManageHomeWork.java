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

public class ManageHomeWork extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_syllabus);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home Work");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GetFireStoreData getFireStoreData = new GetFireStoreData();
        getFireStoreData.getHomeWorkData(this,recyclerView);


        if (common.user_type.equals("Student"))
            fab.setVisibility(View.GONE);




        fab.setOnClickListener(this);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {


        startActivity(new Intent(this,AddHomeWork.class));






    }
}
