package com.opedarkgroup.data.models

data class MesaResponse(
    val id_mesa_pk : Int,
    val id_status_fk: Int,
    val pessoas: Int
)