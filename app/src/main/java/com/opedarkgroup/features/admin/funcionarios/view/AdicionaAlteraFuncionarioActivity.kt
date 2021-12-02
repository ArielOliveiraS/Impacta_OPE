package com.opedarkgroup.features.admin.funcionarios.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.alterafuncionario.AlteraFuncionarioBody
import com.opedarkgroup.data.models.admin.criafuncionario.CriaFuncionarioBody
import com.opedarkgroup.features.admin.funcionarios.viewmodel.AlteraFuncionarioViewModel
import com.opedarkgroup.features.admin.funcionarios.viewmodel.CriaFuncionarioViewModel
import com.opedarkgroup.features.admin.produtos.view.ALTERA_ADICIONA_PRODUTO
import com.opedarkgroup.features.admin.produtos.view.ID_PRODUTO
import com.opedarkgroup.features.admin.produtos.view.NOME_PRODUTO
import com.opedarkgroup.features.admin.produtos.view.VALOR_PRODUTO
import com.opedarkgroup.features.admin.produtos.viewmodel.AlteraProdutoViewModel
import kotlinx.android.synthetic.main.activity_adiciona_altera_funcionario.*
import kotlinx.android.synthetic.main.activity_adiciona_altera_funcionario.btnCancelar
import kotlinx.android.synthetic.main.activity_adiciona_altera_produto.*

class AdicionaAlteraFuncionarioActivity : AppCompatActivity() {

    var nome = ""
    var user = ""
    var procedimento = ""
    var email = ""
    var idFuncionario = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiciona_altera_funcionario)
        val viewModel = ViewModelProviders.of(this).get(CriaFuncionarioViewModel::class.java)

        btnVoltarAdicionaAlteraFuncionario.setOnClickListener { finish() }

        procedimento = intent.getStringExtra(ALTERA_ADICIONA_FUNCIONARIO).toString()
        nome = intent.getStringExtra(NOME).toString()
        user = intent.getStringExtra(USER).toString()
        email = intent.getStringExtra(EMAIL).toString()
        idFuncionario = intent.getIntExtra(ID_FUNCIONARIO, -1)

        if (procedimento == "Altera") {
            editNomeFuncionario.setText(nome, TextView.BufferType.EDITABLE)
            editTextEmailFuncionario.setText(email, TextView.BufferType.EDITABLE)
            editTextUser.setText(user, TextView.BufferType.EDITABLE)

            btnFinalizarFuncionario.setOnClickListener {
                val viewModel = ViewModelProviders.of(this).get(AlteraFuncionarioViewModel::class.java)
                val nome = editNomeFuncionario.text.toString()
                val email = editTextEmailFuncionario.text.toString()

                viewModel.alteraFuncionario(AlteraFuncionarioBody(idFuncionario, nome, email))
                viewModel.alteraFuncionarioResult.observe(this, Observer {
                    if (it) {
                        Toast.makeText(applicationContext, "Funcionario alterado com sucesso!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Erro ao alterar funcionario", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                })
            }
        }

        if (procedimento == "Adiciona") {
            btnFinalizarFuncionario.setOnClickListener {

                val nome = editNomeFuncionario.text.toString()
                val email = editTextEmailFuncionario.text.toString()

                if (!nome.isNullOrEmpty() && !email.isNullOrEmpty()) {
                    viewModel.criaFuncionario(CriaFuncionarioBody(nome, email))
                    viewModel.criaFuncionarioResult.observe(this, Observer {
                        if (it) {
                            Toast.makeText(applicationContext, "Funcionario adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Erro ao adicionar funcionario", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    })
                }
            }
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}