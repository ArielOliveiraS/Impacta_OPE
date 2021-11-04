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
import retrofit2.http.POST

interface LoginApi {

    //**************************************** ADMIN ***********************************//

    @POST("/AdmEmployee/CriaFuncionario")
    fun criaFuncionario(@Body criaFuncionarioBody: CriaFuncionarioBody) : Completable

    @POST("/AdmEmployee/RemoveFuncionario")
    fun removeFuncionario(@Body removeFuncionarioBody: RemoveFuncionarioBody) : Completable

    @POST("/AdmEmployee/AlteraFuncionario")
    fun alteraFuncionario(@Body alteraFuncionarioBody: AlteraFuncionarioBody) : Completable

    @GET("/AdmEmployee/BuscaFuncionarios")
    fun buscaFuncionarios() : Single<List<BuscaFuncionariosResponse>>

    @POST("/AdmEmployee/ResetSenha")
    fun resetSenha(@Body resetSenhaBody: ResetSenhaBody) : Completable

    @POST("/AdmEmployee/AdicionaProduto")
    fun adicionaProduto(@Body adicionaProdutoBody: AdicionaProdutoBody) : Completable

    @POST("/AdmEmployee/RemoveProduto")
    fun removeProduto(@Body removeProdutoBody: RemoveProdutoBody) : Completable

    @POST("/AdmEmployee/AlteraProduto")
    fun alteraProduto(@Body alteraProdutoBody: AlteraProdutoBody) : Completable

    @POST("/AdmEmployee/AdicionaMesa")
    fun adicionaMesa(@Body adicionaMesaBody: AdicionaMesaBody) : Completable

    @POST("/AdmEmployee/RemoveMesa")
    fun removeMesa(@Body removeMesaBody: RemoveMesaBody) : Completable

    @GET("/AdmEmployee/BuscaMesas")
    fun buscaTodasAsMesas() : Single<List<MesaResponse>>


    //*************************************** COMANDA **********************************//

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