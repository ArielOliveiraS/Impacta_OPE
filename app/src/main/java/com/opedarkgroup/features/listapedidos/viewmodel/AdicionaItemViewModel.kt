package com.opedarkgroup.features.listapedidos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.ItemPedido
import com.opedarkgroup.data.models.PedidoResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AdicionaItemViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val adicionaPedidoResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun adicionarPedido(itemPedido: ItemPedido) {
        disposable.add(
            RetrofitService.service.adicionarItem(itemPedido)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adicionaPedidoResult.value = true
                }, { e ->
                    adicionaPedidoResult.value = false
                    error.value = e.message
                })
        )
    }
}