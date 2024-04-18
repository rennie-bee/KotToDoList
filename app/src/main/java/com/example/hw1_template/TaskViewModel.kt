package com.example.hw1_template

import androidx.lifecycle.ViewModel

// TaskViewModel: Handles the business logic of your task data, separating concerns from the UI.
// VITAL: This ViewModel should be the sole source of truth for your UI regarding task data.

class TaskViewModel : ViewModel() {
    val tasks = mutableListOf<TaskItem>()

    fun addTask(task: TaskItem) {
        tasks.add(task)
    }

    fun removeTask(task: TaskItem) {
        tasks.remove(task)
    }

    fun updateTask(updatedTask: TaskItem) {
        val index = tasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            tasks[index] = updatedTask
        }
    }
}


