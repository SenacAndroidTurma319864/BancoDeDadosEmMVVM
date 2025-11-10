package com.example.bancodedadosemmvvm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bancodedadosemmvvm.repository.TodoRepository

class ViewModelFactory (private val todoRepository: TodoRepository) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TodoRepository::class.java).
        newInstance(todoRepository)
    }
}