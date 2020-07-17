package com.androidcafe.newschoolmanagement.Adapters;

import com.androidcafe.newschoolmanagement.Fragment.All;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TablayoutAdapter extends FragmentStatePagerAdapter {






    int mnooftabs;


    public TablayoutAdapter(FragmentManager fm, int mnooftabs) {
        super(fm);
        this.mnooftabs = mnooftabs;
    }

    public TablayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


                All tab1 = new All();
                return tab1;






    }

    @Override
    public int getCount() {
        return 6;
    }
}

