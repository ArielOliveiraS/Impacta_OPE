package com.opedarkgroup.data.models

data class PedidoResponse(
    val idPedido: Int,
    val idMesa: Int,
    val idFuncionario: Int,
    val statusPedido: String,
    val data: String,
    val itens : List<ItemPedido>
)