package com.androidcafe.newschoolmanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.Model.NewSchoolStudentModel;
import com.androidcafe.newschoolmanagement.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewManageAdapter extends RecyclerView.Adapter<NewManageAdapter.MyViewHolder> implements Filterable {

    private Context context;
    List<NewSchoolStudentModel> list;
    List<NewSchoolStudentModel> mDatalist;



    public NewManageAdapter(Context context, List<NewSchoolStudentModel> list) {
        this.context = context;
        this.list = list;
        mDatalist = new ArrayList<>();
        mDatalist.addAll(list);
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.listview,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {



        myViewHolder.title.setText(list.get(i).getStudent_name());
        myViewHolder.description.setText(list.get(i).getStudent_class()+" "+list.get(i).getStudent_section()+"\nMobile No : "+list.get(i).getStudent_mobile()+"\nPassword : "+list.get(i).getSchool_code());

            myViewHolder.description.setText(list.get(i).getStudent_class()+" "+list.get(i).getStudent_section()+"\nMobile No : "+list.get(i).getStudent_mobile()+"\nPassword : "+list.get(i).getSchool_code());



//            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, ShowStudent_details.class);
//                    intent.putExtra("id",list.get(i).getId());
//                    context.startActivity(intent);
//                }
//            });












        if (list.get(i).getProfile_pic().equals("na")||list.get(i).getProfile_pic().equals(""))
        {
        }
        else
        {

            try {
                Picasso.get().load(list.get(i).getProfile_pic()).into(myViewHolder.imageView);

            }catch (IllegalArgumentException e)
            {

            }



        }








    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    @Override
    public Filter getFilter() {
        return userFilter;
    }




    private Filter userFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<NewSchoolStudentModel> filteredList = new ArrayList<>();

            if (constraint==null||constraint.length()==0)
            {

                filteredList.addAll(mDatalist);
            }
            else
            {
                String filter = constraint.toString().toLowerCase().trim();

                for (NewSchoolStudentModel dataItem:mDatalist)
                {
                    if (dataItem.getStudent_name().toLowerCase().contains(filter))
                    {

                        filteredList.add(dataItem);
                    }


                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            list.clear();
            list.addAll((Collection<? extends NewSchoolStudentModel>) results.values);
            notifyDataSetChanged();
        }
    };























    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView banar1;
        TextView title;
        TextView description;
        CircleImageView imageView;


        public MyViewHolder(View itemView) {
            super(itemView);

            banar1 = (ImageView) itemView.findViewById(R.id.banar1);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            imageView = (CircleImageView)itemView.findViewById(R.id.banar1);

        }
    }
}
