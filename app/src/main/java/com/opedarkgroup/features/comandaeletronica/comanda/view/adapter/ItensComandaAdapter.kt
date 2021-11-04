package com.opedarkgroup.features.comandaeletronica.comanda.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opedarkgroup.R
import com.opedarkgroup.data.models.comanda.buscapedido.BuscaPedidoItemResponse
import kotlinx.android.synthetic.main.item_pedido_adicionado.view.*

class ItensComandaAdapter(var list: List<BuscaPedidoItemResponse>):
    RecyclerView.Adapter<ItensComandaAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_pedido_adicionado, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mesa = list[position]
        holder.onBind(mesa)
    }

    fun updateList(newList: List<BuscaPedidoItemResponse>) {
        if (newList != null) {
            this.list = newList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(itemPedido: BuscaPedidoItemResponse) {
            itemView.nomeProduto.text = itemPedido.nomeProduto
            itemView.valorProduto.text = itemPedido.valor.toString()
        }
    }
}