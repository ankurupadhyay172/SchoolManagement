package com.androidcafe.newschoolmanagement.OnlineQuiz;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MultiQuestionAdapter extends FragmentPagerAdapter {

    List<QuestionFragment> fragments = new ArrayList<>();



    public void addFragment(QuestionFragment fragment)
    {
        fragments.add(fragment);
    }

    public MultiQuestionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return new StringBuilder("Question ").append(position+1).toString();
    }
}


