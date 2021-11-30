package com.opedarkgroup.features.admin.mesas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.adicionamesa.AdicionaMesaBody
import com.opedarkgroup.data.models.admin.criafuncionario.CriaFuncionarioBody
import com.opedarkgroup.features.admin.funcionarios.viewmodel.CriaFuncionarioViewModel
import com.opedarkgroup.features.admin.mesas.viewmodel.AdicionaMesaViewModel
import kotlinx.android.synthetic.main.activity_adiciona_altera_funcionario.*
import kotlinx.android.synthetic.main.activity_adiciona_altera_funcionario.btnFinalizarFuncionario
import kotlinx.android.synthetic.main.activity_adiciona_mesa.*

class AdicionaMesaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiciona_mesa)

        val viewModel = ViewModelProviders.of(this).get(AdicionaMesaViewModel::class.java)
        btnFinalizarMesa.setOnClickListener {

            val qtdeMesa = editTextQtdeMesa.text.toString()

            if (!qtdeMesa.isNullOrEmpty()) {
                viewModel.adicionaMesa(AdicionaMesaBody(qtdeMesa.toInt()))

                viewModel.adicionaMesaResult.observe(this, Observer {
                    if (it) {
                        Toast.makeText(applicationContext, "Mesa adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Erro ao adicionar mesa", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                })
            }
        }

        btnCancelarMesa.setOnClickListener {
            finish()
        }

    }

    override fun onBackPressed() {
        finish()
    }
}