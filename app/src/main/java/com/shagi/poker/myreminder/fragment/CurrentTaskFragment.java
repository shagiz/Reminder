package com.shagi.poker.myreminder.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shagi.poker.myreminder.R;
import com.shagi.poker.myreminder.adapter.CurrentTasksAdapter;
import com.shagi.poker.myreminder.database.DBHelper;
import com.shagi.poker.myreminder.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shagi on 27.10.2015.
 */
public class CurrentTaskFragment extends TaskFragment {


    public CurrentTaskFragment() {
        // Required empty public constructor
    }

    OnTaskDoneListener onTaskDoneListener;

    public interface OnTaskDoneListener{
        void onTaskDone(ModelTask task);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try{
            onTaskDoneListener = (OnTaskDoneListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
            + "must implement OnTaskDoneListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvCurrentTasks);

        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);

        adapter = new CurrentTasksAdapter(this);
        recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;
    }


    @Override
    public void addTaskFromDB() {
        List<ModelTask> tasks = new ArrayList<>();
        tasks.addAll(activity.dbHelper.query().getTasks(DBHelper.SELECTION_STATUS + " OR " + DBHelper.SELECTION_STATUS,
                new String[]{Integer.toString(ModelTask.STATUS_CURRENT), Integer.toString(ModelTask.STATUS_OVERDUE)},
                DBHelper.TASK_DATE_COLUMN));

        for (int i = 0; i < tasks.size(); i++) {
            addTask(tasks.get(i), false);
        }
    }

    @Override
    public void moveTask(ModelTask task) {
        Log.d("TAGER","2");
        onTaskDoneListener.onTaskDone(task);
    }
}
