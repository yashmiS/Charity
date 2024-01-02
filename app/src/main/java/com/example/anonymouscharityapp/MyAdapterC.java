package com.example.anonymouscharityapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;

import java.lang.annotation.Target;
import java.util.ArrayList;

public class MyAdapterC extends RecyclerView.Adapter<MyAdapterC.MyViewHolderCo> {
    ArrayList <ModelComplete> mcList;
    Context context;

    public MyAdapterC(Context context,ArrayList<ModelComplete>mcList){
        this.mcList = mcList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapterC.MyViewHolderCo onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.itemcomplete,parent,false);
        return  new MyAdapterC.MyViewHolderCo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterC.MyViewHolderCo holder, int position){
        ModelComplete modelComplete = mcList.get(position);
        holder.eventName.setText(modelComplete.getEventName());
        Glide.with(holder.image).load(modelComplete.getImage()).into(holder.image);


    }
    @Override
    public int getItemCount(){
        return mcList.size();
    }


    public static class MyViewHolderCo extends RecyclerView.ViewHolder{

        TextView eventName;
        ImageView image;
//        ImageView image = new ImageView();


        public MyViewHolderCo(@NonNull View itemView){

            super(itemView);
            //image.setImageResource(new Resource<>());

            eventName = itemView.findViewById(R.id.event_text2);
            image =  (ImageView) itemView.findViewById(R.id.image_view);





        }
    }
}
