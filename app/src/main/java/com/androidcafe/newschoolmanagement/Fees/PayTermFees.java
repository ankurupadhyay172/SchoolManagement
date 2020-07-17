package com.androidcafe.newschoolmanagement.Fees;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Paytm.JSONParser;
import com.androidcafe.newschoolmanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

public class PayTermFees extends AppCompatActivity implements PaytmPaymentTransactionCallback {

    String custid="", orderId="", mid="";


    ImageView student_image,back;
    TextView student_name,head_month,month_fee,vehicle_charge,txt_month,total_amount,concession,fees_saved;
    Button button_pay;


    String total,monthly_fees,vehicle,term_name;

    FirebaseFirestore db;

    RequestQueue rq;


    String UPLOAD_URL="https://script.google.com/macros/s/AKfycbxJyMoxW5c6qFLolSiqG2csdzGBA-_gcBAJp8KehomNgzrv8kUg/exec";
    float total_pay;
    float concession_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_term_fees);




        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //init volly request
        rq = Volley.newRequestQueue(this);



        orderId = UUID.randomUUID().toString();
        custid = common.mobile_no;
        mid = "ULgglV38312289133471"; /// your marchant key

        db = FirebaseFirestore.getInstance();




        concession = (TextView)findViewById(R.id.concession);


        student_image = (ImageView)findViewById(R.id.student_image);
        student_name = (TextView)findViewById(R.id.student_name);
        back = (ImageView)findViewById(R.id.back);
        button_pay = (Button)findViewById(R.id.text_play);
        head_month = (TextView)findViewById(R.id.headmonth);
        student_name.setText(common.student_name+" "+common.student_class+" "+common.student_section);
        month_fee =(TextView)findViewById(R.id.month_fee);
        vehicle_charge = (TextView)findViewById(R.id.vehicle_charge);
        txt_month = (TextView)findViewById(R.id.month);
        total_amount = (TextView)findViewById(R.id.total_amount);
        fees_saved = (TextView)findViewById(R.id.fees_saved);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        concession.setText("Concession "+common.concession+"%");


        if (!common.profile_pic.equals("na"))
        {
            Picasso.get().load(common.profile_pic).into(student_image);
        }


        Intent intent = getIntent();


        if (intent.hasExtra("total"))
        {

            total = intent.getStringExtra("total");
            term_name = intent.getStringExtra("term");

            float percent = Float.parseFloat(common.concession);
            fees_saved.setText("₹ "+(percent/100)*Float.parseFloat(total));


            concession_price = (percent/100)*Float.parseFloat(total);

            total_pay = (Float.parseFloat(total)-concession_price);


            total_amount.setText("₹ "+total_pay);
        }



        head_month.setText(term_name+" Fees");
        txt_month.setText(term_name+" Fees");
        month_fee.setText("₹ "+total);



        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserDetailTOServerdd dl = new sendUserDetailTOServerdd();
                dl.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });









    }






    public class sendUserDetailTOServerdd extends AsyncTask<ArrayList<String>, Void, String> {
        private ProgressDialog dialog = new ProgressDialog(PayTermFees.this);
        //private String orderId , mid, custid, amt;
        String url ="http://ankurupadhyay.000webhostapp.com/xyz/Paytm/paytm/generateChecksum.php";
        String varifyurl = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";
        // "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID"+orderId;
        String CHECKSUMHASH ="";
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
        protected String doInBackground(ArrayList<String>... alldata) {
            JSONParser jsonParser = new JSONParser(PayTermFees.this);
            String param=
                    "MID="+mid+
                            "&ORDER_ID=" + orderId+
                            "&CUST_ID="+custid+
                            "&CHANNEL_ID=WAP&TXN_AMOUNT="+Float.toString(total_pay)+"&WEBSITE=WEBSTAGING"+
                            "&CALLBACK_URL="+ varifyurl+"&INDUSTRY_TYPE_ID=Retail";
            JSONObject jsonObject = jsonParser.makeHttpRequest(url,"POST",param);
            // yaha per checksum ke saht order id or status receive hoga..
            Log.e("CheckSum result >>",jsonObject.toString());
            if(jsonObject != null){
                Log.e("CheckSum result >>",jsonObject.toString());
                try {
                    CHECKSUMHASH=jsonObject.has("CHECKSUMHASH")?jsonObject.getString("CHECKSUMHASH"):"";
                    Log.e("CheckSum result >>",CHECKSUMHASH);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return CHECKSUMHASH;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.e(" setup acc ","  signup result  " + result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            PaytmPGService Service = PaytmPGService.getStagingService();
            // when app is ready to publish use production service
            // PaytmPGService  Service = PaytmPGService.getProductionService();
            // now call paytm service here
            //below parameter map is required to construct PaytmOrder object, Merchant should replace below map values with his own values
            HashMap<String, String> paramMap = new HashMap<String, String>();
            //these are mandatory parameters
            paramMap.put("MID", mid); //MID provided by paytm
            paramMap.put("ORDER_ID", orderId);
            paramMap.put("CUST_ID", custid);
            paramMap.put("CHANNEL_ID", "WAP");
            paramMap.put("TXN_AMOUNT", Float.toString(total_pay));
            paramMap.put("WEBSITE", "WEBSTAGING");
            paramMap.put("CALLBACK_URL" ,varifyurl);
            //paramMap.put( "EMAIL" , "abc@gmail.com");   // no need
            // paramMap.put( "MOBILE_NO" , "9144040888");  // no need
            paramMap.put("CHECKSUMHASH" ,CHECKSUMHASH);
            //paramMap.put("PAYMENT_TYPE_ID" ,"CC");    // no need
            paramMap.put("INDUSTRY_TYPE_ID", "Retail");
            PaytmOrder Order = new PaytmOrder(paramMap);
            Log.e("checksum ", "param "+ paramMap.toString());
            Service.initialize(Order,null);
            // start payment service call here
            Service.startPaymentTransaction(PayTermFees.this, true, true,
                    PayTermFees.this  );
        }
    }






    @Override
    public void onTransactionResponse(Bundle bundle) {


//        if (bundle.get("STATUS").toString().equals("TXN_SUCCESS"))
//        {



        //}

        updateRecipt(bundle.get("ORDERID").toString());



        Log.e("checksum ", " respon true " + bundle.toString());


    }



    @Override
    public void networkNotAvailable() {

    }

    @Override
    public void clientAuthenticationFailed(String inErrorMessage) {

    }

    @Override
    public void someUIErrorOccurred(String inErrorMessage) {

    }

    @Override
    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {

    }

    @Override
    public void onBackPressedCancelTransaction() {

    }

    @Override
    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {

    }





    private void updateRecipt(String orderid) {


        Map<String,Object> todo = new HashMap<>();


        todo.put("term_fees",total);
        todo.put("pay_month",common.currentMonth());
        todo.put("pay_year", Calendar.getInstance().get(Calendar.YEAR));
        todo.put("student_name",common.student_name);
        todo.put("student_class",common.student_class);
        todo.put("student_section",common.student_section);
        todo.put("orderid",orderid);
        todo.put("total_amount",Float.toString(total_pay));
        todo.put("concession_price",Float.toString(concession_price));
        todo.put("concession_per",common.concession);
        todo.put("timestamp", Timestamp.now());
        todo.put("student_medium",common.student_medium);
        todo.put("term",term_name+common.currentYear());

        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).document(common.user_id).collection(common.Fees_Structure).document(orderid).set(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( Task<Void> task) {

                Intent intent = new Intent(PayTermFees.this, AllTypeFeesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                Toast.makeText(PayTermFees.this, "Successfully paid fees", Toast.LENGTH_SHORT).show();


                uploaddataonsheet();




            }
        });







    }





    private void uploaddataonsheet(){
        //Showing the progress dialog
        // final ProgressDialog loading = ProgressDialog.show(Student_entry.this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {
                            //Disimissing the progress dialog
                            //                   loading.dismiss();
                            //Showing toast message of the response
                            Toast.makeText(PayTermFees.this,"Entry successfull" , Toast.LENGTH_LONG).show();

                        }
                        catch (Exception e)
                        {
                            Toast.makeText(PayTermFees.this, ""+e, Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        //             loading.dismiss();

                        //Showing toast
                        Toast.makeText(PayTermFees.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String


                //Getting Image Name
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters




                params.put("id",orderId);
                params.put("rollno",common.student_roll_no);
                params.put("name", common.student_name);
                params.put("fname",common.student_fname);
                params.put("s_class",common.student_class);
                params.put("s_section",common.student_section);
                params.put("s_medium",common.student_medium);
                params.put("fees_term",term_name.toUpperCase());

                params.put("fees",Float.toString(total_pay));
                params.put("concession","0");
                params.put("total",total);
                params.put("method","Online");
                params.put("date",common.getdatetimefromfirebase(Timestamp.now()));



                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(PayTermFees.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
}
