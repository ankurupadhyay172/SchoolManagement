package com.androidcafe.newschoolmanagement.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Adapters.PagerAdapter;
import com.androidcafe.newschoolmanagement.Common.GetFireStoreData;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.Banner;
import com.androidcafe.newschoolmanagement.R;
import com.androidcafe.newschoolmanagement.SplashScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class AdminHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    CollectionReference bannerRef;
    SliderView sliderView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    CardView manage_student;



    CardView add_teacher,sent_news;

    SharedPreferences sharedpreferences;

    NavigationView navigationView;
    ImageView profile_pic;
    TextView full_name,txtcoursr;

    GetFireStoreData getFireStoreData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        getFireStoreData = new GetFireStoreData();
        sharedpreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        ImageView nav_edit = (ImageView)view.findViewById(R.id.edit_profile);
        profile_pic = (ImageView)view.findViewById(R.id.profile_pic);

        full_name =(TextView)view.findViewById(R.id.txtFullName);
        txtcoursr = (TextView)view.findViewById(R.id.txtcourse);

        getFireStoreData.getAdminData(full_name,txtcoursr,AdminHome.this);



        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        bannerRef = FirebaseFirestore.getInstance().collection("Banner");
        sliderView = (SliderView)findViewById(R.id.imageSlider2);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        add_teacher =(CardView)findViewById(R.id.add_teacher);
        manage_student = (CardView)findViewById(R.id.manage_student);
        sent_news = (CardView)findViewById(R.id.sent_news);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.syncState();





        manage_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this,ManageStudent.class));
            }
        });


        sent_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this,SentNotice.class));
            }
        });


        add_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this,ManageTeacher.class));
            }
        });

        loadBanner();

        navigationView.setNavigationItemSelectedListener(this);


        common.school_name = sharedpreferences.getString("school_name","na");

        toolbar.setTitle(common.school_name);






    }




    private void loadBanner() {
        bannerRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {

                        List<Banner> banners = new ArrayList<>();
                        if (task.isSuccessful())
                        {

                            for (QueryDocumentSnapshot bannerSnapShot:task.getResult() )
                            {
                                Banner banner = bannerSnapShot.toObject(Banner.class);
                                banners.add(banner);
                            }
                            loadbanner(banners);
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {


            }
        });

    }


    private void loadbanner(List<Banner> banners) {


        PagerAdapter adapter = new PagerAdapter(this,banners);
        sliderView.setSliderAdapter(adapter);


        sliderView.setPadding(10,10,10,10);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();




    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        int id = item.getItemId();


        SharedPreferences.Editor editor = sharedpreferences.edit();
        if (id == R.id.logout) {

            editor.clear();
            editor.commit();
            Intent intent = new Intent(AdminHome.this, SplashScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Logout Successfull", Toast.LENGTH_SHORT).show();


            // Handle the camera action
        }


        return true;
    }
}

