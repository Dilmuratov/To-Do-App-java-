package com.example.todoappjava;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoappjava.databinding.TaskLayoutBinding;

public class TaskListAdapter extends ListAdapter<TaskData, TaskListAdapter.TaskViewHolder> {
    protected TaskListAdapter() {
        super(new DiffUtil.ItemCallback<TaskData>() {
            @Override
            public boolean areItemsTheSame(@NonNull TaskData oldItem, @NonNull TaskData newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull TaskData oldItem, @NonNull TaskData newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int id, String title, String task);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //private val onItemCLickListener((Int) -> Unit)? = null
    //fun setOnItemClickListener(block: ((Int) -> Unit)){
    //  onItemCLickListener = block
    //}

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TaskLayoutBinding binding;

        TaskViewHolder(TaskLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {
            TaskData taskData = getItem(position);
            binding.tvTask.setText(taskData.getTask());
            binding.tvTitle.setText(taskData.getTitle());

            binding.getRoot().setOnClickListener(v -> {
                onItemClickListener.onItemClick(taskData.getId(), taskData.getTitle(), taskData.getTask());
            });
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(
                TaskLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(position);
    }
}
