package com.opedarkgroup.features.comandaeletronica.comanda.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.API_KEY
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.comanda.enviarpedidos.EnviarPedidoBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EnviarPedidosViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val enviarPedidoResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun enviarPedido(enviarPedidoBody: EnviarPedidoBody) {
        disposable.add(
            RetrofitService.service.enviarPedido(API_KEY, enviarPedidoBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    enviarPedidoResult.value = true
                }, { e ->
                    enviarPedidoResult.value = false
                    error.value = e.message
                })
        )
    }
}