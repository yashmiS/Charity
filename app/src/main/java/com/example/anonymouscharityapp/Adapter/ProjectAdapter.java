package com.example.anonymouscharityapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anonymouscharityapp.Model1.Project;
import com.example.anonymouscharityapp.OngoingEvents;
import com.example.anonymouscharityapp.PaymentMethod;
import com.example.anonymouscharityapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProjectAdapter extends  RecyclerView.Adapter<ProjectAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Project> projectList;
    private List<Project> projectListFull;

    public ProjectAdapter(Context context, List<Project> projectList) {
        this.context = context;
        this.projectListFull = projectList;
        this.projectList = new ArrayList<>(projectListFull);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_displayed_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Project project = projectList.get(position);

        holder.requestTitle.setText(project.getRequestTitle());
        holder.requestLocation.setText(project.getRequestLocation());
        holder.requestStartDate.setText(project.getRequestStartDate());
        holder.requestDescription.setText(project.getRequestDescription());


    }


    @Override
    public int getItemCount() {

        return projectList.size();
    }

    @Override
    public Filter getFilter() {
        return projectFilter;
    }

    private final Filter projectFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Project> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() ==0){
                filteredList.addAll(projectListFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Project project: projectListFull){
                    if(project.getRequestTitle().toLowerCase().contains((filterPattern))){
                        filteredList.add(project);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            filterResults.count =  filteredList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            projectList.clear();
            projectList.addAll((List) filterResults.values  );
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public CircleImageView imageProject;
        public TextView requestTitle, requestLocation, requestStartDate, requestDescription;
        public Button projectButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageProject = itemView.findViewById(R.id.imageProject);
            requestTitle= itemView.findViewById(R.id.requestTitle);
            requestLocation = itemView.findViewById(R.id.requestLocation);
            requestStartDate = itemView.findViewById(R.id.requestStartDate);
            requestDescription = itemView.findViewById(R.id.requestDescription);
            projectButton = itemView.findViewById(R.id.projectButton);

            projectButton.setOnClickListener(this);
//            projectButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(OngoingEvents.this, PaymentMethod.class);
//                    startActivity(intent);
//                }
//            });


        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), PaymentMethod.class);
            view.getContext().startActivity(intent);
        }
    }








//    private final Filter projectFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//           List<Project> filteredList = new ArrayList<>();
//
//           if(constraint == null|| constraint.length() ==0) {
//               filteredList.addAll(projectListFull);
//           }else{
//               String filterPattern  = constraint.toString().toLowerCase().trim();
//
//               for(Project project: projectListFull){
//                   if (project.getRequestTitle().toLowerCase().contains(filterPattern)){
//                       filteredList.add(project);
//                   }
//               }
//           }
//
//           FilterResults results = new FilterResults();
//           results.values = filteredList;
//            results.count = filteredList.size();
//           return results;
//
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            projectList.clear();
//            projectList.addAll((List) results.values  );
//            notifyDataSetChanged();
//        }
//    };
//
}
