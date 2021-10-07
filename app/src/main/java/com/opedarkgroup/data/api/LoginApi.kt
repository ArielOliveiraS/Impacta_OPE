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

    @POST("/Order/AlteraStatusMesa")
    fun alterarStatusMesa(@Body mesaResponse: MesaResponse) : Completable

//    @GET("/Order/BuscaPedido")
//    fun buscarPedido() : Single<List<AlgumaCoisa>>

    @POST("/Order/AlteraStatusPedido")
    fun alterarStatusPedido(@Body pedidoResponse: PedidoResponse) : Completable

    @POST("/Order/AdicionaPedido")
    fun acicionarPedido(@Body pedidoResponse: PedidoResponse) : Completable

}