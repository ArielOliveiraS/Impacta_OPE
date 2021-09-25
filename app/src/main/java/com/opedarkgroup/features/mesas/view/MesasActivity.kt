package com.opedarkgroup.features.mesas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.MesaResponse
import com.opedarkgroup.features.mesas.view.adapter.MesasAdapter
import com.opedarkgroup.features.mesas.viewmodel.BuscaMesasLivresViewModel
import kotlinx.android.synthetic.main.activity_mesas.*

class MesasActivity : AppCompatActivity() {

    private val list = listOf<MesaResponse>()

    val lista = listOf(
        MesaResponse(1, 1, 4),
        MesaResponse(2, 2, 4),
        MesaResponse(3, 3, 4),
        MesaResponse(4, 4, 4),
        MesaResponse(5, 5, 5))


    private val adapter = MesasAdapter(lista)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        btnMesaVoltar.setOnClickListener {
            finish()
        }

        val viewModel = ViewModelProviders.of(this).get(BuscaMesasLivresViewModel::class.java)
        recyclerViewMesas.adapter = adapter
        recyclerViewMesas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.buscarMesasLivres()

        viewModel.buscarMesasLivresResult.observe(this, Observer {
            adapter.updateList(it)
        })
    }

}