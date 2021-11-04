package com.opedarkgroup.features.comandaeletronica.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.opedarkgroup.R
import com.opedarkgroup.features.comandaeletronica.mesasocupadas.view.ConsultaMesasAbertasActivity
import com.opedarkgroup.features.comandaeletronica.mesaslivres.view.MesasActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFazerPedido.setOnClickListener {
            startActivity(Intent(this, MesasActivity::class.java))
        }

        btnConsultarPedido.setOnClickListener {
            startActivity(Intent(this, ConsultaMesasAbertasActivity::class.java))
        }
    }
}