package com.opedarkgroup.features.admin.mesas.view.adapter

import com.opedarkgroup.data.models.comanda.buscamesas.MesaResponse

interface MesasConfigOnClick {
    fun onClick(mesaResponse: MesaResponse)
}