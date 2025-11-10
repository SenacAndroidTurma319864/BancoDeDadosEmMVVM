package com.example.bancodedadosemmvvm.ui.produto

import androidx.lifecycle.*
import com.example.bancodedadosemmvvm.model.Todo
import com.example.bancodedadosemmvvm.repository.TodoRepository
import kotlinx.coroutines.launch

class ProdutoViewModel(private val todoRepository: TodoRepository):
    ViewModel(){
    private val _todos = MutableLiveData<List<Todo>>()
    fun observeTodos(): LiveData<List<Todo>> = _todos
    private val _erros = MutableLiveData<String>()
    fun observeErros(): LiveData<String> = _erros
    fun InserirItem(nome: String, preco: Double, img: String){
        viewModelScope.launch {
            try{
                val resultado = todoRepository.insercaoItem(nome,preco,img)
                _todos.value = resultado
            } catch (e: Exception){
                _erros.value = e.message
            }
        }
    }
    fun obterTodos(){
        viewModelScope.launch {
            try{
                _todos.value = todoRepository.obterTodos()
            } catch (e: Exception){
                _erros.value = e.message
            }
        }
    }
    fun deletarItem(todo: Todo){
        viewModelScope.launch {
            try{
                _todos.value = todoRepository.deletarItem(todo)
            } catch (e: Exception){
                _erros.value = e.message
            }
        }
    }
    fun atualizarItem(todo: Todo){
        viewModelScope.launch {
            try {
                _todos.value = todoRepository.marcacaoConcluida(todo)

            } catch (e: Exception) {
                _erros.value = e.message
            }
        }
    }
}