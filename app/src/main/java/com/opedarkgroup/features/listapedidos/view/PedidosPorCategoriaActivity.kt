package com.opedarkgroup.features.listapedidos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.CategoriaProduto
import com.opedarkgroup.data.models.ItemPedido
import com.opedarkgroup.data.models.PedidoPorCategoria
import com.opedarkgroup.data.models.PedidoResponse
import com.opedarkgroup.features.listapedidos.view.adapter.ClickPedidoViewContract
import com.opedarkgroup.features.listapedidos.view.adapter.ItensPorCategoriaAdapter
import com.opedarkgroup.features.listapedidos.viewmodel.AdicionaItemViewModel
import com.opedarkgroup.features.listapedidos.viewmodel.AdicionaPedidoViewModel
import com.opedarkgroup.features.listapedidos.viewmodel.BuscaProdutosViewModel
import kotlinx.android.synthetic.main.activity_pedidos_por_categoria.*

class PedidosPorCategoriaActivity : AppCompatActivity(), ClickPedidoViewContract {

    private val list = listOf<PedidoPorCategoria>()
    private val adapter = ItensPorCategoriaAdapter(list, this)
    var listaPedido = arrayListOf<ItemPedido>()
    private lateinit var viewModelAdicionaPedido: AdicionaPedidoViewModel
    private lateinit var viewModelAdicionaItem: AdicionaItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_por_categoria)

        pedidosPorCategoriaRecyclerView.adapter = adapter
        pedidosPorCategoriaRecyclerView.layoutManager = LinearLayoutManager(this)

        val categoria: String = intent.getStringExtra(CATEGORIA_PEDIDO).toString()

        val viewModel = ViewModelProviders.of(this).get(BuscaProdutosViewModel::class.java)
        viewModelAdicionaPedido = ViewModelProviders.of(this).get(AdicionaPedidoViewModel::class.java)
        viewModelAdicionaItem = ViewModelProviders.of(this).get(AdicionaItemViewModel::class.java)

        viewModel.buscarProdutosPorCategoria(CategoriaProduto(categoria))

        viewModel.listaProdutosResult.observe(this, Observer {
            adapter.updateList(it)
        })
    }

    override fun onClick(produtoId: Int) {
//        val idMesa: Int = intent.getIntExtra(ID_MESA, -1)
//
        val itemPedido = ItemPedido(13, produtoId, 1)
//        listaPedido.add(itemPedido)
//        val pedido = PedidoResponse(idMesa, 0, listaPedido)
//

        viewModelAdicionaItem.adicionarPedido(itemPedido)

        viewModelAdicionaItem.adicionaPedidoResult.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Pedido adicionado", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(applicationContext, "Erro ao adicionar pedido", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }
}