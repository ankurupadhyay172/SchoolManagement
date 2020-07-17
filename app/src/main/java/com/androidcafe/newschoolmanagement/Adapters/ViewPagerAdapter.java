package com.androidcafe.newschoolmanagement.Adapters;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter  extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titlelist = new ArrayList<>();

    public void addFragment(Fragment fragment,String title)
    {
        fragmentList.add(fragment);
        titlelist.add(title);
    }



    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return titlelist.get(position);
    }
}

