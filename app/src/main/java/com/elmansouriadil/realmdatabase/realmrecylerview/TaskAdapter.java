package com.elmansouriadil.realmdatabase.realmrecylerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by yeah on 4/22/2016.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder>{
    LayoutInflater layoutInflater;
    RealmResults<Task> RT ;
    TaskAdapter(Context context,RealmResults<Task> data){
        updateList(data);
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.task,parent,false);
        TaskHolder t = new TaskHolder(view);
        return t;
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.tn.setText(RT.get(position).getName());
        holder.tt.setText(RT.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return RT.size();
    }
     public class TaskHolder extends RecyclerView.ViewHolder{
        TextView tn,tt;
        public TaskHolder(View itemView) {
            super(itemView);
            tn  = (TextView) itemView.findViewById(R.id.nom);
            tt  = (TextView) itemView.findViewById(R.id.temps);
        }
    }
    public void updateList(RealmResults<Task> t)   {
            RT = t;
            notifyDataSetChanged();
    }
}
