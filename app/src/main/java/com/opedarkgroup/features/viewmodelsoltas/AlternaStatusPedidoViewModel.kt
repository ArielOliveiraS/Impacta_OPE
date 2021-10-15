package com.opedarkgroup.features.viewmodelsoltas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.buscamesas.MesaResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlternaStatusPedidoViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val alternaStatusPedidoResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun alternarStatusMesa(mesaResponse: MesaResponse) {
        disposable.add(
            RetrofitService.service.alterarStatusMesa(mesaResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    alternaStatusPedidoResult.value = true
                }, { e ->
                    alternaStatusPedidoResult.value = false
                    error.value = e.message
                })
        )
    }
}