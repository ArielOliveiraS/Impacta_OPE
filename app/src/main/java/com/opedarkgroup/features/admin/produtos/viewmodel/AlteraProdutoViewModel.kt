package com.opedarkgroup.features.admin.produtos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.API_KEY
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.admin.alteraproduto.AlteraProdutoBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlteraProdutoViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val alteraProdutoResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun alteraProduto(alteraProdutoBody: AlteraProdutoBody) {
        disposable.add(
            RetrofitService.service.alteraProduto(API_KEY, alteraProdutoBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    alteraProdutoResult.value = true
                }, { e ->
                    alteraProdutoResult.value = false
                    error.value = e.message
                })
        )
    }
}