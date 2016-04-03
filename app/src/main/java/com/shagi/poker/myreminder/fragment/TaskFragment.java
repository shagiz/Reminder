package com.shagi.poker.myreminder.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.shagi.poker.myreminder.MainActivity;
import com.shagi.poker.myreminder.adapter.CurrentTasksAdapter;
import com.shagi.poker.myreminder.adapter.TaskAdapter;
import com.shagi.poker.myreminder.model.ModelTask;

/**
 * Created by Shagi on 27.10.2015.
 */
public abstract class TaskFragment extends Fragment{
    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;

    protected TaskAdapter adapter;

    public MainActivity  activity;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null){
            activity= (MainActivity) getActivity();

        }
        addTaskFromDB();
    }

    public void addTask(ModelTask newTask, boolean saveToDB) {
        int position = -1;

        for (int i = 0; i < adapter.getItemCount(); i ++) {
            if (adapter.getItem(i).isTask()) {
                ModelTask task = (ModelTask) adapter.getItem(i);
                if (newTask.getDate() < task.getDate()) {
                    position = i;
                    break;
                }
            }
        }

        if (position != -1) {
            adapter.addItem(position, newTask);
        } else {
            adapter.addItem(newTask);
        }

        if (saveToDB){
            activity.dbHelper.saveTask(newTask);
        }
    }

    public abstract void addTaskFromDB();

    public abstract void moveTask(ModelTask task);
}
