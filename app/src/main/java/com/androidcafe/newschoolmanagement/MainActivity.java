package com.androidcafe.newschoolmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Ragistration.AdminLogin;
import com.androidcafe.newschoolmanagement.Ragistration.StudentLogin;
import com.androidcafe.newschoolmanagement.Ragistration.TeacherLogin;

public class MainActivity extends AppCompatActivity {

    LinearLayout login_student,faculy;
    TextView admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        login_student = (LinearLayout)findViewById(R.id.loginstudent);
        faculy = (LinearLayout)findViewById(R.id.faculty);
        admin = (TextView) findViewById(R.id.admin);





        handleClick();

    }


    private void handleClick() {



        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminLogin.class));
            }
        });




        faculy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TeacherLogin.class));
            }
        });



        login_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudentLogin.class));
            }
        });
    }



}
