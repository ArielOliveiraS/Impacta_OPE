package com.opedarkgroup.features.admin.produtos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.admin.adicionaproduto.AdicionaProdutoBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AdicionaProdutoViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val adicionaProdutoResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun adicionaProduto(adicionaProdutoBody: AdicionaProdutoBody) {
        disposable.add(
            RetrofitService.service.adicionaProduto(adicionaProdutoBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adicionaProdutoResult.value = true
                }, { e ->
                    adicionaProdutoResult.value = false
                    error.value = e.message
                })
        )
    }
}