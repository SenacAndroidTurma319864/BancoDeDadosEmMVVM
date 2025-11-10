package com.example.bancodedadosemmvvm.ui.produto

import android.content.Context
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
    private val listando: AdapterListener,
    private val contexto : Context
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(){

}