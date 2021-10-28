package com.opedarkgroup.data.api

import com.opedarkgroup.data.models.*
import com.opedarkgroup.data.models.buscamesas.MesaResponse
import com.opedarkgroup.data.models.buscapedido.BuscaPedidoBody
import com.opedarkgroup.data.models.buscapedido.BuscaPedidoResponse
import com.opedarkgroup.data.models.buscaprodutoporcategoria.CategoriaProduto
import com.opedarkgroup.data.models.buscaprodutoporcategoria.PedidoPorCategoria
import com.opedarkgroup.data.models.criapedido.CriaPedidoBody
import com.opedarkgroup.data.models.criapedido.CriaPedidoResponse
import com.opedarkgroup.data.models.encerrarpedido.EncerrarPedidoBody
import com.opedarkgroup.data.models.enviarpedidos.EnviarPedidoBody
import com.opedarkgroup.data.models.login.LoginBody
import com.opedarkgroup.data.models.login.LoginResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {

    @POST("/Login/Login")
    fun getLogin(@Body loginBody: LoginBody) : Single<LoginResponse>

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

    @POST("/Order/AdicionaItem")
    fun adicionarItem(@Body itemResponse: ItemPedido) : Completable

    @POST("/Order/EnviaPedido")
    fun enviarPedido(@Body enviarPedidoBody: EnviarPedidoBody) : Completable

    @POST("/Order/CriaPedido")
    fun criaPedido(@Body criaPedidoBody: CriaPedidoBody) : Single<CriaPedidoResponse>

    @POST("/Order/EncerraPedido")
    fun encerrarPedido(@Body encerrarPedidoBody: EncerrarPedidoBody) : Completable
}