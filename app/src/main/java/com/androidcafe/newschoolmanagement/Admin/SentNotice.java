package com.androidcafe.newschoolmanagement.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dmax.dialog.SpotsDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcafe.newschoolmanagement.Common.GetFireStoreData;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.R;
import com.androidcafe.newschoolmanagement.Remote.UploadFile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.androidcafe.newschoolmanagement.Common.common.PDF_REQUEST;

public class SentNotice extends AppCompatActivity implements UploadFile.OnImageUpload{

    FloatingActionButton fab;

    Spinner spinner;
    String selected_notify;
    EditText title,message;


    LinearLayout pdf_layout;
    CheckBox attach_file;

    ImageView add_pdf;
    TextView pdf_txt;
    SpotsDialog waitingDialog;
    Uri saveUri;
    boolean isselected;

    RecyclerView recyclerView;





    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);

        fab = (FloatingActionButton)findViewById(R.id.fab);

        toolbar = (Toolbar)findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Send Notice");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showLoginDialog();
            }
        });



        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetFireStoreData getFireStoreData = new GetFireStoreData();
        getFireStoreData.getDataFronFireStore(this,recyclerView);


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void showLoginDialog() {

        final AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Send Notice");
        dialog.setMessage("Write notice want to send");
        LayoutInflater inflater = LayoutInflater.from(this);
        View login_layout=inflater.inflate(R.layout.msg_layout,null);



        //Handle the spinner


        LinearLayout spinner_layout = (LinearLayout)login_layout.findViewById(R.id.spinner_layout);
        spinner = (Spinner)login_layout.findViewById(R.id.select_user);
        title = (EditText) login_layout.findViewById(R.id.title);
        message = (EditText) login_layout.findViewById(R.id.message);
        attach_file = (CheckBox)login_layout.findViewById(R.id.add_document);
        pdf_layout = (LinearLayout)login_layout.findViewById(R.id.add_pdf_layout);

        add_pdf = (ImageView)login_layout.findViewById(R.id.pdf_file);
        pdf_txt = (TextView)login_layout.findViewById(R.id.pdf_txt);


        attach_file.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    pdf_layout.setVisibility(View.VISIBLE);
                }
                else
                {
                    pdf_layout.setVisibility(View.GONE);
                }


            }
        });









        add_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selectpdf();

                popup();
            }
        });







        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_selecting_adults, R.id.data, common.category());
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0)
                {
                    selected_notify = common.subscribe_All;
                }
                if (position==2)
                {
                    selected_notify = common.subscribe_faculty;
                }
                if (position==1)
                {
                    selected_notify = common.subscribe_student;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        spinner_layout.setVisibility(View.VISIBLE);





        dialog.setView(login_layout);

        //set button
        dialog.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                waitingDialog = new SpotsDialog(SentNotice.this);
                waitingDialog.show();


                if (common.isEmpty(message)||common.isEmpty(title))
                {
                    if (common.isEmpty(message))
                        message.setError("Please enter message");
                    if (common.isEmpty(title))
                        title.setError("Please enter title");


                }else
                {

                    Toast.makeText(SentNotice.this, ""+pdf_txt.getText().toString(), Toast.LENGTH_SHORT).show();
                    if (pdf_txt.getText().toString().equals("File Selected"))
                    {
                        Toast.makeText(SentNotice.this, "file selected", Toast.LENGTH_SHORT).show();



                        UploadData();


                    }
                    if (pdf_txt.getText().toString().equals("Choose File"))
                    {
                        Toast.makeText(SentNotice.this, "file not selected", Toast.LENGTH_SHORT).show();

                        //setData(title.getText().toString(),message.getText().toString());
                    }



                }



            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });




        dialog.show();


    }

    private void UploadData()
    {

        UploadFile file = new UploadFile(this);
        file.uploadonfirestorage(saveUri,SentNotice.this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (PDF_REQUEST==requestCode&&resultCode==RESULT_OK)
        {
            saveUri =data.getData();

            if(!saveUri.equals(null))
            {
                pdf_txt.setText("File Selected");
                add_pdf.setImageResource(R.drawable.pdfview);
                isselected = true;
            }





        }
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
                intent.setType("file/*");
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
                intent.setType("pdf/*");
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

    @Override
    public void getUrl(String url) {
        Toast.makeText(this, ""+url, Toast.LENGTH_SHORT).show();
        Log.e("ankururl",url);
    }


}
