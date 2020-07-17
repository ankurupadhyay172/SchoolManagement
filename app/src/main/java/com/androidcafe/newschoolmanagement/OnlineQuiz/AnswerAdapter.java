package com.androidcafe.newschoolmanagement.OnlineQuiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidcafe.newschoolmanagement.Model.Question;
import com.androidcafe.newschoolmanagement.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    Context context;
    List<Question> list;


    public AnswerAdapter(Context context, List<Question> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_result_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.button.setText("Question "+(position+1));



        try {


            if (list.get(position).getSelected().equals(list.get(position).getAns()))
            {
                holder.button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_white,0,0,0);

            }
            else
            {
                holder.button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_clear_white,0,0,0);

            }




        }catch (NullPointerException e)
        {
            holder.button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error_outline_white,0,0,0);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        Button button;

        public ViewHolder(View itemView) {
            super(itemView);

            button = (Button)itemView.findViewById(R.id.btn_question);



        }
    }
}


