package com.opedarkgroup.features.comandaeletronica.listapedidos.view.adapter


interface ClickPedidoViewContract {

    fun onClick(produtoId: Int, nomeProduto: String? = null, valorProduto: Float? = null)
}