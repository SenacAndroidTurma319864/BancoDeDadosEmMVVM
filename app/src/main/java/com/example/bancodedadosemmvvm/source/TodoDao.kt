package com.example.bancodedadosemmvvm.source
import androidx.room.*
import com.example.bancodedadosemmvvm.model.Todo
interface TodoDao {
    @Insert
    suspend fun addItem(todo: Todo)

    @Query("SELECT * FROM Todo ORDER BY completou, id")
    suspend fun obterElementos(): List<Todo>

    @Query("UPDATE Todo SET completou = 1 WHERE id = id")
    suspend fun marcarComoConcluido(id: Int)

    @Delete
    suspend fun delItem(todo: Todo)
}