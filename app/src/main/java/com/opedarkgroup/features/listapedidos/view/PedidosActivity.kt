package com.opedarkgroup.features.listapedidos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.opedarkgroup.R
import kotlinx.android.synthetic.main.activity_pedidos.*

class PedidosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        val numeroMesa: Int = intent.getIntExtra("MESA", -1)

        numeroMesaTxt.text = "Mesa $numeroMesa"

        selecionarCategoriaPedido()

        btnVizualizarComanda.setOnClickListener {

        }


    }

    private fun selecionarCategoriaPedido() {

        constraintBebidas.setOnClickListener {

        }

        constraintCarnes.setOnClickListener {

        }

        constraintAves.setOnClickListener {

        }

        constraintFrutosDoMar.setOnClickListener {

        }

        constraintBebidas.setOnClickListener {

        }
    }
}