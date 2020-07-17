package com.androidcafe.newschoolmanagement.Fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Fees.PayTermFees;
import com.androidcafe.newschoolmanagement.Fees.SchoolFeesRecipt;
import com.androidcafe.newschoolmanagement.Model.Fees_Model;
import com.androidcafe.newschoolmanagement.Model.Receipt_Model;
import com.androidcafe.newschoolmanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFees extends Fragment implements View.OnClickListener{


    TextView term_first_fees,term_second_fees,term_third_fees,term_fourth_fees;

    TextView term1_fees,term2_fees,term3_fees,term4_fees;
    TextView tandcfee1,tandcfee2,tandcfee3,tandcfee4;

    String txt_fees1,txt_fees2,txt_fees3,txt_fees4;

    FirebaseFirestore db;
    Dialog test;


    public SchoolFees() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_school_fees, container, false);



        db = FirebaseFirestore.getInstance();



        term_first_fees = (TextView)view.findViewById(R.id.term_first_fees);
        term_second_fees = (TextView)view.findViewById(R.id.term_second_fees);
        term_third_fees = (TextView)view.findViewById(R.id.term_third_fees);
        term_fourth_fees = (TextView)view.findViewById(R.id.term_fourth_fees);

        term1_fees = (TextView)view.findViewById(R.id.pay_term1);
        term2_fees = (TextView)view.findViewById(R.id.pay_term2);
        term3_fees = (TextView)view.findViewById(R.id.pay_term3);
        term4_fees = (TextView)view.findViewById(R.id.pay_term4);


        tandcfee1 = (TextView)view.findViewById(R.id.tandcfee1);
        tandcfee2 = (TextView)view.findViewById(R.id.tandcfee2);
        tandcfee3 = (TextView)view.findViewById(R.id.tandcfee3);
        tandcfee4 = (TextView)view.findViewById(R.id.tandcfee4);

        term1_fees.setOnClickListener(this);
        term2_fees.setOnClickListener(this);
        term3_fees.setOnClickListener(this);
        term4_fees.setOnClickListener(this);





        updateView();
        updateRecipt();

        return view;
    }


    private void updateView()
    {

        db.collection(common.School_Code).document(common.Branch_code).collection(common.Fees_Structure).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document:task.getResult())
                    {

                        Fees_Model model = document.toObject(Fees_Model.class);
                        if (model.getSelected_class().equals(common.student_class)) {


                            term_first_fees.setText("₹ "+model.getFirst_term_fees());
                            term_second_fees.setText("₹ "+model.getSecond_term_fees());
                            term_third_fees.setText("₹ "+model.getThird_term_fees());
                            term_fourth_fees.setText("₹ "+model.getFourth_term_fees());


                            txt_fees1 = model.getFirst_term_fees();
                            txt_fees2 = model.getSecond_term_fees();
                            txt_fees3 = model.getThird_term_fees();
                            txt_fees4 = model.getFourth_term_fees();

                        }





                    }


                }



            }
        });



    }







    //---------------------------------------------------------show animation








    private void updateRecipt()
    {
        final Intent recipt = new Intent(getActivity(), SchoolFeesRecipt.class);

        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).document(common.user_id).collection(common.Fees_Structure).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {


                    for (QueryDocumentSnapshot document:task.getResult())
                    {

                        final Receipt_Model model = document.toObject(Receipt_Model.class);



                        if (model.getTerm().trim().equals(common.term1+common.currentYear().trim()))
                        {
                            term1_fees.setText("View Receipt");
                            tandcfee1.setText("Already Paid");


                            term1_fees.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    recipt.putExtra("total",txt_fees1);
                                    recipt.putExtra("term",common.term1);
                                    recipt.putExtra("concession_price",model.getConcession_price());
                                    recipt.putExtra("total_amount",model.getTotal_amount());
                                    recipt.putExtra("date",common.getdatetimefromfirebase(model.getTimestamp()));
                                    startActivity(recipt);

                                }
                            });


                        }

                        if (model.getTerm().trim().equals(common.term2+common.currentYear().trim()))
                        {
                            term2_fees.setText("View Receipt");
                            tandcfee2.setText("Already Paid");



                            term2_fees.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    recipt.putExtra("total",txt_fees2);
                                    recipt.putExtra("term",common.term2);
                                    recipt.putExtra("concession_price",model.getConcession_price());
                                    recipt.putExtra("total_amount",model.getTotal_amount());
                                    recipt.putExtra("date",common.getdatetimefromfirebase(model.getTimestamp()));
                                    startActivity(recipt);

                                }
                            });
                        }

                        if (model.getTerm().trim().equals(common.term3+common.currentYear().trim()))
                        {
                            term3_fees.setText("View Receipt");
                            tandcfee3.setText("Already Paid");

                            term3_fees.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    recipt.putExtra("total",txt_fees3);
                                    recipt.putExtra("term",common.term3);
                                    recipt.putExtra("concession_price",model.getConcession_price());
                                    recipt.putExtra("total_amount",model.getTotal_amount());
                                    recipt.putExtra("date",common.getdatetimefromfirebase(model.getTimestamp()));
                                    startActivity(recipt);

                                }
                            });
                        }

                        if (model.getTerm().trim().equals(common.term4+common.currentYear().trim()))
                        {
                            term4_fees.setText("View Receipt");
                            tandcfee4.setText("Already Paid");

                            term4_fees.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    recipt.putExtra("total",txt_fees4);
                                    recipt.putExtra("term",common.term4);
                                    recipt.putExtra("concession_price",model.getConcession_price());
                                    recipt.putExtra("concession_per",model.getConcession_per());
                                    recipt.putExtra("total_amount",model.getTotal_amount());
                                    recipt.putExtra("date",common.getdatetimefromfirebase(model.getTimestamp()));
                                    startActivity(recipt);

                                }
                            });
                        }




                    }





                }





            }
        });






    }








    @Override
    public void onClick(View v) {

        int id = v.getId();
        Intent intent = new Intent(getActivity(), PayTermFees.class);


        switch (id)
        {
            case R.id.pay_term1:


                intent.putExtra("total",txt_fees1);
                intent.putExtra("term",common.term1);
                startActivity(intent);


                break;

            case R.id.pay_term2:
                intent.putExtra("total",txt_fees2);
                intent.putExtra("term",common.term2);
                startActivity(intent);

                break;


            case R.id.pay_term3:


                intent.putExtra("total",txt_fees3);
                intent.putExtra("term",common.term3);
                startActivity(intent);

                break;


            case R.id.pay_term4:

                intent.putExtra("total",txt_fees4);
                intent.putExtra("term",common.term4);
                startActivity(intent);



                break;



        }






    }
}
