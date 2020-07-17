package com.androidcafe.newschoolmanagement.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Adapters.AllAdapter;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.AllModel;
import com.androidcafe.newschoolmanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class All extends Fragment {


    private AllAdapter allAdapter;
    private RecyclerView recyclerview;
    private ArrayList<AllModel> allModelArrayList;

    FirebaseFirestore db;
    TextView error_msg;


    public All() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_all, container, false);


        db = FirebaseFirestore.getInstance();

        error_msg = (view).findViewById(R.id.error_msg);

        recyclerview = (view).findViewById(R.id.recycler4);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        updateTimeTable();









        return  view;
    }

    private void updateTimeTable() {

        db.collection(common.School_Code).document(common.Branch_code).collection(common.timetable).orderBy("start_milisecond", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete( Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {
                    if (task.getResult().isEmpty())
                    {

                        error_msg.setVisibility(View.VISIBLE);

                    }
                    else
                    {


                        allModelArrayList = new ArrayList<>();

                        for (QueryDocumentSnapshot document:task.getResult())
                        {




                            try {


                                AllModel model = document.toObject(AllModel.class);
                                model.setId(document.getId());


                                if (model.getSelected_days().contains("all")||model.getSelected_days().contains("Monday"))
                                {

                                    allModelArrayList.add(model);

                                }
                                else
                                {
                                    error_msg.setVisibility(View.VISIBLE);
                                }




                            }catch (NullPointerException e)
                            {

                            }


                        }




                        allAdapter = new AllAdapter(getActivity(),allModelArrayList);
                        recyclerview.setAdapter(allAdapter);




                    }





                }




            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                error_msg.setVisibility(View.VISIBLE);
            }
        });


    }

}
