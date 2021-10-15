package com.opedarkgroup.data.models.enviarpedidos

data class EnviarPedidoBody(
    val idPedido: Int,
    val idMesa: Int,
    val idFuncionario: Int,
    val statusPedido: String,
    val data: String,
    val itens: List<EnviarPedidoItemBody>
)