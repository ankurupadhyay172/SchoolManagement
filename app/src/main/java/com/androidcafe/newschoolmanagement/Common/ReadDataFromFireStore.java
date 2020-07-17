package com.androidcafe.newschoolmanagement.Common;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Model.NewSchoolStudentModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

public class ReadDataFromFireStore {

    FirebaseFirestore db;

    OnGetStudentData onGetStudentData;
    int present,absent,leave;

    public ReadDataFromFireStore(OnGetStudentData onGetStudentData) {
        this.onGetStudentData = onGetStudentData;
    }

    public void getStudentData(final List<NewSchoolStudentModel> list, Context context)
    {


        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);

        db = FirebaseFirestore.getInstance();

        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).orderBy("student_name", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete( Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                test.dismiss();

                    if (task.getResult().isEmpty())
                    {



                    }
                    else
                    {

                    }



                    for (QueryDocumentSnapshot document:task.getResult())
                    {
                        NewSchoolStudentModel model = document.toObject(NewSchoolStudentModel.class);
                        model.setId(model.getId());

                        list.add(model);



                        onGetStudentData.getSudentData(list);


                    }
                    //updateRecycler(list);




                }
                else
                {
                   // Toast.makeText(ManageStudent.this, "Some error occur please try again", Toast.LENGTH_SHORT).show();
                }



            }
        });



    }


    public void getStudentDataForAttendance(final List<NewSchoolStudentModel> list, Context context, final TextView p, final TextView a, final TextView l)
    {


        LoadingClass loadingClass = new LoadingClass();
        final Dialog test = loadingClass.showLoginDialog(context);

        db = FirebaseFirestore.getInstance();

        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).orderBy("student_name", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete( Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    test.dismiss();

                    if (task.getResult().isEmpty())
                    {



                    }
                    else
                    {

                    }



                    for (QueryDocumentSnapshot document:task.getResult())
                    {
                        NewSchoolStudentModel model = document.toObject(NewSchoolStudentModel.class);
                        model.setId(model.getId());

                        list.add(model);

                        if (model.getP()==1)
                        {
                            present = present+1;
                        }

                        if (model.getA()==1)
                        {
                           absent = absent+1;
                        }
                        if (model.getL()==1)
                        {
                            leave = leave+1;
                        }

                        onGetStudentData.getSudentData(list);


                    }


                    p.setText("P = "+present);
                    a.setText("A = "+absent);
                    l.setText("L = "+leave);




                }
                else
                {
                    // Toast.makeText(ManageStudent.this, "Some error occur please try again", Toast.LENGTH_SHORT).show();
                }



            }
        });



    }








    public void setAttendance(final String status, String id, final String name) {

        Map<String,Object> todo = new HashMap<>();
        if (status.equals("P"))
        {

            todo.put(status.toLowerCase(),1);
            todo.put("a",0);
            todo.put("l",0);
        }
        if (status.equals("A"))
        {


            todo.put(status.toLowerCase(),1);
            todo.put("p",0);
            todo.put("l",0);

        }

        if (status.equals("L"))
        {



            todo.put(status.toLowerCase(),1);
            todo.put("p",0);
            todo.put("a",0);
        }




        todo.put("day", common.currentdate());
        todo.put("month",common.currentMonth());
        todo.put("year",common.currentYear());



        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).document(id).update(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( Task<Void> task) {

                if (task.isSuccessful())
                {




                }



            }
        });






        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).document(id).collection(common.daily_attendance).document(common.currentdate()+"-"+common.currentMonth()+"-"+common.currentYear()).set(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {




            }
        });









    }







    public interface OnGetStudentData
    {
        public void getSudentData(List<NewSchoolStudentModel> list);
    }


}
