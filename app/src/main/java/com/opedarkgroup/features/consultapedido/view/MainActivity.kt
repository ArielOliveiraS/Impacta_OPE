package com.opedarkgroup.features.consultapedido.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.opedarkgroup.R
import com.opedarkgroup.features.mesas.view.MesasActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFazerPedido.setOnClickListener {
            startActivity(Intent(this, MesasActivity::class.java))
        }
    }
}