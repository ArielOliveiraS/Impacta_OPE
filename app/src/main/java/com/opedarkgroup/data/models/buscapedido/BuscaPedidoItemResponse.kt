package com.opedarkgroup.data.models.buscapedido

data class BuscaPedidoItemResponse(
    val id_item_pk: Int,
    val id_pedido_fk: Int,
    val id_produto_fk: Int,
    val quantidade: Int,
    val valor: Float,
    val nomeProduto:String
)