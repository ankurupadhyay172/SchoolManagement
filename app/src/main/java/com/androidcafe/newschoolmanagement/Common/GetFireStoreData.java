package com.androidcafe.newschoolmanagement.Common;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Adapters.HomeWorkAdapter;
import com.androidcafe.newschoolmanagement.Adapters.NotesAdapter;
import com.androidcafe.newschoolmanagement.Adapters.SyllabusAdapter;
import com.androidcafe.newschoolmanagement.Admin.AdminHome;
import com.androidcafe.newschoolmanagement.Model.AdminModel;
import com.androidcafe.newschoolmanagement.Model.HomeWork_Model;
import com.androidcafe.newschoolmanagement.Model.NewSchoolStudentModel;
import com.androidcafe.newschoolmanagement.Model.NotesModel;
import com.androidcafe.newschoolmanagement.Model.StudentLoginModel;
import com.androidcafe.newschoolmanagement.Model.SyllabusModel;
import com.androidcafe.newschoolmanagement.Model.TeacherLoginModel;
import com.androidcafe.newschoolmanagement.Notice.NewsModel;
import com.androidcafe.newschoolmanagement.Notice.NewsViewAdapter;
import com.androidcafe.newschoolmanagement.Student.StudentHome;
import com.androidcafe.newschoolmanagement.Teacher.TeacherHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;

public class GetFireStoreData {


    FirebaseFirestore db;

    SharedPreferences sharedpreferences;


    public void getDataFronFireStore(final Context context, final RecyclerView recyclerView) {

        db = FirebaseFirestore.getInstance();


        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);




