package com.opedarkgroup.data.models.comanda.buscaprodutoporcategoria

data class PedidoPorCategoria(
    val id_produto_pk: Int,
    val produto: String,
    val id_categoria_fk: Int,
    val valor: Float
)