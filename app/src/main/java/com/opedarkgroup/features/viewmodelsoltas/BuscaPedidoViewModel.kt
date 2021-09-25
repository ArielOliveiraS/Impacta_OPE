package com.opedarkgroup.features.viewmodelsoltas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BuscaPedidoViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val buscarPedidoResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

//    fun buscarPedido(loginResponse: LoginResponse) {
//        disposable.add(
//            RetrofitService.service.bucarPedido()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    buscarPedidoResult.value = true
//                }, { e ->
//                    buscarPedidoResult.value = false
//                    error.value = e.message
//                })
//        )
//    }
}