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

public class TeacherLogin extends AppCompatActivity {

    EditText userid,pass,code;
    Button login;

    GetFireStoreData getFireStoreData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_login);


        userid = (EditText)findViewById(R.id.userid);
        pass = (EditText)findViewById(R.id.pass);
        code = (EditText)findViewById(R.id.institute_code);
        TextView usertype = (TextView) findViewById(R.id.usertype);
        login = (Button)findViewById(R.id.login);
        usertype.setText("Teacher");


        getFireStoreData = new GetFireStoreData();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (common.isEmpty(userid)||common.isEmpty(pass)||common.isEmpty(code))
                {

                    if (common.isEmpty(userid))
                    {
                        userid.setError("Enter Mobile Number");
                    }

                    if (common.isEmpty(pass))
                    {
                        pass.setError("Enter password");
                    }
                    if (common.isEmpty(code))
                    {
                        code.setError("Enter valid Institute code");
                    }



                }
                else
                {
                    getFireStoreData.getTeacherLogin(userid,pass,code,TeacherLogin.this);
                }



            }
        });
    }
}
