package com.androidcafe.newschoolmanagement.Adapters;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.HomeWork_Model;
import com.androidcafe.newschoolmanagement.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkAdapter.MyViewHolder> {


    private Context context;
    private List<HomeWork_Model> list;

    public HomeWorkAdapter(Context context, List<HomeWork_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {





        myViewHolder.title.setText("SUBJECT : "+list.get(i).getSelected_subject().toUpperCase());
        myViewHolder.msg.setText("HOMEWORK : "+list.get(i).getHomework().toUpperCase());
        myViewHolder.time.setText(""+ common.gethours(list.get(i).getTimestamp().getSeconds()));





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView title,msg,time;
        ImageView remove;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            title = (TextView)itemView.findViewById(R.id.title);
            msg = (TextView)itemView.findViewById(R.id.msg);
            time = (TextView)itemView.findViewById(R.id.time);



            if (common.user_type.equals("Teacher"))
                itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select the action");
            menu.add(0,getAdapterPosition(),getAdapterPosition(),"DELETE");
        }
    }
}
