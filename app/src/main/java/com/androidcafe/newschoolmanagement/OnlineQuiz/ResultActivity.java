package com.androidcafe.newschoolmanagement.OnlineQuiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.Question;
import com.androidcafe.newschoolmanagement.R;
import com.androidcafe.newschoolmanagement.SplashScreen;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    List<Question> questions ;

    RecyclerView recyclerView;
    TextView txt_right_answer;

    int selected_count=0;
    int right_ans=0;
    int wrong_ans=0;
    int no_answer=0;
    Button btn_total,btn_right,btn_wrong,btn_no_answer;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);





        //new MaterialStyleDialoge

        questions = common.getQuestionlist();



        txt_right_answer = (TextView)findViewById(R.id.txt_right_answer);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        btn_total = (Button)findViewById(R.id.btn_filter_total);
        btn_right = (Button)findViewById(R.id.btn_filter_right_answer);
        btn_wrong = (Button)findViewById(R.id.btn_filter_wrong_answer);





        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Result");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_result);

        AnswerAdapter adapter = new AnswerAdapter(this,questions);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);



        for (int i=0;i<questions.size();i++)
        {










            try {


                if (questions.get(i).getSelected().equals(null))
                {
                    no_answer = no_answer+1;
                }
                else
                {
                    selected_count = selected_count+1;
                }


                if (questions.get(i).getSelected().equals(questions.get(i).getAns()))
                {
                    //  Toast.makeText(this, "Q"+(i)+" Correct", Toast.LENGTH_SHORT).show();
                    right_ans = right_ans+1;



                }
                else
                {
                    wrong_ans = wrong_ans+1;
                }

            }catch (NullPointerException e)
            {

                no_answer = no_answer+1;
            }




        }




        try {

            txt_right_answer.setText(""+questions.size()+"/"+selected_count);
            btn_right.setText(""+right_ans);

            btn_no_answer.setText(""+no_answer);
            btn_wrong.setText(""+wrong_ans);

            btn_total.setText(""+questions.size());

        }catch (NullPointerException e)
        {

        }




    }


    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, SplashScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        return super.onSupportNavigateUp();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, SplashScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
