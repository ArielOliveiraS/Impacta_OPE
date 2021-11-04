package com.opedarkgroup.features.comandaeletronica.mesaslivres.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opedarkgroup.R
import com.opedarkgroup.data.models.comanda.buscamesas.MesaResponse
import com.opedarkgroup.features.comandaeletronica.mesasocupadas.view.adapter.ClickViewContract
import kotlinx.android.synthetic.main.item_mesa.view.*

class MesasAdapter(var list: List<MesaResponse>, private val listener: ClickViewContract):
    RecyclerView.Adapter<MesasAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_mesa, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mesa = list[position]
        holder.onBind(mesa)

        holder.itemView.setOnClickListener {
            listener.onClick(mesa.id_mesa_pk)
        }
    }

    fun updateList(newList: List<MesaResponse>) {
        //this.list.removeAll(list)
        if (newList != null) {
            this.list = newList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(mesaResponse: MesaResponse) {
            itemView.mesaItem.text = mesaResponse.id_mesa_pk.toString()
        }
    }
}