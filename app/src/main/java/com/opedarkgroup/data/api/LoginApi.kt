package com.opedarkgroup.data.api

import com.opedarkgroup.data.models.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {

    @POST("/Login/Login")
    fun getLogin(@Body loginResponse: LoginResponse) : Completable

    @POST("/Order/BuscaProduto")
    fun buscarProdutos(@Body categoria: CategoriaProduto) : Single<List<PedidoPorCategoria>>

    @GET("/Order/BuscaMesasLivres")
    fun buscarMesasLivres() : Single<List<MesaResponse>>

    @GET("/Order/BuscaMesasOcupadas")
    fun buscarMesasOcupadas() : Single<List<MesaResponse>>

    @POST("/Order/AlteraStatusMesa")
    fun alterarStatusMesa(@Body mesaResponse: MesaResponse) : Completable

    @POST("/Order/BuscaPedido")
    fun buscarPedido(@Body buscaPedidoBody: BuscaPedidoBody) : Single<BuscaPedidoResponse>

    @POST("/Order/AlteraStatusPedido")
    fun alterarStatusPedido(@Body pedidoResponse: PedidoResponse) : Completable

    @POST("/Order/AdicionaPedido")
    fun adicionarPedido(@Body pedidoResponse: PedidoResponse) : Completable

    @POST("/Order/AdicionaItem")
    fun adicionarItem(@Body itemResponse: ItemPedido) : Completable
}