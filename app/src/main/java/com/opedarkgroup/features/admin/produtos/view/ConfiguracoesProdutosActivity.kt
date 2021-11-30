package com.opedarkgroup.features.admin.produtos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.opedarkgroup.R
import kotlinx.android.synthetic.main.activity_configuracoes_produtos.*

const val CATEGORIA_PRODUTOS = "CATEGORIA_PRODUTOS"

class ConfiguracoesProdutosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes_produtos)

        btnVoltarConfigProdutos.setOnClickListener {
            finish()
        }

        btnGerenciarPratos.setOnClickListener {
            val intent = Intent(this, ConfigCategoriaActivity::class.java)
            intent.putExtra(CATEGORIA_PRODUTOS, "Pratos")
            startActivity(intent)
        }

        btnGerenciarSobremesas.setOnClickListener {
            val intent = Intent(this, ConfigCategoriaActivity::class.java)
            intent.putExtra(CATEGORIA_PRODUTOS, "Sobremesa")
            startActivity(intent)
        }

        btnGerenciarBebidas.setOnClickListener {
            val intent = Intent(this, ConfigCategoriaActivity::class.java)
            intent.putExtra(CATEGORIA_PRODUTOS, "Bebidas")
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}