package com.androidcafe.newschoolmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.androidcafe.newschoolmanagement.Admin.AdminHome;
import com.androidcafe.newschoolmanagement.Student.StudentHome;
import com.androidcafe.newschoolmanagement.Teacher.TeacherHome;

public class SplashScreen extends AppCompatActivity {


    int seconds =2000;
    final Handler handler = new Handler();
    SharedPreferences sharedpreferences;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedpreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);


        if (getIntent()!=null&&getIntent().hasExtra("key1"))
        {
            for (String key:getIntent().getExtras().keySet())
            {
                Log.d("apnatime",""+key+"data"+getIntent().getExtras().getString(key));
            }
        }

        timer();
    }


    private void timer() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (seconds > 0) {


                    seconds -= 1000;


                    handler.postDelayed(this, 1000);
                } else {


                    nextactivity();


                }
            }
        });

    }









    public void nextactivity()
    {




        if (sharedpreferences.getString("user_type","na").equals("Teacher")||sharedpreferences.getString("user_type","na").equals("Student")||sharedpreferences.getString("user_type","na").equals("Admin"))
        {

            if (sharedpreferences.getString("user_type","na").equals("Teacher"))
            {
                intent = new Intent(SplashScreen.this, TeacherHome.class);

            }

            if (sharedpreferences.getString("user_type","na").equals("Student"))
            {
                intent = new Intent(SplashScreen.this, StudentHome.class);

            }

            if (sharedpreferences.getString("user_type","na").equals("Admin"))
            {
                intent = new Intent(SplashScreen.this, AdminHome.class);

            }


        }
        else
        {
            intent = new Intent(SplashScreen.this, MainActivity.class);

        }





        handler.removeCallbacksAndMessages(null);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

    }
}
