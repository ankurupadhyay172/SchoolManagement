package com.androidcafe.newschoolmanagement.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Common.UploadDataOnFireStore;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.R;
import com.androidcafe.newschoolmanagement.Remote.UploadFile;

import java.io.IOException;

import static com.androidcafe.newschoolmanagement.Common.common.PDF_REQUEST;

public class AddSyallabus extends AppCompatActivity implements UploadFile.OnImageUpload, View.OnClickListener {

    Spinner student_division,student_medium,student_class,student_subject;
    Toolbar toolbar;
    TextView submit,file_title;
    EditText description;

    UploadFile uploadFile;

    Uri saveUri;

    boolean isselected;

    ImageView select_file;
    UploadDataOnFireStore uploadDataOnFireStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_syallabus);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        student_division = (Spinner)findViewById(R.id.student_division);
        student_medium = (Spinner)findViewById(R.id.student_medium);
        student_class = (Spinner)findViewById(R.id.student_class);
        student_subject = (Spinner)findViewById(R.id.student_subject);
        description = (EditText)findViewById(R.id.description);
        select_file = (ImageView)findViewById(R.id.select_file);
        file_title = (TextView)findViewById(R.id.file_title);
        submit = (TextView)findViewById(R.id.submit);

        uploadDataOnFireStore = new UploadDataOnFireStore();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter div_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, common.divlist());
        student_division.setAdapter(div_adapter);


        ArrayAdapter medium_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, common.mediumlist());
        student_medium.setAdapter(medium_adapter);


        uploadFile = new UploadFile(this);


        student_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter class_adapter = new ArrayAdapter(AddSyallabus.this,android.R.layout.simple_list_item_1,common.classlist(student_division.getSelectedItemPosition()));
                student_class.setAdapter(class_adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter section_adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,common.subjectlist());
        student_subject.setAdapter(section_adapter);

        select_file.setOnClickListener(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isselected||common.isEmpty(description)||student_division.getSelectedItemPosition()==0||student_medium.getSelectedItemPosition()==0||student_class.getSelectedItemPosition()==0||student_subject.getSelectedItemPosition()==0)
                {
                    if (!isselected)
                    {
                        Toast.makeText(AddSyallabus.this, "Please attach file", Toast.LENGTH_SHORT).show();
                    }

                    if (common.isEmpty(description))
                    {
                        description.setError("Enter some description");
                    }

                    if (student_division.getSelectedItemPosition()==0)
                    {
                        ((TextView)student_division.getChildAt(0)).setError("Select Division");
                    }

                    if (student_medium.getSelectedItemPosition()==0)
                    {
                        ((TextView)student_medium.getChildAt(0)).setError("Select Division");
                    }


                    if (student_class.getSelectedItemPosition()==0)
                    {
                        ((TextView)student_class.getChildAt(0)).setError("Select Division");
                    }

                    if (student_subject.getSelectedItemPosition()==0)
                    {
                        ((TextView)student_subject.getChildAt(0)).setError("Select Division");
                    }



                }

                else
                {



                    uploadFile.uploadonfirestorage(saveUri,AddSyallabus.this);



                }

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (PDF_REQUEST==requestCode&&resultCode==RESULT_OK)
        {
            saveUri =data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(AddSyallabus.this.getContentResolver(),data.getData());


                if (common.message_type.equals("image"))
                {
                    select_file.setImageBitmap(bitmap);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }



            if (common.message_type.equals("pdf"))
            {
                select_file.setImageResource(R.drawable.pdfview);

            }

            if (common.message_type.equals("other"))
            {
                select_file.setImageResource(R.drawable.ic_file);
            }



            if(!saveUri.equals(null))
            {

                isselected = true;
            }





        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }



    @Override
    public void getUrl(String url) {


        uploadDataOnFireStore.addSyllabus(student_medium.getSelectedItem().toString(),student_class.getSelectedItem().toString(),student_subject.getSelectedItem().toString(),description.getText().toString(),url,AddSyallabus.this);

    }


    @Override
    public void onClick(View v)
    {

        popup();



    }










    public void popup()
    {
        final Dialog slideDialog = new Dialog(this, R.style.CustomDialogAnimation);
        slideDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // Setting dialogview
        Window window = slideDialog.getWindow();
        //  window.setGravity(Gravity.BOTTOM);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        slideDialog.setContentView(R.layout.choose_option_layout);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        slideDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
        layoutParams.copyFrom(slideDialog.getWindow().getAttributes());


        LinearLayout gellary = (LinearLayout)slideDialog.findViewById(R.id.gallery);
        LinearLayout pdffile = (LinearLayout)slideDialog.findViewById(R.id.pdffile);
        LinearLayout document = (LinearLayout)slideDialog.findViewById(R.id.document);




        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                common.message_type="other";
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent,PDF_REQUEST);
                slideDialog.dismiss();
            }
        });

        gellary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                common.message_type = "image";
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PDF_REQUEST);
                slideDialog.dismiss();
            }
        });




        pdffile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                common.message_type = "pdf";
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select document"),PDF_REQUEST);
                slideDialog.dismiss();
            }
        });

        //int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.480);

        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = height;
        layoutParams.gravity = Gravity.BOTTOM;

        slideDialog.getWindow().setAttributes(layoutParams);
        slideDialog.setCancelable(true);
        slideDialog.setCanceledOnTouchOutside(true);
        slideDialog.show();
    }
}






