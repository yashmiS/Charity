package com.example.anonymouscharityapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList <Model> mList;
    Context context;

    public MyAdapter(Context context,ArrayList<Model>mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Model model = mList.get(position);
        holder.names.setText(model.getNames());
        holder.donations.setText(model.getDonations());



    }
    @Override
    public int getItemCount(){
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView names,donations;

        public MyViewHolder(@NonNull View itemView){

            super(itemView);

            names = itemView.findViewById(R.id.name_text2);
            donations = itemView.findViewById(R.id.amount_text2);



        }
    }
}
