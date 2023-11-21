package com.example.android_todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTodoTitle: TextView = itemView.findViewById(R.id.textViewTodoTitle)
        val checkBoxDone: CheckBox = itemView.findViewById(R.id.checkBoxDone) // Assuming there's a CheckBox with an ID checkBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleCheckBox(textViewTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            textViewTodoTitle.paintFlags = textViewTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            textViewTodoTitle.paintFlags = textViewTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.apply {
            textViewTodoTitle.text = currentTodo.title
            checkBoxDone.isChecked = currentTodo.isChecked
            toggleCheckBox(textViewTodoTitle, currentTodo.isChecked)
            checkBoxDone.setOnCheckedChangeListener { _, isChecked ->
                toggleCheckBox(textViewTodoTitle, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}