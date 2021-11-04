package com.opedarkgroup.features.comandaeletronica.mesaslivres.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.comanda.criapedido.CriaPedidoBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CriaPedidoViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val criaPedidoResult: MutableLiveData<Int> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun criaPedido(criaPedidoBody: CriaPedidoBody) {
        disposable.add(
            RetrofitService.service.criaPedido(criaPedidoBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    criaPedidoResult.value = it.pedidoId
                    Log.i("teste", "buscarMesasLivres sucesso")
                }, { e ->
                    error.value = e.message
                    Log.i("teste", e.message ?: "buscarMesasLivres erro")
                })
        )
    }
}