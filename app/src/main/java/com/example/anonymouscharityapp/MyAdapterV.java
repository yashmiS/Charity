package com.example.anonymouscharityapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterV extends RecyclerView.Adapter<MyAdapterV.MyViewHolderVo> {
    ArrayList <ModelVolunteers> mvList;
    Context context;

    public MyAdapterV(Context context,ArrayList<ModelVolunteers>mvList){
        this.mvList = mvList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolderVo onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.itemvolunteers,parent,false);
        return  new MyViewHolderVo(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolderVo holder,int position){
        ModelVolunteers modelVolunteers = mvList.get(position);
        holder.fullname.setText(modelVolunteers.getFullname());
        holder.email.setText(modelVolunteers.getEmail());



    }
    @Override
    public int getItemCount(){
        return mvList.size();
    }

    public static class MyViewHolderVo extends RecyclerView.ViewHolder{

        TextView fullname,email;

//        ImageView hello = new ImageView();


        public MyViewHolderVo(@NonNull View itemView){

            super(itemView);

//            hello.setImageResource(new Resource);

            fullname = itemView.findViewById(R.id.fullname_text2);
            email = itemView.findViewById(R.id.email_text2);



        }
    }
}
