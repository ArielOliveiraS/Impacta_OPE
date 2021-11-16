package com.opedarkgroup.features.admin.funcionarios.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.admin.removefuncionario.RemoveFuncionarioBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RemoveFuncionarioViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val removeFuncionarioResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun removeFuncionario(removeFuncionarioBody: RemoveFuncionarioBody) {
        disposable.add(
            RetrofitService.service.removeFuncionario(removeFuncionarioBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    removeFuncionarioResult.value = true
                }, { e ->
                    removeFuncionarioResult.value = false
                    error.value = e.message
                })
        )
    }
}