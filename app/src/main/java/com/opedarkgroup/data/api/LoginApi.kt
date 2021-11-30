package com.opedarkgroup.data.api

import com.opedarkgroup.data.models.admin.adicionamesa.AdicionaMesaBody
import com.opedarkgroup.data.models.admin.adicionaproduto.AdicionaProdutoBody
import com.opedarkgroup.data.models.admin.alterafuncionario.AlteraFuncionarioBody
import com.opedarkgroup.data.models.admin.alteraproduto.AlteraProdutoBody
import com.opedarkgroup.data.models.admin.buscafuncionarios.BuscaFuncionariosResponse
import com.opedarkgroup.data.models.admin.criafuncionario.CriaFuncionarioBody
import com.opedarkgroup.data.models.admin.removefuncionario.RemoveFuncionarioBody
import com.opedarkgroup.data.models.admin.removemesa.RemoveMesaBody
import com.opedarkgroup.data.models.admin.removeproduto.RemoveProdutoBody
import com.opedarkgroup.data.models.admin.resetsenha.ResetSenhaBody
import com.opedarkgroup.data.models.comanda.ItemPedido
import com.opedarkgroup.data.models.comanda.PedidoResponse
import com.opedarkgroup.data.models.comanda.buscamesas.MesaResponse
import com.opedarkgroup.data.models.comanda.buscapedido.BuscaPedidoBody
import com.opedarkgroup.data.models.comanda.buscapedido.BuscaPedidoResponse
import com.opedarkgroup.data.models.comanda.buscaprodutoporcategoria.CategoriaProduto
import com.opedarkgroup.data.models.comanda.buscaprodutoporcategoria.PedidoPorCategoria
import com.opedarkgroup.data.models.comanda.criapedido.CriaPedidoBody
import com.opedarkgroup.data.models.comanda.criapedido.CriaPedidoResponse
import com.opedarkgroup.data.models.comanda.encerrarpedido.EncerrarPedidoBody
import com.opedarkgroup.data.models.comanda.enviarpedidos.EnviarPedidoBody
import com.opedarkgroup.data.models.login.LoginBody
import com.opedarkgroup.data.models.login.LoginResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

const val API_KEY = "47E9C885476DB53CBDDE82E7475DA8C0B4D0DE91FE689088EEF509AD4886EAC7"

interface LoginApi {

    //**************************************** ADMIN ***********************************//

    @POST("/AdmEmployee/CriaFuncionario")
    fun criaFuncionario(
        @Header("ApiKey") ApiKey: String,
        @Body criaFuncionarioBody: CriaFuncionarioBody
    ): Completable

    @POST("/AdmEmployee/RemoveFuncionario")
    fun removeFuncionario(
        @Header("ApiKey") ApiKey: String,
        @Body removeFuncionarioBody: RemoveFuncionarioBody
    ): Completable

    @POST("/AdmEmployee/AlteraFuncionario")
    fun alteraFuncionario(
        @Header("ApiKey") ApiKey: String,
        @Body alteraFuncionarioBody: AlteraFuncionarioBody
    ): Completable

    @GET("/AdmEmployee/BuscaFuncionarios")
    fun buscaFuncionarios(@Header("ApiKey") ApiKey: String): Single<List<BuscaFuncionariosResponse>>

    @POST("/AdmEmployee/ResetSenha")
    fun resetSenha(
        @Header("ApiKey") ApiKey: String,
        @Body resetSenhaBody: ResetSenhaBody
    ): Completable

    @POST("/AdmProduct/AdicionaProduto")
    fun adicionaProduto(
        @Header("ApiKey") ApiKey: String,
        @Body adicionaProdutoBody: AdicionaProdutoBody
    ): Completable

    @POST("/AdmProduct/RemoveProduto")
    fun removeProduto(
        @Header("ApiKey") ApiKey: String,
        @Body removeProdutoBody: RemoveProdutoBody
    ): Completable

    @POST("/AdmProduct/AlteraProduto")
    fun alteraProduto(
        @Header("ApiKey") ApiKey: String,
        @Body alteraProdutoBody: AlteraProdutoBody
    ): Completable

    @POST("/AdmTable/AdicionaMesa")
    fun adicionaMesa(
        @Header("ApiKey") ApiKey: String,
        @Body adicionaMesaBody: AdicionaMesaBody
    ): Completable

    @POST("/AdmTable/RemoveMesa")
    fun removeMesa(
        @Header("ApiKey") ApiKey: String,
        @Body removeMesaBody: RemoveMesaBody
    ): Completable

    @GET("/AdmTable/BuscaMesas")
    fun buscaTodasAsMesas(@Header("ApiKey") ApiKey: String): Single<List<MesaResponse>>


    //*************************************** COMANDA **********************************//

    @POST("/Login/Login")
    fun getLogin(
        @Header("ApiKey") ApiKey: String,
        @Body loginBody: LoginBody
    ): Single<LoginResponse>

    @POST("/Order/BuscaProduto")
    fun buscarProdutos(
        @Header("ApiKey") ApiKey: String,
        @Body categoria: CategoriaProduto
    ): Single<List<PedidoPorCategoria>>

    @GET("/Order/BuscaMesasLivres")
    fun buscarMesasLivres(@Header("ApiKey") ApiKey: String): Single<List<MesaResponse>>

    @GET("/Order/BuscaMesasOcupadas")
    fun buscarMesasOcupadas(@Header("ApiKey") ApiKey: String): Single<List<MesaResponse>>

    @POST("/Order/BuscaPedido")
    fun buscarPedido(
        @Header("ApiKey") ApiKey: String,
        @Body buscaPedidoBody: BuscaPedidoBody
    ): Single<BuscaPedidoResponse>

    @POST("/Order/AdicionaItem")
    fun adicionarItem(
        @Header("ApiKey") ApiKey: String,
        @Body itemResponse: ItemPedido
    ): Completable

    @POST("/Order/EnviaPedido")
    fun enviarPedido(
        @Header("ApiKey") ApiKey: String,
        @Body enviarPedidoBody: EnviarPedidoBody
    ): Completable

    @POST("/Order/CriaPedido")
    fun criaPedido(
        @Header("ApiKey") ApiKey: String,
        @Body criaPedidoBody: CriaPedidoBody
    ): Single<CriaPedidoResponse>

    @POST("/Order/EncerraPedido")
    fun encerrarPedido(
        @Header("ApiKey") ApiKey: String,
        @Body encerrarPedidoBody: EncerrarPedidoBody
    ): Completable
}