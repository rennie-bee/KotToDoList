// MainActivity.kt
package com.example.hw1_template

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NewTaskSheet.NewTaskDialogListener, TaskItemClickListener {
    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        adapter = TaskItemAdapter(viewModel.tasks, this)

        findViewById<RecyclerView>(R.id.recyclerViewTasks).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        findViewById<Button>(R.id.buttonAddTask).setOnClickListener {
            val newTaskDialog = NewTaskSheet.newInstance(null)
            newTaskDialog.listener = this
            newTaskDialog.show(supportFragmentManager, NewTaskSheet.TAG)
        }

        updateTaskCount()
    }

    override fun onItemClicked(task: TaskItem) {
        // Handle click on the task body
        showEditTaskDialog(task)
    }

    override fun onBodyClicked(task: TaskItem) {
        // Handle click on the task body
        showEditTaskDialog(task)
    }

    private fun showEditTaskDialog(task: TaskItem) {
        val editTaskDialog = NewTaskSheet.newInstance(task)
        editTaskDialog.listener = this
        editTaskDialog.show(supportFragmentManager, NewTaskSheet.TAG)
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, task: TaskItem) {
        if (dialog is NewTaskSheet) {
            // Check if it's an edit action or add new task action
            if (viewModel.tasks.contains(task)) {
                // Editing an existing task
                viewModel.updateTask(task)
            } else {
                // Adding a new task
                viewModel.addTask(task)
            }
            updateTaskCount()
            adapter.notifyDataSetChanged()
        }
    }

    private fun updateTaskCount() {
        val taskCount = viewModel.tasks.size
        findViewById<TextView>(R.id.textViewTaskCount).text = "Tasks: $taskCount"
    }

    override fun onRemoveButtonClicked(task: TaskItem) {
        viewModel.removeTask(task)
        updateTaskCount()
        adapter.notifyDataSetChanged()
    }
}
