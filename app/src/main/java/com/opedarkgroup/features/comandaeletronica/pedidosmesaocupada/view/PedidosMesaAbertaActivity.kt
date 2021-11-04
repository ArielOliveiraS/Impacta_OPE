package com.opedarkgroup.features.comandaeletronica.pedidosmesaocupada.view

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
import com.opedarkgroup.data.models.comanda.encerrarpedido.EncerrarPedidoBody
import com.opedarkgroup.features.comandaeletronica.comanda.view.adapter.ItensComandaAdapter
import com.opedarkgroup.features.comandaeletronica.comanda.viewmodel.BuscaPedidoViewModel
import com.opedarkgroup.features.comandaeletronica.home.view.MainActivity
import com.opedarkgroup.features.comandaeletronica.listapedidos.view.PedidosActivity
import com.opedarkgroup.features.comandaeletronica.pedidosmesaocupada.viewmodel.EncerrarPedidoViewModel
import kotlinx.android.synthetic.main.activity_pedidos.numeroMesaTxt
import kotlinx.android.synthetic.main.activity_pedidos_mesa_aberta.*

class PedidosMesaAbertaActivity : AppCompatActivity() {

    private var numeroMesa: Int = 0

    private val list = listOf<BuscaPedidoItemResponse>()
    private val adapter = ItensComandaAdapter(list)
    private var idPedido = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_mesa_aberta)

        configuraBotoes()

        numeroMesa = intent.getIntExtra("MESA", -1)
        numeroMesaTxt.text = "Mesa $numeroMesa"

        val viewModel = ViewModelProviders.of(this).get(BuscaPedidoViewModel::class.java)

        recyclerViewPedidosMesaAberta.adapter = adapter
        recyclerViewPedidosMesaAberta.layoutManager = LinearLayoutManager(this)

        viewModel.buscarPedido(BuscaPedidoBody(numeroMesa))

        viewModel.buscarPedidoResult.observe(this, Observer {
            idPedido = it.pedidoId
            it.pedidoResponse?.itens?.let { listaPedidos ->
                adapter.updateList(listaPedidos)
            }
        })
    }

    private fun configuraBotoes() {
        btnAdicionarMaisItens.setOnClickListener {
            val intent = Intent(this, PedidosActivity::class.java)
            var bundle = Bundle()

            bundle.putInt("MESA", numeroMesa)
            bundle.putInt("ID_PEDIDO", idPedido)
            intent.putExtras(bundle)

            startActivity(intent)
        }

        btnConcluirPedido.setOnClickListener {
            val viewModelEncerrarPedido = ViewModelProviders.of(this).get(EncerrarPedidoViewModel::class.java)

            viewModelEncerrarPedido.encerrarPedido(EncerrarPedidoBody(idPedido))
            viewModelEncerrarPedido.pedidoEncerradoResult  .observe(this, Observer {
                if (it){
                    Toast.makeText(applicationContext, "Pedido finalizado", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "Erro ao finalizado pedido", Toast.LENGTH_LONG).show()
                }
            })
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}