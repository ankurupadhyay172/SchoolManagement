package com.androidcafe.newschoolmanagement.Online_Class;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.R;

public class ShowAllSubjectActivity extends AppCompatActivity implements ChatRoomAdapter.onvideolecrure{


    RecyclerView recyclerView;
    ChatRoomAdapter adapter;

   Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_subject);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Online Class");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        adapter = new ChatRoomAdapter(this, common.subjectlist(),this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);










    }

    @Override
    public void clickonvideo(int i) {
        startActivity(new Intent(this, PlayVideoDemo.class));

    }




    @Override
    public boolean onSupportNavigateUp() {
        finish();
        Toast.makeText(this, "this is work", Toast.LENGTH_SHORT).show();
        return super.onSupportNavigateUp();
    }
}
