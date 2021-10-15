package com.opedarkgroup.features.mesaslivres.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.buscamesas.MesaResponse
import com.opedarkgroup.features.listapedidos.view.PedidosActivity
import com.opedarkgroup.features.mesasocupadas.view.adapter.ClickViewContract
import com.opedarkgroup.features.mesaslivres.view.adapter.MesasAdapter
import com.opedarkgroup.features.mesaslivres.viewmodel.BuscaMesasLivresViewModel
import kotlinx.android.synthetic.main.activity_mesas.*

class MesasActivity : AppCompatActivity(), ClickViewContract {

    private val list = listOf<MesaResponse>()

    val lista = listOf(
        MesaResponse(1, 1, 4),
        MesaResponse(2, 2, 4),
        MesaResponse(3, 3, 4),
        MesaResponse(4, 4, 4),
        MesaResponse(5, 5, 5)
    )


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
        val intent = Intent(this, PedidosActivity::class.java)
        intent.putExtra("MESA", mesa)
        startActivity(intent)
    }
}