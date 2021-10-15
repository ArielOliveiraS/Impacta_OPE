package com.opedarkgroup.data.models.buscapedido

import com.opedarkgroup.data.models.buscapedido.BuscaPedidoItemResponse

data class BuscaPedidoResponse(
    val id_pedido_pk: Int,
    val id_mesa_fk: Int,
    val id_funcionario_fk: Int,
    val id_status_ped_fk: Int,
    val data: String,
    val itens: List<BuscaPedidoItemResponse>? = null
)
