package com.androidcafe.newschoolmanagement.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Common.UploadDataOnFireStore;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.R;

public class AddHomeWork extends AppCompatActivity {

    Spinner student_division,student_medium,student_class,student_subject,student_section;

    EditText home_work;
    TextView send;
    Toolbar toolbar;

    UploadDataOnFireStore uploadDataOnFireStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home_work);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        student_division = (Spinner)findViewById(R.id.student_division);
        student_medium = (Spinner)findViewById(R.id.student_medium);
        student_class = (Spinner)findViewById(R.id.student_class);
        student_subject = (Spinner)findViewById(R.id.student_subject);
        student_section = (Spinner)findViewById(R.id.student_section);
        home_work = (EditText)findViewById(R.id.homework);
        send = (TextView)findViewById(R.id.send);

        uploadDataOnFireStore = new UploadDataOnFireStore();


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home Work");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter div_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, common.divlist());
        student_division.setAdapter(div_adapter);


        ArrayAdapter medium_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, common.mediumlist());
        student_medium.setAdapter(medium_adapter);



        student_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter class_adapter = new ArrayAdapter(AddHomeWork.this,android.R.layout.simple_list_item_1,common.classlist(student_division.getSelectedItemPosition()));
                student_class.setAdapter(class_adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter subject_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,common.subjectlist());
        student_subject.setAdapter(subject_adapter);

        ArrayAdapter section_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,common.section());
        student_section.setAdapter(section_adapter);





        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (common.isEmpty(home_work) || student_division.getSelectedItemPosition() == 0 || student_medium.getSelectedItemPosition() == 0 || student_class.getSelectedItemPosition() == 0 || student_subject.getSelectedItemPosition() == 0) {

                    if (common.isEmpty(home_work)) {
                        home_work.setError("Enter some description");
                    }

                    if (student_division.getSelectedItemPosition() == 0) {
                        ((TextView) student_division.getChildAt(0)).setError("Select Division");
                    }

                    if (student_medium.getSelectedItemPosition() == 0) {
                        ((TextView) student_medium.getChildAt(0)).setError("Select Division");
                    }


                    if (student_class.getSelectedItemPosition() == 0) {
                        ((TextView) student_class.getChildAt(0)).setError("Select Division");
                    }

                    if (student_subject.getSelectedItemPosition() == 0) {
                        ((TextView) student_subject.getChildAt(0)).setError("Select Division");
                    }


                }
                else
                {
                    uploadDataOnFireStore.addHomeWork(student_medium.getSelectedItem().toString(),student_class.getSelectedItem().toString(),student_subject.getSelectedItem().toString(),home_work.getText().toString(),student_section.getSelectedItem().toString(),AddHomeWork.this);
                }
            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
