package com.androidcafe.newschoolmanagement.Online_Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidcafe.newschoolmanagement.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.MyViewHolder> {

    Context context;
    List<String> list;
    onvideolecrure onvideolecrure;





    public ChatRoomAdapter(Context context, List<String> list, ChatRoomAdapter.onvideolecrure onvideolecrure) {
        this.context = context;
        this.list = list;
        list.remove(0);
        this.onvideolecrure = onvideolecrure;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_chat_room,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {


        myViewHolder.title.setText(list.get(i));
        myViewHolder.highlight.setText("4");



        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onvideolecrure.clickonvideo(i);
            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView title,subtitle,highlight;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardview);

            title = (TextView)itemView.findViewById(R.id.title);
            subtitle = (TextView)itemView.findViewById(R.id.subtitle);
            highlight = (TextView)itemView.findViewById(R.id.highlight);

        }
    }

    public interface onvideolecrure
    {
        public void clickonvideo(int i);
    }
}
