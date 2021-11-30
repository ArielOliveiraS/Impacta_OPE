package com.opedarkgroup.features.admin.produtos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.adicionaproduto.AdicionaProdutoBody
import com.opedarkgroup.data.models.admin.alteraproduto.AlteraProdutoBody
import com.opedarkgroup.features.admin.produtos.viewmodel.AdicionaProdutoViewModel
import com.opedarkgroup.features.admin.produtos.viewmodel.AlteraProdutoViewModel
import kotlinx.android.synthetic.main.activity_adiciona_altera_produto.*

class AdicionaAlteraProdutoActivity : AppCompatActivity() {

    var nomeProduto = ""
    var valorProduto = 0.0F
    var procedimento = ""
    var idProduto = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiciona_altera_produto)

        procedimento = intent.getStringExtra(ALTERA_ADICIONA_PRODUTO).toString()
        nomeProduto = intent.getStringExtra(NOME_PRODUTO).toString()
        valorProduto = intent.getFloatExtra(VALOR_PRODUTO, 0.0F)
        idProduto = intent.getIntExtra(ID_PRODUTO, -1)



        if (procedimento == "Altera") {
            editTextCategoriaProduto.visibility = View.GONE
            editNomeProduto.setText(nomeProduto, TextView.BufferType.EDITABLE)
            editTextValorProduto.setText(valorProduto.toString(), TextView.BufferType.EDITABLE)

            btnFinalizar.setOnClickListener {
                val viewModel = ViewModelProviders.of(this).get(AlteraProdutoViewModel::class.java)
                val nomeEditText = editNomeProduto.text.toString()
                val valorEditText = editTextValorProduto.text.toString()

                if (!valorEditText.isNullOrEmpty()) {
                    viewModel.alteraProduto(AlteraProdutoBody(idProduto, valorEditText.toDouble(), nomeEditText))
                    viewModel.alteraProdutoResult.observe(this, Observer { produtoAlterado ->
                        if (produtoAlterado) {
                            Toast.makeText(applicationContext, "Produto alterado com sucesso!", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Erro ao alterar produto", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    })
                }
            }
        }

        if (procedimento == "Adiciona") {
            textViewCategoriaTitulo.text = "Adicionar produto"

            btnFinalizar.setOnClickListener {
                val viewModel = ViewModelProviders.of(this).get(AdicionaProdutoViewModel::class.java)

                val nomeEditText = editNomeProduto.text.toString()
                val valorEditText = editTextValorProduto.text.toString()
                val categoriaEditText = editTextCategoriaProduto.text.toString()

                if (!valorEditText.isNullOrEmpty() && !nomeEditText.isNullOrEmpty() && !categoriaEditText.isNullOrEmpty()) {
                    viewModel.adicionaProduto(AdicionaProdutoBody(nomeEditText,categoriaEditText, valorEditText.toFloat()))
                    viewModel.adicionaProdutoResult.observe(this, Observer { produtoAdicionado ->
                        if (produtoAdicionado) {
                            Toast.makeText(applicationContext, "Produto adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Erro ao adicionar produto", Toast.LENGTH_SHORT).show()
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