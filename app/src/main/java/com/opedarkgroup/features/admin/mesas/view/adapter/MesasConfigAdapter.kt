package com.opedarkgroup.features.admin.mesas.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.buscafuncionarios.BuscaFuncionariosResponse
import com.opedarkgroup.data.models.comanda.buscamesas.MesaResponse
import com.opedarkgroup.features.admin.funcionarios.view.adapter.FuncionarioOnClick
import com.opedarkgroup.features.admin.funcionarios.view.adapter.FuncionariosAdapter
import kotlinx.android.synthetic.main.item_config_mesas.view.*
import kotlinx.android.synthetic.main.item_funcionario.view.*

class MesasConfigAdapter (var list: List<MesaResponse>, private val listener: MesasConfigOnClick):
    RecyclerView.Adapter<MesasConfigAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_config_mesas, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mesa = list[position]
        holder.onBind(mesa)

        holder.itemView.setOnClickListener {
            listener.onClick(mesa)
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
            itemView.textViewMesaConfig.text = "Mesa ${mesaResponse.id_mesa_pk}"
        }
    }
}