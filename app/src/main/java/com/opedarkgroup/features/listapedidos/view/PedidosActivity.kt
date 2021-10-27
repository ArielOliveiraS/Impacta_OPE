package com.opedarkgroup.features.listapedidos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.opedarkgroup.R
import com.opedarkgroup.features.comanda.view.VisualizarComandaActivity
import kotlinx.android.synthetic.main.activity_pedidos.*

const val CATEGORIA_PEDIDO = "categoria"
const val ID_MESA = "idMesa"

class PedidosActivity : AppCompatActivity() {

    private var numeroMesa: Int = 0
    var idPedido: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        numeroMesa = intent.getIntExtra("MESA", -1)
        idPedido = intent.getIntExtra("ID_PEDIDO", -1)

        numeroMesaTxt.text = "Mesa $numeroMesa"

        selecionarCategoriaPedido()

        btnVizualizarComanda.setOnClickListener {
            val intent = Intent(this, VisualizarComandaActivity::class.java)
            intent.putExtra(ID_MESA, numeroMesa)
            intent.putExtra("ID_PEDIDO2", idPedido)
            startActivity(intent)
        }
    }

    private fun selecionarCategoriaPedido() {

        setaBebidas.setOnClickListener {
            val intent = Intent(this, PedidosPorCategoriaActivity::class.java)
            intent.putExtra(CATEGORIA_PEDIDO, "Bebidas")
            intent.putExtra(ID_MESA, numeroMesa)
            intent.putExtra("ID_PEDIDO2", idPedido)
            startActivity(intent)
        }

        constraintPratos.setOnClickListener {
            val intent = Intent(this, PedidosPorCategoriaActivity::class.java)
            intent.putExtra(CATEGORIA_PEDIDO, "Pratos")
            intent.putExtra(ID_MESA, numeroMesa)
            intent.putExtra("ID_PEDIDO2", idPedido)
            startActivity(intent)
        }

        constraintSobremesas.setOnClickListener {
            val intent = Intent(this, PedidosPorCategoriaActivity::class.java)
            intent.putExtra(CATEGORIA_PEDIDO, "Sobremesa")
            intent.putExtra(ID_MESA, numeroMesa)
            intent.putExtra("ID_PEDIDO2", idPedido)
            startActivity(intent)
        }
    }
}