package com.opedarkgroup.features.admin.funcionarios.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.buscafuncionarios.BuscaFuncionariosResponse
import com.opedarkgroup.data.models.admin.removefuncionario.RemoveFuncionarioBody
import com.opedarkgroup.features.admin.funcionarios.view.adapter.FuncionarioOnClick
import com.opedarkgroup.features.admin.funcionarios.view.adapter.FuncionariosAdapter
import com.opedarkgroup.features.admin.funcionarios.viewmodel.BuscaFuncionariosViewModel
import com.opedarkgroup.features.admin.funcionarios.viewmodel.RemoveFuncionarioViewModel
import com.opedarkgroup.features.admin.produtos.view.*
import kotlinx.android.synthetic.main.activity_configuracoes_funcionarios.*

const val ALTERA_ADICIONA_FUNCIONARIO = "ALTERA_ADICIONA_FUNCIONARIO"
const val NOME = "NOME"
const val USER = "USER"
const val EMAIL = "EMAIL"

class ConfiguracoesFuncionariosActivity : AppCompatActivity(), FuncionarioOnClick {

    private val list = listOf<BuscaFuncionariosResponse>()
    private val adapter = FuncionariosAdapter(list, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes_funcionarios)
        //listaFuncionarios()
        btnVoltarConfigFuncionarios.setOnClickListener {
            finish()
        }
        floatingActionButtonFuncionarios.setOnClickListener {
            startActivity(Intent(this, AdicionaAlteraFuncionarioActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        listaFuncionarios()
    }

    override fun onClick(funcionariosResponse: BuscaFuncionariosResponse) {
        produtosDialog(funcionariosResponse)
    }

    private fun listaFuncionarios() {
        val viewModel = ViewModelProviders.of(this).get(BuscaFuncionariosViewModel::class.java)
        funcionariosRecyclerView.adapter = adapter
        funcionariosRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.buscaFuncionarios()
        viewModel.buscaFuncionariosResult.observe(this, Observer {
            adapter.updateList(it)
        })
    }

    fun navegarParaAlterarFuncionario(nome: String?, user: String?, email: String?) {
        val intent = Intent(this, AdicionaAlteraFuncionarioActivity::class.java)
        intent.putExtra(ALTERA_ADICIONA_FUNCIONARIO, "Altera")
        intent.putExtra(NOME, nome)
        intent.putExtra(USER, user)
        intent.putExtra(EMAIL, email)
        startActivity(intent)
    }

    fun produtosDialog(funcionariosResponse: BuscaFuncionariosResponse) {
        val alert: AlertDialog
        val builder = AlertDialog.Builder(this)

        builder.setMessage("Deseja alterar os dados do funcionário?")
            .setPositiveButton("Alterar",
                DialogInterface.OnClickListener { dialog, id ->
                    with(funcionariosResponse) {
                        navegarParaAlterarFuncionario(this.nome, this.usuario, this.email)
                    }
                })
            .setNegativeButton("Excluir",
                DialogInterface.OnClickListener { dialog, id ->

                    val viewModel = ViewModelProviders.of(this).get(RemoveFuncionarioViewModel::class.java)
                    viewModel.removeFuncionario(RemoveFuncionarioBody(funcionariosResponse.id_funcionario_pk))

                    viewModel.removeFuncionarioResult.observe(this, Observer { funcionarioRemovido ->
                        with(dialog) {
                            if (funcionarioRemovido) { Toast.makeText(applicationContext, "Funcionario removido com sucesso!", Toast.LENGTH_SHORT).show()
                                listaFuncionarios()
                                dismiss()
                            } else {
                                Toast.makeText(applicationContext, "Erro ao remover funcionario",Toast.LENGTH_SHORT).show()
                                dismiss()
                            }
                        }
                    })
                })

        alert = builder.create()
        alert.show()
    }
}