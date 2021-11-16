package com.opedarkgroup.features.admin.mesas.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.buscafuncionarios.BuscaFuncionariosResponse
import com.opedarkgroup.data.models.admin.removefuncionario.RemoveFuncionarioBody
import com.opedarkgroup.data.models.admin.removemesa.RemoveMesaBody
import com.opedarkgroup.data.models.comanda.buscamesas.MesaResponse
import com.opedarkgroup.features.admin.funcionarios.view.AdicionaAlteraFuncionarioActivity
import com.opedarkgroup.features.admin.funcionarios.viewmodel.RemoveFuncionarioViewModel
import com.opedarkgroup.features.admin.mesas.view.adapter.MesasConfigAdapter
import com.opedarkgroup.features.admin.mesas.view.adapter.MesasConfigOnClick
import com.opedarkgroup.features.admin.mesas.viewmodel.BuscaMesasViewModel
import com.opedarkgroup.features.admin.mesas.viewmodel.RemoveMesaViewModel
import kotlinx.android.synthetic.main.activity_configuracoes_funcionarios.*
import kotlinx.android.synthetic.main.activity_confiuracoes_mesas.*

class ConfiuracoesMesasActivity : AppCompatActivity(), MesasConfigOnClick {

    private val list = listOf<MesaResponse>()
    private val adapter = MesasConfigAdapter(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confiuracoes_mesas)


        floatingActionButtonMesas.setOnClickListener {
            startActivity(Intent(this, AdicionaMesaActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        listaMesas()
    }

    private fun listaMesas() {
        val viewModel = ViewModelProviders.of(this).get(BuscaMesasViewModel::class.java)
        mesasConfigRecyclerView.adapter = adapter
        mesasConfigRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.buscaTodasAsMesas()
        viewModel.buscaTodasAsMesasResult.observe(this, Observer {
            adapter.updateList(it)
        })
    }

    override fun onClick(mesaResponse: MesaResponse) {
        mesasDialog(mesaResponse)
    }

    fun mesasDialog(mesaResponse: MesaResponse) {
        val alert: AlertDialog
        val builder = AlertDialog.Builder(this)

        builder.setMessage("Deseja remover a mesa selecionada?")
            .setPositiveButton("Remover",
                DialogInterface.OnClickListener { dialog, id ->

                    val viewModel = ViewModelProviders.of(this).get(RemoveMesaViewModel::class.java)
                    viewModel.removeMesa(RemoveMesaBody(mesaResponse.id_mesa_pk))

                    viewModel.removeMesaResult.observe(this, Observer { funcionarioRemovido ->
                        with(dialog) {
                            if (funcionarioRemovido) { Toast.makeText(applicationContext, "Mesa removido com sucesso!", Toast.LENGTH_SHORT).show()
                                listaMesas()
                                dismiss()
                            } else {
                                Toast.makeText(applicationContext, "Erro ao remover Mesa",
                                    Toast.LENGTH_SHORT).show()
                                dismiss()
                            }
                        }
                    })
                })
        alert = builder.create()
        alert.show()
    }

}