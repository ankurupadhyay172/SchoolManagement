package com.androidcafe.newschoolmanagement.Admin;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Common.UploadDataOnFireStore;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.Fees_Model;
import com.androidcafe.newschoolmanagement.R;
import com.androidcafe.newschoolmanagement.Remote.UploadFile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.androidcafe.newschoolmanagement.Common.common.PERMISSION_REQUEST;
import static com.androidcafe.newschoolmanagement.Common.common.REQUEST_CODE;

public class AddStudent extends AppCompatActivity implements UploadFile.OnImageUpload {

    EditText student_name, student_father_name, dob, mobile_no, address, roll_no;
    Spinner student_division, student_medium, student_class, student_section;

    EditText fees_total, fees_per;
    RadioGroup rg;
    RadioButton rb;
    String gender = "male";

    TextView submit, student_fees;

    FirebaseFirestore db;
    CircleImageView imageView;

    boolean isimage;
    Uri uri;

    UploadFile uploadFile;


    CheckBox vehicale;

    boolean isvehicle;


    UploadDataOnFireStore uploadDataOnFireStore;
    LinearLayout concessionLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        //define the views
        student_name = (EditText) findViewById(R.id.student_name);
        student_father_name = (EditText) findViewById(R.id.student_father_name);
        dob = (EditText) findViewById(R.id.student_dob);
        mobile_no = (EditText) findViewById(R.id.student_mobile);
        address = (EditText) findViewById(R.id.student_address);
        submit = (TextView) findViewById(R.id.ragister_student);
        student_fees = (TextView) findViewById(R.id.student_fees);

        fees_total = (EditText) findViewById(R.id.fees_total);
        fees_per = (EditText) findViewById(R.id.fees_per);


        vehicale = (CheckBox) findViewById(R.id.add_vehicle);
        concessionLayout = (LinearLayout)findViewById(R.id.concession_layout);



        uploadFile = new UploadFile(this);
        uploadDataOnFireStore = new UploadDataOnFireStore();


