package com.androidcafe.newschoolmanagement.Fees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.R;
import com.squareup.picasso.Picasso;

public class SchoolFeesRecipt extends AppCompatActivity {

    ImageView back,profile_pic;
    TextView paid_name,pay_at,total,month_fees,vehicle_charge,payment_date,month,payment_method,fees_total,term_title,concession;
    String term,fees,total_amount,concession_price;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_fees_recipt);




        back = (ImageView)findViewById(R.id.back);
        paid_name = (TextView)findViewById(R.id.paid_name);
        profile_pic = (ImageView)findViewById(R.id.profile_pic);
        pay_at = (TextView)findViewById(R.id.pay_at);
        total = (TextView)findViewById(R.id.total);
        month_fees = (TextView)findViewById(R.id.monthly_fees);
        vehicle_charge = (TextView)findViewById(R.id.vehicle_charge);
        payment_date = (TextView)findViewById(R.id.payment_date);
        month = (TextView)findViewById(R.id.month);
        payment_method = (TextView)findViewById(R.id.payment_method);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        paid_name.setText(common.student_name);
        fees_total = (TextView)findViewById(R.id.total);
        term_title = (TextView)findViewById(R.id.term_title);
        concession = (TextView)findViewById(R.id.concession);


        if (common.profile_pic.equals("na"))
        {

        }else
            Picasso.get().load(common.profile_pic).into(profile_pic);



        //--------------------------------------------------------------------- get Data from intent


        Intent intent = getIntent();
        if (intent.hasExtra("term"))
        {

            concession_price = intent.getStringExtra("concession_price");

            total_amount = intent.getStringExtra("total_amount");
            term = intent.getStringExtra("term");
            month.setText(term);
            fees = intent.getStringExtra("total");
            month_fees.setText("₹ "+fees);
            fees_total.setText("₹ "+total_amount);
            term_title.setText(term.toUpperCase()+" FEES :"+fees);

            String percent = intent.getStringExtra("concession_per");


            concession.setText("Total Concession : "+ common.concession+ "%  ₹"+concession_price);
            payment_date.setText(intent.getStringExtra("date"));


        }





    }
}
