package com.androidcafe.newschoolmanagement.Notice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.androidcafe.newschoolmanagement.Common.GetFireStoreData;
import com.androidcafe.newschoolmanagement.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class NoticeActivity extends AppCompatActivity  {

    FirebaseFirestore db;
    RecyclerView recyclerView;

    Toolbar toolbar;
   GetFireStoreData getFireStoreData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);


        db = FirebaseFirestore.getInstance();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getFireStoreData = new GetFireStoreData();
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getFireStoreData.getDataFronFireStore(this,recyclerView);



        getSupportActionBar().setTitle("Notice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


}
