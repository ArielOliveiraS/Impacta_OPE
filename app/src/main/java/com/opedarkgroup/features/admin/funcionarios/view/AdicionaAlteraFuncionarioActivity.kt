package com.opedarkgroup.features.admin.funcionarios.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.criafuncionario.CriaFuncionarioBody
import com.opedarkgroup.features.admin.funcionarios.viewmodel.CriaFuncionarioViewModel
import kotlinx.android.synthetic.main.activity_adiciona_altera_funcionario.*
import kotlinx.android.synthetic.main.activity_adiciona_altera_funcionario.btnCancelar

class AdicionaAlteraFuncionarioActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiciona_altera_funcionario)
        val viewModel = ViewModelProviders.of(this).get(CriaFuncionarioViewModel::class.java)



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

        btnCancelar.setOnClickListener {
            finish()
        }
    }

}