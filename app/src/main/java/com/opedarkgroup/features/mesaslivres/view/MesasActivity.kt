package com.opedarkgroup.features.mesaslivres.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.opedarkgroup.R
import com.opedarkgroup.data.models.buscamesas.MesaResponse
import com.opedarkgroup.data.models.criapedido.CriaPedidoBody
import com.opedarkgroup.features.listapedidos.view.PedidosActivity
import com.opedarkgroup.features.mesasocupadas.view.adapter.ClickViewContract
import com.opedarkgroup.features.mesaslivres.view.adapter.MesasAdapter
import com.opedarkgroup.features.mesaslivres.viewmodel.BuscaMesasLivresViewModel
import com.opedarkgroup.features.mesaslivres.viewmodel.CriaPedidoViewModel
import kotlinx.android.synthetic.main.activity_mesas.*

class MesasActivity : AppCompatActivity(), ClickViewContract {

    private val list = listOf<MesaResponse>()
    lateinit var criaPedidoViewModel: CriaPedidoViewModel

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
        var idPedido: Int = 0
        criaPedidoViewModel = ViewModelProviders.of(this).get(CriaPedidoViewModel::class.java)

        criaPedidoViewModel.criaPedido(CriaPedidoBody(mesa, 1))
        criaPedidoViewModel.criaPedidoResult.observe(this, Observer {
            Log.i("teste", "viewmodel cria pedido observer")
//           idPedido = it

            val intent = Intent(this, PedidosActivity::class.java)
            var bundle = Bundle()

            bundle.putInt("MESA", mesa)
            bundle.putInt("ID_PEDIDO", it)
            intent.putExtras(bundle)

//            Bundle bundle = new Bundle();
//            bundle.putString("id_do_item", "seu_dado");
//            Intent intent = new Intent(context, MinhaOutraActivity.class);
//            intent.putExtra(bundle);
//            startActivity(intent);


//            intent.putExtra("MESA", mesa)
//            intent.putExtra("ID_PEDIDO", it)
            startActivity(intent)

        })

//        val intent = Intent(this, PedidosActivity::class.java)
//        intent.putExtra("MESA", mesa)
//        intent.putExtra("ID_PEDIDO", idPedido)
//        startActivity(intent)
    }
}