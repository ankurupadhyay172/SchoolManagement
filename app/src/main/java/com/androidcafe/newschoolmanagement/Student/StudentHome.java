package com.androidcafe.newschoolmanagement.Student;

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
import com.androidcafe.newschoolmanagement.Fees.AllTypeFeesActivity;
import com.androidcafe.newschoolmanagement.Model.Banner;
import com.androidcafe.newschoolmanagement.Notice.NoticeActivity;
import com.androidcafe.newschoolmanagement.OnlineQuiz.MultiQuestionActivity;
import com.androidcafe.newschoolmanagement.Online_Class.ShowAllSubjectActivity;
import com.androidcafe.newschoolmanagement.R;
import com.androidcafe.newschoolmanagement.SplashScreen;
import com.androidcafe.newschoolmanagement.Teacher.ManageHomeWork;
import com.androidcafe.newschoolmanagement.Teacher.ManageNotes;
import com.androidcafe.newschoolmanagement.Teacher.ManageSyllabus;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class StudentHome extends AppCompatActivity implements Slider.BannerInterface, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    SliderView sliderView;
    Slider slider;

    CardView notice,notes,syllabus,homework,card_test,online_class,attendance,time_table,card_fees;
    Toolbar toolbar;

    DrawerLayout drawerLayout;

    SharedPreferences sharedpreferences;

    NavigationView navigationView;
    ImageView profile_pic;
    TextView full_name,txtcoursr;

    GetFireStoreData getFireStoreData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        sharedpreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        getFireStoreData = new GetFireStoreData();

        ImageView nav_edit = (ImageView)view.findViewById(R.id.edit_profile);
        profile_pic = (ImageView)view.findViewById(R.id.profile_pic);

        full_name =(TextView)view.findViewById(R.id.txtFullName);
        txtcoursr = (TextView)view.findViewById(R.id.txtcourse);
        time_table = (CardView)findViewById(R.id.timetable);
        card_fees = (CardView)findViewById(R.id.cardfees);



        getFireStoreData.getStudentData(sharedpreferences.getString("id","na"),profile_pic,full_name,txtcoursr,StudentHome.this);






        sliderView = (SliderView)findViewById(R.id.imageSlider2);
        notice = (CardView)findViewById(R.id.notice);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout1);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        notes = (CardView)findViewById(R.id.tbnotes);
        syllabus = (CardView)findViewById(R.id.tbsyllabus);
        homework = (CardView)findViewById(R.id.homework);
        card_test = (CardView)findViewById(R.id.card_test);
        online_class = (CardView)findViewById(R.id.online_class);
        attendance = (CardView)findViewById(R.id.add_student_attentance);
        setSupportActionBar(toolbar);

        common.user_type = "Student";







        common.student_name = sharedpreferences.getString("name","na");


        common.user_id = sharedpreferences.getString("id","na");

        getSupportActionBar().setTitle(common.student_name);





        slider = new Slider(StudentHome.this,this);
        slider.initFirestore();

        notes.setOnClickListener(this);
        syllabus.setOnClickListener(this);
        homework.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        card_test.setOnClickListener(this);
        online_class.setOnClickListener(this);
        attendance.setOnClickListener(this);
        time_table.setOnClickListener(this);
        card_fees.setOnClickListener(this);


        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentHome.this, NoticeActivity.class));
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.syncState();








    }

    @Override
    public void getBanner(List<Banner> banners) {

        slider.setBanner(sliderView,banners);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();



        switch (id)
        {
            case R.id.tbnotes:
                startActivity(new Intent(this, ManageNotes.class));
                break;

            case R.id.tbsyllabus:
                startActivity(new Intent(this, ManageSyllabus.class));
                break;
            case R.id.homework:
                startActivity(new Intent(this, ManageHomeWork.class));
                break;

            case R.id.card_test:
                startActivity(new Intent(this, MultiQuestionActivity.class));
                break;


            case R.id.online_class:
                startActivity(new Intent(this, ShowAllSubjectActivity.class));
                break;

            case R.id.add_student_attentance:
                startActivity(new Intent(this,ViewAttendanceActivity.class));
                break;

            case R.id.timetable:
                startActivity(new Intent(this,ViewTimeTableActivity.class));
                break;

            case R.id.cardfees:
                startActivity(new Intent(this, AllTypeFeesActivity.class));
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
            Intent intent = new Intent(StudentHome.this, SplashScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Logout Successfull", Toast.LENGTH_SHORT).show();


            // Handle the camera action
        }

        return true;
    }
}
