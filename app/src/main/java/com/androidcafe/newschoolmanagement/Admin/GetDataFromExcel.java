package com.androidcafe.newschoolmanagement.Admin;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidcafe.newschoolmanagement.Common.WriteDataToFireStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

import static com.androidcafe.newschoolmanagement.Common.common.UPLOAD_URL;

public class GetDataFromExcel implements WriteDataToFireStore.OnUploadData{

    RequestQueue rq;
    Context context;

    WriteDataToFireStore writeDataToFireStore = new WriteDataToFireStore(this);


    public GetDataFromExcel(Context context) {
        this.context = context;
    }

    public void initVolly()
    {
        //init volly request
        rq = Volley.newRequestQueue(context);

    }




    public void getDatafromurl(){
        //Showing the progress dialog
        // final ProgressDialog loading = ProgressDialog.show(Student_entry.this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject jsonObject = new JSONObject(s);

                            JSONArray array = jsonObject.getJSONArray("records");




                            for (int i=0;i<array.length();i++)
                            {
                                JSONObject object = array.getJSONObject(i);
                                String id = object.get("ID").toString();
                                String name = object.get("STUDENT_NAME").toString();
                                String fname = object.get("FATHER_NAME").toString();
                                String dob = object.get("DOB").toString();
                                String gendar = object.get("GENDAR").toString();
                                String address = object.get("STUDENT_ADDRESS").toString();



                                String divison = object.get("DIVISION").toString();
                                String medium = object.get("MEDIUM").toString();
                                String student_class = object.get("CLASS").toString();
                                String section = object.get("SECTION").toString();


                                String roll_no = object.get("ROLL_NO").toString();
                                String concession = object.get("CONCESSION").toString();

                                String mobile = object.get("MOBILE_NO").toString();


                                String image=object.get("IMAGE").toString();

                                Log.i("alubukhara2",""+id);


                              writeDataToFireStore.WriteDataToFireStore(id,name,fname,mobile,address,gendar,divison,medium,student_class,section,"test","test",image,"S_101",dob,concession,0,0,0,Integer.parseInt(roll_no),false,false);

//                                NewSchoolStudentModel model = new NewSchoolStudentModel(id,name,fname,mobile,address,gendar,divison,medium,student_class,section,"test","test",image,"S_101",dob,concession,0,0,0,Integer.parseInt(roll_no),false,false);
//                                list.add(model);
//                                onGetDataFromExcel.getStudentDataExcel(list);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Log.i("alubukhara",s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {




                        //Showing toast
//                        Toast.makeText(context, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    @Override
    public void onGetData(String status) {

    }








}
