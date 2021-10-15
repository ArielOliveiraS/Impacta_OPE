package com.opedarkgroup.data.models.enviarpedidos

data class EnviarPedidoItemBody(
    val idItem: Int,
    val idPedido: Int,
    val idProduto: Int,
    val quantidade: Int,
)