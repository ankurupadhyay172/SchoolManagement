package com.androidcafe.newschoolmanagement.Fees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.androidcafe.newschoolmanagement.Adapters.ViewPagerAdapter;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Fragment.SchoolFees;
import com.androidcafe.newschoolmanagement.R;
import com.google.android.material.tabs.TabLayout;

public class AllTypeFeesActivity extends AppCompatActivity {


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_type_fees);



        toolbar = (Toolbar)findViewById(R.id.mytoolbar);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (ViewPager)findViewById(R.id.myviewpager);
        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);


        getSupportActionBar().setTitle(common.student_name+" "+common.student_class+" "+common.student_section);


    }



    public void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SchoolFees(),"Term Fees");


        viewPager.setAdapter(adapter);


    }

}
