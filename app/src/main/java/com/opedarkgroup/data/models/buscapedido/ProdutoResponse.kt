package com.opedarkgroup.data.models.buscapedido

data class ProdutoResponse(
    val id_produto_pk: Int,
    val produto: String,
    val id_categoria_fk: Int,
    val valor: Float
)