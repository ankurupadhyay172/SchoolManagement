package com.androidcafe.newschoolmanagement.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Common.GetFireStoreData;
import com.androidcafe.newschoolmanagement.Common.Slider;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.Banner;
import com.androidcafe.newschoolmanagement.Notice.NoticeActivity;
import com.androidcafe.newschoolmanagement.R;
import com.androidcafe.newschoolmanagement.SplashScreen;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class TeacherHome extends AppCompatActivity implements Slider.BannerInterface, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    SliderView sliderView;
    Slider slider;

    CardView add_attendance,syllabus,notes,notice,homework;

    NavigationView navigationView;

    ImageView profile_pic;
    TextView full_name,txtcoursr;
    SharedPreferences sharedpreferences;

    GetFireStoreData getFireStoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);
        sharedpreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);

        getFireStoreData = new GetFireStoreData();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout1);
        sliderView = (SliderView)findViewById(R.id.imageSlider2);
        add_attendance = (CardView)findViewById(R.id.add_student_attentance);
        syllabus = (CardView)findViewById(R.id.tbsyllabus);
        notes = (CardView)findViewById(R.id.tbaddnotes);
        notice = (CardView)findViewById(R.id.notice);
        homework = (CardView)findViewById(R.id.homework);
        common.user_type = "Teacher";




        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);


        ImageView nav_edit = (ImageView)view.findViewById(R.id.edit_profile);
        profile_pic = (ImageView)view.findViewById(R.id.profile_pic);

        full_name =(TextView)view.findViewById(R.id.txtFullName);
        txtcoursr = (TextView)view.findViewById(R.id.txtcourse);





        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.syncState();


        slider = new Slider(TeacherHome.this,this);
        slider.initFirestore();


        add_attendance.setOnClickListener(this);
        syllabus.setOnClickListener(this);
        notes.setOnClickListener(this);
        notice.setOnClickListener(this);
        homework.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);






        getFireStoreData.getTeacherData(sharedpreferences.getString("id","na"),TeacherHome.this,profile_pic,full_name,txtcoursr,getSupportActionBar());


    }


    @Override
    public void getBanner(List<Banner> banners) {
        slider.setBanner(sliderView,banners);
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();


        switch (id)
        {

            case R.id.add_student_attentance:
                startActivity(new Intent(this,AddAttendanceActivity.class));
                break;


            case R.id.tbsyllabus:
                startActivity(new Intent(this,ManageSyllabus.class));
                break;

            case R.id.tbaddnotes :
                startActivity(new Intent(this,ManageNotes.class));
                break;

            case R.id.notice:
                startActivity(new Intent(this, NoticeActivity.class));
                break;

            case R.id.homework:
                startActivity(new Intent(this,ManageHomeWork.class));
                break;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        SharedPreferences.Editor editor = sharedpreferences.edit();
        if (id == R.id.logout) {

            editor.clear();
            editor.commit();
            Intent intent = new Intent(TeacherHome.this, SplashScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Logout Successfull", Toast.LENGTH_SHORT).show();


            // Handle the camera action
        }


        return true;
    }
}
