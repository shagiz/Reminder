package com.shagi.poker.myreminder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.shagi.poker.myreminder.model.ModelTask;

/**
 * Created by Shagi on 29.10.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;

    public static final String DATABASE_NAME="reminder_database";

    public static final String TASKS_TABLE="tasks_table";

    public static final String TASK_TITLE_COLUMN="task_title";
    public static final String TASK_DATE_COLUMN="task_date";
    public static final String TASK_TIME_STAMP_COLUMN="task_time_stamp";
    public static final String TASK_PRIORITY_COLUMN="task_priority";
    public static final String TASK_STATUS_COLUMN="task_status";

    private static final String TASKS_TABLE_CREATE_SCRIPT = "CREATE TABLE "
            + TASKS_TABLE + " ("+ BaseColumns._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK_TITLE_COLUMN + " TEXT NOT NULL, "
            + TASK_DATE_COLUMN + " LONG, " + TASK_PRIORITY_COLUMN + " INTEGER, "
            + TASK_STATUS_COLUMN + " INTEGER, " + TASK_TIME_STAMP_COLUMN + " LONG);";

    public static final String SELECTION_STATUS = DBHelper.TASK_STATUS_COLUMN + " = ?";

    private DBQueryManager dbQueryManager;
    private DBUpdateManager dbUpdateManager;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbQueryManager = new DBQueryManager(getReadableDatabase());
        dbUpdateManager = new DBUpdateManager(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASKS_TABLE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TASKS_TABLE);
        onCreate(db);
    }

    public void saveTask(ModelTask task){
        ContentValues newValues = new ContentValues();

        newValues.put(TASK_TITLE_COLUMN, task.getTitle());
        newValues.put(TASK_DATE_COLUMN, task.getDate());
        newValues.put(TASK_PRIORITY_COLUMN, task.getPriority());
        newValues.put(TASK_STATUS_COLUMN, task.getStatus());
        newValues.put(TASK_TIME_STAMP_COLUMN, task.getTimeStamp());

        getWritableDatabase().insert(TASKS_TABLE, null, newValues);

    }

    public DBQueryManager query(){
        return dbQueryManager;
    }

    public DBUpdateManager update(){
        return dbUpdateManager;
    }
}
