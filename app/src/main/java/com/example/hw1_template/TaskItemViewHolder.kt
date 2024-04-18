package com.example.hw1_template

import android.view.View
import androidx.recyclerview.widget.RecyclerView

// TaskItemViewHolder: Holds the view for each task item in the list.
// VERY IMPORTANT: This class binds individual views in the RecyclerView to your data.
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
//import com.example.hw1_template.databinding.TaskItemCellBinding

//class TaskItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//    private val textViewTaskName: TextView = view.findViewById(R.id.textViewTaskName)
//    private val textViewTaskDescription: TextView = view.findViewById(R.id.textViewTaskDescription)
//    private val textViewTaskTime: TextView = view.findViewById(R.id.textViewTaskTime)
//    private val buttonRemoveTask: Button = view.findViewById(R.id.buttonRemoveTask)
//
//    fun bind(task: TaskItem, clickListener: TaskItemClickListener) {
//        textViewTaskName.text = task.name
//        textViewTaskDescription.text = task.description
//        textViewTaskTime.text = task.time
//        buttonRemoveTask.setOnClickListener { clickListener.onItemClicked(task) }
//    }
//}

class TaskItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textViewTaskName: TextView = view.findViewById(R.id.textViewTaskName)
    private val textViewTaskDescription: TextView = view.findViewById(R.id.textViewTaskDescription)
    private val textViewTaskTime: TextView = view.findViewById(R.id.textViewTaskTime)
    private val buttonRemoveTask: Button = view.findViewById(R.id.buttonRemoveTask)

    fun bind(task: TaskItem, clickListener: TaskItemClickListener) {
        textViewTaskName.text = task.name
        textViewTaskDescription.text = task.description
        textViewTaskTime.text = task.time

        // Handle click on the task body
        itemView.setOnClickListener {
            clickListener.onBodyClicked(task)
        }

        // Handle click on the remove button
        buttonRemoveTask.setOnClickListener {
            clickListener.onRemoveButtonClicked(task)
        }
    }
}



