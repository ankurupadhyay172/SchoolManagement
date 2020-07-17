package com.androidcafe.newschoolmanagement.Admin;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Common.UploadDataOnFireStore;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.R;
import com.androidcafe.newschoolmanagement.Remote.UploadFile;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.androidcafe.newschoolmanagement.Common.common.PERMISSION_REQUEST;
import static com.androidcafe.newschoolmanagement.Common.common.REQUEST_CODE;

public class AddTeacher extends AppCompatActivity implements UploadFile.OnImageUpload {

    FirebaseFirestore db;
    EditText teacher_name,mobile_no,address,teacher_password;
    Spinner student_division,student_medium,student_class,student_section,student_subject;

    RadioGroup rg;
    RadioButton rb;
    String gender="male";

    TextView submit;
    CircleImageView imageView;

    boolean isimage;
    Uri uri;

    UploadFile uploadFile;

    UploadDataOnFireStore uploadDataOnFireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        db = FirebaseFirestore.getInstance();


        imageView = (CircleImageView)findViewById(R.id.circle_image);

        //define the views
        teacher_name = (EditText)findViewById(R.id.teacher_name);
        teacher_password = (EditText)findViewById(R.id.teacher_password);

        mobile_no = (EditText)findViewById(R.id.student_mobile);
        address = (EditText)findViewById(R.id.student_address);
        submit = (TextView)findViewById(R.id.submit);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermission();

            }
        });


        //define the spinner

        student_division = (Spinner)findViewById(R.id.student_division);
        student_medium = (Spinner)findViewById(R.id.student_medium);
        student_class = (Spinner)findViewById(R.id.student_class);
        student_section = (Spinner)findViewById(R.id.student_section);
        student_subject = (Spinner)findViewById(R.id.student_subject);



        ArrayAdapter section_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,common.subjectlist());
        student_subject.setAdapter(section_adapter);


        rg=(RadioGroup)findViewById(R.id.rggendar);
        uploadFile = new UploadFile(this);
        uploadDataOnFireStore = new UploadDataOnFireStore();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rb = (RadioButton) findViewById(checkedId);

                gender = rb.getText().toString();
                Toast.makeText(AddTeacher.this, ""+rb.getText().toString(), Toast.LENGTH_SHORT).show();


            }
        });


        ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, common.divlist());
        student_division.setAdapter(adapter1);

        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,common.mediumlist());
        student_medium.setAdapter(adapter2);


        ArrayAdapter adapter4 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,common.section());
        student_section.setAdapter(adapter4);


        student_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter adapter3 = new ArrayAdapter(AddTeacher.this, android.R.layout.simple_list_item_1, common.classlist(student_division.getSelectedItemPosition()));
                student_class.setAdapter(adapter3);




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (common.isEmpty(teacher_name)||
                        common.isEmpty(mobile_no) ||common.isEmpty(address)
                        ||student_division.getSelectedItemPosition()==0||student_medium.getSelectedItemPosition()==0||
                        student_class.getSelectedItemPosition()==0||student_subject.getSelectedItemPosition()==0||
                        student_section.getSelectedItemPosition()==0||common.isEmpty(teacher_password))

                {



                    if (common.isEmpty(teacher_name))
                    {
                        teacher_name.setError("Please enter name");

                    }




                    if (student_section.getSelectedItemPosition()==0)
                    {
                        ((TextView)student_section.getChildAt(0)).setError("Select Section");
                    }


                    if (common.isEmpty(address))
                    {
                        address.setError("Please enter address");

                    }




                    if (common.isEmpty(mobile_no))
                    {
                        mobile_no.setError("Please enter mobile no");

                    }

                    if (student_division.getSelectedItemPosition()==0)
                    {
                        ((TextView)student_division.getChildAt(0)).setError("Select Division");
                    }


                    if (student_class.getSelectedItemPosition()==0)
                    {
                        ((TextView)student_class.getChildAt(0)).setError("Select Division");
                    }


                    if (student_medium.getSelectedItemPosition()==0)
                    {
                        ((TextView)student_medium.getChildAt(0)).setError("Select Division");
                    }



                    if (student_subject.getSelectedItemPosition()==0)
                    {
                        ((TextView)student_subject.getChildAt(0)).setError("Select Subject");
                    }

                    if (common.isEmpty(teacher_password))
                    {
                        teacher_password.setError("Please provide password to the teacher");



                    }

                }



                else
                {




                    if (isimage)
                    {

                        uploadFile.uploadonfirestorage(uri,AddTeacher.this);
                    }
                    else
                    {
                        uploadDataOnFireStore.addTeacher(teacher_name.getText().toString(),student_subject.getSelectedItem().toString(),mobile_no.getText().toString()
                        ,address.getText().toString(),gender,student_division.getSelectedItem().toString(),student_medium.getSelectedItem().toString(),student_class.getSelectedItem().toString()
                        ,student_section.getSelectedItem().toString(),teacher_password.getText().toString(),"na",AddTeacher.this);

                    }


                }

















            }
        });



    }



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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            uri = data.getData();
            imageView.setImageURI(data.getData());
            isimage = true;


        }

    }


    @Override
    public void getUrl(String url) {
        uploadDataOnFireStore.addTeacher(teacher_name.getText().toString(),student_subject.getSelectedItem().toString(),mobile_no.getText().toString()
                ,address.getText().toString(),gender,student_division.getSelectedItem().toString(),student_medium.getSelectedItem().toString(),student_class.getSelectedItem().toString()
                ,student_section.getSelectedItem().toString(),teacher_password.getText().toString(),url,AddTeacher.this);

    }
}
