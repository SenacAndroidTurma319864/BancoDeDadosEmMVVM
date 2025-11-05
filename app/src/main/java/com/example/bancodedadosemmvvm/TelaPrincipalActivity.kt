package com.example.bancodedadosemmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancodedadosemmvvm.ui.produto.ProdutoFragment

class TelaPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_principal)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProdutoFragment.newInstance())
                .commitNow()
        }
    }
}