package com.androidcafe.newschoolmanagement.Teacher;

import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Common.common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;

public class HandleTeacherFireStore {

    FirebaseFirestore db;



    public void getTotalStudents(final TextView total)
    {
        db = FirebaseFirestore.getInstance();


        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {

                    int i = 0;
                    for (QueryDocumentSnapshot document:task.getResult())
                    {
                        i = i+1;
                    }
                    total.setText("Total = "+i);

                }


            }
        });
    }
}
