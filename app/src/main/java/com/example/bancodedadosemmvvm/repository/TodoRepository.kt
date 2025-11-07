package com.example.bancodedadosemmvvm.repository
import com.example.bancodedadosemmvvm.model.Todo
import com.example.bancodedadosemmvvm.source.TodoDao
interface TodoRepository {
    suspend fun insercaoItem(nome: String, preco : Double, img: String): List<Todo>
    suspend fun obterTodos(): List<Todo>
    suspend fun marcacaoConcluida(todo: Todo): List<Todo>
    suspend fun deletarItem(todo: Todo): List<Todo>
}
class TodoRepositoryImplementacao(private val todoDao: TodoDao) : TodoRepository{
    override suspend fun insercaoItem(nome: String,
                                      preco : Double,
                                      img: String): List<Todo> {
        val todo = Todo(nome = nome, preco = preco, img = img)
        todoDao.addItem(todo)
        return obterTodos()
    }
    override suspend fun obterTodos() = todoDao.obterElementos()
    override suspend fun marcacaoConcluida(todo: Todo): List<Todo> {
        todoDao.marcarComoConcluido(todo.id)
        return obterTodos()
    }

    override suspend fun deletarItem(todo: Todo): List<Todo> {
        todoDao.delItem(todo)
        return obterTodos()
    }
}

