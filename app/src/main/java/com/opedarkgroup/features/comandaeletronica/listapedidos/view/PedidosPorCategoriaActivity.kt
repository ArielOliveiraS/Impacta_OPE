package com.opedarkgroup.features.comandaeletronica.listapedidos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.comanda.buscaprodutoporcategoria.CategoriaProduto
import com.opedarkgroup.data.models.comanda.ItemPedido
import com.opedarkgroup.data.models.comanda.buscaprodutoporcategoria.PedidoPorCategoria
import com.opedarkgroup.features.comandaeletronica.listapedidos.view.adapter.ClickPedidoViewContract
import com.opedarkgroup.features.comandaeletronica.listapedidos.view.adapter.ItensPorCategoriaAdapter
import com.opedarkgroup.features.comandaeletronica.listapedidos.viewmodel.AdicionaItemViewModel
import com.opedarkgroup.features.comandaeletronica.listapedidos.viewmodel.BuscaProdutosViewModel
import kotlinx.android.synthetic.main.activity_pedidos_por_categoria.*


class PedidosPorCategoriaActivity : AppCompatActivity(), ClickPedidoViewContract {

    private val list = listOf<PedidoPorCategoria>()
    private val adapter = ItensPorCategoriaAdapter(list, this)
    var listaPedido = arrayListOf<ItemPedido>()
    private lateinit var viewModelAdicionaItem: AdicionaItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_por_categoria)

        pedidosPorCategoriaRecyclerView.adapter = adapter
        pedidosPorCategoriaRecyclerView.layoutManager = LinearLayoutManager(this)

        val categoria: String = intent.getStringExtra(CATEGORIA_PEDIDO).toString()

        val viewModel = ViewModelProviders.of(this).get(BuscaProdutosViewModel::class.java)
        viewModelAdicionaItem = ViewModelProviders.of(this).get(AdicionaItemViewModel::class.java)

        viewModel.buscarProdutosPorCategoria(CategoriaProduto(categoria))

        viewModel.listaProdutosResult.observe(this, Observer {
            adapter.updateList(it)
        })

        btnadicionarPedidos.setOnClickListener {
            finish()
        }
    }

    override fun onClick(produtoId: Int) {
        val idPedido = intent.getIntExtra("ID_PEDIDO2", -1)
        val itemPedido = ItemPedido(idPedido, produtoId, 1)
        viewModelAdicionaItem.adicionarItem(itemPedido)
        viewModelAdicionaItem.adicionaPedidoResult.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Item adicionado", Toast.LENGTH_LONG).show()
               // finish()
            } else {
                Toast.makeText(applicationContext, "Erro ao adicionar item", Toast.LENGTH_LONG).show()
                //finish()
            }
        })
    }
}