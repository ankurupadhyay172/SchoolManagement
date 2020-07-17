package com.androidcafe.newschoolmanagement.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Common.PdfViewer;
import com.androidcafe.newschoolmanagement.Common.common;
import com.androidcafe.newschoolmanagement.Model.SyllabusModel;
import com.androidcafe.newschoolmanagement.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.MyViewHolder> {

    Context context;
    List<SyllabusModel> list;





    public SyllabusAdapter(Context context, List<SyllabusModel> list) {
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
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


        SimpleDateFormat format = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a");

        String date = format.format(new Date(list.get(i).getTimestamp().getSeconds()*1000L));






        myViewHolder.title.setText(list.get(i).getSelected_subject().toUpperCase());
        myViewHolder.message.setText(list.get(i).getDescription().toUpperCase());
        myViewHolder.time.setText(common.gethours(list.get(i).getTimestamp().getSeconds()));

        myViewHolder.download.setVisibility(View.VISIBLE);

        myViewHolder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(list.get(i).getPdf_url()));
                v.getContext().startActivity(browserIntent);
            }
        });


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PdfViewer.class);
                intent.putExtra("url",list.get(i).getPdf_url());
                context.startActivity(intent);
            }
        });











    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView title,message,time;
        ImageView download,remove;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            title = (TextView)itemView.findViewById(R.id.title);
            message = (TextView)itemView.findViewById(R.id.msg);
            time = (TextView)itemView.findViewById(R.id.time);
            download = (ImageView)itemView.findViewById(R.id.download_icon);
            remove = (ImageView)itemView.findViewById(R.id.remove);

            if (common.user_type.equals("Teacher"))
            {
                itemView.setOnCreateContextMenuListener(this);

            }




        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select the action");
            menu.add(0,getAdapterPosition(),getAdapterPosition(),"DELETE");
        }
    }
}
