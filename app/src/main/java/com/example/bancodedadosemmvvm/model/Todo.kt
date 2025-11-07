package com.example.bancodedadosemmvvm.model
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity // Entidade
data class Todo (
    @PrimaryKey(autoGenerate = true) // Marco que o proximo dado eh identificador
    val id: Int = 0, // Esse será o identificador do elemento
    val nome: String, // Nome do elemento
    val img: String, // Img desse elemento
    val preco: Double, // Preço desse elemento
    val completou: Boolean = false, // Verifica se foi carregado, colocando como padrão false
)