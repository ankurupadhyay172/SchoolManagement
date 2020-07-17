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

public class AdminLogin extends AppCompatActivity {


    EditText userid,pass,code;

    GetFireStoreData getFireStoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        userid = (EditText)findViewById(R.id.userid);
        pass = (EditText)findViewById(R.id.pass);
        code = (EditText)findViewById(R.id.institute_code);

        TextView usertype = (TextView) findViewById(R.id.usertype);
        usertype.setText("Admin");


        Button button = (Button)findViewById(R.id.login);
        getFireStoreData = new GetFireStoreData();



        button.setOnClickListener(new View.OnClickListener() {
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
                    if (common.isEmpty(code))
                    {
                        code.setError("Enter Institute code");
                    }

                }
                else
                {
                    //startActivity(new Intent(AdminLogin.this, AdminHome.class));
                    getFireStoreData.getLogin(userid,pass,code,AdminLogin.this);
                }


            }
        });
    }
}
