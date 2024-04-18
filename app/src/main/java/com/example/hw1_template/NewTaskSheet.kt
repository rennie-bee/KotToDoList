package com.example.hw1_template

import android.app.AlertDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import android.text.format.DateFormat
import java.util.*

class NewTaskSheet : DialogFragment() {
    companion object {
        const val TAG = "NewTaskSheet"

        fun newInstance(task: TaskItem?): NewTaskSheet {
            val args = Bundle().apply {
                putParcelable("task", task)
            }
            val fragment = NewTaskSheet()
            fragment.arguments = args
            return fragment
        }
    }

    interface NewTaskDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, task: TaskItem)
    }

    var listener: NewTaskDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.fragment_new_task_sheet, null)

            builder.setView(view)
                .setPositiveButton("Save") { dialog, id ->
                    val name = view.findViewById<EditText>(R.id.editTextTaskName).text.toString()
                    val description = view.findViewById<EditText>(R.id.editTextTaskDescription).text.toString()
                    val time = view.findViewById<TextView>(R.id.textViewSelectedTime).text.toString()

                    val task = arguments?.getParcelable<TaskItem>("task") // Get the task argument
                    if (task != null) {
                        // If task is not null, it means we are editing an existing task
                        task.name = name
                        task.description = description
                        task.time = time
                        listener?.onDialogPositiveClick(this, task)
                    } else {
                        // If task is null, it means we are adding a new task
                        val newTask = TaskItem(0, name, description, time)
                        listener?.onDialogPositiveClick(this, newTask)
                    }
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    dialog.cancel()
                }

            val task = arguments?.getParcelable<TaskItem>("task")
            task?.let {
                view.findViewById<EditText>(R.id.editTextTaskName).setText(it.name)
                view.findViewById<EditText>(R.id.editTextTaskDescription).setText(it.description)
                view.findViewById<TextView>(R.id.textViewSelectedTime).text = it.time
            }

            view.findViewById<Button>(R.id.buttonSelectTime).setOnClickListener {
                showTimePickerDialog()
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { time ->
            val textViewTime = dialog?.findViewById<TextView>(R.id.textViewSelectedTime)
            textViewTime?.text = time
        }
        timePicker.show(parentFragmentManager, "timePicker")
    }
}

class TimePickerFragment(private val onTimeSelected: (String) -> Unit) : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(requireActivity(), this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
        onTimeSelected(time)
    }
}
