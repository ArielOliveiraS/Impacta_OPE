package com.opedarkgroup.features.admin.configuracoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.opedarkgroup.R
import com.opedarkgroup.features.admin.funcionarios.view.ConfiguracoesFuncionariosActivity
import com.opedarkgroup.features.admin.mesas.view.ConfiuracoesMesasActivity
import com.opedarkgroup.features.admin.produtos.view.ConfiguracoesProdutosActivity
import kotlinx.android.synthetic.main.activity_configuracoes.*
import kotlinx.android.synthetic.main.activity_main.*

class ConfiguracoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)

        btnVoltarConfig.setOnClickListener {
            finish()
        }

        btnGerenciarFuncionarios.setOnClickListener {
            it.isClickable = false
            startActivity(Intent(this, ConfiguracoesFuncionariosActivity::class.java))
        }

        btnGerenciarProdutos.setOnClickListener {
            it.isClickable = false
            startActivity(Intent(this, ConfiguracoesProdutosActivity::class.java))
        }

        btnGerenciarMesas.setOnClickListener {
            it.isClickable = false
            startActivity(Intent(this, ConfiuracoesMesasActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        btnGerenciarFuncionarios.isClickable = true
        btnGerenciarProdutos.isClickable = true
        btnGerenciarMesas.isClickable = true
    }

    override fun onBackPressed() {
        finish()
    }
}