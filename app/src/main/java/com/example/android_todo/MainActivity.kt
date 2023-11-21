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

    private fun createTestTodos(): MutableList<Todo> {
        val itemsCount: Int = 25
        val testTodos = mutableListOf<Todo>()

        val veryLongTodo: Todo = Todo("veryyyyyyyyyyyyyyyyyyyy long titleeeeeeeeeeeeeeeeeeee")
        testTodos.add(veryLongTodo)

        for (i in 0 until itemsCount) {
            val title = "test${i + 1}"
            val todo = Todo(title)
            testTodos.add(todo)
        }
        return testTodos
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val testTodos = createTestTodos()
        todoAdapter = TodoAdapter(testTodos)

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

