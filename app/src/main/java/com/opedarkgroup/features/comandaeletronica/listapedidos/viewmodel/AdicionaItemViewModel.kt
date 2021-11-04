package com.opedarkgroup.features.comandaeletronica.listapedidos.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.comanda.ItemPedido
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AdicionaItemViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val adicionaPedidoResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun adicionarItem(itemPedido: ItemPedido) {
        disposable.add(
            RetrofitService.service.adicionarItem(itemPedido)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adicionaPedidoResult.value = true
                    Log.i("teste", "adiciona item sucesso")
                }, { e ->
                    adicionaPedidoResult.value = false
                    error.value = e.message
                    Log.i("teste", "adiciona item erro : ${e.message}")
                })
        )
    }
}