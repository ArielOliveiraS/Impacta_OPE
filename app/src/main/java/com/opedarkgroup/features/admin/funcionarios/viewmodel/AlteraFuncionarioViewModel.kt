package com.opedarkgroup.features.admin.funcionarios.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opedarkgroup.data.api.API_KEY
import com.opedarkgroup.data.api.RetrofitService
import com.opedarkgroup.data.models.admin.alterafuncionario.AlteraFuncionarioBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlteraFuncionarioViewModel : ViewModel() {

    val disposable = CompositeDisposable()
    val alteraFuncionarioResult: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun alteraFuncionario(alteraFuncionarioBody: AlteraFuncionarioBody) {
        disposable.add(
            RetrofitService.service.alteraFuncionario(API_KEY, alteraFuncionarioBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    alteraFuncionarioResult.value = true
                }, { e ->
                    alteraFuncionarioResult.value = false
                    error.value = e.message
                })
        )
    }
}