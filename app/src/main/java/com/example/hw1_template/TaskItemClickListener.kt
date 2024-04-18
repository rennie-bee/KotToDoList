package com.example.hw1_template


// TaskItemClickListener: An interface for handling clicks on items in your list.
// ESSENTIAL: You must implement this interface in your ViewHolder or Activity to respond to user interactions.
//interface TaskItemClickListener {
//    fun onItemClicked(task: TaskItem)
//}

interface TaskItemClickListener {
    fun onItemClicked(task: TaskItem)
    fun onBodyClicked(task: TaskItem)
    fun onRemoveButtonClicked(task: TaskItem)
}





