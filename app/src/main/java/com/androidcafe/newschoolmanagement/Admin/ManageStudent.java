package com.androidcafe.newschoolmanagement.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.androidcafe.newschoolmanagement.Adapters.NewManageAdapter;
import com.androidcafe.newschoolmanagement.Common.ReadDataFromFireStore;
import com.androidcafe.newschoolmanagement.Model.NewSchoolStudentModel;
import com.androidcafe.newschoolmanagement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ManageStudent extends AppCompatActivity implements ReadDataFromFireStore.OnGetStudentData{


    FloatingActionButton fab;
    Toolbar toolbar;

    private RecyclerView recyclerView;

    private NewManageAdapter listViewAdapter;

    FirebaseFirestore db;
    List<NewSchoolStudentModel> list=new ArrayList<>();
    ReadDataFromFireStore readDataFromFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);


        db = FirebaseFirestore.getInstance();
        fab = (FloatingActionButton)findViewById(R.id.fab);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Manage Student");



        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageStudent.this,AddStudent.class));
            }
        });



       // getAllData();




        //get data from excel


        GetDataFromExcel excel = new GetDataFromExcel(this);
        excel.initVolly();
        excel.getDatafromurl();


        readDataFromFireStore = new ReadDataFromFireStore(this);
        readDataFromFireStore.getStudentData(list,ManageStudent.this);

    }






    private void updateRecycler(List<NewSchoolStudentModel> list) {



        listViewAdapter = new NewManageAdapter(ManageStudent.this,list);
        recyclerView.setAdapter(listViewAdapter);




    }






    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;

    }










    @Override
    public void getSudentData(List<NewSchoolStudentModel> list) {
        updateRecycler(list);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView)searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                listViewAdapter.getFilter().filter(s);

                return false;
            }
        });

        return true;
    }
}





