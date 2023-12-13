package com.example.todoappjava;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "taskData")
public class TaskData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String task;

    public TaskData(Integer id, String title, String task) {
        this.id = id;
        this.title = title;
        this.task = task;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TaskData)) return false;
        TaskData taskData = (TaskData) obj;
        if (id != taskData.id) return false;
        return title != null ? title.equals(taskData.title) : taskData.task == null;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "Note{" + "id = " + id + ", title = " + title + ", task = " + task + "}\n";
    }
}
