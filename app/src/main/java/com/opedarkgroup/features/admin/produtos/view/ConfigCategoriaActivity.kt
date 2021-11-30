package com.opedarkgroup.features.admin.produtos.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.removeproduto.RemoveProdutoBody
import com.opedarkgroup.data.models.comanda.buscaprodutoporcategoria.CategoriaProduto
import com.opedarkgroup.data.models.comanda.buscaprodutoporcategoria.PedidoPorCategoria
import com.opedarkgroup.features.admin.produtos.viewmodel.RemoveProdutoViewModel
import com.opedarkgroup.features.comandaeletronica.listapedidos.view.adapter.ClickPedidoViewContract
import com.opedarkgroup.features.comandaeletronica.listapedidos.view.adapter.ItensPorCategoriaAdapter
import com.opedarkgroup.features.comandaeletronica.listapedidos.viewmodel.BuscaProdutosViewModel
import kotlinx.android.synthetic.main.activity_config_categoria.*

const val ALTERA_ADICIONA_PRODUTO = "ALTERA_ADICIONA_PRODUTO"
const val NOME_PRODUTO = "NOME_PRODUTO"
const val VALOR_PRODUTO = "VALOR_PRODUTO"
const val ID_PRODUTO = "ID_PRODUTO"

class ConfigCategoriaActivity : AppCompatActivity(), ClickPedidoViewContract {

    private val list = listOf<PedidoPorCategoria>()
    private val adapter = ItensPorCategoriaAdapter(list, this)
    var categoria = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_categoria)

        btnVoltarConfig.setOnClickListener {
            finish()
        }

        categoria = intent.getStringExtra(CATEGORIA_PRODUTOS).toString()

        when (categoria) {
            "Pratos" -> {
                textViewCategoriaTitulo.text = "Pratos"
                listaProdutos()
            }
            "Sobremesa" -> {
                textViewCategoriaTitulo.text = "Sobremesas"
                listaProdutos()
            }
            "Bebidas" -> {
                textViewCategoriaTitulo.text = "Bebidas"
                listaProdutos()
            }
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AdicionaAlteraProdutoActivity::class.java)
            intent.putExtra(ALTERA_ADICIONA_PRODUTO, "Adiciona")
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        listaProdutos()
    }


    fun listaProdutos() {
        val viewModel = ViewModelProviders.of(this).get(BuscaProdutosViewModel::class.java)

        itensPorCategoriaRecyclerView.adapter = adapter
        itensPorCategoriaRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.buscarProdutosPorCategoria(CategoriaProduto(categoria))
        viewModel.listaProdutosResult.observe(this, Observer {
            adapter.updateList(it)
        })

    }

    override fun onClick(produtoId: Int, nomeProduto: String?, valorProduto: Float?) {
        produtosDialog(produtoId, nomeProduto, valorProduto)
    }

    fun navegarParaAlterarPedido(nomeProduto: String?, valorProduto: Float?, produtoId: Int) {
        val intent = Intent(this, AdicionaAlteraProdutoActivity::class.java)
        intent.putExtra(ALTERA_ADICIONA_PRODUTO, "Altera")
        intent.putExtra(NOME_PRODUTO, nomeProduto)
        intent.putExtra(VALOR_PRODUTO, valorProduto)
        intent.putExtra(ID_PRODUTO, produtoId)
        startActivity(intent)
    }

    fun produtosDialog(produtoId: Int, nomeProduto: String?, valorProduto: Float?) {
        val alert: AlertDialog
        val builder = AlertDialog.Builder(this)

        builder.setMessage("Deseja alterar o produto?")
            .setPositiveButton("Alterar",
                DialogInterface.OnClickListener { dialog, id ->
                        navegarParaAlterarPedido(nomeProduto, valorProduto, produtoId)
                })
            .setNegativeButton("Excluir",
                DialogInterface.OnClickListener { dialog, id ->
                    val viewModel = ViewModelProviders.of(this).get(RemoveProdutoViewModel::class.java)
                    viewModel.removeProduto(RemoveProdutoBody(produtoId))

                    viewModel.removeProdutoResult.observe(this, Observer { produtoRemovido ->
                        with(dialog) {
                            if (produtoRemovido) {
                                Toast.makeText(applicationContext, "Produto removido com sucesso!", Toast.LENGTH_SHORT).show()
                                listaProdutos()
                                dismiss()
                            } else {
                                Toast.makeText(applicationContext, "Erro ao remover produto", Toast.LENGTH_SHORT).show()
                                dismiss()
                            }
                        }
                    })
                })

        alert = builder.create()
        alert.show()
    }

    override fun onBackPressed() {
        finish()
    }
}