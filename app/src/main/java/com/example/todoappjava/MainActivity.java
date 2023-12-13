package com.example.todoappjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.todoappjava.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TaskDao dao;
    ActivityMainBinding binding;

    TaskListAdapter adapter = new TaskListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dao = TaskDataBase.getInstance(this).getTaskData();

        List<TaskData> list = dao.getTasks();

        binding.tasksRecyclerView.setAdapter(adapter);

        adapter.submitList(list);

        initListener();

    }

    private void initListener() {
        binding.fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });

        adapter.setOnItemClickListener((id, title, task) -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            intent.putExtra("isUpdate", true);
            intent.putExtra("taskId", id);
            intent.putExtra("title", title);
            intent.putExtra("task", task);
            startActivity(intent);
        });

        binding.ivRefresh.setOnClickListener(v -> {
            adapter.submitList(dao.getTasks());
        });
    }
}