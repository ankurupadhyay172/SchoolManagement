package com.androidcafe.newschoolmanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Model.FacultyModel;
import com.androidcafe.newschoolmanagement.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewManageFacultyAdapter extends RecyclerView.Adapter<NewManageFacultyAdapter.MyViewHolder> {

    private Context context;
    List<FacultyModel> list;
    List<FacultyModel> mDatalist;


    public NewManageFacultyAdapter(Context context, List<FacultyModel> list) {
        this.context = context;
        this.list = list;
        mDatalist = new ArrayList<>();
        mDatalist.addAll(list);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.listview,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.title.setText(list.get(i).getTeacher_name());


            myViewHolder.description.setText(" Class : "+list.get(i).getTeacher_class()+" "+list.get(i).getTeacher_section()+"\n Mobile No:"+list.get(i).getTeacher_mobile()+"\n Password : "+list.get(i).getTeacher_password());



        if (list.get(i).getTeacher_gender().toUpperCase().equals("MALE")&&list.get(i).getProfile_pic().equals("na"))
        {
            myViewHolder.imageView.setImageResource(R.drawable.ic_man);
        }
        if (list.get(i).getTeacher_gender().toUpperCase().equals("FEMALE")&&list.get(i).getProfile_pic().equals("na"))
        {
            myViewHolder.imageView.setImageResource(R.drawable.ic_girlstudent);
        }


        if (!list.get(i).getProfile_pic().equals("na"))
        {
            Picasso.get().load(list.get(i).getProfile_pic()).into(myViewHolder.imageView);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {


        ImageView banar1;
        TextView title;
        TextView description;
        CircleImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            banar1 = (ImageView) itemView.findViewById(R.id.banar1);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            imageView = (CircleImageView)itemView.findViewById(R.id.banar1);

        }
    }
}