        vehicale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    isvehicle = true;
                else
                    isvehicle = false;


            }
        });


        //------------------------------ handle the image

        imageView = (CircleImageView) findViewById(R.id.circle_image);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermission();

            }
        });


        db = FirebaseFirestore.getInstance();


        //define the spinner

        student_division = (Spinner) findViewById(R.id.student_division);
        student_medium = (Spinner) findViewById(R.id.student_medium);
        student_class = (Spinner) findViewById(R.id.student_class);
        student_section = (Spinner) findViewById(R.id.student_section);
        roll_no = (EditText) findViewById(R.id.student_roll_no);

        rg = (RadioGroup) findViewById(R.id.rggendar);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb = (RadioButton) findViewById(checkedId);

                gender = rb.getText().toString();
                Toast.makeText(AddStudent.this, "" + rb.getText().toString(), Toast.LENGTH_SHORT).show();


            }
        });


        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, common.divlist());
        student_division.setAdapter(adapter1);

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, common.mediumlist());
        student_medium.setAdapter(adapter2);


        ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, common.section());
        student_section.setAdapter(adapter4);


        student_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter adapter3 = new ArrayAdapter(AddStudent.this, android.R.layout.simple_list_item_1, common.classlist(student_division.getSelectedItemPosition()));
                student_class.setAdapter(adapter3);




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        student_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (student_class.getSelectedItemPosition() == 0 || student_medium.getSelectedItemPosition() == 0) {

                } else {
                    updateFees(student_class.getSelectedItem().toString(), student_medium.getSelectedItem().toString());

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "dd-MMM-yyyy"; // your format
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());


                        dob.setText(sdf.format(myCalendar.getTime()));
                    }

                };
                new DatePickerDialog(AddStudent.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (common.isEmpty(student_name) || common.isEmpty(student_father_name) ||
                        common.isEmpty(dob) || common.isEmpty(mobile_no) || common.isEmpty(address)
                        || student_division.getSelectedItemPosition() == 0 || student_medium.getSelectedItemPosition() == 0 ||
                        student_class.getSelectedItemPosition() == 0 || common.isEmpty(roll_no)) {


                    if (common.isEmpty(student_name)) {
                        student_name.setError("Please enter name");

                    }


                    if (common.isEmpty(student_father_name)) {
                        student_father_name.setError("Please enter father name");

                    }


                    if (common.isEmpty(dob)) {
                        dob.setError("Please enter date of birth");

                    }


                    if (common.isEmpty(address)) {
                        address.setError("Please enter address");

                    }


                    if (common.isEmpty(mobile_no)) {
                        mobile_no.setError("Please enter mobile no");

                    }

                    if (student_division.getSelectedItemPosition() == 0) {

                        ((TextView)student_division.getChildAt(0)).setError("Select a division");
                    }


                    if (student_class.getSelectedItemPosition() == 0) {
                        ((TextView)student_class.getChildAt(0)).setError("Select a division");
                    }


                    if (student_medium.getSelectedItemPosition() == 0) {
                        ((TextView)student_medium.getChildAt(0)).setError("Select a division");
                    }


                    if (common.isEmpty(roll_no)) {
                        roll_no.setError("Enter roll no");
                    }

                } else {


                    if (isimage) {

                        uploadFile.uploadonfirestorage(uri,AddStudent.this);
                    } else {
                        uploadDataOnFireStore.addStudent(student_name.getText().toString(),dob.getText().toString(),student_father_name.getText().toString(),mobile_no.getText().toString()
                            ,address.getText().toString(),gender,student_division.getSelectedItem().toString(),student_medium.getSelectedItem().toString(),student_class.getSelectedItem().toString()
                            ,student_section.getSelectedItem().toString(),Integer.parseInt(roll_no.getText().toString()),"na",isvehicle,
                                fees_per.getText().toString(),AddStudent.this);

                    }


                }


            }
        });





    }

    private void updateFees(String sclass, String smedium) {
        db.collection(common.School_Code).document(common.Branch_code).collection(common.Fees_Structure).document(sclass + smedium).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(Task<DocumentSnapshot> task) {






                if (task.isSuccessful())
                {


                    if (!task.getResult().exists())
                    {
                        student_fees.setText("NA");

                    }
                    else {


                        concessionLayout.setVisibility(View.VISIBLE);
                        Fees_Model model = task.getResult().toObject(Fees_Model.class);


                        try {



                            final int total = Integer.parseInt(model.getFirst_term_fees()) + Integer.parseInt(model.getSecond_term_fees()) + Integer.parseInt(model.getThird_term_fees()) + Integer.parseInt(model.getFourth_term_fees());


                            fees_per.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {


                                    try {


                                        if (fees_per.getText().toString().equals("")) {

                                        } else {

                                            float percent = Float.parseFloat(s.toString());
                                            fees_total.setText("₹ " + (percent / 100) * total);

                                        }


                                    } catch (NullPointerException e) {

                                    }

                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });


                            student_fees.setText("₹ " + total);


                        } catch (NullPointerException e) {
                            student_fees.setText("NA");

                        }


                    }

                }


            }
        });


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            uri = data.getData();
            imageView.setImageURI(data.getData());
            isimage = true;


        }

    }







    //------------------------------------  handle image method

    private void askPermission() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSION_REQUEST);

            } else {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            }
        }


    }


    @Override
    public void getUrl(String url) {



        uploadDataOnFireStore.addStudent(student_name.getText().toString(),dob.getText().toString(),student_father_name.getText().toString(),mobile_no.getText().toString()
                ,address.getText().toString(),gender,student_division.getSelectedItem().toString(),student_medium.getSelectedItem().toString(),student_class.getSelectedItem().toString()
                ,student_section.getSelectedItem().toString(),Integer.parseInt(roll_no.getText().toString()),url,isvehicle,
                fees_per.getText().toString(),AddStudent.this);
    }
}