package com.opedarkgroup.features.mesas.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opedarkgroup.R
import com.opedarkgroup.data.models.MesaResponse
import kotlinx.android.synthetic.main.item_mesa.view.*

class MesasAdapter(var list: List<MesaResponse>):
    RecyclerView.Adapter<MesasAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_mesa, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characters = list[position]
        holder.onBind(characters)
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