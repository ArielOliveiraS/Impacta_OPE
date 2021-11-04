package com.opedarkgroup.features.comandaeletronica.listapedidos.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opedarkgroup.R
import com.opedarkgroup.data.models.comanda.buscaprodutoporcategoria.PedidoPorCategoria
import kotlinx.android.synthetic.main.item_por_categoria.view.*

class ItensPorCategoriaAdapter(var list: List<PedidoPorCategoria>, private val listener: ClickPedidoViewContract):
    RecyclerView.Adapter<ItensPorCategoriaAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_por_categoria, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.onBind(item)

        holder.itemView.setOnClickListener {
            listener.onClick(item.id_produto_pk)
        }
    }

    fun updateList(newList: List<PedidoPorCategoria>) {
        //this.list.removeAll(list)
        if (newList != null) {
            this.list = newList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(item: PedidoPorCategoria) {
            itemView.nomeProduto.text = item.produto
            itemView.valorProduto.text =  "R$: ${item.valor}"
        }
    }
}