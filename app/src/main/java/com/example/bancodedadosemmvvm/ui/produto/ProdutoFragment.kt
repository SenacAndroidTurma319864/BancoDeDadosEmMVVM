package com.example.bancodedadosemmvvm.ui.produto

import android.app.AlertDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.bancodedadosemmvvm.R
import com.example.bancodedadosemmvvm.ViewModelFactory
import com.example.bancodedadosemmvvm.model.Todo
import com.example.bancodedadosemmvvm.repository.TodoRepository
import com.example.bancodedadosemmvvm.repository.TodoRepositoryImplementacao
import com.example.bancodedadosemmvvm.source.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
class ProdutoFragment : Fragment(), TodoAdapter.AdaptandoLista {
    companion object {
        fun newInstance() = ProdutoFragment()
    }
    private lateinit var mainViewModel: ProdutoViewModel
    private lateinit var adaptando: TodoAdapter
    private lateinit var todoRepositoryImpl: TodoRepository

    lateinit var bancodedados : AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_produto, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iniciarBanco()
        todoRepositoryImpl =
            TodoRepositoryImplementacao(
                bancodedados.todoDao()
            )
        val factory = ViewModelFactory(todoRepositoryImpl)
        mainViewModel = ViewModelProvider(this, factory).
                get(ProdutoViewModel::class.java)
        val fabAdd= view?.findViewById<FloatingActionButton>(R.id.fabAdd)
        configLista()
        observar()
        fabAdd?.setOnClickListener { abrirNovoElemento() }
    }
    private fun iniciarBanco(){
        bancodedados = context.let{
            AppDatabase.invoke(it!!)
        }
    }
    private fun configLista(){
        adaptando = TodoAdapter(mutableListOf(), this,requireContext())
        val rvLista = view?.findViewById<RecyclerView>(R.id.rvLista)
        rvLista?.layoutManager = LinearLayoutManager(context)
        rvLista?.adapter = adaptando
    }
    private fun observar(){
        mainViewModel.obterTodos()
        mainViewModel.observeTodos().observe(viewLifecycleOwner, Observer{
            adaptando.updateList(it as MutableList<Todo>)
        })
        mainViewModel.observeErros().observe(viewLifecycleOwner, Observer{
            if (it != null){
                val txtErro = view?.findViewById<TextView>(R.id.txtErro)
                txtErro?.visibility = VISIBLE
                val rvLista = view?.findViewById<RecyclerView>(R.id.rvLista)
                rvLista?.visibility = GONE
                val fabAdd = view?.findViewById<FloatingActionButton>(R.id.fabAdd)
                fabAdd?.visibility = GONE
            }
        })
    }
    override fun deletarItem(todo: Todo){
        abrirDialogo(todo)
    }
    private fun abrirDialogo(todo: Todo){
        val dialogo = AlertDialog.Builder(requireContext())
        dialogo.setTitle("Removendo elemento")
        dialogo.setMessage("Você deseja remover esse item?")
        dialogo.setPositiveButton("Deletar"){_,_ -> mainViewModel.deletarItem(todo)}
        dialogo.setNegativeButton("Cancelar"){_,_ -> null}
        dialogo.show()
    }
    private fun abrirNovoElemento(){
        val dialogo = AlertDialog.Builder(requireContext())
        dialogo.setTitle("Adicionar novo produto")

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(32,24,32,0)

        val nome = EditText(context)
        nome.hint = "Digite o nome do produto"
        nome.layoutParams = lp
        layout.addView(nome)

        val imagem = EditText(context)
        imagem.hint = "Digite o link da imagem"
        imagem.layoutParams = lp
        layout.addView(imagem)

        val preco = EditText(context)
        preco.hint = "Digite o preço do produto"
        preco.inputType = android.text.InputType.TYPE_CLASS_NUMBER or
                android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        preco.layoutParams = lp
        layout.addView(preco)
        dialogo.setView(layout)
        dialogo.setPositiveButton("Salvar"){_,_, ->
            val nomeProduto = nome.text.toString()
            val imagemProduto = imagem.text.toString()
            val precoProduto = preco.text.toString().toDoubleOrNull() ?: 0.0
            mainViewModel.InserirItem(nomeProduto,precoProduto,imagemProduto)
        }
        dialogo.setNegativeButton("Cancelar", null)
        dialogo.show()
    }

    override fun marcarComoConcluido(todo: Todo) {
        mainViewModel.atualizarItem(todo)
    }
}