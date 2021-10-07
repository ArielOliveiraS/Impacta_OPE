package com.opedarkgroup.features.listapedidos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.CategoriaProduto
import com.opedarkgroup.data.models.PedidoPorCategoria
import com.opedarkgroup.features.listapedidos.view.adapter.ItensPorCategoriaAdapter
import com.opedarkgroup.features.listapedidos.viewmodel.BuscaProdutosViewModel
import kotlinx.android.synthetic.main.activity_pedidos_por_categoria.*

class PedidosPorCategoriaActivity : AppCompatActivity(){

    private val list = listOf<PedidoPorCategoria>()
    private val adapter = ItensPorCategoriaAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_por_categoria)

        pedidosPorCategoriaRecyclerView.adapter = adapter
        pedidosPorCategoriaRecyclerView.layoutManager = LinearLayoutManager(this)

        val viewModel = ViewModelProviders.of(this).get(BuscaProdutosViewModel::class.java)
        viewModel.buscarProdutosPorCategoria(CategoriaProduto("Bebidas"))
        viewModel.listaProdutosResult.observe(this, Observer {
            adapter.updateList(it)
        })

    }
}