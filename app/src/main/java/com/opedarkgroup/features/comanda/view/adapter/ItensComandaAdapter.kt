package com.opedarkgroup.features.comanda.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opedarkgroup.R
import com.opedarkgroup.data.models.PedidoPorCategoria
import kotlinx.android.synthetic.main.item_pedido_adicionado.view.*

class ItensComandaAdapter(var list: List<PedidoPorCategoria>):
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

    fun updateList(newList: List<PedidoPorCategoria>) {
        //this.list.removeAll(list)
        if (newList != null) {
            this.list = newList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(itemPedido: PedidoPorCategoria) {
            itemView.nomeProduto.text = itemPedido.produto
            itemView.valorProduto.text = itemPedido.valor.toString()
        }
    }
}