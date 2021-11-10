package com.opedarkgroup.features.admin.funcionarios.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.buscafuncionarios.BuscaFuncionariosResponse
import com.opedarkgroup.features.admin.funcionarios.view.adapter.FuncionarioOnClick
import com.opedarkgroup.features.admin.funcionarios.view.adapter.FuncionariosAdapter
import com.opedarkgroup.features.admin.viewmodelsoltas.BuscaFuncionariosViewModel
import kotlinx.android.synthetic.main.activity_configuracoes_funcionarios.*

class ConfiguracoesFuncionariosActivity : AppCompatActivity(), FuncionarioOnClick {

    private val list = listOf<BuscaFuncionariosResponse>()
    private val adapter = FuncionariosAdapter(list, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes_funcionarios)

        btnVoltarConfigFuncionarios.setOnClickListener {
            finish()
        }

        val viewModel = ViewModelProviders.of(this).get(BuscaFuncionariosViewModel::class.java)
        funcionariosRecyclerView.adapter = adapter
        funcionariosRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.buscaFuncionarios()
        viewModel.buscaFuncionariosResult.observe(this, Observer {
            adapter.updateList(it)
        })
    }

    override fun onClick(funcionariosResponse: BuscaFuncionariosResponse) {
        Toast.makeText(applicationContext, "botao clicado", Toast.LENGTH_SHORT).show()    }
}