        final List<NewsModel> list = new ArrayList<>();
        db.collection(common.School_Code).document(common.Branch_code).collection(common.News_db).orderBy("TimeStamp", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Map<String,Object> todo = new HashMap<>();
                            todo.put("isseen",true);

                            test.dismiss();
                           
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NewsModel model = document.toObject(NewsModel.class);
                                list.add(model);

                                if (model.getCategory().equals("All User") || model.getCategory().equals(common.user_type))
                                {

                                    db.collection(common.School_Code).document(common.School_Code).collection(common.News_db).document(document.getId()).update(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful())
                                            {
                                                Log.d("isseenankur","sucessfully");
                                            }


                                        }
                                    });
                                    

                                }


                            }

                            setRecyclerView(context,recyclerView,list);

                        }
                        else 
                        {
                            test.dismiss();
                            Toast.makeText(context, "No Record Found", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }

    public void setRecyclerView(Context context,RecyclerView recyclerView, List<NewsModel> list) {
        NewsViewAdapter adapter = new NewsViewAdapter(context, list);
        recyclerView.setAdapter(adapter);
    }




























    public void getSyllabusData(final Context context, final RecyclerView recyclerView)
    {
        db = FirebaseFirestore.getInstance();
        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);



        final List<SyllabusModel> list = new ArrayList<>();
        db.collection(common.School_Code).document(common.Branch_code).collection(common.syllabus_db).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {
                    test.dismiss();
                    if (!task.getResult().isEmpty())
                    {

                        for (QueryDocumentSnapshot document:task.getResult())
                        {


                            SyllabusModel model = document.toObject(SyllabusModel.class);
                            list.add(model);

                            updateRecycle(context,recyclerView,list);


                        }

                    }
                    else
                    {
                        Toast.makeText(context, "No record found", Toast.LENGTH_SHORT).show();
                        test.dismiss();
                    }


                }else
                    test.dismiss();



            }
        });
    }

    private void updateRecycle(Context context, RecyclerView recyclerView, List<SyllabusModel> list)
    {
        SyllabusAdapter adapter = new SyllabusAdapter(context,list);
        recyclerView.setAdapter(adapter);





    }





    public void getNotesData(final Context context, final RecyclerView recyclerView)
    {
        db = FirebaseFirestore.getInstance();
        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);

        final List<NotesModel> list = new ArrayList<>();
        db.collection(common.School_Code).document(common.Branch_code).collection(common.Notes_db).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if (task.isSuccessful())
                {


                    if (!task.getResult().isEmpty())
                    {
                        test.dismiss();
                        for (QueryDocumentSnapshot document:task.getResult())
                        {

                            NotesModel model = document.toObject(NotesModel.class);
                            list.add(model);
                            updateRecycleNotes(context,recyclerView,list);



                        }


                    }
                    else
                    {
                        Toast.makeText(context, "No Record found", Toast.LENGTH_SHORT).show();
                        test.dismiss();
                    }



                }
                else
                {
                    Toast.makeText(context, "No record found", Toast.LENGTH_SHORT).show();
                    test.dismiss();
                }



            }
        });


    }

    private void updateRecycleNotes(Context context, RecyclerView recyclerView, List<NotesModel> list)
    {

        NotesAdapter adapter = new NotesAdapter(context,list);
        recyclerView.setAdapter(adapter);



    }







    public void getHomeWorkData(final Context context, final RecyclerView recyclerView)
    {


        db = FirebaseFirestore.getInstance();

        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);

        final List<HomeWork_Model> list = new ArrayList<>();
        db.collection(common.School_Code).document(common.Branch_code).collection(common.Homework_db).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                if (task.isSuccessful())
                {
                    test.dismiss();
                    if (!task.getResult().isEmpty())
                    {

                        for (QueryDocumentSnapshot document:task.getResult())
                        {

                            HomeWork_Model model = document.toObject(HomeWork_Model.class);
                            list.add(model);
                            updateRecycleHomeWork(context,recyclerView,list);



                        }


                    }
                    else
                        Toast.makeText(context, "No recoed found", Toast.LENGTH_SHORT).show();



                }
                else
                {
                    test.dismiss();
                }


            }
        });


    }

    private void updateRecycleHomeWork(Context context, RecyclerView recyclerView, List<HomeWork_Model> list) {

        HomeWorkAdapter adapter = new HomeWorkAdapter(context,list);
        recyclerView.setAdapter(adapter);

    }









    //------------------------------------------------------Login

    public void getLogin(final EditText userid, final EditText password, final EditText code, final Context context)
    {
        db = FirebaseFirestore.getInstance();
        final String uid = userid.getText().toString();
        final String pass = password.getText().toString();
        String id = code.getText().toString();


        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);


        db.collection(id).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful())
                {

                    test.dismiss();
                    if (task.getResult().exists())
                    {
                        DocumentSnapshot document = task.getResult();
                        AdminModel model = document.toObject(AdminModel.class);


                        if (!model.getInstitute_password().equals(pass)||!model.getInstitute_userid().equals(uid))
                        {
                            if (!model.getInstitute_password().equals(pass))
                                password.setError("Password not match");

                            if (!model.getInstitute_userid().equals(uid))
                                userid.setError("UserId not match");
                        }
                        else
                        {
                            LoginAdmin(document.getId(),model.getInstitute_type(),model.getInstitute_code(),model.getInstitute_name(),model.getInistitute_description(),model.getInstitute_address(),uid,pass,context);
                        }

                    }


                }
                else
                {
                    test.dismiss();
                    code.setError("Enter valid institute code");
                }


            }
        });




    }

    private void LoginAdmin(String id, String institute_type, String institute_code, String institute_name, String inistitute_description, String institute_address, String uid, String pass,Context context)
    {
        sharedpreferences = context.getSharedPreferences("logindata", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("id",id);

        editor.putString("school_code",institute_code);
        editor.putString("school_name",institute_name);
        editor.putString("school_address",institute_address);
        editor.putString("school_userid",uid);
        editor.putString("school_password",pass);
        editor.putString("school_type",institute_type);
        editor.putString("school_discription",inistitute_description);
        editor.putString("user_type","Admin");

        editor.commit();


        Intent intent= new Intent(context, AdminHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);



    }


    //--------------------------------------------------------- student login



    public void getStudentLogin(final EditText userid, final EditText password, final EditText code, final Context context)
    {
        db = FirebaseFirestore.getInstance();
        final String uid = userid.getText().toString();
        final String pass = password.getText().toString();
        String id = code.getText().toString();


        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);


        db.collection(id).document(id).collection(common.new_student_db).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {
                    test.dismiss();
                    if (!task.getResult().isEmpty())
                    {


                        for (QueryDocumentSnapshot document:task.getResult())
                        {

                            StudentLoginModel model = document.toObject(StudentLoginModel.class);


                            if (!model.getSchool_code().equals(pass)||!model.getStudent_mobile().equals(uid))
                            {
                                if (!model.getSchool_code().equals(pass))
                                    password.setError("Password not match");

                                if (!model.getStudent_mobile().equals(uid))
                                    userid.setError("UserId not match");
                            }
                            else
                            {


                               LoginStudent(document.getId(),model.getStudent_name(),model.getStudent_class(),model.getStudent_section(),model.getStudent_roll_no(),model.getStudent_father_name(),model.getStudent_mobile(),model.getSchool_code(),"S_101",model.getSchool_type(),context);
                            }


                        }
                    }
                    else
                    {
                        code.setError("Enter valid institute code");
                    }



                }





            }
        });




    }

    private void LoginStudent(String id, String student_name, String student_class, String student_section, int student_roll_no, String student_father_name, String student_mobile, String school_code, String branch_code, String school_type,Context context)
    {
        sharedpreferences = context.getSharedPreferences("logindata", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("id",id);
        editor.putString("name",student_name);
        editor.putString("class",student_class);
        editor.putString("section",student_section);
        editor.putInt("rollno",student_roll_no);
        editor.putString("father",student_father_name);
        editor.putString("mobile",student_mobile);
        editor.putString("school_code",school_code);
        editor.putString("branch_code",branch_code);
        editor.putString("school_type",school_type);
        editor.putString("user_type","Student");



        editor.commit();



        Intent intent= new Intent(context, StudentHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);


    }


    public void getTeacherLogin(final EditText userid, final EditText password, final EditText code, final Context context)
    {
        db = FirebaseFirestore.getInstance();
        final String uid = userid.getText().toString();
        final String pass = password.getText().toString();
        String id = code.getText().toString();
        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);


        db.collection(id).document(id).collection(common.new_teacher_db).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {
                    test.dismiss();
                    if (!task.getResult().isEmpty())
                    {


                        for (QueryDocumentSnapshot document:task.getResult())
                        {

                            TeacherLoginModel model = document.toObject(TeacherLoginModel.class);


                            if (model.getTeacher_password().equals(pass)&&model.getTeacher_mobile().equals(uid))
                            {


                                Toast.makeText(context, "Successfull login", Toast.LENGTH_SHORT).show();


                                teacherLogin(document.getId(),model.getTeacher_name(),model.getTeacher_subject(),model.getTeacher_class(),model.getSchool_code(),model.getTeacher_section(),model.getSchool_code(),"S_101",context);
                            }
                            else
                            {
                                Snackbar snackbar = Snackbar.make(userid,"Userid and password not match",Snackbar.LENGTH_LONG);
                                snackbar.show();

                            }


                        }
                    }
                    else
                    {
                        test.dismiss();
                        code.setError("Enter valid institute code");
                    }



                }





            }
        });




    }

    private void teacherLogin(String id, String teacher_name, String teacher_subject,String teacher_class, String school_code, String teacher_section, String school_code1, String branch_code, Context context)
    {


        sharedpreferences = context.getSharedPreferences("logindata", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("id",id);
        editor.putString("name",teacher_name);
        editor.putString("subject",teacher_subject);
        editor.putString("class",teacher_class);
        editor.putString("section",teacher_section);
        editor.putString("school_code",school_code);
        editor.putString("branch_code",branch_code);

        editor.putString("user_type","Teacher");

        editor.commit();




        Intent intent= new Intent(context, TeacherHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

    }





    //---------------------------------------------------------getStudent Data




    public void getStudentData(String id, final ImageView profile_pic, final TextView name, final TextView course,Context context)
    {
        db = FirebaseFirestore.getInstance();


        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);



        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful())
                {

                    test.dismiss();
                    if (task.getResult().exists())
                    {


                        DocumentSnapshot document = task.getResult();
                        NewSchoolStudentModel model = document.toObject(NewSchoolStudentModel.class);



                       name.setText(model.getStudent_name());
                       course.setText(model.getStudent_class()+" "+model.getStudent_section());
                        common.student_class = model.getStudent_class();
                        common.student_section = model.getStudent_section();
                        common.mobile_no = model.getStudent_mobile();
                        common.profile_pic = model.getProfile_pic();
                        common.student_medium = model.getStudent_medium();
                        common.student_roll_no = String.valueOf(model.getStudent_roll_no());
                        common.student_fname = model.getStudent_father_name();

                        try {
                           if (!model.getProfile_pic().equals("na"))
                            Picasso.get().load(model.getProfile_pic()).into(profile_pic);

                        }catch (IllegalArgumentException e)
                        {

                        }





                    }







                }



            }
        });




    }






    //----------------------------------------------------------- getAdmin Data



    public void getAdminData(final TextView full_name, final TextView txtcoursr,Context context)
    {





        db = FirebaseFirestore.getInstance();
        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);


        db.collection(common.School_Code).document(common.Branch_code).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful())
                {
                    DocumentSnapshot document = task.getResult();
                    AdminModel model = document.toObject(AdminModel.class);
                    full_name.setText(model.getInstitute_name());
                    txtcoursr.setText(model.getInstitute_address());


                    test.dismiss();


                }




            }
        });





    }








    //-------------------------------------------------------- getTeacher Data

    public void getTeacherData(String id, final Context context, final ImageView profile_pic, final TextView full_name, final TextView txtcoursr, final ActionBar supportActionBar)
    {
        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);


        db = FirebaseFirestore.getInstance();
        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_teacher_db).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful())
                {
                    test.dismiss();

                    if (task.getResult().exists())
                    {
                        DocumentSnapshot document = task.getResult();
                        TeacherLoginModel model = document.toObject(TeacherLoginModel.class);


                        common.mobile_no = model.getTeacher_mobile();

                        common.teacher_name = model.getTeacher_name();


                        full_name.setText(model.getTeacher_name());
                        txtcoursr.setText(model.getTeacher_subject());

                        supportActionBar.setTitle(model.getTeacher_name());


                        try {

                            Picasso.get().load(model.getProfile_pic()).into(profile_pic);

                        }catch (IllegalArgumentException e)
                        {

                        }




                    }


                }



            }
        });
    }





    //------------------------------------------------------ Update Recipt











}
