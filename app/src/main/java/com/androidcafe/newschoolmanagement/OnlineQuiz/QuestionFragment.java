package com.androidcafe.newschoolmanagement.OnlineQuiz;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.Question;
import com.androidcafe.newschoolmanagement.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {




    TextView txt_question;

    RadioGroup radioGroup;

    RadioButton rb1,rb2,rb3,rb4;
    OnselectItem listener;

    FrameLayout imglayout;
    ImageView imageView;
    ProgressBar progressBar;



    public static QuestionFragment newInstance(Question question , int position) {

        Bundle args = new Bundle();
        args.putParcelable("key",question);
        args.putInt("pos",position);
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }



    public QuestionFragment() {
        // Required empty public constructor
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnselectItem)
            listener = (OnselectItem) context;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_question, container, false);

        txt_question = (TextView) view.findViewById(R.id.txt_question_text);
        Bundle bundle = getArguments();
        final Question question = bundle.getParcelable("key");

        final int  position = bundle.getInt("pos");

        rb1 = (RadioButton)view.findViewById(R.id.rb1);
        rb2 = (RadioButton)view.findViewById(R.id.rb2);
        rb3 = (RadioButton)view.findViewById(R.id.rb3);
        rb4 = (RadioButton)view.findViewById(R.id.rb4);
        imglayout = (FrameLayout)view.findViewById(R.id.layout_image);
        imageView = (ImageView)view.findViewById(R.id.img_question);
        progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        radioGroup = (RadioGroup)view.findViewById(R.id.radiogroup);

        try {

            if (!question.getImg().equals(null))
            {
                imglayout.setVisibility(View.VISIBLE);


                Picasso.get().load(question.getImg()).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Image not available", Toast.LENGTH_SHORT).show();
                    }
                });


            }
            else
            {


            }



        }catch (NullPointerException e)
        {

        }






        rb1.setText(question.getOpt1());
        rb2.setText(question.getOpt2());
        rb3.setText(question.getOpt3());
        rb4.setText(question.getOpt4());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton)group.findViewById(checkedId);
                question.setSelected(rb.getText().toString());

                if (!common.position.contains(position))
                {
                    common.position.add(position);
                    listener.totalSelected(common.position.size());
                }


            }
        });





        try {

            //if (question)



        }catch (NullPointerException e)
        {

        }





        txt_question.setText(question.getQuestion());


        return view;


    }


    public interface OnselectItem
    {
        public void totalSelected(int i);
    }
}
