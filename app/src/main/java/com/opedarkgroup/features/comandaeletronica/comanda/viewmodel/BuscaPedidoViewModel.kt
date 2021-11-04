package com.opedarkgroup.features.comandaeletronica.comanda.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.comanda.buscapedido.BuscaPedidoBody
import com.opedarkgroup.data.models.comanda.buscapedido.BuscaPedidoResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BuscaPedidoViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val buscarPedidoResult: MutableLiveData<BuscaPedidoResponse> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun buscarPedido(buscaPedidoBody: BuscaPedidoBody) {
        disposable.add(
            RetrofitService.service.buscarPedido(buscaPedidoBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    buscarPedidoResult.value = it
                }, { e ->
                    error.value = e.message
                })
        )
    }
}