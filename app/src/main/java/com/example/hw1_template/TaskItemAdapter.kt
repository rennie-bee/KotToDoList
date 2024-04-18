package com.example.hw1_template

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter(
    private var tasks: MutableList<TaskItem>,
    private val clickListener: TaskItemClickListener
) : RecyclerView.Adapter<TaskItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item_cell, parent, false)
        return TaskItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task, clickListener)
    }

    override fun getItemCount(): Int = tasks.size

    fun setTasks(newTasks: MutableList<TaskItem>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    fun removeTask(task: TaskItem) {
        val position = tasks.indexOf(task)
        if (position != -1) {
            tasks.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}