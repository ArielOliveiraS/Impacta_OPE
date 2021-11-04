package com.opedarkgroup.features.comandaeletronica.mesasocupadas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.comanda.buscamesas.MesaResponse
import com.opedarkgroup.features.comandaeletronica.mesasocupadas.view.adapter.ClickViewContract
import com.opedarkgroup.features.comandaeletronica.mesaslivres.view.adapter.MesasAdapter
import com.opedarkgroup.features.comandaeletronica.mesasocupadas.viewmodel.BuscaMesasOcupadasViewModel
import com.opedarkgroup.features.comandaeletronica.pedidosmesaocupada.view.PedidosMesaAbertaActivity
import kotlinx.android.synthetic.main.activity_consulta_mesas_abertas.*

class ConsultaMesasAbertasActivity : AppCompatActivity(), ClickViewContract {

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
        setContentView(R.layout.activity_consulta_mesas_abertas)

        btnMesaAbertasVoltar.setOnClickListener {
            finish()
        }

        val viewModel = ViewModelProviders.of(this).get(BuscaMesasOcupadasViewModel::class.java)
        recyclerViewMesasAbertas.adapter = adapter
        recyclerViewMesasAbertas.layoutManager = GridLayoutManager(this, 3)

        viewModel.buscarMesasOcupadas()

        viewModel.buscarMesasOcupadasResult.observe(this, Observer {
            adapter.updateList(it)
        })
    }

    override fun onClick(mesa: Int) {
        val intent = Intent(this, PedidosMesaAbertaActivity::class.java)
        intent.putExtra("MESA", mesa)
        startActivity(intent)
    }
}