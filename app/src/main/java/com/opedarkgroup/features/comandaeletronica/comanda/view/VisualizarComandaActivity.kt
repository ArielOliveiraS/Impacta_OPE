package com.opedarkgroup.features.comandaeletronica.comanda.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.comanda.buscapedido.BuscaPedidoBody
import com.opedarkgroup.data.models.comanda.buscapedido.BuscaPedidoItemResponse
import com.opedarkgroup.data.models.comanda.enviarpedidos.EnviarPedidoBody
import com.opedarkgroup.features.comandaeletronica.comanda.view.adapter.ItensComandaAdapter
import com.opedarkgroup.features.comandaeletronica.comanda.viewmodel.BuscaPedidoViewModel
import com.opedarkgroup.features.comandaeletronica.comanda.viewmodel.EnviarPedidosViewModel
import com.opedarkgroup.features.comandaeletronica.home.view.MainActivity
import com.opedarkgroup.features.comandaeletronica.listapedidos.view.ID_MESA
import kotlinx.android.synthetic.main.activity_visualizar_comanda.*

class VisualizarComandaActivity : AppCompatActivity() {

    private val list = listOf<BuscaPedidoItemResponse>()
    private val adapter = ItensComandaAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizar_comanda)
        listarPedidos()

        btnEnviarComanda.setOnClickListener {
            enviarPedido()
        }
    }

    private fun listarPedidos() {
        val viewModel = ViewModelProviders.of(this).get(BuscaPedidoViewModel::class.java)

        recyclerViewPedidosAdicionados.adapter = adapter
        recyclerViewPedidosAdicionados.layoutManager = LinearLayoutManager(this)

        val idMesa: Int = intent.getIntExtra(ID_MESA, -1)
        numeroMesaTxt.text = "Mesa $idMesa"

        viewModel.buscarPedido(BuscaPedidoBody(idMesa))

        viewModel.buscarPedidoResult.observe(this, Observer {
            it.pedidoResponse?.itens?.let { listaPedidos ->
                adapter.updateList(listaPedidos)
            }
//            it.itens?.let { listaPedidos ->
//                adapter.updateList(listaPedidos)
//            }
        })
    }

    private fun enviarPedido() {
        val viewModelEnviarPedidos =
            ViewModelProviders.of(this).get(EnviarPedidosViewModel::class.java)

        val idPedido = intent.getIntExtra("ID_PEDIDO2", -1)
        val pedido = EnviarPedidoBody(idPedido)

        viewModelEnviarPedidos.enviarPedido(pedido)
        viewModelEnviarPedidos.enviarPedidoResult.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Pedido enviado com sucesso", Toast.LENGTH_SHORT)
                    .show()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Erro ao enviar pedido", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}