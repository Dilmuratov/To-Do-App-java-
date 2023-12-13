package com.example.todoappjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.todoappjava.databinding.ActivityAddTaskBinding;

public class AddTaskActivity extends AppCompatActivity {

    ActivityAddTaskBinding binding;
    TaskDao dao;

    Boolean isUpdate = false;

    Integer taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dao = TaskDataBase.getInstance(this.getApplicationContext()).getTaskData();

        initListeners();

        initObservers();
    }

    private void initObservers() {
        isUpdate = getIntent().getBooleanExtra("isUpdate", false);
        if (isUpdate) {
            taskId = getIntent().getIntExtra("taskId", 1);
            String title = getIntent().getStringExtra("title");
            String task = getIntent().getStringExtra("task");
            binding.etTitle.setText(title);
            binding.etTask.setText(task);
        }
    }

    private void initListeners() {
        binding.btnSave.setOnClickListener(v -> {
            if (!binding.etTitle.getText().toString().isEmpty() && !binding.etTask.getText().toString().isEmpty()) {
                if (!isUpdate) {
                    dao.addTask(new TaskData(0, binding.etTitle.getText().toString(), binding.etTask.getText().toString()));
                } else {
                    dao.updateTask(new TaskData(taskId, binding.etTitle.getText().toString(), binding.etTask.getText().toString()));
                }

                finish();

            } else if (binding.etTitle.getText().toString().isEmpty()) {
                binding.etTitle.setError("Title can't be empty!");
            } else {
                binding.etTask.setError("Task can't be empty!");
            }

        });

    }
}