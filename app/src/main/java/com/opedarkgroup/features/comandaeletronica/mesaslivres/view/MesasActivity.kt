package com.opedarkgroup.features.comandaeletronica.mesaslivres.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.comanda.buscamesas.MesaResponse
import com.opedarkgroup.data.models.comanda.criapedido.CriaPedidoBody
import com.opedarkgroup.features.comandaeletronica.listapedidos.view.PedidosActivity
import com.opedarkgroup.features.comandaeletronica.mesasocupadas.view.adapter.ClickViewContract
import com.opedarkgroup.features.comandaeletronica.mesaslivres.view.adapter.MesasAdapter
import com.opedarkgroup.features.comandaeletronica.mesaslivres.viewmodel.BuscaMesasLivresViewModel
import com.opedarkgroup.features.comandaeletronica.mesaslivres.viewmodel.CriaPedidoViewModel
import kotlinx.android.synthetic.main.activity_mesas.*

class MesasActivity : AppCompatActivity(), ClickViewContract {

    private val list = listOf<MesaResponse>()
    lateinit var criaPedidoViewModel: CriaPedidoViewModel

    private val adapter = MesasAdapter(list, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        btnMesaVoltar.setOnClickListener {
            finish()
        }

        val viewModel = ViewModelProviders.of(this).get(BuscaMesasLivresViewModel::class.java)
        recyclerViewMesas.adapter = adapter
        recyclerViewMesas.layoutManager = GridLayoutManager(this, 3)

        viewModel.buscarMesasLivres()

        viewModel.buscarMesasLivresResult.observe(this, Observer {
            adapter.updateList(it)
        })
    }

    override fun onClick(mesa: Int) {
        criaPedidoViewModel = ViewModelProviders.of(this).get(CriaPedidoViewModel::class.java)

        criaPedidoViewModel.criaPedido(CriaPedidoBody(mesa, 1))
        criaPedidoViewModel.criaPedidoResult.observe(this, Observer {
            val intent = Intent(this, PedidosActivity::class.java)
            val bundle = Bundle()

            bundle.putInt("MESA", mesa)
            bundle.putInt("ID_PEDIDO", it)
            intent.putExtras(bundle)

            startActivity(intent)
        })
    }
}