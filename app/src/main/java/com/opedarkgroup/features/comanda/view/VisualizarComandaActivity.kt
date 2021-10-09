package com.opedarkgroup.features.comanda.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.opedarkgroup.R
import com.opedarkgroup.data.models.BuscaPedidoBody
import com.opedarkgroup.data.models.PedidoPorCategoria
import com.opedarkgroup.features.comanda.view.adapter.ItensComandaAdapter
import com.opedarkgroup.features.comanda.viewmodel.BuscaPedidoViewModel
import com.opedarkgroup.features.listapedidos.view.ID_MESA
import kotlinx.android.synthetic.main.activity_visualizar_comanda.*

class VisualizarComandaActivity : AppCompatActivity() {

    private val list = listOf<PedidoPorCategoria>()
    private val adapter = ItensComandaAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizar_comanda)

        val viewModel = ViewModelProviders.of(this).get(BuscaPedidoViewModel::class.java)

//        recyclerViewPedidosAdicionados.adapter = adapter
//        recyclerViewPedidosAdicionados.layoutManager = LinearLayoutManager(this)

        val idMesa: Int = intent.getIntExtra(ID_MESA, -1)

        viewModel.buscarPedido(BuscaPedidoBody(idMesa))

        viewModel.buscarPedidoResult.observe(this, Observer {
            numeroMesaTxt.text = it.id_pedido_pk.toString()
//            it.itens?.let { listaPedidos ->
//                adapter.updateList(listaPedidos)
//            }
        })
    }
}