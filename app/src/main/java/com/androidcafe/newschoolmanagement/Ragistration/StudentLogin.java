package com.androidcafe.newschoolmanagement.Ragistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Common.GetFireStoreData;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.R;

public class StudentLogin extends AppCompatActivity {

    Button login;

    EditText userid,pass,code;
    GetFireStoreData getFireStoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_login);

        login = (Button)findViewById(R.id.login);
        userid = (EditText)findViewById(R.id.userid);
        pass = (EditText)findViewById(R.id.pass);
        code = (EditText)findViewById(R.id.institute_code);

        getFireStoreData = new GetFireStoreData();

        final TextView usertype = (TextView) findViewById(R.id.usertype);
        usertype.setText("Student");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (common.isEmpty(userid)||common.isEmpty(pass)||common.isEmpty(code))
                {
                    if (common.isEmpty(userid))
                    {
                        userid.setError("Enter userid");
                    }
                    if (common.isEmpty(pass))
                    {
                        pass.setError("Enter password");
                    }
                }
                else
                {
                    getFireStoreData.getStudentLogin(userid,pass,code,StudentLogin.this);

                }



            }
        });








    }
}
