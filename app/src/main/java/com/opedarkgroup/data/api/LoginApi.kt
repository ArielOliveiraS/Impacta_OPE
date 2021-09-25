package com.opedarkgroup.data.api

import com.opedarkgroup.data.models.LoginResponse
import com.opedarkgroup.data.models.MesaResponse
import com.opedarkgroup.data.models.PedidoResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {

    @POST("/Login/Login")
    fun getLogin(@Body loginResponse: LoginResponse) : Completable

//    @GET("/Order/BuscarProdutos")
//    fun buscarProdutos() : Single<AlgumaCoisa>

    @GET("/Order/BuscarMesasLivres")
    fun buscarMesasLivres() : Single<List<MesaResponse>>

    @POST("/Order/AlternarStatusMesa")
    fun alternarStatusMesa(@Body mesaResponse: MesaResponse) : Completable

//    @GET("/Order/BuscarPedido")
//    fun buscarPedido() : Single<List<AlgumaCoisa>>

    @POST("/Order/AlternarStatusPedido")
    fun alternarStatusPedido(@Body pedidoResponse: PedidoResponse) : Completable

    @POST("/Order/AdicionarPedido")
    fun acicionarPedido(@Body pedidoResponse: PedidoResponse) : Completable

}