package com.example.todoappjava;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM taskData")
    List<TaskData> getTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addTask(TaskData taskData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long updateTask(TaskData taskData);

}
