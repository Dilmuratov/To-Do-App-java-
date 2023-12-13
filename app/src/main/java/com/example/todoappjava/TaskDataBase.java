package com.example.todoappjava;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TaskData.class}, version = 1)
public abstract class TaskDataBase extends RoomDatabase {

    public abstract TaskDao getTaskData();

    private static TaskDataBase taskDB;

    public static TaskDataBase getInstance(Context context) {
        if (taskDB == null) {
            taskDB = buildDatabaseInstance(context);
        }
        return taskDB;
    }

    private static TaskDataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, TaskDataBase.class, "task_db").allowMainThreadQueries().build();
    }

    public void cleanUp() {
        taskDB = null;
    }

}
