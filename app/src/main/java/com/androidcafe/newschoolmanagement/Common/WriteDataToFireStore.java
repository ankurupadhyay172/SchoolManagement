package com.androidcafe.newschoolmanagement.Common;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class WriteDataToFireStore {

    FirebaseFirestore db;


    OnUploadData onUploadData;


    public WriteDataToFireStore(OnUploadData onUploadData) {
        this.onUploadData = onUploadData;
    }

    public void WriteDataToFireStore(String id, String name, String fname, String mobile, String address, String gendar, String divison, String medium, String student_class, String section, String school, String token, String image, String branch, String dob, String concession, int a, int p, int l, int roll_no, boolean vehicle, boolean blocked)
    {

        db = FirebaseFirestore.getInstance();


        Map<String,Object> todo = new HashMap<>();


        todo.put("id", id);
        todo.put("student_name", name);
        todo.put("student_father_name", fname);
        todo.put("student_mobile", mobile);
        todo.put("student_address", address);
        todo.put("student_gender", gendar);
        todo.put("student_division", divison);
        todo.put("student_medium", medium);
        todo.put("student_class", student_class);
        todo.put("student_section", section);
        todo.put("school_code", common.School_Code);
        todo.put("school_name", school);
        todo.put("a",0);
        todo.put("p",0);
        todo.put("l", 0);
        todo.put("student_roll_no", roll_no);
        todo.put("profile_pic", image);
        todo.put("school_type", "School");
        todo.put("student_dob", dob);
        todo.put("isvehicle",vehicle);
        todo.put("isblock",blocked);
        todo.put("concession",concession);

        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).document(id).set(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {


                    onUploadData.onGetData("Successfull");

                }



            }
        });




    }




        public interface OnUploadData
        {
            public void onGetData(String status);
        }


}
