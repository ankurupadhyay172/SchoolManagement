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
import com.androidcafe.newschoolmanagement.Model.NotesModel;
import com.androidcafe.newschoolmanagement.R;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {


    Context context;
    List<NotesModel> list;



    public NotesAdapter(Context context, List<NotesModel> list) {
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a");
        //String date = dateFormat.format(new Date());

        myViewHolder.item_title.setText("Topic : "+list.get(i).getNotes_title().toUpperCase());
        myViewHolder.item_message.setText("Subject : "+list.get(i).getSubject_name().toUpperCase()+"\n Message : "+list.get(i).getNotes_message().toUpperCase()+"\n\n"+"By "+list.get(i).getTeacher_name().toUpperCase());
        myViewHolder.time.setText(""+common.gethours(list.get(i).getTimestamp().getSeconds()));



        if (common.user_type.equals("Teacher"))
        {


            myViewHolder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(list.get(i).getNotes_file()));
                    v.getContext().startActivity(browserIntent);
                }
            });




        }


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PdfViewer.class);
                intent.putExtra("url",list.get(i).getNotes_file());
                context.startActivity(intent);
            }
        });


        myViewHolder.download.setVisibility(View.VISIBLE);








    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView item_title,item_message,time;
        ImageView download,remove;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_title = (TextView)itemView.findViewById(R.id.title);
            item_message = (TextView)itemView.findViewById(R.id.msg);
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
