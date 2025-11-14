package com.example.bancodedadosemmvvm.ui.produto

import android.content.Context
import android.provider.DocumentsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bancodedadosemmvvm.R
import com.example.bancodedadosemmvvm.model.Todo

class TodoAdapter(
    private val itemlista: MutableList<Todo>,
    private val listando: AdaptandoLista,
    private val contexto : Context
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(){
    override fun getItemCount() = itemlista.size
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ViewHolder {
        return ViewHolder(parent.
        inflate(R.layout.item_a_ser_listado))
    }
    override fun onBindViewHolder(
        holder: TodoAdapter.ViewHolder,
        position: Int) {
        holder.bindViewHolder(itemlista[position])
    }
    fun updateList(itemlista: MutableList<Todo>){
        this.itemlista.clear()
        this.itemlista.addAll(itemlista)
        notifyDataSetChanged()
    }
    interface AdaptandoLista{
        fun marcarComoConcluido(todo: Todo)
        fun deletarItem(todo: Todo)
    }
    inner class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
                private lateinit var item: Todo
                fun bindViewHolder(item: Todo){
                    this.item = item
            val txtTarefa = itemView.findViewById<TextView>(R.id.txtTarefa)
                    txtTarefa.text = item.nome
                    configTodosCompletos(item)
                val img = itemView.findViewById<ImageView>(R.id.imgCompletou)
                img.setOnClickListener { listando.marcarComoConcluido(item) }
                    //Criar um botão remover
                val elemento = itemView.findViewById<LinearLayout>(R.id.item_lista_controlador)
                    elemento.setOnClickListener {
                        listando.deletarItem(item)
                        return@setOnClickListener
                    }
                }
        private fun configTodosCompletos(todo: Todo){
                if (todo.completou){
                    DrawableCompat.setTint(
                        DrawableCompat.wrap(itemView.
                            findViewById<ImageView>(R.id.imgCompletou).
                                drawable),
                        ContextCompat.getColor(contexto,R.color.black)
                    )
                }
                else{
                    DrawableCompat.setTint(
                        DrawableCompat.wrap(itemView.
                        findViewById<ImageView>(R.id.imgCompletou).
                        drawable),
                        ContextCompat.getColor(contexto,R.color.teal_200)
                    )
                }
            }
        }
}
fun ViewGroup.inflate(@LayoutRes layoutRes: Int,
                      attachToRoot: Boolean = false): View{
    return LayoutInflater.from(context).inflate(layoutRes,
        this,
        attachToRoot)
}