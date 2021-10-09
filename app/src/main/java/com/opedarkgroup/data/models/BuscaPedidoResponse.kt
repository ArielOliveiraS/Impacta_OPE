package com.opedarkgroup.data.models

data class BuscaPedidoResponse(
    val id_pedido_pk: Int,
    val id_mesa_fk: Int,
    val id_funcionario_fk: Int,
    val id_status_ped_fk: Int,
    val data: String,
    //val itens: List<PedidoPorCategoria>? = null
)
