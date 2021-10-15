package com.opedarkgroup.features.pedidosmesaocupada.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.buscapedido.BuscaPedidoBody
import com.opedarkgroup.data.models.buscapedido.BuscaPedidoItemResponse
import com.opedarkgroup.features.comanda.view.adapter.ItensComandaAdapter
import com.opedarkgroup.features.comanda.viewmodel.BuscaPedidoViewModel
import com.opedarkgroup.features.listapedidos.view.ID_MESA
import kotlinx.android.synthetic.main.activity_pedidos.*
import kotlinx.android.synthetic.main.activity_pedidos.numeroMesaTxt
import kotlinx.android.synthetic.main.activity_pedidos_mesa_aberta.*
import kotlinx.android.synthetic.main.activity_visualizar_comanda.*

class PedidosMesaAbertaActivity : AppCompatActivity() {

    private var numeroMesa: Int = 0

    private val list = listOf<BuscaPedidoItemResponse>()
    private val adapter = ItensComandaAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_mesa_aberta)

        numeroMesa = intent.getIntExtra("MESA", -1)
        numeroMesaTxt.text = "Mesa $numeroMesa"

        val viewModel = ViewModelProviders.of(this).get(BuscaPedidoViewModel::class.java)

        recyclerViewPedidosMesaAberta.adapter = adapter
        recyclerViewPedidosMesaAberta.layoutManager = LinearLayoutManager(this)

        viewModel.buscarPedido(BuscaPedidoBody(numeroMesa))

        viewModel.buscarPedidoResult.observe(this, Observer {
            it.itens?.let { listaPedidos ->
                adapter.updateList(listaPedidos)
            }
        })
    }
}