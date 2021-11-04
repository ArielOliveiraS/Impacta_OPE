package com.opedarkgroup.data.models.comanda.buscapedido

data class BuscaPedidoResponse(
    val mensagem: String,
    val pedidoResponse: RetornoBuscaPedidoResponse? = null,
    val pedidoId: Int
)
