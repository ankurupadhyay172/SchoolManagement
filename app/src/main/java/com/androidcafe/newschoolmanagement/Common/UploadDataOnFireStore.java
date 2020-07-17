package com.androidcafe.newschoolmanagement.Common;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Admin.AdminHome;
import com.androidcafe.newschoolmanagement.Teacher.TeacherHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import androidx.annotation.NonNull;

public class UploadDataOnFireStore {

    FirebaseFirestore db;
    Map<String,Object> todo =new HashMap<>();



    public void addSyllabus(String s_medium, String s_class, String s_subject, String s_description, String url, final Context context)
    {


        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);


        List<String> seenusers = new ArrayList<>();
        seenusers.add(common.mobile_no);
        db = FirebaseFirestore.getInstance();
        todo.put("selected_medium",s_medium);
        todo.put("selected_class",s_class);
        todo.put("selected_subject",s_subject);
        todo.put("pdf_url",url);
        todo.put("description",s_description);
        todo.put("timestamp", Timestamp.now());
        todo.put("isseen",false);
        todo.put("seenusers",seenusers);

        String id = UUID.randomUUID().toString();

        db.collection(common.School_Code).document(common.Branch_code).collection(common.syllabus_db).document(id).set(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {

                    test.dismiss();

                    if (task.isSuccessful())
                    {

                        afterSuccessfull(context);

                    }
                }



            }
        });






    }

    private void afterSuccessfull(Context context)
    {

        Toast.makeText(context, "Upload successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, TeacherHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);






    }







    //--------------------------------------------------------------- Add home work

    public void addHomeWork(String s_medium, String s_class, String s_subject, String s_homework, String section, final Context context)
    {

        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);



        db = FirebaseFirestore.getInstance();
        Map<String,Object> todo = new HashMap<>();
        String id = UUID.randomUUID().toString();
        todo.put("selected_class",s_class);
        todo.put("selected_section",section);
        todo.put("selected_subject",s_subject);
        todo.put("selected_medium",s_medium);
        todo.put("homework",s_homework);
        todo.put("timestamp", Timestamp.now());


        db.collection(common.School_Code).document(common.Branch_code).collection(common.Homework_db).document(id).set(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {

                    test.dismiss();
                    afterSuccessfull(context);

                }



            }
        });



    }

    //--------------------------------------------------------------- Add Notes

    public void addNotes(String s_medium, String s_class, String s_subject, String title, String description,String url, final Context context)

    {
        db = FirebaseFirestore.getInstance();
        List<String> seenusers = new ArrayList<>();
        seenusers.add(common.mobile_no);


        //Random id
        String id = UUID.randomUUID().toString();
        Map<String,Object> todo = new HashMap<>();
        todo.put("id",id);

        todo.put("subject_name",s_subject);
        todo.put("teacher_name",common.teacher_name);
        todo.put("student_class",s_class);
        todo.put("student_medium",s_medium);
        todo.put("notes_title",title);
        todo.put("notes_message",description);
        todo.put("notes_file",url);
        todo.put("timestamp", Timestamp.now());
        todo.put("seenusers",seenusers);



        db.collection(common.School_Code).document(common.Branch_code).collection(common.Notes_db).document(id).set(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {


                    afterSuccessfull(context);



                }



            }
        });







    }



    //-------------------------------------------------------------- AddStudent


    public void addStudent(String name, String dob, String fname, String mobile, String address, String gender, String s_division, String s_medium, String s_class, String s_section, int roll_no, String profile_pic, boolean isvehicle, String concession, final Context context)
    {
        db = FirebaseFirestore.getInstance();

        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);

        Map<String, Object> todo = new HashMap<>();

        String id = UUID.randomUUID().toString();

        todo.put("id", id);
        todo.put("student_name", name);
        todo.put("student_father_name", fname);
        todo.put("student_mobile", mobile);
        todo.put("student_address", address);
        todo.put("student_gender", gender);
        todo.put("student_division", s_division);
        todo.put("student_medium", s_medium);
        todo.put("student_class", s_class);
        todo.put("student_section", s_section);
        todo.put("school_code", common.School_Code);
        todo.put("school_name", common.school_name);
        todo.put("a", 0);
        todo.put("p", 0);
        todo.put("l", 0);
        todo.put("student_roll_no", roll_no);
        todo.put("profile_pic", profile_pic);
        todo.put("student_dob", dob);
        todo.put("isvehicle",isvehicle);
        todo.put("isblock",false);
        todo.put("concession",concession);


        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).document(id).set(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    test.dismiss();
                    adminafterSuccessfull(context);


                }





            }
        });









    }

    private void adminafterSuccessfull(Context context)
    {


        Toast.makeText(context, "Upload successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, AdminHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);


    }


    //--------------------------------------------------------- Add Teacher


    public void addTeacher(String name, String subject, String mobile, String address, String gender, String s_division, String s_medium, String s_class, String s_section, String password, String profile_pic, final Context context)
    {

        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);


        db = FirebaseFirestore.getInstance();



        Map<String,Object> todo = new HashMap<>();

        String id = UUID.randomUUID().toString();

        todo.put("id",id);
        todo.put("teacher_name",name);
        todo.put("teacher_subject",subject);
        todo.put("teacher_mobile",mobile);
        todo.put("teacher_address",address);
        todo.put("teacher_gender",gender);
        todo.put("teacher_division",s_division);
        todo.put("teacher_medium",s_medium);
        todo.put("teacher_class",s_class);
        todo.put("teacher_section",s_section);
        todo.put("school_code",common.School_Code);
        todo.put("school_name",common.school_name);
        todo.put("teacher_password",password);
        todo.put("profile_pic",profile_pic);


        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_teacher_db).document(id).set(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {



                if (task.isSuccessful())
                {
                    test.dismiss();
                    adminafterSuccessfull(context);

                }


            }
        });




    }

}
