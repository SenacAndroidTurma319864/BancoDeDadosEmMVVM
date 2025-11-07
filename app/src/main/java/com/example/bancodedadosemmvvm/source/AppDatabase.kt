package com.example.bancodedadosemmvvm.source
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bancodedadosemmvvm.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
    companion object {
        @Volatile
        private var instancia: AppDatabase? = null
        private val BLOQUEIO = Any()

        operator fun invoke(context: Context)
            = instancia ?: synchronized(BLOQUEIO){
                instancia ?: construirBD(context).also{
                    instancia = it
                }
        }

        private fun construirBD(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, "Todo.db"
            ).build()
    }
}