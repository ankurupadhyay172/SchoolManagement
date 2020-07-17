package com.androidcafe.newschoolmanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.NewSchoolStudentModel;
import com.androidcafe.newschoolmanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddAttendanceAdapter extends RecyclerView.Adapter<AddAttendanceAdapter.MyViewHolder> {


    Context context;
    List<NewSchoolStudentModel> list;

    FirebaseFirestore db;
    RadioButton rb;

    TakeAttandance takeAttandance;


    public AddAttendanceAdapter(Context context, List<NewSchoolStudentModel> list, TakeAttandance takeAttandance) {
        this.context = context;
        this.list = list;
        this.takeAttandance = takeAttandance;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        db = FirebaseFirestore.getInstance();

        View itemView = LayoutInflater.from(context).inflate(R.layout.new_student_item_layout,parent,false);

        return new MyViewHolder(itemView,takeAttandance);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position)
    {

        holder.item_name.setText(list.get(position).getStudent_roll_no()+" "+list.get(position).getStudent_name());


        if (!list.get(position).getProfile_pic().equals("na"))
        {
            try {

                Picasso.get().load(list.get(position).getProfile_pic()).into(holder.imageView);

            }catch (IllegalArgumentException e)
            {

            }

        }
        else
        {
            holder.imageView.setImageResource(R.drawable.ic_man);
        }





        db.collection(common.School_Code).document(common.Branch_code).collection(common.new_student_db).document(list.get(position).getId()).
                collection(common.daily_attendance).
                document(common.currentdate()+"-"+common.currentMonth()+"-"+common.currentYear())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {

                        if (Integer.parseInt(task.getResult().getData().get("a").toString()) == 1) {
                            holder.rba.setChecked(true);
                            holder.status.setText("A");


                        }
                        if (Integer.parseInt(task.getResult().getData().get("p").toString()) == 1) {
                            holder.rbp.setChecked(true);
                            holder.status.setText("P");

                        }

                        if (Integer.parseInt(task.getResult().getData().get("l").toString()) == 1) {
                            holder.rbl.setChecked(true);
                            holder.status.setText("L");
                        }


                    } else {


                        Map<String, Object> todo = new HashMap<>();
                        todo.put("a", 0);
                        todo.put("l", 0);
                        todo.put("p", 0);

                        db.collection(common.new_student_db).document(list.get(position).getId()).update(todo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {


                                if (task.isSuccessful()) {

                                }


                            }
                        });


                    }
                }

            }



        });









        holder.rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                View v= holder.itemView;
                rb = (RadioButton)v.findViewById(checkedId);
               holder.status.setText(rb.getText());





            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView item_name,status;
        CircleImageView imageView;
        RadioGroup rg1;
        RadioButton rba,rbp,rbl;

        TakeAttandance takeAttandance;


        public MyViewHolder(@NonNull View itemView,TakeAttandance takeAttandance) {
            super(itemView);

            this.takeAttandance = takeAttandance;
            item_name = (TextView)itemView.findViewById(R.id.item_name);
            imageView = (CircleImageView)itemView.findViewById(R.id.imageView);
            rg1 = (RadioGroup) itemView.findViewById(R.id.rg1);
            status = (TextView)itemView.findViewById(R.id.status);


            rba = (RadioButton)itemView.findViewById(R.id.rba);
            rbp = (RadioButton)itemView.findViewById(R.id.rbp);
            rbl = (RadioButton)itemView.findViewById(R.id.rbl);


            rba.setOnClickListener(this);
            rbp.setOnClickListener(this);
            rbl.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.rba)
                takeAttandance.getAttendance(getAdapterPosition(),rba.getText().toString());
            if (v.getId()==R.id.rbp)
                takeAttandance.getAttendance(getAdapterPosition(),rbp.getText().toString());
            if (v.getId()==R.id.rbl)
                takeAttandance.getAttendance(getAdapterPosition(),rbl.getText().toString());
        }
    }




    public interface TakeAttandance
    {
        public void getAttendance(int position,String value);
    }

}
