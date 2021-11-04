package com.opedarkgroup.features.admin.viewmodelsoltas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.admin.removeproduto.RemoveProdutoBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RemoveProdutoViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val removeProdutoResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun removeProduto(removeProdutoBody: RemoveProdutoBody) {
        disposable.add(
            RetrofitService.service.removeProduto(removeProdutoBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    removeProdutoResult.value = true
                }, { e ->
                    removeProdutoResult.value = false
                    error.value = e.message
                })
        )
    }
}