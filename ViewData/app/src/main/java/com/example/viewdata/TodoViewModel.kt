package com.example.viewdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private val _todos = MutableLiveData<ArrayList<Todo>>()

    val todos: LiveData<ArrayList<Todo>>
        get() = _todos

    init {
        _todos.value = arrayListOf(
            Todo(1, "mandi"),
            Todo(2, "makan")
        )
    }
}