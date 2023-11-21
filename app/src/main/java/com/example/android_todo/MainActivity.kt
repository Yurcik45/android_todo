package com.example.android_todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText

class MainActivity : ComponentActivity() {
    private lateinit var recyclerViewTodoItems: RecyclerView
    private lateinit var buttonAddTodo: Button
    private lateinit var buttonDeleteAllDone: Button
    private lateinit var editTextTodoItem: EditText
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        recyclerViewTodoItems = findViewById(R.id.recyclerViewTodoItems)
        buttonAddTodo = findViewById(R.id.buttonAddTodo)
        buttonDeleteAllDone = findViewById(R.id.buttonDeleteAllDone)
        editTextTodoItem = findViewById(R.id.editTextTodoItem)

        recyclerViewTodoItems.adapter = todoAdapter
        recyclerViewTodoItems.layoutManager = LinearLayoutManager(this)

        buttonAddTodo.setOnClickListener {
            val todoTitle = editTextTodoItem.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                editTextTodoItem.text.clear()
            }
        }
        buttonDeleteAllDone.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}

