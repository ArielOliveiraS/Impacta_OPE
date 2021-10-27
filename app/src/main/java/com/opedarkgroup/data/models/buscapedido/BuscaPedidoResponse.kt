package com.opedarkgroup.data.models.buscapedido

data class BuscaPedidoResponse(
    val mensagem: String,
    val pedidoResponse: RetornoBuscaPedidoResponse? = null,
    val pedidoId: Int
)
