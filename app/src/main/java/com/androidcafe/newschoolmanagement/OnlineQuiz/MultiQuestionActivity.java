package com.androidcafe.newschoolmanagement.OnlineQuiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.Question;
import com.androidcafe.newschoolmanagement.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MultiQuestionActivity extends AppCompatActivity implements QuestionFragment.OnselectItem{


    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Question> questions;

    List<String> answer;

    RequestQueue rq;

    String UPLOAD_URL ="https://script.google.com/macros/s/AKfycbybBJ-t1ZJak8JycLtz40H4GHnB8PQ4e3Wo6u7wjdk49LGa2Lzl/exec";
    Dialog test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_question);



        questions = new ArrayList<>();
        answer = new ArrayList<>();


        //init volly request
        rq = Volley.newRequestQueue(this);


        showLoginDialog();

        getDatafromurl();





        toolbar = (Toolbar)findViewById(R.id.mytoolbar);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (ViewPager)findViewById(R.id.myviewpager);
        setSupportActionBar(toolbar);







    }

    public void setupViewPager(ViewPager viewPager)
    {
        MultiQuestionAdapter adapter = new MultiQuestionAdapter(getSupportFragmentManager());


        for (int i=0;i<questions.size();i++)
        {



            QuestionFragment q = QuestionFragment.newInstance(questions.get(i),i);

            adapter.addFragment(q);

            common.questionlist.add(questions.get(i));


        }






        viewPager.setAdapter(adapter);


    }


    @Override
    public void totalSelected(int i) {
        getSupportActionBar().setTitle("Questions "+questions.size()+"/"+i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.question_menu,menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.check)
        {
            new MaterialStyledDialog.Builder(this).setTitle("Finish ? ").setIcon(R.drawable.ic_smile)
                    .setDescription("Do you really want to finish ?")
                    .setNegativeText("No")
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick( MaterialDialog dialog, DialogAction which) {
                            dialog.dismiss();;
                        }
                    }).setPositiveText("Yes")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {

                            common.setQuestionlist(questions);



                            dialog.dismiss();
                            startActivity(new Intent(MultiQuestionActivity.this,ResultActivity.class));
                            finish();
                        }
                    }).show();






        }


        return true;
    }








    private void getDatafromurl(){
        //Showing the progress dialog
        // final ProgressDialog loading = ProgressDialog.show(Student_entry.this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        test.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(s);

                            JSONArray array = jsonObject.getJSONArray("records");




                            for (int i=0;i<array.length();i++)
                            {
                                JSONObject object = array.getJSONObject(i);
                                String question = object.get("QUESTION").toString();
                                String opt1 = object.get("OPTION1").toString();
                                String opt2 = object.get("OPTION2").toString();
                                String opt3 = object.get("OPTION3").toString();
                                String opt4 = object.get("OPTION4").toString();

                                String ans = object.get("ANSWER").toString();
                                String image = object.get("IMAGE").toString();

                                if (image.equals(""))
                                    questions.add(new Question(question,opt1,opt2,opt3,opt4,ans));
                                else
                                    questions.add(new Question(question,opt1,opt2,opt3,opt4,ans,image));

                            }
                            //Toast.makeText(MultiQuestionActivity.this, ""+value), Toast.LENGTH_SHORT).show();

                            setupViewPager(viewPager);
                            tabLayout.setupWithViewPager(viewPager);


                            getSupportActionBar().setTitle("Questions "+questions.size()+"/"+0);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {




                        //Showing toast

                        try {

                            Toast.makeText(MultiQuestionActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();

                        }catch (NullPointerException e)
                        {

                        }

                       }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String


                //Getting Image Name
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters

                params.put("action","read");
                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(MultiQuestionActivity.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }













    //---------------------------------------------------------show animation


    private void showLoginDialog() {



        test = new Dialog(this);
        test.setTitle("test ");
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        View view = LayoutInflater.from(this).inflate(R.layout.loading_layout,linearLayout);
        test.setContentView(view);
        test.show();



    }








}
