package com.opedarkgroup.data.models.comanda

data class PedidoResponse(
    val IdMesa: Int,
    val IdFuncionario: Int,
    val Itens : List<ItemPedido>
)