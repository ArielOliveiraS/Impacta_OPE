package com.opedarkgroup.data.models

data class ItemPedido(
    val idItem: Int,
    val idPedido: Int,
    val idProduto: Int,
    val quantidade: Int,
    val valor: Int
)