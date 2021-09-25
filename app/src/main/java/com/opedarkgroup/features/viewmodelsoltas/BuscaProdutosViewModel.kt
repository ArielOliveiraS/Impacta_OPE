package com.opedarkgroup.features.viewmodelsoltas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BuscaProdutosViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val buscarProdutosResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

//    fun buscarProdutos(loginResponse: LoginResponse) {
//        disposable.add(
//            RetrofitService.service.buscarProdutos()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    buscarProdutosResult.value = true
//                }, { e ->
//                    buscarProdutosResult.value = false
//                    error.value = e.message
//                })
//        )
//    }
}