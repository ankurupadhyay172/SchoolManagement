package com.androidcafe.newschoolmanagement.Notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.MyViewHolder> {


    Context context;
    List<NewsModel> list;


    public NewsViewAdapter(Context context, List<NewsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {





        myViewHolder.title.setText(list.get(i).getTitle().toUpperCase());
        myViewHolder.msg.setText(list.get(i).getMessage().toUpperCase());
        myViewHolder.time.setText(""+ common.gethours(list.get(i).getTimeStamp().getSeconds()));


        if (list.get(i).getFile_url().equals("na"))
        {

        }
        else
        {
            myViewHolder.download_icon.setVisibility(View.VISIBLE);
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title,msg,time;
        ImageView download_icon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.title);
            msg = (TextView)itemView.findViewById(R.id.msg);
            time = (TextView)itemView.findViewById(R.id.time);
            download_icon = (ImageView)itemView.findViewById(R.id.download_icon);

        }
    }
}
