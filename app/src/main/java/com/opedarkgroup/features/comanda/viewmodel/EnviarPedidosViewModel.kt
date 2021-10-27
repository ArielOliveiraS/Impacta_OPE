package com.opedarkgroup.features.comanda.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.buscapedido.BuscaPedidoBody
import com.opedarkgroup.data.models.buscapedido.BuscaPedidoResponse
import com.opedarkgroup.data.models.enviarpedidos.EnviarPedidoBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EnviarPedidosViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val enviarPedidoResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun enviarPedido(enviarPedidoBody: EnviarPedidoBody) {
        disposable.add(
            RetrofitService.service.enviarPedido(enviarPedidoBody)
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