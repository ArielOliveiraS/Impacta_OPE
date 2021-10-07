package com.opedarkgroup.features.listapedidos.view

import android.content.Intent
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

        setaBebidas.setOnClickListener {
            startActivity(Intent(this, PedidosPorCategoriaActivity::class.java))
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