package com.androidcafe.newschoolmanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Model.AllModel;
import com.androidcafe.newschoolmanagement.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.MyViewHolder> {

    Context context;
    private ArrayList<AllModel> allModelArrayList;


    public AllAdapter(Context context, ArrayList<AllModel> allModelArrayList) {
        this.context = context;
        this.allModelArrayList = allModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(context).inflate(R.layout.all,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        holder.name.setText(allModelArrayList.get(position).getSelected_period());
        holder.time.setText(allModelArrayList.get(position).getStudent_subject());
        holder.txt.setText(allModelArrayList.get(position).getStart_time()+" - "+allModelArrayList.get(position).getEnd_time());
        holder.teacher_name.setText(allModelArrayList.get(position).getTeacher_name());
















    }

    @Override
    public int getItemCount() {
        return allModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image,n2,n3,n4;
        TextView name,time,txt,teacher_name;

        public MyViewHolder(View itemView) {
            super(itemView);


            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            txt=itemView.findViewById(R.id.txt);

            teacher_name=itemView.findViewById(R.id.teacher_name);
            n2=itemView.findViewById(R.id.n2);
            n3=itemView.findViewById(R.id.n3);
            n4=itemView.findViewById(R.id.n4);



        }
    }
}
