package com.opedarkgroup.features.admin.funcionarios.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opedarkgroup.R
import com.opedarkgroup.data.models.admin.buscafuncionarios.BuscaFuncionariosResponse
import kotlinx.android.synthetic.main.item_funcionario.view.*

class FuncionariosAdapter (var list: List<BuscaFuncionariosResponse>, private val listener: FuncionarioOnClick):
    RecyclerView.Adapter<FuncionariosAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_funcionario, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val funcionario = list[position]
        holder.onBind(funcionario)

        holder.itemView.setOnClickListener {
            listener.onClick(funcionario)
        }
    }

    fun updateList(newList: List<BuscaFuncionariosResponse>) {
        //this.list.removeAll(list)
        if (newList != null) {
            this.list = newList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(buscaFuncionariosResponse: BuscaFuncionariosResponse) {
            itemView.textViewNomeFuncionario.text = buscaFuncionariosResponse.nome
        }
    }
}