package com.opedarkgroup.features.admin.configuracoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.opedarkgroup.R
import com.opedarkgroup.features.admin.funcionarios.ConfiguracoesFuncionariosActivity
import com.opedarkgroup.features.admin.mesas.ConfiuracoesMesasActivity
import com.opedarkgroup.features.admin.produtos.ConfiguracoesProdutosActivity
import kotlinx.android.synthetic.main.activity_configuracoes.*

class ConfiguracoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)

        btnVoltarConfig.setOnClickListener {
            finish()
        }

        btnGerenciarFuncionarios.setOnClickListener {
            startActivity(Intent(this, ConfiguracoesFuncionariosActivity::class.java))
        }

        btnGerenciarProdutos.setOnClickListener {
            startActivity(Intent(this, ConfiguracoesProdutosActivity::class.java))
        }

        btnGerenciarMesas.setOnClickListener {
            startActivity(Intent(this, ConfiuracoesMesasActivity::class.java))
        }
    }

}