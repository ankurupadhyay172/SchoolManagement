package com.androidcafe.newschoolmanagement.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Adapters.AddAttendanceAdapter;
import com.androidcafe.newschoolmanagement.Common.ReadDataFromFireStore;
import com.androidcafe.newschoolmanagement.Model.NewSchoolStudentModel;
import com.androidcafe.newschoolmanagement.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class AddAttendanceActivity extends AppCompatActivity implements ReadDataFromFireStore.OnGetStudentData, AddAttendanceAdapter.TakeAttandance {


    Toolbar toolbar;
    TextView p, a, l, total;
    HandleTeacherFireStore handleTeacherFireStore;
    AddAttendanceAdapter adapter;
    List<NewSchoolStudentModel> list= new ArrayList<>();
    RecyclerView recyclerView;

    FirebaseFirestore db;
    ReadDataFromFireStore readDataFromFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);

        handleTeacherFireStore = new HandleTeacherFireStore();
        db = FirebaseFirestore.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        p = (TextView) findViewById(R.id.p);
        a = (TextView) findViewById(R.id.a);
        l = (TextView) findViewById(R.id.l);
        total = (TextView) findViewById(R.id.totalstudent);

        p.setText("P = 0");
        a.setText("A = 0");
        l.setText("L = 0");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Attendance");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        handleTeacherFireStore.getTotalStudents(total);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        readDataFromFireStore = new ReadDataFromFireStore(this);
        readDataFromFireStore.getStudentDataForAttendance(list, AddAttendanceActivity.this, p, a, l);


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }


    @Override
    public void getSudentData(List<NewSchoolStudentModel> list) {

        adapter = new AddAttendanceAdapter(this, list, this);
        recyclerView.setAdapter(adapter);

    }



    @Override
    public void getAttendance(int position, String selected) {



        readDataFromFireStore.setAttendance(selected, list.get(position).getId(), list.get(position).getStudent_name());
        

    }


}





