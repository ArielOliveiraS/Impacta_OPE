package com.opedarkgroup.features.comandaeletronica.pedidosmesaocupada.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.API_KEY
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.comanda.encerrarpedido.EncerrarPedidoBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EncerrarPedidoViewModel : ViewModel(){

    val disposable = CompositeDisposable()
    val pedidoEncerradoResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun encerrarPedido(encerrarPedidoBody: EncerrarPedidoBody) {
        disposable.add(
            RetrofitService.service.encerrarPedido(API_KEY, encerrarPedidoBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    pedidoEncerradoResult.value = true
                }, { e ->
                    pedidoEncerradoResult.value = false
                    error.value = e.message
                })
        )
    }
}